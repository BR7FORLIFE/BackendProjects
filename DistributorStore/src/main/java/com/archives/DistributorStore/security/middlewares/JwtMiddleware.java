package com.archives.DistributorStore.security.middlewares;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.archives.DistributorStore.security.services.JwtServices;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtMiddleware extends OncePerRequestFilter {

    private final JwtServices jwtServices;

    @Override
    @SuppressWarnings("null")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("bearer")) {
            filterChain.doFilter(request, response);
        }

        String tokenJwt = authorization.substring(7);
        String username = null;

        try {
            username = jwtServices.extractUsername(tokenJwt);
        } catch (Exception e) {
            filterChain.doFilter(request, response);
        }

        if(username == null || SecurityContextHolder.getContext().getAuthentication() == null){
            
        }
    }
}
