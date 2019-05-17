package cn.com.cloudstar.rightcloud.framework.common.mybatis.interceptor.helper;

import java.lang.annotation.*;


/**
 * 数据权限过滤
 * @author GaoYuan
 * @date 2018/4/17 下午2:40
 */
@Target(ElementType.METHOD) 
@Retention(RetentionPolicy.RUNTIME) 
@Documented
public @interface PermissionAop {
    String value() default "";

}
