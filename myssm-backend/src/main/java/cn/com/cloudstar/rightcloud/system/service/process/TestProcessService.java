package cn.com.cloudstar.rightcloud.system.service.process;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import cn.com.cloudstar.rightcloud.framework.common.exception.BizException;
import cn.com.cloudstar.rightcloud.framework.common.pojo.AuthUser;
import cn.com.cloudstar.rightcloud.framework.common.util.RequestContextUtil;
import cn.com.cloudstar.rightcloud.system.activiti.util.IdGen;
import cn.com.cloudstar.rightcloud.system.activiti.util.ProcessConstants;
import cn.com.cloudstar.rightcloud.system.activiti.util.StaticDir;
import cn.com.cloudstar.rightcloud.system.dao.act.ProcessMapper;
import cn.com.cloudstar.rightcloud.system.dao.act.ProcessNodeMapper;
import cn.com.cloudstar.rightcloud.system.dao.act.ProcessVersionMapper;
import cn.com.cloudstar.rightcloud.system.pojo.User;
import cn.com.cloudstar.rightcloud.system.pojo.dto.process.ProcessNode;
import cn.com.cloudstar.rightcloud.system.pojo.dto.process.ProcessVersion;
import cn.com.cloudstar.rightcloud.system.pojo.dto.process.ServiceOrder;
import cn.com.cloudstar.rightcloud.system.service.RoleService;
import cn.com.cloudstar.rightcloud.system.service.UserService;
import cn.com.cloudstar.rightcloud.system.service.process.ProcessHelper.ProcessStuts;
import cn.com.cloudstar.rightcloud.system.vo.act.BusinessDto;
import cn.com.cloudstar.rightcloud.system.vo.act.ProcessDto;
import cn.com.cloudstar.rightcloud.system.vo.act.ProcessNodeAddDto;
import cn.com.cloudstar.rightcloud.system.vo.act.ProcessNodeConfig;
import cn.com.cloudstar.rightcloud.system.vo.act.Process;
import cn.com.cloudstar.rightcloud.system.vo.act.ProcessNodeDto;


/**
 * @author Hong.Wu
 * @date: 15:49 2019/07/01
 */
@Slf4j
@Service
public class TestProcessService {
    @Autowired
    private ProcessMapper processMapper;

    @Autowired
    private ProcessVersionMapper processVersionMapper;

    @Autowired
    private ProcessNodeMapper processNodeMapper;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private BusinessService businessService;

    @Autowired
    private UserService userService;

    @Autowired
    private ServiceOrderService serviceOrderService;

    /**
     * 流程状态 - 编辑未发布的状态
     */
    private static final Integer PROCESS_STATUS_EDITED = 1;

    /**
     * 流程状态 - 正常状态
     */
    private static final Integer PROCESS_STATUS_NORMAL = 0;

    @Autowired
    private TaskService taskService;


