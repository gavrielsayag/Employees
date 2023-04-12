package com.gavriel.employees.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gavriel.employees.interceptors.SavingEmployeeInterceptor;

@Configuration
public class InterceptorsConfig implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SavingEmployeeInterceptor()).addPathPatterns("/save");
	}

}
