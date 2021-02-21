package cn.com.cloudstar.rightcloud.framework.test.t003util.excel;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.com.cloudstar.rightcloud.framework.test.t003util.excel.bean.ExBean;


/**
 * @功能：手工构建一个简单格式的Excel
 */
public class PoiOrgTest {

    public static void main(String[] args) throws Exception {
        export();

        // https://blog.csdn.net/zhoubangbang1/article/details/112644767
//        test1();
//        test2();
//        test3();
//        test4();
    }


    //绘制图形
    public static void test4() throws Exception {
        //1.创建workbook工作簿
        Workbook wb = new XSSFWorkbook();
        //2.创建表单Sheet
        Sheet sheet = wb.createSheet("test");
        //读取图片流
        FileInputStream stream = new FileInputStream("d:\\testfiles\\向日葵.jpg");
        byte[] bytes = IOUtils.toByteArray(stream);
        //读取图片到二进制数组
        stream.read(bytes);
        //向Excel添加一张图片,并返回该图片在Excel中的图片集合中的下标
        int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
        //绘图工具类
        CreationHelper helper = wb.getCreationHelper();
        //创建一个绘图对象
        Drawing<?> patriarch = sheet.createDrawingPatriarch();
        //创建锚点,设置图片坐标
        ClientAnchor anchor = helper.createClientAnchor();
        anchor.setCol1(0);//从0开始
        anchor.setRow1(0);//从0开始
        //创建图片
        Picture picture = patriarch.createPicture(anchor, pictureIdx);
        picture.resize();
        //6.文件流
        FileOutputStream fos = new FileOutputStream("d:\\temp\\test.xlsx");
        //7.写入文件
        wb.write(fos);
        fos.close();
    }


    private static void test1() throws Exception {
        //测试创建excel文件
        //1.创建workbook工作簿
        Workbook wb = new XSSFWorkbook();
        //2.创建表单Sheet
        Sheet sheet = wb.createSheet("test");
        //3.文件流
        FileOutputStream fos = new FileOutputStream("E:\\test.xlsx");
        //4.写入文件
        wb.write(fos);
        fos.close();
    }

    private static void test2() throws Exception {
        //测试创建单元格
        //1.创建workbook工作簿
        Workbook wb = new XSSFWorkbook();
        //2.创建表单Sheet
        Sheet sheet = wb.createSheet("test");
        //3.创建行对象，从0开始
        Row row = sheet.createRow(3);
        //4.创建单元格，从0开始
        Cell cell = row.createCell(0);
        //5.单元格写入数据
        cell.setCellValue("传智播客");
        //6.文件流
        FileOutputStream fos = new FileOutputStream("d:\\temp\\test.xlsx");
        //7.写入文件
        wb.write(fos);
        fos.close();
    }

    private static void test3() {
//        //创建单元格样式对象
//        CellStyle cellStyle = wb.createCellStyle();
//        //设置边框
//        cellStyle.setBorderBottom(BorderStyle.DASH_DOT);//下边框
//        cellStyle.setBorderTop(BorderStyle.HAIR);//上边框
//        //设置字体
//        Font font = wb.createFont();//创建字体对象
//        font.setFontName("华文行楷");//设置字体
//        font.setFontHeightInPoints((short)28);//设置字号
//        cellStyle.setFont(font);
//        //设置宽高
//        sheet.setColumnWidth(0, 31 * 256);//设置第一列的宽度是31个字符宽度
//        row.setHeightInPoints(50);//设置行的高度是50个点
//        //设置居中显示
//        cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
//        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//        //设置单元格样式
//        cell.setCellStyle(cellStyle);
//        //合并单元格
//        CellRangeAddress region =new CellRangeAddress(0, 3, 0, 2);
//        sheet.addMergedRegion(region);
    }

