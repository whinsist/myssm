package cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.single;

/**
 * @author Hong.Wu
 * @date: 22:26 2019/08/28
 *
 * 我们可以通过 SingleEnum.INSTANCE 来访问实例。而且创建枚举默认就是线程安全的，
 * 并且还能防止反序列化导致重新创建新的对象。
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
