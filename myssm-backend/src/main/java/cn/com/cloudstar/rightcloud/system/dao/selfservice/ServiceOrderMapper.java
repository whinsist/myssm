/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved.
 */

package cn.com.cloudstar.rightcloud.system.dao.selfservice;

import org.springframework.stereotype.Repository;

import java.util.List;

import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.system.entity.selfservice.ServiceOrder;

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
