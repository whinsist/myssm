/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Title: Excel def</p>
 * <p>Description: </p>
 *
 * @author Chaohong.Mao
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelDef {
    /**
     * Column string.
     *
     * @return the string
     */
    String column();

    /**
     * Column name string.
     *
     * @return the string
     */
    String columnName();

    /**
     * Width int.
     *
     * @return the int
     */
    int width() default 10;
}
