package com.gavriel.employees.exceptions;

public class EmployeeNoSpaceException extends EmployeeException {

	private static final long serialVersionUID = 4342235875891594500L;
	private int maxEmployees;

	public EmployeeNoSpaceException(String operation, int maxEmployees) {
		super(operation);
		this.maxEmployees = maxEmployees;
	}

	public int getMaxEmployees() {
		return maxEmployees;
	}

	public void setMaxEmployees(int maxEmployees) {
		this.maxEmployees = maxEmployees;
	}

}
