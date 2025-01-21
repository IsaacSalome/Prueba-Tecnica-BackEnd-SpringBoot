package com.example.app.employee;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Data
public class EmployeeRequest {
    @NotBlank
    @Length(max = 50)
    private String name;

    @NotBlank
    @Length(max = 50)
    private String lastName;

    @NotBlank
    @Length(max = 50)
    private String company;

    @NotNull
    private Gender gender;

    @NotBlank
    @Length(max = 150)
    private String country;

    @NotBlank
    @Length(max = 150)
    private String state;

    @NotBlank
    @Length(max = 18)
    private String curp;

    @NotBlank
    @Length(max = 13)
    private String rfc;
}
