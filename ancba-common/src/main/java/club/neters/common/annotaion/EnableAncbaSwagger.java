package club.neters.common.annotaion;

import club.neters.common.config.swagger.SwaggerRegistrar;
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
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SwaggerRegistrar.class)
public @interface EnableAncbaSwagger {

    String basePackage();

    String title() default "Api Documentation";

    String description() default "Api Documentation";

    String version() default "1.0";

    String email() default "laozhang@azlinli.com";
}
