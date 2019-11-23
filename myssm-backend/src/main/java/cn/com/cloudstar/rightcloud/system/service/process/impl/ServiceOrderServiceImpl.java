package cn.com.cloudstar.rightcloud.system.service.process.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;

import cn.com.cloudstar.rightcloud.framework.common.exception.BizException;
import cn.com.cloudstar.rightcloud.framework.common.pojo.AuthUser;
import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.framework.common.util.BeanConvertUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.RequestContextUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.WebUtil;
import cn.com.cloudstar.rightcloud.system.activiti.util.OrderType;
import cn.com.cloudstar.rightcloud.system.activiti.util.ProcessConstants;
import cn.com.cloudstar.rightcloud.system.entity.act.ServiceOrder;
import cn.com.cloudstar.rightcloud.system.controller.back.service.bean.request.CreateOrderAuditRequest;
import cn.com.cloudstar.rightcloud.system.controller.back.service.bean.response.DescribeServiceOrderDetailResponse;
import cn.com.cloudstar.rightcloud.system.dao.service.ServiceOrderDetailMapper;
import cn.com.cloudstar.rightcloud.system.dao.service.ServiceOrderMapper;
import cn.com.cloudstar.rightcloud.system.entity.act.ServiceOrderDetail;
import cn.com.cloudstar.rightcloud.system.entity.act.ServiceOrderRecord;
import cn.com.cloudstar.rightcloud.system.service.process.OrderRecordService;
import cn.com.cloudstar.rightcloud.system.service.process.ServiceOrderService;
import cn.com.cloudstar.rightcloud.system.service.process.ProcessService;
import cn.com.cloudstar.rightcloud.system.status.OrderStatus;
import cn.com.cloudstar.rightcloud.system.vo.act.BusinessDto;
import cn.com.cloudstar.rightcloud.system.vo.act.ProcessActivityDto;
import cn.com.cloudstar.rightcloud.system.vo.act.ProcessDto;

/**
 * @author Hong.Wu
 * @date: 8:30 2019/11/03
 */
@Service
@Slf4j
public class ServiceOrderServiceImpl implements ServiceOrderService {

    //00-驳回后再提交
    private static final String OPER_TYPE_00 = "00";
    // 01-通过；
    private static final String OPER_TYPE_01 = "01";
    //02驳回；
    private static final String OPER_TYPE_02 = "02";
    //03-拒绝；
    private static final String OPER_TYPE_03 = "03";

    @Autowired
    private ServiceOrderMapper serviceOrderMapper;

    @Autowired
    private ProcessService processService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private OrderRecordService orderRecordService;

    @Autowired
    private ServiceOrderDetailMapper serviceOrderDetailMapper;

    @Override
    public List<ServiceOrder> selectByParams(Criteria criteria) {
        return serviceOrderMapper.selectByParams(criteria);
    }

    @Override
    public ServiceOrder selectByPrimaryKey(Long servcieOrderId) {
        return serviceOrderMapper.selectByPrimaryKey(servcieOrderId);
    }

    @Override
    public DescribeServiceOrderDetailResponse selectDetail(Long servcieOrderId) {
        ServiceOrder serviceOrder = serviceOrderMapper.selectByPrimaryKey(servcieOrderId);
        DescribeServiceOrderDetailResponse response = BeanConvertUtil.convert(serviceOrder,
                                                                              DescribeServiceOrderDetailResponse.class);
        //
        ProcessDto processDto = processService.selectByBusinessId(serviceOrder.getOrderSn());
        response.setProcessDto(processDto);

        List<ProcessActivityDto> records = processService.instanceRecords(serviceOrder.getOrderSn());
        response.setRecords(records);

        return response;
    }

    @Override
    public ServiceOrder selectByOrderSn(String orderSn) {
        Criteria criteria = new Criteria();
        criteria.put("orderSn", orderSn);
        List<ServiceOrder> serviceOrders = serviceOrderMapper.selectByParams(criteria);
        return serviceOrders.get(0);
    }

    @Override
    public void updateByPrimaryKeySelective(ServiceOrder serviceOrder) {
        serviceOrderMapper.updateByPrimaryKeySelective(serviceOrder);
    }

