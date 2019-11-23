/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.system.controller.back.service.bean.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import cn.com.cloudstar.rightcloud.system.pojo.dto.process.ServiceOrderDetail;
import cn.com.cloudstar.rightcloud.system.pojo.dto.process.ServiceOrderRecord;

@ApiModel(value = "申请单", description = "申请单")
public class ServiceOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 申请单ID
     */
    @ApiModelProperty(name = "申请单ID")
    private Long id;

    /**
     * 申请单SN
     */
    @ApiModelProperty(value = "申请单SN", name = "申请单SN")
    private String orderSn;

    /**
     * 申请单名称
     */
    @ApiModelProperty(value = "申请单名称", name = "申请单名称")
    private String name;

    /**
     * 服务名称
     */
    @ApiModelProperty(value = "服务名称", name = "服务名称")
    private String serviceName;

    /**
     * 申请单类型
     */
    @ApiModelProperty(value = "申请单类型", name = "申请单类型")
    private String type;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID", name = "企业ID")
    private Long orgSid;

    /**
     * 服务类型id
     */
    @ApiModelProperty(value = "服务类型id", name = "服务类型id")
    private String serviceId;

    /**
     * 所有者ID
     */
    @ApiModelProperty(value = "所有者ID", name = "所有者ID")
    private Long ownerId;

    /**
     * 申请单配置
     */
    @ApiModelProperty(value = "申请单配置", name = "申请单配置")
    private String extraAttr;

    /**
     * 订单总金额
     */
    @ApiModelProperty(value = "订单总金额", name = "订单总金额")
    private BigDecimal totalAmount;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", name = "状态")
    private String status;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", name = "创建人")
    private String createdBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "创建时间")
    private Date createdDt;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人", name = "更新人")
    private String updatedBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", name = "更新时间")
    private Date updatedDt;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号", name = "版本号")
    private Long version;
    @ApiModelProperty(value = "手机号", name = "手机号")
    private String mobile;
    @ApiModelProperty(value = "邮箱", name = "邮箱")
    private String email;

    private String applicant;

    private String typeName;

    private String statusName;

    private String orgName;

    private String serviceCategory;

    private Long serviceInstanceId;

    private List<ServiceOrderRecord> orderRecord;

    private String processFlag;

    private String stepName;

    /**
     * 申请单明细
     */
    private List<ServiceOrderDetail> details;

    /**
     * 当前环节通知发送时间
     */
    private Date currentStepNoticeDt;
    /**
     * 当前环节开始时间
     */
    private Date currentStepCreatedDt;
    /**
     * 当前环节id
     */
    private Long currentStepId;
    /**
     *
     */
    private String ownerRealName;

    /**
     * 主干切过来的 sf_service_category里的数据
     */
    private String openType;

    private String opsName;

    private String opsKey;


    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public List<ServiceOrderRecord> getOrderRecord() {
        return orderRecord;
    }

    public void setOrderRecord(List<ServiceOrderRecord> orderRecord) {
        this.orderRecord = orderRecord;
    }

    /**
     * @return 申请单ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 申请单ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 申请单编号
     */
    public String getOrderSn() {
        return orderSn;
    }

    /**
     * @param orderSn 申请单编号
     */
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    /**
     * @return 申请单名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 申请单名称
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * @return 申请单类型
     */
    public String getType() {
        return type;
    }

    /**
     * @param type 申请单类型
     */
    public void setType(String type) {
        this.type = type;
    }

    public Long getOrgSid() {
        return orgSid;
    }

    public void setOrgSid(Long orgSid) {
        this.orgSid = orgSid;
    }

    /**
     * @return 所有者ID
     */
    public Long getOwnerId() {
        return ownerId;
    }

    /**
     * @param ownerId 所有者ID
     */
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * @return 申请单配置
     */
    public String getExtraAttr() {
        return extraAttr;
    }

    /**
     * @param extraAttr 申请单配置
     */
    public void setExtraAttr(String extraAttr) {
        this.extraAttr = extraAttr;
    }

    /**
     * @return 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 创建人
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy 创建人
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return 创建时间
     */
    public Date getCreatedDt() {
        return createdDt;
    }

    /**
     * @param createdDt 创建时间
     */
    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    /**
     * @return 更新人
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy 更新人
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * @return 更新时间
     */
    public Date getUpdatedDt() {
        return updatedDt;
    }

    /**
     * @param updatedDt 更新时间
     */
    public void setUpdatedDt(Date updatedDt) {
        this.updatedDt = updatedDt;
    }

    /**
     * @return 版本号
     */
    public Long getVersion() {
        return version;
    }

    /**
     * @param version 版本号
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<ServiceOrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ServiceOrderDetail> details) {
        this.details = details;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Date getCurrentStepNoticeDt() {
        return currentStepNoticeDt;
    }

    public void setCurrentStepNoticeDt(Date currentStepNoticeDt) {
        this.currentStepNoticeDt = currentStepNoticeDt;
    }

    public Date getCurrentStepCreatedDt() {
        return currentStepCreatedDt;
    }

    public void setCurrentStepCreatedDt(Date currentStepCreatedDt) {
        this.currentStepCreatedDt = currentStepCreatedDt;
    }

    public Long getCurrentStepId() {
        return currentStepId;
    }

    public void setCurrentStepId(Long currentStepId) {
        this.currentStepId = currentStepId;
    }

    public String getOwnerRealName() {
        return ownerRealName;
    }

    public void setOwnerRealName(String ownerRealName) {
        this.ownerRealName = ownerRealName;
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getProcessFlag() {
        return processFlag;
    }

    public void setProcessFlag(String processFlag) {
        this.processFlag = processFlag;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(String serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public Long getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(Long serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }

    public String getOpsName() {
        return opsName;
    }

    public void setOpsName(String opsName) {
        this.opsName = opsName;
    }

    public String getOpsKey() {
        return opsKey;
    }

    public void setOpsKey(String opsKey) {
        this.opsKey = opsKey;
    }
}
