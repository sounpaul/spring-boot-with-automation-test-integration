package com.cg.employee.mapper;

public class EmployeeResponse {

    private String msg;
    private int id;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EmployeeResponse(String msg, int id) {
        this.msg = msg;
        this.id = id;
    }

    public EmployeeResponse() {
        super();
    }

}
