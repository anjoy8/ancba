package club.neters.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * application
 *
 * @author wuare
 * @date 2021/6/24
 */
@EnableEurekaServer
@SpringBootApplication
public class AncbaEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AncbaEurekaApplication.class, args);
    }
}
