package cn.com.cloudstar.rightcloud.framework.test.t003util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Hong.Wu
 * @date: 20:59 2020/02/14
 */
public class Test20ListSort {

    public static void main(String[] args) {

        List<SortItem> dishList = new ArrayList<>();
        dishList.add(new SortItem(3, 3));
        dishList.add(new SortItem(2, 3));
        dishList.add(new SortItem(2, 2));
        dishList.add(new SortItem(2, 1));
        dishList.add(new SortItem(1, 1));

        dishList.add(new SortItem(5, 5));

        // cpu升序， 如果cpu相同按memery升序  ==========>第一个和第二个相比 返回1表示要反转一下  这个是标准写法
        Collections.sort(dishList, (o1, o2) -> {
            if (o1.getCpu() > o2.getCpu()) {
                // 希望cpu由小到大, 如果前面一个数大于后面一个数, 要反转一下即返回1
                return 1;
            } else if (o1.getCpu() < o2.getCpu()) {
                return -1;
            } else {
                if (o1.getMemery() > o2.getMemery()) {
                    // 如果前面一个数大于后面一个数, 要反转一下即返回1
                    return 1;
                } else if (o1.getMemery() < o2.getMemery()) {
                    return -1;
                }
            }
            return 0;
        });
        dishList.forEach(item -> {
            System.out.println(item.getCpu() + " - " + item.getMemery());
        });


    }


    @Data
    @AllArgsConstructor
    public static class SortItem {

        private Integer cpu;
        private Integer memery;
    }


}
