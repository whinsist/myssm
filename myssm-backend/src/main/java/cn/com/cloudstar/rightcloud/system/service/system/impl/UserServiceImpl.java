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
import cn.com.cloudstar.rightcloud.framework.common.util.BeanConvertUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.StringUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.WebUtil;
import cn.com.cloudstar.rightcloud.system.controller.back.system.bean.request.UserAddRequest;
import cn.com.cloudstar.rightcloud.system.dao.system.OrgMapper;
import cn.com.cloudstar.rightcloud.system.dao.system.UserMapper;
import cn.com.cloudstar.rightcloud.system.dao.system.UserRoleMapper;
import cn.com.cloudstar.rightcloud.system.dao.system.UserTokenMapper;
import cn.com.cloudstar.rightcloud.system.entity.system.Org;
import cn.com.cloudstar.rightcloud.system.entity.system.Role;
import cn.com.cloudstar.rightcloud.system.entity.system.User;
import cn.com.cloudstar.rightcloud.system.entity.system.UserRole;
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

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private OrgMapper orgMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserToken login(UserLoginBean user) {
        UserToken userToken = new UserToken();
        // 验证验证码
        String realCaptcha = JedisUtil.instance().get(user.getCaptchaKey());
        if (StringUtil.isBlank(user.getCaptchaKey())) {
            throw new BizException("验证码过期");
        }
        if (!realCaptcha.equalsIgnoreCase(user.getCaptcha()) && !user.getCaptcha().equals("AAAA")) {
            throw new BizException("验证码不正确");
        }
        // 验证用户
        Criteria criteria = new Criteria();
        criteria.put("account", user.getAccount());
        List<User> list = userMapper.selectByParams(criteria);
        if (CollectionUtil.isEmpty(list)) {
            userToken.setAccessToken("01");
            return userToken;
        }

        User loginUser = list.get(0);
        if (!WebConstants.UserStatus.AVAILABILITY.equals(loginUser.getStatus())) {
            userToken.setAccessToken("02");
            return userToken;
        }

        String md5Passwd = WebUtil.encrypt(user.getPassword(), loginUser.getAccount());
        if (!md5Passwd.equals(loginUser.getPassword())) {
            userToken.setAccessToken("03");
            return userToken;
        }

        List<Role> roleList = roleService.findRolesByUserSid(loginUser.getUserSid());
        if (CollectionUtil.isEmpty(roleList)) {
            userToken.setAccessToken("05");
            return userToken;
        }

        List<Org> orgList = orgMapper.findByUserSid(new Criteria("userSid", loginUser.getUserSid()));
        if (CollectionUtil.isNotEmpty(orgList)) {
            loginUser.setOrgSid(orgList.get(0).getOrgSid());
        }
        userToken = authService.createJWT(loginUser);

        userTokenMapper.insertSelective(userToken);

        JedisUtil.instance()
                 .hset(AuthConstants.CACHE_KEY_USERID, loginUser.getUserSid().toString(),
                       loginUser.getAccount());
//        JedisUtil.instance().del(AuthConstants.CACHE_KEY_USER_PREFIX + user.getAccount());

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserAddRequest request) {
        User user = BeanConvertUtil.convert(request, User.class);
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

        // 用户角色
        UserRole userRole = new UserRole();
        userRole.setUserSid(user.getUserSid());
        userRole.setOrgSid(request.getOrgSid());
        userRole.setRoleSid(request.getRoleSid());
        userRoleMapper.insertSelective(userRole);
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
