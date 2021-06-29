package club.neters.blog;

import club.neters.common.annotaion.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableSwagger(basePackage = "club.neters.blog")
@EnableResourceServer
@SpringBootApplication
public class AncbaBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(AncbaBlogApplication.class, args);
        System.out.println("=================项目启动成功=============");
    }

}
