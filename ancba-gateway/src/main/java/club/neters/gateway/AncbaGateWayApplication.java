package club.neters.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * gateway
 *
 * @author wuare
 * @date 2021/6/25
 */

@SpringBootApplication
@EnableDiscoveryClient
public class AncbaGateWayApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(AncbaGateWayApplication.class, args);
        // 读取启用配置文件中的属性
        String authorName = applicationContext.getEnvironment().getProperty("demo");
        System.out.println("================= gw网关服务启动成功，作者：" + authorName + "=============");
    }
}
