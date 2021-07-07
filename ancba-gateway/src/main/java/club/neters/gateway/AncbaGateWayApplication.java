package club.neters.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * gateway
 *
 * @author wuare
 * @date 2021/6/25
 */

@SpringBootApplication
public class AncbaGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AncbaGateWayApplication.class, args);
        System.out.println("================= gw网关服务启动成功 =============");
    }
}
