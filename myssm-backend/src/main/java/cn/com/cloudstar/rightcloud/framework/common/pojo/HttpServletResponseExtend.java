/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.pojo;

/**
 * 扩展了HttpServletResponse接口的标志位(区分原标志位统一采用4位整数)
 *
 * @author Wuhong
 */
public interface HttpServletResponseExtend {

    //系统升级无权限访问
    int SC_UNAUTHORIZED_SYSUPGRADE = 4000;


}
