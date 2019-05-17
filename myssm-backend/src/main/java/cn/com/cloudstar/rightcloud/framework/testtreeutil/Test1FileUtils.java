package cn.com.cloudstar.rightcloud.framework.testtreeutil;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Hong.Wu
 * @date: 9:24 2019/03/19
 */
public class Test1FileUtils {

    public static void main(String[] args) throws IOException {
        File file = new File("e:/temp/byte.txt");
        byte[] data = "123东风风神".getBytes();
        FileUtils.writeByteArrayToFile(file, data);

        InputStream is = new FileInputStream(new File("e:/temp/byte.txt"));
        FileUtils.copyInputStreamToFile(is, new File("e:/temp/byte2.txt"));





//        FileUtils.write(file, parentNode.getContent(), "UTF-8");
//        FileUtils.writeStringToFile(new File(transSrcFilePath), scriptContent);
//
//        FileUtils.readFileToString
//
//        FileUtils.forceDelete(file);
//        FileUtils.deleteDirectory(new File(filePath));

    }
}
