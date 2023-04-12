package com.gavriel.employees.services;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import com.gavriel.employees.models.EmployeeModel;

@Service
@Profile("dev")
public class EmployeeMapService implements IEmployeeService {
	
	private Map<Integer, EmployeeModel> employeesMap;
	private final int maxEmployees;
	
	public EmployeeMapService(@Value("${max.employees}") int maxEmployees)
	{
		this.employeesMap = new HashMap<Integer, EmployeeModel>();
		this.maxEmployees = maxEmployees;
	}

	@Override
	public List<EmployeeModel> GetAllEmployees() {
		return new ArrayList<EmployeeModel>(employeesMap.values());
	}

	@Override
	public EmployeeModel FindEmployee(int id) {
		return employeesMap.get(id);
	}

	@Override
	public status DeleteEmployee(int id) {
		EmployeeModel removed_emp = employeesMap.remove(id);
		if(null == removed_emp)
		{
			return status.NOT_FOUND;
		}
		return status.SUCCESS;
	}

	@Override
	public status SaveEmployee(EmployeeModel emp) {
		Integer id = emp.getId();
		if(null != employeesMap.get(id))
		{
			return status.CONFLICT;
		}
		if(employeesMap.size() == maxEmployees)
		{
			return status.NO_SPACE;
		}
		employeesMap.put(id, emp);
		return status.SUCCESS;
	}

	@Override
	public status UpdateEmployee(EmployeeModel emp) {
		Integer id = emp.getId();
		if(null == employeesMap.get(id))
		{
			return status.NOT_FOUND;
		}
		employeesMap.put(id, emp);
		return status.SUCCESS;
	}

}
