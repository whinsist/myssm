package cn.com.cloudstar.rightcloud.framework.common.pojo.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.cloudstar.rightcloud.framework.common.exception.BizException;
import cn.com.cloudstar.rightcloud.framework.common.util.SpringContextHolder;

/**
 * @author Hong.Wu
 * @date: 19:47 2019/05/18
 */
public class RabbitHelper {
    private static Logger logger = LoggerFactory.getLogger(RabbitHelper.class);

    private static RabbitTemplate scheduleRabbitTemplate;

    static {
        scheduleRabbitTemplate = SpringContextHolder.getBean("scheduleAmqpTemplate");
    }

    public static Object sendScheduleJobMQAndReturnMessage(String message) {
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(new HashMap<String, Object>() {{
            put("test1", "test11");
        }});
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setType(MQConstants.MessageType.JOB);
        messageDTO.setData(list);
        logger.info("1############发送到schedule  {}", messageDTO);

        Object returnData = scheduleRabbitTemplate.convertSendAndReceive(MQConstants.ROUTINGKEY, messageDTO);
        if (null == returnData) {
            throw new BizException("调用schedule出错:连接超时");
        }
        Map<String, Object> map = (Map<String, Object>) returnData;
        if (!map.get("status").equals(1)) {
            throw new BizException("调用schedule出错: " + map.get("message"));
        }
        return returnData;
    }


    public static void scheduleSendServer(MessageDTO messageDTO) {
//        scheduleRabbitTemplate.convertAndSend("syncenv.info", messageDTO);
        scheduleRabbitTemplate.convertAndSend("schedule_exchange", "syncenv.info", messageDTO);
    }
}
