/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Title: Table def</p>
 * <p>Description: </p>
 *
 * @author Chaohong.Mao
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableDef {
    /**
     * Column string.
     *
     * @return the string
     */
    int column();

    /**
     * Key value string [ ].
     *
     * @return the string [ ]
     */
    String[] keyValue() default {};

    /**
     * Key value string [ ].
     *
     * @return int
     */
    CellTypeEnum cellType() default CellTypeEnum.String;
}
