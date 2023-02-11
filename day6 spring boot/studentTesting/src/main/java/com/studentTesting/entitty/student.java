package com.studentTesting.entitty;

import jakarta.persistence.*;

@Entity
@Table(name = "student_data")
public class student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String fname;
    private String lname;
    private String address;

    public student(Integer id, String fname, String lname, String address) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
    }

    public student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}