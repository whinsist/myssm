/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved.
 */

package cn.com.cloudstar.rightcloud.system.service.impl;

import cn.com.cloudstar.rightcloud.framework.common.constants.SysConfigConstants;
import cn.com.cloudstar.rightcloud.framework.common.constants.WebConstants;
import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.framework.common.util.AESUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.PropertiesUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.StringUtil;
import cn.com.cloudstar.rightcloud.system.dao.SysConfigMapper;
import cn.com.cloudstar.rightcloud.system.pojo.SysConfig;
import cn.com.cloudstar.rightcloud.system.service.SysConfigService;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * system config service
 *
 * @author admin
 */
@Component
public class SysConfigServiceImpl implements SysConfigService {
    private static final Logger logger = LoggerFactory.getLogger(SysConfigServiceImpl.class);
    @Autowired
    private SysConfigMapper sysConfigMapper;

    @Override
    public int countByParams(Criteria example) {
        int count = this.sysConfigMapper.countByParams(example);
        logger.debug("count: {}", count);
        return count;
    }

    @Override
    public String getValueByConfigKey(String configKey) {
        String result = "";
        Criteria criteria = new Criteria();
        criteria.put("configKey", configKey);
        List<SysConfig> configs = sysConfigMapper.selectByParams(criteria);
        if (configs != null && configs.size() > 0) {
            result = configs.get(0).getConfigValue();
        }
        return result;
    }

    @Override
    public SysConfig selectByPrimaryKey(Long configSid) {
        return this.sysConfigMapper.selectByPrimaryKey(configSid);
    }

    @Override
    public List<SysConfig> selectByParams(Criteria example) {
        return this.sysConfigMapper.selectByParams(example);
    }

    @Override
    public int deleteByPrimaryKey(Long configSid) {
        return this.sysConfigMapper.deleteByPrimaryKey(configSid);
    }

