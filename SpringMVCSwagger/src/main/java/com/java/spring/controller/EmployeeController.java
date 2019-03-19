package com.java.spring.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.spring.exception.EmployeeNotFoundException;
import com.java.spring.exception.InvalidEmployeeIdException;
import com.java.spring.model.Employee;
import com.java.spring.service.EmployeeService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="EMS", description="Operations pertaining to products in Online Store")
public class EmployeeController {
	
	private static final Logger LOG = Logger.getLogger(EmployeeController.class.getName());
	
	@Autowired
	private EmployeeService empService;
	
	@ApiOperation(value="View list of available Employee", httpMethod="GET", response=Employee.class)
	@ApiResponses(value={
			@ApiResponse(code=200, message="Successfully retrieved list"),
			@ApiResponse(code=401, message="You are not authorized to view the resource"),
			@ApiResponse(code=403, message="Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code=404, message="The resource you were trying to reach is not found")
	})
	@RequestMapping(value="/employee", method=RequestMethod.GET)
	public List<Employee> getALLEmployee() {
		LOG.log(Level.INFO, "Calling getAllEmployee() method");
		return empService.getAllEmployee();
	}
	
	@ApiOperation(value="Save Employee Information", httpMethod="POST", response=String.class)
	@RequestMapping(value="/employee", method=RequestMethod.POST)
	public ResponseEntity<String> saveEmployee(@RequestBody Employee employee) {
		Employee save = empService.saveEmployee(employee);
		LOG.log(Level.INFO, "Calling saveEmployee() method");
		
		return new ResponseEntity<String>("Employee Created Successfully Id " + save.getEmpId(), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/employee/{empId}", method=RequestMethod.GET)
	@ApiOperation(value="Get the Employee details based on emp id", httpMethod="GET")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("empId") String employeeId) {
		Employee employee = empService.getEmployeeById(employeeId);
		
		LOG.log(Level.INFO, "Calling getEmployeeById() method " + employeeId);
		
		if(employee == null || employee.getEmpId() == 0) {
			throw new EmployeeNotFoundException();
		}
		
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	@RequestMapping(value="/employee/two", method=RequestMethod.GET)
	@ApiOperation(value="Get two employee information based on employee id's")
	public ResponseEntity<Object> getTwoEmployee(@RequestParam List<Long> empIds) {
		
		List<Employee> iterableEmp = empService.getTwoEmployee(empIds);
		System.out.println(iterableEmp + "getTwoEmployee");
		if(iterableEmp == null || iterableEmp.isEmpty()) {
			throw new InvalidEmployeeIdException();
		}
		return new ResponseEntity<>(iterableEmp, HttpStatus.OK);
	}
	
	@ExceptionHandler(value=InvalidEmployeeIdException.class)
	public ResponseEntity<Object> throwInvalidEmployeeId(InvalidEmployeeIdException iveexp) {
		return new ResponseEntity<Object>("Employee details not present for given Ids", HttpStatus.NOT_FOUND); 
	}
	
	@ApiOperation(value="Hystrix fault tolerance demo")
	@HystrixCommand(fallbackMethod="fallback_hello", commandProperties={@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="1000")})
	@RequestMapping(value="/employee/hystrix", method=RequestMethod.GET)
	public String hystrixDemo() throws InterruptedException {
		Thread.sleep(3000);
		return "Welcome Hystrix";
	}
	
	private String fallback_hello() {
	   return "Request fails. It takes long time to response";
	}
}
