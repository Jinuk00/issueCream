package com.clova.issuecream.login.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@Slf4j
public class LoginController {

    @GetMapping(value = "/kakao/login")
    public void kakaoLogin(@RequestParam String code) {
        log.info("코드 {}", code);
    }
}
