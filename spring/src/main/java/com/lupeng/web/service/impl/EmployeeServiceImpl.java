package com.lupeng.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crdloo.loanloop.utils.JsonHelper;
import com.lupeng.web.data.EmployeeData;
import com.lupeng.web.entity.Employee;
import com.lupeng.web.entity.Power;
import com.lupeng.web.repository.EmployeePersonaRepository;
import com.lupeng.web.repository.EmployeeRepository;
import com.lupeng.web.repository.PersonaPowerRepository;
import com.lupeng.web.repository.PowerRepository;
import com.lupeng.web.service.EmployeeService;
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

}
