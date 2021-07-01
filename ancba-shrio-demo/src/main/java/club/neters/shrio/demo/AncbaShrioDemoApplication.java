package club.neters.shrio.demo;

import club.neters.common.annotaion.EnableAncbaSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wuare
 * @date 2021/6/25
 */
@SpringBootApplication
@EnableAncbaSwagger(basePackage = "club.neters.user", title = "接口文档", description = "用户模块接口文档")
public class AncbaShrioDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AncbaShrioDemoApplication.class, args);
    }
}