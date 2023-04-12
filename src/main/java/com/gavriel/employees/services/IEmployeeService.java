package com.gavriel.employees.services;

import java.util.List;

import com.gavriel.employees.models.EmployeeModel;

public interface IEmployeeService {
	enum status {SUCCESS, INTERNAL_FAILURE, CONFLICT, NOT_FOUND, NO_SPACE};
	public List<EmployeeModel> GetAllEmployees();
	public EmployeeModel FindEmployee(int id);
	public status DeleteEmployee(int id);
	public status SaveEmployee(EmployeeModel emp);
	public status UpdateEmployee(EmployeeModel emp);
	public int getMaxEmployees();

}
