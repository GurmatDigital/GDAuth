package com.gd.auth.app.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * FUTURE:
 * - Read Authorization: Bearer <token>
 * - Validate JWT
 * - Build Authentication in SecurityContext
 *
 * We are not wiring this into SecurityConfig yet.
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // TODO: implement later
        filterChain.doFilter(request, response);
    }
}

