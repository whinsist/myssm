package cn.com.cloudstar.rightcloud.system.controller.back.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.framework.common.pojo.rest.ResultObject;
import cn.com.cloudstar.rightcloud.framework.common.pojo.rest.ResultObjectUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.BeanConvertUtil;
import cn.com.cloudstar.rightcloud.system.controller.back.service.bean.entity.ServiceOrder;
import cn.com.cloudstar.rightcloud.system.controller.back.service.bean.response.DescribeServiceOrderDetailResponse;
import cn.com.cloudstar.rightcloud.system.controller.back.service.bean.response.DescribeServiceOrderResponse;
import cn.com.cloudstar.rightcloud.system.enums.OrderStatusEnum;
import cn.com.cloudstar.rightcloud.system.enums.OrderTypeEnum;
import cn.com.cloudstar.rightcloud.system.service.process.ServiceOrderService;

/**
 * @author Hong.Wu
 * @date: 22:33 2019/10/27
 */
@RestController
public class ServiceOrderController {

    @Autowired
    private ServiceOrderService serviceOrderService;

    @PostMapping("/back/service_order")
    public List<DescribeServiceOrderResponse> list() {
        Criteria criteria = new Criteria();
        List<ServiceOrder> serviceOrders = serviceOrderService.selectByParams(criteria);
        List<DescribeServiceOrderResponse> resultList = new ArrayList<>();
        serviceOrders.forEach((serviceOrder -> {
            DescribeServiceOrderResponse convert = BeanConvertUtil.convert(serviceOrder,
                                                                           DescribeServiceOrderResponse.class);
            convert.setStatusName(OrderStatusEnum.getName(serviceOrder.getStatus()));
            convert.setTypeName(OrderTypeEnum.getName(serviceOrder.getType()));
            resultList.add(convert);
        }));

        return resultList;
    }

    @GetMapping("/back/service_order/{servcieOrderId}")
    public ResultObject detail(@PathVariable Long servcieOrderId) {
        DescribeServiceOrderDetailResponse response = serviceOrderService.selectDetail(servcieOrderId);
        return ResultObjectUtil.success(response);
    }


}
