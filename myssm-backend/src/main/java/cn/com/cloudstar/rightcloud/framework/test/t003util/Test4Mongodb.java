package cn.com.cloudstar.rightcloud.framework.test.t003util;

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

public class Test4Mongodb {

    public static void main(String[] args) {
        testMy();

//        testOk();
    }

    private static void testMy() {

        // 连接到 mongodb 服务

        ServerAddress serverAddress = new ServerAddress("10.68.6.16", 27017);
        List<ServerAddress> addrs = new ArrayList<>();
        addrs.add(serverAddress);

        // MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
        MongoCredential credential = MongoCredential.createScramSha1Credential("mongoadmin", "app", "123456".toCharArray());
        List<MongoCredential> credentials = new ArrayList<>();
        credentials.add(credential);
        // 通过连接认证获取MongoDB连接
        MongoClient mongoClient = new MongoClient(addrs, credentials);

//        MongoClient mongoClient = new MongoClient("192.168.93.135", 27017);
        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("app");
        // 集合创建
//        mongoDatabase.createCollection("test");
        // 集合 test 选择
        MongoCollection<Document> collection = mongoDatabase.getCollection("test");
        Document document = new Document("title", "MongoDB").append("description", "database").append("likes", 100).append("by", "Fly");
        List<Document> documents = new ArrayList<Document>();
        documents.add(document);
        collection.insertMany(documents);
        System.out.println("文档插入成功");



//        ServerAddress serverAddress = new ServerAddress("192.168.93.135", 27017);
//        List<ServerAddress> addrs = new ArrayList<>();
//        addrs.add(serverAddress);
//
//        //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
//        MongoCredential credential = MongoCredential.createScramSha1Credential("admin", "admin", "admin123456".toCharArray());
//        List<MongoCredential> credentials = new ArrayList<>();
//        credentials.add(credential);
//        MongoClient mongoClient = new MongoClient(addrs, credentials);
//        //连接到数据库
//        MongoDatabase mongoDatabase = mongoClient.getDatabase("admin");
//
//        MongoCollection<Document> collection = mongoDatabase.getCollection("test");
//
//        FindIterable<Document> findIterable = collection.find();
//        MongoCursor<Document> mongoCursor = findIterable.iterator();
//        while (mongoCursor.hasNext()) {
//            String actionName = mongoCursor.next().getString("action_name");
//            System.out.println("--------" + actionName);
//        }

    }

    public static void testOk() throws Exception {

        //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址
        //ServerAddress()两个参数分别为 服务器地址 和 端口
        ServerAddress serverAddress = new ServerAddress("192.168.93.132", 27017);
//            ServerAddress serverAddress = new ServerAddress("10.68.7.90",27017);
//            ServerAddress serverAddress = new ServerAddress("vue-project.rc.com",48583);
        List<ServerAddress> addrs = new ArrayList<>();
        addrs.add(serverAddress);

        //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
        MongoCredential credential = MongoCredential.createScramSha1Credential("rightcloud", "rightcloud", "H89lBgAg".toCharArray());
        List<MongoCredential> credentials = new ArrayList<>();
        credentials.add(credential);
        //通过连接认证获取MongoDB连接
        MongoClient mongoClient = new MongoClient(addrs, credentials);
        //连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("rightcloud");

        MongoCollection<Document> collection = mongoDatabase.getCollection("action_log");

        //检索所有文档
        // 1. 获取迭代器FindIterable<Document> 2. 获取游标MongoCursor<Document> 3. 通过游标遍历检索出的文档集合
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();


        // 导出 action_name为空的数据
        HashSet<String> set = new HashSet<>();
        while (mongoCursor.hasNext()) {
            Document document = mongoCursor.next();
            String actionName = document.getString("action_name");
            boolean isBlank = actionName == null || "".equals(actionName);
            if (isBlank) {
                set.add(document.getString("action_method"));
            }
            System.out.println("--------" + actionName);
        }

        for (String str : set) {
            System.out.println(str);
        }


        String[] xlsTitles = "actionMethod,描述".split(",");
        List<String> xlsList = new ArrayList<>();
        for (String str : set) {
            xlsList.add(str + "¿" + "");
        }
//        ExcelManager excelManager = new ExcelManagerImpl();
//        excelManager.exportExcel("actionMethod.xls", xlsTitles, xlsList, 1024, null);


        //加入查询条件
//            BasicDBObject query = new BasicDBObject();
        //时间区间查询 记住如果想根据这种形式进行时间的区间查询 ，存储的时候 记得把字段存成字符串，就按yyyy-MM-dd HH:mm:ss 格式来
//            query.put("times", new BasicDBObject("$gte", "2018-06-02 12:20:00").append("$lte","2018-07-04 10:02:46"));
//            //模糊查询
//            Pattern pattern = Pattern.compile("^.*王.*$", Pattern.CASE_INSENSITIVE);
//            query.put("userName", pattern);
//            //精确查询
//            query.put("id", "11");
        //skip 是分页查询，从第0条开始查10条数据。 Sorts是排序用的。有descending 和ascending
//            MongoCursor<Document> cursor = collection.find(query).sort(Sorts.orderBy(Sorts.descending("times"))).skip(0).limit(10).iterator();
//            MongoCursor<Document> cursor = collection.find(query).sort(Sorts.orderBy(Sorts.descending("times"))).skip(0).limit(10).iterator();
//            try {
//                while (cursor.hasNext()) {
//
//                    String s = cursor.next().toJson().toString();
//                    System.out.println(s);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                cursor.close();
//            }

    }


}