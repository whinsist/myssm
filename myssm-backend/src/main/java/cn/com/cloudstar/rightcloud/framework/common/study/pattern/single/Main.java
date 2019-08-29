package cn.com.cloudstar.rightcloud.framework.common.study.pattern.single;

import cn.com.cloudstar.rightcloud.framework.common.study.pattern.single.example.Counter;
import cn.com.cloudstar.rightcloud.framework.common.study.pattern.single.example.SingleProperty;

/**
 * @author Hong.Wu
 * @date: 22:31 2019/08/28
 */
public class Main {

    public static void main(String[] args) {

        // 推荐：
        // 1静态内部类 SingleTonStaticInnerClass
        // 2枚举
        for (int i = 0; i < 50; i++) {
            System.out.println(SingleEnum.INSTANCE);
        }


        // 单例模式的使用场景
//        什么是单例模式呢，单例模式(Singleton)又叫单态模式，它出现目的是为了保证一个类在系统中只有一个实例，并提供一个访问它的全局访问点。从这点可以看出，单例模式的出现是为了可以保证系统中一个类只有一个实例而且该实例又易于外界访问，从而方便对实例个数的控制并节约系统资源而出现的解决方案。
//        使用单例模式当然是有原因，有好处的了。在下面几个场景中适合使用单例模式：
//        1、有频繁实例化然后销毁的情况，也就是频繁的 new 对象，可以考虑单例模式；
//        2、创建对象时耗时过多或者耗资源过多，但又经常用到的对象；
//        3、频繁访问 IO 资源的对象，例如数据库连接池或访问本地文件；
//        下面举几个例子来说明一下：
//        1、网站在线人数统计；
//        其实就是全局计数器，也就是说所有用户在相同的时刻获取到的在线人数数量都是一致的。要实现这个需求，计数器就要全局唯一，也就正好可以用单例模式来实现。当然这里不包括分布式场景，因为计数是存在内存中的，并且还要保证线程安全。下面代码是一个简单的计数器实现。

        Counter counter = Counter.getInstance();
        counter.add();
        System.out.println("-----"+counter.getOnline());

        //2、配置文件访问类；
        //项目中经常需要一些环境相关的配置文件，比如短信通知相关的、邮件相关的。比如 properties 文件，这里就以读取一个properties 文件配置为例，如果你使用的 Spring ，
        // 可以用 @PropertySource 注解实现，默认就是单例模式。如果不用单例的话，每次都要 new 对象，每次都要重新读一遍配置文件，很影响性能，如果用单例模式，则只需要读取一遍就好了。
        // 以下是文件访问单例类简单实现：
        SingleProperty singleProperty = SingleProperty.getInstance();
        System.out.println(singleProperty.getName());



    }

}
