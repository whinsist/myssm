/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved.
 */

package cn.com.cloudstar.rightcloud.system.service.system;

import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.system.entity.system.User;
import cn.com.cloudstar.rightcloud.system.entity.system.UserToken;
import cn.com.cloudstar.rightcloud.system.pojo.beans.UserLoginBean;
import cn.com.cloudstar.rightcloud.system.vo.UserEditVo;

import java.util.List;

/**
 * UserService
 *
 * @author qct
 * @date 2016/1/11.
 */
public interface UserService {


    UserToken login(UserLoginBean userLoginBean);

    List<User> selectByParams(Criteria example);

    int updateUser(User user);

    User selectByPrimaryKey(Long userSid);

    int countByParams(Criteria criteria);

    void sendEmail();

    List<User> selectByPageNumSize(User user, Integer pageNum, Integer pageSize);

    List<User> selectByPermission(Criteria criteria);

    void deleteOrUpdateUser(UserEditVo userEditVo);

    void addUser(User user);
}
