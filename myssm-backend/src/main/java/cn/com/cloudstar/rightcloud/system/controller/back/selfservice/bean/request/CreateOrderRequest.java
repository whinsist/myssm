package cn.com.cloudstar.rightcloud.system.controller.back.selfservice.bean.request;

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
@ApiModel(description = "创建订单")
public class CreateOrderRequest {
    @ApiModelProperty(value = "订单ID", name = "订单ID")
    private Long id;

    /**
     * 申请单名称
     */
    @ApiModelProperty(value = "申请单名称", name = "申请单名称")
    private String name;

    /**
     * 申请单类型
     */
    @ApiModelProperty(value = "申请单类型", name = "申请单类型")
    private String type;

    /**
     * 服务类型id
     */
    @ApiModelProperty(value = "服务类型id", name = "服务类型id")
    private String serviceId;

    /**
     * 所有者ID
     */
    @ApiModelProperty(value = "所有者ID", name = "所有者ID")
    private Long ownerId;

    @ApiModelProperty(value = "申请单流程标志", name = "申请单流程标志")
    private String processFlag;


//    private List<ServiceOrderDetailVO> details;

    private Long serviceInstanceId;


}
