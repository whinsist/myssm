package cn.com.cloudstar.rightcloud.system.pojo.vo.act;

import java.util.Map;

/**
 * 审批站内信元数据
 * @author yuz
 * @date 2018/8/24
 */
public class StationMessageMeta {

    public StationMessageMeta() {}

    public StationMessageMeta(Long userSid, String messageId, Map<String, String> content) {
        this.userSid = userSid;
        this.messageId = messageId;
        this.content = content;
    }

    private Long userSid;
    private String messageId;
    private Map<String, String> content;

    public Long getUserSid() {
        return userSid;
    }

    public void setUserSid(Long userSid) {
        this.userSid = userSid;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }
}
