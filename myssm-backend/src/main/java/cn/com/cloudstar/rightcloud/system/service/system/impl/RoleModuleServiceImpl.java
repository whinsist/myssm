package cn.com.cloudstar.rightcloud.system.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.cloudstar.rightcloud.system.dao.system.RoleModuleMapper;
import cn.com.cloudstar.rightcloud.system.service.system.RoleModuleService;

@Service
public class RoleModuleServiceImpl implements RoleModuleService {
    @Autowired
    private RoleModuleMapper roleModuleMapper;

}