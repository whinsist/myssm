/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.system.entity.selfservice;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import lombok.Data;

@Data
public class ServiceCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 服务类别
     */
    private String serviceForm;

    /**
     * 服务类型：group（多组合）  unit（单一）
     */
    private String serviceType;

    /**
     * 服务类别名称
     */
    private String serviceFormName;

    /**
     * 1.public  2.private
     */
    private String serviceClass;


    private String serviceCategoryName;

    private Long serviceOwnerId;

    private String serviceOwnerName;

    private String serviceName;

    private String serviceDesc;

    private String serviceIconPath;

    private String serviceConfig;

    private String envConfig;

    private Long serviceWorkflowId;

    private String serviceWorkflowName;

    private Long orgSid;


    private String createdBy;

    private Date createdDt;

    private String updatedBy;

    private Date updatedDt;

    private Long version;

    private String status;

    /**
     * 自服务基础计价
     */
    private BigDecimal price;

    private String resFlag;

    private String serviceDetails;

    private String openType;

    private Integer flag;

    private Set<String> cloudEnvTypes;

    private List<Long> cloudEnvIds;

    private Boolean deployStatus;

    private String serviceComponent;

    private Boolean isIaaS;

    private Boolean isPaaS;

    private String orgName;

    private String groupName;


}
