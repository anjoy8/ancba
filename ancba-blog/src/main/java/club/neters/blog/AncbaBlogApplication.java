package club.neters.blog;

import club.neters.common.annotaion.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAncbaSwagger(basePackage = "club.neters.blog")
@EnableAncbaResourceServer
@SpringBootApplication
public class AncbaBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(AncbaBlogApplication.class, args);
        System.out.println("=================项目启动成功=============");
    }

}
