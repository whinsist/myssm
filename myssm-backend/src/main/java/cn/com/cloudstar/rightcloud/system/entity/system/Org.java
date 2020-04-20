package cn.com.cloudstar.rightcloud.system.entity.system;

import java.io.Serializable;
import java.util.Date;

public class Org implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 机构SID
     */
    private Long orgSid;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 机构简称
     */
    private String orgCode;

    /**
     * 机构类型：企业company、部门department、项目project
     */
    private String orgType;

    private Long owner;

    /**
     * 树路径
     */
    private String treePath;

    /**
     * 父机构ID
     */
    private Long parentId;

    /**
     * 图标
     */
    private String orgIcon;

    /**
     * 所在省份
     */
    private Long province;

    /**
     * 所在城市
     */
    private Long city;

    /**
     * 所在区域
     */
    private Long area;

    /**
     * 机构地址
     */
    private String address;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人职位
     */
    private String contactPosition;

    /**
     * 联系人电话
     */
    private String contactPhone;

    private String quotaCtrl;

    /**
     * 配额控制方式
     */
    private String quotaMode;

    /**
     * 传真
     */
    private String fax;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private String status;

    /**
     * 租户ID 
     */
    private String tenantIds;

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
     * @return 机构SID
     */
    public Long getOrgSid() {
        return orgSid;
    }

    /**
     * @param orgSid 
	 *            机构SID
     */
    public void setOrgSid(Long orgSid) {
        this.orgSid = orgSid;
    }

    /**
     * @return 机构名称
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * @param orgName 
	 *            机构名称
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * @return 机构简称
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * @param orgCode 
	 *            机构简称
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * @return 机构类型：企业company、部门department、项目project
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * @param orgType 
	 *            机构类型：企业company、部门department、项目project
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    /**
     * @return 树路径
     */
    public String getTreePath() {
        return treePath;
    }

    /**
     * @param treePath 
	 *            树路径
     */
    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }

    /**
     * @return 父机构ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @param parentId 
	 *            父机构ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * @return 图标
     */
    public String getOrgIcon() {
        return orgIcon;
    }

    /**
     * @param orgIcon 
	 *            图标
     */
    public void setOrgIcon(String orgIcon) {
        this.orgIcon = orgIcon;
    }

    /**
     * @return 所在省份
     */
    public Long getProvince() {
        return province;
    }

    /**
     * @param province 
	 *            所在省份
     */
    public void setProvince(Long province) {
        this.province = province;
    }

    /**
     * @return 所在城市
     */
    public Long getCity() {
        return city;
    }

    /**
     * @param city 
	 *            所在城市
     */
    public void setCity(Long city) {
        this.city = city;
    }

    /**
     * @return 所在区域
     */
    public Long getArea() {
        return area;
    }

    /**
     * @param area 
	 *            所在区域
     */
    public void setArea(Long area) {
        this.area = area;
    }

    /**
     * @return 机构地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address 
	 *            机构地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return 联系人姓名
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName 
	 *            联系人姓名
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return 联系人职位
     */
    public String getContactPosition() {
        return contactPosition;
    }

    /**
     * @param contactPosition 
	 *            联系人职位
     */
    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition;
    }

    /**
     * @return 联系人电话
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * @param contactPhone 
	 *            联系人电话
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getQuotaCtrl() {
        return quotaCtrl;
    }

    public void setQuotaCtrl(String quotaCtrl) {
        this.quotaCtrl = quotaCtrl;
    }

    /**
     * @return 配额控制方式
     */
    public String getQuotaMode() {
        return quotaMode;
    }

    /**
     * @param quotaMode 
	 *            配额控制方式
     */
    public void setQuotaMode(String quotaMode) {
        this.quotaMode = quotaMode;
    }

    /**
     * @return 传真
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax 
	 *            传真
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description 
	 *            描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 租户ID 
     */
    public String getTenantIds() {
        return tenantIds;
    }

    /**
     * @param tenantIds 
	 *            租户ID 
     */
    public void setTenantIds(String tenantIds) {
        this.tenantIds = tenantIds;
    }

    /**
     * @return 创建人
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy 
	 *            创建人
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
     * @param createdDt 
	 *            创建时间
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
     * @param updatedBy 
	 *            更新人
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
     * @param updatedDt 
	 *            更新时间
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
     * @param version 
	 *            版本号
     */
    public void setVersion(Long version) {
        this.version = version;
    }
}