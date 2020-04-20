package cn.com.cloudstar.rightcloud.system.service.system;

import cn.com.cloudstar.rightcloud.system.entity.system.Org;

/**
 * @author Hong.Wu
 * @date: 22:52 2020/02/29
 */
public interface OrgService {

    Org selectByPrimaryKey(Long orgSid);
}
