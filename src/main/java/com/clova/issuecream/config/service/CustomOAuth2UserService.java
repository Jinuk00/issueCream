package com.clova.issuecream.config.service;

import com.clova.issuecream.config.dto.CustomOAuth2User;
import com.clova.issuecream.config.dto.KakaoResponse;
import com.clova.issuecream.config.dto.OAuth2Response;
import com.clova.issuecream.login.dto.MemberDto;
import com.clova.issuecream.login.entity.Member;
import com.clova.issuecream.login.enums.Role;
import com.clova.issuecream.login.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        // 부모 클래스의 메서드를 사용하여 객체를 생성함.
        OAuth2User oAuth2User = super.loadUser(request);
        log.info("들어오나? {}", request);

        // 제공자
        String registration = request.getClientRegistration().getRegistrationId();

        // 제공자별로 객체를 구현하여 OAuth2Response 타입으로 반환할거다.
        // 이 다음 섹션에서 구현할거다.
//        OAuth2Response oAuth2Response = null;
        OAuth2Response oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());

        // 제공자별 분기 처리
//        if("kakao".equals(registration)) {
//            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
//        } else if("naver".equals(registration)) {
//            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
//        } else if("google".equals(registration)) {
//            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
//        } else {
//            return null;
//        }

        // 사용자명을 제공자_회원아이디 형식으로만들어 저장할거다. 이 값은 redis에서도 key로 쓰일 예정
        String username = oAuth2Response.getProvider() + "_" + oAuth2Response.getProviderId();

        // 넘어온 회원정보가 이미 우리의 테이블에 존재하는지 확인
        Optional<Member> checkMember = memberRepository.findByUsername(username);

        // 존재하지 않는다면 회원정보를 저장하고 CustomOAuth2User 반환
        if(checkMember.isEmpty()) {
            Member member = Member.builder()
                    .username(username)
                    .name(oAuth2Response.getName())
                    .email(oAuth2Response.getEmail())
                    .role(Role.USER)
                    .build();
            memberRepository.save(member);
        }
//        else {		// 회원정보가 존재한다면 조회된 데이터로 반환한다.
//            Member member = checkMember.get();
//            existData.setEmail(oAuth2Response.getEmail());
//            existData.setName(oAuth2Response.getName());
//            existData.setProfileImage(oAuth2Response.getProfileImage());
//
//            userRepository.save(existData);
//
//            UserDTO userDTO = new UserDTO();
//            userDTO.setUserName(username);
//            userDTO.setName(existData.getName());
//            userDTO.setProfileImage(existData.getProfileImage());
//            userDTO.setRole("ROLE_USER");
//
//            return new CustomOAuth2User(userDTO);
//        }
        return new CustomOAuth2User(checkMember.get().toDto());
    }

}
