package com.lupeng.web.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lupeng.web.entity.Employee;
import com.lupeng.web.entity.Power;
import com.lupeng.web.repository.PowerRepository;
@Repository
public class PowerRepositoryImpl implements PowerRepository {

    @Autowired
    private SessionFactory sessionFactory;  
    
    public Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }
    
//    @Override
//    public List<Power> getAllPower() {
//        Session session = sessionFactory.openSession();  
//        Criteria cri = session.createCriteria(Employee.class);  
//        List<Power> power =  cri.list();  
//        session.close(); 
//        return power;
//    }

    @Override
    public List<Power> getAllPowerTwo() {
      Session session = sessionFactory.openSession();  
      Criteria cri = session.createCriteria(Power.class);  
//      cri.add(Restrictions.isNotEmpty("parentId"));  
      cri.add(Restrictions.or(Restrictions.ne("parentId", null), Property.forName("parentId").isNotNull()))
;      List<Power> power =  cri.list();  
      session.close(); 
      return power;
    }

    @Override
    public List<Power> findPowerByPowerId(List<Long> powerId) {
        Session session = sessionFactory.openSession();  
        Criteria cri = session.createCriteria(Power.class);  
        cri.add(Restrictions.in("powerId", powerId));  
        List<Power> power =  cri.list();  
        session.close();
        return power;
    }

    @Override
    public List<Power> getLevelOne() {
        Session session = sessionFactory.openSession();  
        Criteria cri = session.createCriteria(Power.class);  
//        cri.add(Restrictions.isEmpty("parentId"));  
        cri.add(Restrictions.or(Restrictions.eq("parentId", null), Property.forName("parentId").isNull()));
        List<Power> power =  cri.list();  
        session.close();
        return power;
    }

    
}
