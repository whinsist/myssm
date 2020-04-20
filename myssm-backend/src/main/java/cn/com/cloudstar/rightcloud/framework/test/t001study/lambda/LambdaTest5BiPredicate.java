package cn.com.cloudstar.rightcloud.framework.test.t001study.lambda;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import org.apache.poi.ss.formula.functions.T;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cn.hutool.core.collection.CollectionUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Hong.Wu
 * @date: 21:37 2019/06/24
 */
public class LambdaTest5BiPredicate {

    public static void main(String[] args) {
        List<ResImage> resList = new ArrayList<>();
        resList.add(new ResImage(null, "aaa", "aaa"));
        resList.add(new ResImage(null, "bbb", "bbb"));
        resList.add(new ResImage(null, "ccc", "ccc"));

        List<ResImage> managedResList = new ArrayList<>();
        managedResList.add(new ResImage(1L, "aaa", "aaa"));
        managedResList.add(new ResImage(2L, "bbb", "bbb"));
        managedResList.add(new ResImage(2L, "zzz", "delete"));

        // 判断条件
        BiPredicate<ResImage, ResImage> matchPredicate = (res1, res2) -> Objects.equals(res1.getImageId(),
                                                                                        res2.getImageId());

        // 需要更新的数据
        List<ResImage> requireUpdateList = intersectionDbByPredicate(managedResList, resList, matchPredicate);
        System.out.println("requireUpdateList=" + JSON.toJSONString(requireUpdateList));

        // 需要入库的数据
        List<ResImage> requireInsertList = subtractionByPredicate(resList, managedResList, matchPredicate);
        System.out.println("requireInsertList=" + JSON.toJSONString(requireInsertList));

        // 需要删除的数据
        List<ResImage> requireDeleteList = subtractionByPredicate(managedResList, resList, matchPredicate);
        System.out.println("requireDeleteList=" + JSON.toJSONString(requireDeleteList));

        List<ResImage> requireUpdateListMy = new ArrayList<>();
        List<ResImage> requireDeleteListMy = new ArrayList<>();
        List<ResImage> requireInsertListMy = new ArrayList<>();
        for (ResImage res1 : managedResList) {

            boolean isExist = false;
            for (ResImage res2 : resList) {
                boolean flag = matchPredicate.test(res2, res1);
                System.out.println(flag + "  " + res1.getImageId() + "  " + res2.getImageId());
                if (flag) {
                    requireUpdateListMy.add(res1);
                }
                if (flag) {
                    isExist = true;
                }
            }

            if (!isExist) {
                requireDeleteListMy.add(res1);
            }
        }
        for (ResImage res2 : resList) {
            boolean isExist = false;
            for (ResImage res1 : managedResList) {
                boolean flag = matchPredicate.test(res2, res1);
                if (flag) {
                    isExist = true;
                }
            }
            if (!isExist) {
                requireInsertListMy.add(res2);
            }
        }
        System.out.println(JSON.toJSONString(requireUpdateListMy));
        System.out.println(JSON.toJSONString(requireDeleteListMy));
        System.out.println(JSON.toJSONString(requireInsertListMy));


    }

    private static <T, R> List<T> subtractionByPredicate(List<T> original, List<R> compare,
                                                         BiPredicate<T, R> matchPredicate) {
        // subtraction 减法
        if (CollectionUtil.isEmpty(original)) {
            return Lists.newArrayList();
        }
        if (CollectionUtil.isEmpty(compare)) {
            return original;
        }

        return original.stream()
                       .filter(res1 -> compare.stream().noneMatch(res2 -> matchPredicate.test(res1, res2)))
                       .collect(Collectors.toList());
    }

    private static <T, R> List<T> intersectionDbByPredicate(List<T> dbList, List<R> source,
                                                            BiPredicate<T, R> matchPredicate) {
        // 交集
        if (CollectionUtil.isEmpty(dbList) || CollectionUtil.isEmpty(source)) {
            return Lists.newArrayList();
        }
        return dbList.stream().filter(res1 -> {
            return source.stream().anyMatch(res2 -> matchPredicate.test(res1, res2));
        }).collect(Collectors.toList());
    }

    @Data
    @AllArgsConstructor
    public static class ResImage {

        private Long id;
        private String imageId;
        private String imageName;
    }
}
