package cn.com.cloudstar.rightcloud.framework.test.t001study.lambda;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import lombok.Data;

import cn.com.cloudstar.rightcloud.framework.common.exception.BizException;

/**
 * @author Hong.Wu
 * @date: 21:37 2019/06/24
 *
 * 参考微信
 * JAVA8之妙用Optional解决判断Null为空的问题
    java一日一条  2020-05-08
 */
public class LambdaTest6Optional {

    public static void main(String[] args) {
        String province = null;
        OptUser optUser = null;
        if (optUser != null) {
            OptAddress optAddress = optUser.getOptAddress();
            if (optAddress != null) {
                province = optAddress.getProvince();
            }
        }
        System.out.println("最原始：" + province);
        // API介绍
        // Optional(T value),empty(),of(T value),ofNullable(T value)
        // 这四个函数之间具有相关性，因此放在一组进行记忆。
        /**
         *
         API介绍
         1、Optional(T value),empty(),of(T value),ofNullable(T value)
         这四个函数之间具有相关性，因此放在一组进行记忆。
         2、为空时
         orElse(T other)，orElseGet(Supplier<? extends T> other)和orElseThrow(Supplier<? extends X> exceptionSupplier)
         这两个函数的区别：当user值不为null时，orElse函数依然会执行createUser()方法，而orElseGet函数并不会执行createUser()方法，大家可自行测试。
         */
        optUser = new OptUser();
        optUser.setName("org");
        OptUser optUserorElse = Optional.ofNullable(optUser).orElse(createUser());
        System.out.println(optUserorElse);
        OptUser optUserorElseGet = Optional.ofNullable(optUser).orElseGet(() -> createUser());
        System.out.println(optUserorElseGet);
        OptUser optUser1 = Optional.ofNullable(optUser).orElseThrow(() -> new BizException("zzzzz"));

        /**
         * 3、
         map(Function<? super T, ? extends U> mapper)和flatMap(Function<? super T, Optional<U>> mapper)
         这两个函数放在一组记忆，这两个函数做的是转换值的操作

         如果当前对象为NULL，则返回自己或者一个 Optional 空实例。不 NULL 则执行后面的 Lambda，得到返回结果的Optional 实例。大概是这样，详细区别见源码吧
         public Optional<T> filter(Predicate<? super T> var1)：
         public <U> Optional<U> map(Function<? super T, ? extends U> var1)：
         public <U> Optional<U> flatMap(Function<? super T, Optional<U>> var1)：
         */
        String name = Optional.ofNullable(optUser)
                              .map(u -> {
                                  return u.getName();
                              })
                              .get();
        System.out.println(name);
        /**
         * 4、isPresent()和ifPresent(Consumer<? super T> consumer)
         这两个函数放在一起记忆，isPresent即判断value值是否为空，而ifPresent就是在value值不为空时，做一些操作。这两个函数的源码如下
         */
        /**
         * 5、filter(Predicate<? super T> predicate)     不多说，直接上源码
         * filter 方法接受一个 Predicate 来对 Optional 中包含的值进行过滤，如果包含的值满足条件，那么还是返回这个 Optional；否则返回 Optional.empty。
         用法如下

         如上所示，如果user的name的长度是小于6的，则返回。如果是大于6的，则返回一个EMPTY对象。
         */
        optUser = null;
        Optional<OptUser> optUser2 = Optional.ofNullable(optUser).filter(u -> u.getName().length() > 6);
        // 直接.get要报错  要判断是否为空isPresent
        //System.out.println(optUser2.get());

        // 实例1
        //String getCity = getCity(optUser);
        // 实例2
        doSomething(optUser);
        // 实例3
        optUser = new OptUser("zzz");
        optUser = getZhangsanUser(optUser);
        System.out.println(optUser);

        getsl4(optUser);

        /**
         * 用了 isPresent() 处理 NullPointerException 不叫优雅, 有了  orElse, orElseGet 等, 特别是 map 方法才叫优雅.
         其他几个, filter() 把不符合条件的值变为 empty() ,   flatMap() 总是与 map() 方法成对的,   orElseThrow() 在有值时直接返回, 无值时抛出想要的异常.

         一句话小结: 使用 Optional 时尽量不直接调用 Optional.get() 方法, Optional.isPresent() 更应该被视为一个私有方法,
         应依赖于其他像 Optional.orElse() , Optional.orElseGet() , Optional.map() 等这样的方法.

         最后, 最好的理解 Java 8 Optional 的方法莫过于看它的源代码java.util.Optional , 阅读了源代码才能真真正正的让你解释起来最有底气, Optional 的方法中基本都是内部调用   
         isPresent() 判断, 真时处理值, 假时什么也不做.
         */

    }

    public static List<String> getsl4(OptUser user) {
        Optional<OptUser> optional = Optional.ofNullable(user);
//        if (optional.isPresent()) {
//            return optional.get().getOrders();
//        } else {
//            return Collections.emptyList();
//        }
        return optional.map(u -> u.getOrders()).orElse(Collections.emptyList());
    }

    private static OptUser getZhangsanUser(OptUser optUser) {
        //以前写法
//        if (optUser != null) {
//            if ("zhangsan".equals(optUser.getName())) {
//                return optUser;
//            }
//            return createUser("zhangsan");
//        } else {
//            return createUser("zhangsan");
//        }
        // jdk8
        return Optional.ofNullable(optUser)
                       .filter(u -> "zhangsan".equals(u.getName()))
                       .orElseGet(() -> {
                           return createUser("zhangsan");
                       });

    }

    private static void doSomething(OptUser optUser) {
        if (optUser != null) {
            //doSomething(optUser);
        }
        // isPresent 是否为空
        // ifPresent 如果为空
        Optional.ofNullable(optUser).ifPresent(u -> {
            //doSomething(optUser);
        });

    }

    public static String getCity(OptUser optUser) {
//        // 在函数方法中,  以前写法
//        if (optUser != null) {
//            OptAddress optAddress = optUser.getOptAddress();
//            if (optAddress != null) {
//                return optAddress.getCity();
//            }
//        }
//        throw new BizException("取值错误");

        // java8
        return Optional.ofNullable(optUser)
                       .map(u -> u.getOptAddress())
                       .map(a -> a.getCity())
                       .orElseThrow(() -> new BizException("取值错误"));

    }

    private static OptUser createUser() {
        OptUser optUser = new OptUser();
        optUser.setName("zhangsan");
        return optUser;
    }

    private static OptUser createUser(String name) {
        OptUser optUser = new OptUser();
        optUser.setName(name);
        return optUser;
    }




    @Data
    public static class OptUser {

        private String name;
        private OptAddress optAddress;
        private List<String> orders;

        public OptUser(String name) {
            this.name = name;
        }

        public OptUser() {
        }
    }

    @Data
    public static class OptAddress {

        private String province;
        private String city;
        private String area;
    }
}
