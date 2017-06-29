package com.lupeng.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lupeng.web.entity.Persona;
import com.lupeng.web.entity.Persona_power;
import com.lupeng.web.repository.PersonaPowerRepository;
import com.lupeng.web.repository.PersonaRepository;
import com.lupeng.web.repository.PowerRepository;
import com.lupeng.web.service.PersonaService;

@Service("personaService")
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private PersonaPowerRepository personaPowerRepository;
    @Autowired
    private PowerRepository powerRepository;

    @Override
    public List<Persona> getAllPersonas() {
        return personaRepository.getAllPersonas();
    }

    @Override
    public Persona getByPersonaId(Long personaId) {
        return personaRepository.findPersonaBypersonaId(personaId);
    }

    @Override
    public Persona getByPersonaName(String personaName) {
        return personaRepository.findByPersonaName(personaName);
    }

    @Transactional(readOnly = false)
    @Override
    public void updatePersona(Persona persona, Long[] powerIds) {
        try {
            Long personaId = persona.getPersonaId();
//            if (personaId.equals(null) || "".equals(personaId)){
//                personaRepository.save(persona);
//            }
            // 先删除以前的权限
            List<Persona_power> persona_power = personaPowerRepository.findPersona_power(personaId);
            personaPowerRepository.delete(persona_power);
            // 重新添加权限
            if (powerIds != null) {
                for (Long tempLong : powerIds) {
                    Persona_power personapower = new Persona_power();
                    personapower.setPersonaId(personaId);
                    personapower.setPowerId(tempLong);
                    personaPowerRepository.save(personapower);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional(readOnly = false)
    @Override
    public int addPersona(Persona persona, Long[] powerIds) {
        int result = 1;
        Persona temp_persona = null;
        Long personaId = 0l;
        String temp_str = null;
        temp_str = persona.getPersonaName();
        temp_persona = personaRepository.findByPersonaName(temp_str);
        if (temp_persona != null) {
            return 0;
        }
        try {
            personaRepository.save(persona);
            personaId = persona.getPersonaId();
            Persona_power personapower = new Persona_power();
            if (powerIds != null) {
                for (Long tempLong : powerIds) {
                    personapower.setPersonaId(personaId);
                    personapower.setPowerId(tempLong);
                    personaPowerRepository.save(personapower);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

}
