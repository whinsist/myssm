package cn.com.cloudstar.rightcloud.system.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import cn.com.cloudstar.rightcloud.framework.common.annotation.MyCacheable;
import cn.com.cloudstar.rightcloud.system.dao.system.UserMapper;
import cn.com.cloudstar.rightcloud.system.entity.system.User;
import cn.com.cloudstar.rightcloud.system.service.system.UserCacheService;

/**
 * @author Hong.Wu
 * @date: 21:24 2020/02/17
 */
@CacheConfig(cacheNames = "coach")
@Service
public class UserCacheServiceImpl implements UserCacheService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @MyCacheable(key = "'userSid:'+#p0", ttl = 3600, state = false)
    public User selectUseCache(Long userSid) {
        User user = userMapper.selectByPrimaryKey(userSid);
        return user;
    }

    @Override
    @CacheEvict(key = "'userSid:'+#p0")
    public void delete(Long userSid)  {

    }
}
