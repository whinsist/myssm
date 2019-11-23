/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.system.entity.system;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel
public class SysConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 配置SID
     */
    @ApiModelProperty(value = "配置SID")
    private Long configSid;

    /**
     * 配置类型
     */
    @ApiModelProperty(value = "配置类型")
    private String configType;

    /**
     * 配置类型
     */
    @ApiModelProperty(value = "配置类型")
    private String configTypeName;

    /**
     * 配置名称
     */
    @ApiModelProperty(value = "配置名称")
    private String configName;

    /**
     * 配置Key
     */
    @ApiModelProperty(value = "配置Key")
    private String configKey;

    /**
     * 配置值
     */
    @ApiModelProperty(value = "配置值")
    private String configValue;

    /**
     * 数据类型
     */
    @ApiModelProperty(value = "数据类型")
    private String dataType;

    /**
     * 显示类型
     */
    @ApiModelProperty(value = "显示类型")
    private String displayType;

    /**
     * 性能单位
     */
    @ApiModelProperty(value = "性能单位")
    private String unit;

    /**
     * 取值区域
     */
    @ApiModelProperty(value = "取值区域")
    private String valueDomain;

    /**
     * 取值增量
     */
    @ApiModelProperty(value = "取值增量")
    private String valueIncrement;

    /**
     * 显示排序
     */
    @ApiModelProperty(value = "显示排序")
    private int sortRank;

    /**
     * 配置描述
     */
    @ApiModelProperty(value = "配置描述")
    private String description;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdDt;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedDt;

    /**
     * 版本号
     */
    private Long version;

    /**
     * @return 配置SID
     */
    public Long getConfigSid() {
        return configSid;
    }

    /**
     * @param configSid 配置SID
     */
    public void setConfigSid(Long configSid) {
        this.configSid = configSid;
    }

    /**
     * @return 配置名称
     */
    public String getConfigName() {
        return configName;
    }

    /**
     * @param configName 配置名称
     */
    public void setConfigName(String configName) {
        this.configName = configName;
    }

    /**
     * @return 配置Key
     */
    public String getConfigKey() {
        return configKey;
    }

    /**
     * @param configKey 配置Key
     */
    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    /**
     * @return 配置值
     */
    public String getConfigValue() {
        return configValue;
    }

    /**
     * @param configValue 配置值
     */
    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    /**
     * @return 配置描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description 配置描述
     */
    public void setDescription(String description) {
        this.description = description;
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

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValueDomain() {
        return valueDomain;
    }

    public void setValueDomain(String valueDomain) {
        this.valueDomain = valueDomain;
    }

    public String getValueIncrement() {
        return valueIncrement;
    }

    public void setValueIncrement(String valueIncrement) {
        this.valueIncrement = valueIncrement;
    }

    public int getSortRank() {
        return sortRank;
    }

    public void setSortRank(int sortRank) {
        this.sortRank = sortRank;
    }

    public String getConfigTypeName() {
        return configTypeName;
    }

    public void setConfigTypeName(String configTypeName) {
        this.configTypeName = configTypeName;
    }
}