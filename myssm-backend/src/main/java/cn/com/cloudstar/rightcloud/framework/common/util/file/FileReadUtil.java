package cn.com.cloudstar.rightcloud.framework.common.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author Hong.Wu
 * @date: 13:26 2019/03/03
 */
public class FileReadUtil {


    public static String readFile(String filePath) {
        StringBuffer buffer = new StringBuffer();
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            byte[] temp = new byte[2048];
            int len ;
            while ((len = is.read(temp)) != -1) {
                String s = new String(temp, 0, len);
                buffer.append(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer.toString();
    }

    public static String readFile(URL url) {
        return readFileByUrlStream(url, null);
    }

    public static String readFile4Shell(URL url) {
        return readFileByUrlStream(url, "\n");
    }

    private static String readFileByUrlStream(URL url, String lineAddSign) {
        InputStream is = null;
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();
        try {
            is = url.openStream();
            reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + (lineAddSign != null ? lineAddSign : ""));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer.toString();
    }

    /**
     * 一次性把文件读完，不一定适合读所有的文件 如xml
     * @param filePath
     * @return
     */
    public static String readFileOnce(String filePath) {
        File file = new File(filePath);
        Long fileLen = file.length();
        FileInputStream in = null;
        byte[] tempByte = new byte[fileLen.intValue()];
        try {
            in = new FileInputStream(file);
            in.read(tempByte);
            return new String(tempByte, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        String path = "e:/temp/XmlUtils.xml";

        System.out.println(FileReadUtil.readFile(path));
        System.out.println(FileReadUtil.readFileOnce(path));

        URL url = Thread.currentThread().getContextClassLoader().getResource("shell/test.sh");
        System.out.println(FileReadUtil.readFile(url));
        System.out.println(FileReadUtil.readFile4Shell(url));



    }



}

