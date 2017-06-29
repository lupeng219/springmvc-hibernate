package com.lupeng.web.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.lupeng.web.data.EmployeeData;
import com.lupeng.web.entity.Employee;

public interface EmployeeService extends UserDetailsService{

    /**
     * 通过角色id查找员工id
     */
    public List<Long> fingEmployeeIdBypersonaId(Long personaId);

    /**
     * 通过员工id获得员工列表
     */
    public List<Employee> getEmployeeByEmployeeId(List<Long> employeeId);

    /**
     * 从新加载权限
     */
    public Authentication reLoadUserPower(EmployeeData employee);
}
