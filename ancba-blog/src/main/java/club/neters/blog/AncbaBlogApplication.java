package club.neters.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"club.neters"})//扫描其他模块的配置类，有club.neters路径相同都可以
public class AncbaBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(AncbaBlogApplication.class, args);
        System.out.println("=================项目启动成功=============");
    }

}
