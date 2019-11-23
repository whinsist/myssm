package cn.com.cloudstar.rightcloud.system.pojo.process.entity;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 自定义流程
 *
 * @author jerry
 * @date 2018/8/3
 */
@Data
public class Process implements Cloneable {

    private Long id;
    @ApiModelProperty(value = "流程定义key")
    private String processCode;
    @ApiModelProperty(value = "流程定义名称")
    private String processName;
    private String businessCode;
    private String businessName;
    private Long orgSid;
    @ApiModelProperty(value = "流程定义描述")
    private String description;
    private String createdBy;
    private Date createdDt;
    private String updatedBy;
    private Date updatedDt;
    private Integer status;
    private List<String> businessCodes;
    private String orderByClause;
}
