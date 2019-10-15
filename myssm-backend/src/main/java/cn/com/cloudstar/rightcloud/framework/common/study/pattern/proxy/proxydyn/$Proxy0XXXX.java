package cn.com.cloudstar.rightcloud.framework.common.study.pattern.proxy.proxydyn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;


class ProxyXXXX{
    InvocationHandler h=null;
    protected ProxyXXXX(InvocationHandler h) {
        this.h = h;
    }
	//...
}


//在$Proxy0构造方法中，$Proxy0调用父类Proxy的构造器,为h赋值,
public final class $Proxy0XXXX extends Proxy //implements WorkInterface
        {
    private static Method m1;
    private static Method m0;
    private static Method m3;
    private static Method m2;

    //静态代码块给Method赋值为我们自己的接口的实现类的对应的Method对象
    static {
        try {
            m1 = Class.forName("java.lang.Object").getMethod("equals",
                                                             new Class[] { Class.forName("java.lang.Object") });
            m0 = Class.forName("java.lang.Object").getMethod("hashCode",
                                                             new Class[0]);
            m3 = Class.forName("cn.edu.jlu.proxy.UserManager").getMethod("work",
                                                                         new Class[0]);
            m2 = Class.forName("java.lang.Object").getMethod("toString",
                                                             new Class[0]);
        } catch (NoSuchMethodException nosuchmethodexception) {
            throw new NoSuchMethodError(nosuchmethodexception.getMessage());
        } catch (ClassNotFoundException classnotfoundexception) {
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
        }
    }

    public $Proxy0XXXX(InvocationHandler invocationhandler) {
        super(invocationhandler);
    }

    @Override
    public final boolean equals(Object obj) {
        try {
            return ((Boolean) super.h.invoke(this, m1, new Object[] { obj }))
                    .booleanValue();
        } catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    @Override
    public final int hashCode() {
        try {
            return ((Integer) super.h.invoke(this, m0, null)).intValue();
        } catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }


    /*从$Proxy0的源码可以看出，动态代理类不仅代理了接口中的方法，
        而且还代理了java的根类从Object中的继承而来的
        equals()、hashcode()、toString()这三个方法，并且仅此三个方法

        当调用$Proxy0对象的toString方法时，会调用super.h.invoke(this, m2, null);进而调用父类的invoke方法
    */
    @Override
    public final String toString() {
        try {
            return (String) super.h.invoke(this, m2, null);
        } catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    //@Override
    public void work() {
        try {
            super.h.invoke(this, m3, null);
            return;
        } catch (Error e) {
        } catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }

    }
}  