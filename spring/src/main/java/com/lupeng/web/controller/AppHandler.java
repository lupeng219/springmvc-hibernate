package com.lupeng.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lupeng.web.util.BusinessExcepsion;

@ControllerAdvice
public class AppHandler {
    @ExceptionHandler(BusinessExcepsion.class)
    public String dupHandler(HttpServletRequest request,Model model){
        request.setAttribute("aa", "a");
        model.addAttribute("a", "aa");
        return "redirect:/service/login/index";
    }
   
}
