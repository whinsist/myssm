package cn.com.cloudstar.rightcloud.system.service.process;

import java.util.List;
import java.util.Map;

import cn.com.cloudstar.rightcloud.framework.common.pojo.AuthUser;
import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.system.controller.back.process.bean.request.CreateProcessNodeRequest;
import cn.com.cloudstar.rightcloud.system.controller.back.process.bean.request.CreateProcessRequest;
import cn.com.cloudstar.rightcloud.system.controller.back.process.bean.request.UpdateProcessRequest;
import cn.com.cloudstar.rightcloud.system.entity.act.Process;
import cn.com.cloudstar.rightcloud.system.vo.act.BusinessDto;
import cn.com.cloudstar.rightcloud.system.vo.act.ProcessActivityDto;
import cn.com.cloudstar.rightcloud.system.vo.act.ProcessDto;

/**
 * @author Hong.Wu
 * @date: 9:16 2019/10/27
 */
public interface ProcessService {

    List<Process> selectByParams(Criteria criteria);

    void createProcessDefine(CreateProcessRequest createProcessRequest, AuthUser authUser);

    Map<String, Object> getProcessDetail(Long processId);

    Long addProcessNode(CreateProcessNodeRequest createRequest);

    void deployProcess(Long processId);

    void deleteOrUpdateProcess(UpdateProcessRequest request);

    void startProcess(String processCode, String orderSn, Map<String, Object> variables);

    ProcessDto selectByBusinessId(String orderSn);

    List<ProcessActivityDto> instanceRecords(String orderSn);

    BusinessDto candidateTask(String candidateId);

    void taskPass(String candidateId, String businsessId, Map<String, Object> variables);

    void taskReject(String candidateId, String businsessId, Map<String, Object> variables);

    List<String> candidateHistoryTasks(String assigneeId);
}
