package cn.com.cloudstar.rightcloud.system.controller.common;

import cn.com.cloudstar.rightcloud.framework.common.util.excel.ExcelUtils;
import cn.com.cloudstar.rightcloud.system.pojo.UserExcel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hong.Wu
 * @date: 1:50 2018/09/01
 */
@RestController
@RequestMapping("/export")
public class ExportController {

    @GetMapping("/excel")
    @ResponseBody
    public void exportActionLog(@Context HttpServletRequest request, HttpServletResponse response) throws Exception {
        //OutputStream out = new FileOutputStream("e:/temp/1.xls");
        // 设置响应
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=叫什么文件夹名呢");
        OutputStream out = response.getOutputStream();


        List<UserExcel> list = new ArrayList<>();
        ExcelUtils excel = new ExcelUtils();
        UserExcel userExcel = new UserExcel();
        userExcel.setAccount("111");
        userExcel.setRealName("222");
        userExcel.setEmail("333");
        userExcel.setMobile("444");
        list.add(userExcel);
        excel.exportExcel(out, "sheet_1", list, UserExcel.class);
        out.flush();
        out.close();

//        FileUtil.fileDownLoad(response, uploadConfig.getUploadBasePath() +"/"+ relativeUrl, fileName);
    }
}
