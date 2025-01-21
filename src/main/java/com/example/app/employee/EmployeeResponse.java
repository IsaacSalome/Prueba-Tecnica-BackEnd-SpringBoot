package com.example.app.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmployeeResponse {
    private String employeeNumber;
    private String name;
    private String lastName;
    private String company;
    private String gender;
    private String country;
    private String state;
    private String curp;
    private String rfc;

    public EmployeeResponse(Employee employee) {
        this.employeeNumber = "E" + String.format("%04d", employee.getEmployeeNumber());
        this.name = employee.getName();
        this.lastName = employee.getLastName();
        this.company = employee.getCompany();
        this.gender = employee.getGender().name();
        this.country = employee.getCountry();
        this.state = employee.getState();
        this.curp = employee.getCurp();
        this.rfc = employee.getRfc();
    }


}
