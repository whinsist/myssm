package cn.com.cloudstar.rightcloud.system.entity.system;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色SID
     */
    private Long roleSid;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 角色状态 (0:禁用，1:有效，9:锁定)
     */
    private String status;

    /**
     * 角色类型(platform:平台角色;project:项目角色)
     */
    private String roleType;

    /**
     * 数据范围
     */
    private String dataScope;

    /**
     * 是否系统内置角色
     */
    private String isSys;

    /**
     * 组织SID
     */
    private Long orgSid;

    /**
     * 默认访问模块
     */
    private String moduleCategory;

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
     * @return 角色SID
     */
    public Long getRoleSid() {
        return roleSid;
    }

    /**
     * @param roleSid
     *            角色SID
     */
    public void setRoleSid(Long roleSid) {
        this.roleSid = roleSid;
    }

    /**
     * @return 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName
     *            角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return 角色编码
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * @param roleCode
     *            角色编码
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * @return 角色描述
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * @param roleDesc
     *            角色描述
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    /**
     * @return 角色状态 (0:禁用，1:有效，9:锁定)
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            角色状态 (0:禁用，1:有效，9:锁定)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 角色类型(platform:平台角色;project:项目角色)
     */
    public String getRoleType() {
        return roleType;
    }

    /**
     * @param roleType
     *            角色类型(platform:平台角色;project:项目角色)
     */
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    /**
     * @return 数据范围
     */
    public String getDataScope() {
        return dataScope;
    }

    /**
     * @param dataScope
     *            数据范围
     */
    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    /**
     * @return 是否系统内置角色
     */
    public String getIsSys() {
        return isSys;
    }

    /**
     * @param isSys
     *            是否系统内置角色
     */
    public void setIsSys(String isSys) {
        this.isSys = isSys;
    }

    /**
     * @return 组织SID
     */
    public Long getOrgSid() {
        return orgSid;
    }

    /**
     * @param orgSid
     *            组织SID
     */
    public void setOrgSid(Long orgSid) {
        this.orgSid = orgSid;
    }

    /**
     * @return 默认访问模块
     */
    public String getModuleCategory() {
        return moduleCategory;
    }

    /**
     * @param moduleCategory
     *            默认访问模块
     */
    public void setModuleCategory(String moduleCategory) {
        this.moduleCategory = moduleCategory;
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