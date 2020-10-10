package com.employee.service.create;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.model.EmployeeInfo;
import com.employee.repository.EmployeeRepository;
import com.employee.service.MessageConstants;

/*
 * this class inserts employee record into database 
 */
@Service
public class EmployeeCreationServiceImpl implements EmployeeCreationService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeCreationServiceImpl.class);
	
	@Autowired
	EmployeeRepository empRepo;

	@Override
	public EmployeeCreationResponse addEmployee(EmployeeCreationRequest employeeCreationRequest,
			HttpServletRequest request, HttpSession session) {
		
		EmployeeCreationResponse response = new EmployeeCreationResponse();
		try {
			EmployeeInfo empInformation = createEmployee(employeeCreationRequest);
			if (empInformation != null) {
				logger.debug("EMPLOYEE RECORD SAVED" + empInformation);
				setSuccessResponse(response, MessageConstants.DETAILS_SAVED);
			} else {
				setFailureResponse(response, MessageConstants.ERROR_WHILE_SAVING);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			setFailureResponse(response, ex);
		}
		return response;
	}
	
	EmployeeInfo createEmployee(EmployeeCreationRequest request) {
		EmployeeInfo empInfo = new EmployeeInfo();
		empInfo.setFirstName(request.getFirstName());
		empInfo.setLastName(request.getLastName());
		empInfo.setEmailId(request.getEmailId());
		empInfo.setAge(request.getAge());
		empInfo.setGender(request.getGender());
		empInfo.setAddress(request.getAddress());
		empInfo.setDeleteFlag(false);
		return empRepo.save(empInfo);
	}
	
	private EmployeeCreationResponse setSuccessResponse(EmployeeCreationResponse response, final String msgConst) {
		response.setSuccessful(true);
		response.setMessage(msgConst);
		return response;
	}

	private EmployeeCreationResponse setFailureResponse(EmployeeCreationResponse response, final String msgConst) {
		response.setSuccessful(false);
		response.setMessage(msgConst);
		return response;
	}

	private EmployeeCreationResponse setFailureResponse(EmployeeCreationResponse response, Exception e) {
		response.setSuccessful(false);
		response.setMessage(e.getMessage());
		return response;
	}

}
