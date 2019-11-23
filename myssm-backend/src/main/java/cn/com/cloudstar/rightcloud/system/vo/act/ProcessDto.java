package cn.com.cloudstar.rightcloud.system.vo.act;

import java.io.Serializable;

import lombok.Data;

/**
 *
 * @author yuz
 * @date 2018/8/2
 */
@Data
public class ProcessDto implements Serializable {

    /**
     * 流程定义
     */
    String defineKey;
    /**
     * 流程定义ID
     */
    private String code;
    /**
     * 流程定义名称
     */
    private String name;
    /**
     * 流程png图片(base64)
     */
    private String image;
    /**
     * 当前节点左边距
     */
    int actLeft;
    /**
     * 当前节点右边距
     */
    int actTop;
    /**
     * 当前节点宽度
     */
    int actWidth;
    /**
     * 当前节点高度
     */
    int actHeight;

    private String description;

    private String businessCode;
}
