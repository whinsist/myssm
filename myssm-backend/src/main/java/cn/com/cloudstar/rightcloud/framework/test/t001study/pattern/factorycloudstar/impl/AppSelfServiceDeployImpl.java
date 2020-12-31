package cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.factorycloudstar.impl;

import cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.factorycloudstar.SelfServiceDeployService;
import cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.factorycloudstar.bean.ResResult;
import cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.factorycloudstar.bean.SelfServiceModel;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

/**
 * 应用部署服务实现
 */
@Service("appSelfServiceDeployService")
public class AppSelfServiceDeployImpl implements SelfServiceDeployService {


    @Override
    public ResResult deployCloudService(SelfServiceModel selfServiceModel, Consumer<ResResult> updateStatus) {
        return null;
    }
}
