package com.lupeng.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.openjpa.persistence.jdbc.Index;

@Entity(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;// 员工id

    @Index
    @Column(length = 64)
    private String custNo;// 员工号
    @Column(length = 50)
    private String username;// 员工登录账号
    @Column(length = 50)
    private String password;// 员工登录密码
    @Column(length = 20)
    private String realName;// 员工姓名
    @Column(length = 20)
    private String qq;// 员工QQ
    @Column(length = 20)
    private String telephone;// 员工办公电话
    @Column(length = 20)
    private String position;// 员工职位
    @Column(length = 4)
    private String sex;// 员工性别
    @Column(length = 20)
    private String email;// 员工邮箱
    @Column(length = 20)
    private String phone;// 员工手机号
    @Column(length = 30)
    private String employIDCardNum;// 员工身份证
    private Date empRegTime;// 注册时间
    private Date lastLoginTime;// 最后登录时间
    @Column(length = 5)
    private String empStatus;// 员工账号状态
    @Column(length = 5)
    private String employeeType;// 主要用于区别admin

    public Employee() {

    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmployIDCardNum() {
        return employIDCardNum;
    }

    public void setEmployIDCardNum(String employIDCardNum) {
        this.employIDCardNum = employIDCardNum;
    }

    public Date getEmpRegTime() {
        return empRegTime;
    }

    public void setEmpRegTime(Date empRegTime) {
        this.empRegTime = empRegTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

}
