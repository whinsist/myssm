package cn.com.cloudstar.rightcloud.framework.test.t003util;

import org.apache.commons.io.FileUtils;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Hong.Wu
 * @date: 20:59 2020/02/14
 */
public class Test19Compare {

    public static void main(String[] args) throws IOException {

        //用Collectors对List去重
        //test();
        //test2();

        List<String> listApi = FileUtils.readLines(new File("E:\\temp\\comp-api.txt"));
        List<String> listKzt = FileUtils.readLines(new File("E:\\temp\\comp-kzt.txt"));
        List<String> listDb = FileUtils.readLines(new File("E:\\temp\\comp-db.txt"));

//        for (String api : listApi) {
//            if (!listReal.contains(api)) {
//                System.out.println(api);
//            }
//        }

//
        for (String kzt : listKzt) {
            if (!listDb.contains(kzt)) {
                System.out.println("select image_id,image_name,cloud_env_id from res_image where image_name='"+kzt+"';");
            }
        }


    }

    private static void test2() throws IOException {
        List<String> list = FileUtils.readLines(new File("E:\\temp\\11.txt"));

        for (int  i =0 ;i<list.size(); i++) {
            String[] split = list.get(i).split(" ");
            System.out.println("dishList.add(new Dish(\""+split[1]+"\", \""+split[0]+"\"));");
        }
        List<Dish> dishList = new ArrayList<>();
        dishList.add(new Dish("7bbba11f-2761-468f-bdd8-b91d40ea2e42", "admin-hcltest-kz"));
        dishList.add(new Dish("3c80947c-53c9-43ec-974e-2e63657480e1", "hcl-kz"));
        dishList.add(new Dish("d8cb7a21-0bcb-44f9-8d48-2ebbe1b43311", "快照"));
        dishList.add(new Dish("45fd2c3d-11cf-4644-bbb1-fa26ecc2ec17", "chh-0309333"));
        dishList.add(new Dish("65351bd4-1b9c-4486-afa6-bf8f6c574c97", "cscs-001"));
        dishList.add(new Dish("ab1410dc-b43e-458a-9dbd-a65071746116", "ggxt35"));
        dishList.add(new Dish("44a97ecc-6c4a-4a39-8609-85bb21adb35e", "public-image-1550"));
        dishList.add(new Dish("7dde5f4f-55bc-42ea-b87b-d6109e04d254", "testxq0305"));
        dishList.add(new Dish("b2e0bace-a94e-4f77-b435-0ff450e821b8", "test-image-cs"));
        dishList.add(new Dish("704b28c5-5e86-4f47-a2eb-2e0d185c651c", "testxq1"));
        dishList.add(new Dish("a5297b0b-36db-4944-a12e-ec6a361c5eac", "test-image"));
        dishList.add(new Dish("e17159c2-bc2d-4e28-bb67-42b1ecd2146f", "test-sync"));
        dishList.add(new Dish("13892c50-9a9c-4ec9-9a71-612ff13dec30", "test"));
        dishList.add(new Dish("62162d5f-b5a4-4cdb-939a-c5c62f92129f", "escloud监控测试-快照"));
        dishList.add(new Dish("a04f23eb-3b31-4f36-9952-85d59aef1e03", "admin-image-kz01"));
        dishList.add(new Dish("b07917e1-d769-481e-ac48-d2617fc614e2", "admin-slkz"));
        dishList.add(new Dish("7316326f-0b7f-4c3b-9d40-f69e144ea8ec", "createrinst-kz"));
        dishList.add(new Dish("2491c70f-5421-4577-8d81-4fea9690b5b3", "hucuilan-image"));
        dishList.add(new Dish("ab089fb0-a02d-4d6b-9257-1740e909d1fd", "admin-image"));
        dishList.add(new Dish("0c40a0d3-980e-4a37-bc29-8092855de78d", "实例快照0103"));
        dishList.add(new Dish("77188810-df73-4a28-940d-97c22bfd826c", "zrtest"));
        dishList.add(new Dish("7bbba11f-2761-468f-bdd8-b91d40ea2e42", "admin-hcltest-kz"));
        dishList.add(new Dish("45fd2c3d-11cf-4644-bbb1-fa26ecc2ec17", "chh-0309333"));
        dishList.add(new Dish("65351bd4-1b9c-4486-afa6-bf8f6c574c97", "cscs-001"));
        dishList.add(new Dish("ab1410dc-b43e-458a-9dbd-a65071746116", "ggxt35"));
        dishList.add(new Dish("44a97ecc-6c4a-4a39-8609-85bb21adb35e", "public-image-1550"));
        dishList.add(new Dish("7dde5f4f-55bc-42ea-b87b-d6109e04d254", "testxq0305"));
        dishList.add(new Dish("b2e0bace-a94e-4f77-b435-0ff450e821b8", "test-image-cs"));
        dishList.add(new Dish("704b28c5-5e86-4f47-a2eb-2e0d185c651c", "testxq1"));
        dishList.add(new Dish("e17159c2-bc2d-4e28-bb67-42b1ecd2146f", "test-sync"));
        dishList.add(new Dish("a04f23eb-3b31-4f36-9952-85d59aef1e03", "admin-image-kz01"));
        dishList.add(new Dish("b07917e1-d769-481e-ac48-d2617fc614e2", "admin-slkz"));
        dishList.add(new Dish("7316326f-0b7f-4c3b-9d40-f69e144ea8ec", "createrinst-kz"));
        dishList.add(new Dish("ab089fb0-a02d-4d6b-9257-1740e909d1fd", "admin-image"));
        dishList.add(new Dish("77188810-df73-4a28-940d-97c22bfd826c", "zrtest"));
        dishList.add(new Dish("5962cc90-3c34-40dd-a0df-dadfe125dbff", "快照2-用于转换成镜像使用的"));
        dishList.add(new Dish("e5011763-56a4-4665-9c10-3c97c10e5f93", "winserver-2012R2-x86_64"));
        dishList.add(new Dish("9dd6bf86-48c0-4b42-8e39-6d8e3cea5ae1", "Windows-2008-Enterprise-R2"));
        dishList.add(new Dish("e960707b-e723-4ee4-bd2c-74d5fd4e0add", "rhel-server-7.3-x86_64"));
        dishList.add(new Dish("e76c2b03-699c-489f-9235-01a20d08c2c0", "centos7.4-x86_64"));
        dishList.add(new Dish("68d72962-d259-47c4-98c4-e3e4727860aa", "TestVM"));
        dishList.add(new Dish("aed6c09f-3bcd-4f31-b76e-5e34ab1dec54", "dr-image"));


        List<Dish> result = dishList.stream()
                             .collect(Collectors.collectingAndThen(
                                     Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getId()))),
                                     ArrayList::new)
                             );
        System.out.println(result.size());





    }

    private static void test() {
//        在学习本篇之前，最好对java8新特性有一定的了解。可以参考：Java8新特性--流(Stream)
//        场景：有一个实体的List集合，需要根据实体中的某个字段对List去重
//        https://www.cnblogs.com/xuwenjin/p/9660393.html
        List<Dish> dishList = new ArrayList<>();
        Dish dish1 = new Dish("001", "张三");
        dishList.add(dish1);
        Dish dish2 = new Dish("001", "李四");
        dishList.add(dish2);
        Dish dish3 = new Dish("002", "王五");
        dishList.add(dish3);

        // TreeSet中的元素，如果是实体，必须得传比较器(或者实体类需要实现Comparable中的compareTo方法)，不然就会报错
        // TreeSet是Set的子类，里面的元素有序且不能重复，可以去重
//        TreeSet<Dish> treeSet2 = new TreeSet<>(Comparator.comparing(Dish::getId));
//        treeSet2.addAll(dishList);
//        treeSet2.forEach(d -> System.out.println("id:" + d.getId() + ", name:" + d.getName()));
//          从上面可以看到TreeSet可以根据实体中的某个字段(这里是id)排序后去重。如果再将TreeSet转为List就可以达到我们的目的：
//        List<Dish> newDishList = new ArrayList<>(treeSet2);
//        newDishList.forEach(d -> System.out.println("id:" + d.getId() + ", name:" + d.getName()));


//        测试Collectors.toCollection方法：将结果收集到其它类型的集合中(这里是TreeSet)
//        TreeSet<Dish> treeSet2 = dishList.stream()
//                                         .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Dish::getId))));
//        List<Dish> newDishList = new ArrayList<>(treeSet2);
//        newDishList.forEach(d -> System.out.println("id:" + d.getId() + ", name:" + d.getName()));

//        测试Collectors.collectingAndThen方法：将流中的数据通过Collector计算，计算的结果再通过Function处理一下
//                * (这里是将TreeSet转为ArrayList。即相当于将最终结果又经过了new ArrayList<>(treeSet))
        List<Dish> newDishList = dishList.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Dish::getId))), ArrayList::new));
        newDishList.forEach(d -> System.out.println("id:" + d.getId() + ", name:" + d.getName()));


    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Dish {

        private String id;

        private String name;

    }

}
