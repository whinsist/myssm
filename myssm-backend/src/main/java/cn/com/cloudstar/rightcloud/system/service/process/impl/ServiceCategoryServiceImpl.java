package cn.com.cloudstar.rightcloud.system.service.process.impl;

import com.google.common.collect.Maps;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;

import cn.com.cloudstar.rightcloud.framework.common.pojo.AuthUser;
import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.framework.common.util.AuthUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.RequestContextUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.UuidUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.WebUtil;
import cn.com.cloudstar.rightcloud.system.entity.act.ServiceCategory;
import cn.com.cloudstar.rightcloud.system.entity.act.ServiceOrder;
import cn.com.cloudstar.rightcloud.system.controller.back.service.bean.request.CreateOrderRequest;
import cn.com.cloudstar.rightcloud.system.dao.act.ProcessMapper;
import cn.com.cloudstar.rightcloud.system.dao.service.ServiceCategoryMapper;
import cn.com.cloudstar.rightcloud.system.dao.service.ServiceOrderMapper;
import cn.com.cloudstar.rightcloud.system.entity.act.ServiceOrderRecord;
import cn.com.cloudstar.rightcloud.system.entity.act.Process;
import cn.com.cloudstar.rightcloud.system.service.process.ProcessService;
import cn.com.cloudstar.rightcloud.system.service.process.ServiceCategoryService;
import cn.com.cloudstar.rightcloud.system.activiti.util.ProcessHelper;
import cn.com.cloudstar.rightcloud.system.status.OrderStatus;

/**
 * @author Hong.Wu
 * @date: 23:27 2019/10/27
 */
@Service
public class ServiceCategoryServiceImpl implements ServiceCategoryService {
    @Autowired
    private ServiceCategoryMapper serviceCategoryMapper;
    @Autowired
    private ProcessService processService;
    @Autowired
    private ProcessMapper processMapper;
    @Autowired
    private ServiceOrderMapper serviceOrderMapper;


    @Override
    public ServiceCategory selectByPrimaryKey(Long servcieCategoryId) {
        return serviceCategoryMapper.selectByPrimaryKey(servcieCategoryId);
    }

    @Override
    public List<ServiceCategory> selectByParams(Criteria criteria) {
        return serviceCategoryMapper.selectByParams(criteria);
    }

    @Override
    public void applyServiceCateory(CreateOrderRequest request) {
        AuthUser authUser = RequestContextUtil.getAuthUserInfo();
        String businessCode = ProcessHelper.getProcessBusinessCode(request.getType());
        Process businessProcess = processMapper.selectByBusinessCode(businessCode, null);


        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setOrderSn(UuidUtil.getShortUuid("serverOrder-"));
        serviceOrder.setName(request.getName() + "_" + DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN));
        serviceOrder.setStatus(Objects.nonNull(businessProcess) ? OrderStatus.APPROVING : OrderStatus.SUCCEED);
        serviceOrder.setOrgSid(AuthUtil.getCurrentOrgSid());
        serviceOrder.setOwnerId(authUser.getUserSid());
        serviceOrder.setType(request.getType());
        WebUtil.prepareInsertParams(serviceOrder, authUser.getAccount());
        this.serviceOrderMapper.insertSelective(serviceOrder);
        if (!CollectionUtils.isEmpty(serviceOrder.getDetails())) {

        }
        //新增审批提单操作记录
        ServiceOrderRecord orderRecord = new ServiceOrderRecord();


        Map<String, Object> variables = Maps.newHashMap();
        variables.put("_business_code", businessCode);
        variables.put("_service_order_id", serviceOrder.getId().toString());
        variables.put("_service_order_content", serviceOrder.getName());
        variables.put("_service_order_created_time", DateUtil.formatDateTime(serviceOrder.getCreatedDt()));
        variables.put("_apply_uname", authUser.getRealName());
        variables.put("_apply_usersid", authUser.getUserSid().toString());
        variables.put("__org_sid", authUser.getOrgSid().toString());


        processService.startProcess(businessProcess.getProcessCode(), serviceOrder.getOrderSn(), variables);

    }
}
