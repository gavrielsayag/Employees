package com.gavriel.employees.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.gavriel.employees.exceptions.EmployeeConflictException;
import com.gavriel.employees.exceptions.EmployeeException;
import com.gavriel.employees.exceptions.EmployeeInternalException;
import com.gavriel.employees.exceptions.EmployeeNoSpaceException;
import com.gavriel.employees.exceptions.EmployeeNotFoundException;
import com.gavriel.employees.models.EmployeeModel;
import com.gavriel.employees.services.IEmployeeService;
import com.gavriel.employees.thirdparty.ICurrencyConverter;

import jakarta.validation.Valid;

@RestController
public class EmployeesController {
	
	private ICurrencyConverter converter;
	private IEmployeeService service;
	
	private final Logger logger;
	
	public EmployeesController(ICurrencyConverter converter, IEmployeeService service)
	{
		this.converter = converter;
		this.service = service;
		logger = LoggerFactory.getLogger(this.getClass());
	}
	
	@GetMapping("/get/{id}")
	public EmployeeModel GetEmployee(@PathVariable("id") int id) throws EmployeeNotFoundException
	{
		EmployeeModel emp = service.FindEmployee(id);
		if(null == emp)
		{
			logger.error("Trying to get a non-exists employee");
			throw new EmployeeNotFoundException("getting an employee", id);
		}
		return emp;
	}
	
	@GetMapping("/get")
	public List<EmployeeModel> GetAllEmployee()
	{
		return service.GetAllEmployees();
	}
	
	@DeleteMapping("/delete")
	public void DeleteEmployee(@RequestParam("id") int id) throws EmployeeException {
		switch(service.DeleteEmployee(id))
		{
		case SUCCESS:
			break;
		case INTERNAL_FAILURE:
			logger.error("Failed to delete the employee");
			throw new EmployeeInternalException("deleting an employee");
		case NOT_FOUND:
			logger.error("Trying to delete a non-exists employee");
			throw new EmployeeNotFoundException("deleting an employee", id);
		default:
			logger.error("Unknown error");
			throw new EmployeeException("deleting an employee");
		}

	}
	
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public void PostEmployee(@RequestBody @Valid EmployeeModel emp) throws Exception{
		emp.setSalary((int) converter.USDToILS(emp.getSalary()));
		switch(service.SaveEmployee(emp)) {
		case SUCCESS:
			break;
		case CONFLICT:
			logger.error("Trying to save an already-exists employee");
			throw new EmployeeConflictException("creating an employee", emp.getId());
		case INTERNAL_FAILURE:
			logger.error("Failed to save the employee");
			throw new EmployeeInternalException("creating an employee");
		case NO_SPACE:
			logger.error("No space to save a new employee");
			throw new EmployeeNoSpaceException("creating an employee", service.getMaxEmployees());
		default:
			logger.error("Unknown error");
			throw new EmployeeException("creating an employee");
		}
	}
	
	@PutMapping("/update")
	public void PutEmployee(@RequestBody @Valid EmployeeModel emp) throws EmployeeException {
		switch(service.UpdateEmployee(emp))
		{
		case SUCCESS:
			break;
		case INTERNAL_FAILURE:
			logger.error("Failed to update the employee");
			throw new EmployeeInternalException("updating an employee");
		case NOT_FOUND:
			logger.error("Trying to update a non-exists employee");
			throw new EmployeeNotFoundException("updating an employee", emp.getId());
		default:
			logger.error("Unknown error");
			throw new EmployeeException("updating an employee");
		}
	}
	
	

}
