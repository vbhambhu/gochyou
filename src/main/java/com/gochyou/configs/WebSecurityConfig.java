package com.gochyou.configs;


import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;


    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username,pass, status from users where username=?");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

	   http.authorizeRequests()
	   .antMatchers("/", "/api/**","/register").permitAll()
               .anyRequest().authenticated()
               .and()
               .formLogin()
               .loginPage("/login")
               .permitAll()
               .and()
               .logout()
               .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
               .permitAll();
    }
}