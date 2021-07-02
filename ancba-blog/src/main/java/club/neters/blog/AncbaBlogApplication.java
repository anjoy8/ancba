package club.neters.blog;

import club.neters.common.annotaion.EnableAncbaSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAncbaSwagger(basePackage = "club.neters.blog", title = "接口文档", description = "博客模块接口文档")
@SpringBootApplication
//@EnableResourceServer
public class AncbaBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(AncbaBlogApplication.class, args);
        System.out.println("=================blog微服务启动成功=============");
    }

}
