package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private UserDetailsService userDetailService;

	@Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http
	      .authorizeRequests()
	        // アクセス制限のないURLを指定
	        .antMatchers("/").permitAll()
	        // そのほかは認証済みでしかアクセスできない
	        .anyRequest().authenticated()
	        .and()
	       // ログイン画面の設定 アクセス制限なし
	      .formLogin()
	        .loginPage("/login")
	        .permitAll()
	        .and()
	      .logout()
	        .permitAll();
	  }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // パスワード設定。公式チュートリアルの記述だとエラーが発生
        String password = passwordEncoder().encode("password");

        // インメモリの認証を行うための設定
        auth.userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder());
        
        userDetailService.registerAdmin("admin", "youmustchangethis", "admin@localhost");
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                            "/images/**",
                            "/css/**",
                            "/javascript/**"
                            );
    }
}
