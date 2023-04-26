package com.employee.service;






import com.employee.vo.EmployeeVO;


public interface IEmployeeService {
	
	//public Employee getEmployeeByEmployeeId(int employeeId) throws EmployeeIDException;
	
	public EmployeeVO getByEmployeeId(int employeeID) throws Exception;

}
