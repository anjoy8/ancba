# blog-spring

#### 如果配置admin的客户端，步骤如下

- [x] 1、pom文件中，引用依赖；
```xml
    <!--spring-boot-admin-->
    <dependency>
        <groupId>de.codecentric</groupId>
        <artifactId>spring-boot-admin-starter-client</artifactId>
        <version>2.3.1</version>
    </dependency>
    <!--/spring-boot-admin-->

```
- [x] 2、yaml文件中，配置admin地址等信息
```
spring:
  application:
    name: admin-client # 给client应用取个名字
  boot:
    admin:
      client:
        url: http://localhost:8088



#开放端点用于SpringBoot Admin的监控
management:
  endpoints:
    web:
      exposure:
        include: '*'
  endpoint:
    health:
      show-details: ALWAYS

#配置生成日志文件名称
logging:
  file:
    name: admin-client.log        
```



