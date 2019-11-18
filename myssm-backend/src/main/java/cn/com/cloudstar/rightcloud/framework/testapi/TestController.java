package cn.com.cloudstar.rightcloud.framework.testapi;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.cloudstar.rightcloud.framework.common.constants.WebConstants.MsgCd;
import cn.com.cloudstar.rightcloud.framework.common.pojo.BaseGridReturn;
import cn.com.cloudstar.rightcloud.framework.common.pojo.Criteria;
import cn.com.cloudstar.rightcloud.framework.common.pojo.RestResult;
import cn.com.cloudstar.rightcloud.framework.common.pojo.RestResult.Status;
import cn.com.cloudstar.rightcloud.framework.common.pojo.rest.ResultObject;
import cn.com.cloudstar.rightcloud.framework.common.pojo.rest.ResultObjectUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.DateUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.JsonUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.ValidateCode;
import cn.com.cloudstar.rightcloud.framework.common.util.WebUtil;
import cn.com.cloudstar.rightcloud.framework.interceptor.helper.UserThreadLocal;
import cn.com.cloudstar.rightcloud.system.dao.ExamMapper;
import cn.com.cloudstar.rightcloud.system.pojo.Exam;
import cn.com.cloudstar.rightcloud.system.pojo.User;
import cn.com.cloudstar.rightcloud.system.service.MqService;
import cn.com.cloudstar.rightcloud.system.service.UserService;

/**
 * @author Hong.Wu
 * @date: 10:57 2018/09/01
 */
@RequestMapping("/test")
@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private MqService mqService;

    @RequestMapping("/captcha")
    public String captcha(ModelMap model) {
        Map<String, Object> map = new HashMap<>();
        map.put("info", "2233");
        map.put("time", DateUtil.dateFormat(new Date()));
        model.addAttribute("json", JsonUtil.toJson(map));

        ValidateCode vCode = new ValidateCode(100, 30, 4, 10);
        String captcha = vCode.getCode();
        String base64Str = "data:image/jpg;base64," + Base64.encodeBase64String(vCode.getBuffImgByte());
        model.addAttribute("captcha", captcha);
        model.addAttribute("base64Str", base64Str);
        return "/test/index";
    }

    @RequestMapping("/sendEmail")
    @ResponseBody
    public ResultObject sendEmail() {
        userService.sendEmail();
        return ResultObjectUtil.success();
    }

    @GetMapping("/page")
    @ResponseBody
    public RestResult test_page(HttpServletRequest request) {
        Criteria criteria = new Criteria();
        WebUtil.preparePageParams(request, criteria, "user_sid asc");
        //  注：返回结果list，已经是Page对象，Page对象是一个ArrayList
        // 原理：使用ThreadLocal来传递和保存Page对象，每次查询，都需要单独设置PageHelper.startPage()方法。
        // 总记录数count放到Page对象中 是在 dialect.afterCount(count, parameter, rowBounds)方法中
        List<User> users = this.userService.selectByParams(criteria);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        BaseGridReturn baseGridReturn = new BaseGridReturn(pageInfo);



        User user = new User();
        List<User> list2 = this.userService.selectByPageNumSize(user, 1, 2);
        List<User> list3 = this.userService.selectByPageNumSize(user, null, 2);

        User threadLocalUser = UserThreadLocal.get();
        System.out.println("从本地线程中获取当前用："+threadLocalUser.getRealName());


        return new RestResult(baseGridReturn);
    }

    @GetMapping("/interceptor")
    @ResponseBody
    public RestResult interceptor() {
        // 查询所有
//        Criteria criteria = new Criteria();
//        List<Exam> exams = examMapper.selectByParams(criteria);
//        return new RestResult(exams);

        // 分页查询
        Criteria criteria = new Criteria();
        criteria.setPageNum(1);
        criteria.setPageSize(2);
        List<Exam> exams = examMapper.selectByParams(criteria);
        PageInfo pageInfo = new PageInfo<>(exams);
        Map<String, Object> map = Maps.newLinkedHashMap();
        map.put("总条数", pageInfo.getTotal());
        map.put("总页数", pageInfo.getPages());
        map.put("结果集", pageInfo.getList());
        return new RestResult(map);
    }


    @GetMapping("/i18n")
    @ResponseBody
    public RestResult i18n(HttpServletResponse response, HttpServletRequest request) {
        String message = WebUtil.getMessage(MsgCd.INFO_INSERT_SUCCESS);
        String messageUs = WebUtil.getMessage(MsgCd.INFO_INSERT_SUCCESS, null, Locale.US);
        String messageCustom = WebUtil.getMessage(MsgCd.INFO_INSERT_SUCCESS, null, new Locale("web", "BASE64"));
        String messageZh = WebUtil.getMessage("test.message", new String[]{"中文"});
        String messageUs2 = WebUtil.getMessage("test.message", new String[]{"美国"}, Locale.US);
        return new RestResult(Status.SUCCESS, messageUs);
    }


    @GetMapping("/mq")
    @ResponseBody
    public ResultObject sendMQMessage(HttpServletResponse response, HttpServletRequest request) {
        mqService.sendScheduleSyncEnvMessage();
        return ResultObjectUtil.success();
    }


}
