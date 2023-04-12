package com.gavriel.employees.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.gavriel.employees.models.EmployeeModel;
import com.gavriel.employees.repositories.EmployeeRepository;

@Service
@Profile({"prod", "default"})
public class EmployeeMySQLService implements IEmployeeService {
	
	private EmployeeRepository repo;
	private final int maxEmployees;
	
	public EmployeeMySQLService(@Value("${max.employees}") int maxEmployees, EmployeeRepository repo)
	{
		this.repo = repo;
		this.maxEmployees = maxEmployees;
	}

	@Override
	public List<EmployeeModel> GetAllEmployees() {
		return repo.findAll();
	}

	@Override
	public EmployeeModel FindEmployee(int id) {
		Optional<EmployeeModel> emp = repo.findById(id);
		if(false == emp.isPresent())
		{
			return null;
		}
		return emp.get();
	}

	@Override
	public status DeleteEmployee(int id) {
		if(null == FindEmployee(id))
		{
			return status.NOT_FOUND;
		}
		repo.deleteById(id);
		return status.SUCCESS;
	}

	@Override
	public status SaveEmployee(EmployeeModel emp) {
		if(null != FindEmployee(emp.getId()))
		{
			return status.CONFLICT;
		}
		if(repo.count() == maxEmployees)
		{
			return status.NO_SPACE;
		}
		repo.saveAndFlush(emp);
		return status.SUCCESS;
	}

	@Override
	public status UpdateEmployee(EmployeeModel emp) {
		if(null == FindEmployee(emp.getId()))
		{
			return status.NOT_FOUND;
		}
		repo.saveAndFlush(emp);
		return status.SUCCESS;
	}

	public int getMaxEmployees() {
		return maxEmployees;
	}

}
