package cn.com.cloudstar.rightcloud.framework.testtreeutil;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import lombok.Data;

import cn.com.cloudstar.rightcloud.framework.common.util.DBUtil;

public class Test5DbUtil {

    public static void main(String[] args) {
        String sql = "select * from sys_m_user;";


        List<JobGroupName> maps = DBUtil.queryBeanList(sql, JobGroupName.class, null);


        for (JobGroupName vo : maps) {
            System.out.println(vo);

        }
    }

    @Data
    public static class JobGroupName{
        private Long user_sid;
        private String account;

    }



}