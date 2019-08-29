1、pom.xml配置flyway-core及maven插件

2、运行maven插件 flyway:migrate  如果是新建数据不行的话可以先flywar:baseline
    如果出现错误想在原来的sql上改动，可以删除schema_version表中对应的version版本

3、HttpClientUtil 两种方式

4、Spring的国际化（i18n）功能是通过MessageSource接口实现的，他提供了MessageSource::getMessage方法从预设的资源中获取对应的数据。
在介绍MessageSource之前，得先说清楚Java（J2SE）对国际化的基本实现——ResourceBundle，因为MessageSource是用它实现的。ResourceBundle很好理解，他就是按照规范的格式放置*.properties资源文件，
然后根据输入的语言环境来返回资源。
*中文先把需要中午转成unicode码（站长工具可以转）


5、增加.gitignore 文件

6、测试rabbitmq
    测试mq消息发送  注意 1先初始化交换机  2初始化队列  3队列上绑定Routing key和交换机 


7、泛型 GeneratorRead.java
 // <T> void <T>声明一个参数 void无返回值

8、DBUtil的使用

9、bean转map  map转bean（java.beans.BeanInfo的使用）

10、RetryUtil重试工具类

11、DBUtil源码与Spring-rabbit源码收获

12、测试jdk8的lambda表达式

13、ssh密钥 (生成密钥 公钥私钥指纹  下载私钥downSshKey  ssh登录用私钥)

14、activity增加进系统

15、面试问题：list循环并移除其中元素； 线程安全的单例模式三种；比较自定义对象为什么要重写equals和hashcode方法 
	





