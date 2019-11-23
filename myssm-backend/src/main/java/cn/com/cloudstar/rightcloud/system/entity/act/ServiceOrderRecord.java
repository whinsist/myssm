package cn.com.cloudstar.rightcloud.system.entity.act;

import java.io.Serializable;
import java.util.Date;

public class ServiceOrderRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 记录SID
     */
    private Long id;

    /**
     * 申请单ID
     */
    private Long orderId;

    /**
     * 申请单SN
     */
    private String orderSn;

    /**
     * 处理时间
     */
    private Date auditTime;

    /**
     * 流程类型
     */
    private String handler;

    /**
     * 处理用户名称
     */
    private String handlerName;

    /**
     * 处理环节
     */
    private String step;

    /**
     * 处理信息
     */
    private String info;

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
     * 提醒时间
     */
    private Date noticeDt;

    /**
     * @return 记录SID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 记录SID
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
     * @return 申请单SN
     */
    public String getOrderSn() {
        return orderSn;
    }

    /**
     * @param orderSn 申请单SN
     */
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    /**
     * @return 处理时间
     */
    public Date getAuditTime() {
        return auditTime;
    }

    /**
     * @param auditTime 处理时间
     */
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    /**
     * @return 流程类型
     */
    public String getHandler() {
        return handler;
    }

    /**
     * @param handler 流程类型
     */
    public void setHandler(String handler) {
        this.handler = handler;
    }

    /**
     * @return 处理环节
     */
    public String getStep() {
        return step;
    }

    /**
     * @param step 处理环节
     */
    public void setStep(String step) {
        this.step = step;
    }

    /**
     * @return 处理信息
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param info 处理信息
     */
    public void setInfo(String info) {
        this.info = info;
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

    public Date getNoticeDt() {
        return noticeDt;
    }

    public void setNoticeDt(Date noticeDt) {
        this.noticeDt = noticeDt;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }
}