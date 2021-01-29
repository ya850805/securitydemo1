package com.practice.securitydemo1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfigTest extends WebSecurityConfigurerAdapter {

  @Autowired private UserDetailsService userDetailsService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.formLogin() // 自定義自己編寫的登入頁面
        .loginPage("/login.html") // 登入頁面設置
        .loginProcessingUrl("/user/login") // 登入訪問路徑
        .defaultSuccessUrl("/index") // 登入成功後，跳轉路徑
        .permitAll()
        .and()
        .authorizeRequests()
        .antMatchers("/", "/user/login", "/hello") // 設置哪些路徑可以直接訪問，不需要認證
        .permitAll()
//        .antMatchers("/index").hasAuthority("admins,manager") // 當前登入用戶需要有admins權限才能訪問/index
        .antMatchers("/index").hasAnyAuthority("admins,manager") // 當前登入用戶需要有admins或manager權限才能訪問/index
        .anyRequest()
        .authenticated()
        .and()
        .csrf()
        .disable(); // 關閉csrf防護
  }

  //  @Override
  //  protected void configure(HttpSecurity http) throws Exception {
  //    http.csrf().ignoringAntMatchers("/h2-console/**");
  //    http.headers().frameOptions().sameOrigin();
  //  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
