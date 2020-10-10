package com.employee.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.EmployeeInfo;
import com.employee.service.create.EmployeeCreationRequest;
import com.employee.service.create.EmployeeCreationResponse;
import com.employee.service.create.EmployeeCreationService;
import com.employee.service.delete.EmployeeDeleteResponse;
import com.employee.service.delete.EmployeeDeleteService;
import com.employee.service.find.EmployeeFindingService;
import com.employee.service.update.EmployeeUpdationRequest;
import com.employee.service.update.EmployeeUpdationResponse;
import com.employee.service.update.EmployeeUpdationService;

/*
 * This Class responsible for calling appropriate CRUD services 
 */

@RestController
@RequestMapping("/api")
@Validated
public class EmployeeRestController {

	@Autowired
	EmployeeCreationService creation;

	@Autowired
	EmployeeDeleteService deletion;

	@Autowired
	EmployeeFindingService find;

	@Autowired
	EmployeeUpdationService update;

	@PostMapping(value = "/create")
	public ResponseEntity<EmployeeCreationResponse> create(
			@Valid @RequestBody EmployeeCreationRequest employeeCreationRequest, HttpServletRequest request,
			HttpSession session, BindingResult bindingResult) throws Exception {
		EmployeeCreationResponse response = null;

		try {
			response = creation.addEmployee(employeeCreationRequest, request, session);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header(response.message).body(response);

		}
		if (response.isSuccessful())
			return ResponseEntity.ok().header(response.message).body(response);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).header(response.message).body(response);


	}

	@PostMapping(value = "/update")
	public ResponseEntity<EmployeeUpdationResponse> update(
			@Valid @RequestBody EmployeeUpdationRequest employeeUpdationRequest, HttpServletRequest request,
			HttpSession session) throws Exception {
		EmployeeUpdationResponse response = null;
		try {
			response = update.changeEmployeeInfo(employeeUpdationRequest, request, session);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header(response.message).body(response);

		}
		if (response.isSuccessful())
			return ResponseEntity.ok().header(response.message).body(response);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).header(response.message).body(response);

	}

	@GetMapping(value = "/deleteById/{id}")
	public ResponseEntity<?> deleteById(@PathVariable String id) throws Exception {
		EmployeeDeleteResponse response = null;
		try {
			response = deletion.deleteEmployee(id);
			System.out.println(response);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		if (response.isSuccessful())
			return ResponseEntity.ok().header(response.message).body(response);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).header(response.message).body(response);
	}

	@GetMapping(value = "/findAll")
	public ResponseEntity<?> findAll() throws Exception {
		List<EmployeeInfo> response = new ArrayList<EmployeeInfo>();
		try {
			response = find.findAllNonDeletedRecords();
			System.out.println(response);
			return ResponseEntity.ok().header("success").body(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header(e.getMessage()).body(response);

		}
	}

	@GetMapping(value = "/findAllDeletedRecords")
	public ResponseEntity<?> findAllDeletedRecords() throws Exception {
		List<EmployeeInfo> response = new ArrayList<EmployeeInfo>();
		try {
			response = find.findAllDeletedRecords();
			System.out.println(response);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		if (response != null)
			return ResponseEntity.ok().header("").body(response);
		else
			return ResponseEntity.ok().header("").body(response);
	}

	@GetMapping(value = "/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable String id) throws Exception {
		EmployeeInfo response = new EmployeeInfo();
		try {
			response = find.findById(id);
			System.out.println(response);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		if (response != null)
			return ResponseEntity.ok().header("").body(response);
		else
			return ResponseEntity.ok().header("").body(response);

	}

}
