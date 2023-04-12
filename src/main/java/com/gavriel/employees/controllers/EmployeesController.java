package com.gavriel.employees.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.gavriel.employees.models.EmployeeModel;
import com.gavriel.employees.services.ICurrencyConverter;
import com.gavriel.employees.services.IEmployeeService;

import jakarta.validation.Valid;

@RestController
public class EmployeesController {
	
	@Autowired
	private ICurrencyConverter converter;
	
	@Autowired
	private IEmployeeService service;
	
	private final Logger logger;
	
	public EmployeesController()
	{
		logger = LoggerFactory.getLogger(this.getClass());
	}
	
	@GetMapping("/get/{id}")
	public EmployeeModel GetEmployee(@PathVariable("id") int id)
	{
		EmployeeModel emp = service.FindEmployee(id);
		if(null == emp)
		{
			logger.error("Trying to get a non-exists employee");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The employee you wanted to get ןisn't found");
		}
		return emp;
	}
	
	@GetMapping("/get")
	public List<EmployeeModel> GetAllEmployee()
	{
		return service.GetAllEmployees();
	}
	
	@DeleteMapping("/delete")
	public void DeleteEmployee(@RequestParam("id") int id) {
		switch(service.DeleteEmployee(id))
		{
		case SUCCESS:
			break;
		case INTERNAL_FAILURE:
			logger.error("Failed to delete the employee");
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete the employee");
		case NOT_FOUND:
			logger.error("Trying to delete a non-exists employee");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The employee you wanted to delete ןisn't found");
		default:
			logger.error("Unknown error");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown error");
		}

	}
	
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public void PostEmployee(@RequestBody @Valid EmployeeModel emp){
		try
		{
			emp.setSalary((int) converter.USDToILS(emp.getSalary()));
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
		}
		switch(service.SaveEmployee(emp)) {
		case SUCCESS:
			break;
		case CONFLICT:
			logger.error("Trying to save an already-exists employee");
			throw new ResponseStatusException(HttpStatus.CONFLICT, "There is a user with this id, you can delete this user or change the id of the new employee");
		case INTERNAL_FAILURE:
			logger.error("Failed to save the employee");
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "failed to update the data");
		case NO_SPACE:
			logger.error("No space to save a new employee");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no space left for new employees, please remove some employees in order to add new ones");
		default:
			logger.error("Unknown error");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown error");
		}
	}
	
	@PutMapping("/update")
	public void PutEmployee(@RequestBody @Valid EmployeeModel emp) {
		switch(service.UpdateEmployee(emp))
		{
		case SUCCESS:
			break;
		case INTERNAL_FAILURE:
			logger.error("Failed to update the employee");
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "failed to update the data");
		case NOT_FOUND:
			logger.error("Trying to update a non-exists employee");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The employee you wanted to update ןisn't found");
		default:
			logger.error("Unknown error");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown error");
		}
	}
	
	

}
