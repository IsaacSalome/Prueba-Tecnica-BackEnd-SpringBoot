package com.example.app.employee;

import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeResponse createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeService.saveEmployee(employeeRequest);
        return new EmployeeResponse(employee);
    }

    @GetMapping("/{employeeNumber}")
    public EmployeeResponse getEmployeeById(@PathVariable String employeeNumber) {
        Integer numericEmployeeNumber = extractEmployeeNumber(employeeNumber);
        return employeeService.getEmployeeById(numericEmployeeNumber);
    }
    
    @DeleteMapping("/{employeeNumber}")
    public void deleteEmployee(@PathVariable String employeeNumber) {
        Integer numericEmployeeNumber = extractEmployeeNumber(employeeNumber);
        employeeService.deleteEmployee(numericEmployeeNumber);
    }
    
    @PutMapping("/{employeeNumber}")
    public EmployeeResponse updateEmployee(@PathVariable String employeeNumber, @Valid @RequestBody EmployeeRequest employeeRequest) {
        Integer numericEmployeeNumber = extractEmployeeNumber(employeeNumber);
        Employee updatedEmployee = employeeService.updateEmployee(numericEmployeeNumber, employeeRequest);
        return new EmployeeResponse(updatedEmployee);
    }
    
    // Filters
    
    @GetMapping("/gender")
    public Map<String, Long> getTotalByGender() {
        return employeeService.getTotalByGender();
    }

    
    @GetMapping("/country")
    public Map<String, Long> getTotalByCountry() {
        return employeeService.getTotalByCountry();
    }

    @GetMapping("/state")
    public Map<String, Long> getTotalByState() {
        return employeeService.getTotalByState();
    }
    
    private Integer extractEmployeeNumber(String formattedEmployeeNumber) {
        if (formattedEmployeeNumber.startsWith("E")) {
            return Integer.parseInt(formattedEmployeeNumber.substring(1));
        }
        throw new IllegalArgumentException("Invalid employee number format.");
    }

}