package cn.com.cloudstar.rightcloud.system.controller.back.service.bean.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * The type DescribeSelfCategoryResponse.
 * <p>
 *
 * @author Xiaolu.Liu
 * @date 2019/7/2
 */
@Data
@ApiModel(description = "查询关联到项目上的服务")
public class DescribeProjectCategoryResponse {
    @ApiModelProperty(notes = "ID")
    private Long id;

    @ApiModelProperty(notes = "服务名称")
    private String serviceName;

    @ApiModelProperty(notes = "服务的图标路径")
    private String serviceIconPath;

    @ApiModelProperty(notes = "服务的描述")
    private String serviceDesc;

    @ApiModelProperty(notes = "服务类别")
    private String serviceForm;

    @ApiModelProperty(notes = "发布组织")
    private String orgName;
}
