package cn.com.cloudstar.rightcloud.framework.test.t003util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.HexUtil;

import cn.com.cloudstar.rightcloud.framework.common.pojo.LicenseVo;
import cn.com.cloudstar.rightcloud.framework.common.util.BeanUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.SystemUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.TripleDES;

/**
 * @author Hong.Wu
 * @date: 13:54 2019/10/11
 */
public class Test15License {


    public static void main(String[] args) {
        String productSN = SystemUtil.getProductSN();
        String productSnHexStr = HexUtil.encodeHexStr(productSN);
        FileUtil.writeBytes(productSnHexStr.getBytes(), "e://sn.txt");
        System.out.println("写入特征码：" + productSN);

        String reeadProductSn = HexUtil.decodeHexStr(new String(FileUtil.readBytes("e://sn.txt")));
        System.out.println("获取特征码：" + reeadProductSn);

//        LicenseVo licenseVo = analyzeLincenseVo("csrc-poc-lsdx-2019073001MpuPVnLqSW5eU+tinebBG6fuEgVbmfmtJXia+/Pkm1R3xRWtnI5UOaVSF8pGmxFrU0HeRx+mkkxZRcOHAPWQR6iUBi7ebil2AOZXvbJ5kUAr20Hlh0as5lOctqQaNugh3HY4xC3GhmlUiiT9sXJke1lwFBURabDOqQMdiPicGsF0Z03DAQr9fsUWs3IgKcKKhWl3ETXgjyZFzNrXbPI+s0PO6x1PbFlpo6m9yhgwbflRE32nxax1OmWHtnz/vyxyJh8Dv3PB6xIQ+hg5YUXs2xVv3zs9lbdkmfrKv75psYWDWSgT4cUsKDkyR0r+hs0+hI+QC5RfJjzgeefd07tiW2YCTVCMFZzudd592JRY4PU/c2319H+Bjv5/I9zj/bEkYjEg630rdBliENJo8=");
//        System.out.println("licenseVo="+ JSON.toJSONString(licenseVo));

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

            System.out.println("解密结果：" + decryptResult);

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


}
