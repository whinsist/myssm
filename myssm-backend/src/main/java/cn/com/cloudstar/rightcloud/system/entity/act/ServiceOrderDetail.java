/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.system.entity.act;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ServiceOrderDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 申请单明细ID
     */
    private Long id;

    /**
     * 申请单ID
     */
    private Long orderId;

    /**
     * 计费类型：包年包月、按量付费
     */
    private String chargeType;

    /**
     * 服务ID
     */
    private Long serviceId;

    /**
     * 服务类型
     */
    private String serviceType;

    /**
     * 企业ID
     */
    private Long orgSid;


    /**
     * 状态
     */
    private String status;

    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 申请时长
     */
    private Integer duration;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 服务实例ID
     */
    private Long serviceInstanceId;

    /**
     * 退订时间
     */
    private Date endTime;

    /**
     * 版本号
     */
    private Long version;

    /**
     * 服务属性
     */
    private String serviceAttr;

    /**
     * 服务配置
     */
    private String serviceConfig;

    /**
     * 云服务描述
     */
    private String serviceCategoryDesc;

    /**
     * 应用配置
     */
    private List<Map<String, Object>> appConfigs = new ArrayList<>();

    /**
     * 配置显示
     */
    private String instanceTypeDisplay;

    /**
     * 镜像ID
     */
    private String imageId;

    /**
     * 镜像名称
     */
    private String imageName;

    /**
     * 关联云环境名称
     */
    private String cloudEnvName;

    /**
     * 云环境类型
     */
    private String cloudEnvType;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 实例名称
     */
    private String instanceName;

    private List<Map<String, Object>> resVdConfigs = new ArrayList<>();

    private String displayContent;

    private String preSpec;

    /**
     * 自服务部署实例开始时间
     */
    private Date sfServiceDeployInstStartTime;
    /**
     * 字符吴部署实例到期时间
     */
    private Date sfServiceDeployInstEndTime;

    private String resFlag;

    private Long clusterId;

    /**
     * @return 申请单明细ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 申请单明细ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 申请单ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * @param orderId 申请单ID
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * @return 计费类型：包年包月、按量付费
     */
    public String getChargeType() {
        return chargeType;
    }

    /**
     * @param chargeType 计费类型：包年包月、按量付费
     */
    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    /**
     * @return 服务ID
     */
    public Long getServiceId() {
        return serviceId;
    }

    /**
     * @param serviceId 服务ID
     */
    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * @return 服务类型
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * @param serviceType 服务类型
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Long getOrgSid() {
        return orgSid;
    }

    public void setOrgSid(Long orgSid) {
        this.orgSid = orgSid;
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
     * @return 购买数量
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity 购买数量
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return 申请时长
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * @param duration 申请时长
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * @return 金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount 金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    /**
     * @return 服务属性
     */
    public String getServiceAttr() {
        return serviceAttr;
    }

    /**
     * @param serviceAttr 服务属性
     */
    public void setServiceAttr(String serviceAttr) {
        this.serviceAttr = serviceAttr;
    }

    /**
     * @return 服务配置
     */
    public String getServiceConfig() {
        return serviceConfig;
    }

    /**
     * @param serviceConfig 服务配置
     */
    public void setServiceConfig(String serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(Long serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getServiceCategoryDesc() {
        return serviceCategoryDesc;
    }

    public void setServiceCategoryDesc(String serviceCategoryDesc) {
        this.serviceCategoryDesc = serviceCategoryDesc;
    }

    public List<Map<String, Object>> getAppConfigs() {
        return appConfigs;
    }

    public void setAppConfigs(List<Map<String, Object>> appConfigs) {
        this.appConfigs = appConfigs;
    }

    public String getCloudEnvName() {
        return cloudEnvName;
    }

    public void setCloudEnvName(String cloudEnvName) {
        this.cloudEnvName = cloudEnvName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }


    public String getInstanceTypeDisplay() {
        return instanceTypeDisplay;
    }

    public void setInstanceTypeDisplay(String instanceTypeDisplay) {
        this.instanceTypeDisplay = instanceTypeDisplay;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public List<Map<String, Object>> getResVdConfigs() {
        return resVdConfigs;
    }

    public void setResVdConfigs(List<Map<String, Object>> resVdConfigs) {
        this.resVdConfigs = resVdConfigs;
    }

    public String getDisplayContent() {
        return displayContent;
    }

    public void setDisplayContent(String displayContent) {
        this.displayContent = displayContent;
    }

    public String getPreSpec() {
        return preSpec;
    }

    public void setPreSpec(String preSpec) {
        this.preSpec = preSpec;
    }

    /**
     * 格式化申请时长
     */
    public String getTimeLength() {
        if(duration == -1){
            return "永久";
        }

        int days = duration / 24;

        String timeLength = "";
        if (days < 30) {
            timeLength = days + "天";

            return timeLength;
        }

        int months = days / 30;
        days = days % 30;
        if (days > 0) {
            timeLength = months + "个月" + days + "天";
        } else {
            timeLength = months + "个月";
        }

        return timeLength;
    }

    public Date getSfServiceDeployInstStartTime() {
        return sfServiceDeployInstStartTime;
    }

    public void setSfServiceDeployInstStartTime(Date sfServiceDeployInstStartTime) {
        this.sfServiceDeployInstStartTime = sfServiceDeployInstStartTime;
    }

    public Date getSfServiceDeployInstEndTime() {
        return sfServiceDeployInstEndTime;
    }

    public void setSfServiceDeployInstEndTime(Date sfServiceDeployInstEndTime) {
        this.sfServiceDeployInstEndTime = sfServiceDeployInstEndTime;
    }

    public String getResFlag() {
        return resFlag;
    }

    public void setResFlag(String resFlag) {
        this.resFlag = resFlag;
    }

    public String getCloudEnvType() {
        return cloudEnvType;
    }

    public void setCloudEnvType(String cloudEnvType) {
        this.cloudEnvType = cloudEnvType;
    }

    public Long getClusterId() {
        return clusterId;
    }

    public void setClusterId(Long clusterId) {
        this.clusterId = clusterId;
    }
}
