package club.neters.blog.core.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实体类注释
 * 用于代码生成器获取实体类注释及其字段注释
 *
 * @author laozhang
 * @date 2021/06/12
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityDoc {

    /**
     * 注释
     * 类注释/字段注释
     *
     * @return note
     */
    String note() default "";

    /**
     * 是否属于类注释
     *
     * @return 是否是类
     */
    boolean isClass() default false;
}
