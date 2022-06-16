package com.cg.employee.beans;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Employee")
@Component
public class Employee {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "FIRSTNAME")
    private String first_name;

    @Column(name = "LASTNAME")
    private String last_name;

    @Column(name = "PHONENUMBER")
    private String phone_number;

    @CreationTimestamp
    @Column(name = "CURRENTTIMESTAMP")
    private LocalDateTime createDateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", phone_number="
                + phone_number + "]";
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
