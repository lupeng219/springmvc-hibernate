package com.lupeng.web.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lupeng.web.entity.Employee;
import com.lupeng.web.entity.Persona_power;
import com.lupeng.web.entity.User;
import com.lupeng.web.repository.PersonaPowerRepository;

@Repository
public class PersonaPowerRepositoryImpl  implements PersonaPowerRepository{

    @Autowired
    private SessionFactory sessionFactory;  
    
    @Override
    public List<Long> findPowerId(long personaId) {
        Session session = sessionFactory.openSession();  
        Criteria cri = session.createCriteria(Persona_power.class);  
        cri.add(Restrictions.eq("personaId", personaId));  
        List<Persona_power> persona_power =  cri.list();  
        List<Long> id = new ArrayList<>();
        for(Persona_power p: persona_power){
           id.add(p.getPowerId());
        }
        session.close();  
        return id;
    }

    @Override
    public List<Persona_power> findPersona_power(Long personaId) {
        // TODO Auto-generated method stub   @Query("SELECT  pp from Persona_power pp  WHERE pp.personaId=?1")
        Session session = sessionFactory.openSession();  
        Criteria cri = session.createCriteria(Persona_power.class);  
        cri.add(Restrictions.eq("personaId", personaId));  
        List<Persona_power> persona_power_list =  cri.list();  
        session.close();  
        return persona_power_list;
        
    }

    @Override
    public void delete(List<Persona_power> persona_power) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.openSession();  
        
        Transaction tx1 = session.beginTransaction();  
         
        // 先加载一个持久化对象  
        for (Persona_power p :persona_power){
        
        Persona_power pp = (Persona_power)session.load(Persona_power.class, p.getId());  
         
        session.delete(pp); // 计划执行一个delete语句  
        } 
        tx1.commit(); // 清理缓存，执行delete语句  
         
        session.close();// 从缓存中删除Customer对象  
        
    }

    @Override
    public void save(Persona_power persona_power) {
        sessionFactory.openSession().save(persona_power);
        
    }

}
