package com.cg.employee.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cg.employee.beans.Employee;
import com.cg.employee.mapper.EmployeeResponse;
import com.cg.employee.service.impl.EmployeeServiceImpl;

@RestController
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeServiceImpl employeeServiceImpl;

	@PostMapping(value = "/api/addEmployee")
	public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody Employee employee) {
		logger.info("Received request to add a new employee {}");
		try {
			employee = employeeServiceImpl.addEmployee(employee);
			return new ResponseEntity<EmployeeResponse>(new EmployeeResponse("Employee created", employee.getId()),
					HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Exception caught", e);
			return new ResponseEntity<EmployeeResponse>(
					new EmployeeResponse("Employee exists in database", employee.getId()), HttpStatus.ACCEPTED);
		}

	}

	@GetMapping(value = "/api/getEmployee/id")
	public Employee getEmployeeById(@RequestParam(value = "id") int id) {
		logger.info("Recieved request to retrieve employee details by Id {}");
		try {
			return employeeServiceImpl.retriveEmployeeById(id);
		} catch (Exception e) {
			logger.error("Exception caught", e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/api/getEmployee/firstName")
	public List<Employee> getEmployeeByFirstName(@RequestParam(value = "first_name") String first_name) {
		logger.info("Recieved request to retrive employee details by first_name {}");
		try {
			return employeeServiceImpl.retrieveEmployeeByFirstName(first_name.toUpperCase());
		} catch (Exception e) {
			logger.error("Exception caught", e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/api/getEmployee/lastName")
	public List<Employee> getEmployeeByLastName(@RequestParam(value = "last_name") String last_name) {
		logger.info("Recieved request to retrive employee details by last_name {}");
		try {
			return employeeServiceImpl.retrieveEmployeeByLastName(last_name.toUpperCase());
		} catch (Exception e) {
			logger.error("Exception caught", e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(value = "/api/deleteEmployee")
	public ResponseEntity<EmployeeResponse> deleteEmployeeById(@RequestParam(value = "id") int id) {
		logger.info("Recieved request to deleted employee by Id {}");
		try {
			employeeServiceImpl.deleteEmployeeById(id);
			return new ResponseEntity<EmployeeResponse>(new EmployeeResponse("Employee deleted", id), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception caught", e);
			return new ResponseEntity<EmployeeResponse>(new EmployeeResponse("Employee do not exist in database", id),
					HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping(value = "/api/updateEmployee")
	public Employee updateEmployeeById(@RequestParam(value = "id") int id, @RequestBody Employee employee) {
		logger.info("Recieved request to update employee details {}");
		try {
			return employeeServiceImpl.updateEmployeeById(id, employee);
		} catch (Exception e) {
			logger.error("Exception caught", e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
