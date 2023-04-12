package com.gavriel.employees.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "employees")
public class EmployeeModel {
	@Id
	@NotNull
	private Integer id;
	@NotNull
	private String name;
	@NotNull
	private String city;
	@NotNull
	private String country;
	@NotNull
	private Integer salary;
	
	public EmployeeModel()
	{
		
	}
	
	public EmployeeModel(Integer id, String name, String city, String country, Integer salary) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.country = country;
		this.salary = salary;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	

}
