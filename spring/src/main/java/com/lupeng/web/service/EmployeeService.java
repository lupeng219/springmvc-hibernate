package com.lupeng.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.lupeng.web.data.EmployeeData;
import com.lupeng.web.entity.Employee;
import com.lupeng.web.util.PageUtil;

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
    /**
     * 查询员工列表
     * @param pager
     * @return
     */
    List<Employee> getAllEmployee(PageUtil pager);
    /**
     * 添加员工
     * @return
     */
    public Long addEmployee(Employee employee,Long personaId);
    /**
     * 更新员工信息
     * 
     * @param custNo
     * @return
     */
    public Map<String, Object> updateEmployee(Employee employee, Long personaId);
    
    /**
     * 修改员工状态
     * 
     * @param empStatus
     * @param custNo
     * @return
     */
    public Map<String, Object> updateEnable(String empStatus, String custNo);
    
    /**
     * 更改员工密码
     * 
     * @param custNo
     * @return
     */
    public Map<String, Object> updatePassword(String password, String custNo);
    /**
     * 删除员工
     * 
     * @param custNo
     * @return
     */
    public Map<String, Object> deleteEmployee(String custNo);
    /**
     * 得到员工详细信息
     * 
     * @param custNo
     * @return
     */
    public Map<String, Object> getEmployeeInfo(String custNo);
}
