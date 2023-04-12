package com.gavriel.employees.exceptions;

public class EmployeeConflictException extends EmployeeException {

	private static final long serialVersionUID = 4783541292045916587L;
	private int id;

	public EmployeeConflictException(String operation, int id) {
		super(operation);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
