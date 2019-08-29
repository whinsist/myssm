package cn.com.cloudstar.rightcloud.system.vo.act;

import java.util.List;

/**
 * 审核节点配置
 * @author yuz
 * @date 2018/8/17
 */
public class ProcessNodeConfig {
    List<ProcessNodeRoleDto> candidates;
    Boolean configEnable;
    ProcessNodeMessageDto messageConfig;
    List<ProcessNodeRoleDto> candidateThirds;
    List<String>  notifyWays;

    public List<ProcessNodeRoleDto> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<ProcessNodeRoleDto> candidates) {
        this.candidates = candidates;
    }

    public Boolean getConfigEnable() {
        return configEnable;
    }

    public void setConfigEnable(Boolean configEnable) {
        this.configEnable = configEnable;
    }

    public ProcessNodeMessageDto getMessageConfig() {
        return messageConfig;
    }

    public void setMessageConfig(ProcessNodeMessageDto messageConfig) {
        this.messageConfig = messageConfig;
    }

    public List<ProcessNodeRoleDto> getCandidateThirds() {
        return candidateThirds;
    }

    public void setCandidateThirds(List<ProcessNodeRoleDto> candidateThirds) {
        this.candidateThirds = candidateThirds;
    }

    public List<String> getNotifyWays() {
        return notifyWays;
    }

    public void setNotifyWays(List<String> notifyWays) {
        this.notifyWays = notifyWays;
    }
}
