package com.review.shop.domain.auth.security.config;

import com.review.shop.domain.auth.security.filter.JwtFilter;
import com.review.shop.domain.auth.security.handler.CustomAuthenticationEntryPoint;
import com.review.shop.domain.auth.security.service.CustomUserDetailsService;
import com.review.shop.domain.auth.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;

    private static final String[] AUTH_WHITELIST = {
            "/auth/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
    };



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(Customizer.withDefaults());

        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.formLogin(AbstractHttpConfigurer::disable);

        http.httpBasic(AbstractHttpConfigurer::disable);

        http.addFilterBefore(new JwtFilter(customUserDetailsService, jwtUtil), UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(authenticationEntryPoint)
                // .accessDeniedHandler(accessDeniedHandler) // 필요시 AccessDeniedHandler 추가
        );

        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated()
        );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
