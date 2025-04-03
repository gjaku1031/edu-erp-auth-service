package com.eduerp.auth_service.security;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {

    
    private final Key key;
    private final long accessTokenValidityInSeconds;
    private final long refreshTokenValidityInSeconds;

    /**
     * JwtTokenProvider 생성자
     * 
     * @param secret                        JWT 서명에 사용할 비밀키 문자열
     * @param accessTokenValidityInSeconds  액세스 토큰 유효 기간 (초)
     * @param refreshTokenValidityInSeconds 리프레시 토큰 유효 기간 (초)
     */
    public JwtTokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-token-validity-in-seconds}") long accessTokenValidityInSeconds,
            @Value("${jwt.refresh-token-validity-in-seconds}") long refreshTokenValidityInSeconds) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.accessTokenValidityInSeconds = accessTokenValidityInSeconds;
        this.refreshTokenValidityInSeconds = refreshTokenValidityInSeconds;
    }

    /**
     * 액세스 토큰을 생성합니다.
     * 
     * @param authentication 인증 정보
     * @return String 생성된 JWT 액세스 토큰
     */
    public String createAccessToken(Authentication authentication) {
        return createToken(authentication, accessTokenValidityInSeconds);
    }

  
    public String createRefreshToken(Authentication authentication) {
        return createToken(authentication, refreshTokenValidityInSeconds);
    }
    
    private String createToken(Authentication authentication, long validityInSeconds) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity = new Date(now + validityInSeconds * 1000);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .setIssuedAt(new Date(now))
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    
}
