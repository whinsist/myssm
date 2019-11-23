package cn.com.cloudstar.rightcloud.system.service.process;

import java.util.List;

import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.system.controller.back.service.bean.entity.ServiceCategory;
import cn.com.cloudstar.rightcloud.system.controller.back.service.bean.request.CreateOrderRequest;

/**
 * @author Hong.Wu
 * @date: 23:27 2019/10/27
 */
public interface ServiceCategoryService {

    ServiceCategory selectByPrimaryKey(Long servcieCategoryId);

    List<ServiceCategory> selectByParams(Criteria criteria);

    void applyServiceCateory(CreateOrderRequest createOrderRequest);
}
