/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.pojo;

import java.io.Serializable;
import java.util.Date;


public class Attachment implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long attachmentSid;

    private String attachmentName;

    private String attachmentUrl;

    private String attachmentType;

    private String attachmentTypeName;

    private String attachmentLocation;

    private String attachmentDesc;

    private String originalName;

    private String extName;

    private Long attachmentSize;

    private Date uploadDate;

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
    private Integer version;

    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    /**
     * @return the attachmentDesc
     */
    public String getAttachmentDesc() {
        return attachmentDesc;
    }

    /**
     * @param attachmentDesc the attachmentDesc to set
     */
    public void setAttachmentDesc(String attachmentDesc) {
        this.attachmentDesc = attachmentDesc;
    }

    /**
     * @return the originalName
     */
    public String getOriginalName() {
        return originalName;
    }

    /**
     * @param originalName the originalName to set
     */
    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    /**
     * @return the extName
     */
    public String getExtName() {
        return extName;
    }

    /**
     * @param extName the extName to set
     */
    public void setExtName(String extName) {
        this.extName = extName;
    }

    /**
     * @return the attachmentSize
     */
    public Long getAttachmentSize() {
        return attachmentSize;
    }

    /**
     * @param attachmentSize the attachmentSize to set
     */
    public void setAttachmentSize(Long attachmentSize) {
        this.attachmentSize = attachmentSize;
    }

    /**
     * @return the uploadDate
     */
    public Date getUploadDate() {
        return uploadDate;
    }

    /**
     * @param uploadDate the uploadDate to set
     */
    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Long getAttachmentSid() {
        return attachmentSid;
    }

    public void setAttachmentSid(Long attachmentSid) {
        this.attachmentSid = attachmentSid;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public String getAttachmentLocation() {
        return attachmentLocation;
    }

    public void setAttachmentLocation(String attachmentLocation) {
        this.attachmentLocation = attachmentLocation;
    }

    /**
     * @return the attachmentTypeName
     */
    public String getAttachmentTypeName() {
        return attachmentTypeName;
    }

    /**
     * @param attachmentTypeName the attachmentTypeName to set
     */
    public void setAttachmentTypeName(String attachmentTypeName) {
        this.attachmentTypeName = attachmentTypeName;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the createdDt
     */
    public Date getCreatedDt() {
        return createdDt;
    }

    /**
     * @param createdDt the createdDt to set
     */
    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    /**
     * @return the updatedBy
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy the updatedBy to set
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * @return the updatedDt
     */
    public Date getUpdatedDt() {
        return updatedDt;
    }

    /**
     * @param updatedDt the updatedDt to set
     */
    public void setUpdatedDt(Date updatedDt) {
        this.updatedDt = updatedDt;
    }

    /**
     * @return the version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

}