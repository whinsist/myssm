/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved.
 */

package cn.com.cloudstar.rightcloud.system.controller.back.selfservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.com.cloudstar.rightcloud.framework.common.pojo.AuthUser;
import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.framework.common.pojo.rest.ResultObject;
import cn.com.cloudstar.rightcloud.framework.common.pojo.rest.ResultObjectUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.RequestContextUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.WebUtil;
import cn.com.cloudstar.rightcloud.system.controller.back.selfservice.bean.request.CreateProcessNodeRequest;
import cn.com.cloudstar.rightcloud.system.controller.back.selfservice.bean.request.CreateProcessRequest;
import cn.com.cloudstar.rightcloud.system.controller.back.selfservice.bean.request.UpdateProcessRequest;
import cn.com.cloudstar.rightcloud.system.pojo.jqgrid.JQGridBean;
import cn.com.cloudstar.rightcloud.system.pojo.jqgrid.JQGridBeanUtil;
import cn.com.cloudstar.rightcloud.system.service.selfservice.ProcessService;
import cn.com.cloudstar.rightcloud.system.entity.selfservice.Process;

@RestController
@RequestMapping("/back/process")
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @PostMapping("/list")
    public JQGridBean list(HttpServletRequest request) {
        Criteria criteria = new Criteria();
        WebUtil.preparePageParams(request, criteria, "id desc");
        List<Process> processes = this.processService.selectByParams(criteria);
        return JQGridBeanUtil.buildJQGrid(processes);
    }

    @PostMapping("/add")
    public ResultObject addProcess(@RequestBody CreateProcessRequest createProcessRequest) {
        AuthUser authUser = RequestContextUtil.getAuthUserInfo(WebUtil.getRequest());
        processService.createProcessDefine(createProcessRequest, authUser);
        return ResultObjectUtil.success();
    }

    @RequestMapping("/deleteOrUpdate")
    @ResponseBody
    public ResultObject deleteOrUpdate(UpdateProcessRequest request) {
        processService.deleteOrUpdateProcess(request);
        return ResultObjectUtil.success();
    }

    @PostMapping("/deploy/{processId}")
    public ResultObject deployProcessNode(@PathVariable Long processId) {
        processService.deployProcess(processId);
        return ResultObjectUtil.success();
    }


    @GetMapping("/detail/{processId}")
    public ResultObject addProcess(@PathVariable Long processId) {
        Map<String, Object> map = processService.getProcessDetail(processId);
        return ResultObjectUtil.success(map);
    }

    /////////////////////////

    @PostMapping("/node/add")
    public ResultObject addProcessNode(@RequestBody CreateProcessNodeRequest createRequest) {
        Long nodeId = processService.addProcessNode(createRequest);
        return ResultObjectUtil.success(nodeId);
    }

}
