package club.neters.common.annotaion;

import club.neters.common.config.security.CustomWebSecurityConfigurer;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wuare
 * @date 2021/6/29
 */
@Deprecated
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CustomWebSecurityConfigurer.class)
public @interface EnableAncbaResourceServer {

}
