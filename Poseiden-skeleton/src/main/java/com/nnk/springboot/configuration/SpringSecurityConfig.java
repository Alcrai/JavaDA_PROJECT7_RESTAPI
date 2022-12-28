package com.nnk.springboot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM Users WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username, role FROM Users WHERE username=?")
        ;

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers
                        ("/css/**").permitAll()
                .antMatchers("/*").hasAnyRole("ADMIN","USER")
                .antMatchers("/").hasAnyRole("ADMIN","USER")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .defaultSuccessUrl("/bidList/list")
                .and()
                .logout().invalidateHttpSession
                        (true).clearAuthentication(true)
                .logoutRequestMatcher
                        (new AntPathRequestMatcher("/app-logout"))
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/bidList/list");
    }

}