package com.lupeng.web.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        List<Power> power =new ArrayList<>();
        if(powerId.size()>0){
            Session session = sessionFactory.openSession();  
            Criteria cri = session.createCriteria(Power.class);  
            cri.add(Restrictions.in("powerId", powerId));  
            power =  cri.list();  
            session.close();
        }
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

    @Override
    public void save(Power power) {
        Session session = sessionFactory.openSession();  
        Transaction tx=session.beginTransaction();
        session.save(power);//保存后customer对象处于持久化状态
        session.flush();//清空缓存后customer对象处于游离状态
        tx.commit();
        session.close();
//        getCurrentSession().save(power);
    }

    
}
