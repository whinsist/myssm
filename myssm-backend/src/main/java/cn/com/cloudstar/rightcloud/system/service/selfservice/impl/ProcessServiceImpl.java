package cn.com.cloudstar.rightcloud.system.service.selfservice.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import cn.com.cloudstar.rightcloud.framework.common.exception.BizException;
import cn.com.cloudstar.rightcloud.framework.common.pojo.AuthUser;
import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.framework.common.util.AssertUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.BeanConvertUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.RequestContextUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.WebUtil;
import cn.com.cloudstar.rightcloud.system.activiti.image.DefaultProcessDiagramGenerator;
import cn.com.cloudstar.rightcloud.system.activiti.util.IdGen;
import cn.com.cloudstar.rightcloud.system.activiti.util.ProcessConstants;
import cn.com.cloudstar.rightcloud.system.controller.back.selfservice.bean.request.CreateProcessNodeRequest;
import cn.com.cloudstar.rightcloud.system.controller.back.selfservice.bean.request.CreateProcessRequest;
import cn.com.cloudstar.rightcloud.system.controller.back.selfservice.bean.request.ProcessNodeRoleVO;
import cn.com.cloudstar.rightcloud.system.controller.back.selfservice.bean.request.UpdateProcessRequest;
import cn.com.cloudstar.rightcloud.system.dao.selfservice.ProcessMapper;
import cn.com.cloudstar.rightcloud.system.dao.selfservice.ProcessNodeMapper;
import cn.com.cloudstar.rightcloud.system.dao.selfservice.ProcessVersionMapper;
import cn.com.cloudstar.rightcloud.system.enums.ActivityCanstant;
import cn.com.cloudstar.rightcloud.system.enums.ProcessNodeStatusEnum;
import cn.com.cloudstar.rightcloud.system.enums.ProcessStatusEnum;
import cn.com.cloudstar.rightcloud.system.entity.system.User;
import cn.com.cloudstar.rightcloud.system.entity.selfservice.ProcessNode;
import cn.com.cloudstar.rightcloud.system.entity.selfservice.ProcessVersion;
import cn.com.cloudstar.rightcloud.system.entity.selfservice.Process;
import cn.com.cloudstar.rightcloud.system.service.selfservice.ProcessService;
import cn.com.cloudstar.rightcloud.system.service.system.UserService;
import cn.com.cloudstar.rightcloud.system.activiti.util.ProcessEvent;
import cn.com.cloudstar.rightcloud.system.activiti.util.ProcessHelper;
import cn.com.cloudstar.rightcloud.system.activiti.util.ProcessHelper.NodeTypeEnum;
import cn.com.cloudstar.rightcloud.system.pojo.vo.act.BusinessDto;
import cn.com.cloudstar.rightcloud.system.pojo.vo.act.ProcessActivityDto;
import cn.com.cloudstar.rightcloud.system.pojo.vo.act.ProcessDto;
import cn.com.cloudstar.rightcloud.system.pojo.vo.act.ProcessNodeConfig;
import cn.com.cloudstar.rightcloud.system.pojo.vo.act.ProcessNodeRoleDto;

/**
 * @author Hong.Wu
 * @date: 9:16 2019/10/27
 */
