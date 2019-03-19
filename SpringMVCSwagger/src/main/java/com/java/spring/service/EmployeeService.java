package com.java.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.spring.model.Employee;
import com.java.spring.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	public List<Employee> getAllEmployee() {
		return employeeRepo.findAll();
	}

	public Employee saveEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}

	public Employee getEmployeeById(String employeeId) {
		Optional<Employee> empOpt = employeeRepo.findById(Long.valueOf(employeeId));
		if(empOpt.isPresent()) {
			return empOpt.get();			
		} else {
			return new Employee();
		}
	}

	public List<Employee> getTwoEmployee(List<Long> empIds) {
		Iterable<Long> empid = empIds;
		return employeeRepo.findAllById(empid);
		
	}
}
