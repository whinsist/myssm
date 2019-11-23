package cn.com.cloudstar.rightcloud.system.service.selfservice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.cloudstar.rightcloud.system.dao.selfservice.ServiceOrderRecordMapper;
import cn.com.cloudstar.rightcloud.system.entity.selfservice.ServiceOrderRecord;
import cn.com.cloudstar.rightcloud.system.service.selfservice.OrderRecordService;

/**
 * @author Hong.Wu
 * @date: 14:22 2019/11/03
 */
@Service
public class OrderRecordServiceImpl implements OrderRecordService {

    @Autowired
    private ServiceOrderRecordMapper serviceOrderRecordMapper;

    @Override
    public void insertSelective(ServiceOrderRecord orderRecord) {
        serviceOrderRecordMapper.insertSelective(orderRecord);
    }
}
