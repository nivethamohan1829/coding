package com.employee.service.delete;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.exception.EmployeeNotFoundException;
import com.employee.repository.EmployeeRepository;
import com.employee.service.MessageConstants;
import com.employee.service.find.EmployeeFindingServiceImpl;

/*
 * this class deletes the employee record by id
 */
@Service
public class EmployeeDeleteServiceImpl implements EmployeeDeleteService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeFindingServiceImpl.class);
	
	@Autowired
	EmployeeRepository empRepo;

	@Override
	public EmployeeDeleteResponse deleteEmployee(String id) {
		EmployeeDeleteResponse response = new EmployeeDeleteResponse();
		
		try {
			empRepo.findById(Long.parseLong(id)).get();
			logger.debug("EMPLOYEE RECORD FOUND" );
		}catch (Exception ex) {
			throw new EmployeeNotFoundException("Employee record not found");
		}
		
		try {
			 empRepo.softDelete(Long.parseLong(id));
			logger.debug("EMPLOYEE RECORD DELETED" );
			setSuccessResponse(response, MessageConstants.DELETED);
			 
		}catch (Exception ex) {
			ex.printStackTrace();
			setFailureResponse(response, ex);
		}
		return response;
	}

	private EmployeeDeleteResponse setSuccessResponse(EmployeeDeleteResponse response, final String msgConst) {
		response.setSuccessful(true);
		response.setMessage(msgConst);
		return response;
	}
	
	private EmployeeDeleteResponse setFailureResponse(EmployeeDeleteResponse response, Exception e) {
		response.setSuccessful(false);
		response.setMessage(e.getMessage());
		return response;
	}

}
