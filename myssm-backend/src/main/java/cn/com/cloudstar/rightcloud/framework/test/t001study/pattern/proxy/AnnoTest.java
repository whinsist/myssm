package cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.proxy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnoTest {
	
	
	String value();
	
	//HttpMethod method();
}
