package com.example.case_study.config;

import com.example.case_study.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;


import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }


    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/", "/login").permitAll();
        http.authorizeRequests().antMatchers("/employee/create","/employee/edit/*","/employee/delete/*")
                .access("hasAnyRole('Director','HR')");
        http.authorizeRequests().antMatchers("/employee")
                        .access("hasAnyRole('Director','SaleManager','HR','ApartmentManager')");
        http.authorizeRequests().antMatchers("/customer/create")
                        .access("hasAnyRole('Director','SaleManager','Business')");
        http.authorizeRequests().antMatchers("/customer/edit/*","/customer/delete/*")
                .access("hasAnyRole('Director','SaleManager')");
        http.authorizeRequests().antMatchers("/contract/create")
                        .access("hasAnyRole('Director','SaleManager','Business')");
        http.authorizeRequests().antMatchers("/contract/edit/*","/contract/delete/*")
                .access("hasAnyRole('Director','SaleManager')");
        http.authorizeRequests().antMatchers("/room/detail/*","/room/update/*","/room/maintenance")
                        .access("hasAnyRole('Director','ApartmentManager')");
        http.authorizeRequests().antMatchers("/facility/create","/facility/update","/facility/delete/*")
                        .access("hasAnyRole('Director','ApartmentManager')");
        http.authorizeRequests().antMatchers("/account")
                .access("hasRole('Director')");
        http.authorizeRequests().antMatchers("/contract/manager")
                        .access("hasRole('SaleManager')");
        http.authorizeRequests().antMatchers("/contract/director")
                .access("hasRole('Director')");
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/400");
        http.authorizeRequests().and().formLogin()
                .loginProcessingUrl("/j_spring_security_check") // submit url
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard")//
                .failureUrl("/login?error=true")//
                .usernameParameter("phoneNumber")//
                .passwordParameter("password")
                // Cấu hình cho Logout Page.
                .and().logout().logoutUrl("/logout")
                .logoutSuccessUrl("/logoutSuccessful");
        // Cấu hình Remember Me.
        http.authorizeRequests().and() //
                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(24 * 60 * 60); // 24h
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
        return memory;
    }
}
