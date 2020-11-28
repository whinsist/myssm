package cn.com.cloudstar.rightcloud.framework.common.util.ssh.bean;

public class AllocateIp4LparParam extends BaseShellParam {


    private String lparName;
    private String ip;

    public String getLparName() {
        return lparName;
    }

    public void setLparName(String lparName) {
        this.lparName = lparName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
