package com.clova.issuecream.config.filter;

import com.clova.issuecream.common.exception.UserException;
import com.clova.issuecream.config.JWTUtil;
import com.clova.issuecream.config.dto.CustomOAuth2User;
import com.clova.issuecream.login.dto.MemberDto;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 요청 헤더에 있는 access라는 값을 가져오자 이게 accessToken이다.
        String accessToken = request.getHeader("access");

        // 요청헤더에 access가 없는 경우
        if(accessToken  == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // Bearer 제거 <- oAuth2를 이용했다고 명시적으로 붙여주는 타입인데 JWT를 검증하거나 정보를 추출 시 제거해줘야한다.
        String originToken = accessToken.substring(7);

        // 유효한지 확인 후 클라이언트로 상태 코드 응답
        // Token 만료 확인
        try {
            if (jwtUtil.isExpired(originToken)) {

            }
        } catch (ExpiredJwtException jwtException) {
            filterChain.doFilter(request, response);
            return;
        }
//        if (jwtUtil.isExpired(originToken)) {
//            Optional<Cookie> refresh = Arrays.stream(request.getCookies()).filter(i -> i.getName().equals("refresh"))
//                    .findAny();
//            if (refresh.isEmpty()) {
//                throw new UserException("로그아웃 됐습니다. 다시 로그인 해주세요.");
//            }
//            String refreshToken = refresh.get().getValue();
//
//            if (jwtUtil.isExpired(refreshToken)) {
//                throw new UserException("로그아웃 됐습니다. 다시 로그인 해주세요.");
//            }
//
//            String email = jwtUtil.getEmail(refreshToken);
//            String role = jwtUtil.getRole(refreshToken);
//            String newAccessToken = jwtUtil.createJwt("access", email, role, 5 * 60 * 1000L); // 5 minutes
//
//            response.setHeader("access", newAccessToken);
//        }
//        try {
//            if(jwtUtil.isExpired(originToken)) {
//                PrintWriter writer = response.getWriter();
//                writer.println("access token expired");
//
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                return;
//            }
//        } catch (ExpiredJwtException e) {
//            PrintWriter writer = response.getWriter();
//            writer.println("access token expired");
//
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
        log.info("요청 url 확인 :{} \n {}", request.getRequestURI(), request.getRequestURL());

        // accessToken인지 refreshToken인지 확인
        String category = jwtUtil.getCategory(originToken);

        // JWTFilter는 요청에 대해 accessToken만 취급하므로 access인지 확인
        if(!"access".equals(category)) {
            PrintWriter writer = response.getWriter();
            writer.println("invalid access token");

            response.setStatus(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
            return;
        }

        // 사용자명과 권한을 accessToken에서 추출
        String email = jwtUtil.getEmail(originToken);
        String role = jwtUtil.getRole(originToken);

        MemberDto userDTO = MemberDto.builder()
                .email(email).role(role)
                .build();

        CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDTO);

        Authentication authentication = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
