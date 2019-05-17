/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 共通文件操作Util
 */
public final class FileUtil {

    public final static String FILE_PATH_PROPER_KEY = "file.upload.path";

	/*@Autowired
    private static AttachmentService attachmentService = SpringContextHolder.getBean("attachmentServiceImpl");*/
    /**
     * Class文件扩展名
     */
    public static final String CLASS_EXT = ".class";
    /**
     * Jar文件扩展名
     */
    public static final String JAR_FILE_EXT = ".jar";
    /**
     * 在Jar中的路径jar的扩展名形式
     */
    public static final String JAR_PATH_EXT = ".jar!";
    /**
     * 当Path为文件形式时, path会加入一个表示文件的前缀
     */
    public static final String PATH_FILE_PRE = "file:";
    /**
     * The Unix separator character.
     */
    private static final char UNIX_SEPARATOR = '/';
    /**
     * The Windows separator character.
     */
    private static final char WINDOWS_SEPARATOR = '\\';
    private static Logger log = LoggerFactory.getLogger(FileUtil.class);
    private static String filenameTemp;

    /**
     * 构造方法
     */
    private FileUtil() {

    }

    /**
     * 从系统Classpath中读取文件
     *
     * @param filePath 文件相对路径
     * @return StringBuffer
     */
    public static StringBuffer readFileByClasspath(String filePath) {

        StringBuffer sb = new StringBuffer();
        try {
            // 返回读取指定资源的输入流
            InputStream is = FileUtil.class.getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String s = "";
            try {
                while ((s = br.readLine()) != null) {
                    sb.append(s);
                }
            } catch (IOException e) {
                log.error("文件读取失败!\n" + ExceptionUtils.getFullStackTrace(e));
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return sb;
    }

    /**
     * 根据文件地址读取文件
     *
     * @param filePath 文件位置
     * @return StringBuffer
     */
    public static StringBuffer readFileByFilePath(String filePath) {
        BufferedReader reader = null;
        StringBuffer sb = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(filePath));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
            }
            log.info("读取文件:" + filePath);
            log.info("文件内容为:" + sb.toString());
        } catch (FileNotFoundException e) {
            log.error("文件未找到!\n" + ExceptionUtils.getFullStackTrace(e));
        } catch (IOException e) {
            log.error("文件读取失败!\n" + ExceptionUtils.getFullStackTrace(e));
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb;
    }

