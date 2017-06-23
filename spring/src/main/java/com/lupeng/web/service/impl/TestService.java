package com.lupeng.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lupeng.web.entity.User;
import com.lupeng.web.repository.UserRepository;

@Service("testService")
public class TestService implements com.lupeng.web.service.TestService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public String test() {
        return "TEST";
    }

    @Override
    public Long savaUser(User user) {
        
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findAll(id);
    }

}
