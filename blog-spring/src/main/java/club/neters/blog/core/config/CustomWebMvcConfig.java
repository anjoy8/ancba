package club.neters.blog.core.config;

import club.neters.blog.core.Interceptor.UserLoginAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class CustomWebMvcConfig implements WebMvcConfigurer {
    @Resource
    private UserLoginAuthInterceptor userLoginAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加对用户未登录的拦截器，并添加排除项
        //error路径，excludePathPatterns排除访问的路径在项目中不存在的时候，
        //springboot会将路径变成 /error, 导致无法进行排除。
        registry.addInterceptor(userLoginAuthInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/js/**", "/css/**", "/img/**", "/plugins/**")
                .excludePathPatterns("/api/login")
                .excludePathPatterns("/error");
    }
}
