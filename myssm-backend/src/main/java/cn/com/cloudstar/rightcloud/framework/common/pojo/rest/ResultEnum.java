package cn.com.cloudstar.rightcloud.framework.common.pojo.rest;

/**
 * @author Hong.Wu
 * @date: 8:35 2019/03/24
 */
public enum  ResultEnum {
    SUCCESS(200, "操作成功"),
    ERROR(500, "操作失败"),
    ERROR_SHIRO(4000, "shiro拦截失败");

    private Integer code;
    private String message;

    ResultEnum (Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }


}
