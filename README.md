# hkitemplate
spring boot template
一个spring boot 集成框架

当前demo主要做了以下几件小事



1. 通过ResultBean统一格式返回给前端 

2. AOP捕获进入controller之后的异常 ControllerExceptionAdvice 捕获其他异常

3. 使用mybatis-plus 初始化生成controller 中的CRUD 只要有数据库表后直接可以生成对于的CRUD

4. 拦截器ParamInterceptor拦截过滤token 

5. 使用新版swagger 保证测试接口的时候可以填写相对应的token

6. 增加IdGen 工具 用来获取分布是id  保证不会重复

7. 添加redis 工具来处理缓存

8. 增加调用第三方rest工具类

9. 增加token生成工具 以及 md5数据加密工具

10. 添加Druid连接池

11. 添加测试Controller 测试类 在test目录下

12. 统一日志处理 logback

13. 添加lombok

14. maven多环节构建

15. 添加MP 复杂业务下xml 包括 多表关联,list循环,分页

16. 添加枚举和变量例子

17. 使用lombok slf4j打印日志

18. 在AOP中添加slf4j的MDC便于日志的链路追踪并分级处理

19. 添加接口防刷

20. 添加特殊异常处理

21. 添加文件打包压缩工具












