package com.example.app.employee;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
public class Employee {
		@Id
	    private Integer employeeNumber;  // Primary key generated automatically

	    @NotBlank(message = "Name cannot be empty.")
	    @Length(max = AppConstants.STRING_SIZE_SMALL, message = "Name must not exceed {max} characters.")
	    private String name;

	    @NotBlank(message = "Last name cannot be empty.")
	    @Length(max = AppConstants.STRING_SIZE_SMALL, message = "Last name must not exceed {max} characters.")
	    private String lastName;

	    @NotBlank(message = "Company cannot be empty.")
	    @Length(max = AppConstants.STRING_SIZE_SMALL, message = "Company must not exceed {max} characters.")
	    private String company;

	    @NotNull(message = "Gender cannot be null.")
	    @Enumerated(EnumType.STRING)
	    private Gender gender;

	    @NotBlank(message = "Country cannot be empty.")
	    @Length(max = AppConstants.STRING_SIZE_MEDIUM, message = "Country must not exceed {max} characters.")
	    private String country;

	    @NotBlank(message = "State cannot be empty.")
	    @Length(max = AppConstants.STRING_SIZE_MEDIUM, message = "State must not exceed {max} characters.")
	    private String state;

	    @NotBlank(message = "CURP cannot be empty.")
	    @Length(max = 18, message = "CURP must not exceed {max} characters.")
	    private String curp;

	    @NotBlank(message = "RFC cannot be empty.")
	    @Length(max = 13, message = "RFC must not exceed {max} characters.")
	    private String rfc;
}

