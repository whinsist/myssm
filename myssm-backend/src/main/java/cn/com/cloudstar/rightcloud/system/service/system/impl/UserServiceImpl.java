/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved.
 */

package cn.com.cloudstar.rightcloud.system.service.system.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.hutool.core.collection.CollectionUtil;

import cn.com.cloudstar.rightcloud.framework.common.cache.JedisUtil;
import cn.com.cloudstar.rightcloud.framework.common.constants.AuthConstants;
import cn.com.cloudstar.rightcloud.framework.common.constants.WebConstants;
import cn.com.cloudstar.rightcloud.framework.common.exception.BizException;
import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.framework.common.util.AssertUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.StringUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.WebUtil;
import cn.com.cloudstar.rightcloud.system.dao.system.UserMapper;
import cn.com.cloudstar.rightcloud.system.dao.system.UserTokenMapper;
import cn.com.cloudstar.rightcloud.system.entity.system.Role;
import cn.com.cloudstar.rightcloud.system.entity.system.User;
import cn.com.cloudstar.rightcloud.system.entity.system.UserToken;
import cn.com.cloudstar.rightcloud.system.pojo.beans.UserLoginBean;
import cn.com.cloudstar.rightcloud.system.service.system.EmailTread;
import cn.com.cloudstar.rightcloud.system.service.system.AuthService;
import cn.com.cloudstar.rightcloud.system.service.system.RoleService;
import cn.com.cloudstar.rightcloud.system.service.system.SysConfigService;
import cn.com.cloudstar.rightcloud.system.service.system.UserService;
import cn.com.cloudstar.rightcloud.system.controller.back.system.bean.request.UserEditVo;


/**
 * OrgServiceImpl
 *
 * @author qct
 * @date 2016/1/11.
 */
