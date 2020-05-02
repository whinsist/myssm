package cn.com.cloudstar.rightcloud.framework.test.t001study.rpc.server;

import cn.com.cloudstar.rightcloud.framework.test.t001study.rpc.common.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "Hello " + name;
    }

    @Override
    public String hi(String msg) {
        return "Hi, " + msg;
    }
}