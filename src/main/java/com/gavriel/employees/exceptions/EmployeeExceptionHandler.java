package com.gavriel.employees.exceptions;

import java.util.Map;
import java.util.HashMap;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class EmployeeExceptionHandler {
	
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(EmployeeConflictException.class)
	public Map<String, String> ConflictExceptionHandling(EmployeeConflictException ex)
	{
		System.out.println("here - " + ex);
		Map<String, String> response = new HashMap<>();
		response.put("Main message", "There is already a user with the given ID");
		response.put("Operation", ex.getOperation());
		response.put("Given ID", String.valueOf(ex.getId()));
		response.put("Stamp", "Employee exception");
		response.put("Advice", "Use the \"\\get\" endpoint in order to see which IDs are already in use");
		return response;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(EmployeeInternalException.class)
	public Map<String, String> InternalExceptionHandling(EmployeeInternalException ex)
	{
		Map<String, String> response = new HashMap<>();
		response.put("Main message", "An internal error occured while trying to execute the operation");
		response.put("Operation", ex.getOperation());
		response.put("Stamp", "Employee exception");
		return response;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(EmployeeNoSpaceException.class)
	public Map<String, String> NoSpaceExceptionHandling(EmployeeNoSpaceException ex)
	{
		Map<String, String> response = new HashMap<>();
		response.put("Main message", "There is no space in the service for another employee");
		response.put("Operation", ex.getOperation());
		response.put("Max number of employees", String.valueOf(ex.getMaxEmployees()));
		response.put("Stamp", "Employee exception");
		response.put("Advice", "Use the \\delete endpoint in order to delete some employees");
		return response;
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmployeeNotFoundException.class)
	public Map<String, String> NotFoundExceptionHandling(EmployeeNotFoundException ex)
	{
		Map<String, String> response = new HashMap<>();
		response.put("Main message", "There is no user with the given ID");
		response.put("Operation", ex.getOperation());
		response.put("Given ID", String.valueOf(ex.getId()));
		response.put("Stamp", "Employee exception");
		response.put("Advice", "Use the \\get endpoint in order to see which IDs are already in use");
		return response;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(EmployeeException.class)
	public Map<String, String> GenericEmployeeExceptionHandling(EmployeeException ex)
	{
		Map<String, String> response = new HashMap<>();
		response.put("Main message", "An unknown error occured while trying to execute the operation");
		response.put("Operation", ex.getOperation());
		response.put("Stamp", "Employee exception");
		return response;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({Exception.class, RuntimeException.class})
	public Map<String, String> ExceptionHandling(Throwable ex)
	{
		Map<String, String> response = new HashMap<>();
		response.put("Main message", "An unknown error occured while trying to execute the operation");
		response.put("Message", ex.getMessage());
		response.put("Stamp", "Throwable");
		return response;
	}


}
