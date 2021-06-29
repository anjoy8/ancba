package club.neters.common.annotaion;

import club.neters.common.config.oauth2resource.OAuth2ResourceServerConfig;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

/**
 * @author wuare
 * @date 2021/6/29
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(OAuth2ResourceServerConfig.class)
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled=true)
public @interface EnableAncbaResourceServer {
}
