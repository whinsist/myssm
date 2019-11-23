package cn.com.cloudstar.rightcloud.system.activiti.util;

/**
 * DESC:
 *
 * @author jzhongchen
 * @date 2019/07/15 10:13
 */
public interface OrderType {

    // 申请
    String APPLY = "apply";

    // 执行脚本
    String EXEC_SCRIPT = "execScript";

    // 升配
    String UPGRADE = "upgrade";

    // 降配
    String DEGRADE = "degrade";

    // 变更配置
    String CHANGEGRADE = "changegrade";

    // 续订
    String RENEW = "renew";

    // 退订
    String RELEASE = "release";

    String EXTEND = "extend";

    String MODIFY = "modify";

    String DELETE = "delete";
}
