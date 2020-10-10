package com.employee.service.update;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.repository.EmployeeRepository;
import com.employee.service.MessageConstants;
//this class updates the employee record in the database

@Service
public class EmployeeUpdationServiceImpl implements EmployeeUpdationService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeUpdationServiceImpl.class);
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Override
	public EmployeeUpdationResponse changeEmployeeInfo(EmployeeUpdationRequest employeeUpdationRequest,
			HttpServletRequest request, HttpSession session) {
		
		EmployeeUpdationResponse response = new EmployeeUpdationResponse();
		
		try {
			int updateStatus = updateEmployee(employeeUpdationRequest);
			if (updateStatus != 0) {
				logger.debug("EMPLOYEE RECORD UPDATED" + employeeUpdationRequest);
				setSuccessResponse(response, MessageConstants.UPDATED);
			} else {
				setFailureResponse(response, MessageConstants.UPDATION_ERROR);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			setFailureResponse(response, ex);
		}
		return response;
	}
	
	
	private int updateEmployee(EmployeeUpdationRequest request) {
		return empRepo.updateEmployeeInfo(request.getFirstName(), request.getLastName(), request.getEmailId(), request.getAge(), request.getGender(), request.getAddress(), request.getId());
	}


	private EmployeeUpdationResponse setSuccessResponse(EmployeeUpdationResponse response, final String msgConst) {
		response.setSuccessful(true);
		response.setMessage(msgConst);
		return response;
	}

	private EmployeeUpdationResponse setFailureResponse(EmployeeUpdationResponse response, final String msgConst) {
		response.setSuccessful(false);
		response.setMessage(msgConst);
		return response;
	}

	private EmployeeUpdationResponse setFailureResponse(EmployeeUpdationResponse response, Exception e) {
		response.setSuccessful(false);
		response.setMessage(e.getMessage());
		return response;
	}


}
