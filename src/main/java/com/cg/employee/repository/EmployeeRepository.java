package com.cg.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.employee.beans.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("Select e from Employee e where upper(e.first_name)=?1")
	List<Employee> findEmployeesByFirstName(String first_name);

	@Query("Select e from Employee e where upper(e.last_name)=?1")
	List<Employee> findEmployeesByLastName(String last_name);

}
