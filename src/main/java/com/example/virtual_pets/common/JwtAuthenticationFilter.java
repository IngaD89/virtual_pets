package com.example.virtual_pets.common;

import com.example.virtual_pets.common.utils.JwtUtils;
import com.example.virtual_pets.models.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LogManager.getLogger(JwtAuthenticationFilter.class);

    private final JwtUtils jwtUtils;

    // List of public routes (excluded from the filter)
    private static final Set<String> PUBLIC_PATHS = Set.of(
            "/auth/register",
            "/auth/login",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**"
    );

    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain chain
    ) throws ServletException, IOException {

        String token = jwtUtils.extractToken(request);

        if (token != null) {
            try {
                if (jwtUtils.validateToken(token)) {
                    LOGGER.info("Valid token. Extracting claims...");
                    Claims claims = jwtUtils.extractClaims(token);
                    String username = claims.getSubject();
                    Role role = jwtUtils.extractRole(token);

                    LOGGER.info("Claims extracted. User: {}, Role: {}", username, role);

                    List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_" + role.name());
                    Authentication auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(auth);

                    LOGGER.info("User authenticated successfully: {}", username);
                }
            } catch (ExpiredJwtException e) {
                LOGGER.error("Expired token: {}", token);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Expired token");
                return;
            } catch (JwtException e) {
                LOGGER.error("Invalid token signature: {}", token);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return;
            } catch (Exception e) {
                LOGGER.error("Error validating token: {}", token, e);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication error");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        boolean isPublic = PUBLIC_PATHS.stream().anyMatch(path::startsWith);
        LOGGER.info("Checking path: {} - Public: {}", path, isPublic);
        return isPublic;
    }
}