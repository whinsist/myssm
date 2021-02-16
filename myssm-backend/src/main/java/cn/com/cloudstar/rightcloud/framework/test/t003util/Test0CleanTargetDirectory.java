package cn.com.cloudstar.rightcloud.framework.test.t003util;

import cn.hutool.core.io.FileUtil;

import java.io.File;

/**
 * @author Hong.Wu
 * @date: 15:26 2021/02/16
 */
public class Test0CleanTargetDirectory {
    public static void main(String[] args) {

        recursionFile("D:\\works");
    }

    public static void recursionFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            for (File fileItem : files) {
                if (fileItem.isDirectory()) {
                    //System.out.println("文件夹:" + fileItem.getAbsolutePath() + "  " + fileItem.getName());
                    boolean deleteFlag = "target".equals(fileItem.getName()) && fileItem.getAbsolutePath().endsWith("\\target");
                    if (deleteFlag) {
                        FileUtil.del(fileItem.getAbsolutePath());
                        System.out.println("删除文件夹：" + fileItem.getAbsolutePath());
                    } else {
                        recursionFile(fileItem.getAbsolutePath());
                    }
                } else {
                    //System.out.println("文件:" + fileItem.getAbsolutePath());
                }
            }
        } else {
            //System.out.println("文件不存在!");
        }
    }
}
