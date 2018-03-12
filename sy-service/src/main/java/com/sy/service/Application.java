package com.sy.service;

import java.util.concurrent.CountDownLatch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 
 * @author cck
 */
@MapperScan(basePackages = { "com.sy.mapper" })
@SpringBootApplication(scanBasePackageClasses = Application.class)
public class Application {

	public static void main(String[] args) throws InterruptedException {
		
		ApplicationContext ctx = new SpringApplicationBuilder()
				.sources(Application.class)
				.web(false).run(args);
//		CountDownLatch closeLatch = ctx.getBean(CountDownLatch.class);
//		closeLatch.await();
	}

	@Bean
	public CountDownLatch closeLatch() {
		
		return new CountDownLatch(1);
	}

}
