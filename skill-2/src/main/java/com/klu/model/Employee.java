package com.klu.model;

import javax.persistence.*;

@Entity
@Table(name = "emp")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empid;

    private String empname;
    private Double empsalary;

    @ManyToOne
    @JoinColumn(name = "deptid")
    private Department department;

    // -------- FIXED SETTERS & GETTERS --------

    public void setEmpId(int empid) {
        this.empid = empid;   // ✅ FIXED
    }

    public int getEmptId() {
        return empid;
    }

    public void setEmpName(String empname) {
        this.empname = empname;
    }

    public String getEmpName() {
        return empname;
    }

    public void setEmpSal(double empsalary) {
        this.empsalary = empsalary;
    }

    public Double getEmpsal() {
        return empsalary;
    }

    public void setDep(Department department) {
        this.department = department;
    }

    public Department getdep() {
        return department;
    }
}
