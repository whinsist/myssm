package cn.com.cloudstar.rightcloud.system.controller.back.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.framework.common.pojo.rest.ResultObject;
import cn.com.cloudstar.rightcloud.framework.common.pojo.rest.ResultObjectUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.BeanConvertUtil;
import cn.com.cloudstar.rightcloud.system.controller.back.service.bean.entity.ServiceCategory;
import cn.com.cloudstar.rightcloud.system.controller.back.service.bean.request.CreateOrderRequest;
import cn.com.cloudstar.rightcloud.system.controller.back.service.bean.response.DescribeProjectCategoryResponse;
import cn.com.cloudstar.rightcloud.system.service.process.ServiceCategoryService;

/**
 * @author Hong.Wu
 * @date: 22:33 2019/10/27
 */
@RestController
public class ServiceCategoryController {

    @Autowired
    private ServiceCategoryService serviceCategoryService;

    @PostMapping("/back/service_category")
    public List<DescribeProjectCategoryResponse> list() {
        Criteria criteria = new Criteria();
        criteria.setDistinct(true);
        List<ServiceCategory> serviceCategories = serviceCategoryService.selectByParams(criteria);
        return BeanConvertUtil.convert(serviceCategories, DescribeProjectCategoryResponse.class);
    }

    @GetMapping("/back/service_category/{servcieCategoryId}")
    public ResultObject detail(@PathVariable Long servcieCategoryId) {
        ServiceCategory serviceCategory = serviceCategoryService.selectByPrimaryKey(servcieCategoryId);
        return ResultObjectUtil.success(serviceCategory);
    }

    @PostMapping("/back/service_category/apply")
    public ResultObject apply(@RequestBody CreateOrderRequest createOrderRequest) {
        serviceCategoryService.applyServiceCateory(createOrderRequest);
        return ResultObjectUtil.success();
    }

}
