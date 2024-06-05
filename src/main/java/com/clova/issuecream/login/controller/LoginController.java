package com.clova.issuecream.login.controller;

import com.clova.issuecream.config.dto.CustomOAuth2User;
import com.clova.issuecream.login.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RestController
@Slf4j
public class LoginController {

    @PostMapping(value = "/user/info")
    public String loginOAuth(@AuthenticationPrincipal CustomOAuth2User memberDto) {
        return memberDto.getName();
    }
}
