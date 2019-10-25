package com.aaa.huahui.config;

import com.aaa.huahui.config.authenticationhandler.LoginFailHandler;
import com.aaa.huahui.config.authenticationhandler.LoginSuccessHandler;
import com.aaa.huahui.config.authenticationhandler.LogoutSuccessHandler;
import com.aaa.huahui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${secert_key}")
    private String KEY = "dewitt";

    @Autowired
    LoginFailHandler loginFailHandler;

    @Autowired
    LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    LogoutSuccessHandler logoutSuccessHandler;


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder); // 设置密码加密方式
        return authenticationProvider;
    }

    /**
     * 自定义配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.csrf().disable();
        http.cors();


        http.authorizeRequests().antMatchers(HttpMethod.POST, "/setting").permitAll();

        //测试
        http.authorizeRequests().antMatchers("/test**", "/myself/mystar", "/course/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/test*").permitAll();

        http.authorizeRequests().antMatchers("/css/**", "/js/**", "/fonts/**",
                "/index", "/img/**", "/file/**", "/lib/**").permitAll();

        http.authorizeRequests().antMatchers(HttpMethod.POST, "/login", "/register").permitAll();
        http.authorizeRequests().antMatchers("/login", "/register").permitAll();

        //基于 Form 表单登录验证
        http.formLogin().loginPage("/login")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailHandler)
                .and().rememberMe().key(KEY)
                .and().exceptionHandling().accessDeniedPage("/403");
        //监控
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/actuator/**").hasIpAddress("127.0.0.1");
        http.authorizeRequests().antMatchers("/druid/**").hasIpAddress("127.0.0.1");

        //注销登录
        http.logout().logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .invalidateHttpSession(true);

        http.headers().frameOptions().sameOrigin();

        //在测试和上传文件时禁用 csrf
//        http.csrf().ignoringAntMatchers("/uploadimg", "/test**");

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());

    }
}
