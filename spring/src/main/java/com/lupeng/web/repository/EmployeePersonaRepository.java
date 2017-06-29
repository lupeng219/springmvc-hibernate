package com.lupeng.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.lupeng.web.entity.Employee_persona;

public interface EmployeePersonaRepository {

    public Long findPersonaIdByEmployeeId(long employeeId);
    
   
    public Employee_persona findEmployee_personaByEmployeeId(long employeeId);

   
    public List<Long> fingEmployeeIdBypersonaId(Long personaId);
}
