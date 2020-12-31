package cn.com.cloudstar.rightcloud.framework.test.t003util;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.*;

public class Test14BuildTree {

    public static JSONArray treeRecursionDataList(List<Map<String, Object>> treeList, int parentId) {
        JSONArray childMenu = new JSONArray();
        for (Object object : treeList) {
            JSONObject jsonMenu = JSONObject.parseObject(JSON.toJSONString(object));
            int keyValue = jsonMenu.getIntValue("id");
            int parentValue = jsonMenu.getIntValue("parentId");
            if (Objects.equals(parentId, parentValue)) {
                JSONArray nodes = treeRecursionDataList(treeList, keyValue);
                jsonMenu.put("childs", nodes);
                childMenu.add(jsonMenu);
            }
        }
        return childMenu;
    }


    public static JSONArray treeRecursionCategory(List<CategoryPO> list, Long parentId) {
        JSONArray childMenu = new JSONArray();
        for (Object object : list) {
            JSONObject jsonMenu = JSONObject.parseObject(JSON.toJSONString(object));
            Long keyValue = jsonMenu.getLong("id");
            Long parentValue = jsonMenu.getLong("parentId");
            if (Objects.equals(parentId, parentValue)) {
                JSONArray nodes = treeRecursionCategory(list, keyValue);
                jsonMenu.put("childs", nodes);
                childMenu.add(jsonMenu);
            }
        }
        return childMenu;
    }




    public static void main(String[] args) {
        // 获得用户岗位包含的所有分类
        List<Map<String, Object>> userJobCategoryList = getUserJobContainAllCategoryList();
        JSONArray treeResult1 = treeRecursionDataList(userJobCategoryList, 0);
        System.out.println(JSON.toJSONString(treeResult1));


        //
        List<CategoryPO> list2 = getList2();
        JSONArray treeResult2 = treeRecursionCategory(list2, 0L);
        // parentId的值决定了从哪个根节点开始
        //JSONArray treeResult2 = treeRecursionCategory(list2, 2012300000004187L);
        System.out.println(JSON.toJSONString(treeResult2));

    }

    private static List<CategoryPO> getList2() {
        String str = FileUtil.readUtf8String("d:/tmp/list.txt");
        return JSON.parseArray(str, CategoryPO.class);
    }


    public static List<Map<String, Object>> getUserJobContainAllCategoryList() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 1);
        map1.put("parentId", 0);
        map1.put("name", "岗位资格性培训");
        list.add(map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("id", 2);
        map2.put("parentId", 1);
        map2.put("name", "专业知识");
        list.add(map2);

        Map<String, Object> map3 = new HashMap<>();
        map3.put("id", 3);
        map3.put("parentId", 0);
        map3.put("name", "test3");
        list.add(map3);
        Map<String, Object> map4 = new HashMap<>();
        map4.put("id", 4);
        map4.put("parentId", 2);
        map4.put("name", "test4");
        list.add(map4);

        Map<String, Object> map5 = new HashMap<>();
        map5.put("id", 5);
        map5.put("parentId", 666);
        map5.put("name", "test5");
        list.add(map5);
        return list;
    }


    @Data
    public static class CategoryPO {
        private Long id;
        private String name;
        private Integer level;
        private Long parentId;
    }
}


