package cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.factorycloudstar;

import cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.factorycloudstar.bean.SelfServiceModel;
import cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.factorycloudstar.facotry.SelfServiceDeployFactory;
import cn.com.cloudstar.rightcloud.system.entity.selfservice.ServiceOrderDetail;
import org.springframework.beans.factory.annotation.Autowired;

public class TestHandleService {

    @Autowired
    private SelfServiceDeployFactory selfServiceDeployFactory;

    public void invokeDeployService(ServiceOrderDetail detail) {

        SelfServiceDeployService deployService = selfServiceDeployFactory.getSelfServiceDeploymentService("");
        SelfServiceModel selfServiceModel = new SelfServiceModel();
        deployService.deployCloudService(selfServiceModel,  resInsResult -> {

        });
    }
}
