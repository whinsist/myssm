/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * POI操作excel通用方法
 *
 * @author ssr
 */
public class POIUtil {

    String filePath;

    // 声明一个xls的excel工作表(支持97-2003)
    private HSSFWorkbook wb;

    // 构造方法一
    public POIUtil() {
        wb = new HSSFWorkbook();
    }

    /**
     * 给后缀为.xls的excel模板中的单元格赋值
     *
     * @param sheetNo 工作表index
     * @return List<Cell>
     */
    public void ObjToExcel(int sheetNo, HashMap<String, String> map) {
        List<Cell> list = new ArrayList<Cell>();
        // 得到当前的工作表
        HSSFSheet sheet = this.wb.getSheetAt(sheetNo);
        // 遍历得到该工作表下面的所有单元格Cell
        for (Iterator<Row> rit = sheet.rowIterator(); rit.hasNext(); ) {
            // 得到row行
            HSSFRow row = (HSSFRow) rit.next();
            for (Iterator<Cell> cit = row.cellIterator(); cit.hasNext(); ) {
                // 得到单元格
                HSSFCell cell = (HSSFCell) cit.next();
                list.add(cell);
            }
        }
        for (Cell aList : list) {
            String value = aList.toString();
            int index = value.indexOf("$");
            if (index >= 0) {
                String newIndex = value.substring(1);
                aList.setCellValue(map.get(newIndex));
            }
        }
    }


