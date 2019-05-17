/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class AuthUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户SID
     */
    private Long userSid;

    /**
     * 账号
     */
    private String account;

    /**
     * 账号
     */
    private String accountName;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 部门ID
     */
    private Long orgSid;

    /**
     * 密码
     */
    private String password;

    /**
     * UUID
     */
    private String uuid;

    /**
     * 用户真实姓名
     */
    private String realName;

    /**
     * 性别 0:男 1:女
     */
    private Integer sex;

    /**
     * 短信限制
     */
    private Integer smsMax;
    /**
     * 已用短信条数
     */
    private Integer smsUsed;


    /**
     * 短信限制
     */
    private Integer smsRemain;

    /**
     * 电子邮件地址
     */
    private String email;

    /**
     * 手机
     */
    private String mobile;

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
     * 当前请求Ip
     */
    private String currentRequestIp;

    /**
     * 租户SID
     */
    private Long mgtObjSid;

    /**
     * 用户状态
     */
    private String status;

    /**
     * 角色
     */
    private String roles;

    /**
     * 权限
     */
    private String permissions;

    /**
     * 企业ID
     */
    private Long companyId;
    private String companyName;
    private String userTypeName;
    private String sexName;
    private String statusName;
    private String balance;
    private String userLevel;
    private String userLevelName;
    private String accountStartTime;
    private String accountEndTime;
    private String totalQuota;
    /**
     * 项目id
     *
     * @return
     */
    private String userMgtObj;

    public AuthUser() {
    }

    public AuthUser(Long userSid, String account, String accountName, String userType, String password, String uuid,
                    String realName, Integer sex, String email, String mobile, String remark, Integer errorCount,
                    Date lastLoginTime, String lastLoginIp, String currentRequestIp, Long mgtObjSid, String status,
                    String roles, String permissions, String userTypeName, String sexName, String statusName,
                    String balance, String userLevel, String userLevelName, String accountStartTime,
                    String accountEndTime, String totalQuota, String userMgtObj, Long companyId, String companyName) {
        this.userSid = userSid;
        this.account = account;
        this.accountName = accountName;
        this.userType = userType;
        this.password = password;
        this.uuid = uuid;
        this.realName = realName;
        this.sex = sex;
        this.email = email;
        this.mobile = mobile;
        this.remark = remark;
        this.errorCount = errorCount;
        this.lastLoginTime = lastLoginTime;
        this.lastLoginIp = lastLoginIp;
        this.currentRequestIp = currentRequestIp;
        this.mgtObjSid = mgtObjSid;
        this.status = status;
        this.roles = roles;
        this.permissions = permissions;
        this.userTypeName = userTypeName;
        this.sexName = sexName;
        this.statusName = statusName;
        this.balance = balance;
        this.userLevel = userLevel;
        this.userLevelName = userLevelName;
        this.accountStartTime = accountStartTime;
        this.accountEndTime = accountEndTime;
        this.totalQuota = totalQuota;
        this.userMgtObj = userMgtObj;
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public boolean isAdmin() {
        return Objects.equals(userSid, 100L);
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserLevelName() {
        return userLevelName;
    }

    public void setUserLevelName(String userLevelName) {
        this.userLevelName = userLevelName;
    }

    public String getAccountStartTime() {
        return accountStartTime;
    }

    public void setAccountStartTime(String accountStartTime) {
        this.accountStartTime = accountStartTime;
    }

    public String getAccountEndTime() {
        return accountEndTime;
    }

    public void setAccountEndTime(String accountEndTime) {
        this.accountEndTime = accountEndTime;
    }

    public String getTotalQuota() {
        return totalQuota;
    }

    public void setTotalQuota(String totalQuota) {
        this.totalQuota = totalQuota;
    }

    public String getCurrentRequestIp() {
        return currentRequestIp;
    }

    public void setCurrentRequestIp(String currentRequestIp) {
        this.currentRequestIp = currentRequestIp;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 用户SID
     */
    public Long getUserSid() {
        return userSid;
    }

    /**
     * @param userSid 用户SID
     */
    public void setUserSid(Long userSid) {
        this.userSid = userSid;
    }

    /**
     * @return the userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     * @param userType the userType to set
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * @return 账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account 账号
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
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return 用户真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName 用户真实姓名
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
     * @param sex 性别 0:男 1:女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * @return 电子邮件地址
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email 电子邮件地址
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
     * @param mobile 手机
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark 备注
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
     * @param errorCount 密码错误次数
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
     * @param lastLoginTime 上次登录时间
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
     * @param lastLoginIp 上次登录IP地址
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the userMgtObj
     */
    public String getUserMgtObj() {
        return userMgtObj;
    }

    /**
     * @param userMgtObj the userMgtObj to set
     */
    public void setUserMgtObj(String userMgtObj) {
        this.userMgtObj = userMgtObj;
    }

    /**
     * @return the accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName the accountName to set
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * @return the mgtObjSid
     */
    public Long getMgtObjSid() {
        return mgtObjSid;
    }

    /**
     * @param mgtObjSid the mgtObjSid to set
     */
    public void setMgtObjSid(Long mgtObjSid) {
        this.mgtObjSid = mgtObjSid;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public Integer getSmsMax() {
        return smsMax;
    }

    public void setSmsMax(Integer smsMax) {
        this.smsMax = smsMax;
    }

    public Integer getSmsRemain() {
        return smsRemain;
    }

    public void setSmsRemain(Integer smsRemain) {
        this.smsRemain = smsRemain;
    }

    public Integer getSmsUsed() {
        return smsUsed;
    }

    public void setSmsUsed(Integer smsUsed) {
        this.smsUsed = smsUsed;
    }

    public Long getOrgSid() {
        return orgSid;
    }

    public void setOrgSid(Long orgSid) {
        this.orgSid = orgSid;
    }

    public static class Builder {
        private Long userSid;
        private String account;
        private String accountName;
        private String userType;
        private String password;
        private String uuid;
        private String realName;
        private Integer sex;
        private String email;
        private String mobile;
        private String remark;
        private Integer errorCount;
        private Date lastLoginTime;
        private String lastLoginIp;
        private String currentRequestIp;
        private Long mgtObjSid;
        private String status;
        private String roles;
        private String permissions;
        private String userTypeName;
        private String sexName;
        private String statusName;
        private String balance;
        private String userLevel;
        private String userLevelName;
        private String accountStartTime;
        private String accountEndTime;
        private String totalQuota;
        private String userMgtObj;
        private Long companyId;
        private String companyName;

        public Builder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder userSid(Long userSid) {
            this.userSid = userSid;
            return this;
        }

        public Builder account(String account) {
            this.account = account;
            return this;
        }

        public Builder accountName(String accountName) {
            this.accountName = accountName;
            return this;
        }

        public Builder userType(String userType) {
            this.userType = userType;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder uuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder realName(String realName) {
            this.realName = realName;
            return this;
        }

        public Builder sex(Integer sex) {
            this.sex = sex;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder mobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public Builder remark(String remark) {
            this.remark = remark;
            return this;
        }

        public Builder errorCount(Integer errorCount) {
            this.errorCount = errorCount;
            return this;
        }

        public Builder lastLoginTime(Date lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
            return this;
        }

        public Builder lastLoginIp(String lastLoginIp) {
            this.lastLoginIp = lastLoginIp;
            return this;
        }

        public Builder currentRequestIp(String currentRequestIp) {
            this.currentRequestIp = currentRequestIp;
            return this;
        }

        public Builder mgtObjSid(Long mgtObjSid) {
            this.mgtObjSid = mgtObjSid;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder roles(String roles) {
            this.roles = roles;
            return this;
        }

        public Builder permissions(String permissions) {
            this.permissions = permissions;
            return this;
        }

        public Builder userTypeName(String userTypeName) {
            this.userTypeName = userTypeName;
            return this;
        }

        public Builder sexName(String sexName) {
            this.sexName = sexName;
            return this;
        }

        public Builder statusName(String statusName) {
            this.statusName = statusName;
            return this;
        }

        public Builder balance(String balance) {
            this.balance = balance;
            return this;
        }

        public Builder userLevel(String userLevel) {
            this.userLevel = userLevel;
            return this;
        }

        public Builder userLevelName(String userLevelName) {
            this.userLevelName = userLevelName;
            return this;
        }

        public Builder accountStartTime(String accountStartTime) {
            this.accountStartTime = accountStartTime;
            return this;
        }

        public Builder accountEndTime(String accountEndTime) {
            this.accountEndTime = accountEndTime;
            return this;
        }

        public Builder totalQuota(String totalQuota) {
            this.totalQuota = totalQuota;
            return this;
        }

        public Builder userMgtObj(String userMgtObj) {
            this.userMgtObj = userMgtObj;
            return this;
        }

        public Builder companyId(Long companyId) {
            this.companyId = companyId;
            return this;
        }

        public AuthUser build() {
            return new AuthUser(userSid,
                    account,
                    accountName,
                    userType,
                    password,
                    uuid,
                    realName,
                    sex,
                    email,
                    mobile,
                    remark,
                    errorCount,
                    lastLoginTime,
                    lastLoginIp,
                    currentRequestIp,
                    mgtObjSid,
                    status,
                    roles,
                    permissions,
                    userTypeName,
                    sexName,
                    statusName,
                    balance,
                    userLevel,
                    userLevelName,
                    accountStartTime,
                    accountEndTime,
                    totalQuota,
                    userMgtObj,
                    companyId,
                    companyName
            );
        }
    }


}
