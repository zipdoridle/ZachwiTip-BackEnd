package com.zipdoridle.zachwitip.config;

import com.zipdoridle.zachwitip.auth.handler.OAuthLoginSuccessfulHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final OAuthLoginSuccessfulHandler oAuthLoginSuccessfulHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/images/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/");

        http.oauth2Login()
                .userInfoEndpoint()
                .userService(new DefaultOAuth2UserService())
                .and()
                .successHandler(oAuthLoginSuccessfulHandler)
                .failureUrl("/login")
                .permitAll();

        http.exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendRedirect("/login"))
                .accessDeniedHandler((request, response, accessDeniedException) -> response.sendRedirect("/"));

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .mvcMatchers(HttpMethod.GET, "/**/api-docs/**")
                .mvcMatchers(HttpMethod.GET, "/swagger-resources/**")
                .mvcMatchers(HttpMethod.GET, "/swagger-ui/**")
                .mvcMatchers(HttpMethod.GET,"/webjars/**")
                .mvcMatchers(HttpMethod.GET, "/swagger/**");

    }
}
