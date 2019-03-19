package com.java.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionController {
	
	@ExceptionHandler(value=EmployeeNotFoundException.class)
	public ResponseEntity<Object> exception(EmployeeNotFoundException enf) {
		return new ResponseEntity<Object>("Employee Not Found", HttpStatus.NOT_FOUND);
	}
}
