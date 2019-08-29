package cn.com.cloudstar.rightcloud.system.vo.act;

/**
 * 消息类型DTO
 * @author jerry
 * @date 2018/8/20
 */
public class MessageTypeDto {
    private String type;
    private String name;

    public MessageTypeDto() {}

    public MessageTypeDto(String type, String name) {
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
