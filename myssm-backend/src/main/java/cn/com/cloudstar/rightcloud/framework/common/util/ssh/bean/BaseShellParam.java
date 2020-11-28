package cn.com.cloudstar.rightcloud.framework.common.util.ssh.bean;

/**
 * @author yangsen
 */
public class BaseShellParam {

    private String hostIp;
    private int hostPort;
    private String remoteUser;
    private String remotePwd;


    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }


    public String getRemoteUser() {
        return remoteUser;
    }

    public void setRemoteUser(String remoteUser) {
        this.remoteUser = remoteUser;
    }

    public String getRemotePwd() {
        return remotePwd;
    }

    public void setRemotePwd(String remotePwd) {
        this.remotePwd = remotePwd;
    }

    public int getHostPort() {
        return hostPort;
    }

    public void setHostPort(int hostPort) {
        this.hostPort = hostPort;
    }
}
