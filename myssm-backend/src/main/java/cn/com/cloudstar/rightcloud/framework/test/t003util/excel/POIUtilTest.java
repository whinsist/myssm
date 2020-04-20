package cn.com.cloudstar.rightcloud.framework.test.t003util.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.com.cloudstar.rightcloud.framework.test.t003util.excel.bean.ExBean;


/**
 * @功能：手工构建一个简单格式的Excel
 */
public class POIUtilTest {

    public static void main(String[] args) throws Exception {
        export();
    }

    public static void export() throws Exception {
        // 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();
        // poi-3.17.jar
        //org.apache.poi.hssf.usermodel.HSSFWorkbook wbx = new HSSFWorkbook();
        // poi-ooxml-3.17.jar
        //org.apache.poi.xssf.usermodel.XSSFWorkbook wby = new XSSFWorkbook();

        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("数据表一");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中  
//        HSSFCellStyle style = wb.createCellStyle();
        // 创建一个居中格式
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFCellStyle styleHeader = wb.createCellStyle();
        styleHeader.setAlignment(HorizontalAlignment.CENTER);
        HSSFFont font = wb.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        //设置颜色
        styleHeader.setAlignment(HorizontalAlignment.CENTER);
        //边框填充
        styleHeader.setBorderBottom(BorderStyle.DOUBLE); //下边框
        styleHeader.setBorderLeft(BorderStyle.THIN);//左边框
        styleHeader.setBorderTop(BorderStyle.THIN);//上边框
        styleHeader.setBorderRight(BorderStyle.THIN);//右边框
        styleHeader.setFont(font);
        styleHeader.setWrapText(false);

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("学号");
        cell.setCellStyle(styleHeader);
        cell = row.createCell((short) 1);
        cell.setCellValue("姓名");
        cell.setCellStyle(styleHeader);
        cell = row.createCell((short) 2);
        cell.setCellValue("年龄");
        cell.setCellStyle(styleHeader);
        cell = row.createCell((short) 3);
        cell.setCellValue("生日");
        cell.setCellStyle(styleHeader);

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
        List<ExBean> list = getStudent();

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);
            ExBean stu = list.get(i);
            // 第四步，创建单元格，并设置值  
            row.createCell((short) 0).setCellValue((double) stu.getId());
            row.createCell((short) 1).setCellValue(stu.getName());
            row.createCell((short) 2).setCellValue((double) stu.getAge());
            row.createCell((short) 3).setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(stu.getBirth()));
        }
        // 第六步，将文件存到指定位置  
        FileOutputStream fout = new FileOutputStream("e:/temp/students.xlsx");
        wb.write(fout);
        fout.close();
        System.out.println("导出excel成功..");

    }

    private static List<ExBean> getStudent() throws Exception {
        List list = new ArrayList();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        ExBean user1 = new ExBean(1, "张三", 16, df.parse("1997-03-12"));
        ExBean user2 = new ExBean(2, "李四", 17, df.parse("1996-08-12"));
        ExBean user3 = new ExBean(3, "王五", 26, df.parse("1985-11-12"));
        list.add(user1);
        list.add(user2);
        list.add(user3);
        return list;
    }


}

