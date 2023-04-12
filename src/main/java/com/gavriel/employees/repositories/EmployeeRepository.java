package com.gavriel.employees.repositories;

import com.gavriel.employees.models.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Integer>{

}