@Component
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SysConfigService sysconfigService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserTokenMapper userTokenMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserToken login(UserLoginBean user) {
        UserToken userToken = null;
        // 验证验证码
        String realCaptcha = JedisUtil.instance().get(user.getCaptchaKey());
        if (StringUtil.isBlank(user.getCaptchaKey())) {
            throw new BizException("验证码过期");
        }
        if (!realCaptcha.equalsIgnoreCase(user.getCaptcha())) {
            throw new BizException("验证码不正确");
        }
        // 验证用户
        Criteria critera = new Criteria();
        critera.put("account", user.getAccount());

        //1、查询用户是否存在
        List<User> list = userMapper.selectByParams(critera);
        if (CollectionUtil.isNotEmpty(list)) {
            User loginUser = list.get(0);
            //2、判断用户状态
            if (WebConstants.UserStatus.AVAILABILITY.equals(loginUser.getStatus())) {

                //匹配成功标记
                boolean isSuccessFlag = false;

                //3、判断用户密码是否正确
                String md5Passwd = WebUtil.encrypt(user.getPassword(), loginUser.getAccount());
                if (md5Passwd.equals(loginUser.getPassword())) {
                    isSuccessFlag = true;
                }

                if (isSuccessFlag) {
                    //4、查询用户在升级系统是是否可用
                    boolean hasAccess = sysconfigService.querySysUpgradeUserFlag(loginUser.getUserSid());
                    if (hasAccess) {
                        //5、查询用户是否有权限
                        List<Role> roleList = roleService.findRolesByUserSid(loginUser.getUserSid());
                        if (null != roleList && !roleList.isEmpty()) {
                            userToken = authService.createJWT(loginUser);
                            if (logger.isDebugEnabled()) {
                                logger.debug("generated user token({}): {}", loginUser.getAccount(), userToken);
                            }
                            userTokenMapper.insertSelective(userToken);
                            JedisUtil.instance()
                                     .hset(AuthConstants.CACHE_KEY_USERID, loginUser.getUserSid().toString(),
                                           loginUser.getAccount());
                        } else {
                            //没有权限
                            userToken = new UserToken();
                            userToken.setAccessToken("05");
                        }
                    } else {
                        userToken = new UserToken();
                        userToken.setAccessToken("04");
                    }
                } else {
                    // 用户密码错误
                    userToken = new UserToken();
                    userToken.setAccessToken("03");
                }
            } else {
                // 用户状态无效 02:用户无效 021:待审核 022:用户被禁用
                userToken = new UserToken();
                userToken.setAccessToken("02");
                if (WebConstants.UserStatus.NOTAPPROVE.equals(loginUser.getStatus())) {
                    userToken.setAccessToken("021");
                } else if (WebConstants.UserStatus.FORBIDDEN.equals(loginUser.getStatus())) {
                    userToken.setAccessToken("022");
                }
            }
        } else {
            // 用户不存在
            userToken = new UserToken();
            userToken.setAccessToken("01");
        }
        return userToken;
    }

    @Override
    public List<User> selectByParams(Criteria example) {
        return this.userMapper.selectByParams(example);
    }


    @Override
    public int updateUser(User user) {
        return this.userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User selectByPrimaryKey(Long userSid) {
        return this.userMapper.selectByPrimaryKey(userSid);
    }

    @Override
    public int countByParams(Criteria criteria) {
        return this.userMapper.countByParams(criteria);
    }


    @Override
    public void sendEmail() {
        System.out.println("11");
        System.out.println("22");

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new EmailTread("444089024@qq.com"));
//        executorService.execute(() -> {
//            String userEmail = "444089024@qq.com";
//            String subject = "test";
//            String content = "test";
//            boolean sendMailFlag = MailUtil.sendMail(Collections.singletonList(userEmail), null, null, subject, content, null);
//            System.out.println(sendMailFlag);
//        });

        executorService.shutdown();

//        processSendEmail();
        System.out.println("33");
        System.out.println("44");
    }

    @Override
    public List<User> selectByPageNumSize(User user, Integer pageNum, Integer pageSize) {
        return this.userMapper.selectByPageNumSize(user, pageNum, pageSize);
    }

    @Override
    public List<User> selectByPermission(Criteria criteria) {
        return this.userMapper.selectByPermission(criteria);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOrUpdateUser(UserEditVo userEditVo) {
        String oper = userEditVo.getOper();
        AssertUtil.notBlank(oper, "未找到对应的操作");
        // 删除操作
        if ("del".equals(oper)) {
            String ids = userEditVo.getId();
            String[] split = ids.split(",");
            List<Long> deleteIdList = new ArrayList<>();
            for (String id : split) {
                if (StringUtils.isNotBlank(id)) {
                    List<String> notDeleteIdList = Arrays.asList("100", "467");
                    if (notDeleteIdList.contains(id)) {
                        throw new BizException("特殊账号不能删除! id=" + id);
                    }
                    deleteIdList.add(Long.parseLong(id));
                }
            }
            if (CollectionUtil.isNotEmpty(deleteIdList)) {
                Criteria criteria = new Criteria();
                criteria.put("useSidList", deleteIdList);
                this.userMapper.deleteByParams(criteria);
            }
        }
        // 修改操作
        if ("edit".equals(oper)) {
            User user = new User();
            user.setUserSid(userEditVo.getUserSid());
            user.setRealName(userEditVo.getRealName());
            user.setMobile(userEditVo.getMobile());
            user.setStatus(userEditVo.getStatus());
            WebUtil.prepareUpdateParams(user);
            this.userMapper.updateByPrimaryKeySelective(user);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(User user) {
        // 查询account是否存在
        Criteria criteria = new Criteria();
        criteria.put("account", user.getAccount());
        List<User> users = this.userMapper.selectByParams(criteria);
        if (CollectionUtil.isNotEmpty(users)) {
            throw new BizException("account已经存在");
        }
        user.setPassword(WebUtil.encrypt(user.getPassword(), user.getAccount()));
        WebUtil.prepareInsertParams(user);
        this.userMapper.insertSelective(user);
    }

//    @Async("cloudExecutor")
//    public boolean processSendEmail() {
//        String userEmail = "444089024@qq.com";
//        String subject = "test";
//        String content = "test";
//        boolean sendMailFlag = MailUtil.sendMail(Collections.singletonList(userEmail), null, null, subject, content,
//                                                 null);
//        System.out.println(sendMailFlag);
//        return sendMailFlag;
//    }
}
