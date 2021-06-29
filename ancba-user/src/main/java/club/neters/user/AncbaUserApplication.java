package club.neters.user;

import club.neters.common.annotaion.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wuare
 * @date 2021/6/25
 */
@SpringBootApplication
@EnableSwagger(basePackage = "club.neters.user", title = "接口文档", description = "用户模块接口文档")
public class AncbaUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(AncbaUserApplication.class, args);
    }
}
