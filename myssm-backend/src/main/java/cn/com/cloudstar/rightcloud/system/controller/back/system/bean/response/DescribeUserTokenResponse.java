package cn.com.cloudstar.rightcloud.system.controller.back.system.bean.response;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 获取用户凭证 出参
 *
 * @author yangchaolang.
 */
@ApiModel(description = "获取用户凭证")
@Setter
@Getter
public class DescribeUserTokenResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("最近登录IP")
    private String lastLoginIp;

    @ApiModelProperty("最近登录时间")
    private Date lastLoginTime;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("皮肤主题")
    private String skinTheme;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("是否admin")
    private Boolean admin;

    @ApiModelProperty("当前组织")
    private CurrentOrgVO currentOrg;

    @ApiModelProperty("当前角色类型")
    private String currentRoleType;

    @ApiModelProperty("是否demo账户")
    private Boolean isDemoAccount;

    @ApiModelProperty("平台类型")
    private String platformType;

    @ApiModelProperty("菜单列表")
    private Map<String, List<TreeNodeVO>> menus;

    @ApiModelProperty("权限列表")
    private List<String> permissions;

    @ApiModelProperty("当前角色名")
    private String currentRoleName;

    @ApiModelProperty("routes")
    private Map<String, String> routes;

    @ApiModelProperty("用户名称")
    private String account;

    @ApiModelProperty("最大数据")
    private String maxDataScope;

    @ApiModelProperty("用户ID")
    private Long userSid;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("用户token ID")
    private String tokenId;

    @ApiModelProperty("saas到期时间")
    private Date limitDate;
    @ApiModelProperty("openId")
    private String openId;
    @ApiModelProperty("微信头像")
    private String avatarUrl;
    @ApiModelProperty("微信名称")
    private String wechatName;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
