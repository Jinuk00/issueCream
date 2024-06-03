package com.clova.issuecream.config;


import com.clova.issuecream.config.filter.JWTFilter;
import com.clova.issuecream.config.handler.CustomSuccessHandler;
import com.clova.issuecream.config.service.CustomOAuth2UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JWTUtil jwtUtil;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomSuccessHandler customSuccessHandler;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() { // security를 적용하지 않을 리소스
        return web -> web.ignoring()
                // error endpoint를 열어줘야 함, favicon.ico 추가!
                .requestMatchers(AntPathRequestMatcher.antMatcher("/error"), AntPathRequestMatcher.antMatcher("/favicon.ico"),
                        AntPathRequestMatcher.antMatcher("/index.html"),
                        AntPathRequestMatcher.antMatcher("/js/**"),
                        PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(request ->
                        request.requestMatchers(
                                        "/",
                                        "/login/**"
                                ).permitAll()
                                .anyRequest().authenticated()
                )
                .oauth2Login(
                        oauth2Login ->
                                oauth2Login
                                        .userInfoEndpoint(userInfoEndpointConfig ->
                                                userInfoEndpointConfig.userService(customOAuth2UserService))
                                        .successHandler(customSuccessHandler)
                                        .loginPage("/")
                );
//                .addFilterBefore(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)

        return http.build();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                // rest api 설정
//                .csrf(AbstractHttpConfigurer::disable) // csrf 비활성화 -> cookie를 사용하지 않으면 꺼도 된다. (cookie를 사용할 경우 httpOnly(XSS 방어), sameSite(CSRF 방어)로 방어해야 한다.)
//                .cors(AbstractHttpConfigurer::disable) // cors 비활성화 -> 프론트와 연결 시 따로 설정 필요
//                .httpBasic(AbstractHttpConfigurer::disable) // 기본 인증 로그인 비활성화
//                .formLogin(AbstractHttpConfigurer::disable) // 기본 login form 비활성화
//                .logout(AbstractHttpConfigurer::disable) // 기본 logout 비활성화
//                .headers(c -> c.frameOptions(
//                        HeadersConfigurer.FrameOptionsConfig::disable).disable()) // X-Frame-Options 비활성화
//                .sessionManagement(c ->
//                        c.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 사용하지 않음
//
//                // request 인증, 인가 설정
//                .authorizeHttpRequests(request ->
//                        request.requestMatchers(
//                                new AntPathRequestMatcher("/"),
//                                new AntPathRequestMatcher("/auth/success")
//                                ).permitAll()
//                .anyRequest().authenticated()
//                )
//
//        // oauth2 설정
//                .oauth2Login(oauth -> // OAuth2 로그인 기능에 대한 여러 설정의 진입점
//                // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정을 담당
//                oauth.userInfoEndpoint(c -> c.userService(oAuth2UserService))
//                        // 로그인 성공 시 핸들러
//                        .successHandler(oAuth2SuccessHandler)
//        );
//
//                // jwt 관련 설정
////                .addFilterBefore(tokenAuthenticationFilter,
////                        UsernamePasswordAuthenticationFilter.class)
////                .addFilterBefore(new TokenExceptionFilter(), tokenAuthenticationFilter.getClass()) // 토큰 예외 핸들링
//
//                // 인증 예외 핸들링
////                .exceptionHandling((exceptions) -> exceptions
////                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
////                        .accessDeniedHandler(new CustomAccessDeniedHandler()));
//
//        return http.build();
//    }
}