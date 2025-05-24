package com.ceos.vote.security.config;

import com.ceos.vote.security.filter.CustomAuthenticationFilter;
import com.ceos.vote.security.filter.JwtAuthorizationFilter;
import com.ceos.vote.security.filter.JwtExceptionFilter;
import com.ceos.vote.security.handler.JwtAuthenticationFailureHandler;
import com.ceos.vote.security.handler.JwtAuthenticationSuccessHandler;
import com.ceos.vote.security.handler.JwtLogoutSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsConfig corsConfig;

    private final JwtLogoutSuccessHandler jwtLogoutSuccessHandler;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final JwtExceptionFilter jwtExceptionFilter;
    private final JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;
    private final JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler;

    private final AuthenticationConfiguration authenticationConfiguration;


    private static final String[] WHITE_LIST = {
            "/user/v1/login",
            "/user/v1/join",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/actuator/health",
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))

                // cors
                .cors(cors -> cors.configurationSource(corsConfig.apiCorsConfigurationSource()))

                // 경로별 인가
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITE_LIST).permitAll()
                        .anyRequest().authenticated()
                )

                // 로그아웃 추가
                .logout(logout -> logout
                        .logoutUrl("/user/v1/logout")
                        .logoutSuccessHandler(jwtLogoutSuccessHandler)
                        .permitAll()
                )

                // 필터 추가
                .addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthorizationFilter, CustomAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFilter, jwtAuthorizationFilter.getClass());

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers("/error",
                                 "/favicon.ico"
                );
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager(authenticationConfiguration));
        customAuthenticationFilter.setFilterProcessesUrl("/user/v1/login");
        customAuthenticationFilter.setAuthenticationSuccessHandler(jwtAuthenticationSuccessHandler);
        customAuthenticationFilter.setAuthenticationFailureHandler(jwtAuthenticationFailureHandler);
        customAuthenticationFilter.afterPropertiesSet();
        return customAuthenticationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
