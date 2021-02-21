package cn.com.cloudstar.rightcloud.framework.test.t003util.excel;

import com.google.common.collect.Lists;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cn.com.cloudstar.rightcloud.framework.common.util.ExcelManagerUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.excel.ExcelUtils;
import cn.com.cloudstar.rightcloud.framework.test.t003util.excel.bean.Fruit;
import cn.com.cloudstar.rightcloud.system.pojo.vo.excel.UserExcelVo;

import static java.lang.System.currentTimeMillis;

/**
 * @author Hong.Wu
 * @date: 15:26 2021/01/15
 */
public class TestExcelRightcloudExcel {

    public static void main(String[] args) throws Exception {

        exportExcel();
        //useXLSTransformer();

//        String tempFilePath = "d://temp//xx.xlsx";
//        ExcelUtils excel = new ExcelUtils(tempFilePath);
//        List<UserExcelVo> list = excel.getSheetValueByIndex(0, UserExcelVo.class);
//        System.out.println(list);
    }

    private static void exportExcel() throws Exception {
        List<UserExcelVo> excelVos = Lists.newArrayList();
        for (int i = 0; i < 101; i++) {
            UserExcelVo userExcelVo = new UserExcelVo();
            userExcelVo.setStuName("测试" + i);
            BigDecimal bigDecimal = new BigDecimal("500238198001133230").add(new BigDecimal(i));
            userExcelVo.setIdNumber(bigDecimal.toString());
            userExcelVo.setGradeName("2018级");
            userExcelVo.setClassName("1班");
            userExcelVo.setEnrollDate("2000-12-12");
            userExcelVo.setSchoolLength(4);
            userExcelVo.setMajorName("计算机");
            userExcelVo.setStudyWay("走读");
            userExcelVo.setBankCardNo("yhk0123");
            userExcelVo.setPayStandards("111");
            userExcelVo.setPhone("15223235656");
            userExcelVo.setEducationStatus("在校");
            excelVos.add(userExcelVo);
        }
        ExcelUtils excel = new ExcelUtils();
        OutputStream out = new FileOutputStream("d://temp//xx.xlsx");
        excel.exportExcel(out, "sheet1", excelVos, UserExcelVo.class);
        out.close();
    }

    private static void useXLSTransformer() {
        // 使用模版
        List<Fruit> list = new ArrayList<>();
        list.add(new Fruit("苹果", 2.01f));
        list.add(new Fruit("桔子", 2.05f));
        String templateFileName = "template/template.xlsx";
        String resultFileName = "template/result/fruit.xlsx";
        ExcelManagerUtil.createExcelByTemplate(templateFileName, list, resultFileName);
    }


}
