package cn.com.cloudstar.rightcloud.framework.testtreeutil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test14BuildTree {
    private static final int ROOTID_IS_ZORE = 0;
    public static JSONArray treeRecursionDataList(List<Map<String, Object>> treeList, int parentId) {
        JSONArray childMenu = new JSONArray();
        for (Object object : treeList) {
            JSONObject jsonMenu = JSONObject.parseObject(JSON.toJSONString(object));
            int menuId = jsonMenu.getIntValue("id");
            int pid = jsonMenu.getIntValue("parentId");
            if (parentId == pid) {
                JSONArray nodes = treeRecursionDataList(treeList, menuId);
                jsonMenu.put("childs", nodes);
                childMenu.add(jsonMenu);
            }
        }
        return childMenu;
    }


    public static void main(String[] args) {
        // 获得用户岗位包含的所有分类
        List<Map<String, Object>> userJobCategoryList = getUserJobContainAllCategoryList();
        JSONArray returnList = treeRecursionDataList(userJobCategoryList, ROOTID_IS_ZORE);
        System.out.println(JSON.toJSONString(returnList));
    }

    public static List<Map<String, Object>> getUserJobContainAllCategoryList() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 1);
        map1.put("parentId", ROOTID_IS_ZORE);
        map1.put("name", "岗位资格性培训");
        list.add(map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("id", 2);
        map2.put("parentId", 1);
        map2.put("name", "专业知识");
        list.add(map2);

        Map<String, Object> map3 = new HashMap<>();
        map3.put("id", 3);
        map3.put("parentId", ROOTID_IS_ZORE);
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
}

