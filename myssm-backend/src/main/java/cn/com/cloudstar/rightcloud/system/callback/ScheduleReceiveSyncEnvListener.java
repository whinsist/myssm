/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved.
 */

package cn.com.cloudstar.rightcloud.system.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.com.cloudstar.rightcloud.framework.common.pojo.mq.MessageDTO;
import cn.com.cloudstar.rightcloud.framework.common.pojo.mq.RabbitHelper;

/**
 * 云环境同步回调函数
 *
 * @author wuhong
 * @date 2018.03.08
 */
@Component
@Scope("prototype")
public class ScheduleReceiveSyncEnvListener {
    private static Logger logger = LoggerFactory.getLogger(ScheduleReceiveSyncEnvListener.class);


    public Map<String, Object> listen(MessageDTO messageDTO) {
        logger.info("2############schedule接受消息 开始同步云环境：{}", messageDTO);

        asyncProcessSyncEnv();

        Map<String, Object> map = new HashMap<>();
        map.put("status", 1);
        map.put("message", "schedule处理成功");
        return map;
    }

    private void asyncProcessSyncEnv() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            // 同步云环境成功后发送消息到server段
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MessageDTO messageDTO = new MessageDTO("envId_123");
            messageDTO.setData("schedule发送同步信息到server");
            logger.info("3############schedule同步完成 返回server消息：{}", messageDTO);
            RabbitHelper.scheduleSendServer(messageDTO);
        });
        executorService.shutdown();
    }


}

