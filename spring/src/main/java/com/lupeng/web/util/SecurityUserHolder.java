package com.lupeng.web.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.lupeng.web.data.EmployeeData;

/**
 * Spring Security提供了一个线程安全的对象：SecurityContextHolder�?通过这个对象，我们可以访问当前的登录用户
 * 
 * @author Administrator
 */
public class SecurityUserHolder {

    /**
     * 获取当前用户
     * 
     * @return
     */
    public static EmployeeData getCurrentUser() {
        Authentication au = SecurityContextHolder.getContext().getAuthentication();
        if (au == null) {
            return null;
        } else {
            EmployeeData employee = (EmployeeData) au.getPrincipal();
            return employee;
        }

    }

    /**
     * 获得当前用户昵称
     * 
     * @return
     */
    public static String getNickname() {
        Authentication au = SecurityContextHolder.getContext().getAuthentication();
        if (au == null) {
            return null;
        } else {
            EmployeeData employee = (EmployeeData) au.getPrincipal();
            if (employee != null) {
                return employee.getRealName();
            } else {
                return null;
            }
        }

    }

    /**
     * 获得当前用户的客户号
     * 
     * @return
     */
    public static String getCustNo() {
        Authentication au = SecurityContextHolder.getContext().getAuthentication();
        if (au == null) {
            return null;
        } else {
            EmployeeData employee = (EmployeeData) au.getPrincipal();
            if (employee != null) {
                return employee.getCustNo();
            } else {
                return null;
            }
        }

    }

    /**
     * 获得当前用户的的IP地址
     * 
     * @return
     */
    public static String getRemoteAddress() {
        try {
            Authentication au = SecurityContextHolder.getContext().getAuthentication();
            if (au == null) {
                return null;
            } else {
                WebAuthenticationDetails auth = (WebAuthenticationDetails) au.getDetails();
                return auth.getRemoteAddress();
            }
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 获取当前用户认证
     * 
     * @return
     */
    public static Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        } else {
            return authentication;
        }

    }

    /**
     * 设置当前用户认证
     * 
     * @return
     */
    public static void setAuthentication(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
