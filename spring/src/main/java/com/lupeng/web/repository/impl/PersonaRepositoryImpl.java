package com.lupeng.web.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lupeng.web.entity.Employee;
import com.lupeng.web.entity.Persona;
import com.lupeng.web.repository.PersonaRepository;
@Repository
public class PersonaRepositoryImpl implements PersonaRepository {

    @Autowired
    private SessionFactory sessionFactory;  
    
    public Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }
    
    @Override
    public List<Persona> getAllPersonas() {
        
        // TODO Auto-generated method stub
        //@Query("SELECT p FROM Persona p ")
        Session session = sessionFactory.openSession();  
        Criteria cri = session.createCriteria(Persona.class);  
        List<Persona>  perList = cri.list();
        session.close();  
        return perList ;
    }

    @Override
    public Persona findPersonaBypersonaId(Long personaId) {
        // TODO Auto-generated method stub @Query("SELECT p FROM Persona p WHERE p.personaId = ?1")
        return (Persona) getCurrentSession().get(Persona.class,personaId);
    }

    @Override
    public Persona findByPersonaName(String personaName) {
        // TODO Auto-generated method stub @Query("SELECT p FROM Persona p WHERE p.personaName=?1")
        Session session = sessionFactory.openSession();  
        Criteria cri = session.createCriteria(Persona.class);
        cri.add(Restrictions.eq("personaName", personaName));
        List<Persona>  perList = cri.list();
        Persona persona =null;
        if(perList.size()>0){
            persona = perList.get(0);
        }
        session.close();  
        return persona;
    }

    @Override
    public void save(Persona persona) {
        // TODO Auto-generated method stub
        getCurrentSession().save(persona);
        
    }

}
