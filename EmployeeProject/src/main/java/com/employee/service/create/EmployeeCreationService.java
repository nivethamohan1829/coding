package com.employee.service.create;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public interface EmployeeCreationService {

	EmployeeCreationResponse addEmployee(EmployeeCreationRequest employeeCreationRequest,	HttpServletRequest request, HttpSession session);

}
