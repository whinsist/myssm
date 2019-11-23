package cn.com.cloudstar.rightcloud.system.service.selfservice;

import java.util.List;

import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.system.entity.selfservice.ServiceCategory;
import cn.com.cloudstar.rightcloud.system.controller.back.selfservice.bean.request.CreateOrderRequest;

/**
 * @author Hong.Wu
 * @date: 23:27 2019/10/27
 */
public interface ServiceCategoryService {

    ServiceCategory selectByPrimaryKey(Long servcieCategoryId);

    List<ServiceCategory> selectByParams(Criteria criteria);

    void applyServiceCateory(CreateOrderRequest createOrderRequest);
}
