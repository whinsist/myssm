package cn.com.cloudstar.rightcloud.system.pojo.vo.act;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 流程节点
 * @author yuz
 * @date 2018/8/3
 */
@ApiModel(value = "ProcessNode",description = "流程节点实体类")
public class ProcessNode implements Cloneable {
    Long id;
    @ApiModelProperty(value = "流程节点名称")
    String nodeName;
    Long processId;
    Long versionId;
    @ApiModelProperty(value = "流程节点描述")
    String description;
    String createdBy;
    Date createdDt;
    String updatedBy;
    Date updatedDt;
    Integer nodeType;
    String configData;
    Float  sortNum;
    /**
     * 0: 正常节点(开始、关闭)
     * 1: 服务节点(开通服务、拒绝关闭)
     * 2: 服务拒绝关闭节点
     * 3: 服务开通审批节点
     * 103: 发布持久化节点
     * 104: 已删除
     */
    Integer status;
    /**
     * 流程标识
     */
    String processIdentify;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
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

    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }

    public String getConfigData() {
        return configData;
    }

    public void setConfigData(String configData) {
        this.configData = configData;
    }

    public Float getSortNum() {
        return sortNum;
    }

    public void setSortNum(Float sortNum) {
        this.sortNum = sortNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public Object clone () throws CloneNotSupportedException {
        return super.clone();
    }

    public String getProcessIdentify() {
        return processIdentify;
    }

    public void setProcessIdentify(String processIdentify) {
        this.processIdentify = processIdentify;
    }
}
