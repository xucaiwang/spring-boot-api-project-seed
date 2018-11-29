package com.company.project.configurer;

import com.company.project.auth.CustomAuthenticationManager;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.filter.JWTAuthenticationFilter;
import com.company.project.filter.JWTLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  安全配置类
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.parentAuthenticationManager(new CustomAuthenticationManager(userDetailsService));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //禁用 csrf
        http.cors().and().csrf().disable().authorizeRequests()
                //允许以下请求
                .antMatchers("/v1_0_0/sessionTest/**","/v1_0_0/user/regisiter","/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security",
                        "/swagger-ui.html", "/webjars/**","/swagger-resources/configuration/ui","/swagge‌​r-ui.html").permitAll()
                // 所有请求需要身份认证
                .anyRequest().authenticated()
                .and()
                //设置登出
                .logout().permitAll();
                //验证登陆
        JWTLoginFilter jwtLoginFilter = new JWTLoginFilter(authenticationManager());
        jwtLoginFilter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                httpServletResponse.setContentType("application/json;charset=UTF-8");
                httpServletResponse.getWriter().println( new Result().setCode(ResultCode.FAIL).setMessage(e.getMessage()).toString());
            }
        });
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter(authenticationManager());
        http.addFilter(jwtLoginFilter)
                //验证token
                .addFilter(jwtAuthenticationFilter);


        http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                httpServletResponse.setContentType("application/json;charset=UTF-8");
                httpServletResponse.getWriter().println( new Result().setCode(ResultCode.UNAUTHORIZED).setMessage("请登陆!").toString());
            }
        });
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
