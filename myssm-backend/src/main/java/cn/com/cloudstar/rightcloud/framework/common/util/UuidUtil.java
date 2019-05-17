/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */
package cn.com.cloudstar.rightcloud.framework.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * UUID共通类
 *
 * @author 刘洋
 */
public final class UuidUtil {

    /**
     * 可打印字符
     */
    private static String[] CHARS = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    /**
     * 取得8位UUID
     * <p>
     * 本算法利用62个可打印字符，通过随机生成32位UUID，由于UUID都为十六进制， 所以将UUID分成8组，每4个为一组，
     * 然后通过模62操作，结果作为索引取出字符
     *
     * @return 8位UUID
     */
    public static String getShortUuid() {
        return getShortUuid(null);
    }

    /**
     * 取得8位UUID
     * <p>
     * 本算法利用62个可打印字符，通过随机生成32位UUID，由于UUID都为十六进制， 所以将UUID分成8组，每4个为一组， 然后通过模62操作，结果作为索引取出字符
     *
     * @param prefix 前缀字符串
     * @return 8位UUID
     */
    public static String getShortUuid(String prefix) {

        StringBuilder shortBuffer = new StringBuilder();
        if (StringUtils.isNotEmpty(prefix)) {
            shortBuffer.append(prefix);
        }
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(CHARS[x % 0x3E]);
        }
        return shortBuffer.toString().toLowerCase();
    }

    /**
     * 取得32位UUID
     *
     * @return 32位UUID
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}