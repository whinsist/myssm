package cn.com.cloudstar.rightcloud.system.controller.back.selfservice.bean.request;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProcessNodeRoleVO implements Serializable {

    @ApiModelProperty(value = "获选人类型", required = true)
    private String type;

    @ApiModelProperty(value = "获选人名称", required = true)
    private String name;

    @ApiModelProperty(value = "候选人关联ID", required = true)
    private String refId;

    @ApiModelProperty(value = "状态值", required = true)
    private Integer status;

    @ApiModelProperty(value = "子节点")
    private List<ProcessNodeRoleVO> childrens;
}