package ua.lab2.interceptors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ua.lab2.security.JwtValidator;

@Slf4j
public class UserIdentificationInterceptor implements HandlerInterceptor {

    public UserIdentificationInterceptor() {
    }

    @Override
    public boolean preHandle(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object handler)
            throws Exception {
        log.debug("UserIdentificationInterceptor intercepting request to " + httpRequest.getRequestURI());
        String authorizationHeader = httpRequest.getHeader("Authorization");
        String token = authorizationHeader.substring("Bearer ".length());
        try {
            final DecodedJWT jwt = JWT.decode(token);
            log.debug(jwt.getClaim("sub").asString());
            log.debug(jwt.getClaim("name").asString());
            httpRequest.setAttribute("userId", jwt.getClaim("sub").asString());
            httpRequest.setAttribute("fullName", jwt.getClaim("name").asString());
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
