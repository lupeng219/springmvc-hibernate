package com.lupeng.web.repository.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lupeng.web.entity.Employee;
import com.lupeng.web.repository.EmployeeRepository;
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Autowired
    private SessionFactory sessionFactory;  
    @Override
    public Employee findEmployeeByUserName(String name) {
        Session session = sessionFactory.openSession();  
        Criteria cri = session.createCriteria(Employee.class);  
        cri.add(Restrictions.eq("username", name)); 
        Employee employee = null;
        if (cri.list().size()>0){
            employee = (Employee) cri.list().get(0);  
        }
        session.close();  
        return employee ;
    }

}
