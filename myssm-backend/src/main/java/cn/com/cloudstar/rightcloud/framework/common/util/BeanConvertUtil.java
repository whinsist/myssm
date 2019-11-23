package cn.com.cloudstar.rightcloud.framework.common.util;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

/**
 * The type BeanConvertUtil.
 * <p>
 *
 * @author Xiaolu.Liu
 * @date 2019/7/1
 */
@Slf4j
public class BeanConvertUtil {

    /**
     * 返回指定类型的list
     *
     * @param srcData 原始数据
     * @param destClass 目标类型
     * @param <T> <T>
     * @return list
     */
    public static <T> List<T> convert(List<?> srcData, Class<T> destClass) {
        List<T> convertBeanList = transformBeanList(srcData, destClass);

        if (srcData instanceof Page) {
            Page srcPage = (Page) srcData;

            Page<T> destPage = new Page<>();
            destPage.addAll(convertBeanList);
            destPage.setPageNum(srcPage.getPageNum());
            destPage.setPageSize(srcPage.getPageSize());
            destPage.setTotal(srcPage.getTotal());
            destPage.setPages(srcPage.getPages());


            return destPage;
        }

        return convertBeanList;
    }

    /**
     * 对象拷贝
     *
     * @param <T>       the type parameter
     * @param srcObj    the src obj
     * @param destClass the dest class
     * @return the t
     */
    public static <T> T convert(Object srcObj, Class<T> destClass) {
        if (Objects.isNull(srcObj)) {
            return null;
        }
        String srcJson = "";
        if (srcObj instanceof String) {
            srcJson = (String) srcObj;
        } else {
            srcJson = JSON.toJSONString(srcObj);
        }

        return JSON.parseObject(srcJson, destClass);
    }



    /**
     * 对象List拷贝
     *
     * @param <T> the type parameter
     * @param srcObjList the src obj list
     * @param destClass the dest class
     *
     * @return the list
     */
    private static <T> List<T> transformBeanList(List<?> srcObjList, Class<T> destClass) {
        List<T> destList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(srcObjList)) {
            destList = JSON.parseArray(JSON.toJSONString(srcObjList), destClass);
        }

        return destList;
    }

}
