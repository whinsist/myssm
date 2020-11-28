package cn.com.cloudstar.rightcloud.framework.test.t001study.hutool;

import java.util.HashMap;

import cn.hutool.core.util.ObjectUtil;

/**
 * @author Hong.Wu
 * @date: 11:07 2020/08/26
 */
public class T01ObjectUtil {

    public static void main(String[] args) {
        //ObjectUtil.isNull();
        boolean empty = ObjectUtil.isEmpty(new HashMap<>());
        System.out.println(empty);

        boolean basicType = ObjectUtil.isBasicType(new Integer("12"));
        System.out.println("basicType=" + basicType);

    }


}
