package com.employee.controller;

import org.slf4j.Logger;





import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.employee.service.IEmployeeService;
import com.employee.vo.EmployeeVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	IEmployeeService service;
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@GetMapping("/{employeeID}")
	public EmployeeVO getByEmployeeId(@PathVariable int employeeID)throws Exception{
		logger.info("Record of EmployeeID("+employeeID+")is called");
		return service.getByEmployeeId(employeeID);
	}
	
	
	
	

}
