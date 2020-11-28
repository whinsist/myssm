package cn.com.cloudstar.rightcloud.framework.test.t001study.classtest;

import java.io.File;
import java.net.URL;

/**
 * @author Hong.Wu
 * @date: 13:58 2020/10/23
 *
 * https://blog.csdn.net/zhangshk_/article/details/82704010
 */
public class TestClassLoader {

    public static void main(String[] args) {
        System.out.println(TestClassLoader.class.getResource("ehcache.xml"));
        System.out.println(TestClassLoader.class.getResource("/ehcache.xml"));
        System.out.println();
        System.out.println(TestClassLoader.class.getClassLoader());
        System.out.println(TestClassLoader.class.getClassLoader().getResource("ehcache.xml"));
        System.out.println(TestClassLoader.class.getClassLoader().getResource("/ehcache.xml"));


        String path = TestClassLoader.class.getResource("/").toString();
        System.out.println("path = " + path);
        URL path1 = TestClassLoader.class.getClassLoader().getResource("");
        System.out.println("path1= "+path1);

        String path2 = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println("path2 = " + path2);

        System.out.println(new File(path2+"ehcache.xml").exists());
    }


    // Class的getResource方法
    /**
     public java.net.URL getResource(String name) {
         name = resolveName(name);
         ClassLoader cl = getClassLoader0();// 获取加载该Class的ClassLoader，sun.misc.Launcher$AppClassLoader@18b4aac2
         if (cl==null) { //如果加载该Class的ClassLoader为null，则表示这是一个系统class
            // A system class.
            return ClassLoader.getSystemResource(name); //如果是系统class
         }
         return cl.getResource(name);//调用ClassLoader的getResource方法
     }
     */

    //下面是ClassLoader的getResource方法
    /**

     public URL getResource(String name) {
         URL url;
         if (parent != null) {//这里的parent为sun.misc.Launcher$ExtClassLoader@7d4793a8
            url = parent.getResource(name);//这里是一个递归调用，再次进入之后parent为null
         } else {
            url = getBootstrapResource(name);//到达系统启动类加载器
         }
         if (url == null) {//系统启动类加载器没有加载到，递归回退到第一次调用然后是扩展类加载器
            url = findResource(name);
         }
         return url;//最后如果都没有加载到，双亲委派加载失败，则加载应用本身自己的加载器。
     }
     */

}
