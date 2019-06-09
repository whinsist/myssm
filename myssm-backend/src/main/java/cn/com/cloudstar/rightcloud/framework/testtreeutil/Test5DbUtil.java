package cn.com.cloudstar.rightcloud.framework.testtreeutil;

import java.util.List;

import lombok.Data;

import cn.com.cloudstar.rightcloud.framework.common.util.DBUtil;

public class Test5DbUtil {

    public static void main(String[] args) {
        String sql = "select * from sys_m_user where user_sid = ?";

        List<TestSysUser> maps = DBUtil.queryBeanList(sql, TestSysUser.class, 100);

        for (TestSysUser vo : maps) {
            System.out.println(vo);
        }
    }

    @Data
    public static class TestSysUser{
        private Long user_sid;
        private String account;

    }



}