    public static void export() throws Exception {
        // 第一步，创建一个webbook，对应一个Excel文件  
        //HSSFWorkbook wb = new HSSFWorkbook();
        // poi-3.17.jar
        //org.apache.poi.hssf.usermodel.HSSFWorkbook wb = new HSSFWorkbook();
        // poi-ooxml-3.17.jar
        org.apache.poi.xssf.usermodel.XSSFWorkbook wb = new XSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = wb.createSheet("数据表一");
        // 设置第一列的宽度是20个字符宽度 (汉字乘以512英文和数字乘以256)
        sheet.setColumnWidth(0, 20 * 256);
        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 20 * 256);
        sheet.setColumnWidth(3, 20 * 256);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        XSSFRow tbHeadRow = sheet.createRow(0);
        // 设置第一行的高度是30个点
        tbHeadRow.setHeightInPoints(30);

        // 第四步，创建单元格，并设置表头
        XSSFCellStyle cellStyle = getHeadStyle(wb);
        XSSFCell headCell1 = tbHeadRow.createCell((short) 0);//head第1个单元格
        headCell1.setCellValue("学号");
        headCell1.setCellStyle(cellStyle);
        XSSFCell headCell2 = tbHeadRow.createCell((short) 1);//head第2个单元格
        headCell2.setCellValue("姓名");
        headCell2.setCellStyle(cellStyle);
        XSSFCell headCell3 = tbHeadRow.createCell((short) 2);//head第3个单元格
        headCell3.setCellValue("年龄");
        headCell3.setCellStyle(cellStyle);
        XSSFCell headCell4 = tbHeadRow.createCell((short) 3);//head第4个单元格
        headCell4.setCellValue("生日");
        headCell4.setCellStyle(cellStyle);
        XSSFCell headCell5 = tbHeadRow.createCell((short) 4);//head第5个单元格
        headCell5.setCellValue("身份号");
        headCell5.setCellStyle(cellStyle);

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
        List<ExBean> list = getStudent();
        for (int i = 0; i < list.size(); i++) {
            ExBean stu = list.get(i);
            // 第五步，创建下面的每行 header被占 从1开始是数据行
            XSSFRow tbDataRow = sheet.createRow(i + 1);
            tbDataRow.createCell((short) 0).setCellValue((double) stu.getId());
            tbDataRow.createCell((short) 1).setCellValue(stu.getName());
            tbDataRow.createCell((short) 2).setCellValue((double) stu.getAge());
            tbDataRow.createCell((short) 3).setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(stu.getBirth()));
            tbDataRow.createCell((short) 4).setCellValue(stu.getIdcard());
        }
        // 第六步，将文件存到指定位置  
        FileOutputStream os = new FileOutputStream("d:/temp/students.xlsx");
        wb.write(os);
        os.close();
        System.out.println("导出excel成功..");
    }

    private static XSSFCellStyle getHeadStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = wb.createCellStyle();
        // 设置自动换行
        cellStyle.setWrapText(true);
        // 垂直居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 水平居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 设置边框填充
        cellStyle.setBorderBottom(BorderStyle.DOUBLE); //下边框
        cellStyle.setBottomBorderColor(IndexedColors.RED.getIndex());
        cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        cellStyle.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle.setBorderRight(BorderStyle.THIN);//右边框
        // 设置背景色
        cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(220, 230, 241)));
        //headStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(156, 195, 230)));
        //headStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        // 填充模式
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 设置字体
        XSSFFont font = wb.createFont();
        font.setFontName("华文行楷");//设置字体
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);//设置字号
        cellStyle.setFont(font);
        return cellStyle;
    }

    private static List<ExBean> getStudent() throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        Date date = df.parse("1997-03-12");
        List list = new ArrayList();
        for (int i = 0; i < 100; i++) {
            BigDecimal bigDecimal = new BigDecimal("500238198001133230").add(new BigDecimal(i));
            list.add(new ExBean((i+1), "张三", 16, date, bigDecimal.toString()));
        }
        return list;
    }


}

