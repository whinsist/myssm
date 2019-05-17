package cn.com.cloudstar.rightcloud.framework.common.util;

import java.nio.ByteBuffer;
import java.util.Arrays;

import cn.hutool.core.collection.CollectionUtil;

/**
 * @author Hong.Wu
 * @date: 9:34 2019/05/08
 */
public class ByteLongUtil {
    public static String longToHex(long x) {
        byte[] bytes = longToBytes(x);
        return byte2Hex(bytes);
    }

    public static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }

    /**
     * 将byte转为16进制
     *
     * @param bytes
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    public static long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(bytes);
        buffer.flip();
        return buffer.getLong();
    }


    public static void main(String[] args) throws Exception {
        Long lon = 1125697697974583296L;
        byte[] bytes = longToBytes(lon);

        System.out.println(bytes.length);
        System.out.println(bytesToLong(bytes));

        System.out.println("");
        System.out.println(byte2Hex(bytes));
        //0f9f481794400000

        System.out.println(byte2Hex(bytes).equals(longToHex(lon)));





        // 打印字节
        for (byte bx : bytes) {
            int xxx  = bx;
            System.out.println("字节--"+bx+"  "+  xxx);
        }

        System.out.println(Arrays.toString(bytes));
        byte[] bs = {0x0F, 0x1F, 0x2F, 0x3F, 0x4F, 0x5F, 0x6F};
        System.out.println(Arrays.toString(bs));

        byte[] bs2 = "你好".getBytes("UTF-8");
        System.out.println(bs2.length+"字节："+Arrays.toString(bs2));
    }
}
