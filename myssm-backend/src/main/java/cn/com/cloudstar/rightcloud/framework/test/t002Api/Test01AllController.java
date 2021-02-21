package cn.com.cloudstar.rightcloud.framework.test.t002Api;

import cn.com.cloudstar.rightcloud.framework.common.exception.BizException;
import cn.com.cloudstar.rightcloud.framework.test.t002Api.hh.ContractOrderRelHeadDTO;
import cn.com.cloudstar.rightcloud.framework.test.t002Api.hh.OrderInfoTemplate;
import cn.com.cloudstar.rightcloud.framework.test.t002Api.hh.XDocReport;
import com.deepoove.poi.XWPFTemplate;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;

import com.lowagie.text.pdf.BaseFont;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
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
import cn.com.cloudstar.rightcloud.system.dao.system.ExamMapper;
import cn.com.cloudstar.rightcloud.system.entity.system.User;
import cn.com.cloudstar.rightcloud.system.entity.system.Exam;
import cn.com.cloudstar.rightcloud.system.service.mq.MqService;
import cn.com.cloudstar.rightcloud.system.service.system.UserCacheService;
import cn.com.cloudstar.rightcloud.system.service.system.UserService;

/**
 * @author Hong.Wu
 * @date: 10:57 2018/09/01
 */
@RequestMapping("/test")
@Controller
public class Test01AllController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private MqService mqService;

    @Autowired
    private UserCacheService userCacheService;

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

//        Criteria criteria = new Criteria();
//        PageHelper.startPage(2, 10);
//        List<User> users = this.userService.selectByParams(criteria);
//        PageInfo<User> pageInfo = new PageInfo<>(users);


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


    @GetMapping("/cacheData")
    @ResponseBody
    public ResultObject cacheData(Long userId) {
        User user = userCacheService.selectUseCache(userId);
        return ResultObjectUtil.success(user);
    }

    @GetMapping("/fileEncoding")
    public void test() throws Exception {
        HttpGet request = new HttpGet(
                "http://192.168.0.104:8086/api/v1/sys_config/config_data?configKey=base.monitor.data.collect.frequency");
        // add request header
        request.addHeader("User-Agent", "Mozilla/5.0");
        request.addHeader("xxx", "");
        CloseableHttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(request);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result);
        System.out.println("1、发现result是乱码, 打印看一下file.encoding=是GBK" + System.getProperty("file.encoding"));
        // -Dfile.encoding就是用来改变jdk处理文件等的默认字符编码
        System.out.println("2、file.encoding=是GBK 说明jvm的linux的默认编码是GBK,");
        System.out.println(
                "3、想一想返回的是字节流  如果指定用utf-8读取的话不是乱码，即使用new InputStreamReader(response.getEntity().getContent(), utf-8)");
        System.out.println("4、设置jvm的默认编码是utf-8, 设置方式 tomcat vm options:-Dfile.encoding=UTF8 但出现了idea控制台中文乱码");
        System.out.println("4、idea - help - Edit clutom Vmoptons在最后添加一行-Dfile.encoding=UTF-8");


    }

    @GetMapping("/pdfdownload")
    @ResponseBody
    public void pdfdownload(HttpServletResponse res) {

//        MbMember member = mbMemberMapper.selectByPrimaryKey(orderCreateUserId);
//
//
//        //根据订单号调用接口获取数据
//        ContractOrderRelHeadDTO headDTO = facadeClient.getPurchaseOrderDetailInfo(reqParam);;
//
//
//        //下载
//        OrderInfoTemplate orderInfoTemplate = new OrderInfoTemplate(headDTO).invoke();
//        XWPFTemplate template = orderInfoTemplate.getTemplate();
//        if (template == null) {
//            throw new BizException("下载合同失败！");
//        }
//        try {
//            FileOutputStream fos = new FileOutputStream(orderInfoTemplate.getFileName() + ".docx");
//            template.write(fos);
//            template.close();
//            fos.close();
//            fos.flush();
//
//            XDocReport.wordConverterToPdf(orderInfoTemplate.getFileName() + ".docx",
//                    orderInfoTemplate.getFileName() + ".pdf", "/lib/simhei.ttf",
//                    BaseFont.IDENTITY_H);
//
//            File outFile = new File(orderInfoTemplate.getFileName() + ".pdf");
//            res.setCharacterEncoding("utf-8");
//            res.setContentType("application/x-msdownload");
//            res.setHeader("Content-Disposition", "attachment;filename=" + orderInfoTemplate.getFileName() + ".pdf");
//            OutputStream os = res.getOutputStream();
//            FileInputStream in = new FileInputStream(outFile);
//
//            byte buffer[] = new byte[2048];
//            int len = 0;
//            // 循环将输入流中的内容读取到缓冲区中
//            while ((len = in.read(buffer)) > 0) {
//                // 输出缓冲区内容到浏览器，实现文件下载
//                os.write(buffer, 0, len);
//            }
//            in.close();
//            os.close();
//            os.flush();
//            if (outFile.exists()) {
//                outFile.delete();
//            }
//            File wordFile = new File(orderInfoTemplate.getFileName() + ".docx");
//            if (wordFile.exists()) {
//                wordFile.delete();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }

}
