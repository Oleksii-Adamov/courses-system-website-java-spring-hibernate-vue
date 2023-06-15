package ua.lab2.interceptors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class AccessInterceptor implements HandlerInterceptor {

    private final List<String> teacherPaths = new ArrayList<>(List.of(new String[]{"/api/courses/teacher-courses", "/api/courses/create", "/api/courses/grade-student", "/api/courses/course-students"}));

    private final List<String> studentPaths = new ArrayList<>(List.of(new String[]{"/api/courses/student-courses", "/api/courses/student-grade", "/api/courses/join"}));

    public AccessInterceptor() {
    }

    @Override
    public boolean preHandle(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object handler)
            throws Exception {
        log.debug("AccessInterceptor intercepting request to " + httpRequest.getRequestURI());
        log.debug(httpRequest.getPathInfo());
        String authorizationHeader = httpRequest.getHeader("Authorization");
        String token = authorizationHeader.substring("Bearer ".length());
        try {
            final DecodedJWT jwt = JWT.decode(token);
            JSONArray jsonArray = new JSONArray(jwt.getClaim("realm_access").asMap().get("roles").toString());
            Iterator<Object> iterator = jsonArray.iterator();
            List<String> roles = new ArrayList<>();
            while(iterator.hasNext()) {
                roles.add((String) iterator.next());
            }
            String pathInfo = httpRequest.getPathInfo();
            if ((!roles.contains("Teacher") && teacherPaths.contains(pathInfo))
                    || (!roles.contains("Student") && studentPaths.contains(pathInfo))) {
                log.info("Access denied, you don't have corresponding role");
                httpResponse.sendError(401, "Access denied, you don't have corresponding role");
                return false;
            }
            else {
                return true;
            }
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
