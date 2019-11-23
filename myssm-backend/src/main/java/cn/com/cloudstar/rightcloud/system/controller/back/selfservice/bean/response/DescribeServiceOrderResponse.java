package cn.com.cloudstar.rightcloud.system.controller.back.selfservice.bean.response;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.Data;


@Data
@ApiModel(description = "查询关联到项目上的服务")
public class DescribeServiceOrderResponse {
    private Long id;
    private String orderSn;
    private String name;
    private String serviceName;
    private String type;
    private Long orgSid;
    private String serviceId;
    private Long ownerId;
    private String extraAttr;
    private BigDecimal totalAmount;
    private String status;
    private String createdBy;
    private Date createdDt;
    private String updatedBy;
    private Date updatedDt;
    private Long version;
    private String mobile;
    private String email;
    private String applicant;
    private String typeName;
    private String statusName;
    private String orgName;
    private String serviceCategory;
    private Long serviceInstanceId;
    private String processFlag;
    private String stepName;
    private Date currentStepNoticeDt;
    private Date currentStepCreatedDt;
    private Long currentStepId;
    private String ownerRealName;
    private String openType;
    private String opsName;
    private String opsKey;
}
