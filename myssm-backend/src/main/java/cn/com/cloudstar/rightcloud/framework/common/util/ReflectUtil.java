/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util;

import cn.com.cloudstar.rightcloud.framework.common.constants.BillingConstants;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liyi
 * @date 2017/12/13
 */
public class ReflectUtil {

    /**
     * 反射获取规格
     *
     * @param objList
     * @param codeList
     * @return
     */
    public static List<Map<String, String>> getSpecList(Class classzz, List<?> objList, List<String> codeList) {
        if (objList == null || objList.size() == 0 || codeList == null || codeList.size() == 0) {
            return null;
        }
        List<Map<String, String>> list = new ArrayList<>();
        for (Object obj : objList) {
            Map<String, String> specMap = new HashMap<>();
            //Class objCla = (Class) obj.getClass();
            Field[] fs = classzz.getDeclaredFields();
            for (String string : codeList ){
                // 定义一个flag，
                boolean flag = true;
                for (int i = 0; i< fs.length; i++){
                    Field f = fs[i];
                    f.setAccessible(true); //设置些属性是可以访问的
                    try {
                        Object val = f.get(obj);//得到此属性的值
                        // 证明该对象中存在这个属性，那么就判断该值是否为空
                        if (string.equals(f.getName())){
                            flag = false;
                            if (StringUtil.isNullOrEmpty(val)) {
                                specMap.put(string, "null");
                            } else {
                                specMap.put(string, String.valueOf(val));
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                if (flag){
                    // 如果flag是true，那么证明传入的字段在对象里面没有这个属性
                    if (string.equals(BillingConstants.ModuleCode.CHARGE_TYPE)){
                        //specMap.put(string, BillingConstants.ChargeType.POST_PAID);
                    }else if (string.equals(BillingConstants.ModuleCode.BILLING_TYPE)){
                        specMap.put(string, BillingConstants.BillingType.USE_STRATEGY);
                    }
                }
            }
            /*for (int i = 0; i < fs.length; i++) {
                Field f = fs[i];
                f.setAccessible(true); //设置些属性是可以访问的
                try {
                    Object val = f.get(obj);//得到此属性的值
                    for (String str : codeList) {
                        if (f.getName().equals(str)) {
                            if (StringUtil.isNullOrEmpty(val)) {
                                specMap.put(str, "null");
                            } else {
                                specMap.put(str, String.valueOf(val));
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }*/
            list.add(specMap);
        }
        return list;
    }

    private static void setSpecMap(List<String> codeList, Map<String, String> specMap, Field f, Object val) {
        for (String str : codeList) {
            if (f.getName().equals(str)) {
                if (StringUtil.isNullOrEmpty(val)) {
                    specMap.put(str, "null");
                } else {
                    specMap.put(str, String.valueOf(val));
                }
            }
        }
    }

    /**
     * 反射获取规格 id关联对象
     */
    public static List<Map<String, Map<String, String>>> getSpecMapById(Class classzz, List<?> objList,
                                                                        List<String> codeList) {
        if (objList == null || objList.size() == 0 || codeList == null || codeList.size() == 0) {
            return null;
        }
        List<SpecVoMap> specVoMapList = new ArrayList<>();
        for (Object obj : objList) {
            SpecVoMap specVoMap = new SpecVoMap();
            Map<String, String> specMap = new HashMap<>();
            Field[] fs = classzz.getDeclaredFields();
            for (Field f : fs) {
                //设置些属性是可以访问的
                f.setAccessible(true);
                try {
                    //得到此属性的值
                    Object val = f.get(obj);
                    //设置id
                    if (f.getName().equals("id")) {
                        specVoMap.setId(String.valueOf(val));
                    }
                    setSpecMap(codeList, specMap, f, val);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            specVoMap.setSpecMap(specMap);
            specVoMapList.add(specVoMap);
        }
        List<Map<String, Map<String, String>>> list = new ArrayList<>();
        for (SpecVoMap specVoMap : specVoMapList) {
            Map<String, Map<String, String>> map = new HashMap<>();
            map.put(specVoMap.getId(), specVoMap.getSpecMap());
            list.add(map);
        }
        return list;
    }

    public static class SpecVoMap {
        private String id;
        private Map<String, String> specMap;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Map<String, String> getSpecMap() {
            return specMap;
        }

        public void setSpecMap(Map<String, String> specMap) {
            this.specMap = specMap;
        }
    }
}
