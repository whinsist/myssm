package cn.com.cloudstar.rightcloud.system.controller.back;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.ApiOperation;

import cn.com.cloudstar.rightcloud.framework.common.exception.BizException;
import cn.com.cloudstar.rightcloud.framework.common.pojo.AuthUser;
import cn.com.cloudstar.rightcloud.framework.common.pojo.RestResult;
import cn.com.cloudstar.rightcloud.framework.common.util.RequestContextUtil;
import cn.com.cloudstar.rightcloud.system.activiti.util.StaticDir;
import cn.com.cloudstar.rightcloud.system.service.process.TestProcessService;
import cn.com.cloudstar.rightcloud.system.vo.act.Process;

/**
 * @author Hong.Wu
 * @date: 15:36 2019/07/22
 */
@RestController
@RequestMapping("/process")
public class ProcessMgtCtrl {

    @Autowired
    private TestProcessService testProcessService;

    @ApiOperation("添加流程定义")
    @PostMapping("/mgt/defines")
    public RestResult saveProcess(@RequestBody Process process, HttpServletRequest request) {

        if (StringUtils.isBlank(process.getProcessName())) {
            throw new BizException("请输入流程名称");
        }

        if (StringUtils.isBlank(process.getBusinessCode())) {
            throw new BizException("请选择业务类型");
        }

        if (!StaticDir.BUSINESS_MAP.containsKey(process.getBusinessCode())) {
            throw new BizException("非法业务类型");
        }

        AuthUser authUser = RequestContextUtil.getAuthUserInfo(request);
        testProcessService.createProcessDefine(process, authUser);

        return new RestResult();
    }
}
