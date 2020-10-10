package com.employee.service.find;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.employee.model.EmployeeInfo;

public interface EmployeeFindingService {
	
	List<EmployeeInfo> findAllNonDeletedRecords();
	
	List<EmployeeInfo> findAllDeletedRecords();
	
	EmployeeInfo findById(String id);

}
