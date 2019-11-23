package cn.com.cloudstar.rightcloud.system.pojo.vo.act;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 流程审批候选人
 * @author yuz
 * @date 2018/8/17
 */
@ApiModel(value = "ProcessNodeRoleDto",description = "流程审批候选人")
public class ProcessNodeRoleDto {
    @ApiModelProperty(value = "获选人类型")
    String type;
    @ApiModelProperty(value = "获选人名称")
    String name;
    @ApiModelProperty(value = "候选人关联ID")
    String refId;
    Integer status;
    List<ProcessNodeRoleDto> childrens;

    public ProcessNodeRoleDto() {}

    public ProcessNodeRoleDto(String type, String name, String refId, Integer status, List<ProcessNodeRoleDto> childrens) {
        this.type = type;
        this.name = name;
        this.refId = refId;
        this.status = status;
        this.childrens = childrens;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ProcessNodeRoleDto> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<ProcessNodeRoleDto> childrens) {
        this.childrens = childrens;
    }
}
