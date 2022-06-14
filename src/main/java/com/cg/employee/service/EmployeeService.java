package com.cg.employee.service;

import java.util.List;

import com.cg.employee.beans.Employee;

public interface EmployeeService {

	public Employee addEmployee(Employee employee);

	public Employee retriveEmployeeById(int id);

	public List<Employee> retrieveEmployeeByFirstName(String first_name);

	public List<Employee> retrieveEmployeeByLastName(String last_name);

	public void deleteEmployeeById(int id);

	public Employee updateEmployeeById(int id, Employee employee);

}