    public void createProcessDefine(Process process, AuthUser authUser) {
        Long companyId = null;//roleService.getCompayIdByOrgSid(authUser.getOrgSid());

        // 检查流程定义是重名
        Process existsed = null;//processMapper.selectByProcessName(process.getProcessName(), authUser.getOrgSid());
        if (Objects.nonNull(existsed)) {
            throw new BizException("流程定义名称不能重复！");
        }

        Process processCodeExists = null;//processMapper.selectByBusinessCode(process.getBusinessCode(), authUser.getOrgSid());
        if (Objects.nonNull(processCodeExists)) {
            throw new BizException(String.format("[%s]的流程已经存在，不能重复定义",
                                                 StaticDir.BUSINESS_MAP.get(process.getBusinessCode())));
        }
        IdGen idGen = IdGen.get();


        process.setProcessCode("processdef-" + idGen.nextId());
        process.setCreatedBy(authUser.getAccount());
        process.setCreatedDt(DateTime.now().toDate());
        process.setUpdatedBy(authUser.getAccount());
        process.setUpdatedDt(DateTime.now().toDate());
        process.setOrgSid(companyId);
        process.setStatus(0);
//        this.processMapper.insertSelective(process);

        ProcessVersion processVersion = new ProcessVersion();
        processVersion.setProcessId(process.getId());
        processVersion.setVersionName(process.getProcessName());
        processVersion.setDescription(process.getDescription());
        processVersion.setIsdefault(true);
        processVersion.setStatus(0);
        processVersionMapper.insertSelective(processVersion);

        List<ProcessNode> nodes = Lists.newArrayList();

        ProcessNode start = new ProcessNode();
        start.setProcessId(process.getId());
        start.setVersionId(processVersion.getId());
        start.setNodeName("开始");
        start.setNodeType(ProcessHelper.NodeTypeEnum.START.getType());
        start.setDescription("流程开始");
        start.setSortNum(0.0f);
        start.setStatus(0);
        start.setConfigData("{}");
        start.setCreatedBy(authUser.getAccount());
        start.setCreatedDt(DateTime.now().toDate());
        start.setUpdatedBy(authUser.getAccount());
        start.setUpdatedDt(DateTime.now().toDate());
        processNodeMapper.insertSelective(start);
        nodes.add(start);

        ProcessNode openService = new ProcessNode();
        openService.setProcessId(process.getId());
        openService.setVersionId(processVersion.getId());
        openService.setNodeName("执行");
        openService.setNodeType(ProcessHelper.NodeTypeEnum.SERVICE.getType());
        openService.setDescription("服务开通");
        openService.setSortNum(200.0f);
        openService.setStatus(1);
        openService.setConfigData("{}");
        openService.setCreatedBy(authUser.getAccount());
        openService.setCreatedDt(DateTime.now().toDate());
        openService.setUpdatedBy(authUser.getAccount());
        openService.setUpdatedDt(DateTime.now().toDate());
        processNodeMapper.insertSelective(openService);
        nodes.add(openService);

        ProcessNode end  = new ProcessNode();
        end.setProcessId(process.getId());
        end.setVersionId(processVersion.getId());
        end.setNodeName("结束");
        end.setNodeType(ProcessHelper.NodeTypeEnum.END.getType());
        end.setDescription("流程结束");
        end.setSortNum(400.0f);
        end.setStatus(0);
        end.setConfigData("{}");
        end.setCreatedBy(authUser.getAccount());
        end.setCreatedDt(DateTime.now().toDate());
        end.setUpdatedBy(authUser.getAccount());
        end.setUpdatedDt(DateTime.now().toDate());
        processNodeMapper.insertSelective(end);
        nodes.add(end);

        deployProcess(process, processVersion, nodes);
    }

    public void addProcessNode(Long processId, ProcessNodeAddDto addDto, AuthUser authUser) {
        Process process = null;///this.processMapper.selectByPrimaryKey(processId);
        Objects.requireNonNull(process, "流程定义未找到");

        updateProcessStatus(processId, PROCESS_STATUS_EDITED);
        ProcessVersion processVersion = processVersionMapper.selectByProcessId(processId).get(0);

        List<ProcessNode> nodes = processNodeMapper.selectByVersionId(processVersion.getId());


        ProcessNode processNode = new ProcessNode();
        processNode.setNodeName(addDto.getNodeName());
        processNode.setDescription(addDto.getDescription());
        processNode.setProcessId(processId);
        processNode.setVersionId(processVersion.getId());
        // 3: 服务开通审批节点
        processNode.setStatus(ProcessStuts.ServiceOpenAuditNode);
        processNode.setNodeType(ProcessHelper.NodeTypeEnum.USERTASK.getType());
        processNode.setCreatedBy(authUser.getAccount());
        processNode.setCreatedDt(DateTime.now().toDate());
        processNode.setUpdatedBy(authUser.getAccount());
        processNode.setUpdatedDt(DateTime.now().toDate());
        // config
        ProcessNodeConfig config = new ProcessNodeConfig();
        config.setCandidates(addDto.getCandidates().stream().filter(c -> Objects.nonNull(c)).collect(Collectors.toList()));
        config.setCandidateThirds(addDto.getCandidateThirds().stream().filter(c -> Objects.nonNull(c)).collect(Collectors.toList()));
        config.setConfigEnable(addDto.getConfigEnable());
        config.setNotifyWays(addDto.getNotifyWays() != null ? addDto.getNotifyWays() : null);
        processNode.setConfigData(JSON.toJSONString(config));
        // sortNum
        float rangeStart = 0.0f;
        float rangeEnd = 1000.0f;
        ProcessNode curNode = null;
        for (int i = 0; i < nodes.size(); i++) {
            ProcessNode node = nodes.get(i);
            if (node.getNodeType().equals(ProcessHelper.NodeTypeEnum.START.getType())
                    || node.getNodeType().equals(ProcessHelper.NodeTypeEnum.USERTASK.getType())) {
                rangeStart = node.getSortNum();
                rangeEnd = nodes.get(i + 1).getSortNum();
                curNode = node;
            }
        }
        processNode.setSortNum((rangeStart + rangeEnd) / 2);
        processNodeMapper.insertSelective(processNode);

        if (curNode.getNodeType().equals(ProcessHelper.NodeTypeEnum.START.getType())) {
            ProcessNode rejectNode = new ProcessNode();
            rejectNode.setProcessId(process.getId());
            rejectNode.setVersionId(processVersion.getId());
            rejectNode.setNodeName("拒绝并关闭");
            rejectNode.setNodeType(ProcessHelper.NodeTypeEnum.SERVICE.getType());
            rejectNode.setDescription("用户审批拒绝");
            rejectNode.setSortNum(nodes.get(nodes.size() - 1).getSortNum() - 10.0F);
            // ProcessStuts.ServiceRejectCloseNode
            rejectNode.setStatus(2);
            rejectNode.setConfigData("{}");
            rejectNode.setCreatedBy(authUser.getAccount());
            rejectNode.setCreatedDt(DateTime.now().toDate());
            rejectNode.setUpdatedBy(authUser.getAccount());
            rejectNode.setUpdatedDt(DateTime.now().toDate());
            processNodeMapper.insertSelective(rejectNode);
        }
    }

