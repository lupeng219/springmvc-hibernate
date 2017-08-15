package com.lupeng.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



import com.lupeng.web.data.EmployeeData;
import com.lupeng.web.data.Index_Menu;
import com.lupeng.web.data.PowerData;
import com.lupeng.web.entity.Employee;
import com.lupeng.web.entity.Persona;
import com.lupeng.web.entity.Power;
import com.lupeng.web.service.EmployeeService;
import com.lupeng.web.service.PersonaService;
import com.lupeng.web.service.PowerService;
import com.lupeng.web.util.BusinessExcepsion;
import com.lupeng.web.util.Const;
import com.lupeng.web.util.JsonHelper;
import com.lupeng.web.util.SecurityUserHolder;
import com.lupeng.web.util.StringUtil;


@Controller
@RequestMapping("/persona")
public class PersonaController {

    private static final Logger logger = LoggerFactory.getLogger(PersonaController.class);

    @Autowired
    private PersonaService personaService;
    @Autowired
    private PowerService powerService;
    @Autowired
    private EmployeeService employeeService;
    @Resource(name = "redisTemplate")
    protected ValueOperations<String, List<Index_Menu>> Index_MenuCache;

    /**
     * 角色详情
     * 
     * @param request
     * @param personaId
     * @return
     */
    @RequestMapping("/getPersonaDetails")
    public String getPersonaDetails(HttpServletRequest request,
            @RequestParam(value = "personaId", required = false) Long personaId) {
        logger.info(String.format(" call getPersonaDetails +++#personaId :  %s]", personaId));
        List<Persona> allPersonas = null;
        allPersonas = personaService.getAllPersonas();
        request.setAttribute("allPersonas", allPersonas);
        // 角色成员列表
        List<Employee> allEmployee = null;
        // 角色对应的权限
        List<Power> persona_powers = null;
        // 所有权限查询
        List<Power> allPowers = null;

        List<PowerData> allPowerDatas = new ArrayList<>();
        // 角色信息
        Persona p2p_persona = null;
        // 得到所有的权限
        allPowers = powerService.getAllPower();
        for (Power power : allPowers) {
            allPowerDatas.add(JsonHelper.convert(power, PowerData.class));
           
        }
        if (personaId != null) {
            List<Long> employeeId = employeeService.fingEmployeeIdBypersonaId(personaId);
            allEmployee = employeeService.getEmployeeByEmployeeId(employeeId);
            request.setAttribute("allEmployee", allEmployee);
            List<Long> powerId = powerService.fingPowerIdByPersonaId(personaId);
            persona_powers = powerService.findPowerByPowerId(powerId);
            request.setAttribute("persona_powers", persona_powers);
            p2p_persona = personaService.getByPersonaId(personaId);
            request.setAttribute("p2p_persona", p2p_persona);
            request.setAttribute("personaId", personaId);
        }

        List<Power> list1 = powerService.getLevelOne();
        // 查询所有权限信息
        // 一级权限放入list1,二级权限放入list2
        allPowerDatas = getNewList(allPowerDatas, persona_powers);
        request.setAttribute("allPowers", allPowerDatas);
        request.setAttribute("list1", list1);
        request.setAttribute("personaId", personaId);
        return "power/toPowerIndex";
    }

    public List<PowerData> getNewList(List<PowerData> allPowers, List<Power> persona_powers) {
        List<PowerData> result = new ArrayList<PowerData>();
        Long powerId = null;
        for (PowerData powerA : allPowers) {
            powerId = powerA.getPowerId();
            if (persona_powers != null) {
                for (Power powerB : persona_powers) {
                    if (powerId.equals(powerB.getPowerId())) {
                        powerA.setTempStr("1");
                        break;
                    }
                }
            }
            result.add(powerA);
        }
        return result;
    }

    /**
     * 添加或者保存角色
     * 
     * @param request
     * @param personId
     * @param powerIds
     * @param personaName
     * @param personaDesc
     * @return
     */
    @RequestMapping("/savaOrUpdatePersona")
    @ResponseBody
    public Map<String, Object> savaOrUpdatePersona(HttpServletRequest request,
            @RequestParam(value = "personId", required = false) Long personId,
            @RequestParam(value = "powerIds[]", required = false) Long[] powerIds,
            @RequestParam(value = "personaName", required = false) String personaName,
            @RequestParam(value = "personaDesc", required = false) String personaDesc) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("retCode", true);
        if (StringUtil.isEmpty(personaName)) {
            map.put(Const.retCode, false);
            map.put(Const.retMsg, "角色名称不能为空");
            return map;
        }
        if (personId != null && personId == 1) {
            return map;
        } else {
            Persona oldPersona = null;
            Persona persona = new Persona();
            persona.setPersonaDesc(personaDesc);
            persona.setPersonaEnable("1");
            persona.setPersonaName(personaName);
            persona.setPersonaId(personId);
            // 修改
            if (personId != null) {
                personaService.updatePersona(persona, powerIds);
                // 更新用户权限
                List<Index_Menu> result = null;
                result = Index_MenuCache.get(Const.MENU + personId);
                if (result != null) {
                    Index_MenuCache.getOperations().delete(Const.MENU + personId);
                }
            } else {
                oldPersona = personaService.getByPersonaName(personaName);
                // 添加
                if (oldPersona != null) {
                    map.put("retCode", false);
                    map.put("retMsg", "角色名称已经存在");
                    return map;
                }
                personaService.addPersona(persona, powerIds);
            }
            EmployeeData employeeData = SecurityUserHolder.getCurrentUser();
            if (personId != null) {
                if (personId.equals(employeeData.getPersonaId())) {
                    SecurityUserHolder
                            .setAuthentication(employeeService.reLoadUserPower(employeeData));
                }
            }
        }
        return map;
    }

    /**
     * 添加菜单
     */
    @RequestMapping("addPower")
    public String addPower(HttpServletRequest request) throws BusinessExcepsion {
        List<Power> power = powerService.getLevelOne();
        try {
            
        } catch (Exception e) {
            throw new BusinessExcepsion(); 
        }
       
        request.setAttribute("power", power);
        return "power/addPower";

    }

    /**
     * 保存添加菜单信息
     */
    @RequestMapping("addPowerInfoTwo")
    @ResponseBody
    public Map<String, Object> addPowerInfoTwo(HttpServletRequest request, String powerUrl,
            String powerName, String menuName, Long powerId) {
        Power power = new Power();
        power.setMenuName(menuName);
        power.setParentId(powerId);
        power.setPowerName(powerName);
        power.setPowerUrl(powerUrl);
        return powerService.addPowerInfo(power);
    }

    /**
     * 保存添加菜单信息
     */
    @RequestMapping("addPowerInfo")
    @ResponseBody
    public Map<String, Object> addPowerInfo(HttpServletRequest request, String powerName,
            String menuName) {
        Power power = new Power();
        power.setMenuName(menuName);
        power.setPowerName(powerName);
        return powerService.addPowerInfo(power);
    }
}
