package cn.com.cloudstar.rightcloud.framework.common.pojo.rest;

/**
 * @author Hong.Wu
 * @date: 8:42 2019/03/24
 */
public class ResultObjectUtil {

    public static ResultObject success(){
        return success(null);
    }

    public static ResultObject success(Object object){
        ResultObject resultObject = new ResultObject(ResultEnum.SUCCESS);
        resultObject.setData(object);
        return resultObject;
    }


    public static ResultObject error(){
        return error(null);
    }
    public static ResultObject error(Object object){
        ResultObject resultObject = new ResultObject(ResultEnum.ERROR);
        resultObject.setData(object);
        return resultObject;
    }

}
