package com.gavriel.employees.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

@Aspect
@Component
public class EmployeesControllerAspect {
	private final Logger logger;
	
	public EmployeesControllerAspect()
	{
		logger = LoggerFactory.getLogger(this.getClass());
	}
	
	@Before("execution (* com.gavriel.employees.controllers.*.*(..))")
	public void BeforeLog()
	{
		logger.info("service got a request");	
	}
}
