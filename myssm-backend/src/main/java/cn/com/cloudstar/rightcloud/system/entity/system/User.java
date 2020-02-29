package cn.com.cloudstar.rightcloud.system.entity.system;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
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


}