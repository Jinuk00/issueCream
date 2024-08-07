package com.clova.issuecream.login.controller;

import com.clova.issuecream.common.dto.CommonResponse;
import com.clova.issuecream.common.enums.ResultCode;
import com.clova.issuecream.config.JWTUtil;
import com.clova.issuecream.config.dto.CustomOAuth2User;
import com.clova.issuecream.config.service.RedisService;
import com.clova.issuecream.contents.service.BookmarkService;
import com.clova.issuecream.login.entity.Member;
import com.clova.issuecream.login.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final JWTUtil jwtUtil;
    private final RedisService redisService;
    private final LoginService loginService;
    private final BookmarkService bookmarkService;
    @PostMapping(value = "/user/info")
    public CommonResponse loginOAuth(@AuthenticationPrincipal CustomOAuth2User memberDto) {
        Map<String, String> result = new HashMap<>();
        result.put("email",memberDto.getEmail());
        result.put("scrapCnt", String.valueOf(bookmarkService.countScrap(memberDto.getEmail())));
        return CommonResponse.okData(result);
    }

    @PostMapping("/user/logout")
    public void logout(@AuthenticationPrincipal CustomOAuth2User memberDto, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Optional<Cookie> refreshCookie = Arrays.stream(cookies)
                .filter(cookie -> "refresh".equals(cookie.getName()))
                .findFirst();

        if (refreshCookie.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String refreshToken = refreshCookie.get().getValue();
        if (refreshToken == null || refreshToken.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String key = jwtUtil.getEmail(refreshToken);

        if (redisService.getValues(key) == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        //카카오 로그아웃
//        Member member = loginService.searchMemberByEmail(memberDto.getEmail());
//        loginService.logoutKakao(member.getUsername().replace("kakao_", ""));

        redisService.deleteValues(key);

        Cookie cookie = new Cookie("refresh", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");

        Cookie cookie2 = new Cookie("JSESSIONID", null);
        cookie2.setMaxAge(0);
        cookie2.setPath("/");

        response.setStatus(HttpServletResponse.SC_OK);
        response.addCookie(cookie);
        response.addCookie(cookie2);
    }


    @DeleteMapping("/user/info")
    public CommonResponse deleteUser(@AuthenticationPrincipal CustomOAuth2User memberDto, HttpServletResponse response) {
        //카카오 인증 끊기
        Member member = loginService.searchMemberByEmail(memberDto.getEmail());
        if (member != null) {
            loginService.deleteKakaoUserInfo(member.getUsername().replace("kakao_", ""));
        }
        //DB 삭제
        ResultCode resultCode = loginService.deleteUser(memberDto.getEmail());
        Cookie cookie = new Cookie("refresh", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");

        Cookie cookie2 = new Cookie("JSESSIONID", null);
        cookie2.setMaxAge(0);
        cookie2.setPath("/");
        response.addCookie(cookie);
        response.addCookie(cookie2);
        if (resultCode.equals(ResultCode.OK)) {
            return CommonResponse.ok("탈퇴가 완료됐습니다. 감사합니다.");
        }
        return CommonResponse.error("302", "존재하지 않는 회원입니다.");
    }
}
