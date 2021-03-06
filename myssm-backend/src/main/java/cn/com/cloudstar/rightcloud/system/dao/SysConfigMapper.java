/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved.
 */

package cn.com.cloudstar.rightcloud.system.dao;

import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.system.pojo.SysConfig;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * SysConfigMapper
 *
 * @author admin
 * @date 2018/1/25.
 */
@Repository
public interface SysConfigMapper {
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
    int deleteByPrimaryKey(Long configSid);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(SysConfig record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(SysConfig record);

    /**
     * 根据条件查询记录集
     */
    List<SysConfig> selectByParams(Criteria example);

    /**
     * 根据主键查询记录
     */
    SysConfig selectByPrimaryKey(Long configSid);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByParamsSelective(@Param("record") SysConfig record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据条件更新记录
     */
    int updateByParams(@Param("record") SysConfig record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(SysConfig record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(SysConfig record);

    /**
     * 根据条件查询记录
     */
    List<SysConfig> selectConfigTypeByParams(Criteria criteria);
}