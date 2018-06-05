package com.lupeng.web.repository;

import java.util.List;

import org.springframework.data.domain.Page;

import com.lupeng.web.entity.Employee;
import com.lupeng.web.util.PageUtil;

public interface EmployeeRepository {
    
    public Employee findEmployeeByUserName(String name);
   
    public List<Employee> getEmployeeByEmployeeId(List<Long> employeeId);
    
    List<Employee> getAllEmployee(PageUtil pager);
    /**
     * 添加员工
     * @return
     */
    public Long save(Employee employee);
    
    public void updateEmp(Employee employee);
    
    public Employee findEmployeeByCustNo(String custNo);
    
    public void delete(Employee employee);
    
    public Employee findEmployeeByEmployeeId(Long employeeId);
}
