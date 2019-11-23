package cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.single;

/**
 * @author Hong.Wu
 * @date: 22:19 2019/08/28
 *
 * 二、静态内部类实现 单例模式
 *
 * 静态内部类实现单例模式，主要原理为：Java中静态内部类可以访问其外部类的静态成员属性，同时，静态内部类只有当被调用的时候才开始首次被加载，
 *
 * 利用了classloader的机制来保证初始化instance时只有一个线程，所以也是线程安全的，同时没有性能损耗(加synchronized同步锁)，这种实现更推荐。
 *
 */
public class SingleTonStaticInnerClass {

    private SingleTonStaticInnerClass (){}


    // 利用静态内部类特性实现外部类的单例
    private static class SingleTonStaticInnerClassHolder {
        private static SingleTonStaticInnerClass singleTon = new SingleTonStaticInnerClass();
    }

    public static SingleTonStaticInnerClass getInstance() {
        return SingleTonStaticInnerClassHolder.singleTon;
    }

}
