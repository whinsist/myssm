

1、pom.xml配置flyway-core及maven插件


2、运行maven插件 flyway:migrate  如果是新建数据不行的话可以先flywar:baseline
    如果出现错误想在原来的sql上改动，可以删除schema_version表中对应的version版本


3、HttpClientUtil 两种方式


4、Spring的国际化（i18n）功能是通过MessageSource接口实现的，他提供了MessageSource::getMessage方法从预设的资源中获取对应的数据。
在介绍MessageSource之前，得先说清楚Java（J2SE）对国际化的基本实现——ResourceBundle，因为MessageSource是用它实现的。ResourceBundle很好理解，他就是按照规范的格式放置*.properties资源文件，
然后根据输入的语言环境来返回资源。
*中文先把需要中午转成unicode码（站长工具可以转）


5、增加.gitignore 文件
测试dbutil http://localhost:8081/api/v1

6、测试rabbitmq
测试mq消息发送  注意 1先初始化交换机  2初始化队列  3队列上绑定Routing key和交换机 





