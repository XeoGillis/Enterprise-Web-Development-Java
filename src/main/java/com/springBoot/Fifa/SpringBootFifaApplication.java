package com.springBoot.Fifa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import service.VoetbalService;
import service.VoetbalServiceDb;
import service.VoetbalServiceImpl;
import validator.FifaValidation;

@SpringBootApplication
public class SpringBootFifaApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFifaApplication.class, args);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("resources/css/");
		registry.addResourceHandler("/img/**").addResourceLocations("resources/img/");
	}
	
	@Bean
	VoetbalService voetbalService() {
		return new VoetbalServiceImpl();
	}
	
	@Bean
	FifaValidation fifaValidation() {
		return new FifaValidation();
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
}
