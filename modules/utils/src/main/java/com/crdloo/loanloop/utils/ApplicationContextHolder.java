package com.crdloo.loanloop.utils;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;

/**
 * 用于获取当前应用的ApplicationContext
 *
 * 须要在IoC容器创建该对象
 */
public class ApplicationContextHolder implements ApplicationContextAware, ServletContextAware {
    protected static ApplicationContext applicationContext;
    protected static ServletContext servletContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
    }

    public void setServletContext(ServletContext context) {
        servletContext = context;
    }

    public static ServletContext getServletContext() {
        return servletContext;
    }

    public static Object getBean(String name) throws BeansException {
        if (applicationContext == null)
            return null;

        return applicationContext.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        if (applicationContext == null)
            return null;

        return applicationContext.getBean(name, requiredType);
    }

    public static <T> T getBean(Class<T> requiredType) throws BeansException {
        if (applicationContext == null)
            return null;

        return applicationContext.getBean(requiredType);
    }
}
