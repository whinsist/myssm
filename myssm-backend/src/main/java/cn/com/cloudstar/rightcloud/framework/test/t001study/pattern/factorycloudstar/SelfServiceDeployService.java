package cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.factorycloudstar;

import cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.factorycloudstar.bean.ResResult;
import cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.factorycloudstar.bean.SelfServiceModel;

import java.util.function.Consumer;

public interface SelfServiceDeployService {

    ResResult deployCloudService(SelfServiceModel selfServiceModel, Consumer<ResResult> updateStatus);
}
