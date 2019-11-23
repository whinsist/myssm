package cn.com.cloudstar.rightcloud.system.service.process;

import java.util.List;

import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.system.entity.act.ServiceOrder;
import cn.com.cloudstar.rightcloud.system.controller.back.service.bean.request.CreateOrderAuditRequest;
import cn.com.cloudstar.rightcloud.system.controller.back.service.bean.response.DescribeServiceOrderDetailResponse;

/**
 * @author Hong.Wu
 * @date: 23:27 2019/10/27
 */
public interface ServiceOrderService {


    List<ServiceOrder> selectByParams(Criteria criteria);

    ServiceOrder selectByPrimaryKey(Long servcieOrderId);

    DescribeServiceOrderDetailResponse selectDetail(Long servcieOrderId);

    ServiceOrder selectByOrderSn(String orderSn);

    void updateByPrimaryKeySelective(ServiceOrder serviceOrder);

    List<ServiceOrder> pagePendingServiceOrders();

    void auditServiceOrder(CreateOrderAuditRequest request);

    List<ServiceOrder> pageProcessedServiceOrders();
}
