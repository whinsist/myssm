package cn.com.cloudstar.rightcloud.framework.testtreeutil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import lombok.Data;

import cn.com.cloudstar.rightcloud.framework.common.study.jdbctest.JdbcUtils;
import cn.com.cloudstar.rightcloud.framework.common.util.DBUtil;

public class Test5DbUtil {

    public static void main(String[] args) throws Exception {

//        testApacheDbUtil();
        testLongBlob();


    }


    private static void testLongBlob() throws Exception {
        /**
         CREATE TABLE person ( person_id INT auto_increment PRIMARY KEY,
         first_name CHAR ( 45 ) NOT NULL,
         second_name CHAR ( 45 ) NOT NULL,
         photo LONGBLOB )
         */

        File file = new File("E:/testfiles/1-yes.jpg");
        InputStream input = new BufferedInputStream(new FileInputStream(file));
        String sql = "insert  into person(first_name, second_name, photo) values(?, ?, ?)";
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "梁");
        ps.setString(2, "志明");
        ps.setBlob(3, input);
        ps.executeUpdate();
        System.out.println("增加成功！");

        PreparedStatement pstmt = connection.prepareStatement("select * from person");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            InputStream is = rs.getBinaryStream("photo");
            FileOutputStream fos = new FileOutputStream("e:/temp/photo" + rs.getInt("person_id") + ".jpg");
            byte[] buffer = new byte[4096];
            while ((is.read(buffer)) != -1) {
                fos.write(buffer);
            }
            fos.close();
        }
        System.out.println("生成附件成功！");

        JdbcUtils.free(rs, ps, connection);
    }

    private static void testApacheDbUtil() {
        String sql = "select * from sys_m_user where user_sid = ?";
        List<TestSysUser> beanList = DBUtil.queryBeanList(sql, TestSysUser.class, 100);
        for (TestSysUser vo : beanList) {
            System.out.println(vo);
        }
    }

    @Data
    public static class TestSysUser {

        private Long user_sid;
        private String account;
    }

}