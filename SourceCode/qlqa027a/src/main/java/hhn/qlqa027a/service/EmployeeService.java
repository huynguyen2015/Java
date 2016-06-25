/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.service;

import hhn.qlqa027a.entities.Employee;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.util.Constants;
import java.util.List;
import javax.persistence.Query;

/**
 * Customer Service
 *
 * @author Cem Ikta
 */
public class EmployeeService extends AbstractService<Employee> {

    private final StatusService statusService;
    public EmployeeService() {
        super(Employee.class);
        statusService = new StatusService();
    }

    /**
     * Huy Nguyen, 2016-05-29, Get employee by email
     * @param email
     * @return 
     */
    public Employee getEmployeeByEmail(String email){
        try{
            Query query = getEntityManager().createNamedQuery(Employee.FIND_BY_EMAIL);
            query.setParameter("email", email);

            List results = query.getResultList();
            Employee foundEntity = null;
            if(!results.isEmpty()){
                // ignores multiple results
                foundEntity = (Employee) results.get(0);
            }
            return foundEntity;
        }
        catch(Exception ex){
            throw ex;
        }
    }
    
    /**
     * Huy Nguyen, 2016-05-29, Check email had existed in database
     * @param email
     * @param employeeId
     * @return 
     */
    public boolean isUsedEmail(String email, int employeeId){
        try{
            Query query = getEntityManager().createNamedQuery(Employee.FIND_BY_EMAIL);
            query.setParameter("email", email);

            List results = query.getResultList();
            Employee foundEntity = null;
            if(!results.isEmpty()){
                // ignores multiple results
                foundEntity = (Employee) results.get(0);
            }
            return foundEntity != null && employeeId != foundEntity.getId();
        }
        catch(Exception ex){
            throw ex;
        }
    }       
    
    @Override
    public boolean remove(Employee employee){
        try{
            Status deleteStatus = statusService.find(Constants.statusDeleted);
            employee.setStatus(deleteStatus);
            update(employee);
            return true;
        }catch(Exception ex){
             throw ex;
        }      
    }
}
