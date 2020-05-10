package cn.com.cloudstar.rightcloud.framework.common.util.stream;

import com.google.common.io.CharStreams;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Hong.Wu
 * @date: 5:07 2020/05/04
 */
public class StreamUtil {

    public static void main(String[] args) throws IOException {
        InputStream is = null;
        String text1 = IOUtils.toString(is, "UTF-8");
        String text2 = CharStreams.toString(new InputStreamReader(is, "UTF-8"));
        byte[] text3 = Files.readAllBytes(Paths.get("d:/sample.txt"));
    }

    public static String convertStreamToString(InputStream inputStream, boolean close) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i;
        try {
            while ((i = inputStream.read()) != -1) {
                baos.write(i);
            }
            return baos.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                if (close) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    /**
     * inputStream 装换为 string 1.(StringBuffer+InputStreamReader+BufferedReader )，不推荐使用，BufferedReader在readLine（）
     * 时会在读取到换行符时直接返回，然后读取下一行，会丢失换行符（what fk is that？）
     *
     * @param inputStream
     */
    public static String convertStreamToString2(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }


}
