package cn.com.cloudstar.rightcloud.framework.test.t001study.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import lombok.Data;

/**
 * @author Hong.Wu
 * @date: 12:38 2020/05/02
 */
public class JsonTest {

    public static void main(String[] args) throws IOException {
        String json = "{\"username\":\"tom\",\"company\":{\"companyName\":\"中华\",\"address\":\"北京\"},\"cars\":[\"奔驰\",\"宝马\"]}}";
        String arrayJson = "[{\"number\":64,\"result\":\"SUCCESS\"},{\"number\":65,\"result\":\"FAILURE\"},{\"number\":66,\"result\":\"ABORTED\"},{\"number\":67,\"result\":\"SUCCESS\"}]";

        // Json字符串转换成JsonNode对象
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(json);
        Iterator<String> keys = jsonNode.fieldNames();
        while (keys.hasNext()) {
            System.out.println("key键是:" + keys.next());
        }
        JsonNode path = jsonNode.path("username");
        JsonNode resultValue = jsonNode.findValue("username");
        JsonNode resultPath = jsonNode.findPath("username");

        // 1.jackson
        // 第1步：创建ObjectMapper对象。创建ObjectMapper对象。它是一个可重复使用的对象。
        String jsonString = "{\"name\":\"Mahesh\", \"age\":21, \"stus\":[{\"id\":1, \"stu_name\":\"test\"}]}";
        // 第2步：反序列化JSON到对象。
        JacksonFooBean jacksonFooBean = mapper.readValue(jsonString, JacksonFooBean.class);
        System.out.println(jacksonFooBean);
        // 第2步：序列化为json字符串
        String jsonStr = mapper.writeValueAsString(jacksonFooBean);

        //2.( fastjson)
        //从 Java 变量到 JSON 格式的编码过程如下
        JSONObject obj = new JSONObject();
        obj.put("string", "string");
        obj.put("int", 2);
        obj.put("boolean", true);
        obj.put("list", Arrays.asList(1, 2, 3));
        obj.put("null", null);
        System.out.println(JSON.toJSONString(obj));
        // 从 JSON 对象到 Java 变量的解码过程如下：
        JSONObject javaBean = JSON.parseObject(obj.toJSONString());
        System.out.println(javaBean.getString("string"));
        System.out.println(javaBean.getInteger("int"));
        System.out.println(javaBean.getBooleanValue("boolean"));
        JSONArray list = javaBean.getJSONArray("list");
        List<Integer> integers = JSON.parseArray(list.toJSONString(), Integer.class);
        System.out.println(integers);

        // json字符串转换成JavaBean
        String studentJson = "{\"stuId\":\"116\",\"stuName\":\"赵云\",\"stuAddress\":\"常山\",\"stuIQ\":\"93\", \"emps\":[{\"id\":1,\"emp_name_cn\":\"aa\"}]}";
        FooBean fooBean = JSON.parseObject(studentJson, FooBean.class);
        System.out.println(JSON.toJSONString(fooBean));

        // json字符串转换成JavaBean（先转为JSONObject 在转为真正的javaBean）
        JSONObject object = JSON.parseObject(studentJson);
        //JsonObject对象转换成JavaBean
        FooBean student = object.toJavaObject(FooBean.class);

//        其他的常用方法
//        public static final Object parse(String text); // 把JSON文本parse为JSONObject或者JSONArray
//        public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray
//        public static final <T> List<T> parseArray(String text, Class<T> clazz); //把JSON文本parse成JavaBean集合
//        public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本
//        public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本
//        public static final Object toJSON(Object javaObject); // 将JavaBean转换为JSONObject或者JSONArray。

    }

    @Data
    public static class JacksonFooBean {

        private String name;
        private Integer age;
        private List<JacksonStu> stus;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)//比如json有stu_namex 但类中没有这个属性时不
    public static class JacksonStu {

        private String id;
        @JsonProperty(value = "stu_name")
        private String stuName;
    }

    @Data
    public static class FooBean {

        private String stuId;
        private String stuName;
        private String stuAddress;
        private String stuIQ;
        private List<FooSub> emps;
    }

    @Data
    public static class FooSub {

        private String id;
        // fastJson 用JSONField
        @JSONField(name = "emp_name_cn")
//        @JsonProperty(value = "emp_name_cn")
        private String empName;
    }
}
