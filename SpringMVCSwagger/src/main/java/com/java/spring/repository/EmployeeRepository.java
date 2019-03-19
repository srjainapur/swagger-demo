package com.java.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.spring.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
