package cn.com.cloudstar.rightcloud.framework.test.t004dtt;


import cn.com.cloudstar.rightcloud.framework.common.util.DBUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hong.Wu
 * @date: 13:29 2020/11/20
 */
public class Test {
    public static void main(String[] args) throws Exception {

        //show databases;
        String path = "D:\\work_cloudstar\\workspace_kg\\myssm\\myssm-backend\\src\\main\\java\\cn\\com\\cloudstar\\rightcloud\\framework\\test\\t004dtt\\tables.txt";
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = "";
        List<String> list = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            list.add(line);
        }
        System.out.println("#获取数据库size : " + list.size());


        System.out.println("#删除数据库：");
        list.forEach(dbname -> {
            System.out.println(String.format("DROP DATABASE IF EXISTS  %s;", dbname));
        });

        System.out.println("#创建数据库：");
        list.forEach(dbname -> {
            System.out.println(String.format("CREATE DATABASE IF NOT EXISTS %s DEFAULT CHARSET utf8mb4;", dbname));
        });

        System.out.println("#导入数据库：");
        list.forEach(dbname -> {
            System.out.println(String.format("mysql -uroot -p123456 %s < ./baofengsrm146/%s.sql;", dbname, dbname));
        });

        // 导出
        System.out.println("#导出数据库：");
        list.forEach(dbname -> {
            System.out.println(String.format("mysqldump -uroot -pmysql@BFSRM146 %s -P3306 > ./baofengsrm146/%s.sql;", dbname, dbname));
        });


    }
}