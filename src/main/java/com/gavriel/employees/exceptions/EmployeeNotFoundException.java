package com.gavriel.employees.exceptions;

public class EmployeeNotFoundException extends EmployeeException {
	private static final long serialVersionUID = 4579465275587094730L;
	private int id;
	
	public EmployeeNotFoundException(String operation, int id)
	{
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
