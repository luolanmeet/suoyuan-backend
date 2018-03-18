package com.sy.zconfigurer;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
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
		System.out.println("\n\n addCorsMappings \n\n");
		registry.addMapping("/**/*")
			.allowedHeaders("X-Requested-With", "Content-Type", 
					"Accept", "token", "Access-Control-Allow-Origin:*")
			.allowedMethods("GET", "POST", "PUT")
			.allowedOrigins("*")
			.maxAge(Integer.MAX_VALUE);
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
		fastJsonHttpMessageConverter.setSupportedMediaTypes(
				Arrays.asList(MediaType.ALL));
		fastJsonHttpMessageConverter.setDefaultCharset(StandardCharsets.UTF_8);
		
		converters.add(fastJsonHttpMessageConverter);
	}
	
}