    @Override
    public int updateByPrimaryKeySelective(SysConfig record) {
        return this.sysConfigMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysConfig record) {
        return this.sysConfigMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByParams(Criteria example) {
        return this.sysConfigMapper.deleteByParams(example);
    }

    @Override
    public int updateByParamsSelective(SysConfig record, Criteria example) {
        return this.sysConfigMapper.updateByParamsSelective(record, example.getCondition());
    }

    @Override
    public int updateByParams(SysConfig record, Criteria example) {
        return this.sysConfigMapper.updateByParams(record, example.getCondition());
    }

    @Override
    public int insert(SysConfig record) {
        return this.sysConfigMapper.insert(record);
    }

    @Override
    public int insertSelective(SysConfig record) {
        return this.sysConfigMapper.insertSelective(record);
    }

    @Override
    public List<SysConfig> selectConfigTypeByParams(Criteria criteria) {
        return this.sysConfigMapper.selectConfigTypeByParams(criteria);
    }

    @Override
    public SysConfig selectByConfigKey(String configKey) {
        Criteria criteria = new Criteria();
        criteria.put("configKey", configKey);
        List<SysConfig> configs = sysConfigMapper.selectByParams(criteria);
        if (configs != null && configs.size() > 0) {
            return configs.get(0);
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> getSysNotifyConfig() {
        List<Map<String, Object>> resultList = new ArrayList<>();
        // 系统配置
        Criteria criteria = new Criteria();
        criteria.put("configType", WebConstants.ConfigType.NOTIFY);
        List<SysConfig> sysConfigs = this.sysConfigMapper.selectByParams(criteria);
        Map<String, Object> sysMap = new HashMap<>();
        sysMap.put("item", "系统配置");
        sysMap.put("ctrl", "");
        sysMap.put("mode", "sys");
        sysMap.put("id", WebConstants.ConfigType.NOTIFY);
        Map<String, Map<String, String>> datas;
        datas = sysConfigs.stream().map(sysConfig -> new HashMap<>(ImmutableMap.of(sysConfig.getConfigKey(),
                sysConfig.getConfigValue()
        ))).collect(Collectors.toMap((t -> {
            String[] key = t.keySet().stream().findFirst().get().split("\\.");
            return key[key.length - 1];
        }), Function.identity()));

        sysMap.put("data", datas);
        resultList.add(sysMap);

        // APP配置

        return resultList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSysNotifyConfig(Map<String, String> params) {
        Criteria criteria = new Criteria();
        int result = 0;
        if ("sys".equals(params.get("mode"))) {
            // 系统配置
            criteria.put("configType", params.get("id"));
            criteria.put("configKey", params.get("key"));
            SysConfig sysConfig = new SysConfig();
            sysConfig.setConfigValue(params.get("status"));
            result = this.sysConfigMapper.updateByParamsSelective(sysConfig, criteria.getCondition());
        }
        return result != 0;
    }


    /**
     * 当系统升级时 查询用户是否可以访问
     */
    @Override
    public boolean querySysUpgradeUserFlag(Long userSid) {
//        try {
//            //升级时间
//            String upgradeTime = PropertiesUtil.getProperty("platform.upgrade.time");
//            if (StringUtil.isBlank(upgradeTime)) {
//                return true;
//            }
//            String[] timeArr = upgradeTime.split(",");
//            Date startDate = StringUtil.strToDate(timeArr[0], "yyyy-MM-dd HH:mm:ss");
//            Date endDate = StringUtil.strToDate(timeArr[1], "yyyy-MM-dd HH:mm:ss");
//            Date nowDate = new Date();
//            if (nowDate.after(startDate) && nowDate.before(endDate)) {
//                //如果在升级时间内 必须要使用规定的用户
//                String userSidStr = PropertiesUtil.getProperty("platform.upgrade.usersid");
//                if (StringUtil.isBlank(userSidStr)) {
//                    return false;
//                }
//                String[] idArr = userSidStr.split(",");
//                for (String id : idArr) {
//                    if (userSid == Long.parseLong(id)) {
//                        return true;
//                    }
//                }
//                return false;
//            }
//        } catch (Exception e) {
//            logger.error("系统升级查询用户是否可以访问出错：", e);
//        }
//        return true;
        return true;
    }

    /**
     * 查询当前时间是否在升级
     *
     * @return true升级中
     */
    @Override
    public boolean queryIsSysUpgradeNow() {
        try {
            String upgradeTime = PropertiesUtil.getProperty("platform.upgrade.time");
            if (StringUtil.isBlank(upgradeTime)) {
                return false;
            }
            //在时间内 即在升级
            String[] timeArr = upgradeTime.split(",");
            Date startDate = StringUtil.strToDate(timeArr[0], "yyyy-MM-dd HH:mm:ss");
            Date endDate = StringUtil.strToDate(timeArr[1], "yyyy-MM-dd HH:mm:ss");
            Date nowDate = new Date();
            if (nowDate.after(startDate) && nowDate.before(endDate)) {
                return true;
            }
        } catch (Exception e) {
            logger.error("查询当前时间是否在升级出错：", e);
        }
        return false;
    }

    /**
     * 查询系统的升级消息
     */
    @Override
    public String queryUpgradeInfo() {
        try {
            //升级时间
            String upgradeTime = PropertiesUtil.getProperty("platform.upgrade.time");
            //升级消息
            String upgradeInfo = PropertiesUtil.getProperty("platform.upgrade.info");
            //可提前提示的时间(默认一天)
            String advdayStr = PropertiesUtil.getProperty("platform.upgrade.advday");
            int advday = StringUtil.isBlank(advdayStr) ? 1 : Integer.parseInt(advdayStr);
            if (StringUtil.isNotBlank(upgradeTime) && StringUtil.isNotBlank(upgradeInfo)) {
                //如果在升级时间的前一天就能显示升级消息
                String[] timeArr = upgradeTime.split(",");
                Date startDate = StringUtil.strToDate(timeArr[0], "yyyy-MM-dd HH:mm:ss");
                Date endDate = StringUtil.strToDate(timeArr[1], "yyyy-MM-dd HH:mm:ss");
                Date nowDate = new Date();
                if (nowDate.before(endDate)) {
                    if (nowDate.after(startDate) || nowDate.equals(startDate)) {
                        return upgradeInfo;
                    } else {
                        //提前的时间也可显示消息
                        if (startDate.getTime() - nowDate.getTime() <= advday * 24 * 60 * 60 * 1000) {
                            return upgradeInfo;
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("查询升级消息出错：", e);
        }
        return null;
    }

    @Override
    public boolean isPlatformExpire() {
        String expireData = PropertiesUtil.getProperty(SysConfigConstants.PLATFORM_LICENCE_KEY);
        String securityKey = PropertiesUtil.getProperty(SysConfigConstants.SECURITY_KEY);
        if (StringUtil.isNullOrEmpty(expireData) || StringUtil.isNullOrEmpty(securityKey)) {
            return true;
        }
        String value = AESUtil.decrypt(expireData, securityKey);
        Date now = new Date();
        Date date = StringUtil.strToDate(value, StringUtil.DF_YMD_24);
        if (StringUtil.isNullOrEmpty(date)) {
            return true;
        }
        return now.compareTo(date) > 0;
    }

    @Override
    public boolean queryProjectAdminAuditIsClose() {
        /// boolean isCloseProjectAudit = project.getAuditStatus() != null && project.getAuditStatus() == 0;
        SysConfig sysConfig = this.selectByConfigKey(WebConstants.SysConfigKey.PROJECTADMIN_AUDITSTATUS);
        return sysConfig != null && "0".equals(sysConfig.getConfigValue());
    }
    @Override
    public boolean queryCompanyAdminAuditIsClose() {
        /// boolean isCloseCompanyAudit = company.getAuditStatus() != null && company.getAuditStatus() == 0;
        SysConfig sysConfig = this.selectByConfigKey(WebConstants.SysConfigKey.COMPANYADMIN_AUDITSTATUS);
        return sysConfig != null && "0".equals(sysConfig.getConfigValue());
    }

    @Override
    public SysConfig findByConfigTypeAndKey(String configType, String configKey) {
        Criteria criteria = new Criteria();
        criteria.put("configType", configType);
        criteria.put("configKey", configKey);
        List<SysConfig> sysConfigs = sysConfigMapper.selectByParams(criteria);
        if (!CollectionUtils.isEmpty(sysConfigs)) {
            return sysConfigs.get(0);
        }
        return null;
    }
}