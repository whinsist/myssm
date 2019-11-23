/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved.
 */

package cn.com.cloudstar.rightcloud.system.dao.system;

import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.system.entity.system.UserToken;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * UserTokenMapper
 *
 * @author qct
 * @date 2016/2/25.
 */
@Repository
public interface UserTokenMapper {

    /**
     * 根据条件查询记录总数
     */
    int countByParams(Criteria example);

    /**
     * 根据条件删除记录
     */
    int deleteByParams(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long userTokenId);

    /**
     * 插入
     */
    int insert(UserToken userToken);

    /**
     * 部分插入
     */
    int insertSelective(UserToken userToken);

    /**
     * 根据条件查询记录
     */
    List<UserToken> selectByParams(Criteria example);

    /**
     * 根据主键查询记录
     */
    UserToken selectByPrimaryKey(Long userTokenId);
}
