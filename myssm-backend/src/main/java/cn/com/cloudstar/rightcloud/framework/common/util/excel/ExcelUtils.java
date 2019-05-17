/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util.excel;

import cn.com.cloudstar.rightcloud.framework.common.exception.PropertyAccessException;
import cn.com.cloudstar.rightcloud.framework.common.util.BeanUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.StringUtil;
import com.google.common.base.Strings;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    private XSSFWorkbook workBook = null;

    public ExcelUtils() {
        this.workBook = new XSSFWorkbook();
    }

    public ExcelUtils(String filePath) throws IOException {
        this.workBook = new XSSFWorkbook(new FileInputStream(new File(filePath)));
    }



    public <T> List<T> getSheetValueByIndex(int sheetIndex, Class<T> clazz)
            throws InstantiationException, IllegalAccessException, PropertyAccessException {
        return getSheetValueByName(workBook.getSheetName(sheetIndex), clazz);
    }

    public <T> List<T> getSheetValueByName(String sheetName, Class<T> clazz)
            throws InstantiationException, IllegalAccessException, PropertyAccessException {
        List<T> sheetList = new LinkedList<T>();
        XSSFSheet sheet = workBook.getSheet(sheetName);

        if (sheet == null) {
            return null;
        }
        T rowData;
        Field[] fields = clazz.getDeclaredFields();
        TableDef t;
        int cellIndex;
        XSSFRow row;
        XSSFCell cell;
        String cellVal;
        SheetCtrl ctrl = clazz.getAnnotation(SheetCtrl.class);
        for (int i = sheet.getFirstRowNum() + ctrl.offsetRow(); i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);

            if (row.getCell(ctrl.valExistsIndex()) == null ||
                    Strings.isNullOrEmpty(row.getCell(ctrl.valExistsIndex()).getRawValue())) {
                break;
            }
            rowData = clazz.newInstance();
            for (Field field : fields) {
                t = field.getAnnotation(TableDef.class);

                if (t == null) {
                    continue;
                }

                cellIndex = t.column();
                cell = row.getCell(cellIndex);
                switch (t.cellType()) {
                    case String:
                        cell.setCellType(CellType.STRING);
                        cellVal = cell.getStringCellValue();
                        break;
                    case Number:
                        cell.setCellType(CellType.NUMERIC);
                        cellVal = Double.toString(cell.getNumericCellValue());
                        break;
                    case Date:
                        cellVal = StringUtil.dateFormat(cell.getDateCellValue(), StringUtil.DF_YMD);
                        break;
                    case Formula:
                        cellVal = cell.getCellFormula();
                        break;
                    default:
                        cellVal = cell.getRawValue();
                }
                if (t.keyValue().length != 0) {
                    for (int j = 0; j < t.keyValue().length; j += 2) {
                        if (t.keyValue()[j].equals(cellVal)) {
                            cellVal = t.keyValue()[j + 1];
                            break;
                        }
                    }
                }

                BeanUtil.setBeanProperty(rowData, field.getName(), StringUtils.trim(cellVal));
            }
            sheetList.add(rowData);
        }

        return sheetList;
    }

    public <T> void exportExcel(String sheetName, List<T> param, Class<T> clazz) throws PropertyAccessException {
        XSSFSheet sheet = this.workBook.createSheet(sheetName);

        Field[] fields = clazz.getDeclaredFields();

        ExcelDef e = null;
        int cellIndex = 0;
        XSSFRow row = sheet.createRow(0);

        XSSFCell cell = null;
        XSSFCellStyle styleHeader = this.workBook.createCellStyle();
        styleHeader.setAlignment(HorizontalAlignment.CENTER);
//        styleHeader.setFillBackgroundColor(XSSFColor.YELLOW.index);
        XSSFFont font = workBook.createFont();
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
        cell = row.createCell(0);
        cell.setCellValue("No");
        cell.setCellStyle(styleHeader);
        sheet.setColumnWidth(0, 5 * 256);
        for (Field field : fields) {
            e = field.getAnnotation(ExcelDef.class);
            if (e == null) {
                continue;
            }
            cellIndex = Integer.parseInt(e.column());
            sheet.setColumnWidth(cellIndex, e.width() * 256);
            cell = row.createCell(cellIndex);
            cell.setCellStyle(styleHeader);
            cell.setCellValue(e.columnName());
        }
        int j = 1;
        String value = null;
        for (int i = 0; i < param.size(); i++, j++) {
            row = sheet.createRow(j);
            cell = row.createCell(0);
            cell.setCellFormula("ROW()-1");
            for (Field field : fields) {
                e = field.getAnnotation(ExcelDef.class);
                if (e == null) {
                    continue;
                }
                cellIndex = Integer.parseInt(e.column());
                cell = row.createCell(cellIndex);
                Object valueObj = BeanUtil.getBeanProperty(param.get(i), field.getName());
                if(valueObj instanceof String){
                    value = (String)valueObj;
                }else if(valueObj instanceof BigDecimal){
                    BigDecimal val = ((BigDecimal)valueObj).setScale(2,BigDecimal.ROUND_HALF_UP);
                    value = val != null?val.toString():"";
                }else if(valueObj instanceof Date){
                    value = StringUtil.dateFormat((Date)valueObj, StringUtil.DF_YMD_24);
                }else{
                    value = valueObj != null?valueObj.toString():"";
                }
                cell.setCellValue(value);
            }
        }

        //FileOutputStream fout = new FileOutputStream(filePath);
        //this.workBook.write(out);
        //fout.close();
    }
    public void flushOut(OutputStream outputStream) throws IOException {
        this.workBook.write(outputStream);
    }

    public List<Map<String, String>> getRowDataToMap(int sheetIndex, Class<?> clazz)
            throws InstantiationException, IllegalAccessException, PropertyAccessException {
        return getRowDataToMap(workBook.getSheetName(sheetIndex), clazz);
    }

    public List<Map<String, String>> getRowDataToMap(String sheetName, Class<?> clazz)
            throws PropertyAccessException {
        List<Map<String, String>> sheetList = new ArrayList<>();
        XSSFSheet sheet = workBook.getSheet(sheetName);

        if (sheet == null) {
            return null;
        }
        XSSFRow row;
        XSSFCell cell;
        SheetCtrl ctrl = clazz.getAnnotation(SheetCtrl.class);
        row = sheet.getRow(ctrl.keyRow());
        Map<Integer, String> keyCell = new HashMap<>();
        row.cellIterator().forEachRemaining(c -> {
            c.setCellType(CellType.STRING);
            if (Strings.isNullOrEmpty(c.getStringCellValue())) {
                return;
            }
            keyCell.put(c.getColumnIndex(), c.getStringCellValue());
        });

        for (int i = sheet.getFirstRowNum() + ctrl.offsetRow(); i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);

            if (row.getCell(ctrl.valExistsIndex()) == null ||
                    Strings.isNullOrEmpty(row.getCell(ctrl.valExistsIndex()).getRawValue())) {
                break;
            }
            Map<String, String> rowDataMap = new HashMap<>();
            for (Integer index : keyCell.keySet()) {
                cell = row.getCell(index);
                cell.setCellType(CellType.STRING);
                rowDataMap.put(keyCell.get(index), cell.getStringCellValue());
            }
            sheetList.add(rowDataMap);
        }

        return sheetList;
    }

    public <T> void setInfoToExcel(String filePath, String sheetName, List<T> param, Class<T> clazz)
            throws PropertyAccessException, IOException {
        XSSFSheet sheet = this.workBook.createSheet(sheetName);

        Field[] fields = clazz.getDeclaredFields();

        ExcelDef e = null;
        int cellIndex = 0;
        XSSFRow row = sheet.createRow(0);

        XSSFCell cell = null;
        XSSFCellStyle styleHeader = this.workBook.createCellStyle();
        styleHeader.setAlignment(HorizontalAlignment.CENTER);
        XSSFFont font = workBook.createFont();
        //粗体显示
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
        cell = row.createCell(0);
        cell.setCellValue("No");
        cell.setCellStyle(styleHeader);
        sheet.setColumnWidth(0, 5 * 256);
        for (Field field : fields) {
            e = field.getAnnotation(ExcelDef.class);
            if (e == null) {
                continue;
            }
            cellIndex = Integer.parseInt(e.column());
            sheet.setColumnWidth(cellIndex, e.width() * 256);
            cell = row.createCell(cellIndex);
            cell.setCellStyle(styleHeader);
            cell.setCellValue(e.columnName());
        }
        int j = 1;
        String value = null;
        for (int i = 0; i < param.size(); i++, j++) {
            row = sheet.createRow(j);
            cell = row.createCell(0);
            cell.setCellFormula("ROW()-1");
            for (Field field : fields) {
                e = field.getAnnotation(ExcelDef.class);
                if (e == null) {
                    continue;
                }
                cellIndex = Integer.parseInt(e.column());
                cell = row.createCell(cellIndex);
                value = (String) BeanUtil.getBeanProperty(param.get(i), field.getName());
                cell.setCellValue(value);
            }
        }

        FileOutputStream fout = new FileOutputStream(filePath);
        this.workBook.write(fout);
        fout.close();

    }

    public <T> void exportExcel(OutputStream out, String templatePath, Map<String, Object> data) throws PropertyAccessException, IOException, InvalidFormatException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(templatePath);
        XLSTransformer transformer = new XLSTransformer();
        Workbook workbook = transformer.transformXLS(is, data);
        workbook.write(out);
    }

    public <T> void exportExcel(OutputStream out, String sheetName, List<T> param, Class<T> clazz)
            throws PropertyAccessException, IOException {
        XSSFSheet sheet = this.workBook.createSheet(sheetName);

        Field[] fields = clazz.getDeclaredFields();

        ExcelDef e = null;
        int cellIndex = 0;
        XSSFRow row = sheet.createRow(0);

        XSSFCell cell = null;
        XSSFCellStyle styleHeader = this.workBook.createCellStyle();
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
        cell = row.createCell(0);
        cell.setCellValue("No");
        cell.setCellStyle(styleHeader);
        sheet.setColumnWidth(0, 5 * 256);
        for (Field field : fields) {
            e = field.getAnnotation(ExcelDef.class);
            if (e == null) {
                continue;
            }
            cellIndex = Integer.parseInt(e.column());
            sheet.setColumnWidth(cellIndex, e.width() * 256);
            cell = row.createCell(cellIndex);
            cell.setCellStyle(styleHeader);
            cell.setCellValue(e.columnName());
        }
        int j = 1;
        String value = null;
        for (int i = 0; i < param.size(); i++, j++) {
            row = sheet.createRow(j);
            cell = row.createCell(0);
            cell.setCellFormula("ROW()-1");
            for (Field field : fields) {
                e = field.getAnnotation(ExcelDef.class);
                if (e == null) {
                    continue;
                }
                cellIndex = Integer.parseInt(e.column());
                cell = row.createCell(cellIndex);
                Object valueObj = BeanUtil.getBeanProperty(param.get(i), field.getName());
                if (valueObj instanceof String) {
                    value = (String) valueObj;
                } else if (valueObj instanceof BigDecimal) {
                    BigDecimal val = ((BigDecimal) valueObj).setScale(2, BigDecimal.ROUND_HALF_UP);
                    value = val != null ? val.toString() : "";
                } else if (valueObj instanceof Date) {
                    value = StringUtil.dateFormat((Date) valueObj, StringUtil.DF_YMD_24);
                } else {
                    value = valueObj != null ? valueObj.toString() : "";
                }
                cell.setCellValue(value);
            }
        }

        //FileOutputStream fout = new FileOutputStream(filePath);
        this.workBook.write(out);
        //fout.close();
    }

    public <T> void exportExcelAndMerge(OutputStream out, String sheetName, List<T> param, Class<T> clazz , Integer[] mergeData)
            throws PropertyAccessException, IOException {
        XSSFSheet sheet = this.workBook.createSheet(sheetName);

        Field[] fields = clazz.getDeclaredFields();

        ExcelDef e = null;
        int cellIndex = 0;
        XSSFRow row = sheet.createRow(0);

        XSSFCell cell = null;
        XSSFCellStyle styleHeader = this.workBook.createCellStyle();
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
        cell = row.createCell(0);
        cell.setCellValue("No");
        cell.setCellStyle(styleHeader);
        sheet.setColumnWidth(0, 5 * 256);
        for (Field field : fields) {
            e = field.getAnnotation(ExcelDef.class);
            if (e == null) {
                continue;
            }
            cellIndex = Integer.parseInt(e.column());
            sheet.setColumnWidth(cellIndex, e.width() * 256);
            cell = row.createCell(cellIndex);
            cell.setCellStyle(styleHeader);
            cell.setCellValue(e.columnName());
        }
        int j = 1;
        String value = null;
        for (int i = 0; i < param.size(); i++, j++) {
            row = sheet.createRow(j);
            cell = row.createCell(0);
            cell.setCellFormula("ROW()-1");
            for (Field field : fields) {
                e = field.getAnnotation(ExcelDef.class);
                if (e == null) {
                    continue;
                }
                cellIndex = Integer.parseInt(e.column());
                cell = row.createCell(cellIndex);
                Object valueObj = BeanUtil.getBeanProperty(param.get(i), field.getName());
                if (valueObj instanceof String) {
                    value = (String) valueObj;
                } else if (valueObj instanceof BigDecimal) {
                    BigDecimal val = ((BigDecimal) valueObj).setScale(2, BigDecimal.ROUND_HALF_UP);
                    value = val != null ? val.toString() : "";
                } else if (valueObj instanceof Date) {
                    value = StringUtil.dateFormat((Date) valueObj, StringUtil.DF_YMD_24);
                } else {
                    value = valueObj != null ? valueObj.toString() : "";
                }
                cell.setCellValue(value);
            }
        }
        sheet.addMergedRegion(new CellRangeAddress(mergeData[0],mergeData[1],mergeData[2],mergeData[3]));

        //FileOutputStream fout = new FileOutputStream(filePath);
        this.workBook.write(out);
        //fout.close();
    }

    @SuppressWarnings("deprecation")
//    public void setPullDown(int pullCol, String[] pullDown) {
//        XSSFSheet sheet = workBook.getSheetAt(0);
//        DVConstraint constraint = DVConstraint.createExplicitListConstraint(pullDown);
//        CellRangeAddressList regions = new CellRangeAddressList(1, sheet.getLastRowNum(), pullCol, pullCol);
//        XSSFDataValidation data_validation = new XSSFDataValidation(regions, constraint);
//        sheet.addValidationData(data_validation);
//    }

    public void saveFile(String filePath) {
        try {
            FileOutputStream fout = new FileOutputStream(filePath);
            this.workBook.write(fout);
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
