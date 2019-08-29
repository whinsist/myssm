package cn.com.cloudstar.rightcloud.system.vo.act;

import java.util.Map;

/**
 * 短信发送元数据
 * @author yuz
 * @date 2018/8/28
 */
public class SmsMessageMeta extends StationMessageMeta {

    public SmsMessageMeta(Long userSid, String messageId, Map<String, String> content, Map<String, Object> sendAddress) {
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
