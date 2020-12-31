package cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.factorycloudstar.facotry;

import cn.com.cloudstar.rightcloud.framework.common.constants.WebConstants;
import cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.factorycloudstar.SelfServiceDeployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


/**
 * 自服务应用部署
 *
 * @author ShiWenQiang
 */
@Component("selfServiceDeployFactory")
@Slf4j
public class SelfServiceDeployFactory {

    @Autowired
    @Qualifier("appSelfServiceDeployService")
    private SelfServiceDeployService appSelfServiceDeployService;

    @Autowired
    @Qualifier("scriptSelfServiceDeployService")
    private SelfServiceDeployService scriptSelfServiceDeployService;

    @Autowired
    @Qualifier("stackSelfServiceDeployService")
    private SelfServiceDeployService stackSelfServiceDeployService;



    /**
     * 部署类应用实现
     *
     * @param deployType 部署类型
     */
    public SelfServiceDeployService getSelfServiceDeploymentService(String deployType) {
        // @Qualifier 注释来指出我们想要使用哪个 bean 来解决问题：


        if (WebConstants.CloudDeploymentType.STACK.equals(deployType)) {
            return stackSelfServiceDeployService;
        } else if (WebConstants.CloudDeploymentType.APP.equals(deployType)) {
            return appSelfServiceDeployService;
        } else if (WebConstants.CloudDeploymentType.SCRIPT.equals(deployType)) {
            return scriptSelfServiceDeployService;
        } else {
            throw new RuntimeException("no support deploy type ! " + deployType);
        }
    }
}
