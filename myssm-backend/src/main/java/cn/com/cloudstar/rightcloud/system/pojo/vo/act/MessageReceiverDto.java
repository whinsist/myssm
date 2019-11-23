package cn.com.cloudstar.rightcloud.system.pojo.vo.act;

/**
 * 消息接收人类型
 * @author jerry
 * @date 2018/8/20
 */
public class MessageReceiverDto {
    private String type;
    private String name;

    public MessageReceiverDto() {}

    public MessageReceiverDto(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
