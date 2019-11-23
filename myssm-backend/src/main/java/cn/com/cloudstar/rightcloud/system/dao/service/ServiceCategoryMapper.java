/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved.
 */

package cn.com.cloudstar.rightcloud.system.dao.service;

import org.springframework.stereotype.Repository;

import java.util.List;
import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.system.controller.back.service.bean.entity.ServiceCategory;

/**
 * ServiceCategoryMapper
 *
 * @author admin
 * @date 2018/1/29.
 */
@Repository
public interface ServiceCategoryMapper {

    /**
     * 根据条件查询记录集
     */
    List<ServiceCategory> selectByParams(Criteria example);


    ServiceCategory selectByPrimaryKey(Long servcieCategoryId);
}