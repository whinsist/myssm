package cn.com.cloudstar.rightcloud.framework.testtreeutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import cn.com.cloudstar.rightcloud.framework.common.exception.RetryException;
import cn.com.cloudstar.rightcloud.framework.common.util.RetryUtil;

/**
 * @author Hong.Wu
 * @date: 19:01 2019/06/09
 */
public class Test8Retry {
    private static Logger logger = LoggerFactory.getLogger(Test8Retry.class);
    public static void main(String[] args) {


            AtomicInteger count = new AtomicInteger();
            int total = 5;
            final String[] instanceIdArr = {null};
            RetryUtil.retry(total, 1L, TimeUnit.SECONDS, false, () -> {
                count.getAndIncrement();
                logger.info("{}循环检测主机[{}:{}]的信息", (count+"/"+total), "test", "127.0.0.1");

                instanceIdArr[0] = getInstanceId();
                if (instanceIdArr[0] == null) {
                    throw new RetryException("主机instanceId未查询到, 继续");
                }
            });

            System.out.println("instanceId="+instanceIdArr[0]);




    }


    public static String getInstanceId() {
        Integer random = new Random().nextInt(10);
        if (random < 5) {
            return null;
        }
        return random.toString();
    }
}
