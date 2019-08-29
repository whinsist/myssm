package cn.com.cloudstar.rightcloud.framework.common.study.pattern.single;

/**
 *
 * 单例模式——线程安全的两种实现
 * 一、双重检查锁定（double-checked locking）
 *
 * 推荐使用懒汉式写法，即延迟加载，当需要用到实例的时候，才去初始化(new)此实例。
 * 但在并发环境下，一重判断，即判断一次instance为null，是不行的，并发环境下如果同时多个线程进入方法体就不能保证单例了，因此衍生了双重检查锁定的实现。
 *
 *
 * 二、静态内部类实现 单例模式
 */
public class SingleTon4DoubleCheck {

	// 静态实例变量加上volatile
    private static volatile SingleTon4DoubleCheck instance;

    // 私有化构造函数
    private SingleTon4DoubleCheck() {}

    // 双重检查锁
    public static SingleTon4DoubleCheck getInstance() {
        if (instance == null) {
            synchronized(SingleTon4DoubleCheck.class){
                if(instance == null){
                    instance = new SingleTon4DoubleCheck();
                }
            }
        }
        return instance;
    }
}