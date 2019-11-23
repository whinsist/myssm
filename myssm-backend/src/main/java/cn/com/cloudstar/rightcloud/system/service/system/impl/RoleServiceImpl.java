/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved.
 */

package cn.com.cloudstar.rightcloud.system.service.system.impl;

import cn.com.cloudstar.rightcloud.system.dao.system.RoleMapper;
import cn.com.cloudstar.rightcloud.system.entity.system.Role;
import cn.com.cloudstar.rightcloud.system.service.system.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * RoleServiceImpl
 *
 * @author admin
 * @date 2018/1/25.
 */
@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<Role> findRolesByUserSid(Long userSid) {
        return roleMapper.findRolesByUserSid(userSid);
    }

    @Override
    public Role selectByPrimaryKey(Long id) {
        return null;
    }
}