    public void deployProcessDefine(Long processId, AuthUser authUser) {
//        Process process = this.processMapper.selectByPrimaryKey(processId);
//        Objects.requireNonNull(process, "流程定义未找到");
//        updateProcessStatus(processId, PROCESS_STATUS_NORMAL);
//
//        ProcessVersion processVersion = processVersionMapper.selectByProcessId(processId).get(0);
//
//        List<ProcessNode> nodes = this.processNodeMapper.selectByVersionId(processVersion.getId());
//        deployProcess(process, processVersion, nodes);
    }

    private void deployProcess(Process process, ProcessVersion processVersion, List<ProcessNode> originNodes) {
        IdGen idGen = IdGen.get();

        // 拷贝用户任务对象
        List<ProcessNode> nodes = originNodes.stream().map(node -> {
            if (node.getNodeType() == ProcessHelper.NodeTypeEnum.USERTASK.getType()) {
                try {
                    ProcessNode cloneNode = (ProcessNode) node.clone();
                    // 103: 发布持久化节点
                    cloneNode.setStatus(ProcessStuts.PublicDurableNode);
                    cloneNode.setId(null);
                    return cloneNode;
                } catch (CloneNotSupportedException e) {
                    return node;
                }
            }
            return node;
        }).filter(node -> {
            if (node.getNodeType().equals(ProcessHelper.NodeTypeEnum.SERVICE.getType())) {
                if (ProcessConstants.TICKET.equals(process.getBusinessCode())) {
                    if (!node.getStatus().equals(1)) {
                        return false;
                    }
                }
            }
            return true;
        }).collect(Collectors.toList());

        // 持久化用户任务对象 (当用户更新或删除原节点后，任务配置信息保持不变)
        for (ProcessNode node : nodes) {
            if (node.getNodeType() == ProcessHelper.NodeTypeEnum.USERTASK.getType()) {
                processNodeMapper.insertSelective(node);
            }
        }

        Map<Long, FlowElement> flows = Maps.newHashMap();
        Map<Long, FlowElement> flowsAdditionals = Maps.newHashMap();

        BpmnModel model = new BpmnModel();
        org.activiti.bpmn.model.Process actProcess = new org.activiti.bpmn.model.Process();
        model.addProcess(actProcess);
        actProcess.setId(process.getProcessCode());
        actProcess.setName(process.getProcessName());

        for (ProcessNode node : nodes) {
            if (node.getNodeType().equals(ProcessHelper.NodeTypeEnum.START.getType())) {
                FlowElement startFlow = ProcessEvent.startEvent(idGen.nextStringId("act-"), node);
                actProcess.addFlowElement(startFlow);
                flows.put(node.getId(), startFlow);
            } else if (node.getNodeType().equals(ProcessHelper.NodeTypeEnum.GATEWAY.getType())) {
                // 预留(角色网关)
            } else if (node.getNodeType().equals(ProcessHelper.NodeTypeEnum.USERTASK.getType())) {
                FlowElement taskFlow = ProcessEvent.taskEvent(idGen.nextStringId("act-"), node);
                actProcess.addFlowElement(taskFlow);
                flows.put(node.getId(), taskFlow);

                if (!ProcessConstants.TICKET.equals(process.getBusinessCode())) {
                    FlowElement whetherFlow = ProcessEvent.gatewayEvent(idGen.nextStringId("act-"), node);
                    actProcess.addFlowElement(whetherFlow);
                    flowsAdditionals.put(node.getId(), whetherFlow);
                }
            } else if (node.getNodeType().equals(ProcessHelper.NodeTypeEnum.SERVICE.getType())) {
                FlowElement openFlow;
                if (node.getStatus().equals(1)) {
                    openFlow = ProcessEvent.serviceOpenEvent(idGen.nextStringId("act-"), node);
                    actProcess.addFlowElement(openFlow);
                    flows.put(node.getId(), openFlow);
                } else {
                    if (!ProcessConstants.TICKET.equals(process.getBusinessCode())) {
                        openFlow = ProcessEvent.serviceCloseEvent(idGen.nextStringId("act-"), node);
                        actProcess.addFlowElement(openFlow);
                        flows.put(node.getId(), openFlow);
                    }
                }
            } else if (node.getNodeType().equals(ProcessHelper.NodeTypeEnum.END.getType())) {
                FlowElement endFlow = ProcessEvent.endEvent(idGen.nextStringId("act-"), node);
                actProcess.addFlowElement(endFlow);
                flows.put(node.getId(), endFlow);
            }
        }

        for (int i = 0; i < nodes.size(); i++) {
            ProcessNode node = nodes.get(i);

            if (node.getNodeType().equals(ProcessHelper.NodeTypeEnum.START.getType())) {
                FlowElement startFlow = flows.get(node.getId());
                FlowElement nextFlow = flows.get(nodes.get(i + 1).getId());
                actProcess.addFlowElement(ProcessEvent.sequenceFlow(startFlow.getId(), nextFlow.getId()));
            } else if (node.getNodeType().equals(ProcessHelper.NodeTypeEnum.GATEWAY.getType())) {
                // 预留(角色网关)
            } else if (node.getNodeType().equals(ProcessHelper.NodeTypeEnum.USERTASK.getType())) {
                if (!ProcessConstants.TICKET.equals(process.getBusinessCode())) {
                    FlowElement userFlow = flows.get(node.getId());
                    FlowElement wheatherFlow = flowsAdditionals.get(node.getId());

                    actProcess.addFlowElement(ProcessEvent.sequenceFlow(userFlow.getId(), wheatherFlow.getId()));

                    //设置条件
                    FlowElement passFlow = flows.get(nodes.get(i + 1).getId());
                    actProcess.addFlowElement(ProcessEvent.sequenceFlow(wheatherFlow.getId(),
                                                                        passFlow.getId(), "通过", "${audit=='pass'}"));

                    ProcessNode rejectNode = ProcessEvent.findRejectNode(nodes);
                    FlowElement rejectFlow = flows.get(rejectNode.getId());
                    actProcess.addFlowElement(ProcessEvent.sequenceFlow(wheatherFlow.getId(),
                                                                        rejectFlow.getId(), "拒绝", "${audit=='reject'}"));
                } else {
                    FlowElement userFlow = flows.get(node.getId());
                    FlowElement nextFlow = flows.get(nodes.get(i + 1).getId());

                    actProcess.addFlowElement(ProcessEvent.sequenceFlow(userFlow.getId(), nextFlow.getId()));
                }
            } else if (node.getNodeType().equals(ProcessHelper.NodeTypeEnum.SERVICE.getType())) {
                FlowElement serviceFlow = flows.get(node.getId());
                FlowElement nextFlow = flows.get(nodes.get(nodes.size() - 1).getId());

                actProcess.addFlowElement(ProcessEvent.sequenceFlow(serviceFlow.getId(), nextFlow.getId()));
            } else if (node.getNodeType().equals(ProcessHelper.NodeTypeEnum.END.getType())) {
                //
            }
        }

        new BpmnAutoLayout(model).execute();

        Deployment deployment = repositoryService.createDeployment()
                                                 .addBpmnModel(process.getProcessCode() + ".bpmn", model).deploy();

        processVersion.setDeploymentId(deployment.getId());
        ProcessDefinition processDefinition = repositoryService
                .createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult();
        processVersion.setProcessIdentify(processDefinition.getId());
        processVersionMapper.updateByPrimaryKeySelective(processVersion);

        for (ProcessNode node : nodes) {
            if (node.getNodeType() == ProcessHelper.NodeTypeEnum.USERTASK.getType()) {
                node.setProcessIdentify(processDefinition.getId());
                processNodeMapper.updateByPrimaryKeySelective(node);
            }
        }
    }

