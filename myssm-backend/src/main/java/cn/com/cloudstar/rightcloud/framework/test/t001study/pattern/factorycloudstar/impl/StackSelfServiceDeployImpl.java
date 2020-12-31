package cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.factorycloudstar.impl;

import cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.factorycloudstar.SelfServiceDeployService;
import cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.factorycloudstar.bean.ResResult;
import cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.factorycloudstar.bean.SelfServiceModel;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;


/**
 * 编排服务部署实现
 *
 * @author ShiWenQiang
 */
@Service("stackSelfServiceDeployService")
public class StackSelfServiceDeployImpl implements SelfServiceDeployService {


    @Override
    public ResResult deployCloudService(SelfServiceModel selfServiceModel, Consumer<ResResult> updateStatus) {
        return null;
    }
}
