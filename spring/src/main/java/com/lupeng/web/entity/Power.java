package com.lupeng.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Power")
public class Power {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long powerId;
    @Column(length = 60)
    private String powerUrl;// 权限路径
    @Column(length = 50)
    private String menuName;// 菜单名称
    @Column(length = 11)
    private Long parentId;// 父类菜单id
    @Column(length = 30)
    private String powerName;// 权限名称

    public Long getPowerId() {
        return powerId;
    }

    public void setPowerId(Long powerId) {
        this.powerId = powerId;
    }

    public String getPowerUrl() {
        return powerUrl;
    }

    public void setPowerUrl(String powerUrl) {
        this.powerUrl = powerUrl;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

}
