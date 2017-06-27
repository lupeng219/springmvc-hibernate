package com.lupeng.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personaId; // 角色id
    @Column(length = 50)
    private String personaName; // 角色名
    @Column(length = 50)
    private String personaDesc;// 角色描述
    @Column(length = 2)
    private String personaEnable;// 角色是否启用

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

    public String getPersonaName() {
        return personaName;
    }

    public void setPersonaName(String personaName) {
        this.personaName = personaName;
    }

    public String getPersonaDesc() {
        return personaDesc;
    }

    public void setPersonaDesc(String personaDesc) {
        this.personaDesc = personaDesc;
    }

    public String getPersonaEnable() {
        return personaEnable;
    }

    public void setPersonaEnable(String personaEnable) {
        this.personaEnable = personaEnable;
    }

}
