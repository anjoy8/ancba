package club.neters.user;

import club.neters.common.annotaion.EnableAncbaSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author maven plugin
 * @date 2021/6/25
 */
@SpringBootApplication
@EnableAncbaSwagger(basePackage = "club.neters.user", title = "接口文档", description = "用户模块接口文档")
@EnableFeignClients
public class AncbaUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(AncbaUserApplication.class, args);
        System.out.println("================= user微服务启动成功 =============");
    }
}
