package cn.com.cloudstar.rightcloud.framework.common.util;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * @author Hong.Wu
 * @date: 14:37 2019/11/19
 */
public class SystemUtil {

    /**
     * 获取产品硬件特征码 注意是系统序列号+CPU序列号+当前时间生成的短字符串
     */
    public static String getProductSN() {
        // $uuid+$cupid+时间戳(yyyyMMddHHmmss)
        String sysUUID = SystemUtil.getSystemUUID();
        String cpuId = SystemUtil.getCpuId();
        StringBuilder sb = new StringBuilder();
        sb.append(sysUUID);
        sb.append(cpuId);
        sb.append(StringUtil.dateFormat(new Date(), "yyyyMMddHHmmss"));
        String snShort = ShortTextUtil.getShortChar(sb.toString(), "-", 4, 4);
        return snShort;
    }

    /**
     * 操作系统id
     **/
    public static String getSystemUUID() {
        String command = null;
        if (isLinux()) {
            // linux 系统uuid
            command = "dmidecode -s system-uuid";
        } else {
            // windows 系统序列号
            command = "wmic os get serialnumber";
        }
        return executeCmd(command);
    }


    /**
     * CPU id
     **/
    public static String getCpuId() {
        String command = null;
        if (isLinux()) {
            // linux
            command = "dmidecode -t 4 | grep ID |sort -u | sed 's/.*ID://;s/ //g'";
        } else {
            // windows CPU序列号
            command = "wmic cpu get processorid";
        }
        return executeCmd(command);
    }

    public static String executeCmd(String cmd) {
        if (StringUtil.isNullOrEmpty(cmd)) {
            throw new IllegalArgumentException("输入执行命令不能为空!");
        }
        StringBuilder result = new StringBuilder();
        Process process = null;
        BufferedReader bufrIn = null;
        BufferedReader bufrError = null;
        // 获取命令执行结果, 有两个结果: 正常的输出 和 错误的输出（PS: 子进程的输出就是主进程的输入）
        try {
            process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
            bufrIn = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            bufrError = new BufferedReader(new InputStreamReader(process.getErrorStream(), "UTF-8"));
            // 读取输出
            String line;
            while ((line = bufrIn.readLine()) != null) {
                result.append(line).append('\n');
            }
            while ((line = bufrError.readLine()) != null) {
                result.append(line).append('\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(bufrError);
            IOUtils.closeQuietly(bufrIn);
            try {
                process.destroy();
            } catch (Exception e) {
            }
        }
        return result.toString();
    }

    private static boolean isLinux() {
        String s = System.getProperties().getProperty("file.separator");
        return "/".equalsIgnoreCase(s);
    }
}
