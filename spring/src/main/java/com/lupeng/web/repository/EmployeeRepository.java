package com.lupeng.web.repository;

import com.lupeng.web.entity.Employee;

public interface EmployeeRepository {
    
    public Employee findEmployeeByUserName(String name);
}
