/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved.
 */

package cn.com.cloudstar.rightcloud.system.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import cn.com.cloudstar.rightcloud.framework.common.pojo.mq.MessageDTO;

/**
 * 云环境同步回调函数
 *
 * @author wuhong
 * @date 2018.03.08
 */
@Component
@Scope("prototype")
public class SyncEvnCallbackListener {
    private static Logger logger = LoggerFactory.getLogger(SyncEvnCallbackListener.class);

    @Autowired
    private SimpMessagingTemplate template;



    public void listen(MessageDTO messageDTO) {
        logger.info("4############接收schedule云环境同步消息：{}", messageDTO);
    }


}

