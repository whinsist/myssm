package cn.com.cloudstar.rightcloud.framework.test.t003util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import cn.com.cloudstar.rightcloud.framework.common.exception.RetryException;
import cn.com.cloudstar.rightcloud.framework.common.util.RetryUtil;

/**
 * @author Hong.Wu
 * @date: 19:01 2019/06/09
 */
public class Test8Retry {
    private static Logger logger = LoggerFactory.getLogger(Test8Retry.class);

    public static void main(String[] args) {


        int total = 5;
        AtomicInteger count = new AtomicInteger();
        AtomicReference<String> reference = new AtomicReference<>();
        RetryUtil.retry(total, 1L, TimeUnit.SECONDS, false, () -> {
            count.getAndIncrement();
            String info = String.format("%s循环检测主机[%s]的信息", (count + "/" + total), "127.0.0.1");
            System.out.println(info);
            String instanceId = getInstanceId();
            if (instanceId == null) {
                throw new RuntimeException("累计" + count + "次 主机instanceId未查询到");
            } else {
                reference.set(instanceId);
            }
        });

        // 如果throwIfFail=true total满之后不走下面的语句
        // 一般做法是throwIfFail=false, 然后在下面取得值然后判断之是否为空
        System.out.println("instanceId=" + reference.get());


    }


    public static String getInstanceId() {
        Integer random = new Random().nextInt(100);
        if (random < 90) {
            return null;
        }
        return random.toString();
    }
}
