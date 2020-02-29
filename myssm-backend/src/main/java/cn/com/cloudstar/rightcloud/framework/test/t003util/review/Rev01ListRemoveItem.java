package cn.com.cloudstar.rightcloud.framework.test.t003util.review;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Hong.Wu
 * @date: 21:47 2019/08/28
 */
public class Rev01ListRemoveItem {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("bb");
        list.add("bb");
        list.add("ccc");
        list.add("ccc");
        list.add("ccc");

//        remove1(list);
//        remove2(list);

//        yes3(list);
        yes4(list);

        for (String s : list) {
            System.out.println("element : " + s);
        }
    }

    private static void yes4(List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String s = it.next();
            if (s.equals("bb")) {
                it.remove();
            }
        }
    }

    private static void yes3(List<String> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            String s = list.get(i);
            if (s.equals("bb")) {
                list.remove(s);
            }
        }
    }

    private static void remove2(List<String> list) {
        for (String s : list) {
            if (s.equals("bb")) {
                list.remove(s);
            }
        }
    }

    public static void remove1(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.equals("bb")) {
                list.remove(s);
            }
        }
    }

}
