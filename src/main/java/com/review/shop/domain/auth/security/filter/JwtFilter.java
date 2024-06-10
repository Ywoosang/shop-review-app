package com.review.shop.domain.auth.security.filter;

import com.review.shop.domain.auth.security.service.CustomUserDetailsService;
import com.review.shop.domain.auth.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            String accessToken = authHeader.substring(7);
            if(jwtUtil.validateToken(accessToken)) {
                String email = jwtUtil.getUserEmail(accessToken);

                // 실제 가입된 사용자인지 체크
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
                if(userDetails != null) {
                    // Filter 를 통해 AuthenticationToken 을 Authentication Manager 로 위임
                    // 사용자의 principal 과 credential 정보를 Authentication 안에 담는다.
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    // 현재 Request 의 Security Context 에 접근권한 설정
                    // Authentication 을 SecurityContext 에 보관한다.
                    // 이 SecurityContext 를 SecurityContextHolder 에 담아 보관한다.
                    // 인증 완료 후의 객체 생성
                    // 해당 요청이 필터를 거쳐 인가에 성공해 승인된 Request 라는 의미
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
