package com.archives.DistributorStore.security.middlewares;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private final UserDetailsService userDetailsService;

    @Override
    @SuppressWarnings("null")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
        }

        String tokenJwt = authorization.substring(7);
        String storeNic = null;

        try {
            storeNic = jwtServices.extractNicStore(tokenJwt);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Bearer authentication");
        }

        if (storeNic == null || SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails storeDetails = userDetailsService.loadUserByUsername(storeNic);

            try {
                if (!jwtServices.isTokenExpired(tokenJwt)) {
                    UsernamePasswordAuthenticationToken authStore = new UsernamePasswordAuthenticationToken(storeNic,
                            null, storeDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authStore);

                }
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "invalid token - Request not authorize");
            }
        }
    }
}
