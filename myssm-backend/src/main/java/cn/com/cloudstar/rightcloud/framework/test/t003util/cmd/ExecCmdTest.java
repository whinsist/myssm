package cn.com.cloudstar.rightcloud.framework.test.t003util.cmd;

import cn.com.cloudstar.rightcloud.framework.common.util.SystemUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RuntimeUtil;

import java.io.File;

/**
 * @author Hong.Wu
 * @date: 14:40 2021/02/16
 */
public class ExecCmdTest {
    public static void main(String[] args) {
        String cpuId = SystemUtil.getCpuId();
        System.out.println(cpuId);
        String wmic = RuntimeUtil.execForStr( "wmic cpu get processorid");
        System.out.println(wmic);
        String str = RuntimeUtil.execForStr("ipconfig");
        System.out.println(str);




    }


}
