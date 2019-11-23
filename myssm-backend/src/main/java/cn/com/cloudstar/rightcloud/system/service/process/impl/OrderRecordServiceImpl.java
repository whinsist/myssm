package cn.com.cloudstar.rightcloud.system.service.process.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.cloudstar.rightcloud.system.dao.service.ServiceOrderRecordMapper;
import cn.com.cloudstar.rightcloud.system.entity.act.ServiceOrderRecord;
import cn.com.cloudstar.rightcloud.system.service.process.OrderRecordService;

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
