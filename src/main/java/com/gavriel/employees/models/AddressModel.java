package com.gavriel.employees.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "adresses")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class AddressModel {
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String streetName;
	@NotNull
	private String cityName;
	@NotNull
	private String countryName;
	@NotNull
	private int zipCode;
	@JsonIgnore
	@OneToOne(mappedBy = "address")
	private EmployeeModel employee;
	
	public AddressModel()
	{
		
	}
	
	public AddressModel(@NotNull int id, @NotNull String streetName, @NotNull String cityName,
			@NotNull String countryName, @NotNull int zipCode, EmployeeModel employee) {
		this.id = id;
		this.streetName = streetName;
		this.cityName = cityName;
		this.countryName = countryName;
		this.zipCode = zipCode;
		this.employee = employee;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public EmployeeModel getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeModel employee) {
		this.employee = employee;
	}
	
	
}
