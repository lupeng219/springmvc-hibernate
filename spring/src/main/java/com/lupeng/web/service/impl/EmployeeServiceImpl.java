package com.lupeng.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.input.TeeInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lupeng.web.data.EmployeeData;
import com.lupeng.web.entity.Employee;
import com.lupeng.web.entity.Employee_persona;
import com.lupeng.web.entity.Persona;
import com.lupeng.web.entity.Power;
import com.lupeng.web.repository.EmployeePersonaRepository;
import com.lupeng.web.repository.EmployeeRepository;
import com.lupeng.web.repository.PersonaPowerRepository;
import com.lupeng.web.repository.PersonaRepository;
import com.lupeng.web.repository.PowerRepository;
import com.lupeng.web.service.EmployeeService;
import com.lupeng.web.util.Const;
import com.lupeng.web.util.JsonHelper;
import com.lupeng.web.util.PageUtil;
import com.lupeng.web.util.SecurityUserHolder;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeePersonaRepository employeePersonaRepository;
    @Autowired
    private PowerRepository powerRepository;
    @Autowired
    private PersonaPowerRepository personaPowerRepository;
    @Autowired
    private Md5PasswordEncoder passwordEncode;
    @Autowired
    private PersonaRepository personaRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
       
        logger.info(String.format("Call EmployeeServiceImpl#loadUserByUsername:[username = %s] ",
                username));
        Employee employee = null;

        try {
            employee = employeeRepository.findEmployeeByUserName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (employee == null) {
            throw new UsernameNotFoundException("用户名或密码不正确!");
        }
        Long personaId = employeePersonaRepository
                .findPersonaIdByEmployeeId(employee.getEmployeeId());
        EmployeeData ed = new EmployeeData(employee);
        ed.setPersonaId(personaId);
        // 超级管理添加所有的权限
        if ("1".equals(employee.getEmployeeType())) {
            List<Power> list = powerRepository.getAllPowerTwo();
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            GrantedAuthority ga = null;
            ga = new SimpleGrantedAuthority("ROLE_INDEX");
            authorities.add(ga);
            try {
                for (Power power : list) {
                    // 给员工添加权限
                    ga = new SimpleGrantedAuthority(power.getPowerName());
                    authorities.add(ga);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            ed.setAuthorities(authorities);
        } else {
            // 普通员工
            // 员工权限集合
            List<Power> powers = null;
            List<Long> powerId = personaPowerRepository.findPowerId(personaId);
            powers = powerRepository.findPowerByPowerId(powerId);
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            GrantedAuthority ga = null;
            ga = new SimpleGrantedAuthority("ROLE_INDEX");
            authorities.add(ga);
            try {
                for (Power power : powers) {
                    // 给员工添加权限
                    ga = new SimpleGrantedAuthority(power.getPowerName());
                    authorities.add(ga);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            ed.setAuthorities(authorities);
        }
        return ed;
    }
    @Override
    public List<Long> fingEmployeeIdBypersonaId(Long personaId) {
        logger.info(String.format(
                "call EmployeeServiceImpl#fingEmployeeIdBypersonaId:[personaId=%s]", personaId));
        return employeePersonaRepository.fingEmployeeIdBypersonaId(personaId);
    }

    @Override
    public List<Employee> getEmployeeByEmployeeId(List<Long> employeeId) {
        logger.info(String.format(
                "call EmployeeServiceImpl#getEmployeeByEmployeeId:[employeeId=%s]", employeeId));
       
            return employeeRepository.getEmployeeByEmployeeId(employeeId);
        
    }

    @Override
    public Authentication reLoadUserPower(EmployeeData employee) {
        logger.info(String.format("call EmployeeServiceImpl#reLoadUserPower:[employee=%s]",
                JsonHelper.formatJson(employee)));
        Long personaId = employeePersonaRepository
                .findPersonaIdByEmployeeId(employee.getEmployeeId());
        employee.setPersonaId(personaId);
        // 超级管理添加所有的权限
        if ("1".equals(employee.getEmployeeType())) {
            List<Power> list = powerRepository.getAllPowerTwo();
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            GrantedAuthority ga = null;
            ga = new SimpleGrantedAuthority("ROLE_INDEX");
            authorities.add(ga);
            try {
                for (Power power : list) {
                    // 给员工添加权限
                    ga = new SimpleGrantedAuthority(power.getPowerName());
                    authorities.add(ga);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            employee.setAuthorities(authorities);
        } else {
            // 普通员工
            // 员工权限集合
            List<Power> powers = null;
            List<Long> powerId = personaPowerRepository.findPowerId(personaId);
            powers = powerRepository.findPowerByPowerId(powerId);
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            GrantedAuthority ga = null;
            ga = new SimpleGrantedAuthority("ROLE_INDEX");
            authorities.add(ga);
            try {
                for (Power power : powers) {
                    // 给员工添加权限
                    ga = new SimpleGrantedAuthority(power.getPowerName());
                    authorities.add(ga);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            employee.setAuthorities(authorities);
        }
        Authentication authentication = SecurityUserHolder.getAuthentication();
        return authentication;
    }
    @Override
    public List<Employee> getAllEmployee(PageUtil pager) {
        return employeeRepository.getAllEmployee(pager);
    }
    @Override
    public Long addEmployee(Employee employee, Long personaId) {
        logger.info(String.format(
                "Call EmployeeServiceImpl#addEmployee:[employee = %s,personaId = %s] ",
                JsonHelper.formatJson(employee), personaId));
        Employee temp_employee = null;
        String username = employee.getUsername();
        temp_employee = employeeRepository.findEmployeeByUserName(username);
        if (temp_employee != null) {
            return 0l;
        }
        Long employeeId = null;
        Long result = 1l;
        String custNo = employee.getCustNo();
        String password = employee.getPassword();
        password = passwordEncode.encodePassword(password, custNo);
        try {
            employee.setPassword(password);
            // 添加员工
            employeeRepository.save(employee);
            employeeId = employee.getEmployeeId();
            // 添加权限
            Employee_persona employee_persona = new Employee_persona();
            employee_persona.setEmployeeId(employeeId);
            employee_persona.setPersonaId(personaId);
            employeePersonaRepository.save(employee_persona);
        } catch (Exception e) {
            result = 0l;
            e.printStackTrace();
            throw e;
        }
        return result;
    
    }
    @Override
    public Map<String, Object> updateEnable(String empStatus, String custNo) {
        logger.info(String.format("call EmployeeServiceImpl#updateEnable:[empStatus=%s,custNo=%s]",
                empStatus, custNo));
        Map<String, Object> map = new HashMap<String, Object>();
        Employee employee = employeeRepository.findEmployeeByCustNo(custNo);
        if (employee == null) {
            map.put(Const.retCode, false);
            map.put(Const.retMsg, "员工不存在");
            return map;
        }
        employee.setEmpStatus(empStatus);
        employeeRepository.updateEmp(employee);
        map.put(Const.retCode, true);
        map.put(Const.retMsg, "更新成功!");
        return map;
    }
    @Override
    public Map<String, Object> updatePassword(String password, String custNo) {

        logger.info(String.format("call EmployeeServiceImpl#updatePassword:[custNo=%s]", custNo));
        logger.info("更改员工密码custNo" + custNo);
        Map<String, Object> map = new HashMap<String, Object>();
        Employee employee = employeeRepository.findEmployeeByCustNo(custNo);
        if (employee == null) {
            map.put(Const.retCode, false);
            map.put(Const.retMsg, "员工不存在");
            return map;
        }
        password = passwordEncode.encodePassword(password, custNo);
        employee.setPassword(password);
        employeeRepository.updateEmp(employee);
        map.put(Const.retCode, true);
        map.put(Const.retMsg, "修改成功");
        return map;
    
    }
    @Override
    public Map<String, Object> deleteEmployee(String custNo) {

        logger.info(String.format("call EmployeeServiceImpl#deleteEmployee:[custNo=%s]", custNo));
        logger.info("删除员工custNo" + custNo);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Employee employee = employeeRepository.findEmployeeByCustNo(custNo);
            if (employee == null) {
                map.put(Const.retCode, false);
                map.put(Const.retMsg, "员工不存在");
                return map;
            }
            // 删除员工表
            employeeRepository.delete(employee);
            // 删除员工-角色对象关系表
            Employee_persona employee_persona = employeePersonaRepository
                    .findEmployee_personaByEmployeeId(employee.getEmployeeId());
            employeePersonaRepository.delete(employee_persona);
            map.put(Const.retCode, true);
            map.put(Const.retMsg, "删除成功!");
        } catch (Exception e) {
            map.put(Const.retCode, true);
            map.put(Const.retMsg, "删除失败");
            e.printStackTrace();
            throw e;
        }
        return map;
    
    }
    @Override
    public Map<String, Object> getEmployeeInfo(String custNo) {

        logger.info(String.format("call EmployeeServiceImpl#getEmployeeInfo:[custNo=%s]", custNo));
        Employee employee = employeeRepository.findEmployeeByCustNo(custNo);
        Map<String, Object> map = new HashMap<String, Object>();
        if (employee == null) {
            map.put(Const.retCode, false);
            map.put(Const.retMsg, "员工不存在");
            return map;
        }
        Long personaId = employeePersonaRepository
                .findPersonaIdByEmployeeId(employee.getEmployeeId());
        if (personaId == null) {
            map.put(Const.retCode, false);
            map.put(Const.retMsg, "员工不存在");
            return map;
        }
        Persona persona = personaRepository.findPersonaBypersonaId(personaId);
        map.put("retCode", true);
        map.put("employee", employee);
        map.put("persona", persona);
        return map;
    
    }
    @Override
    public Map<String, Object> updateEmployee(Employee employee, Long personaId) {

        logger.info(
                String.format("call EmployeeServiceImpl#updateEmployee:[employee=%s,personaId=%s]",
                        JsonHelper.formatJson(employee), personaId));
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Long employeeId = employee.getEmployeeId();
            Employee temp_employee = employeeRepository.findEmployeeByEmployeeId(employeeId);
            if (temp_employee == null) {
                map.put(Const.retCode, false);
                map.put(Const.retMsg, "员工不存在");
                return map;
            }
            temp_employee.setCustNo(employee.getCustNo());
            temp_employee.setUsername(employee.getUsername());
            temp_employee.setRealName(employee.getRealName());
            temp_employee.setEmployIDCardNum(employee.getEmployIDCardNum());
            temp_employee.setPhone(employee.getPhone());
            temp_employee.setEmployeeId(employee.getEmployeeId());;
            // 更新员工表
            employeeRepository.save(employee);
            // 更新员工角色关系表
            Employee_persona employee_persona = employeePersonaRepository
                    .findEmployee_personaByEmployeeId(employee.getEmployeeId());
            employee_persona.setPersonaId(personaId);
            employeePersonaRepository.save(employee_persona);
            map.put(Const.retCode, true);
            map.put(Const.retMsg, "更新成功");
        } catch (Exception e) {
            map.put(Const.retCode, true);
            map.put(Const.retMsg, "更新失败");
            e.printStackTrace();
            throw e;
        }
        return map;
    
    }

}
