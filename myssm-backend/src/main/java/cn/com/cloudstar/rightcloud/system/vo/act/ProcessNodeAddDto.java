package cn.com.cloudstar.rightcloud.system.vo.act;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 添加审批节点dto
 * @author yuz
 * @date 2018/8/20
 */
@ApiModel(value = "ProcessNodeAddDto",description = "流程审批节点传输对象")
public class ProcessNodeAddDto {
    @ApiModelProperty(value = "流程节点名称")
    String nodeName;
    @ApiModelProperty(value = "流程节点描述")
    String description;
    @ApiModelProperty(value = "是否可编辑")
    Boolean configEnable;
    @ApiModelProperty(value = "候选角色列表")
    List<ProcessNodeRoleDto> candidates;
    @ApiModelProperty(value = "第三方候选用户列表")
    List<ProcessNodeRoleDto> candidateThirds;
    @ApiModelProperty(value = "提醒方式")
    List<String> notifyWays;


    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<String> getNotifyWays() {
        return notifyWays;
    }

    public void setNotifyWays(List<String> notifyWays) {
        this.notifyWays = notifyWays;
    }
}
