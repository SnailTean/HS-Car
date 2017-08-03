**恒生拼车系统** 


**具有如下特点** 
- 轻量级的权限系统，只涉及Spring、Shiro、Mybatis后端框架，降低学习使用成本
- 友好的代码结构及注释，便于阅读及二次开发
- 支持HTML、JSP、Velocity、Freemarker等视图，零技术门槛
- 灵活的权限控制，可控制到页面或按钮，满足绝大部分的权限需求
- 页面交互使用Vue2.x，极大的提高了开发效率
- 完善的代码生成机制，可在线生成entity、xml、dao、service、html、js、sql代码，减少70%以上的开发任务
- 引入quartz定时任务，可动态完成任务的添加、修改、删除、暂停、恢复及日志查看等功能
- 引入API模板，根据token作为登录令牌，极大的方便了APP接口开发
- 引入Hibernate Validator校验框架，轻松实现后端校验
- 引入云存储服务，已支持：七牛云、阿里云、腾讯云等
- 引入路由机制，刷新页面会停留在当前页


**项目结构** 
```
hscar
│
├─sql  项目SQL语句
│
├─hscar-business 恒生拼车核心业务模块(jar包)
│  ├─entity层
│  ├─dao层
│  ├─service层
│  └─*.xml Mybatis配置文件
│
├─hscar-api Http接口模块,web工程可以在线测试接口(jar包)
│  ├─token app接口权限控制
│  ├─api Http接口服务(/api/customer/*、/api/driver/*)
│  └─vo层
│
├─hscar-app 恒生拼车移动app(war包)
│  ├─AppPageController 页面控制器
│  ├─js 移动端js代码
│  └─WEB-INF/page/hscar/app 移动端页面
│
├─hscar-common 公共模块(jar包)
│  ├─工具类
│  ├─字典表、日志表
│  └─db.properties 数据库配置文件
│
├─hscar-file 文件模块(jar包)
│  └─oss 云存储服务:七牛云、阿里云、腾讯云等
│ 
├─hscar-gen 代码生成器模块(jar包)
│  ├─template 代码生成器模板（可增加或修改相应模板）
│  └─generator.properties 配置文件（配置包名、类型转换等）
│ 
├─hscar-schedule 定时任务模块(jar包)
│
├─hscar-shiro 权限模块(jar包)
│ 
├──hscar-admin 恒生拼车后台管理系统(war包)
│  ├─js 系统业务js代码
│  ├─statics 第三方库、插件等静态资源
│  ├─index.html AdminLTE主题风格（默认主题）
│  └─index1.html Layui主题风格
│
```


 **技术选型：** 
- 核心框架：Spring Framework 4.3
- 安全框架：Apache Shiro 1.3
- 视图框架：Spring MVC 4.3
- 持久层框架：MyBatis 3.3
- 定时器：Quartz 2.2
- 数据库连接池：Druid 1.0
- 日志管理：SLF4J 1.7、Log4j
- 页面交互：Vue2.x


 **软件需求** 
- JDK1.7+
- MySQL5.5+
- Tomcat7.0+
- Maven3.0+


 **本地部署**
- 通过git下载源码
- 创建数据库hscar，数据库编码为UTF-8
- 执行sql/db.sql文件，初始化数据【按需导入表结构及数据】
- 修改db.properties文件，更新MySQL账号和密码
- 执行【mvn clean install -Dmaven.test.skip=true】指令，进行maven install操作
- 后台管理系统（账号密码：admin/admin）：
  执行【clean package jetty:run】指令，访问：http://localhost:8080/；
  或者用jetty或tomcat热部署插件启动，访问http://localhost:8080/hscar-admin/
- 恒生拼车接口系统（用于接口调试和文档查看）：  执行【clean package jetty:run】指令，项目访问路径：http://localhost:8080/；  或者用jetty或tomcat热部署插件启动，访问http://localhost:8080/hscar-api/
- 恒生拼车APP（手机端）：  执行【clean package jetty:run】指令，项目访问路径：http://localhost:8080/；  或者用jetty或tomcat热部署插件启动，访问http://localhost:8080/hscar-app/
