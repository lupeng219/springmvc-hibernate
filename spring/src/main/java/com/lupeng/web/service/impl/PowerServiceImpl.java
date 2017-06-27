package com.lupeng.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lupeng.web.data.Index_Menu;
import com.lupeng.web.entity.Power;
import com.lupeng.web.repository.PowerRepository;
import com.lupeng.web.service.PowerService;
@Service
public class PowerServiceImpl  implements PowerService{
    @Autowired
    private PowerRepository powerRepository;
    @Override
    public List<Index_Menu> getMenus() {
        // 首页菜单实体类
        Index_Menu indexMenu = null;
        // 二级菜单
        Power subMenu = null;
        // 首页菜单集合，返回结果集
        List<Index_Menu> reuslt_list = new ArrayList<Index_Menu>();
        // 一级菜单
        List<Power> level_one = null;
        level_one = powerRepository.getLevelOne();
        // 二级菜单
        List<Power> level_two = null;

        level_two = powerRepository.getAllPowerTwo();
        // 临时long类型
        Long temp_long = null;
        List<Power> subMenu_list = null;
        // 临时二级菜单
        Power classB = null;
        // 遍历一级菜单
        for (Power classA : level_one) {
            indexMenu = new Index_Menu();
            // 一级菜单权限
            String classA_powerName = "";
            if (level_two != null && level_two.size() > 0) {
                subMenu_list = new ArrayList<Power>();
                // 遍历二级菜单
                for (int i = 0; i < level_two.size(); i++) {
                    classB = level_two.get(i);
                    // 如果等于一级菜单id
                    temp_long = classB.getParentId();
                    if (temp_long.equals(classA.getPowerId())) {
                        subMenu = new Power();
                        // 菜单
                        subMenu.setMenuName(classB.getMenuName());
                        // 权限路径
                        subMenu.setPowerUrl(classB.getPowerUrl());
                        // 二级菜单权限
                        subMenu.setPowerName(classB.getPowerName());
                        // 添加二级菜单
                        subMenu_list.add(subMenu);
                        classA_powerName += level_two.get(i).getPowerName() + ",";
                    }
                }
                // 一级菜单权限
                // 去掉最后的逗号
                if (classA_powerName != null && !"".equals(classA_powerName)) {
                    classA_powerName = classA_powerName.substring(0, classA_powerName.length() - 1);
                }
                // 一级菜单的二级菜单list
                indexMenu.setSubMenuName(subMenu_list);
            }
            // 一级菜单和二级菜单组装
            indexMenu.setMenuName(classA.getMenuName());
            indexMenu.setClassName(classA.getPowerName());
            indexMenu.setPowerName(classA_powerName);
            reuslt_list.add(indexMenu);
        }

        return reuslt_list;
    }

}
