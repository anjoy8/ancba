package club.neters.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 任务调度
 *
 */
@SpringBootApplication
public class AncbaScheduleApplication
{
    public static void main(String[] args) {
        SpringApplication.run(AncbaScheduleApplication.class, args);
        System.out.println("================= task微服务启动成功 =============");
    }
}
