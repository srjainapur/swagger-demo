package com.java.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="PA_EMPLOYEE")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="EMP_ID", nullable=false, length=10, unique=true)
	@ApiModelProperty(notes="This is auto generated Employee Id")
	private long empId;
	
	@Column(name="FIRST_NAME", length=25)
	@ApiModelProperty(notes="First Name of employee, Max lenght is 25 chars")
	private String firstName;
	
	@Column(name="LAST_NAME", length=25)
	@ApiModelProperty(notes="Last Name of employee, Max lenght is 25 chars")
	private String lastName;
	
	@Column(name="SALARY", length=10)
	private int salary;
	
	@Column(name="DATE_OF_BIRTH", length=15)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateOfBirth;
	
	public Employee() {
		
	}
	
	public Employee(String firstName, String lastName, int salary, Date dateOfBirth) {
		this.firstName = firstName;
		this.lastName=lastName;
		this.salary=salary;
		this.dateOfBirth=dateOfBirth;
	}

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
