package cn.com.cloudstar.rightcloud.framework.interceptor.helper;


import cn.com.cloudstar.rightcloud.system.pojo.User;

public class UserThreadLocal {

    private static final ThreadLocal<User> LOCAL = new ThreadLocal<>();

    private UserThreadLocal() {

    }

    public static void set(User user) {
        LOCAL.set(user);
    }

    public static User get() {
        return LOCAL.get();
    }

}
