package cn.com.cloudstar.rightcloud.system.service.system.impl;

import org.springframework.stereotype.Service;

import cn.com.cloudstar.rightcloud.framework.common.pojo.mq.RabbitHelper;
import cn.com.cloudstar.rightcloud.system.service.mq.MqService;

/**
 * @author Hong.Wu
 * @date: 19:41 2019/05/18
 */
@Service
public class MqServiceImpl implements MqService {

    @Override
    public void sendScheduleSyncEnvMessage() {
        // 发送到schedule
        Object object = RabbitHelper.sendScheduleJobMQAndReturnMessage("aaaaaaaa");
    }
}
