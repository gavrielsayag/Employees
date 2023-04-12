package com.gavriel.employees;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.gavriel.employees.repositories")
public class EmployeesApplication implements CommandLineRunner {
	
	private final Logger logger;
	
	public EmployeesApplication()
	{
		logger = LoggerFactory.getLogger(this.getClass());
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("EXECUTING : command line runner, This is called after the application context is loaded, but before the execution of the main method is complete. num of args - " + args.length);
		 
        for (int i = 0; i < args.length; ++i) {
        	logger.info("args[{}]: {}", i, args[i]);
        }
		
	}
}
