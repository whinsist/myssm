package cn.com.cloudstar.rightcloud.framework.common.util.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author Hong.Wu
 * @date: 10:49 2019/03/19
 */
public class InputStreamTest {
    public static void main(String[] args) throws Exception {

        // input转为字节数组  标准
//        InputStream input = new FileInputStream(new File("e:/temp/1.txt"));
        InputStream input = new FileInputStream(new File("e:/temp/1.jpg"));
        byte[] b = new byte[1024];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int len;
        while ((len = input.read(b)) != -1){
            bos.write(b, 0, len);
        }
        System.out.println(new String(bos.toByteArray()));
        bos.close();
        input.close();
    }
}
