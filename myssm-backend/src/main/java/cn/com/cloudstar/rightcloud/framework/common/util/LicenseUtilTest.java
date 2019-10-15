package cn.com.cloudstar.rightcloud.framework.common.util;

import com.alibaba.fastjson.JSON;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.HexUtil;

import cn.com.cloudstar.rightcloud.framework.common.pojo.LicenseVo;

/**
 * @author Hong.Wu
 * @date: 13:54 2019/10/11
 */
public class LicenseUtilTest {

    private static final String SQL_QUERY_LICENSE = "select * from license";
    private static final String SQL_QUERY_LICENSE_COUNT = "select count(*) as num from license";
    private static final String SQL_UPDATE_LICENSE = "update license set LICENSE_SERIALNO = ?";
    private static final String SQL_INSERT_LICENSE = "insert into license values (?)";
    private static final boolean CHECK_SN = true;
    private static LicenseVo LICENSEVO = null;
    private static String snFilePath = "/env_config/setting.dat";

    public static void main(String[] args) {
        String productSN = getProductSN();

//        FileUtil.writeBytes(HexUtil.encodeHexStr(productSN).getBytes(), "e://sn.txt");
//        String productSn = HexUtil.decodeHexStr(new String(FileUtil.readBytes("e://sn.txt")));
//        System.out.println(productSn);
//        System.out.println("readProductSNFile=="+readProductSNFile());

        LicenseVo licenseVo = analyzeLincenseVo("csrc-poc-lsdx-2019073001MpuPVnLqSW5eU+tinebBG6fuEgVbmfmtJXia+/Pkm1R3xRWtnI5UOaVSF8pGmxFrU0HeRx+mkkxZRcOHAPWQR6iUBi7ebil2AOZXvbJ5kUAr20Hlh0as5lOctqQaNugh3HY4xC3GhmlUiiT9sXJke1lwFBURabDOqQMdiPicGsF0Z03DAQr9fsUWs3IgKcKKhWl3ETXgjyZFzNrXbPI+s0PO6x1PbFlpo6m9yhgwbflRE32nxax1OmWHtnz/vyxyJh8Dv3PB6xIQ+hg5YUXs2xVv3zs9lbdkmfrKv75psYWDWSgT4cUsKDkyR0r+hs0+hI+QC5RfJjzgeefd07tiW2YCTVCMFZzudd592JRY4PU/c2319H+Bjv5/I9zj/bEkYjEg630rdBliENJo8=");
        System.out.println("licenseVo="+ JSON.toJSONString(licenseVo));


    }

    /**
     * 解析许可证信息
     **/
    public static LicenseVo analyzeLincenseVo(String licenseStr) {
        try {
            if (StringUtils.isBlank(licenseStr)) {
                return null;
            }
            // 24位加密key + 6位随机扰乱码 + 生成的license
            String licenseKey = licenseStr.substring(0, 24);
            String licenseReal = licenseStr.substring(30);

            TripleDES.initKey(licenseKey);
            String decryptResult = new TripleDES().decrypt(licenseReal);

            System.out.println("解密结果："+decryptResult);

            Map<String, Object> map = new HashMap<>();
            String[] splitArr = decryptResult.split("&");
            for (String str : splitArr) {
                String[] kvArr = str.split("=");
                map.put(kvArr[0], kvArr.length >= 2 ? kvArr[1] : null);
            }
            LicenseVo resultVo = new LicenseVo();
            BeanUtil.transMap2Bean(map, resultVo);
            return resultVo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取产品特征码文件中的产品序列号
     **/
    public static String readProductSNFile() {
        byte[] bytes = FileUtil.readBytes(snFilePath);
        return HexUtil.decodeHexStr(new String(bytes));
    }

    /**
     * 获取产品硬件特征码
     */
    public static String getProductSN() {
        // $uuid+$cupid+时间戳(yyyyMMddHHmmss)
        String sysUUID = getSystemUUID();
        String cpuId = getCpuId();
        StringBuilder sb = new StringBuilder();
        sb.append(sysUUID);
        sb.append(cpuId);
        sb.append(StringUtil.dateFormat(new Date(), "yyyyMMddHHmmss"));
        String sn = ShortTextUtil.getShortChar(sb.toString(), "-", 4, 4);
        System.out.println("产品特征码原文==========="+sb.toString());
        System.out.println("短码========="+sn);
        return sn;
    }
    private static boolean isLinux() {
        String s = System.getProperties().getProperty("file.separator");
        return "/".equalsIgnoreCase(s);
    }

    /**
     * 操作系统id
     **/
    private static String getSystemUUID() {
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
    private static String getCpuId() {
        String command = null;
        if (isLinux()) {
            // linux
            command = "dmidecode -t 4 | grep ID |sort -u | sed 's/.*ID://;s/ //g'";
        } else {
            //windows
            command = "wmic cpu get processorid";
        }
        return executeCmd(command);
    }

    private static String executeCmd(String cmd) {
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


}
