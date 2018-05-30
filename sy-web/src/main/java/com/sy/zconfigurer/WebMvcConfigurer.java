package com.sy.zconfigurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sy.filter.UserAuthFilter;

/**
 *
 * @author cck
 */
@Configuration
@EnableWebMvc
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
         registry.addInterceptor(new UserAuthFilter());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("file:D:/suoyuan/", "classpath:/static/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("\n\n addCorsMappings \n\n");
         registry.addMapping("/**/*")
         .allowedHeaders("X-Requested-With", "Content-Type", "Accept",
             "token","Access-Control-Request-Headers",
             "Access-Control-Request-Method")
         .allowedMethods("GET", "POST", "PUT", "OPTIONS")
         .allowedOrigins("*")
         .maxAge(Integer.MAX_VALUE);
    }

}
