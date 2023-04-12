package com.gavriel.employees.filters;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ControllersFilter implements Filter {
	
	private final Logger logger;
	
	public ControllersFilter()
	{
		logger = LoggerFactory.getLogger(this.getClass());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		logger.info("filter - before forwarding the request to the controller for execution!\nHeaders before - " + httpResponse.getHeaderNames());
		httpResponse.addHeader("Funny header", "This is so funny");
		chain.doFilter(request, response);
		logger.info("filter - after execution and getting response! we added to the response a funny header\nHeaders after - " + httpResponse.getHeaderNames());
	}

}
