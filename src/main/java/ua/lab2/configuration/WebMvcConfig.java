package ua.lab2.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ua.lab2.interceptors.UserIdentificationInterceptor;
import ua.lab2.interceptors.ValidateJWTAccessTokenInterceptor;
import ua.lab2.security.JwtValidator;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        JwtValidator jwtValidator = new JwtValidator();
        registry.addInterceptor(new ValidateJWTAccessTokenInterceptor(jwtValidator));
        registry.addInterceptor(new UserIdentificationInterceptor());
    }
}
