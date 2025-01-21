package com.example.app.employee;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT MAX(e.employeeNumber) FROM Employee e")
    Integer findMaxEmployeeNumber();

    @Query("SELECT e.gender, COUNT(e.gender) FROM Employee e GROUP BY e.gender")
    List<Object[]> countByGender();


    @Query("SELECT e.country, COUNT(e.country) FROM Employee e GROUP BY e.country")
    List<Object[]> countByCountry();

    @Query("SELECT e.state, COUNT(e.state) FROM Employee e GROUP BY e.state")
    List<Object[]> countByState();
}