package com.lupeng.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.lupeng.web.entity.Employee;

public interface EmployeeRepository {
    
    public Employee findEmployeeByUserName(String name);
   
    public List<Employee> getEmployeeByEmployeeId(List<Long> employeeId);
}
