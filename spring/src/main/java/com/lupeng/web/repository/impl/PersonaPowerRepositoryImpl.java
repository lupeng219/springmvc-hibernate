package com.lupeng.web.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lupeng.web.entity.Employee;
import com.lupeng.web.entity.Persona_power;
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
        List<Long> id = null;
        for(Persona_power p: persona_power){
           id.add(p.getPowerId());
        }
        session.close();  
        return id;
    }

}
