package com.sy.zconfigurer;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sy.filter.UserAuthFilter;

/**
 * 
 * @author cck
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = { WebMvcConfigurer.class })
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new UserAuthFilter());
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**/*")
			.allowedHeaders("X-Requested-With", "Content-Type", 
					"Accept", "token", "Access-Control-Allow-Origin:*")
			.allowedMethods("GET", "POST", "PUT")
			.allowedOrigins("*")
			.maxAge(Integer.MAX_VALUE);
	}
	
}
