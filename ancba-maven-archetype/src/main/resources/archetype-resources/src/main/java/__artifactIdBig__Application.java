#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author maven plugin
 * @date 2021/6/25
 */
@SpringBootApplication
public class ${artifactIdBig}Application {

    public static void main(String[] args) {
        SpringApplication.run(${artifactIdBig}Application.class, args);
    }
}
