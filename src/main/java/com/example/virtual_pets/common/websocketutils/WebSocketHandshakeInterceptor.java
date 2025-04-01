package com.example.virtual_pets.common.websocketutils;

import com.example.virtual_pets.common.utils.JwtUtils;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

public class WebSocketHandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    private final JwtUtils jwtUtils;

    public WebSocketHandshakeInterceptor(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes
    ) throws Exception {
        String token = getTokenFromHandshake(request);

        if (isValidToken(token)) {
            return super.beforeHandshake(request, response, wsHandler, attributes);
        } else {
            response.setStatusCode(HttpStatus.FORBIDDEN);
            return false;
        }
    }

    private String getTokenFromHandshake(ServerHttpRequest request) {
        String authHeader = request.getHeaders().getFirst("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    private boolean isValidToken(String token) {
        try {
            return jwtUtils.validateToken(token);
        } catch (JwtException e) {
            return false;
        }
    }
}
