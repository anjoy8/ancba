package club.neters.shrio.demo;

import club.neters.common.annotaion.EnableAncbaSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wuare
 * @date 2021/6/25
 */
@SpringBootApplication
@EnableAncbaSwagger(basePackage = "club.neters.shrio.demo", title = "接口文档", description = "Shiro案例模块接口文档", oauth = false)
public class AncbaShrioDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AncbaShrioDemoApplication.class, args);
    }
}
