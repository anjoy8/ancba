# Ancba    [![JDK](https://img.shields.io/badge/jdk-1.8.0-d.svg)](#)  [![Build status](https://github.com/anjoy8/blog-spring/workflows/Java/badge.svg)](https://github.com/anjoy8/blog-spring/actions)  [![Build Status](https://dev.azure.com/laozhangisphi/anjoy8/_apis/build/status/anjoy8.ancba?branchName=master)](https://dev.azure.com/laozhangisphi/anjoy8/_build?definitionId=2)   [![License MIT](https://img.shields.io/badge/license-MIT-blue.svg?style=flat-square)](https://github.com/anjoy8/blog-spring/blob/master/LICENSE) 
 
#### 打造`Blog.Core`项目的`SpringBoot`兄弟版。  
`Ancba` (Another New CLI By Alacrity) 另一个全新的敏捷脚手架（单体/模块化/微服务都可支持）。

### 核心操作

- [x] 在 `..../resources/application-local.yml` 文件中，配置项目端口号、MySql连接数据等；
- [x] 使用 `..../generator/MyBatisPlusGenerator` 生成器（直接运行即可），生成三层代码（实体、仓储、服务），若存在，则不覆盖；
- [x] 封装插件：集成 `Swagger2` 做接口文档；
- [x] 基于 `spring-security` 的`oauth2`协议搭建认证中心，并封装插件，全局可拔插使用；
- [x] 基于 `spring-cloud-eureka` 搭建服务发现；
- [x] 封装 `apache-shiro` 的demo ，实现简单用户授权操作（暂做demo，整个微服务项目基于security做认证授权）；
- [ ] 基于数据库动态配置权限，实现 `RBAC` 服务授权；
- [ ] and so on...
  
### 目的：   

- [x] 给 `.NET Core` 学习者一个了解`java`的机会，我们同处多语言的时代；
- [x] PS：目前本项目是练手项目，等熟悉后，可继续拓展；


