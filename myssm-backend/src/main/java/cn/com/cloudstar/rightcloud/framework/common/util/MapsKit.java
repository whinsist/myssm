/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * \n
 * The type Maps.\n
 * \n
 * Created on 2016/9/26 \n
 *
 * @author Chaohong.Mao
 */
public class MapsKit {
    public static <K, V> Map<K, V> of(K key, V val) {
        return new HashMap<K, V>() {{
            put(key, val);
        }};
    }

    public static <K, V> Map<K, V> of(K key1, V val1, K key2, V val2) {
        return new HashMap<K, V>() {{
            put(key1, val1);
            put(key2, val2);
        }};
    }

    public static <K, V> Map<K, V> of(K key1, V val1, K key2, V val2, K key3, V val3) {
        return new HashMap<K, V>() {{
            put(key1, val1);
            put(key2, val2);
            put(key3, val3);
        }};
    }

    public static <K, V> Map<K, V> of(K key1, V val1, K key2, V val2, K key3, V val3, K key4, V val4) {
        return new HashMap<K, V>() {{
            put(key1, val1);
            put(key2, val2);
            put(key3, val3);
            put(key4, val4);
        }};
    }
}
