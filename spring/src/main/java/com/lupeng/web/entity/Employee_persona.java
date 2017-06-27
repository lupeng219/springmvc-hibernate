package com.lupeng.web.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Employee_persona")
public class Employee_persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId; // id
    @Column(length = 11)
    private Long employeeId; // 员工id
    @Column(length = 11)
    private Long personaId; // 角色id

    public Employee_persona() {

    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

}
