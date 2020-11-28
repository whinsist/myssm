package cn.com.cloudstar.rightcloud.framework.common.util.ssh.res;

import java.util.List;

/**
 * @author Hong.Wu
 * @date: 20:59 2018/11/01
 */
public class JschResult {
    /**
     * 正常状态：exitStatus != null && exitStatus == 0
     */
    private Integer exitStatus;
    private List<String> result;

    public Integer getExitStatus() {
        return exitStatus;
    }

    public void setExitStatus(Integer exitStatus) {
        this.exitStatus = exitStatus;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

    public boolean getExitStatusFlag() {
        return exitStatus != null && exitStatus == 0;
    }

    @Override
    public String toString() {
        return "JschResult{" +
                "exitStatus=" + exitStatus +
                ", result=" + result +
                '}';
    }

    public static void main(String[] args) {
        JschResult jschResult = new JschResult();
        System.out.println(jschResult.getExitStatusFlag());
    }
}
