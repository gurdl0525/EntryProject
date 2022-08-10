package com.example.assignment.global.security;

import com.example.assignment.global.error.exception.InvalidTokenException;
import com.example.assignment.global.security.auth.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    @Value("${auth.jwt.secret}")
    private String secretKey;
    @Value("${auth.jwt.header}")
    private String header;
    @Value("${auth.jwt.exp.access}")
    private Long accessTokenExpiration;
    @Value("${auth.jwt.exp.refresh}")
    private Long refreshTokenExpiration;
    private final AuthDetailsService authDetailsService;

    public String generateAccessToken(String id){
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setSubject(id)
                .claim("type", "access")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .compact();
    }

    public String generateRefreshToken(String id){
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setSubject(id)
                .claim("type", "refresh")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .compact();
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(header);
        if (bearerToken != null){
            if(bearerToken.length() > 7){
                return bearerToken.substring(7);
            } else {
                throw InvalidTokenException.getInstance();
            }
        }
        return null;
    }

    public boolean validateToken(String token){
        try{
            return getTokenBody(token).getExpiration()
                    .after(new Date());
        }catch (Exception e){
            throw InvalidTokenException.getInstance();
        }
    }

    public Authentication authentication(String token){
        var authDetails = authDetailsService.loadUserByUsername(getUserId(token));
        return new UsernamePasswordAuthenticationToken(authDetails, "", authDetails.getAuthorities());
    }

    public String getUserId(String token){
        try{
            return getTokenBody(token).getSubject();
        }catch (Exception e){
            throw InvalidTokenException.getInstance();
        }
    }

    public Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
}
