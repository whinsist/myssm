package cn.com.cloudstar.rightcloud.framework.common.util;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Hong.Wu
 * @date: 13:55 2020/09/03
 */
public class CollectionHelper {

    @Data
    public static class ResVd {
        private String uuid;
        private String vdName;
        private String status;
        public ResVd(String uuid, String vdName) {
            this.uuid = uuid;
            this.vdName = vdName;
        }
    }

    public static void main(String[] args) {
        List<ResVd> managedResList = Arrays.asList(new ResVd("1", "磁盘1"), new ResVd("999", "磁盘999"), new ResVd("", "名字一样"));


        List<ResVd> resList = Lists.newArrayList();
        resList.add(new ResVd("1", "磁盘1"));
        resList.add(new ResVd("2", "磁盘2"));
        resList.add(new ResVd("3", "磁盘3"));
        resList.add(new ResVd("4", "名字一样"));

        // 判断条件
        BiPredicate<ResVd, ResVd> matchPredicate = (res1, res2) -> Objects.equals(res1.getUuid(), res2.getUuid());
        BiPredicate<ResVd, ResVd> matchNamePredicate = (res1, res2) -> StrUtil.isBlank(res1.getUuid())
                && Objects.equals(res1.getVdName(), res2.getVdName());

        // 需要更新的数据
        List<ResVd> requireUpdateList = intersectionDbByPredicate(managedResList, resList, matchPredicate);
        List<ResVd> requireUpdateList1 = intersectionDbByPredicate(managedResList, resList, matchNamePredicate);
        // 需要插入的数据
        List<ResVd> requireInsertList = subtractionByPredicate(resList, managedResList, matchPredicate.or(matchNamePredicate));
        // 需要删除的数据
        List<ResVd> requireDeleteList = subtractionByPredicate(managedResList, resList, matchPredicate.or(matchNamePredicate));


//        // 判断条件
//        BiPredicate<ResVm, ResVm> matchPredicate = (res1, res2) -> Objects.equals(res1.getInstanceId(), res2.getInstanceId());
//        // 需要新增的
//        List<ResVm> requireInsertList = BaremetalHelper.subtractionByPredicate(resList, manageResVms, matchPredicate);
//        // 需要删除的
//        List<ResVm> requireDeleteList = BaremetalHelper.subtractionByPredicate(manageResVms, resList, matchPredicate);
//        // 需要更新的
//        List<ResVm> requireUpdateList = BaremetalHelper.intersectionDbByPredicate(manageResVms, resList, matchPredicate);

    }   

    /**
     * 根据给定条件获取两个数组的差集
     *
     * @param original 原始数据
     * @param compare 比较数组
     * @param matchPredicate 判断条件
     * @param <T>
     */
    public static  <T, R> List<T> subtractionByPredicate(List<T> original, List<R> compare, BiPredicate<T, R> matchPredicate) {
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


    /**
     * 根据给定条件获取两个数组的交集
     *
     * @param dbList
     * @param source
     * @param matchPredicate 判断条件
     * @param <T>
     */
    public static <T, R> List<T> intersectionDbByPredicate(List<T> dbList, List<R> source, BiPredicate<T, R> matchPredicate) {
        if (CollectionUtil.isEmpty(dbList) || CollectionUtil.isEmpty(source)) {
            return Lists.newArrayList();
        }

        return dbList.stream()
                     .filter(res1 -> source.stream().anyMatch(res2 -> matchPredicate.test(res1, res2)))
                     .collect(Collectors.toList());
    }
}
