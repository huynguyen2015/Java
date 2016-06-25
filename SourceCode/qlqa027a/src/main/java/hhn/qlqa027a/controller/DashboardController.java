/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.controller;

import hhn.qlqa027a.framework.AbstractPageController;
import hhn.qlqa027a.framework.PageView;
import hhn.qlqa027a.GUI.DashboardPage;
import hhn.qlqa027a.entities.Employee;
import hhn.qlqa027a.service.AbstractService;
import hhn.qlqa027a.service.EmployeeService;

/**
 * Dashboard Controller.
 *
 * @author Cem Ikta
 */
public class DashboardController extends AbstractPageController {
        
    private EmployeeService employeeService;
    @Override
    protected PageView createPageView() {        
        return new DashboardPage();
    }    

    public String getName() {
        return "DashboardController";
    }
    
    public EmployeeService getService() {
        if (employeeService == null) {
            employeeService = createService();
        }
        return employeeService;
    }
    
    protected EmployeeService createService() {
        return new EmployeeService();
    }

    public Employee CheckLogin(String email, String password) throws Exception{
        try{
            if(email == null || "".equals(email))
                throw new Exception("Email can't be empty");
            if(password == null || "".equals(password))
                throw new Exception("Password can't be empty");
            if(password.length() < 6)
                throw new Exception("Password is incorrect");
            
            Employee employee = getService().getEmployeeByEmail(email);
            if(employee == null)
                throw new Exception("Email is not existed");
            else if(!employee.getPassword().equals(password))
                throw new Exception("Password is incorrect");
            return employee;
        }catch(Exception ex){
            throw ex;
        }
    }
}
