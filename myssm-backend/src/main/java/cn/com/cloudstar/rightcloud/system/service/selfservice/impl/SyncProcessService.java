package cn.com.cloudstar.rightcloud.system.service.selfservice.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import cn.com.cloudstar.rightcloud.system.entity.selfservice.ProcessNode;
import cn.com.cloudstar.rightcloud.system.entity.selfservice.ServiceOrder;
import cn.com.cloudstar.rightcloud.system.dao.selfservice.ProcessNodeMapper;
import cn.com.cloudstar.rightcloud.system.enums.ActivityCanstant;
import cn.com.cloudstar.rightcloud.system.service.selfservice.BusinessService;
import cn.com.cloudstar.rightcloud.system.service.selfservice.ServiceOrderService;
import cn.com.cloudstar.rightcloud.system.pojo.vo.act.ProcessNodeConfig;

/**
 * @author Hong.Wu
 * @date: 10:46 2019/11/03
 */
@Service
@Slf4j
public class SyncProcessService {

    @Autowired
    private BusinessService businessService;

    @Autowired
    private ServiceOrderService serviceOrderService;

    @Autowired
    private ProcessNodeMapper processNodeMapper;

    final ExecutorService threadPool = new ThreadPoolExecutor(4,
                                                              32, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());


    @Transactional(rollbackFor = Exception.class)
    public List<String> auditCandidateInlineMgts(DelegateExecution execution, String auditIds) {
        String[] auditIdArr = auditIds.split(",");
        String businssId = execution.getProcessBusinessKey();

        Map<String, Object> variables = execution.getVariables();
        log.info(ActivityCanstant.PREFIX + "2-1auditCandidateInlineMgts 申请单[{}], 组织: [{}], 候选人参数: [{}]", businssId,
                 variables.get("__org_sid"), auditIds);

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
            allCandidateUsers.addAll(businessService.queryCandidateUsers(businssId, variables, roleIds));
        }

        //根据选定用户选择候选人，需要判断权限
        //log.info("申请单[{}], 候选人列表: {}", businssId, JSON.toJSON(allCandidateUsers));

        String businessCode = (String) variables.get("_business_code");
        ServiceOrder serviceOrder = serviceOrderService.selectByOrderSn(businssId);
        serviceOrder.setStepName(execution.getCurrentActivityName());
        serviceOrder.setId(serviceOrder.getId());
        serviceOrderService.updateByPrimaryKeySelective(serviceOrder);

        List<String> candidateUsers = allCandidateUsers.stream().distinct().collect(Collectors.toList());
        if (candidateUsers.size() < 1) {
            // 自动审批通过（当审批节点没有候选人）(废弃)
            // autoApprovalWhenNoCandidates(execution.getId(), businssId, variables);
        }
        return candidateUsers;
    }

    public void actTackCreatedCallback(DelegateTask task, String nodeId) {
        log.info(ActivityCanstant.PREFIX + "2-2actTackCreatedCallback 申请单[{}]的审批任务[{}]已创建  发送消息等等",
                 task.getExecution().getProcessBusinessKey(),
                 task.getName());

        ProcessNode processNode = processNodeMapper.selectByPrimaryKey(Long.parseLong(nodeId));

        if (processNode != null) {
            // 发送消息
            ProcessNodeConfig config = JSON.parseObject(processNode.getConfigData(), ProcessNodeConfig.class);

            Map<String, Object> execVariables = task.getExecution().getVariables();
            String businessId = task.getExecution().getProcessBusinessKey();
            List<Long> candidates = task.getCandidates()
                                        .stream()
                                        .map(c ->
                                                     Long.parseLong(c.getUserId()))
                                        .collect(Collectors.toList());

//
            threadPool.submit(() -> {
                //businessService.taskCreatedMessage(businessId, candidates, execVariables, config.getNotifyWays());
            });
        }
    }

    public void actTackCompletedCallback(DelegateTask task, String nodeId) {
        Map<String, Object> execVariables = task.getExecution().getVariables();
        String auditUname = (String) execVariables.get("_audit_uname");

        log.info(ActivityCanstant.PREFIX + "3actTackCompletedCallback [{}]完成了申请单[{}]的审批任务[{}]  发送消息之类的", auditUname,
                 task.getExecution().getProcessBusinessKey(), task.getName());

        ProcessNode processNode = processNodeMapper.selectByPrimaryKey(Long.parseLong(nodeId));

        if (processNode != null) {
            // 发送消息
            ProcessNodeConfig config = JSON.parseObject(processNode.getConfigData(), ProcessNodeConfig.class);
            String businessId = task.getExecution().getProcessBusinessKey();
            String usersid = (String) execVariables.get("_apply_usersid");
            threadPool.submit(() -> {
                //businessService.taskCompletedMessage(businessId, auditUname, Long.parseLong(usersid), execVariables, config.getNotifyWays());
            });
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void doOpen(DelegateExecution execution) {
        String businssId = execution.getProcessBusinessKey();
        Map<String, Object> variables = execution.getVariables();

        log.info(ActivityCanstant.PREFIX + "4doOpen 申请单[{}]的审批流程已经全部审批通过，create vm ...", businssId);

        businessService.approve(businssId, variables);
    }
}
