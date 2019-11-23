package cn.com.cloudstar.rightcloud.system.activiti.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import org.activiti.bpmn.model.ActivitiListener;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.ExclusiveGateway;
import org.activiti.bpmn.model.ImplementationType;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.ServiceTask;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import cn.com.cloudstar.rightcloud.framework.common.exception.BizException;
import cn.com.cloudstar.rightcloud.system.entity.act.ProcessNode;
import cn.com.cloudstar.rightcloud.system.activiti.util.ProcessHelper.ExpressionsetImplementation;
import cn.com.cloudstar.rightcloud.system.vo.act.ProcessNodeConfig;
import cn.com.cloudstar.rightcloud.system.vo.act.ProcessNodeRoleDto;

public class ProcessEvent{
    public static StartEvent startEvent(String id, ProcessNode processNode) {
        StartEvent startEvent = new StartEvent();
        startEvent.setId(id);
        startEvent.setName(processNode.getNodeName());
        return startEvent;
    }

    public static UserTask taskEvent(String id, ProcessNode processNode) {
        UserTask userTask = new UserTask();
        userTask.setId(id);
        userTask.setName(processNode.getNodeName());
        userTask.setCandidateUsers(Arrays.asList("zhangsan", "lisi", "100"));

        ProcessNodeConfig config = JSON.parseObject(
                processNode.getConfigData(), ProcessNodeConfig.class);

        List<String> candidateList = Lists.newArrayList();
        List<ProcessNodeRoleDto> candidates = config.getCandidates();
        if (candidates != null && candidates.size() > 0) {
            List<String> roleList = candidates.stream().map(role -> role.getType()
                    + "-" + role.getRefId()).collect(Collectors.toList());
            candidateList.addAll(roleList);
        }
        List<ProcessNodeRoleDto> candidateThirds = config.getCandidateThirds();
        if (candidateThirds != null && candidateThirds.size() > 0) {
            List<String> userList = candidateThirds.stream()
                                                   .map(role ->
                                                                "201" + "-" + role.getRefId())
                                                   .collect(Collectors.toList());
            candidateList.addAll(userList);
        }

        if (candidateList.size() > 0) {
            String roleIds = candidateList.stream().collect(Collectors.joining(","));
            String condition = String.format(ExpressionsetImplementation.AuditCandidateInlineMgts, roleIds);
            userTask.setCandidateUsers(Arrays.asList(condition));
        } else {
            throw new BizException("流程节点未现在审批角色或审批人");
        }

        if (Objects.nonNull(config.getNotifyWays()) && config.getNotifyWays().size() > 0) {
            List<ActivitiListener> taskListeners = Lists.newArrayList();

            ActivitiListener createdListener = new ActivitiListener();
            createdListener.setEvent("create");
            createdListener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_EXPRESSION);
            createdListener.setImplementation(String.format(ExpressionsetImplementation.ActTackCreatedCallback, processNode.getId().toString()));
            taskListeners.add(createdListener);

            ActivitiListener completedListener = new ActivitiListener();
            completedListener.setEvent("complete");
            completedListener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_EXPRESSION);
            completedListener.setImplementation(String.format(ExpressionsetImplementation.ActTackCompletedCallback, processNode.getId().toString()));
            taskListeners.add(completedListener);
            userTask.setTaskListeners(taskListeners);
        }
        return userTask;
    }

    public static ExclusiveGateway gatewayEvent(String id, ProcessNode processNode) {
        ExclusiveGateway exclusiveGateway = new ExclusiveGateway();
        exclusiveGateway.setId(id);
        exclusiveGateway.setName(processNode.getNodeName());
        return exclusiveGateway;
    }

    public static ServiceTask serviceOpenEvent(String id, ProcessNode processNode) {
        ServiceTask serviceTask = new ServiceTask();
        serviceTask.setId("open" + id);
        serviceTask.setName(processNode.getNodeName());
        serviceTask.setImplementationType("expression");
        serviceTask.setImplementation(ExpressionsetImplementation.DoOpen);
        return serviceTask;
    }

    public static ServiceTask serviceCloseEvent(String id, ProcessNode processNode) {
        ServiceTask serviceTask = new ServiceTask();
        serviceTask.setId("close" + id);
        serviceTask.setName(processNode.getNodeName());
        serviceTask.setImplementationType("expression");
        serviceTask.setImplementation(ExpressionsetImplementation.DoClose);
        return serviceTask;
    }

    public static EndEvent endEvent(String id, ProcessNode processNode) {
        EndEvent endEvent = new EndEvent();
        endEvent.setId(id);
        endEvent.setName(processNode.getNodeName());
        return endEvent;
    }

    public static SequenceFlow sequenceFlow(String from, String to, String name, String condition) {
        SequenceFlow flow = sequenceFlow(from, to, name);
        flow.setConditionExpression(condition);
        return flow;
    }

    public static SequenceFlow sequenceFlow(String from, String to, String name) {
        SequenceFlow flow = sequenceFlow(from, to);
        flow.setName(name);
        return flow;
    }

    public static SequenceFlow sequenceFlow(String from, String to) {
        SequenceFlow flow = new SequenceFlow();
        flow.setSourceRef(from);
        flow.setTargetRef(to);
        return flow;
    }

    public static ProcessNode findRejectNode(List<ProcessNode> processNodes) {
        for (ProcessNode node : processNodes) {
            if (node.getStatus().equals(2)) {
                return node;
            }
        }
        return null;
    }
}