package cn.com.cloudstar.rightcloud.system.vo.excel;

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
    @ExcelDef(column = "0", columnName = "userSid", width = 15)
    private Long userSid;

    @TableDef(column = 1)
    @ExcelDef(column = "1", columnName = "帐号", width = 15)
    private String account;

    @TableDef(column = 2)
    @ExcelDef(column = "2", columnName = "用户名", width = 15)
    private String realName;


    @TableDef(column = 3)
    @ExcelDef(column = "3", columnName = "联系手机", width = 15)
    private String mobile;

    @TableDef(column = 4, keyValue = {"a", "b"})
    @ExcelDef(column = "4", columnName = "xxx", width = 15)
    private String xxx;
}