    private void updateProcessStatus(Long processId, Integer status) {
        Process process = new Process();
        process.setId(processId);
        process.setStatus(status);

//        WebUtil.prepareUpdateParams(process);
//        processMapper.updateByPrimaryKeySelective(process);
    }

    public Map<String,Object> getDefinesDetail(Long processId) {
//        Process process =  this.processMapper.selectByPrimaryKey(processId);
//        ProcessVersion processVersion = this.processVersionMapper.selectByProcessId(processId).get(0);
//
//        List<ProcessNode> processNodes = this.processNodeMapper.selectAuditNodeByVersionId(processVersion.getId());
//        List<ProcessNodeDto> processNodeDtos = processNodes.stream().map(node -> {
//            ProcessNodeDto processNodeDto = new ProcessNodeDto(node);
//            processNodeDto.setNodeTypeName(ProcessHelper.NodeTypeEnum.getEnumByType(node.getNodeType()).getValue());
//            ProcessNodeConfig config = JSON.parseObject(node.getConfigData(), ProcessNodeConfig.class);
//            processNodeDto.setConfigEnable(config.getConfigEnable());
//            if (config.getCandidates() != null) {
//                processNodeDto.setCandidates(config.getCandidates().stream().filter(c-> Objects.nonNull(c)).collect(Collectors.toList()));
//            }
//            if (config.getCandidateThirds() != null) {
//                processNodeDto.setCandidateThirds(config.getCandidateThirds().stream().filter(c-> Objects.nonNull(c)).collect(Collectors.toList()));
//            }
//            processNodeDto.setMessageConfig(config.getMessageConfig());
//
//            List<String> candidateNames = Lists.newArrayList();
//            if (Objects.nonNull(processNodeDto.getCandidates())) {
//                candidateNames.addAll(processNodeDto.getCandidates().stream().map(c->c.getName()).collect(Collectors.toList()));
//            }
//            if (Objects.nonNull(processNodeDto.getCandidateThirds())) {
//                candidateNames.addAll(processNodeDto.getCandidateThirds().stream().map(c->c.getName()).collect(Collectors.toList()));
//            }
//            processNodeDto.setCandidateName(String.join(",", candidateNames));
//            processNodeDto.setNotifyWays(config.getNotifyWays());
//            return processNodeDto;
//        }).collect(Collectors.toList());
//
//        int nodNumber = 0;
//        for (ProcessNodeDto nodeDto : processNodeDtos) {
//            nodeDto.setNodeNumber(++nodNumber);
//        }
//
//        Map<String, Object> defineMap = Maps.newHashMapWithExpectedSize(2);
//        // 审批节点
//        defineMap.put("records", processNodeDtos);
//        defineMap.put("process", this.selectByDefineKey(process.getProcessCode()));
//        defineMap.put("processStatus", process.getStatus());
//        return defineMap;
return null;
    }


