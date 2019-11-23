/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved.
 */

package cn.com.cloudstar.rightcloud.system.service.system;


import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.system.entity.system.SysConfig;

import java.util.List;
import java.util.Map;

/**
 * SysConfigService
 *
 * @author admin
 * @date 2018/1/25.
 */
public interface SysConfigService {
    /**
     * 统计
     */
    int countByParams(Criteria example);

    /**
     * 根据主键查询
     */
    SysConfig selectByPrimaryKey(Long configSid);

    /**
     * 根据条件查询
     */
    List<SysConfig> selectByParams(Criteria example);

    /**
     * 根据主键删除
     */
    int deleteByPrimaryKey(Long configSid);

    /**
     * 根据主键更新
     */
    int updateByPrimaryKeySelective(SysConfig record);

    /**
     * 根据主键更新
     */
    int updateByPrimaryKey(SysConfig record);

    /**
     * 根据条件删除
     */
    int deleteByParams(Criteria example);

    /**
     * 根据条件更新
     */
    int updateByParamsSelective(SysConfig record, Criteria example);

    /**
     * 根据条件更新
     */
    int updateByParams(SysConfig record, Criteria example);

    /**
     * 插入数据
     */
    int insert(SysConfig record);

    /**
     * 插入数据
     */
    int insertSelective(SysConfig record);

    /**
     * 根据条件查询
     */
    List<SysConfig> selectConfigTypeByParams(Criteria criteria);
    SysConfig selectByConfigKey(String configKey);

    /**
     * 此方法不会进行空结果验证，调用者需要自己判断
     */
    String getValueByConfigKey(String configKey);

    /**
     * 获取通知配置
     */
    List<Map<String, Object>> getSysNotifyConfig();

    /**
     * 更新通知配置
     */
    boolean updateSysNotifyConfig(Map<String, String> params);

    /**
     * 查询系统的升级消息（提前一天可以查到）
     *
     * @return null:表示没有升级消息
     */
    String queryUpgradeInfo();

    /**
     * 当系统升级时 查询用户是否可以访问
     *
     * @param userSid :用户id
     * @return ture:可以操作 false 不能操作
     */
    boolean querySysUpgradeUserFlag(Long userSid);

    /**
     * 查询当前时间是否在升级中
     */
    boolean queryIsSysUpgradeNow();

    /**
     * 平台是否过期
     */
    boolean isPlatformExpire();

    /**
     * 项目管理员审核是否关闭
     */
    boolean queryProjectAdminAuditIsClose();

    /**
     * 企业管理员审核是否关闭
     */
    boolean queryCompanyAdminAuditIsClose();

    SysConfig findByConfigTypeAndKey(String configType, String configKey);
}