    /**
     * 将数据输出到excel文件里
     * 支持97-2003版本的Excel
     *
     * @param headerTitle  表头名称
     * @param headerField  表头对应的属性
     * @param headerWidth  表头各列的宽度
     * @param dataList     表格内容数据列表
     * @param sheetName    sheet名称数组
     * @param downFileName 写入的文件名称
     * @param type         字段类型（显示的类型，字符串或数值）
     */
    public void doFromDataToExecl(HttpServletResponse resp, String[] headerTitle, String[] headerField,
                                  short[] headerWidth, List<Map> dataList, String[] sheetName, String downFileName,
                                  Integer[] type) {

        String excelFileName = "";
        try {
            // 创建新的Excel 工作簿
//			HSSFWorkbook workbook = new HSSFWorkbook();
            // 创建基础格式
            HSSFCellStyle cellstyle = this.wb.createCellStyle();
            // 设置自动换行
            cellstyle.setWrapText(true);
            // 垂直
            cellstyle.setVerticalAlignment(VerticalAlignment.CENTER);
            // 水平
            cellstyle.setAlignment(HorizontalAlignment.CENTER);
            // 下边框
            cellstyle.setBorderBottom(BorderStyle.THIN);
            // 左边框
            cellstyle.setBorderLeft(BorderStyle.THIN);
            // 上边框
            cellstyle.setBorderTop(BorderStyle.THIN);
            // 右边框
            cellstyle.setBorderRight(BorderStyle.THIN);

            HSSFCellStyle cellstyle1 = this.wb.createCellStyle();
            cellstyle1.cloneStyleFrom(cellstyle);
            cellstyle1.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            cellstyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // 在Excel工作簿中建一工作表
            HSSFSheet sheet = this.wb.createSheet(sheetName[0]);
            // 第一行是表头
            HSSFRow row = sheet.createRow((short) 0);
            // 设置列高
            row.setHeight((short) (30 * 20));
            // 设置表头数据
            for (int i = 0; i < headerTitle.length; i++) {
                sheet.setColumnWidth(i, headerWidth[i]);
                // 在索引0的位置创建行（最顶端的行）
                // 在索引0的位置创建单元格（左上端）
                HSSFCell cell = row.createCell((short) i);
                // 设置背景色
                cell.setCellStyle(cellstyle1);
                // 定义单元格为字符串类型
                cell.setCellType(CellType.STRING);
                // 在单元格中输入一些内容
                cell.setCellValue(headerTitle[i]);
            }

            // 遍历数据列表，将其写入sheet
            if (dataList != null && dataList.size() != 0) {
                int index = 1;
                for (Map aDataList : dataList) {
                    HashMap hMap = (HashMap) aDataList;
                    // 从第二行开始
                    HSSFRow dataRow = sheet.createRow((short) (index));
                    for (int j = 0; j < headerField.length; j++) {
                        // 在索引0的位置创建行（最顶端的行）
                        // 在索引0的位置创建单元格（左上端）
                        HSSFCell cell = dataRow.createCell((short) j);
                        // 设置背景色
                        cell.setCellStyle(cellstyle);
                        // 定义单元格为字符串类型
                        if (type.length > 0) {
                            if (type[j] == 0) {
                                cell.setCellType(CellType.NUMERIC);
                            } else {
                                cell.setCellType(CellType.STRING);
                            }
                        } else {
                            cell.setCellType(CellType.STRING);
                        }
                        // 设置对应表头的数据到单元格
                        if (hMap.containsKey(headerField[j])) {
                            if (hMap.get(headerField[j]) == null || "".equals(hMap.get(headerField[j]).toString())) {
                                cell.setCellValue("");
                            } else if (type.length > 0) {
                                if (type[j] == 0) {
                                    cell.setCellValue(Double.parseDouble(hMap.get(headerField[j]).toString()));
                                } else {
                                    cell.setCellValue(hMap.get(headerField[j]).toString());
                                }
                            } else {
                                cell.setCellValue(hMap.get(headerField[j]).toString());
                            }
                        }
                    }
                    index++;
                }
            }

            //将生成的excel对象，输出为文件
            String filePath = PropertiesUtil.getProperty("upload.tmpfile.path");
            File fileDir = new File(filePath);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            OutputStream outputStream;
            //生成随机UUID文件名称
            String fileName = UUID.randomUUID().toString() + ".xls";
            try {
                outputStream = new FileOutputStream(filePath + "/" + fileName);
                try {
                    this.wb.write(outputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            excelFileName = filePath + "/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.fileDownLoad(resp, excelFileName, downFileName);
        this.deleteFile(excelFileName);
    }

    /**
     * 文件下载
     *
     * @param rsp      HttpServletResponse对象
     * @param filePath 文件在服务器上的路径
     * @param fileName 用户下载时,保存的文件名
     */
    public InputStream fileDownLoad(HttpServletResponse rsp, String filePath, String fileName) {
        InputStream fis = null;
        OutputStream fos = null;

        File file = new File(filePath);
        if (!file.exists()) {
            return null;
            // 请不要删除，以后有用
            // file.createTempFile(prefix, suffix, directory)
        }
        try {
            // 读取临时文件下载
            fis = new BufferedInputStream(new FileInputStream(filePath));
            // 每次读取的缓冲数组大小
            int readBuffer = 2048;
            // 缓冲数组
            byte[] buffer = new byte[readBuffer];
            int byteRead = 0;
            // 计算最后一次缓冲区的大小
            int lastBufferSize = fis.available() % readBuffer;
            int leftByte = -1;
            // 如果文件的大小,小于缓冲区的值
            if (fis.available() < readBuffer) {
                leftByte = fis.available();
            }
            // 清空response
            rsp.reset();
            // 设置response的Header
            rsp.addHeader("Content-Disposition",
                    "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO-8859-1")
            );
            // rep.addHeader("Content-Length", "" + savedPath.length());
            rsp.setContentType("application/octet-stream;charset=GBK");
            fos = new BufferedOutputStream(rsp.getOutputStream());

            // 输出流写出到终端
            while ((byteRead = fis.read(buffer)) != -1) {
                // log.debug(fis.available());
                // 最后一次缓冲
                if (fis.available() == 0) {
                    // 文件大小是缓冲 整倍数
                    if (lastBufferSize == 0) {
                        fos.write(buffer, 0, buffer.length);
                    } else if (leftByte == -1) {
                        fos.write(buffer, 0, lastBufferSize);
                    }
                    // 文件大小小于缓冲
                    else {
                        fos.write(buffer, 0, leftByte);
                    }
                } else {
                    fos.write(buffer, 0, buffer.length);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 删除一个文件
     *
     * @param filePath 文件路径
     */
    public boolean deleteFile(String filePath) {
        File f = new File(filePath);
        return f.exists() && f.delete();
    }

    /**
     * 将数据输出到excel文件里
     * 支持97-2003版本的Excel
     *
     * @param headerTitle  表头名称
     * @param headerField  表头对应的属性
     * @param headerWidth  表头各列的宽度
     * @param dataList     表格内容数据列表
     * @param sheetName    sheet名称数组
     * @param downFileName 写入的文件名称
     * @param type         字段类型（显示的类型，字符串或数值）
     */
    public void doFromDataListToExecl(HttpServletResponse resp, List<String[]> headerTitle, List<String[]> headerField,
                                      List<short[]> headerWidth, List<List<Map>> dataList, String[] sheetName,
                                      String downFileName, List<Integer[]> type) {

        String excelFileName = "";
        try {
            // 创建新的Excel 工作簿
//			HSSFWorkbook workbook = new HSSFWorkbook();
            // 创建基础格式
            HSSFCellStyle cellstyle = this.wb.createCellStyle();
            // 设置自动换行
            cellstyle.setWrapText(true);
            // 垂直
            cellstyle.setVerticalAlignment(VerticalAlignment.CENTER);
            // 水平
            cellstyle.setAlignment(HorizontalAlignment.CENTER);
            // 下边框
            cellstyle.setBorderBottom(BorderStyle.THIN);
            // 左边框
            cellstyle.setBorderLeft(BorderStyle.THIN);
            // 上边框
            cellstyle.setBorderTop(BorderStyle.THIN);
            // 右边框
            cellstyle.setBorderRight(BorderStyle.THIN);

            HSSFCellStyle cellstyle1 = this.wb.createCellStyle();
            cellstyle1.cloneStyleFrom(cellstyle);
            cellstyle1.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            cellstyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // 在Excel工作簿中建一工作表
            for (int n = 0; n < sheetName.length; n++) {
                HSSFSheet sheet = this.wb.createSheet(sheetName[n]);
                // 第一行是表头
                HSSFRow row = sheet.createRow((short) 0);
                // 设置列高
                row.setHeight((short) (30 * 20));
                // 设置表头数据
                for (int i = 0; i < headerTitle.get(n).length; i++) {
                    sheet.setColumnWidth(i, headerWidth.get(n)[i]);
                    // 在索引0的位置创建行（最顶端的行）
                    // 在索引0的位置创建单元格（左上端）
                    HSSFCell cell = row.createCell((short) i);
                    // 设置背景色
                    cell.setCellStyle(cellstyle1);
                    // 定义单元格为字符串类型
                    cell.setCellType(CellType.STRING);
                    // 在单元格中输入一些内容
                    cell.setCellValue(headerTitle.get(n)[i]);
                }

                // 遍历数据列表，将其写入sheet
                if (dataList != null && dataList.size() != 0) {
                    int index = 1;
                    for (Map map : dataList.get(n)) {
                        HashMap hMap = (HashMap) map;
                        // 从第二行开始
                        HSSFRow dataRow = sheet.createRow((short) (index));
                        for (int j = 0; j < headerField.get(n).length; j++) {
                            // 在索引0的位置创建行（最顶端的行）
                            // 在索引0的位置创建单元格（左上端）
                            HSSFCell cell = dataRow.createCell((short) j);
                            // 设置背景色
                            cell.setCellStyle(cellstyle);
                            // 定义单元格为字符串类型
                            if (type.size() > 0 && type.get(n).length > 0) {
                                if (type.get(n)[j] == 0) {
                                    cell.setCellType(CellType.NUMERIC);
                                } else {
                                    cell.setCellType(CellType.STRING);
                                }
                            } else {
                                cell.setCellType(CellType.STRING);
                            }
                            // 设置对应表头的数据到单元格
                            if (hMap.containsKey(headerField.get(n)[j])) {
                                if (hMap.get(headerField.get(n)[j]) == null ||
                                        "".equals(hMap.get(headerField.get(n)[j]).toString())) {
                                    cell.setCellValue("");
                                } else if (type.size() > 0 && type.get(n).length > 0) {
                                    if (type.get(n)[j] == 0) {
                                        cell.setCellValue(Double.parseDouble(hMap.get(headerField.get(n)[j])
                                                .toString()));
                                    } else {
                                        cell.setCellValue(hMap.get(headerField.get(n)[j]).toString());
                                    }
                                } else {
                                    cell.setCellValue(hMap.get(headerField.get(n)[j]).toString());
                                }
                            }
                        }
                        index++;
                    }
                }
            }

            //将生成的excel对象，输出为文件
            String filePath = PropertiesUtil.getProperty("upload.tmpfile.path");
            File fileDir = new File(filePath);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            OutputStream outputStream;
            //生成随机UUID文件名称
            String fileName = UUID.randomUUID().toString() + ".xls";
            try {
                outputStream = new FileOutputStream(filePath + "/" + fileName);
                try {
                    this.wb.write(outputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            excelFileName = filePath + "/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.fileDownLoad(resp, excelFileName, downFileName);
        this.deleteFile(excelFileName);
    }
}
