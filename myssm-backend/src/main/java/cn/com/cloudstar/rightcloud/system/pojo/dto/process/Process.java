package cn.com.cloudstar.rightcloud.system.pojo.dto.process;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 自定义流程
 * @author jerry
 * @date 2018/8/3
 */
@ApiModel(value = "Process",description = "流程定义实体类")
public class Process implements Cloneable {
    @ApiModelProperty(value = "流程定义ID")
    Long id;
    @ApiModelProperty(value = "流程定义key")
    String processCode;
    @ApiModelProperty(value = "流程定义名称")
    String processName;
    String businessCode;
    String businessName;
    Long orgSid;
    @ApiModelProperty(value = "流程定义描述")
    String description;
    String createdBy;
    Date   createdDt;
    String updatedBy;
    Date   updatedDt;
    Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Long getOrgSid() {
        return orgSid;
    }

    public void setOrgSid(Long orgSid) {
        this.orgSid = orgSid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDt() {
        return updatedDt;
    }

    public void setUpdatedDt(Date updatedDt) {
        this.updatedDt = updatedDt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
