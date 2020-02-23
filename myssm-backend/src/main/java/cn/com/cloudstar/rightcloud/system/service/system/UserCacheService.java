package cn.com.cloudstar.rightcloud.system.service.system;

import cn.com.cloudstar.rightcloud.system.entity.system.User;

/**
 * @author Hong.Wu
 * @date: 21:23 2020/02/17
 */
public interface UserCacheService {

    User selectUseCache(Long userSid);

    void delete(Long userSid);
}
