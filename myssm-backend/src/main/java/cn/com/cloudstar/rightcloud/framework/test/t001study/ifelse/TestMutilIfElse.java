package cn.com.cloudstar.rightcloud.framework.test.t001study.ifelse;

import java.util.HashMap;
import java.util.function.Function;

import scala.annotation.meta.param;

/**
 * @author Hong.Wu
 * @date: 5:56 2020/06/02
 */
public class TestMutilIfElse {
    public static void main(String[] args) {
        String parms = "11";

        if (parms.equals("1")) {
            doAction1("");
        } else if (parms.equals("2")) {
            doAction2("");
        } else if (parms.equals("3")) {
            doAction3("");
        }


//        Map<?, Function<?>> actionMappings = new HashMap<>();
        // 这里泛型 ? 是为方便演示，实际可替换为你需要的类型

//        // When init
//        actionMappings.put("1", (someParams) -> { doAction1(someParams)});
//        actionMappings.put("2", (someParams) -> { doAction2(someParams)});
//        actionMappings.put("3", (someParams) -> { doAction3(someParams)});
//
//        // 省略 null 判断
//        actionMappings.get(param).apply(someParams);




    }

    private static void doAction1(String s) {
    }
    private static void doAction2(String s) {
    }

    private static void doAction3(String s) {
    }


}
