package cn.com.cloudstar.rightcloud.system.controller.back.process.bean.request;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "添加流程定义审核节点 请求参数")
public class CreateProcessNodeRequest implements Serializable {
    private Long processId;

    @ApiModelProperty(value = "流程节点名称", required = true)
    private String nodeName;

    @ApiModelProperty(value = "流程节点描述")
    private String description;

    @ApiModelProperty(value = "候选角色列表")
    private List<ProcessNodeRoleVO> candidates;

    @ApiModelProperty(value = "第三方候选用户列表")
    private List<ProcessNodeRoleVO> candidateThirds;

    @ApiModelProperty(value = "提醒方式", required = true)
    private List<String> notifyWays;
}

