package com.lupeng.web.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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

    @Override
    public Employee_persona findEmployee_personaByEmployeeId(long employeeId) {
        // TODO Auto-generated method stub @Query("SELECT e FROM Employee_persona e WHERE e.employeeId =?1")
        Session session = sessionFactory.openSession();  
        Criteria cri = session.createCriteria(Employee_persona.class);  
        cri.add(Restrictions.eq("employeeId", employeeId));  
        Employee_persona employee_persona = (Employee_persona) cri.list().get(0);  
        session.close(); 
        return employee_persona;
    }

    @Override
    public List<Long> fingEmployeeIdBypersonaId(Long personaId) {
        // TODO Auto-generated method stub @Query("SELECT e.employeeId FROM Employee_persona e WHERE e.personaId IN(?1)")
        Session session = sessionFactory.openSession();  
        Criteria cri = session.createCriteria(Employee_persona.class);  
        cri.add(Restrictions.eq("personaId", personaId));  
        List<Employee_persona> employee_persona = cri.list();
        List<Long>  id = new ArrayList<Long>();
        for (Employee_persona e:employee_persona){
            id.add(e.getEmployeeId());
        }
        session.close(); 
        return id;
    }

}
