package com.lupeng.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lupeng.web.entity.Employee;
import com.lupeng.web.entity.Persona;
import com.lupeng.web.service.EmployeeService;
import com.lupeng.web.service.PersonaService;
import com.lupeng.web.util.Const;
import com.lupeng.web.util.GetUniqueNoUtil;
import com.lupeng.web.util.PageUtil;
import com.lupeng.web.util.StringUtil;


@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PersonaService personaService;

    /**
     * 获得员工列表
     * 
     * @param model
     * @param pageNumber
     * @param pageSize
     * @param personaName
     * @param parameName
     * @return
     */
    @RequestMapping("/getAllEmployees")
    public String getAllEmployees(Model model, Integer pageNumber, Integer pageSize,
            String personaName, String parameName) {
        if (pageNumber == null) {
            pageNumber = 0;
        } else {
            pageNumber--;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageUtil pageUtil = new PageUtil(pageSize);
        pageUtil.setPageIndex(pageNumber + 1);
        pageUtil.setPageSize(pageSize);
        
        List<Employee> list = employeeService.getAllEmployee(pageUtil);
        model.addAttribute("page", pageUtil);
        model.addAttribute("employeeList", list);
        List<Persona> personas = personaService.getAllPersonas();
        model.addAttribute("personas", personas);
        return "employee/getAllEmployees";
    }

    /**
     * 添加员工
     * 
     * @param request
     * @param response
     * @param employee
     * @return
     */
    @RequestMapping("/addEmployee")
    @ResponseBody
    public Map<String, Object> addEmployee(HttpServletRequest request,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "realName") String realName,
            @RequestParam(value = "employIDCardNum") String employIDCardNum,
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "personaId") Long personaId) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtil.isEmpty(username) && StringUtil.isEmpty(realName)) {
            map.put(Const.retCode, false);
            map.put(Const.retMsg, "登录名或真实姓名不能为空");
            return map;
        }
        Employee employee = new Employee();
        Long result = null;
        String custNo = GetUniqueNoUtil.getCustNo();
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setRealName(realName);
        employee.setEmployIDCardNum(employIDCardNum);
        employee.setPhone(phone);
        employee.setEmpRegTime(new Date());
        // 可用
        employee.setEmpStatus("1");
        // 普通员工
        employee.setEmployeeType("2");
        employee.setCustNo(custNo);
        result = employeeService.addEmployee(employee, personaId);
        if (result.equals(0l)) {
            map.put(Const.retCode, Boolean.FALSE);
            map.put(Const.retMsg, "该员工已经存在!");
            return map;
        }
        map.put(Const.retCode, Boolean.TRUE);
        map.put(Const.retMsg, "添加成功!");
        return map;
    }

    /**
     * 更新员工状态
     * 
     * @param request
     * @param empStatus
     * @param custNo
     * @return
     */
    @RequestMapping("/updateEmpState")
    @ResponseBody
    public Map<String, Object> updateEmpState(HttpServletRequest request,
            @RequestParam(value = "empStatus") String empStatus,
            @RequestParam(value = "custNo") String custNo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map = employeeService.updateEnable(empStatus, custNo);
        return map;
    }

    /**
     * 更新密码
     * 
     * @param request
     * @param password
     * @param custNo
     * @return
     */
    @RequestMapping("/updatePassword")
    @ResponseBody
    public Map<String, Object> updatePassword(HttpServletRequest request,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "repassword") String repassword,
            @RequestParam(value = "custNo") String custNo) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtil.isEmpty(password)) {
            map.put(Const.retCode, false);
            map.put(Const.retMsg, "新密码不能为空!");
            return map;
        } ;
        if (password.length() < 4 || password.length() > 16) {
            map.put(Const.retCode, false);
            map.put(Const.retMsg, "密码在4-16位之间!");
            return map;
        } ;
        if (StringUtil.isEmpty(repassword)) {
            map.put(Const.retCode, false);
            map.put(Const.retMsg, "重复密码不能为空!");
            return map;
        } ;
        if (!password.equals(repassword)) {
            map.put("retCode", false);
            map.put("retMsg", "两次密码不相等!");
            return map;
        } ;
        return employeeService.updatePassword(repassword, custNo);

    }

    /**
     * 删除员工
     * 
     * @param custNo
     * @return
     */
    @RequestMapping("/deleteEmployee")
    @ResponseBody
    public Map<String, Object> deleteEmployee(String custNo) {
        return employeeService.deleteEmployee(custNo);
    }

    /**
     * 得到管理员的详细信息
     * 
     * @param custNo
     * @return
     */
    @RequestMapping("/getEmployeeInfo")
    @ResponseBody
    public Map<String, Object> getEmployeeInfo(String custNo) {
        return employeeService.getEmployeeInfo(custNo);

    }

    /**
     * 更新员工
     * 
     * @param request
     * @param username
     * @param password
     * @param realName
     * @param employIDCardNum
     * @param phone
     * @param personaId
     * @return
     */
    @RequestMapping("/updateEmployee")
    @ResponseBody
    public Map<String, Object> updateEmployee(HttpServletRequest request,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "realName") String realName,
            @RequestParam(value = "employIDCardNum") String employIDCardNum,
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "personaId") Long personaId,
            @RequestParam(value = "employeeId") Long employeeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtil.isEmpty(username) && StringUtil.isEmpty(realName)) {
            map.put(Const.retCode, false);
            map.put(Const.retMsg, "登录名或真实姓名不能为空");
            return map;
        }
        Employee employee = new Employee();
        employee.setUsername(username);
        employee.setRealName(realName);
        employee.setEmployIDCardNum(employIDCardNum);
        employee.setPhone(phone);
        employee.setEmployeeId(employeeId);
        if (personaId == 1) {
            employee.setEmployeeType("1");
        } else {
            employee.setEmployeeType("2");
        }
        return employeeService.updateEmployee(employee, personaId);

    }
}
