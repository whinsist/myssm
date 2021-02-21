package cn.com.cloudstar.rightcloud.framework.test.t003util.excel;

import com.alibaba.fastjson.JSON;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.com.cloudstar.rightcloud.framework.common.util.ExcelManagerUtil;


public class TestExcelZbj {

    public static void main(String[] args) {

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
        ExcelManagerUtil.exportExcel(fileName, titles, list, 1024, null, "d:/temp/xssfworkbook.xlsx");

        List<String[]> strings = ExcelManagerUtil.importExcel(new File("d:/temp/xssfworkbook.xlsx"), 1);
        System.out.println(JSON.toJSONString(strings));
    }



}
