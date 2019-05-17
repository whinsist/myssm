/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved.
 */

package cn.com.cloudstar.rightcloud.framework.common.util.excel;

import cn.com.cloudstar.rightcloud.framework.common.util.ClassLoaderUtil;
import com.google.common.base.Throwables;
import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type ExcelDownloadUtil.
 * <p>
 * Created on 2018/1/25
 *
 * @author ChaoHong.Mao
 */
public class ExcelDownloadUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelDownloadUtil.class);

    /**
     * Export excel.
     *
     * @param xlsTemplateFileName the template name
     * @param exportFileName      the export file name
     * @param beans               the beans
     */
    public static void exportExcel(HttpServletRequest request, HttpServletResponse response, String xlsTemplateFileName,
                                   String exportFileName, Map<String, Object> beans, Class<?> callingClazz) {
        response.setHeader("Content-Disposition", "attachment;filename=" + exportFileName);
        response.setContentType("application/vnd.xls;charset=utf-8");
        try (InputStream is = ClassLoaderUtil
                .getResourceAsStream(xlsTemplateFileName, callingClazz); OutputStream fos = response
                .getOutputStream()) {

            logger.debug("get excel template file path is---> {}", xlsTemplateFileName);
            logger.debug("get exportFileName  is---> {}", exportFileName);

            XLSTransformer transformer = new XLSTransformer();
            // generate the excel workbook according to the template and
            // parameters
            Workbook workbook = transformer.transformXLS(is, beans);


            // output the generated excel file
            workbook.write(fos);

        } catch (Exception e) {
            logger.error(Throwables.getStackTraceAsString(e));
        }
    }

    /**
     * @param inputXLS            文件输入流
     * @param xmlTemplateFilePath 模板相对路径  template/xxx.xml
     */
    public static <T> List<T> getExcelData(InputStream inputXLS, String xmlTemplateFilePath, List<T> list,
                                           Class<?> callingClazz) {
        try (InputStream inputXML = new BufferedInputStream(ClassLoaderUtil.getResourceAsStream(xmlTemplateFilePath,
                callingClazz
        ))) {

            XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
            Map<String, Object> beans = new HashMap<>();
            beans.put("list", list);
            XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
            if (readStatus.isStatusOK()) {
                logger.debug("------------------------>> read excel successful, excel data size : {}", list.size());
            }
        } catch (Exception e) {
            logger.error(Throwables.getStackTraceAsString(e));
        }
        return list;
    }
}
