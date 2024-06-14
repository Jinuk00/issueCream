package com.clova.issuecream.login.service;

import com.clova.issuecream.common.enums.ResultCode;
import com.clova.issuecream.login.entity.Member;
import com.clova.issuecream.login.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    private final MemberRepository memberRepository;

    @Value("${spring.kakao.admin-key}")
    private String kakaoAdminKey;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoClientId;

    @Value("${spring.logout-url}")
    private String logoutRedirectUrl;

    public ResultCode deleteUser(String email) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if (optionalMember.isPresent()) {
            memberRepository.delete(optionalMember.get());
            return ResultCode.OK;
        }
        return ResultCode.incorrect;
    }

    public Member searchMemberByEmail(String email) {
        return memberRepository.findByEmail(email).orElse(null);
    }

    public void logoutKakao(String kakaoUserId) {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://kapi.kakao.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .defaultHeader("Authorization", "KakaoAK " + kakaoAdminKey)
                .build();

        String block = webClient.post()
                .uri("v1/user/logout")
                .bodyValue("target_id_type=user_id&target_id=" + kakaoUserId)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.info("처리값 {}",block);

    }
    
    //카카오에 등록된 유저 서비스 끊기
    public void deleteKakaoUserInfo(String kakaoUserId) {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://kapi.kakao.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .defaultHeader("Authorization", "KakaoAK " + kakaoAdminKey)
                .build();
        String url = "/v1/user/unlink";

        String block = webClient.post()
                .uri(url)
                .bodyValue("target_id_type=user_id&target_id=" + kakaoUserId)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.info("값 {}", block);
    }
}
