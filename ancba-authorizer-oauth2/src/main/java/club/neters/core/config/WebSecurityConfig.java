package club.neters.core.config;

import club.neters.app.service.UserDetailService;
import club.neters.core.util.UserPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserDetailService userDetailService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .requestMatchers()
                .antMatchers("/oauth/authorize**", "/login**", "/error**")
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 内存模式
//        auth.inMemoryAuthentication()
//                .withUser("laozhang")
//                .password(passwordEncoder.encode("123456"))
//                .roles("USER");

        // 自定义用户服务模式
        auth.userDetailsService(userDetailService)
                .passwordEncoder(new UserPasswordEncoder());
    }

}
