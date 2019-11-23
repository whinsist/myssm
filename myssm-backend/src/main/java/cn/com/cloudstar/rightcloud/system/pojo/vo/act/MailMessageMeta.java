package cn.com.cloudstar.rightcloud.system.pojo.vo.act;

import java.util.Map;

/**
 * 邮件发送元数据
 * @author yuz
 * @date 2018/8/27
 */
public class MailMessageMeta extends StationMessageMeta {

    public MailMessageMeta(Long userSid, String messageId, Map<String, String> content, Map<String, Object> sendAddress) {
        super(userSid, messageId, content);
        this.sendAddress = sendAddress;
    }

    private Map<String, Object> sendAddress;

    public Map<String, Object> getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(Map<String, Object> sendAddress) {
        this.sendAddress = sendAddress;
    }
}
