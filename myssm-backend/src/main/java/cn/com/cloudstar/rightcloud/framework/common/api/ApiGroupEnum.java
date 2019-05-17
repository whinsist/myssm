package cn.com.cloudstar.rightcloud.framework.common.api;


public enum ApiGroupEnum {

    ALL("全部","all"),
    SELF_SERVICE_GROUP("自服务","self"),
    AUDIT_GROUP("审批","audit"),
    RESOURCE_GROUP("资源","resource"),
    MONITOR_GROUP("监控","monitor"),
    SYSTEM_GROUP("系统","system"),
    OPERATION_GROUP("运营","operation"),
    MAINTENANCE_GROUP("运维","maintenance");

    private String groupName;
    private String path;

    ApiGroupEnum(String groupName, String path) {
        this.groupName = groupName;
        this.path = path;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
