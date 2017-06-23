package com.lupeng.web.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lupeng.web.entity.User;
import com.lupeng.web.repository.UserRepository;
@Repository
public class UserRepositoryImpl  implements UserRepository{
    
    @Autowired
    private SessionFactory sessionFactory;  
    
    public Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }
    @Override
    public Long save(User user) {
      
        return (Long) getCurrentSession().save(user);
       
    }
    @Override
    public User findAll(Long id) {
        return (User) getCurrentSession().get(User.class, id);
    }

}
