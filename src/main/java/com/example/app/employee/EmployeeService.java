package com.example.app.employee;

import com.example.app.employee.Employee;
import com.example.app.employee.EmployeeRepository;


import com.example.app.employee.AppConstants;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)



public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeResponse::new)
                .collect(Collectors.toList());
    }

    public EmployeeResponse getEmployeeById(Integer employeeNumber) {
        Employee employee = employeeRepository.findById(employeeNumber)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        return new EmployeeResponse(employee);
    }
    
    public Employee saveEmployee(EmployeeRequest employeeRequest) {
        int nextEmployeeNumber = getNextEmployeeNumber();
        
        if (employeeRepository.existsById(nextEmployeeNumber)) {
            throw new IllegalArgumentException("Employee number already exists: " + nextEmployeeNumber);
        }

        Employee employee = new Employee();
        employee.setEmployeeNumber(nextEmployeeNumber);
        employee.setName(employeeRequest.getName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setCompany(employeeRequest.getCompany());
        employee.setGender(employeeRequest.getGender());
        employee.setCountry(employeeRequest.getCountry());
        employee.setState(employeeRequest.getState());
        employee.setCurp(employeeRequest.getCurp());
        employee.setRfc(employeeRequest.getRfc());

        return employeeRepository.save(employee);
    }
    
    public Employee updateEmployee(Integer employeeNumber, EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.findById(employeeNumber)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        employee.setName(employeeRequest.getName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setCompany(employeeRequest.getCompany());
        employee.setGender(employeeRequest.getGender());
        employee.setCountry(employeeRequest.getCountry());
        employee.setState(employeeRequest.getState());
        employee.setCurp(employeeRequest.getCurp());
        employee.setRfc(employeeRequest.getRfc());
        return employeeRepository.save(employee);
    }
    
    public void deleteEmployee(Integer employeeNumber) {
        if (!employeeRepository.existsById(employeeNumber)) {
            throw new IllegalArgumentException("Employee not found");
        }
        employeeRepository.deleteById(employeeNumber);
    }
    
    private void mapRequestToEmployee(EmployeeRequest request, Employee employee) {
        employee.setName(request.getName());
        employee.setLastName(request.getLastName());
        employee.setCompany(request.getCompany());
        employee.setGender(request.getGender());
        employee.setCountry(request.getCountry());
        employee.setState(request.getState());
        employee.setCurp(request.getCurp());
        employee.setRfc(request.getRfc());
    }


    private int getNextEmployeeNumber() {
        Integer maxEmployeeNumber = employeeRepository.findMaxEmployeeNumber();
        return (maxEmployeeNumber == null ? 10 : maxEmployeeNumber + 10);
    }
    
    public Map<String, Long> getTotalByGender() {
        List<Object[]> results = employeeRepository.countByGender();
        Map<String, Long> genderCount = new HashMap<>();
        
        for (Object[] result : results) {
            Gender gender = (Gender) result[0];  // Aquí, casteamos el enum Gender
            Long count = (Long) result[1];
            genderCount.put(gender.toString(), count);  // Convertimos Gender a String
        }
        
        return genderCount;
    }
    
    public Map<String, Long> getTotalByCountry() {
        List<Object[]> results = employeeRepository.countByCountry();
        Map<String, Long> countryCount = new HashMap<>();
        
        for (Object[] result : results) {
            String country = (String) result[0];  // El primer elemento es el country (String)
            Long count = (Long) result[1];         // El segundo elemento es el conteo (Long)
            countryCount.put(country, count);
        }
        
        return countryCount;
    }

    public Map<String, Long> getTotalByState() {
        List<Object[]> results = employeeRepository.countByState();
        Map<String, Long> stateCount = new HashMap<>();
        
        for (Object[] result : results) {
            String state = (String) result[0];    // El primer elemento es el state (String)
            Long count = (Long) result[1];         // El segundo elemento es el conteo (Long)
            stateCount.put(state, count);
        }
        
        return stateCount;
    }

    

}