package com.lupeng.web.repository;

import com.lupeng.web.entity.User;

public interface UserRepository {

    
    public Long save(User user); 
    
    public User findAll(Long id);
}
