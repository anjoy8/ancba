package club.neters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author maven plugin
 * @date 2021/6/25
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AncbaAuthorizerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AncbaAuthorizerApplication.class, args);
        System.out.println("=================鉴权认证中心启动成功=============");
    }
}
