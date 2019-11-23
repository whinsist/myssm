/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved.
 */

package cn.com.cloudstar.rightcloud.system.service.system;



import cn.com.cloudstar.rightcloud.system.entity.system.Role;

import java.util.List;

/**
 * 角色Service
 *
 * @author zharong
 */
public interface RoleService {

    /**
     * 根据用户sid查询角色
     */
    List<Role> findRolesByUserSid(Long userSid);

    Role selectByPrimaryKey(Long id);
}