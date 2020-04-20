package cn.com.cloudstar.rightcloud.system.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.cloudstar.rightcloud.system.dao.system.OrgMapper;
import cn.com.cloudstar.rightcloud.system.entity.system.Org;
import cn.com.cloudstar.rightcloud.system.service.system.OrgService;

/**
 * @author Hong.Wu
 * @date: 22:53 2020/02/29
 */
@Service
public class OrgServiceImpl implements OrgService {

    @Autowired
    private OrgMapper orgMapper;

    @Override
    public Org selectByPrimaryKey(Long orgSid) {
        return orgMapper.selectByPrimaryKey(orgSid);
    }
}
