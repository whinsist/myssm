package cn.com.cloudstar.rightcloud.system.controller.back.process.bean.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "请求参数")
public class CreateProcessRequest implements Serializable {

    @ApiModelProperty(value = "流程定义名称")
    private String processName;

    @ApiModelProperty(value = "业务类型")
    private String businessCode;

    @ApiModelProperty(value = "流程定义描述")
    private String description;

}