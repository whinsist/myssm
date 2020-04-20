package cn.com.cloudstar.rightcloud.framework.test.t003util.excel;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Hong.Wu
 * @date: 5:30 2020/04/01
 *
 * 先目前支持的是xlsx
 */
public class ExcelManagerUtil {

    /*每个sheet的最多行数 （不包括头部）     注： xls最大行数65536*/
    private static final int SheetMaxRowsExcludeHead = 65535;

    private static final String DefaultExportExcelPath = "e:/temp/default.xlsx";


    public static void exportExcel(String fileName, String[] titles, List<String> list, int width,
                                   HttpServletResponse response) {

        try {
            // 创建一个EXCEL
            XSSFWorkbook workBook = new XSSFWorkbook();
            HSSFWorkbook wb = new HSSFWorkbook();
            XSSFCellStyle styleHeader = workBook.createCellStyle();
            styleHeader.setAlignment(HorizontalAlignment.CENTER);
            XSSFFont font = workBook.createFont();
            font.setBold(true);//粗体显示
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

            // 获取需要几个sheet来保存
            int sheetCounts = list.size() % SheetMaxRowsExcludeHead == 0 ?
                    list.size() / SheetMaxRowsExcludeHead :
                    list.size() / SheetMaxRowsExcludeHead + 1;
            for (int sheetN = 1; sheetN <= sheetCounts; sheetN++) {
                // 创建一个SHEET
                String sheetName = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "_" + sheetN;
                XSSFSheet sheet = workBook.createSheet(sheetName);
                // 创建SHEET第一行头部并填充标题
                XSSFRow headerRow = sheet.createRow(0);
                for (int i = 0; i < titles.length; i++) {
                    String colomnName = titles[i];
                    XSSFCell cell = headerRow.createCell(i);
                    cell.setCellStyle(styleHeader);
                    cell.setCellValue(colomnName);
                    // 列宽度自适应     汉字乘以512，英文和数字乘以256
                    sheet.setColumnWidth(i, colomnName.length() * width);
                }
                // 创建SHEET填充内容
                int start = (sheetN - 1) * SheetMaxRowsExcludeHead;
                int end =
                        sheetN * SheetMaxRowsExcludeHead > list.size() ? list.size() : sheetN * SheetMaxRowsExcludeHead;
                for (int i = start; i < end; i++) {
                    String rowData = list.get(i);
                    String[] str_array = rowData.split("¿");
                    int sheetRowNum = (i % SheetMaxRowsExcludeHead) + 1;
                    // 创建行
                    XSSFRow row_ = sheet.createRow(sheetRowNum);
                    // 创建列
                    for (int j = 0; j < str_array.length; j++) {
                        XSSFCell cell = row_.createCell(j);
                        //cell.setCellStyle(contentCellStyle);
                        cell.setCellValue(str_array[j]);
                    }
                }
            }
            // 输出
            if (response != null) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition",
                                   "attachment; filename= " + new String(fileName.getBytes("GBK"), "ISO8859_1"));
                ServletOutputStream out = response.getOutputStream();
                workBook.write(out);
                out.close();
            } else {
                FileOutputStream fout = new FileOutputStream(DefaultExportExcelPath);
                workBook.write(fout);
                fout.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static List<String[]> importExcel(File file, int excludeIndex) {
        List<String[]> returnList = new ArrayList<String[]>();
        try {
            // 判断文件类型
            //boolean isExcel2007 = file.getAbsolutePath().matches("^.+\\.(?i)(xlsx)$");
            boolean isExcel2007 = true;
            if (isExcel2007) {
                returnList = importXLSX(file, excludeIndex);
            } else {
                returnList = importXLS(file, excludeIndex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnList;
    }

    private static List<String[]> importXLS(File file, int excludeIndex) throws Exception {
        List<String[]> returnList = new ArrayList<>();
//            XSSFWorkbook workbook = Workbook.getWorkbook(file);
//            Sheet sheet = workbook.getSheet(0);
        // 得到总行数
//            int rownum = sheet.getLastRowNum();
//            for (int i = 0; i < rownum; i++) {
//                if (i < excludeIndex) {
//                    continue;
//                }
//                int columnNum = sheet.getColumns();
//                String[] columns = new String[columnNum];
//                for (int j = 0; j < columnNum; j++) {
//                    String column = sheet.getCell(j, i).getContents();
//                    columns[j] = column;
//                }
//                returnList.add(columns);
//            }
        return returnList;
    }

    private static List<String[]> importXLSX(File file, int excludeIndex) throws Exception {
        List<String[]> returnList = new ArrayList<>();
        // 1、解析Excel
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rownum = sheet.getLastRowNum() + 1;
        for (int i = 0; i < rownum; i++) {
            if (i < excludeIndex) {
                continue;
            }
            XSSFRow row = sheet.getRow(i);
            int columnNum = row.getLastCellNum();
            String[] columns = new String[columnNum];
            for (int j = 0; j < columnNum; j++) {
                String column = Objects.isNull(row.getCell(j)) ? null : row.getCell(j).toString();
                columns[j] = column;
            }
            returnList.add(columns);
        }
        return returnList;
    }


    /**
     * 根据模板生成Excel文件.
     *
     * @param templateFileName 模板文件.
     * @param list 模板中存放的数据.
     * @param resultFileName 生成的文件.
     */
    public static void createExcelByTemplate(String templateFileName, List<?> list, String resultFileName) {
        //创建XLSTransformer对象
        XLSTransformer transformer = new XLSTransformer();
        //获取java项目编译后根路径
        URL url = ExcelManagerUtil.class.getClassLoader().getResource("");
        //得到模板文件路径
        String srcFilePath = url.getPath() + templateFileName;
        Map<String, Object> beanParams = new HashMap<>();
        beanParams.put("list", list);
        String destFilePath = url.getPath() + resultFileName;
        try {
            //生成Excel文件
            transformer.transformXLS(srcFilePath, beanParams, destFilePath);
        } catch (ParsePropertyException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
