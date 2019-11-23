package cn.com.cloudstar.rightcloud.system.controller.back.service.bean.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * The type CreateOrderRequest.
 * <p>
 *
 * @author Xiaolu.Liu
 * @date 2019/7/2
 */
@Data
public class CreateOrderAuditRequest {

    @ApiModelProperty("申请单ID")
    private Long orderId;

//    @ApiModelProperty(value = "用户ID", required = true)
//    private String userSid;

    @ApiModelProperty(value = "审批类型", required = true)
    private String approveType;

    @ApiModelProperty(value = "审批意见", required = true)
    private String approveAdvice;



//    @ApiModelProperty("申请单SN")
//    private String orderSn;

}
