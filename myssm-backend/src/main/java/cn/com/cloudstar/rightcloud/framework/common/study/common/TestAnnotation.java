package cn.com.cloudstar.rightcloud.framework.common.study.common;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import cn.com.cloudstar.rightcloud.framework.common.study.pattern.proxy.AnnoTest;
import cn.com.cloudstar.rightcloud.framework.common.study.pattern.proxy.HireHouseImpl;

/**
 * @author Hong.Wu
 * @date: 9:31 2019/10/15
 */
public class TestAnnotation {
    public static void main(String[] args) throws NoSuchMethodException {
        //测试annotation
        Class clazz = HireHouseImpl.class;
        Method method = clazz.getMethod("hire", null);
        boolean hasAnno = method.isAnnotationPresent(AnnoTest.class);
        System.out.println("hasAnno="+hasAnno);
        if(hasAnno){
            AnnoTest annotation = method.getAnnotation(AnnoTest.class);
            String value = annotation.value();
            System.out.println(value);
        }


        Annotation[] annotations = method.getAnnotations();
        for(Annotation annotation : annotations){
            System.out.println("annotation="+annotation+"  "+annotation);
        }
    }
}
