/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ChaoHong.Mao
 * @date 2016/5/13.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SheetCtrl {
    /**
     * 读取的行数偏移
     */
    int offsetRow() default 0;

    /**
     * 判断最后读取行的cell index
     */
    int valExistsIndex() default 0;

    /**
     * 读取动态数据的key值
     */
    int keyRow() default 0;
}
