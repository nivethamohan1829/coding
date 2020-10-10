package com.employee.service.update;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public interface EmployeeUpdationService {

	EmployeeUpdationResponse changeEmployeeInfo(EmployeeUpdationRequest employeeUpdationRequest,	HttpServletRequest request, HttpSession session);

}
