package com.cg.employee.exception;

public class EmployeeAlreadyExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeAlreadyExistException(final String message) {
		super(message);
	}

}
