# Ancba    [![JDK](https://img.shields.io/badge/jdk-1.8.0-d.svg)](#)  [![Build status](https://github.com/anjoy8/blog-spring/workflows/Java/badge.svg)](https://github.com/anjoy8/blog-spring/actions)  [![Build Status](https://dev.azure.com/laozhangisphi/anjoy8/_apis/build/status/anjoy8.ancba?branchName=master)](https://dev.azure.com/laozhangisphi/anjoy8/_build?definitionId=2)   [![License MIT](https://img.shields.io/badge/license-MIT-blue.svg?style=flat-square)](https://github.com/anjoy8/blog-spring/blob/master/LICENSE) 
 
#### 打造`Blog.Core`项目的`SpringBoot`微服务版，但是更强大 👏  
`Ancba` (Another New CLI By Alacrity) 另一个全新的敏捷脚手架（单体/模块化/微服务都可支持）。

### 核心知识点与进度 📣

- [x] 在 `..../resources/application-local.yml` 文件中，配置项目端口号、MySql连接数据等；
- [x] 使用 `..../generator/MyBatisPlusGenerator` 生成器（直接运行即可），生成三层代码（实体、仓储、服务），若存在，则不覆盖；
- [x] 基于 `spring-cloud-gateway` 搭建网关服务/认证鉴权/分流熔断；
- [x] 基于 `alibaba-nacos` 搭建服务发现；
- [x] 集成 `spring-boot-admin` 做服务监控；
- [x] 集成 `Swagger2` 做接口文档，并封装插件，通过注解引入微服务；
- [x] 基于 `Knif4j` 工具，配合网关，搭建微服务聚合文档；（参考 `ancba-gateway` 用户微服务)
- [x] 基于 `spring-security` 的`oauth2`协议搭建认证中心，并封装插件，全局可拔插使用；
- [x] 封装 `apache-shiro` 微服务 ，实现简单用户授权操作；（测试 `shiro` 的demo，整个微服务项目基于security做认证授权）
- [x] 基于数据库动态配置权限，实现 `RBAC` 服务授权；（参考 `ancba-user` 用户微服务，或者在网关中查看 `accessManager`)
- [x] 使用 `spring-cloud-openfeign` 实现服务间调用；（参考 `ancba-user` 用户微服务)
- [x] 实现 `openfign` 调用被 `security` 加权的其他微服务；（案例同上，通过`token`方式，也可在网关处理鉴权，服务间就不用鉴权）
- [ ] and so on...
  
### 操作与运行步骤 📕

- [x] 如果要单独运行某一个项目，需要在对应的 `application.yml` 文件中，配置数据库相关数据，用到的是 `blog.core` 的项目的数据库，当然你也可以重新生成一个新的数据库。数据库文件地址是：`https://gitee.com/laozhangIsPhi/gtFiles/raw/master/blogcore001.sql`。
- [x] 如果要查看认证鉴权/网关/服务发现/Admin服务管理等效果，请参考上面的对应步骤。


  
### 目的和希望 👍：   

- [ ] 如果感觉对自己现在或者将来有帮助，麻烦点个Star吧。
- [x] 给 `.NET Core` 学习者一个了解`java`的机会，我们同处多语言的时代；
- [x] PS：目前本项目是练手项目，等熟悉后，可继续拓展；


