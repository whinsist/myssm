package cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.proxy.proxydyn;

/**
 * @author Hong.Wu
 * @date: 21:20 2019/10/10
 */
public class Main2 {

    public static void main(String[] args) {

        // 目标对象
        User user = new User();

        ProxyDynamic proxyDynamic = new ProxyDynamic();
        // 返回代理对象  其实也是加强版的目标对象 class com.sun.proxy.$Proxy0(jvm运行时动态生成的一个对象)
        // 代理对象继承了Proxy 实现了与目标对象相同的接口WorkInface  所以必须实现一个接口
        // 为什么不能用User来接  为什么能用接口来接
        WorkInface workInface = (WorkInface) proxyDynamic.bind(user);
        // 代理对象实现的所有接口
        Class<?>[] interfaces = workInface.getClass().getInterfaces();
        for (Class interfacez : interfaces) {
            System.out.println(interfacez.getName());
        }




        // 无论调用目标对象的那个方法都是在调用InvocationHandler的invoke方法
        String res = workInface.work();
        System.out.println("res-----" + res);


        WorkInface w2 = (WorkInface) ProxyUtil.proxyUserInterface(user);
        System.out.println("res2-------"+w2.work());


    }
}
