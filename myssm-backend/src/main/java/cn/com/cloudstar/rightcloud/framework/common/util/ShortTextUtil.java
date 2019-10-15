package cn.com.cloudstar.rightcloud.framework.common.util;

import com.google.common.base.Joiner;

import java.util.Arrays;
import java.util.UUID;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.digest.DigestUtil;

/**
 * 短码生成工具类<br/> 可将输入字符串提取为类似 N7FU-ZABJ-NNEQ-AZFB 的短码
 */
public class ShortTextUtil {

    // 自定义生成MD5加密字符串前的混合KEY
    private static final String KEY = "RightCloud";
    // 要使用生成的字符
    private static final String[] CHARS = new String[]{
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B",
            "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    /**
     * 获取短字符
     *
     * @param uuid the uuid
     * @param splitter 分隔符
     * @param unitCharNum 每部分字符长度
     * @param unitNum 分组数量
     *
     * @return 大写 short char
     */
    public static String getShortChar(String uuid, String splitter, int unitCharNum, int unitNum) {
        String[] arr = shortText(uuid);
        String rst = (Joiner.on("").join(arr)).toUpperCase();
        return Joiner.on(splitter).join(Arrays.asList(rst.split("(?<=\\G.{" + unitCharNum + "})"))
                                              .subList(0, unitNum));
    }

    private static String[] shortText(String string) {

        String hex = DigestUtil.md5Hex(KEY + string);
        int hexLen = hex.length();
        int subHexLen = hexLen / 8;
        String[] shortStr = new String[4];

        for (int i = 0; i < subHexLen; i++) {
            String outChars = "";
            int j = i + 1;
            String subHex = hex.substring(i * 8, j * 8);
            long idx = 0x3FFFFFFF & Long.parseLong(subHex, 16);

            for (int k = 0; k < 6; k++) {
                long index = 0x0000003D & idx;
                outChars += CHARS[(int) index];
                idx = idx >> 5;
            }
            shortStr[i] = outChars;
        }

        return shortStr;
    }

    /**
     * test example
     **/
    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString();
        String cpuid = "0000000000000000"
                + "F2060000FFFBAB0F"
                + "F2060300FFFBAB0F";
        String timestamp = DatePattern.PURE_DATETIME_FORMAT.format(System.currentTimeMillis());
        String shortChar = getShortChar(uuid + cpuid + timestamp, "-", 4, 4);
        System.out.println(shortChar);
        String path = "d:\\temp\\env_config\\setting.dat";
        FileUtil.writeBytes(HexUtil.encodeHexStr(shortChar).getBytes(), path);
        byte[] bytes = FileUtil.readBytes(path);
        String productSn = HexUtil.decodeHexStr(new String(bytes));
        System.out.println(new String(bytes));
        System.out.println(productSn);
    }
}