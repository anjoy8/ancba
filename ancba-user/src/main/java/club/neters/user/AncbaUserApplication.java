package club.neters.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wuare
 * @date 2021/6/25
 */
@SpringBootApplication
@ComponentScan(basePackages = {"club.neters"})
public class AncbaUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(AncbaUserApplication.class, args);
    }
}
