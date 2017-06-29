package com.lupeng.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.lupeng.web.entity.Persona_power;

public interface PersonaPowerRepository {

    public List<Long> findPowerId(long personaId);
 
    public List<Persona_power> findPersona_power(Long personaId);
    
    public void delete( List<Persona_power> persona_power);
    
    public void save(Persona_power persona_power);
}
