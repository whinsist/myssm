package cn.com.cloudstar.rightcloud.system.controller.back.system.bean.response;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户当前组织
 *
 * @author yangchaolang.
 * @date 2019/7/15.
 */
@ApiModel(description = "用户当前组织")
@Data
public class CurrentOrgVO {

    @ApiModelProperty("组织ID")
    private Long orgSid;
    @ApiModelProperty("组织名称")
    private String orgName;
    @ApiModelProperty("组织类型")
    private String orgType;
    @ApiModelProperty("上级组织ID")
    private Long parentOrgSid;
    @ApiModelProperty("项目余额")
    private BigDecimal balance;
    @ApiModelProperty("项目账户 ID")
    private Long accountSid;
    @ApiModelProperty("配额控制方式")
    private String quotaMode;
}
