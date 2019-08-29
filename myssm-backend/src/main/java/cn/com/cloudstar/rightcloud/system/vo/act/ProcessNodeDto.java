package cn.com.cloudstar.rightcloud.system.vo.act;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 流程节点传输对象
 * @author jerry
 * @date 2018/8/8
 */
public class ProcessNodeDto extends ProcessNode {

    public ProcessNodeDto () {}

    public ProcessNodeDto (ProcessNode processNodeDto) {
        this.id = processNodeDto.id;
        this.nodeName = processNodeDto.nodeName;
        this.processId = processNodeDto.processId;
        this.versionId = processNodeDto.versionId;
        this.description = processNodeDto.description;
        this.createdBy = processNodeDto.createdBy;
        this.createdDt = processNodeDto.createdDt;
        this.updatedBy = processNodeDto.updatedBy;
        this.updatedDt = processNodeDto.updatedDt;
        this.nodeType = processNodeDto.nodeType;
        this.configData = processNodeDto.configData;
    }

    private String nodeTypeName;
    private String candidateName;
    private String nextNode;
    private Integer nodeNumber;

    @ApiModelProperty(value = "是否可编辑")
    Boolean configEnable;

    @ApiModelProperty(value = "候选角色列表")
    List<ProcessNodeRoleDto> candidates;

    @ApiModelProperty(value = "第三方候选用户列表")
    List<ProcessNodeRoleDto> candidateThirds;

    @ApiModelProperty(value = "审批消息配置")
    ProcessNodeMessageDto messageConfig;

    @ApiModelProperty(value = "提醒方式")
    List<String> notifyWays;

    public String getNodeTypeName() {
        return nodeTypeName;
    }

    public void setNodeTypeName(String nodeTypeName) {
        this.nodeTypeName = nodeTypeName;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getNextNode() {
        return nextNode;
    }

    public void setNextNode(String nextNode) {
        this.nextNode = nextNode;
    }

    public Boolean getConfigEnable() {
        return configEnable;
    }

    public void setConfigEnable(Boolean configEnable) {
        this.configEnable = configEnable;
    }

    public List<ProcessNodeRoleDto> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<ProcessNodeRoleDto> candidates) {
        this.candidates = candidates;
    }

    public List<ProcessNodeRoleDto> getCandidateThirds() {
        return candidateThirds;
    }

    public void setCandidateThirds(List<ProcessNodeRoleDto> candidateThirds) {
        this.candidateThirds = candidateThirds;
    }

    public ProcessNodeMessageDto getMessageConfig() {
        return messageConfig;
    }

    public void setMessageConfig(ProcessNodeMessageDto messageConfig) {
        this.messageConfig = messageConfig;
    }

    public List<String> getNotifyWays() {
        return notifyWays;
    }

    public void setNotifyWays(List<String> notifyWays) {
        this.notifyWays = notifyWays;
    }

    public Integer getNodeNumber() {
        return nodeNumber;
    }

    public void setNodeNumber(Integer nodeNumber) {
        this.nodeNumber = nodeNumber;
    }
}
