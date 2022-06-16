package com.cg.employee.models;

import lombok.Data;

@Data
public class EmployeeDetails {

    private int id;
    private String first_name;
    private String last_name;
    private String phone_number;

    public EmployeeDetails(int id, String firstName, String lastName, String phoneNumber) {
        this.id = id;
        this.first_name = firstName;
        this.last_name = lastName;
        this.phone_number = phoneNumber;
    }

    public EmployeeDetails() {
        super();
    }

}
