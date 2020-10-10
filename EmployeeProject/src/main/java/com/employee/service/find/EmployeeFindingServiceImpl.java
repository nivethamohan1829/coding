package com.employee.service.find;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.exception.EmployeeNotFoundException;
import com.employee.model.EmployeeInfo;
import com.employee.repository.EmployeeRepository;

// this class find the employee information from the database
@Service
public class EmployeeFindingServiceImpl implements EmployeeFindingService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeFindingServiceImpl.class);
	
	@Autowired
	EmployeeRepository empRepo;

	@Override
	public List<EmployeeInfo> findAllNonDeletedRecords() {
		List<EmployeeInfo> empInfo = new ArrayList<>();
		try {
			empInfo = empRepo.findAll();
			logger.debug("EMPLOYEE RECORD FOUND" );
			 
		}catch (Exception ex) {
			throw new EmployeeNotFoundException("Employee record not found");
		}
		return empInfo;
	}

	@Override
	public List<EmployeeInfo> findAllDeletedRecords() {
		List<EmployeeInfo> empInfo = new ArrayList<>();
		try {
			empInfo = empRepo.deletedEntities();
			logger.debug("EMPLOYEE RECORD FOUND" );
		}catch (Exception ex) {
			throw new EmployeeNotFoundException("Employee record not found");
		}
		return empInfo;
	}

	@Override
	public EmployeeInfo findById(String id) {
		EmployeeInfo empInfo = new EmployeeInfo();
	
		try {
			empInfo = empRepo.findById(Long.parseLong(id)).get();
			logger.debug("EMPLOYEE RECORD FOUND" );
			return empInfo;
		}catch (Exception ex) {
			throw new EmployeeNotFoundException("Employee record not found");
		}
		
	}

}