    /**
     * 文件下载
     *
     * @param rsp      HttpServletResponse对象
     * @param filePath 文件在服务器上的路径
     * @param fileName 用户下载时,保存的文件名
     */
    public static InputStream fileDownLoad(HttpServletResponse rsp, String filePath, String fileName) {
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
            rsp.addHeader("File-Name", new String(fileName.getBytes("gb2312"), "ISO-8859-1"));
//            rsp.setContentType("application/octet-stream;charset=GBK");
            rsp.setContentType("application/x-download");

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
        } catch (FileNotFoundException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            log.debug(e.getMessage());
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
     * 在cxf中文件下载
     *
     * @param filePath 下载文件的地址
     * @param fileName 下载文件名称
     */
    public static ResponseBuilder fileDownLoadInCxf(String filePath, String fileName) {

        File file = new File(filePath);
        ResponseBuilder response = Response.ok(file);
        response.header("Content-Disposition", "attachment; filename=" + fileName + "");

        return response;
    }

    /**
     * 文件下载
     *
     * @param rsp     HttpServletResponse对象
     * @param iStream 文件在服务器上的路径
     */
    public static void ImageOutput(HttpServletResponse rsp, InputStream iStream) {
        InputStream fis = null;
        OutputStream fos = null;

        try {
            // 读取临时文件下载
            fis = new BufferedInputStream(iStream);
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
            //rsp.addHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes("gb2312"), "ISO-8859-1"));
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
        } catch (FileNotFoundException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            log.debug(e.getMessage());
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
    }

    public static Map<String, Integer> getImageSizeByBufferedImage(InputStream inputStream) throws IOException {
        Map<String, Integer> imageInfo = new HashMap<String, Integer>();
        BufferedImage sourceImg = javax.imageio.ImageIO.read(inputStream);
        imageInfo.put("imageWidth", sourceImg.getWidth());
        imageInfo.put("imageHeight", sourceImg.getHeight());
        return imageInfo;
    }


    /**
     * 导出文件
     */
    @SuppressWarnings("unused")
    public static void writeToFile(InputStream ins, String path) {
        try {
            OutputStream out = new FileOutputStream(new File(path));
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = ins.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/*public static boolean creatTxtFile(String name,Attachments attach) throws IOException {
        boolean flag = false;
		WebUtil.prepareInsertParams(attach);
		int result = attachmentService.insertSelective(attach);
		if(1==result){
			String path = com.hptsic.cloud.common.utils.PropertiesUtil.getProperty("upload.path");
			String uploadPath = path+attach.getAttachmentUrl();
			File filename = new File(uploadPath);
			if (!filename.getParentFile().exists()) {
				filename.getParentFile().mkdirs();
			}
			 OutputStream out = new FileOutputStream(new File(uploadPath));  
			 int read = 0; 
			 byte[] bytes = new byte[1024];
			 out.write(bytes, 0, read);
			 out.flush();  
		     out.close(); 
		     flag = true;
		}
		return flag;
	}*/

    public static boolean writeTxtFile(String content, File fileName) throws Exception {
        RandomAccessFile mm = null;
        boolean flag = false;
        FileOutputStream o = null;
        try {
            o = new FileOutputStream(fileName);
            o.write(content.getBytes("GBK"));
            o.close();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mm != null) {
                mm.close();
            }
        }
        return flag;
    }

    /**
     * 删除一个文件
     *
     * @param filePath 文件路径
     */
    public static boolean deleteFile(String filePath) {
        File f = new File(filePath);
        return f.isFile() && f.exists() && f.delete();
    }

    /**
     * directly download result set to excel file
     *
     * @param fileName  -- filename
     * @param sheetName -- sheetName
     * @param titles    // excel中的标题列名{实体字段名，表头，表头宽度,值对象Map}
     *                  String[][] titles = new String[][] {
     *                  {"happendDate","发生日期","3000"},
     *                  {"className","班级","3000",classMap<String,Object>},
     *                  {"evalDesc","发现问题","5000"},
     *                  {"teacherScore","班主任及分数","4000"},
     *                  {"stuScore","学生及分数","4000"},
     *                  {"recordBy","记录人","3000"},
     *                  {"recordDate","记录日期","3000"}
     *                  };
     * @param rsList    -- Object list to be download
     * @param rsp       -- HttpServletResponse
     */
    public static String downloadCommonExcelFile(String fileName, String sheetName, Object[][] titles, List rsList,
                                                 HttpServletResponse rsp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 生成数据到excel
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet(sheetName);
        Row titleRow = sheet.createRow(0);
        // 添加标题栏
        for (int i = 0; i < titles.length; i++) {
            if (titles[i][0] == "") {
                continue;
            }
            Cell c = titleRow.createCell(i);
            c.setCellValue((String) titles[i][1]);
            sheet.setColumnWidth(i, Integer.parseInt((String) titles[i][2]));
        }

        // 添加内容
        for (int i = 0; i < rsList.size(); i++) {
            Object object = rsList.get(i);
            Row row = sheet.createRow(i + 1);
            // 处理每行的每一列的元素
            for (int j = 0; j < titles.length; j++) {
                if (titles[j][0] == "") {
                    continue;
                }
                try {
                    Object obj = PropertyUtils.getProperty(object, (String) titles[j][0]);
                    Class propertyType = PropertyUtils.getPropertyType(object, (String) titles[j][0]);

                    if (obj != null) {
                        //check value map, and convert the obj value to map value
                        if (titles[j].length >= 4 && titles[j][3] != null) {
                            //need to get value from the map
                            Map valueMap = (Map) titles[j][3];
                            Object oldValue = obj;
                            obj = valueMap.get(obj.toString());
                            if (obj == null) {
                                log.warn("Cannot get value from value map by key :" + oldValue);
                                obj = "";
                            }
                        }

                        //set value to excel
                        Cell c1 = row.createCell(j);
                        if (propertyType == null) {
                            c1.setCellValue(obj.toString());
                            continue;
                        }
                        String fType = propertyType.toString();
                        if (fType.equals("class java.util.Date") || fType.equals("class java.sql.Date")) {
                            java.util.Date cDate = (java.util.Date) obj;
                            c1.setCellValue(sdf.format(cDate));
                        } else if (fType.equals("class java.math.BigDecimal") ||
                                fType.equals("class java.lang.Double") || fType.equals("class java.lang.Integer") ||
                                fType.equals("double")) {
                            try {
                                Double ss = Double.parseDouble(obj.toString());
                                c1.setCellType(Cell.CELL_TYPE_NUMERIC);
                                c1.setCellValue(Double.parseDouble(obj.toString()));
                            } catch (NumberFormatException ee) {
                                c1.setCellType(Cell.CELL_TYPE_STRING);
                                c1.setCellValue(obj.toString());
                            }
                        } else {
                            c1.setCellValue(obj.toString());
                        }
                    } else {
                        if (titles[j].length >= 5 && titles[j][4] != null) {
                            obj = titles[j][4];
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        // 写入文件到本地临时路径
        try {
            // 清空response
            rsp.reset();
            // 设置response的Header
            String formatedName = new String(fileName.getBytes("gb2312"), "ISO-8859-1");
            rsp.addHeader("Content-Disposition", "attachment;filename=" + formatedName);
            rsp.addHeader("File-Name", formatedName);
            // rep.addHeader("Content-Length", "" + savedPath.length());
            rsp.setContentType("application/octet-stream;charset=GBK");
            BufferedOutputStream out = new BufferedOutputStream(rsp.getOutputStream());
            wb.write(out);
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }

    /**
     * 创建File对象
     *
     * @param parent 父目录
     * @param path   文件路径
     * @return File
     */
    public static File file(String parent, String path) {
        if (Strings.isNullOrEmpty(path)) {
            throw new NullPointerException("File path is blank!");
        }
        return new File(parent, path);
    }

    /**
     * 创建File对象
     *
     * @param parent 父文件对象
     * @param path   文件路径
     * @return File
     */
    public static File file(File parent, String path) {
        if (Strings.isNullOrEmpty(path)) {
            throw new NullPointerException("File path is blank!");
        }
        return new File(parent, path);
    }

    /**
     * 创建File对象
     *
     * @param uri 文件URI
     * @return File
     */
    public static File file(URI uri) {
        if (uri == null) {
            throw new NullPointerException("File uri is null!");
        }
        return new File(uri);
    }

    /**
     * 判断文件是否存在，如果path为null，则返回false
     *
     * @param path 文件路径
     * @return 如果存在返回true
     */
    public static boolean exist(String path) {
        return path != null && file(path).exists();
    }

    /**
     * 创建File对象，自动识别相对或绝对路径，相对路径将自动从ClassPath下寻找
     *
     * @param path 文件路径
     * @return File
     */
    public static File file(String path) {
        if (Strings.isNullOrEmpty(path)) {
            throw new NullPointerException("File path is blank!");
        }
        return new File(getAbsolutePath(path));
    }

    /**
     * 获取绝对路径，相对于ClassPath的目录<br>
     * <p>
     * 如果给定就是绝对路径，则返回原路径，原路径把所有\替换为/<br>
     * <p>
     * 兼容Spring风格的路径表示，例如：classpath:config/example.setting也会被识别后转换
     *
     * @param path 相对路径
     * @return 绝对路径
     */
    public static String getAbsolutePath(String path) {
        if (path == null) {
            path = StringUtil.EMPTY;
        } else {
            path = normalize(path);

            if (StringUtil.C_SLASH == path.charAt(0) || path.matches("^[a-zA-Z]:/.*")) {
                // 给定的路径已经是绝对路径了

                return path;
            }
        }

        //兼容Spring风格的ClassPath路径，去除前缀，不区分大小写

        path = StringUtil.removePrefixIgnoreCase(path, "classpath:");
        path = StringUtil.removePrefix(path, StringUtil.SLASH);

        // 相对于ClassPath路径

        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        URL url = classLoader.getResource(path);
//        String reultPath = url != null ? url.getPath() : Cla + path;
        // return StringUtil.removePrefix(reultPath, PATH_FILE_PRE);

        return url.getPath();
    }

    /**
     * 修复路径<br>
     * <p>
     * 1. 统一用 / <br>
     * <p>
     * 2. 多个 / 转换为一个
     * <p>
     * 3. 去除两边空格
     * <p>
     * 4. .. 和 . 转换为绝对路径
     * <p>
     * 5. 去掉前缀，例如file:
     *
     * @param path 原路径
     * @return 修复后的路径
     */
    public static String normalize(String path) {
        if (path == null) {
            return null;
        }
        String pathToUse = path.replaceAll("[/\\\\]{1,}", "/").trim();

        int prefixIndex = pathToUse.indexOf(StringUtil.COLON);
        String prefix = "";
        if (prefixIndex != -1) {
            prefix = pathToUse.substring(0, prefixIndex + 1);
            if (prefix.contains("/")) {
                prefix = "";
            } else {
                pathToUse = pathToUse.substring(prefixIndex + 1);
            }
        }
        if (pathToUse.startsWith(StringUtil.SLASH)) {
            prefix = prefix + StringUtil.SLASH;
            pathToUse = pathToUse.substring(1);
        }

        List<String> pathList = StringUtil.split(pathToUse, StringUtil.C_SLASH);
        List<String> pathElements = new LinkedList<String>();
        int tops = 0;

        for (int i = pathList.size() - 1; i >= 0; i--) {
            String element = pathList.get(i);
            switch (element) {
                case StringUtil.DOT:
                    //当前目录，丢弃

                    break;
                case StringUtil.DOUBLE_DOT:
                    tops++;
                    break;
                default:
                    if (tops > 0) {
                        // Merging path element with element corresponding to top path.
                        tops--;
                    } else {
                        // Normal path element found.
                        pathElements.add(0, element);
                    }
                    break;
            }
        }

        // Remaining top paths need to be retained.

        for (int i = 0; i < tops; i++) {
            pathElements.add(0, StringUtil.DOUBLE_DOT);
        }

        return prefix + Joiner.on(StringUtil.SLASH).join(pathElements);
    }

    /**
     * 判断文件是否存在，如果file为null，则返回false
     *
     * @param file 文件
     * @return 如果存在返回true
     */
    public static boolean exist(File file) {
        return file != null && file.exists();
    }

    /**
     * 是否存在匹配文件
     *
     * @param directory 文件夹路径
     * @param regexp    文件夹中所包含文件名的正则表达式
     * @return 如果存在匹配文件返回true
     */
    public static boolean exist(String directory, String regexp) {
        File file = new File(directory);
        if (!file.exists()) {
            return false;
        }

        String[] fileList = file.list();
        if (fileList == null) {
            return false;
        }

        for (String fileName : fileList) {
            if (fileName.matches(regexp)) {
                return true;
            }

        }
        return false;
    }

    /**
     * 获取标准的绝对路径
     *
     * @param file 文件
     * @return 绝对路径
     */
    public static String getAbsolutePath(File file) {
        if (file == null) {
            return null;
        }

        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            return file.getAbsolutePath();
        }
    }

    /**
     * 获取绝对路径<br/>
     * <p>
     * 此方法不会判定给定路径是否有效（文件或目录存在）
     *
     * @param path      相对路径
     * @param baseClass 相对路径所相对的类
     * @return 绝对路径
     */
    public static String getAbsolutePath(String path, Class<?> baseClass) {
        if (path == null) {
            path = StringUtil.EMPTY;
        }
        if (baseClass == null) {
            return getAbsolutePath(path);
        }
        // return baseClass.getResource(path).getPath();

        String p = baseClass.getResource(path).getPath();
        return p.replace(PATH_FILE_PRE, "");
    }

    /**
     * 判断是否为目录，如果path为null，则返回false
     *
     * @param path 文件路径
     * @return 如果为目录true
     */
    public static boolean isDirectory(String path) {
        return path != null && file(path).isDirectory();
    }

    /**
     * 判断是否为目录，如果file为null，则返回false
     *
     * @param file 文件
     * @return 如果为目录true
     */
    public static boolean isDirectory(File file) {
        return file != null && file.isDirectory();
    }

    /**
     * 判断是否为文件，如果path为null，则返回false
     *
     * @param path 文件路径
     * @return 如果为文件true
     */
    public static boolean isFile(String path) {
        return path != null && file(path).isFile();
    }

    /**
     * 判断是否为文件，如果file为null，则返回false
     *
     * @param file 文件
     * @return 如果为文件true
     */
    public static boolean isFile(File file) {
        return file != null && file.isFile();
    }

    /**
     * 检查两个文件是否是同一个文件
     *
     * @param file1 文件1
     * @param file2 文件2
     * @return 是否相同
     */
    public static boolean equals(File file1, File file2) {
        try {
            file1 = file1.getCanonicalFile();
            file2 = file2.getCanonicalFile();
        } catch (IOException ignore) {
            return false;
        }
        return file1.equals(file2);
    }

    // -------------------------------------------------------------------------------------------- name start

    /**
     * 返回主文件名
     *
     * @param file 文件
     * @return 主文件名
     */
    public static String mainName(File file) {
        if (file.isDirectory()) {
            return file.getName();
        }
        return mainName(file.getName());
    }

    /**
     * 返回主文件名
     *
     * @param fileName 完整文件名
     * @return 主文件名
     */
    public static String mainName(String fileName) {
        if (Strings.isNullOrEmpty(fileName) || !fileName.contains(StringUtil.DOT)) {
            return fileName;
        }
        return fileName.substring(0, fileName.lastIndexOf(StringUtil.DOT));
    }

    /**
     * 获取文件扩展名，扩展名不带“.”
     *
     * @param file 文件
     * @return 扩展名
     */
    public static String extName(File file) {
        if (null == file) {
            return null;
        }
        if (file.isDirectory()) {
            return null;
        }
        return extName(file.getName());
    }

    /**
     * 获得文件的扩展名，扩展名不带“.”
     *
     * @param fileName 文件名
     * @return 扩展名
     */
    public static String extName(String fileName) {
        if (fileName == null) {
            return null;
        }
        int index = fileName.lastIndexOf(StringUtil.DOT);
        if (index == -1) {
            return StringUtil.EMPTY;
        } else {
            String ext = fileName.substring(index + 1);
            // 扩展名中不能包含路径相关的符号

            return (ext.contains(String.valueOf(UNIX_SEPARATOR)) ||
                    ext.contains(String.valueOf(WINDOWS_SEPARATOR))) ? StringUtil.EMPTY : ext;
        }
    }

    /**
     * 判断文件路径是否有指定后缀，忽略大小写<br>
     * <p>
     * 常用语判断扩展名
     *
     * @param file   文件或目录
     * @param suffix 后缀
     * @return 是否有指定后缀
     */
    public static boolean pathEndsWith(File file, String suffix) {
        return file.getPath().toLowerCase().endsWith(suffix);
    }

    // -------------------------------------------------------------------------------------------- name end

}

