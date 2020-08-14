package cn.com.cloudstar.rightcloud.framework.test.t001study.lambda;

import java.util.function.Consumer;

/**
 * @author Hong.Wu
 * @date: 19:03 2020/07/19
 */
public class LambdaTest8Consumer {

    public static void main(String[] args) {
        deployCloudService(1, resVar -> {
            System.out.println("1111");
        });
    }

    private static Boolean deployCloudService(int param, Consumer<Boolean> updateStatus) {

        Boolean resInsResult = createServerToPaas("test");
        if (updateStatus != null) {
            updateStatus.accept(resInsResult);
        }
        return resInsResult;

    }

    private static Boolean createServerToPaas(String cloudVmInst) {
        return null;
    }


}
