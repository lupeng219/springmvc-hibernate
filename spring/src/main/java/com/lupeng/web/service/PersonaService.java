package com.lupeng.web.service;

import java.util.List;

import com.lupeng.web.entity.Persona;

public interface PersonaService {

    /**
     * 查询的角色列表
     * 
     * @return
     */
    public List<Persona> getAllPersonas();

    /**
     * 根据personaId查询角色
     * 
     * @param personaId
     * @return
     */
    public Persona getByPersonaId(Long personaId);

    /**
     * 根据name查询
     */
    public Persona getByPersonaName(String personaName);

    /**
     * 更新角色和权限
     * 
     * @param persona
     * @param powerId
     */
    public void updatePersona(Persona persona, Long[] powerIds);

    /**
     * 添加新的角色
     * 
     * @param persona
     * @return
     */
    public int addPersona(Persona persona, Long[] powerIds);

}