@Slf4j
@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private ProcessVersionMapper processVersionMapper;

    @Autowired
    private ProcessNodeMapper processNodeMapper;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ProcessMapper processMapper;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProcessEngineConfiguration processEngineConfiguration;

    @Autowired
    private ProcessEngineFactoryBean processEngine;

    @Autowired
    private UserService userService;

    @Override
    public List<Process> selectByParams(Criteria criteria) {
        return processMapper.selectByParams(null);
    }

    @Override
    public void createProcessDefine(CreateProcessRequest createProcessRequest, AuthUser authUser) {
        // 检查流程定义
        AssertUtil.notBlank(createProcessRequest.getProcessName(), "名称不能为空");
        AssertUtil.notBlank(createProcessRequest.getBusinessCode(), "businessCode不能为空");
        Process processCodeExists = processMapper.selectByBusinessCode(createProcessRequest.getBusinessCode(),
                                                                       authUser.getOrgSid());
        if (Objects.nonNull(processCodeExists)) {
            throw new BizException(String.format("[%s]的流程已经存在，不能重复定义",
                                                 ProcessHelper.BUSINESS_MAP.get(
                                                         createProcessRequest.getBusinessCode())));
        }

        IdGen idGen = IdGen.get();

        Process process = new Process();
        process.setProcessName(createProcessRequest.getProcessName());
        process.setBusinessCode(createProcessRequest.getBusinessCode());
        process.setDescription(createProcessRequest.getDescription());
        process.setProcessCode("processdef-" + idGen.nextId());
        process.setCreatedBy(authUser.getAccount());
        process.setCreatedDt(DateTime.now().toDate());
        process.setUpdatedBy(authUser.getAccount());
        process.setUpdatedDt(DateTime.now().toDate());
        process.setOrgSid(authUser.getOrgSid());
        process.setStatus(0);
        this.processMapper.insertSelective(process);

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
        processNodeMapper.insertSelective(openService);
        nodes.add(openService);

        ProcessNode end = new ProcessNode();
        end.setProcessId(process.getId());
        end.setVersionId(processVersion.getId());
        end.setNodeName("结束");
        end.setNodeType(ProcessHelper.NodeTypeEnum.END.getType());
        end.setDescription("流程结束");
        end.setSortNum(400.0f);
        end.setStatus(0);
        end.setConfigData("{}");
        processNodeMapper.insertSelective(end);
        nodes.add(end);

        deployProcess(process, processVersion, nodes);
    }

    @Override
    public Map<String, Object> getProcessDetail(Long processId) {
        Process process = this.processMapper.selectByPrimaryKey(processId);
        String processDefineKey = process.getProcessCode();
        ProcessDefinition define = repositoryService.createProcessDefinitionQuery()
                                                    .processDefinitionKey(processDefineKey)
                                                    .orderByProcessDefinitionVersion().desc()
                                                    .listPage(0, 1).get(0);
        ProcessDto processDto = new ProcessDto();
        processDto.setDefineKey(define.getKey());
        processDto.setCode(define.getKey());
        processDto.setName(define.getName());
        processDto.setImage(processPngImage(define));
        processDto.setDescription(process.getDescription());

        List<ProcessVersion> processVersions = processVersionMapper.selectByProcessId(processId);
        ProcessVersion processVersion = processVersions.get(0);
        List<ProcessNode> processNodes = processNodeMapper.selectAuditNodeByVersionId(processVersion.getId());

        Map<String, Object> defineMap = Maps.newHashMapWithExpectedSize(3);
        // 审批节点
        defineMap.put("records", processNodes);
        // 流程信息
        defineMap.put("process", processDto);
        defineMap.put("processStatus", process.getStatus());
        return defineMap;
    }

    @Override
    public Long addProcessNode(CreateProcessNodeRequest request) {
        Long processId = request.getProcessId();
        Process process = processMapper.selectByPrimaryKey(request.getProcessId());

        Process processUpdate = new Process();
        processUpdate.setId(processId);
        processUpdate.setStatus(ProcessStatusEnum.EDITED.getCode());
        WebUtil.prepareUpdateParams(processUpdate);
        processMapper.updateByPrimaryKeySelective(processUpdate);

        List<ProcessVersion> processVersions = processVersionMapper.selectByProcessId(processId);
        ProcessVersion processVersion = processVersions.get(0);
        List<ProcessNode> nodes = processNodeMapper.selectByVersionId(processVersion.getId());

        //
        ProcessNode processNode = new ProcessNode();
        processNode.setNodeName(request.getNodeName());
        processNode.setDescription(request.getDescription());
        processNode.setProcessId(processId);
        processNode.setVersionId(processVersion.getId());
        processNode.setStatus(3);
        processNode.setNodeType(NodeTypeEnum.USERTASK.getType());
        //
        List<ProcessNodeRoleDto> candidatesList = new ArrayList<>();
        for (ProcessNodeRoleVO processNodeRoleVO : request.getCandidates()) {
            ProcessNodeRoleDto processNodeRoleDto = BeanConvertUtil.convert(processNodeRoleVO,
                                                                            ProcessNodeRoleDto.class);
            candidatesList.add(processNodeRoleDto);
        }
        List<ProcessNodeRoleDto> candidateThirdList = new ArrayList<>();
        for (ProcessNodeRoleVO processNodeRoleVO : request.getCandidateThirds()) {
            ProcessNodeRoleDto processNodeRoleDto = BeanConvertUtil.convert(processNodeRoleVO,
                                                                            ProcessNodeRoleDto.class);
            candidateThirdList.add(processNodeRoleDto);
        }
        ProcessNodeConfig config = new ProcessNodeConfig();
        config.setCandidates(candidatesList);
        config.setCandidateThirds(candidateThirdList);
        config.setNotifyWays(request.getNotifyWays());
        processNode.setConfigData(JSON.toJSONString(config));

        float rangeStart = 0.0f;
        float rangeEnd = 1000.0f;
        ProcessNode curNode = null;
        for (int i = 0; i < nodes.size(); i++) {
            ProcessNode node = nodes.get(i);

            if (node.getNodeType().equals(NodeTypeEnum.START.getType())
                    || node.getNodeType().equals(NodeTypeEnum.USERTASK.getType())) {
                rangeStart = node.getSortNum();
                rangeEnd = nodes.get(i + 1).getSortNum();
                curNode = node;
            }
        }
        if (curNode.getNodeType().equals(NodeTypeEnum.START.getType())) {
            ProcessNode rejectNode = new ProcessNode();
            rejectNode.setProcessId(process.getId());
            rejectNode.setVersionId(processVersion.getId());
            rejectNode.setNodeName("拒绝并关闭");
            rejectNode.setNodeType(NodeTypeEnum.SERVICE.getType());
            rejectNode.setDescription("用户审批拒绝");
            rejectNode.setSortNum(nodes.get(nodes.size() - 1).getSortNum() - 10.0F);
            rejectNode.setStatus(2);
            rejectNode.setConfigData("{}");
            processNodeMapper.insertSelective(rejectNode);
        }
        processNode.setSortNum((rangeStart + rangeEnd) / 2);

        processNodeMapper.insertSelective(processNode);
        return processNode.getId();
    }

    @Override
    public void deployProcess(Long processId) {
        Process process = processMapper.selectByPrimaryKey(processId);
        Process processUpdate = new Process();
        processUpdate.setId(processId);
        processUpdate.setStatus(ProcessStatusEnum.NORMAL.getCode());
        WebUtil.prepareUpdateParams(processUpdate);
        processMapper.updateByPrimaryKeySelective(processUpdate);

        List<ProcessVersion> processVersions = processVersionMapper.selectByProcessId(processId);
        ProcessVersion processVersion = processVersions.get(0);

        List<ProcessNode> nodes = processNodeMapper.selectByVersionId(processVersion.getId());
        deployProcess(process, processVersion, nodes);
    }

    @Override
    public void deleteOrUpdateProcess(UpdateProcessRequest request) {
        String oper = request.getOper();
        AssertUtil.notBlank(oper, "未找到对应的操作");
        // 删除操作
        if ("del".equals(oper)) {
            String ids = request.getId();
            String[] split = ids.split(",");
            for (String id : split) {
                long processId = Long.parseLong(id);

                processMapper.deleteByPrimaryKey(processId);
                processVersionMapper.deleteByProcessId(processId);
                processNodeMapper.deleteByProcessId(processId);
            }
        }
        // 修改操作
        if ("edit".equals(oper)) {
            Process process = new Process();
            process.setId(Long.parseLong(request.getId()));
            process.setProcessName(request.getProcessName());
            this.processMapper.updateByPrimaryKeySelective(process);
        }
    }

    @Override
    public void startProcess(String processId, String businessId, Map<String, Object> variables) {
        AuthUser authUser = RequestContextUtil.getAuthUserInfo();
        if (Objects.isNull(variables)) {
            variables = Maps.newHashMap();
        }

        String business_code = (String) variables.get("_business_code");
        String business_name = ProcessHelper.BUSINESS_MAP.get(business_code);

        String startComment = String.format("[%s]提交了[%s]申请",
                                            authUser.getRealName(), business_name);

        variables.put("__process_start_comment", startComment);
        variables.put("__process_id", processId);
        variables.put("__org_sid", authUser.getOrgSid().toString());

        log.info(ActivityCanstant.PREFIX + "1start、流程启动消息：{}, 申请单: [{}]", startComment, businessId);

        runtimeService.startProcessInstanceByKey(processId, businessId, variables);
    }

    @Override
    public ProcessDto selectByBusinessId(String businessId) {
        HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery()
                                                    .processInstanceBusinessKey(businessId).singleResult();
        if (hpi == null) {
            return null;
        }

        ProcessDefinition define = repositoryService.createProcessDefinitionQuery()
                                                    .processDefinitionId(hpi.getProcessDefinitionId()).singleResult();
        if (define == null) {
            return null;
        }

        ProcessDto processDto = new ProcessDto();
        processDto.setDefineKey(hpi.getProcessDefinitionKey());
        processDto.setCode(hpi.getProcessDefinitionKey());
        processDto.setName(hpi.getProcessDefinitionName());
        processDto.setImage(processPngImage(businessId));

        return processDto;
    }

    @Override
    public List<ProcessActivityDto> instanceRecords(String businessId) {
        HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery()
                                                    .processInstanceBusinessKey(businessId).singleResult();
        if (hpi == null) {
            return Arrays.asList();
        }

        BpmnModel model = repositoryService.getBpmnModel(hpi.getProcessDefinitionId());
        Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
        Map<String, Integer> actMap = Maps.newHashMap();
        int actSort = 1;
        for (FlowElement e : flowElements) {
            actMap.put(e.getId(), actSort++);
        }

        List<HistoricActivityInstance> activitiyInstances = historyService
                .createHistoricActivityInstanceQuery()
                .processInstanceId(hpi.getId())
                .orderByHistoricActivityInstanceStartTime().asc().list();

        List<ProcessActivityDto> acts = new ArrayList<>();
        ProcessActivityDto actDto = null;
        Map<String, Object> variables = null;

        String processDefinitionId = hpi.getProcessDefinitionId();

        ProcessDefinitionEntity entity = (ProcessDefinitionEntity) repositoryService
                .getProcessDefinition(processDefinitionId);

        Collections.sort(activitiyInstances, (a, b) -> {
            int asort = actMap.get(a.getActivityId());
            int bsort = actMap.get(b.getActivityId());
            return asort - bsort;
        });

        for (HistoricActivityInstance act : activitiyInstances) {
            if ("startEvent".equals(act.getActivityType())) {
                actDto = new ProcessActivityDto();
                actDto.setId(act.getId());
                actDto.setActivityId(act.getActivityId());
                actDto.setActivityName("提交申请流程");
                actDto.setAssigneeId(act.getAssignee());
                actDto.setActivityType(act.getActivityType());
                actDto.setStartTime(act.getStartTime());
                actDto.setEndTime(act.getEndTime());
                actDto.setStatus("提交申请");

                if (variables == null) {
                    variables = executionVariables(act.getExecutionId());
                }
                actDto.setAssigneeName((String) variables.get("_apply_uname"));
                actDto.setComment((String) variables.get("__process_start_comment"));

                acts.add(actDto);
            } else if ("endEvent".equals(act.getActivityType())) {
                actDto = new ProcessActivityDto();
                actDto.setId(act.getId());
                actDto.setActivityId(act.getActivityId());
                actDto.setActivityName("流程结束");
                actDto.setAssigneeId(act.getAssignee());
                actDto.setActivityType(act.getActivityType());
                actDto.setStartTime(act.getStartTime());
                actDto.setEndTime(act.getEndTime());
                actDto.setStatus("结束");
                actDto.setComment("流程结束");

                actDto.setAssigneeId("SYSTEM");
                actDto.setAssigneeName("[系统]");

                acts.add(actDto);
            } else if ("userTask".equals(act.getActivityType())) {
                actDto = new ProcessActivityDto();
                actDto.setId(act.getId());
                actDto.setActivityId(act.getActivityId());
                actDto.setActivityName(act.getActivityName());
                actDto.setAssigneeId(act.getAssignee());
                actDto.setActivityType(act.getActivityType());
                actDto.setStartTime(act.getStartTime());

                if (act.getEndTime() == null) {
                    List<IdentityLink> identitys = taskService.getIdentityLinksForTask(act.getTaskId());
                    if (identitys != null && identitys.size() > 0) {
                        String condiates = identitys.stream().map(identity -> {
                            identity.getUserId();
                            User user = userService.selectByPrimaryKey(Long.parseLong(identity.getUserId()));
                            if (user != null) {
                                return user.getRealName();
                            }

                            return "";
                        }).collect(Collectors.joining(","));
                        actDto.setComment(String.format("等待[%s]审批", condiates));
                        actDto.setAssigneeName(String.format("[%s]", condiates));
                    }
                } else {
                    //
                }

                acts.add(actDto);
            } else if ("exclusiveGateway".equals(act.getActivityType())) {
                if (actDto != null) {
                    actDto.setEndTime(act.getEndTime());

                    if (variables == null) {
                        variables = executionVariables(act.getExecutionId());
                    }

                    actDto.setStatus("审批中");
                    if (variables.containsKey(actDto.getActivityId()
                                                      + ProcessConstants.AUDIT_STATUS_SUFFIX)) {

                        String audit = variables.get(actDto.getActivityId()
                                                             + ProcessConstants.AUDIT_STATUS_SUFFIX).toString();
                        if (ProcessConstants.AUDIT_PASS.equals(audit)) {
                            actDto.setStatus("通过");
                        } else if (ProcessConstants.AUDIT_REJECT.equals(audit)) {
                            actDto.setStatus("拒绝");
                        }
                    }
                    if (variables.containsKey(actDto.getActivityId()
                                                      + ProcessConstants.AUDIT_COMMENT_SUFFIX)) {

                        actDto.setComment(variables.get(actDto.getActivityId()
                                                                + ProcessConstants.AUDIT_COMMENT_SUFFIX).toString());
                    }

                    if (variables.containsKey(actDto.getActivityId() + "_audit_uname")) {
                        actDto.setAssigneeName(variables.get(actDto.getActivityId() + "_audit_uname").toString());
                    } else {
                        if ("SYSTEM".equals(actDto.getAssigneeId())) {
                            actDto.setAssigneeName("[系统]");
                        } else {
                            try {
                                User user = userService.selectByPrimaryKey(Long.parseLong(actDto.getAssigneeId()));
                                if (user != null) {
                                    actDto.setAssigneeName(user.getRealName());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            } else if ("serviceTask".equals(act.getActivityType())) {
                actDto = new ProcessActivityDto();
                actDto.setId(act.getId());
                actDto.setActivityId(act.getActivityId());
                actDto.setActivityName(act.getActivityName());
                actDto.setAssigneeId(act.getAssignee());
                actDto.setActivityType(act.getActivityType());
                actDto.setStartTime(act.getStartTime());
                actDto.setEndTime(act.getEndTime());

                actDto.setAssigneeId("SYSTEM");
                actDto.setAssigneeName("[系统]");

                if (variables == null) {
                    variables = executionVariables(act.getExecutionId());
                }

                String audit = (String) variables.get(ProcessConstants.AUDIT);
                if (ProcessConstants.AUDIT_PASS.equals(audit)) {
                    actDto.setStatus("服务开通");
                    actDto.setComment("执行服务开通");

                    String businessCode = (String) variables.get("_business_code");
                    if (businessCode != null) {
                        String businessName = ProcessHelper.BUSINESS_MAP.get(businessCode);
                        if (businessName != null) {
                            actDto.setComment(String.format("执行[%s]", businessName));
                        }
                    }

                } else if (ProcessConstants.AUDIT_REJECT.equals(audit)) {
                    actDto.setStatus("拒绝并关闭");
                    actDto.setComment("关闭流程");
                }

                acts.add(actDto);
            }
        }

        Map<String, User> users = Maps.newHashMap();
        User system = new User();
        //system.setName("自动处理");
        users.put("SYSTEM", system);

        for (ProcessActivityDto act : acts) {
            if (!users.containsKey(act.getAssigneeId())) {
                try {
                    User user = userService.selectByPrimaryKey(Long.parseLong(act.getAssigneeId()));
                    users.put(act.getAssigneeId(), user);
                } catch (Exception e) {
                }
            }

            User user = users.get(act.getAssigneeId());
            if (user != null) {
                //act.setAssigneeName(user.getName());
            }

            ActivityImpl activity = entity.findActivity(act.getActivityId());
            act.setActLeft(activity.getX());
            act.setActTop(activity.getY());
            act.setActWidth(activity.getWidth());
            act.setActHeight(activity.getHeight());

            if (act.getEndTime() == null) {
                act.setActived(true);
            }
        }

        return acts;
    }

    @Override
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

    @Override
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

        log.info(ActivityCanstant.PREFIX
                         + "审批任务完成消息：[{}]完成了申请单[{}]的[{}]审批任务 auditCandidateInlineMgts -> actTackCreatedCallback -> actTackCompletedCallback ->",
                 auditUname, businsessId, task.getName());
    }

    @Override
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

    @Override
    public List<String> candidateHistoryTasks(String assigneeId) {

        List<HistoricActivityInstance> historys = historyService
                .createHistoricActivityInstanceQuery()
                .taskAssignee(assigneeId).list();

        List<String> ids = historys.stream().map(history -> {

            HistoricProcessInstance historicProcessInstance = historyService
                    .createHistoricProcessInstanceQuery()
                    .processInstanceId(history.getProcessInstanceId()).singleResult();

            return historicProcessInstance.getBusinessKey();
        }).collect(Collectors.toList());

        return ids;
    }

    private Map<String, Object> executionVariables(String executionId) {

        // 获取流程变量
        List<HistoricVariableInstance> variableInstances = historyService
                .createHistoricVariableInstanceQuery().executionId(executionId).list();

        Map<String, Object> variables = Maps.newHashMap();
        for (HistoricVariableInstance variable : variableInstances) {
            variables.put(variable.getVariableName(), variable.getValue());
        }

        return variables;
    }

    private String processPngImage(String businessId) {

        Task task = taskService.createTaskQuery()
                               .processInstanceBusinessKey(businessId).singleResult();

        HistoricProcessInstance processInstance;
        String processInstanceId;
        //获取历史流程实例
        if (task == null) {
            processInstance = historyService.createHistoricProcessInstanceQuery()
                                            .processInstanceBusinessKey(businessId).singleResult();

            if (processInstance == null) {
                throw new BizException("任务未找到");
            }

            processInstanceId = processInstance.getId();
            ProcessConstants.PROCESS_FINISHED.set(true);
        } else {
            processInstanceId = task.getProcessInstanceId();
            processInstance = historyService.createHistoricProcessInstanceQuery()
                                            .processInstanceId(processInstanceId).singleResult();
            ProcessConstants.PROCESS_FINISHED.set(false);
        }

        //获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        processEngineConfiguration = processEngine.getProcessEngineConfiguration();
        Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);

        ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity)
                repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());

        Collection<FlowElement> flowElements = bpmnModel.getMainProcess().getFlowElements();
        Map<String, Integer> actMap = Maps.newHashMap();
        int actSort = 1;
        for (FlowElement e : flowElements) {
            actMap.put(e.getId(), actSort++);
        }

        List<HistoricActivityInstance> highLightedActivitList = historyService
                .createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId)
                .orderByHistoricActivityInstanceStartTime().asc().list();

        Collections.sort(highLightedActivitList, (a, b) -> {
            int asort = actMap.get(a.getActivityId());
            int bsort = actMap.get(b.getActivityId());
            return asort - bsort;
        });

        //高亮环节id集合
        List<String> highLightedActivitis = new ArrayList<>();
        //高亮线路id集合
        List<String> highLightedFlows = DefaultProcessDiagramGenerator
                .getHighLightedFlows(definitionEntity, highLightedActivitList);

        for (HistoricActivityInstance tempActivity : highLightedActivitList) {
            String activityId = tempActivity.getActivityId();
            highLightedActivitis.add(activityId);
        }

        InputStream pngStream = diagramGenerator.generateDiagram(bpmnModel, "png",
                                                                 highLightedActivitis, highLightedFlows, "宋体", "宋体",
                                                                 "宋体", null, 1.0);

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = pngStream.read(buffer, 0, 1024)) != -1) {
                out.write(buffer, 0, len);
            }

            byte[] pngBytes = out.toByteArray();

            return Base64.getEncoder().encodeToString(pngBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
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
        return null;
    }


    private void deployProcess(Process process, ProcessVersion processVersion, List<ProcessNode> originNodes) {
        IdGen idGen = IdGen.get();

        // 拷贝用户任务对象
        List<ProcessNode> nodes = originNodes.stream().map(node -> {
            if (node.getNodeType() == ProcessHelper.NodeTypeEnum.USERTASK.getType()) {
                try {
                    ProcessNode cloneNode = (ProcessNode) node.clone();
                    // 103: 发布持久化节点
                    cloneNode.setStatus(ProcessNodeStatusEnum.PublicDurableNode.getCode());
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
            if (node.getNodeType().equals(ProcessHelper.NodeTypeEnum.USERTASK.getType())) {
                processNodeMapper.insertSelective(node);
            }
        }

        Map<Long, FlowElement> flows = Maps.newHashMap();
        Map<Long, FlowElement> flowsAdditionals = Maps.newHashMap();

        org.activiti.bpmn.model.Process bpmnProcessDef = new org.activiti.bpmn.model.Process();
        bpmnProcessDef.setId(process.getProcessCode());
        bpmnProcessDef.setName(process.getProcessName());
        for (ProcessNode node : nodes) {
            if (node.getNodeType().equals(ProcessHelper.NodeTypeEnum.START.getType())) {
                FlowElement startFlow = ProcessEvent.startEvent(idGen.nextStringId("act-"), node);
                bpmnProcessDef.addFlowElement(startFlow);
                flows.put(node.getId(), startFlow);
            } else if (node.getNodeType().equals(ProcessHelper.NodeTypeEnum.GATEWAY.getType())) {
                // 预留(角色网关)
            } else if (node.getNodeType().equals(ProcessHelper.NodeTypeEnum.USERTASK.getType())) {
                FlowElement taskFlow = ProcessEvent.taskEvent(idGen.nextStringId("act-"), node);
                bpmnProcessDef.addFlowElement(taskFlow);
                flows.put(node.getId(), taskFlow);
                if (!ProcessConstants.TICKET.equals(process.getBusinessCode())) {
                    FlowElement whetherFlow = ProcessEvent.gatewayEvent(idGen.nextStringId("act-"), node);
                    bpmnProcessDef.addFlowElement(whetherFlow);
                    flowsAdditionals.put(node.getId(), whetherFlow);
                }
            } else if (node.getNodeType().equals(ProcessHelper.NodeTypeEnum.SERVICE.getType())) {
                FlowElement openFlow;
                if (node.getStatus().equals(1)) {
                    openFlow = ProcessEvent.serviceOpenEvent(idGen.nextStringId("act-"), node);
                    bpmnProcessDef.addFlowElement(openFlow);
                    flows.put(node.getId(), openFlow);
                } else {
                    if (!ProcessConstants.TICKET.equals(process.getBusinessCode())) {
                        openFlow = ProcessEvent.serviceCloseEvent(idGen.nextStringId("act-"), node);
                        bpmnProcessDef.addFlowElement(openFlow);
                        flows.put(node.getId(), openFlow);
                    }
                }
            } else if (node.getNodeType().equals(ProcessHelper.NodeTypeEnum.END.getType())) {
                FlowElement endFlow = ProcessEvent.endEvent(idGen.nextStringId("act-"), node);
                bpmnProcessDef.addFlowElement(endFlow);
                flows.put(node.getId(), endFlow);
            }
        }

        for (int i = 0; i < nodes.size(); i++) {
            ProcessNode node = nodes.get(i);
            if (node.getNodeType().equals(ProcessHelper.NodeTypeEnum.START.getType())) {
                FlowElement startFlow = flows.get(node.getId());
                FlowElement nextFlow = flows.get(nodes.get(i + 1).getId());
                bpmnProcessDef.addFlowElement(ProcessEvent.sequenceFlow(startFlow.getId(), nextFlow.getId()));
            } else if (node.getNodeType().equals(ProcessHelper.NodeTypeEnum.GATEWAY.getType())) {
                // 预留(角色网关)
            } else if (node.getNodeType().equals(ProcessHelper.NodeTypeEnum.USERTASK.getType())) {
                if (!ProcessConstants.TICKET.equals(process.getBusinessCode())) {
                    FlowElement userFlow = flows.get(node.getId());
                    FlowElement wheatherFlow = flowsAdditionals.get(node.getId());
                    bpmnProcessDef.addFlowElement(ProcessEvent.sequenceFlow(userFlow.getId(), wheatherFlow.getId()));

                    //设置条件
                    FlowElement passFlow = flows.get(nodes.get(i + 1).getId());
                    bpmnProcessDef.addFlowElement(ProcessEvent.sequenceFlow(wheatherFlow.getId(),
                                                                            passFlow.getId(), "通过",
                                                                            "${audit=='pass'}"));

                    ProcessNode rejectNode = ProcessEvent.findRejectNode(nodes);
                    FlowElement rejectFlow = flows.get(rejectNode.getId());
                    bpmnProcessDef.addFlowElement(ProcessEvent.sequenceFlow(wheatherFlow.getId(),
                                                                            rejectFlow.getId(), "拒绝",
                                                                            "${audit=='reject'}"));
                } else {
                    FlowElement userFlow = flows.get(node.getId());
                    FlowElement nextFlow = flows.get(nodes.get(i + 1).getId());
                    bpmnProcessDef.addFlowElement(ProcessEvent.sequenceFlow(userFlow.getId(), nextFlow.getId()));
                }
            } else if (node.getNodeType().equals(ProcessHelper.NodeTypeEnum.SERVICE.getType())) {
                FlowElement serviceFlow = flows.get(node.getId());
                FlowElement nextFlow = flows.get(nodes.get(nodes.size() - 1).getId());

                bpmnProcessDef.addFlowElement(ProcessEvent.sequenceFlow(serviceFlow.getId(), nextFlow.getId()));
            } else if (node.getNodeType().equals(ProcessHelper.NodeTypeEnum.END.getType())) {
                //
            }
        }
        BpmnModel model = new BpmnModel();
        model.addProcess(bpmnProcessDef);

        // 需要jgraphx-1.10.4.1.jar包
        new cn.com.cloudstar.rightcloud.system.activiti.bpmn.BpmnAutoLayout(model).execute();
        String xmlStr = ProcessHelper.getXmlStr(model);
        System.out.println(xmlStr);

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


}
