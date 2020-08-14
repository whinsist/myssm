package cn.com.cloudstar.rightcloud.system.controller.back.selfservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import cn.com.cloudstar.rightcloud.framework.common.pojo.rest.ResultObject;
import cn.com.cloudstar.rightcloud.framework.common.pojo.rest.ResultObjectUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.BeanConvertUtil;
import cn.com.cloudstar.rightcloud.system.entity.selfservice.ServiceOrder;
import cn.com.cloudstar.rightcloud.system.controller.back.selfservice.bean.request.CreateOrderAuditRequest;
import cn.com.cloudstar.rightcloud.system.controller.back.selfservice.bean.response.DescribeServiceOrderResponse;
import cn.com.cloudstar.rightcloud.system.service.selfservice.ServiceOrderService;

/**
 * @author Hong.Wu
 * @date: 22:33 2019/10/27
 */
@RestController
public class OrderAuditController {

    @Autowired
    private ServiceOrderService serviceOrderService;

    @PostMapping("/back/order_audit/pending")
    public List<DescribeServiceOrderResponse> list() {
        List<ServiceOrder> serviceOrders = serviceOrderService.pagePendingServiceOrders();
        return BeanConvertUtil.convert(serviceOrders, DescribeServiceOrderResponse.class);
    }

    @PostMapping("/back/order_audit/processed")
    public List<DescribeServiceOrderResponse> getProcessedServiceOrderList() {
        List<ServiceOrder> serviceOrders = serviceOrderService.pageProcessedServiceOrders();
        return BeanConvertUtil.convert(serviceOrders, DescribeServiceOrderResponse.class);
    }


    @PutMapping("/back/order_audit/submit")
    public ResultObject submit(@RequestBody CreateOrderAuditRequest request) {
        serviceOrderService.auditServiceOrder(request);
        return ResultObjectUtil.success();
    }

}