package com.cg.employee.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.employee.beans.Employee;
import com.cg.employee.exception.EmployeeAlreadyExistException;
import com.cg.employee.exception.EmployeeNotFoundException;
import com.cg.employee.repository.EmployeeRepository;
import com.cg.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee addEmployee(Employee employee) {
		if (employeeRepository.existsById(employee.getId())) {
			logger.error("Employee ID exists in database");
			throw new EmployeeAlreadyExistException(String.format("Employee exists with id=%s", employee.getId()));
		} else {
			logger.info("Adding employee {}");
			return employeeRepository.save(employee);
		}
	}

	@Override
	public Employee retriveEmployeeById(int id) {
		if (employeeRepository.existsById(id)) {
			logger.info("Employee with " + id + " found in database");
			return employeeRepository.findById(id).get();
		} else {
			logger.error("Employee with given Id not found in database");
			throw new EmployeeNotFoundException(String.format("Employee with id=%s not found", id));
		}

	}

	@Override
	public List<Employee> retrieveEmployeeByFirstName(String first_name) {
		List<Employee> employees = employeeRepository.findEmployeesByFirstName(first_name);
		if (employees.size() != 0) {
			logger.info("Employees with given first name found in database");
			return employees;
		} else {
			logger.error("Employee with given first_name not found in database");
			throw new EmployeeNotFoundException(
					String.format("Employee with first_name=%s not found in database", first_name));
		}
	}

	@Override
	public List<Employee> retrieveEmployeeByLastName(String last_name) {

		List<Employee> employees = employeeRepository.findEmployeesByLastName(last_name);
		if (employees.size() != 0) {
			logger.info("Employees with given last name found in database");
			return employees;
		} else {
			logger.error("Employee with given last_name not found in database");
			throw new EmployeeNotFoundException(
					String.format("Employee with last_name=%s not found in database", last_name));
		}

	}

	@Override
	public void deleteEmployeeById(int id) {
		if (employeeRepository.existsById(id)) {
			logger.info("Employee found in database, hence deleting");
			employeeRepository.deleteById(id);
		} else {
			logger.error("Employee with given Id not found in database");
			throw new EmployeeNotFoundException(String.format("Employee with id=%s not found", id));
		}

	}

	@Override
	public Employee updateEmployeeById(int id, Employee employee) {
		if (employeeRepository.existsById(id)) {
			logger.info("Employee found in database, hence updating");
			Employee existingEmployee = employeeRepository.findById(id).get();
			existingEmployee.setFirst_name(employee.getFirst_name());
			existingEmployee.setLast_name(employee.getLast_name());
			existingEmployee.setPhone_number(employee.getPhone_number());
			return employeeRepository.save(existingEmployee);
		} else {
			logger.error("Employee with given Id not found in database");
			throw new EmployeeNotFoundException(String.format("Employee with id=%s not found", id));
		}
	}

}
