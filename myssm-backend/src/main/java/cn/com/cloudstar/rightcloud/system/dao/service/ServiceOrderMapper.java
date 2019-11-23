/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved.
 */

package cn.com.cloudstar.rightcloud.system.dao.service;

import org.springframework.stereotype.Repository;

import java.util.List;

import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.system.controller.back.service.bean.entity.ServiceOrder;

/**
 * ServiceOrderMapper
 *
 * @author admin
 * @date 2018/1/29.
 */
@Repository
public interface ServiceOrderMapper {

    int insertSelective(ServiceOrder record);

    List<ServiceOrder> selectByParams(Criteria criteria);

    ServiceOrder selectByPrimaryKey(Long servcieOrderId);

    void updateByPrimaryKeySelective(ServiceOrder serviceOrder);
}
