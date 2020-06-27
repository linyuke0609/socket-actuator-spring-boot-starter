# socket-actuator-spring-boot-starter

![输入图片说明](https://img.shields.io/badge/license-Apache--2.0-blue "在这里输入图片标题") ![输入图片说明](https://img.shields.io/badge/maven-1.0.0-green "在这里输入图片标题") ![输入图片说明](https://img.shields.io/badge/Prs-welcome-red "在这里输入图片标题")

   :smile:  :smile:  :smile: 
#### 介绍
使用自动化配置，实现springboot应用的长连接、监控设备接入，无需任何一行代码，即可从容简单的管理你的springboot。


#### 学习之前
本项目基于springboot2.x版本进行开发，所以2.x以下版本可能会出现问题，建议在入手之前确认你的springboot版本号。

需要进行开源提交PR的同学,建议先学习[springboot学习](http://blog.didispace.com/spring-boot-learning-2x/) 


#### 使用教学

   maven引入:
```
<dependency>
  <groupId>com.xiejr.actuator</groupId>
  <artifactId>socket-actuator-spring-boot-starter</artifactId>
  <version>1.0.1.RELEASE</version>
</dependency>
```

在配置文件application.yml或者application.properties中配置相关的信息,如下:

```
socket:
  actuator:
    author: xiejiarong
    ip-address: 127.0.0.1
    active: true #开启监控的开关参数
    version: 1.0
    system-name: springboot监控系统
    server:
      eviction-interval-timer-in-ms: 10  #socket心跳移除定时器时间
      lease-renewal-interval-in-seconds: 3 #设备心跳续约时间
      evit-when-time-out: false #到期后是否立马移除
      lease-expiration-duration-in-seconds: 9  #心跳最长间隔
    simple-page-application: true 
 ```
 项目启动之后访问http:ip+port/monitor.html即可看到监控页面。效果图如下:
 
 ![主页](https://images.gitee.com/uploads/images/2020/0626/142043_4d959464_2291825.png "屏幕截图.png")
 
 
 
#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


 开源不易，各位developer给颗星哈。

   欢迎发起PR，一起来完善这个项目。 
