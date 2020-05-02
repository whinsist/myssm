package cn.com.cloudstar.rightcloud.framework.test.t001study.rpc.server;

import cn.com.cloudstar.rightcloud.framework.test.t001study.rpc.common.HelloService;
import cn.com.cloudstar.rightcloud.framework.test.t001study.rpc.common.RpcFramework;

/**
 * https://blog.csdn.net/justloveyou_/article/details/79441306
 */
public class RpcProvider {
    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        // RPC框架将服务暴露出来，供客户端消费
        RpcFramework.export(service, 1234);
    }
}