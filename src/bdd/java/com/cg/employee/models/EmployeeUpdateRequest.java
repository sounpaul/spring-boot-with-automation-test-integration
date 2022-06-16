package com.cg.employee.models;

public class EmployeeUpdateRequest {
    private String first_name;
    private String last_name;
    private String phone_number;

    public EmployeeUpdateRequest(String first_name, String last_name, String phone_number) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
    }
}
