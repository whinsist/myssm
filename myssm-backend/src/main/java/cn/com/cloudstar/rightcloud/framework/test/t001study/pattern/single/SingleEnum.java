package cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.single;

/**
 * @author Hong.Wu
 * @date: 22:26 2019/08/28
 *
 * 我们可以通过 SingleEnum.INSTANCE 来访问实例。而且创建枚举默认就是线程安全的，
 * 并且还能防止反序列化导致重新创建新的对象。
 *
 *
SingletonEnum的定义利用的enum是一种特殊的class.代码中的第一行INSTANCE会被编译器编译为SingletonEnum本身的一个对象.当第一次访问SingletonEnum.INSTANCE时会创建该对象,并且enum变量的创建是线程安全的
著作权归作者所有。商业转载请联系作者获得授权,非商业转载请注明出处。
原文: https://www.cnblogs.com/makefile/p/enum-singleton.html © 康行天下
 *
 */
public enum SingleEnum {
    INSTANCE;
    SingleEnum(){
        System.out.println("构造函数执行");
    }

    public String getName(){
        return "singleEnum";
    }
}
