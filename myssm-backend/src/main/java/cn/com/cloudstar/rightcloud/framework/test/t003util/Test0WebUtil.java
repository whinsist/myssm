package cn.com.cloudstar.rightcloud.framework.test.t003util;

import cn.com.cloudstar.rightcloud.framework.common.util.WebUtil;

/**
 * @author Hong.Wu
 * @date: 18:57 2019/05/17
 */
public class Test0WebUtil {
    public static void main(String[] args) {
        String jiami = WebUtil.encryptBase64("你好");
        System.out.println("base64解密："+WebUtil.decryptBase64(jiami));
//        ReflectionUtils.findMethod()
//        ReflectionUtils.invokeMethod()


    }


}
