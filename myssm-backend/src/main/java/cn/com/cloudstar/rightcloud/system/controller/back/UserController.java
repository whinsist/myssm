/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved.
 */

package cn.com.cloudstar.rightcloud.system.controller.back;

import com.google.common.collect.Lists;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import cn.com.cloudstar.rightcloud.framework.common.exception.BizException;
import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.framework.common.pojo.RestResult;
import cn.com.cloudstar.rightcloud.framework.common.pojo.rest.ResultObject;
import cn.com.cloudstar.rightcloud.framework.common.pojo.rest.ResultObjectUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.JsonUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.WebUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.excel.ExcelUtils;
import cn.com.cloudstar.rightcloud.framework.common.util.file.FileDownLoadUtil;
import cn.com.cloudstar.rightcloud.system.pojo.User;
import cn.com.cloudstar.rightcloud.system.pojo.jqgrid.JQGridBean;
import cn.com.cloudstar.rightcloud.system.pojo.jqgrid.JQGridBeanUtil;
import cn.com.cloudstar.rightcloud.system.service.UserService;
import cn.com.cloudstar.rightcloud.system.vo.UserEditVo;
import cn.com.cloudstar.rightcloud.system.vo.excel.UserExcelVo;


@Controller
@RequestMapping("/back/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;

    @PostMapping("/list")
    @ResponseBody
    public JQGridBean list(HttpServletRequest request) {
        Criteria criteria = new Criteria();
        WebUtil.preparePageParams(request, criteria, "user_sid asc");
        List<User> users = this.userService.selectByParams(criteria);
        return JQGridBeanUtil.buildJQGrid(users);
    }


    @PostMapping("/add")
    @ResponseBody
    public ResultObject addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResultObjectUtil.success();
    }



    @PostMapping("/edit_profile")
    @ResponseBody
    public ResultObject editProfile(@RequestBody User user) {
        this.userService.updateUser(user);
        return ResultObjectUtil.success(user);
    }



    @RequestMapping("/deleteOrUpdate")
    @ResponseBody
    public ResultObject editProfile(UserEditVo userEditVo) {
        userService.deleteOrUpdateUser(userEditVo);
        return ResultObjectUtil.success();
    }


    @GetMapping("/download")
    @ResponseBody
    public void downloadUser(HttpServletResponse response, HttpServletRequest request)
            throws UnsupportedEncodingException {
        List<User> users = userService.selectByPermission(null);

        List<UserExcelVo> excelVos = Lists.newArrayList();
        for (User user : users) {
            UserExcelVo userExcelVo = new UserExcelVo();
            BeanUtils.copyProperties(user, userExcelVo);
            excelVos.add(userExcelVo);
        }
        String fileNameByAgent = FileDownLoadUtil.getFileNameByAgent(request.getHeader("USER-AGENT"), "用户列表.xls");

        OutputStream out = null;
        response.setHeader("Content-Disposition", "attachment;filename="+fileNameByAgent);
        response.setContentType("application/vnd.ms-excel");
        try {
            out = response.getOutputStream();
            ExcelUtils excel = new ExcelUtils();
            excel.exportExcel(out, "sheet1", excelVos, UserExcelVo.class);

            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @PostMapping("/import")
    public RestResult excelImport(@Context HttpServletRequest request,@RequestParam MultipartFile files) {
        String fileDir= "e://tempsave/testimport";
        if (!new File(fileDir).exists()) {
            new File(fileDir).mkdir();
        }
        String filePath = fileDir + File.separator + files.getOriginalFilename();
        try {
            FileUtils.copyInputStreamToFile(files.getInputStream(), new File(filePath));
            ExcelUtils excel = new ExcelUtils(filePath);
            List<UserExcelVo> userExcelVos = excel.getSheetValueByIndex(0, UserExcelVo.class);
            System.out.println("导入的excel数据："+ JsonUtil.toJson(userExcelVos));
            //opsNodeService.insertBatchVo(nodes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("上传文件失败:"+e.getMessage());
        } finally {
            FileUtils.deleteQuietly(new File(filePath));
        }
        return new RestResult(RestResult.Status.SUCCESS,  "上传成功", filePath);

    }



}
