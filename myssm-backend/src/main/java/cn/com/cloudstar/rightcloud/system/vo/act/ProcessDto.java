package cn.com.cloudstar.rightcloud.system.vo.act;

import java.io.Serializable;

/**
 *
 * @author yuz
 * @date 2018/8/2
 */
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

    public String getDefineKey() {
        return defineKey;
    }

    public void setDefineKey(String defineKey) {
        this.defineKey = defineKey;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getActLeft() {
        return actLeft;
    }

    public void setActLeft(int actLeft) {
        this.actLeft = actLeft;
    }

    public int getActTop() {
        return actTop;
    }

    public void setActTop(int actTop) {
        this.actTop = actTop;
    }

    public int getActWidth() {
        return actWidth;
    }

    public void setActWidth(int actWidth) {
        this.actWidth = actWidth;
    }

    public int getActHeight() {
        return actHeight;
    }

    public void setActHeight(int actHeight) {
        this.actHeight = actHeight;
    }
}
