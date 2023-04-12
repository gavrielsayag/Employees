package com.gavriel.employees.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SavingEmployeeInterceptor implements HandlerInterceptor 
{
	private final Logger logger;
	
	public SavingEmployeeInterceptor()
	{
		logger = LoggerFactory.getLogger(this.getClass());
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
	{
		logger.info("interceptor - We are going to save an employee! that's exciting!");
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception 
	{
		logger.info("interceptor - We finished the adding! we are now ging to notify the client");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object  handler, Exception exception) throws Exception 
	{
		logger.info("interceptor - And now we are done!");
	}
}
