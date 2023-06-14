package ua.lab2.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import ua.lab2.security.JwtValidator;

import java.util.Map;
@Slf4j
public class ValidateJWTAccessTokenInterceptor implements HandlerInterceptor {
    JwtValidator jwtValidator;
    public ValidateJWTAccessTokenInterceptor(JwtValidator jwtValidator) {
        this.jwtValidator = jwtValidator;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object handler)
            throws Exception {
        log.debug("ValidateJWTAccessTokenInterceptor intercepting request to " + httpRequest.getRequestURI());
        String authorizationHeader = httpRequest.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            httpResponse.sendError(401, "Invalid Authorization header structure");
            return false;
        }
        String token = authorizationHeader.substring("Bearer ".length());
        log.debug(token);
        try {
            jwtValidator.validate(token);
            log.debug("ValidateJWTAccessTokenInterceptor: token is valid");
            return true;
        }
        catch (Exception e) {
            log.error(e.getMessage());
            httpResponse.sendError(401, e.getMessage());
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        // noting
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // noting
    }
}
//@Slf4j
//public class ValidateJWTAccessTokenInterceptor implements HandshakeInterceptor {
//    JwtValidator jwtValidator;
//    public ValidateJWTAccessTokenInterceptor(JwtValidator jwtValidator) {
//        this.jwtValidator = jwtValidator;
//    }
//
//    @Override
//    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
//        log.debug("ValidateJWTAccessTokenInterceptor intercepting request to " + request.getURI());
//        String authorizationHeader = request.getHeaders().getFirst("Authorization");
//        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
//            response.setStatusCode(HttpStatusCode.valueOf(401));
//            return false;
//        }
//        String token = authorizationHeader.substring("Bearer ".length());
//        log.debug(token);
//        try {
//            jwtValidator.validate(token);
//            log.debug("ValidateJWTAccessTokenInterceptor: token is valid");
//            return true;
//        }
//        catch (Exception e) {
//            log.error(e.getMessage());
//            response.setStatusCode(HttpStatusCode.valueOf(401));
//            return false;
//        }
//    }
//
//    @Override
//    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
//        // noting
//    }
//}
