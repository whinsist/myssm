package cn.com.cloudstar.rightcloud.framework.common.util;


import cn.com.cloudstar.rightcloud.framework.common.exception.BizException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;


/**
 * @author Hong.Wu
 * @date: 18:30 2018/08/22
 */
public class AssertUtil {



    public static void notBlank(Object obj, String message) {
        if (obj == null) {
            throw new BizException(message);
        }
        if (obj instanceof String) {
            if (StringUtils.isBlank((String)obj)) {
                throw new BizException(message);
            }
        }
        if (obj instanceof List) {
            if (List.class.cast(obj).size() == 0) {
                throw new BizException(message);
            }
        }
    }

    public static void contains(String object, List<String> conList, String message) {
        boolean isContains = false;
        for (String str : conList) {
            if (str.equals(object)) {
                isContains = true; break;
            }
        }
        if (!isContains) {
            throw new BizException(message);
        }
    }
    



}
