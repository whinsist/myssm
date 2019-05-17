package cn.com.cloudstar.rightcloud.system.pojo;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户SID
     */
    private Long userSid;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 用户编码
     */
    private String userCode;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * BASE64密码
     */
    private String password64;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别 0:男 1:女
     */
    private Integer sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 职务头衔
     */
    private String title;

    /**
     * 企业ID
     */
    private Long companyId;

    /**
     * 项目ID
     */
    private Long projectId;

    /**
     * 组织ID
     */
    private Long orgSid;

    /**
     * 用户状态（0:禁用，1:有效，2:锁定）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 密码错误次数
     */
    private Integer errorCount;

    /**
     * 上次登录时间
     */
    private Date lastLoginTime;

    /**
     * 上次登录IP地址
     */
    private String lastLoginIp;

    /**
     * 服务限制数量
     */
    private Integer serviceLimitQuantity;

    /**
     * 申请理由
     */
    private String applyReason;

    /**
     * 最大短信数
     */
    private Integer smsMax;

    private String uuid;

    /**
     * 用户偏好主题
     */
    private String skinTheme;

    /**
     * 第三方认证绑定ID
     */
    private String authId;

    /**
     * 账户认证类型 1. local 2. ad
     */
    private String authType;

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
    private String headImage;

    /**
     * @return 用户SID
     */
    public Long getUserSid() {
        return userSid;
    }

    /**
     * @param userSid
     *            用户SID
     */
    public void setUserSid(Long userSid) {
        this.userSid = userSid;
    }

    /**
     * @return 用户类型
     */
    public String getUserType() {
        return userType;
    }

    /**
     * @param userType
     *            用户类型
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * @return 用户编码
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * @param userCode
     *            用户编码
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
     * @return 账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account
     *            账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return BASE64密码
     */
    public String getPassword64() {
        return password64;
    }

    /**
     * @param password64
     *            BASE64密码
     */
    public void setPassword64(String password64) {
        this.password64 = password64;
    }

    /**
     * @return 真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName
     *            真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * @return 性别 0:男 1:女
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * @param sex
     *            性别 0:男 1:女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * @return 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return 手机
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     *            手机
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return 职务头衔
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            职务头衔
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return 企业ID
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId
     *            企业ID
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * @return 项目ID
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * @param projectId
     *            项目ID
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    /**
     * @return 组织ID
     */
    public Long getOrgSid() {
        return orgSid;
    }

    /**
     * @param orgSid
     *            组织ID
     */
    public void setOrgSid(Long orgSid) {
        this.orgSid = orgSid;
    }

    /**
     * @return 用户状态（0:禁用，1:有效，2:锁定）
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            用户状态（0:禁用，1:有效，2:锁定）
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     *            备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return 密码错误次数
     */
    public Integer getErrorCount() {
        return errorCount;
    }

    /**
     * @param errorCount
     *            密码错误次数
     */
    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    /**
     * @return 上次登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * @param lastLoginTime
     *            上次登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * @return 上次登录IP地址
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * @param lastLoginIp
     *            上次登录IP地址
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    /**
     * @return 服务限制数量
     */
    public Integer getServiceLimitQuantity() {
        return serviceLimitQuantity;
    }

    /**
     * @param serviceLimitQuantity
     *            服务限制数量
     */
    public void setServiceLimitQuantity(Integer serviceLimitQuantity) {
        this.serviceLimitQuantity = serviceLimitQuantity;
    }

    /**
     * @return 申请理由
     */
    public String getApplyReason() {
        return applyReason;
    }

    /**
     * @param applyReason
     *            申请理由
     */
    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    /**
     * @return 最大短信数
     */
    public Integer getSmsMax() {
        return smsMax;
    }

    /**
     * @param smsMax
     *            最大短信数
     */
    public void setSmsMax(Integer smsMax) {
        this.smsMax = smsMax;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return 用户偏好主题
     */
    public String getSkinTheme() {
        return skinTheme;
    }

    /**
     * @param skinTheme
     *            用户偏好主题
     */
    public void setSkinTheme(String skinTheme) {
        this.skinTheme = skinTheme;
    }

    /**
     * @return 第三方认证绑定ID
     */
    public String getAuthId() {
        return authId;
    }

    /**
     * @param authId
     *            第三方认证绑定ID
     */
    public void setAuthId(String authId) {
        this.authId = authId;
    }

    /**
     * @return 账户认证类型 1. local 2. ad
     */
    public String getAuthType() {
        return authType;
    }

    /**
     * @param authType
     *            账户认证类型 1. local 2. ad
     */
    public void setAuthType(String authType) {
        this.authType = authType;
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

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }
}