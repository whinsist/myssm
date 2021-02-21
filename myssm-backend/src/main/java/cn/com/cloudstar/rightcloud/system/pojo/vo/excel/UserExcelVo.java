package cn.com.cloudstar.rightcloud.system.pojo.vo.excel;

import lombok.Data;

import cn.com.cloudstar.rightcloud.framework.common.util.excel.CellTypeEnum;
import cn.com.cloudstar.rightcloud.framework.common.util.excel.ExcelDef;
import cn.com.cloudstar.rightcloud.framework.common.util.excel.SheetCtrl;
import cn.com.cloudstar.rightcloud.framework.common.util.excel.TableDef;

/**
 * @author Hong.Wu
 * @date: 17:40 2019/06/25
 */
@Data
@SheetCtrl(offsetRow = 1, valExistsIndex = 1)
public class UserExcelVo {

    @TableDef(column = 0, cellType = CellTypeEnum.Long)
    @ExcelDef(column = "0", columnName = "姓名", width = 15)
    private String stuName;

    @TableDef(column = 1)
    @ExcelDef(column = "1", columnName = "身份证号码", width = 15)
    private String idNumber;

    @TableDef(column = 2)
    @ExcelDef(column = "2", columnName = "年级", width = 15)
    private String gradeName;

    @TableDef(column = 3)
    @ExcelDef(column = "3", columnName = "班级", width = 15)
    private String className;

    //@TableDef(column = 4, keyValue = {"a", "b"})
    @TableDef(column = 4)
    @ExcelDef(column = "4", columnName = "入学日期", width = 15)
    private String enrollDate;

    @TableDef(column = 5)
    @ExcelDef(column = "5", columnName = "学制", width = 15)
    private Integer schoolLength;

    @TableDef(column = 6)
    @ExcelDef(column = "6", columnName = "专业", width = 15)
    private String majorName;

    @TableDef(column = 7)
    @ExcelDef(column = "7", columnName = "就读方式", width = 15)
    private String studyWay;

    @TableDef(column = 8)
    @ExcelDef(column = "8", columnName = "银行卡号", width = 15)
    private String bankCardNo;

    @TableDef(column = 9)
    @ExcelDef(column = "9", columnName = "月发放标准", width = 15)
    private String payStandards;

    @TableDef(column = 10)
    @ExcelDef(column = "10", columnName = "联系电话", width = 15)
    private String phone;

    @TableDef(column = 11)
    @ExcelDef(column = "11", columnName = "学籍状态", width = 15)
    private String educationStatus;

}
