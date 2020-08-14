package cn.com.cloudstar.rightcloud.framework.test.t003util;

import com.alibaba.fastjson.JSON;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;

import cn.com.cloudstar.rightcloud.framework.common.util.StringUtil;
import cn.com.cloudstar.rightcloud.framework.test.t001study.jdbctest.JdbcUtils;
import cn.com.cloudstar.rightcloud.framework.common.util.DBUtil;

public class Test5DbUtil {

    public static void main(String[] args) throws Exception {

//        testApacheDbUtil();
//        testLongBlob();

        testGenerEnvAttrJson();


    }

    private static void testGenerEnvAttrJson() {
        String sql = "SELECT * FROM cloud_env_attr_config where cloud_env_type = ?";
        List<TestCloudEnvAttr> beanList = DBUtil.queryBeanList(sql, TestCloudEnvAttr.class, "KSyun");


        System.out.println(JSON.toJSONString(beanList));

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

    @Getter
    public static class TestCloudEnvAttr {
        private String cloud_env_type;
        private String attr_name;
        private String attr_key;
        private String data_type;
        private String display_type;
        private String unit;
        private String value_domain;
        private String value_increment;
        private String value_rule;
        private String sort_rank;
        private String description;
        private String status;
        private String created_by;
        private String created_dt;
        private String updated_by;
        private String updated_dt;
        private String version;
        private String information;

        public void setCloud_env_type(String cloud_env_type) {
            this.cloud_env_type = StringUtil.nullToEmpty(cloud_env_type);
        }

        public void setAttr_name(String attr_name) {
            this.attr_name = StringUtil.nullToEmpty(attr_name);
        }

        public void setAttr_key(String attr_key) {
            this.attr_key = StringUtil.nullToEmpty(attr_key);
        }

        public void setData_type(String data_type) {
            this.data_type = StringUtil.nullToEmpty(data_type);
        }

        public void setDisplay_type(String display_type) {
            this.display_type = StringUtil.nullToEmpty(display_type);
        }

        public void setUnit(String unit) {
            this.unit = StringUtil.nullToEmpty(unit);
        }

        public void setValue_domain(String value_domain) {
            this.value_domain = StringUtil.nullToEmpty(value_domain);
        }

        public void setValue_increment(String value_increment) {
            this.value_increment = StringUtil.nullToEmpty(value_increment);
        }

        public void setValue_rule(String value_rule) {
            this.value_rule = StringUtil.nullToEmpty(value_rule);
        }

        public void setSort_rank(String sort_rank) {
            this.sort_rank = StringUtil.nullToEmpty(sort_rank);
        }

        public void setDescription(String description) {
            this.description = StringUtil.nullToEmpty(description);
        }

        public void setStatus(String status) {
            this.status = StringUtil.nullToEmpty(status);
        }

        public void setCreated_by(String created_by) {
            this.created_by = StringUtil.nullToEmpty(created_by);
        }

        public void setCreated_dt(String created_dt) {
            this.created_dt = StringUtil.nullToEmpty(attr_name);
        }

        public void setUpdated_by(String updated_by) {
            this.updated_by = StringUtil.nullToEmpty(updated_by);
        }

        public void setUpdated_dt(String updated_dt) {
            this.updated_dt = StringUtil.nullToEmpty(updated_dt);
        }

        public void setVersion(String version) {
            this.version = StringUtil.nullToEmpty(version);
        }

        public void setInformation(String information) {
            this.information = StringUtil.nullToEmpty(information);
        }
    }

}