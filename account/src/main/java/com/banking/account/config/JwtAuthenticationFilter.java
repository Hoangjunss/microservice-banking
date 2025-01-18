package com.banking.account.config;


import com.banking.account.redis.RedisContants;
import com.banking.account.redis.RedisService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private OurUserDetailsService ourUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisService redisService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;

        if (authHeader == null || authHeader.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        jwtToken = authHeader.substring(7); // Loại bỏ tiền tố "Bearer "
        userEmail = jwtTokenUtil.extractUsernameToken(jwtToken);

        // Kiểm tra token từ Redis
        String tokenKey = RedisContants.WHITE_LIST_PASSWORD + jwtToken;
        ObjectMapper objectMapper = new ObjectMapper();
        UserDetails userDetails;

        try {
            // Lấy user từ Redis nếu có
            String userJson = (String) redisService.get(tokenKey);
            if (userJson != null) {
                // Chuyển đổi JSON thành `UserDetails`
                userDetails = objectMapper.readValue(userJson, UserDetails.class);
                log.info("User details retrieved from Redis for token: {}", userDetails);
            } else {
                // Nếu không tìm thấy trong Redis, kiểm tra trong DB
                if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    userDetails = ourUserDetailsService.loadUserByUsername(userEmail);

                    // Nếu token hợp lệ, lưu vào Redis để dùng cho lần tiếp theo
                    if (jwtTokenUtil.isTokenValid(jwtToken, userDetails)) {
                        long ttl = jwtTokenUtil.getExpirationDate(jwtToken).getTime() - System.currentTimeMillis();
                        String userJsonToCache = objectMapper.writeValueAsString(userDetails);
                        redisService.set(tokenKey, userJsonToCache);
                        redisService.setTimeToLive(tokenKey, ttl / 1000); // Đặt TTL bằng giây
                    } else {
                        filterChain.doFilter(request, response);
                        return;
                    }
                } else {
                    filterChain.doFilter(request, response);
                    return;
                }
            }

            // Nếu xác thực thành công, thiết lập `SecurityContext`
            if (jwtTokenUtil.isTokenValid(jwtToken, userDetails)) {
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                log.info("Role: {}", userDetails.getAuthorities().toString());

                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);
            }

        } catch (Exception e) {
            log.error("Error processing JWT token or Redis: {}", e.getMessage());
            filterChain.doFilter(request, response);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
