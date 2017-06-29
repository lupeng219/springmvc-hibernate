package com.lupeng.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.lupeng.web.entity.Persona;


public interface PersonaRepository {

   
    public List<Persona> getAllPersonas();

    public void save(Persona persona);
    
    public Persona findPersonaBypersonaId(Long personaId);

   
    public Persona findByPersonaName(String personaName);

}
