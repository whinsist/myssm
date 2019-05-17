package cn.com.cloudstar.rightcloud.framework.common.pojo.rest;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Hong.Wu
 * @date: 8:32 2019/03/24
 */
@Setter
@Getter
public class ResultObject {

    private Integer code;
    private String message;
    private Object data;
    public ResultObject (){
    }

    public ResultObject (ResultEnum resultEnum){
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }



}
