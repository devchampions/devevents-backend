package com.devchampions.infrastructure.auth;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class AuthConfiguration extends WebSecurityConfigurerAdapter {

    @Value(value = "${auth0.clientId}")
    private String clientId;
    
    @Value(value = "${auth0.issuer}")
    private String issuer;

    @Value(value = "${auth0.clientSecret}")
    private String clientSecret;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtWebSecurityConfigurer
                .forHS256(clientId, issuer, clientSecret.getBytes())
                .configure(http)
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/events").permitAll()
                .antMatchers(HttpMethod.GET, "/events").permitAll()
                .antMatchers(HttpMethod.GET, "/info").permitAll()
                .antMatchers(HttpMethod.GET, "/h2-console/**").permitAll()
                .antMatchers(HttpMethod.GET, "/error").permitAll()
                .antMatchers(HttpMethod.GET, "/events/**").permitAll()
                .anyRequest().authenticated();
    }
}
