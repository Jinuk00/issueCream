package com.clova.issuecream.config.handler;

import com.clova.issuecream.config.JWTUtil;
import com.clova.issuecream.config.dto.CustomOAuth2User;
import com.clova.issuecream.config.service.RedisService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.util.Collection;
import java.util.Iterator;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JWTUtil jwtUtil;

    @Value("${spring.front-target}")
    private String frontUrl;
    private final RedisService redisService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("성공 핸들러");
        CustomOAuth2User customUserDetail = (CustomOAuth2User) authentication.getPrincipal();

        // 토큰 생성시에 사용자명과 권한이 필요하니 준비하자
//        String username = customUserDetail.getName();
        String email = customUserDetail.getEmail();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        // accessToken과 refreshToken 생성
        String accessToken = jwtUtil.createJwt("access", email, role, 3600000L);
        String refreshToken = jwtUtil.createJwt("refresh", email, role, 7200000L);

        // redis에 insert (key = username / value = refreshToken)
        redisService.setValues(email, refreshToken, Duration.ofMillis(7200000L)); //2시간 유효기간

        // 응답
//        response.setHeader("Authorization", "Bearer " + accessToken);
        response.addCookie(createCookie("refresh", refreshToken));
        response.setStatus(HttpStatus.OK.value());
//        response.sendRedirect(frontUrl);        // 로그인 성공시 프론트에 알려줄 redirect 경로

        getRedirectStrategy().sendRedirect(request, response, frontUrl + "?code=" + "Bearer " + accessToken);
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(2 * 60 * 60);     // 쿠키가 살아있을 시간
        /*cookie.setSecure();*/         // https에서만 동작할것인지 (로컬은 http 환경이라 안먹음)
        /*cookie.setPath("/");*/        // 쿠키가 전역에서 동작
        cookie.setHttpOnly(true);       // http에서만 쿠키가 동작할 수 있도록 (js와 같은곳에서 가져갈 수 없도록)
        cookie.setPath("/");

        return cookie;
    }
}
