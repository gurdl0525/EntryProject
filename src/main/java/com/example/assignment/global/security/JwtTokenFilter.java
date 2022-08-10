package com.example.assignment.global.security;

import com.example.assignment.global.error.exception.InvalidTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        try {
            String token = jwtTokenProvider.resolveToken(request);
            if(token != null && jwtTokenProvider.validateToken(token)){
                var auth = jwtTokenProvider.authentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch (InvalidTokenException e){
            SecurityContextHolder.clearContext();
        }
        chain.doFilter(request, response);
    }

}
