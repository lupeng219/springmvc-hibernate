package com.lupeng.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.lupeng.web.entity.Power;

public interface PowerRepository {
//    
//    public List<Power> getAllPower();
  
    public List<Power> getLevelOne();
    
    public List<Power> getAllPowerTwo();
    
    public List<Power> findPowerByPowerId(List<Long> powerId);
}
