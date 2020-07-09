package com.svc.security.config;

import com.svc.security.user.DemoUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
private final PasswordEncoder passwordEncoder;
private final DemoUserDetailsService userDetailsService;

    public SecurityConfig(PasswordEncoder passwordEncoder, DemoUserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests().
                antMatchers("/home").permitAll().
                antMatchers("/admin").hasRole("ADMIN").
                antMatchers("/user").hasRole("USER").
                and().
                formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
//        .
//                passwordEncoder(passwordEncoder);
//        auth.
//                inMemoryAuthentication().
//                withUser("user").password(passwordEncoder.encode("user")).roles("USER").and().
//                withUser("admin").password(passwordEncoder.encode("admin")).roles("ADMIN", "USER");
    }
}