    @Override
    public List<ServiceOrder> pagePendingServiceOrders() {
        BusinessDto businessDto = processService.candidateTask(
                RequestContextUtil.getAuthUserInfo().getUserSid().toString());
        if (Objects.isNull(businessDto.getBusinessIds()) || businessDto.getBusinessIds().size() < 1) {
            return Lists.newArrayListWithCapacity(0);
        }
        Criteria criteria = new Criteria();
        criteria.put("orderSnList", businessDto.getBusinessIds());
        List<ServiceOrder> serviceOrders = this.serviceOrderMapper.selectByParams(criteria);
        serviceOrders.stream().forEach(order -> {
            if (StringUtils.isBlank(order.getStepName())) {
                order.setStatusName(businessDto.getBusinessStatus().get(order.getOrderSn()));
            } else {
                order.setStatusName(order.getStepName());
            }
        });

        return serviceOrders;
    }

    @Override
    public void auditServiceOrder(CreateOrderAuditRequest request) {
        // 查询申请单
        ServiceOrder serviceOrder = serviceOrderMapper.selectByPrimaryKey(request.getOrderId());
        AuthUser authUser = RequestContextUtil.getAuthUserInfo(WebUtil.getRequest());

        Task task = taskService.createTaskQuery().processInstanceBusinessKey(serviceOrder.getOrderSn()).singleResult();
        if (Objects.isNull(task)) {
            throw new BizException("审批任务不存在或者已经被其他人处理");
        }
        //
        //serviceOrderMapper.updateByPrimaryKeySelective(serviceOrder);

        // 插入申请记录
        ServiceOrderRecord orderRecord = new ServiceOrderRecord();
        orderRecord.setOrderId(serviceOrder.getId());
        orderRecord.setOrderSn(serviceOrder.getOrderSn());
        orderRecord.setAuditTime(new Date());
        orderRecord.setHandler(authUser.getUserSid().toString());
        orderRecord.setStep(task.getName());

        WebUtil.prepareInsertParams(orderRecord);
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("_audit_uname", authUser.getRealName());
        variables.put("_audit_account", authUser.getAccount());
        variables.put("_audit_approve_advice", request.getApproveAdvice());
        if (OPER_TYPE_01.equals(request.getApproveType())) {

            String approveDesc = String.format("由[%s]审批通过，审批意见：[%s]", authUser.getRealName(),
                                               request.getApproveAdvice());
            variables.put(ProcessConstants.AUDIT_COMMENT, approveDesc);

            orderRecord.setInfo(approveDesc);
            orderRecordService.insertSelective(orderRecord);

            processService.taskPass(authUser.getUserSid().toString(), serviceOrder.getOrderSn(), variables);
        } else if (OPER_TYPE_03.equals(request.getApproveType())) {
            try {
                String approveDesc = String.format("由[%s]拒绝并关闭，审批意见：[%s]", authUser.getRealName(),
                                                   request.getApproveAdvice());
                variables.put(ProcessConstants.AUDIT_COMMENT, approveDesc);

                orderRecord.setInfo(approveDesc);
                orderRecordService.insertSelective(orderRecord);

                processService.taskReject(authUser.getUserSid().toString(), serviceOrder.getOrderSn(), variables);

                List<ServiceOrderDetail> serviceOrderDetails = serviceOrderDetailMapper.selectByParams(
                        new Criteria("orderId", serviceOrder.getId()));

                if (!CollectionUtil.isEmpty(serviceOrderDetails)) {
                    //this.revertIp(JsonUtil.fromJson(serviceOrderDetails.get(0).getServiceConfig()));
                }
            } finally {
                log.info("审核拒绝，释放相关的端口预占用");
                // 释放端口预占用
                //this.unlockServerOrderClusterPorts(serviceOrder);
            }
        }

    }

    @Override
    public List<ServiceOrder> pageProcessedServiceOrders() {
        AuthUser authUserInfo = RequestContextUtil.getAuthUserInfo();
        List<String> taskBusinessIds = processService.candidateHistoryTasks(
                authUserInfo.getUserSid().toString());

        if (CollectionUtil.isEmpty(taskBusinessIds)) {
            return Lists.newArrayListWithCapacity(0);
        }
        Criteria criteria = new Criteria();
        criteria.put("orderSnList", taskBusinessIds);
        List<ServiceOrder> serviceOrders = this.serviceOrderMapper.selectByParams(criteria);
        serviceOrders.stream().forEach(order -> {
            if (!StringUtils.isBlank(order.getStepName()) && !OrderStatus.COMPLETED.equals(order.getStatus())) {
                order.setStatusName(order.getStepName());
            } else if (OrderStatus.COMPLETED.equals(order.getStatus())
                    && OrderType.RELEASE.equals(order.getType())) {
                order.setStatusName("退订完成");
            } else if (OrderStatus.COMPLETED.equals(order.getStatus())
                    && OrderType.CHANGEGRADE.equals(order.getType())) {
                order.setStatusName("变更完成");
            }
        });

        return serviceOrders;
    }
}
