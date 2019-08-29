package cn.com.cloudstar.rightcloud.system.vo.act;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Process",description = "流程定义实体类")
public class Process implements Cloneable {
    @ApiModelProperty(value = "流程定义ID")
    Long id;
    @ApiModelProperty(value = "流程定义key")
    String processCode;
    @ApiModelProperty(value = "流程定义名称")
    String processName;
    String businessCode;
    String businessName;
    Long orgSid;
    @ApiModelProperty(value = "流程定义描述")
    String description;
    String createdBy;
    Date   createdDt;
    String updatedBy;
    Date   updatedDt;
    Integer status;
}
