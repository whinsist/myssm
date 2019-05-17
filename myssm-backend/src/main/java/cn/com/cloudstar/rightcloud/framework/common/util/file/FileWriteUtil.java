package cn.com.cloudstar.rightcloud.framework.common.util.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Hong.Wu
 * @date: 14:38 2018/11/14
 */
public class FileWriteUtil {

    public static void writeStringToFile(String filePath, String content) {
        // 追加模式
        writeStringToFile(filePath, content, "UTF-8",true);
    }

    public static void writeStringToFile(String path, String content, String encoding, boolean append) {
        FileOutputStream fos = null;
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            fos = new FileOutputStream(file, append);
            fos.write(content.getBytes(encoding));
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




    public static void main(String[] args) {
        String path = "e:/temp/test.txt";
        FileWriteUtil.writeStringToFile(path, "作者：cxy");

    }

}
