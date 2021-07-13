### 安装步骤

ps:命令中包含有网桥配置，如果不需要，可以去掉

#### 1、启动admin调度中心
```
docker run -d \
-e PARAMS="--spring.datasource.url=jdbc:mysql://118.25.25.13:3069/xxl_job?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=UTC --spring.datasource.username=root --spring.datasource.password=root --spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver" \
-p 8080:8080 \
-v /data/xxl-admin/logs:/data/applogs \
--name my-xxl-job-admin-2.2.0  \
-d xuxueli/xxl-job-admin:2.2.0
```

#### 2、启动executor执行器
```
docker run --name=ancba-task-xxl-container \
-d -v /data/ancba/ancba-task-xxl/application-dev.yml:/opt/application-dev.yml \
--add-host=host.docker.internal:host-gateway --network dev-ops --network-alias devops-ancba-task-xxl \
-it -p 8186:8186 -p 9876:9876 laozhangisphi/ancba-task-xxl:0.0.1-SNAPSHOT
```