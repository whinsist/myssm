package cn.com.cloudstar.rightcloud.system.vo.act;

import java.util.List;

/**
 * 审批节点消息配置
 * @author yuz
 * @date 2018/8/17
 */
public class ProcessNodeMessageDto {
    String title;
    String message;
    List<String> receivers;
    List<String> sendTypes;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }

    public List<String> getSendTypes() {
        return sendTypes;
    }

    public void setSendTypes(List<String> sendTypes) {
        this.sendTypes = sendTypes;
    }
}
