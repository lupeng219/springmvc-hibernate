package com.lupeng.web.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.lupeng.web.entity.Employee;
import com.lupeng.web.repository.EmployeeRepository;
import com.lupeng.web.util.PageUtil;
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Autowired
    private SessionFactory sessionFactory;  
    @Override
    public Employee findEmployeeByUserName(String name) {
        Session session = sessionFactory.openSession();  
        Criteria cri = session.createCriteria(Employee.class);  
        cri.add(Restrictions.eq("username", name)); 
        Employee employee = null;
        if (cri.list().size()>0){
            employee = (Employee) cri.list().get(0);  
        }
        session.close();  
        return employee ;
    }
    @Override
    public List<Employee> getEmployeeByEmployeeId(List<Long> employeeId) {
        // TODO Auto-generated method stub @Query("SELECT e  FROM Employee e WHERE e.employeeId  in(?1) ")
        List<Employee> e = new ArrayList<>() ;
        if (employeeId.size()>0){
            Session session = sessionFactory.openSession();  
            Criteria cri = session.createCriteria(Employee.class);  
            cri.add(Restrictions.in("employeeId", employeeId)); 
            e =  cri.list();  
            session.close();  
        }
        return e;
    }
    @Override
    public List<Employee> getAllEmployee(PageUtil pager) {
        Session session = sessionFactory.openSession();  
        session.beginTransaction();  
          
        List list = session.createQuery("from Employee")  
                .setFirstResult((pager.getPageIndex()-1)*pager.getPageSize())  
                .setMaxResults(pager.getPageSize())  
                .list();  
          
        Long count = (Long)session.createQuery("select count(*) from Employee").uniqueResult(); //获取总记录数  
        count =count==null?0:count;  
        pager.setResultList(list);
        pager.setTotalCount(count.intValue());
          
        session.getTransaction().commit();  
        session.close();
        return list;
    }
    @Override
    public Long save(Employee employee) {
        Session session = sessionFactory.openSession();  
        Long ret = (Long) session.save(employee);
        session.close(); 
        return ret;
    }
    @Override
    public void updateEmp(Employee employee) {
        Session session = sessionFactory.openSession();  
        session.beginTransaction();  
        session.update(employee);
        session.getTransaction().commit();  
        session.close(); 
       
    }
    @Override
    public Employee findEmployeeByCustNo(String custNo) {
        Session session = sessionFactory.openSession();  
        Criteria cri = session.createCriteria(Employee.class);  
        cri.add(Restrictions.eq("custNo", custNo)); 
        Employee employee = null;
        if (cri.list().size()>0){
            employee = (Employee) cri.list().get(0);  
        }
        session.close();  
        return employee ;
    }
    @Override
    public void delete(Employee employee) {
        Session session = sessionFactory.openSession();  
        session.beginTransaction();
        session.delete(employee);
        session.getTransaction().commit();  
        session.close();
    }
    @Override
    public Employee findEmployeeByEmployeeId(Long employeeId) {
        Session session = sessionFactory.openSession();  
        Employee e=  (Employee) session.get(Employee.class, employeeId);
        session.close(); 
        return e;
    }

}
