package com.example.simple_resful.config;

import com.example.simple_resful.service.MyAppUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAppUserDetailService myAppUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/about", "/home", "/").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/control/**").hasAnyRole("ADMIN", "USER")
                .and().formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/app-login")
                    .usernameParameter("app_username")
                    .passwordParameter("app_password")
                    .defaultSuccessUrl("/control")
                .and().logout()
                    .logoutUrl("/app-logout")
                    .logoutSuccessUrl("/login")
                .and().exceptionHandling()
                    .accessDeniedPage("/error/403");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myAppUserDetailService)
                .passwordEncoder(passwordEncoder());
    }

}
