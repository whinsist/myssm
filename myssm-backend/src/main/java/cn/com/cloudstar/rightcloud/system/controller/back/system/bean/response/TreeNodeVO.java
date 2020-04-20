package cn.com.cloudstar.rightcloud.system.controller.back.system.bean.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "树节点")
@Data
public class TreeNodeVO {
    @ApiModelProperty("ID")
    private String id;
    @ApiModelProperty("名称")
    private String name;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty("icon")
    private String icon;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty("special")
    private String special;
    @ApiModelProperty("子节点")
    private List<TreeNodeVO> children;
}
