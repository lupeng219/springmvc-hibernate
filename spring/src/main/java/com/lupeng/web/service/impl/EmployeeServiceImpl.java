package com.lupeng.web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lupeng.web.data.EmployeeData;
import com.lupeng.web.entity.Employee;
import com.lupeng.web.repository.EmployeeRepository;
import com.lupeng.web.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = null;

        try {
            employee = employeeRepository.findEmployeeByUserName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (employee == null) {
            throw new UsernameNotFoundException("用户名或密码不正确!");
        }
        EmployeeData ed = new EmployeeData(employee);
        return ed;
       /*
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
    */}

}
