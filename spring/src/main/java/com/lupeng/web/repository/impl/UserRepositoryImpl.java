package com.lupeng.web.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
    @Override
    public Long removeUserById(Long id) {
        
        Session session = getCurrentSession();  
        
        Transaction tx1 = session.beginTransaction();  
         
        // 先加载一个持久化对象  
         
        User customer = (User)session.load(User.class, id);  
         
        session.delete(customer); // 计划执行一个delete语句  
         
        tx1.commit(); // 清理缓存，执行delete语句  
         
        session.close();// 从缓存中删除Customer对象  
        return 1L;
    }

}
