package com.employee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.employee.exception.EmployeeIDException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EmployeeIDException.class)
	  public ResponseEntity<String> handleEmployeeIDException(EmployeeIDException ex) {
	    return new ResponseEntity<String>("Invalid Employee ID", HttpStatus.BAD_REQUEST);
	  }

		/*
		 * @ExceptionHandler(Exception.class) public ResponseEntity<String>
		 * handleException(Exception ex) { return new
		 * ResponseEntity<String>("An error occurred",
		 * HttpStatus.INTERNAL_SERVER_ERROR); }
		 */


}
