package com.lupeng.web.service;

import java.util.List;
import java.util.Map;

import com.lupeng.web.data.Index_Menu;
import com.lupeng.web.entity.Power;

public interface PowerService {
    

    /**
     * 得到员工可以展示的页面
     * 
     * @param custNo
     * @return
     */
    public List<Index_Menu> getMenus();

    /**
     * 得到所有的权限
     * 
     * @return
     */
    public List<Power> getAllPower();

    /**
     * 一级权限
     * 
     * @return
     */
    public List<Power> getLevelOne();

    /**
     * 根据角色id查询权限id
     */
    public List<Long> fingPowerIdByPersonaId(Long personaId);

    /**
     * 根据powerid查询权限
     */
    public List<Power> findPowerByPowerId(List<Long> powerId);

    /**
     * 添加菜单
     * 
     * @param power
     * @return
     */
    public Map<String, Object> addPowerInfo(Power power);
}
