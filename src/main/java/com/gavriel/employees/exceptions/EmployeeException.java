package com.gavriel.employees.exceptions;

public class EmployeeException extends Exception {
	private static final long serialVersionUID = -8870736137443412622L;
	private String operation;
	
	public EmployeeException(String operation) {
		super();
		this.operation = operation;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
