package com.lupeng.web.repository.impl;

import org.springframework.stereotype.Repository;

import com.lupeng.web.entity.Employee;
import com.lupeng.web.repository.EmployeeRepository;
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Override
    public Employee findEmployeeByUserName(String name) {
        // TODO Auto-generated method stub
        Employee e = new Employee();
        return e ;
    }

}
