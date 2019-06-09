package cn.com.cloudstar.rightcloud.framework.testtreeutil;

import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import cn.com.cloudstar.rightcloud.framework.common.pojo.TestBean;
import cn.com.cloudstar.rightcloud.framework.common.util.WebUtil;

/**
 * @author Hong.Wu
 * @date: 21:17 2019/06/08
 */
public class Test7PuplationField {

    public static void main(String[] args) throws Exception {

        TestBean testBean = new TestBean();
        testBean.setName("aaa");
        System.out.println("origin ---- "+testBean);

        prepareInsertParams(testBean, "admin");

        System.out.println("now ---- "+testBean);
    }


    private static <T> void prepareInsertParams(T object, String user) throws Exception {
        Date date = new Date();
//        Class<?> clazz = object.getClass();
//        Method setCreateByMethod = clazz.getMethod("setCreateBy", String.class);
//        setCreateByMethod.invoke(object, user);
//        Method setCreateDtMethod = clazz.getMethod("setCreateDt", Date.class);
//        setCreateDtMethod.invoke(object, date);
//
//
//        Method setUpdateByMethod = clazz.getMethod("setUpdateBy", String.class);
//        setUpdateByMethod.invoke(object, user);
//        Method setUpdateDtMethod = clazz.getMethod("setUpdateDt", Date.class);
//        setUpdateDtMethod.invoke(object, date);
//
//        Method setVersionMethod = clazz.getMethod("setVersion", Long.class);
//        setVersionMethod.invoke(object, 1L);

        invokeSet(object, "createBy", user);
        invokeSet(object, "createDt", date);
        invokeSet(object, "updateBy", user);
        invokeSet(object, "updateDt", date);
        invokeSet(object, "version", 1L);

//        WebUtil.prepareInsertParams(user);

//        object.setCreateBy(user);
//        object.setCreateDt(date);
//        object.setUpdateBy(user);
//        object.setCreateDt(date);
//        object.setVersion(1L);

    }

    private static void invokeSet(Object object, String fieldName, Object value) throws Exception {
        String methodName = "set"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
        Method setMethod = object.getClass().getMethod(methodName, value.getClass());
        System.out.println(setMethod);
        setMethod.invoke(object, value);
    }

}
