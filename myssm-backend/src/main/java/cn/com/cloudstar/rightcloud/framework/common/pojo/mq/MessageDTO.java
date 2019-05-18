package cn.com.cloudstar.rightcloud.framework.common.pojo.mq;

import lombok.Data;

/**
 * @author Hong.Wu
 * @date: 19:44 2019/05/18
 */
@Data
public class MessageDTO {
    /**
     * 消息类型
     * {@link cn.com.cloudstar.rightcloud.framework.common.pojo.mq.MQConstants.MessageType}
     */
    private String type;

    private Boolean isSend;

    /**
     * 传输数据 建议是自定义的VO对象
     */
    private Object data;

    public MessageDTO (){}
    public MessageDTO (String type){
        this.type = type;
    }


}
