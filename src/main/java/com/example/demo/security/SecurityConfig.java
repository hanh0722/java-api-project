package com.example.demo.security;

import java.util.ArrayList;

import com.example.demo.security.filter.CustomAuthorizationFilter;
import com.example.demo.security.filter.CustomAuthorizationUser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthorizationFilter customAuthorizationFilter = new CustomAuthorizationFilter(authenticationManagerBean());
        customAuthorizationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/api/login").permitAll();
        // http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/user").permitAll();
        // all route for permit all, dont need to check security
        http.authorizeRequests().antMatchers("/api/user/**").permitAll();
        // error in set only check token, if it's good, can access
        // add specific route for checking security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthorizationFilter);
        http.addFilterBefore(new CustomAuthorizationUser(), UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}