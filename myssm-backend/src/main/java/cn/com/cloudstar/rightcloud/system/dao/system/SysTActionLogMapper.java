/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved.
 */

package cn.com.cloudstar.rightcloud.system.dao.system;

import cn.com.cloudstar.rightcloud.system.entity.system.SysTActionLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * The interface Sys t action log mapper.
 */
@Repository
public interface SysTActionLogMapper {


    /**
     * 根据用户账户查询角色及公司名称
     * @param account 账户
     * @param actionMethod 操作地址
     * @return
     */
    SysTActionLog selectRoleCompanyByAccount(@Param("account") String account, @Param("actionMethod") String actionMethod);


}