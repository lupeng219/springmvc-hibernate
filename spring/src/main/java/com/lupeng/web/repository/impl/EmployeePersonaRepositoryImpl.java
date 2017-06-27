package com.lupeng.web.repository.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lupeng.web.entity.Employee;
import com.lupeng.web.entity.Employee_persona;
import com.lupeng.web.repository.EmployeePersonaRepository;
@Repository
public class EmployeePersonaRepositoryImpl  implements EmployeePersonaRepository{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Long findPersonaIdByEmployeeId(long employeeId) {
       
      Session session = sessionFactory.openSession();  
      Criteria cri = session.createCriteria(Employee_persona.class);  
      cri.add(Restrictions.eq("employeeId", employeeId));  
      Employee_persona employee_persona = (Employee_persona) cri.list().get(0);  
      session.close(); 
      return employee_persona.getPersonaId();
    }

}