    public ProcessDto selectByDefineKey(String defineKey) {
        ProcessDefinition define = repositoryService.createProcessDefinitionQuery()
                                                    .processDefinitionKey(defineKey)
                                                    .orderByProcessDefinitionVersion().desc()
                                                    .listPage(0, 1).get(0);
        ProcessDto processDto = new ProcessDto();
        processDto.setDefineKey(define.getKey());
        processDto.setCode(define.getKey());
        processDto.setName(define.getName());
        processDto.setImage(processPngImage(define));
        if (processDto.getName() == null) {
            Process param = new Process();
            param.setProcessCode(defineKey);
//            List<Process> processList = processMapper.selectByParams(param);
//            if (processList.size() > 0) {
//                Process process = processList.get(0);
//                processDto.setName(process.getProcessName());
//            }
        }
        return processDto;
    }
    private String processPngImage(ProcessDefinition define) {
        try {
            InputStream pngStream = repositoryService.getResourceAsStream(
                    define.getDeploymentId(), define.getDiagramResourceName());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = pngStream.read(buffer, 0, 1024)) != -1) {
                out.write(buffer, 0, len);
            }

            byte[] pngBytes = out.toByteArray();

            return Base64.getEncoder().encodeToString(pngBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public void startProcess(String processId, String businessId, Map<String, Object> variables) {
        AuthUser authUser = RequestContextUtil.getAuthUserInfo();

        if (Objects.isNull(variables)) {
            variables = Maps.newHashMap();
        }

        ProcessDto processDto = selectByDefineKey(processId);

        String business_code = (String) variables.get("_business_code");
        String business_name = StaticDir.BUSINESS_MAP.get(business_code);

        business_name = business_name != null ? business_name : processDto.getName();

        String startComment = String.format("[%s]提交了[%s]申请", authUser.getRealName(), business_name);

        variables.put("__process_start_comment", startComment);
        variables.put("__process_id", processId);
        variables.put("__org_sid", authUser.getOrgSid().toString());

        log.info("流程启动消息：{}, 申请单: [{}]", startComment, businessId);

        // String processDefinitionKey, String businessKey, Map<String, Object> variables
        runtimeService.startProcessInstanceByKey(processId, businessId, variables);
    }


    @Transactional(rollbackFor = Exception.class)
    public List<String> auditCandidateInlineMgts(DelegateExecution execution, String auditIds) {
        String[] auditIdArr = auditIds.split(",");
        String businssId = execution.getProcessBusinessKey();

        Map<String, Object> variables = execution.getVariables();
        log.info("申请单[{}], 组织: [{}], 候选人参数: [{}]",
                 businssId, variables.get("__org_sid"), auditIds);

        List<String> allCandidateUsers = Lists.newArrayList();
        List<String> roleIds = Lists.newArrayList();
        List<String> userIds = Lists.newArrayList();

        for (String auditId : auditIdArr) {
            String[] split = auditId.split("-");
            //已20开头的是用户 ID，已10开头的是角色 ID
            if (split[0].startsWith("20")) {
                userIds.add(split[1]);
            } else if (split[0].startsWith("10")) {
                roleIds.add(split[2]);
            }
        }

        //根据角色查询候选人
        if (roleIds.size() > 0) {
            List<String> candidateUsers = null;//businessService.queryCandidateUsers(businssId, variables, roleIds);
            allCandidateUsers.addAll(candidateUsers);
        }

        //根据选定用户选择候选人，需要判断权限
        if (userIds.size() > 0) {
            List<User> users = null;//userService.findUserUnderOrg(Long.valueOf((String) variables.get("__org_sid")));
            List<String> candidateUsers = userIds.parallelStream()
                                                 .filter(userId -> users.parallelStream().anyMatch(user -> Objects.equals(userId, String.valueOf(user.getUserSid()))))
                                                 .collect(Collectors.toList());
            allCandidateUsers.addAll(candidateUsers);
        }

        log.info("申请单[{}], 候选人列表: {}", businssId, JSON.toJSON(allCandidateUsers));

        String businessCode = (String) variables.get("_business_code");

        if (!ProcessConstants.TICKET.equals(businessCode)) {
//            ServiceOrder serviceOrder = serviceOrderService.selectByOrderSn(businssId);

//            serviceOrder.setStepName(execution.getCurrentActivityName());
//            serviceOrderService.updateByPrimaryKeySelective(serviceOrder);
        } else {
            String ticketUserid = (String) variables.get("_ticket_usersid");
            if (StringUtils.isNotBlank(ticketUserid)) {
                allCandidateUsers.add(ticketUserid);
            }
        }

        List<String> candidateUsers = allCandidateUsers.stream().distinct().collect(Collectors.toList());
        if (candidateUsers.size() < 1) {
            // 自动审批通过（当审批节点没有候选人）(废弃)
            // autoApprovalWhenNoCandidates(execution.getId(), businssId, variables);
        }

        return candidateUsers;

    }


    @Transactional(rollbackFor = Exception.class)
    public void doOpen(DelegateExecution execution) {
        String businssId = execution.getProcessBusinessKey();
        Map<String, Object> variables = execution.getVariables();

        log.info("申请单[{}]的审批流程已经全部审批通过，执行服务开通", businssId);

//        businessService.approve(businssId, variables);
    }

    public BusinessDto candidateTask(String candidateId) {
        TaskQuery taskQuery = taskService.createTaskQuery();
        //TODO fixme 暂时 activity 中保存了用户 id ，所以导致在项目中也可以查看到相同用户的组织管理员待审核记录
        //  修复这个问题需要在 activity 中添加角色，并且角色修改后需要更新 activity 中的数据
        taskQuery.taskCandidateOrAssigned(candidateId);
        List<Task> tasks = taskQuery.list();
        if (tasks.size() == 0) {
            return new BusinessDto(Lists.newArrayList(), Maps.newHashMapWithExpectedSize(0));
        }

        Map<String, String> statusMap = Maps.newHashMap();

        Set<String> instanceIds = tasks.stream().map(task -> {
            statusMap.put(task.getProcessInstanceId(), task.getName());
            return task.getProcessInstanceId();
        }).collect(Collectors.toSet());
        List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery()
                                                        .processInstanceIds(instanceIds).list();

        Map<String, String> businessStatusMap = Maps.newHashMap();
        List<String> businsessIds = instances.stream().map(instance -> {
            businessStatusMap.put(instance.getBusinessKey(), statusMap.get(instance.getId()));
            return instance.getBusinessKey();
        }).collect(Collectors.toList());

        return new BusinessDto(businsessIds, businessStatusMap);
    }


    @Transactional(rollbackFor = Exception.class)
    public void taskPass(String candidateId, String businsessId, Map<String, Object> variables) {
        if (variables == null) {
            variables = Maps.newHashMap();
        }

        variables.put(ProcessConstants.AUDIT, ProcessConstants.AUDIT_PASS);

        Task task = taskService.createTaskQuery().processInstanceBusinessKey(businsessId).singleResult();
        if (task == null) {
            throw new BizException("该任务已被处理，请勿重复处理");
        }

        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                                                        .processInstanceId(task.getProcessInstanceId()).singleResult();

        variables.put(processInstance.getActivityId() + ProcessConstants.AUDIT_STATUS_SUFFIX,
                      ProcessConstants.AUDIT_PASS);

        Object comment = variables.get(ProcessConstants.AUDIT_COMMENT);
        if (comment != null) {
            // 保存审批注释(暂时未用到)
            taskService.addComment(task.getId(), task.getProcessInstanceId(), String.valueOf(comment));

            variables.put(processInstance.getActivityId() + ProcessConstants.AUDIT_COMMENT_SUFFIX,
                          String.valueOf(comment));
        }

        Object auditUname = variables.get("_audit_uname");
        if (auditUname != null) {
            variables.put(processInstance.getActivityId() + "_audit_uname",
                          String.valueOf(auditUname));
        }
        variables.put("_task_name", task.getName());

        taskService.claim(task.getId(), candidateId);
        taskService.complete(task.getId(), variables);

        log.info("审批任务完成消息：[{}]完成了申请单[{}]的[{}]审批任务", auditUname, businsessId, task.getName());
    }

    @Transactional(rollbackFor = Exception.class)
    public void taskReject(String candidateId, String businsessId, Map<String, Object> variables) {
        if (variables == null) {
            variables = Maps.newHashMap();
        }

        variables.put(ProcessConstants.AUDIT, ProcessConstants.AUDIT_REJECT);

        Task task = taskService.createTaskQuery().processInstanceBusinessKey(businsessId).singleResult();
        if (task == null) {
            throw new BizException("该任务已被处理，请勿重复处理");
        }
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                                                        .processInstanceId(task.getProcessInstanceId()).singleResult();

        variables.put(processInstance.getActivityId() + ProcessConstants.AUDIT_STATUS_SUFFIX,
                      ProcessConstants.AUDIT_REJECT);

        Object comment = variables.get(ProcessConstants.AUDIT_COMMENT);
        if (comment != null) {
            taskService.addComment(task.getId(), task.getProcessInstanceId(), String.valueOf(comment));

            variables.put(processInstance.getActivityId() + ProcessConstants.AUDIT_COMMENT_SUFFIX,
                          String.valueOf(comment));
        }

        Object auditUname = variables.get("_audit_uname");
        if (auditUname != null) {
            variables.put(processInstance.getActivityId() + "_audit_uname",
                          String.valueOf(auditUname));
        }
        variables.put("_task_name", task.getName());

        taskService.claim(task.getId(), candidateId);
        taskService.complete(task.getId(), variables);
    }


    @Transactional(rollbackFor = Exception.class)
    public void doClose(DelegateExecution execution) {
        String businssId = execution.getProcessBusinessKey();
        Map<String, Object> variables = execution.getVariables();

        log.info("申请单[{}]的审批流程已被拒绝，执行拒绝并关闭", businssId);

//        businessService.reject(businssId, variables);
    }
}









