package cn.com.cloudstar.rightcloud.framework.test.t003util.excel;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import cn.com.cloudstar.rightcloud.framework.common.util.excel.ExcelUtils;
import cn.com.cloudstar.rightcloud.framework.test.t003util.excel.ExcelManagerUtil;
import cn.com.cloudstar.rightcloud.framework.test.t003util.excel.bean.Fruit;
import cn.com.cloudstar.rightcloud.system.pojo.vo.excel.UserExcelVo;

public class Main {

    public static void main(String[] args) throws Exception {

        //useExcelManagerUtil();
        useTemplate();
        //useExcelUtils();
    }


    private static void useExcelUtils() throws Exception {
        List<UserExcelVo> excelVos = Lists.newArrayList();
        for (int i = 0; i < 20; i++) {
            UserExcelVo userExcelVo = new UserExcelVo();
            userExcelVo.setAccount("xxx" + i);
            excelVos.add(userExcelVo);
        }
        ExcelUtils excel = new ExcelUtils();
        OutputStream out = new FileOutputStream("e://temp//xx.xlsx");
        excel.exportExcel(out, "sheet1", excelVos, UserExcelVo.class);
        out.close();
    }

    private static void useTemplate() {
        // 使用模版
        List<Fruit> list = new ArrayList<>();
        list.add(new Fruit("苹果", 2.01f));
        list.add(new Fruit("桔子", 2.05f));
        String templateFileName = "template/template.xlsx";
        String resultFileName = "template/result/fruit.xlsx";
        ExcelManagerUtil.createExcelByTemplate(templateFileName, list, resultFileName);
    }

    private static void useExcelManagerUtil() {
        //导出EXCEL文件名
        String fileName = "用户登录统计列表.xls";
        //导出的EXCEL的标题
        String title = "ip,信息,内容是否是地方所发生的发";
        String[] titles = title.split(",");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            StringBuffer sb = new StringBuffer();
            sb.append("code" + i);
            sb.append("¿");
            sb.append("code" + i);
            sb.append("¿");
            sb.append("code" + i);
            list.add(sb.toString());
        }
        ExcelManagerUtil.exportExcel(fileName, titles, list, 1024, null);

        List<String[]> strings = ExcelManagerUtil.importExcel(new File("e:/temp/default.xlsx"), 1);
        System.out.println(JSON.toJSONString(strings));
    }
}
