package club.neters.user;

import club.neters.common.annotaion.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wuare
 * @date 2021/6/25
 */
@SpringBootApplication
@EnableSwagger(basePackage = "club.neters")
public class AncbaUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(AncbaUserApplication.class, args);
    }
}
