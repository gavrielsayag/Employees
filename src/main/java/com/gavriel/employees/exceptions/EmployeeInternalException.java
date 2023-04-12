package com.gavriel.employees.exceptions;

public class EmployeeInternalException extends EmployeeException {

	private static final long serialVersionUID = 2558158838101285348L;

	public EmployeeInternalException(String operation) {
		super(operation);
	}

}
