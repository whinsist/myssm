package cn.com.cloudstar.rightcloud.framework.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Hong.Wu
 * @date: 16:45 2020/08/01
 */
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {

    double limitNum();
}
