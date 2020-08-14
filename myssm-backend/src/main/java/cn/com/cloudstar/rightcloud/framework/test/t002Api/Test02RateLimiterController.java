package cn.com.cloudstar.rightcloud.framework.test.t002Api;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import cn.hutool.core.util.RandomUtil;

import cn.com.cloudstar.rightcloud.framework.common.annotation.RateLimit;
import cn.com.cloudstar.rightcloud.framework.test.t001study.rateLimite.RateLimiterFactory;
import cn.com.cloudstar.rightcloud.framework.test.t001study.rateLimite.RateLimiterTest.FooItem;

@RequestMapping("/test")
@Controller
public class Test02RateLimiterController {


    @RequestMapping("/ratelimiter")
    @ResponseBody
    public String ratelimiter() {
        if (!RateLimiterFactory.tryAcquire("ApiInfo.TEST")) {
            //当前请求数过高
            return "The current number of requests is too high!";
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ratelimiter ok";
    }


    @RequestMapping("/ratelimiter_anno")
    @ResponseBody
    @RateLimit(limitNum = 1)
    public String ratelimiterUseAnnotation(int page) {
        try {
            Thread.sleep(RandomUtil.randomInt(500, 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<FooItem> list = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(new FooItem("name:" + page + ":" + i));
        }
        return JSON.toJSONString(list);
    }


}