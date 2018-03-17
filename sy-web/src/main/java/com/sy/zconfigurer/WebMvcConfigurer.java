package com.sy.zconfigurer;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
	
}
