package com.lupeng.web.service;

import com.lupeng.web.entity.User;

public interface TestService {
    
    public String test();
    
    public Long savaUser(User user);
    
    public User findUserById(Long id);
    
    public Long removeUserById(Long id);
}
