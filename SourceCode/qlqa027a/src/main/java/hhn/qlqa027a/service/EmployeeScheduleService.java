/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.service;

import hhn.qlqa027a.entities.Employeeschedule;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.util.Constants;
import java.util.List;
import javax.persistence.Query;

/**
 * Customer Service
 *
 * @author Cem Ikta
 */
public class EmployeeScheduleService extends AbstractService<Employeeschedule> {

    private final StatusService statusService;
    public EmployeeScheduleService() {
        super(Employeeschedule.class);
        statusService = new StatusService();
    }

    
    @Override
    public boolean remove(Employeeschedule employeeschedule){
        try{
            Status deleteStatus = statusService.find(Constants.statusDeleted);
            employeeschedule.setStatus(deleteStatus);
            update(employeeschedule);
            return true;
        }catch(Exception ex){
             throw ex;
        }      
    }
}
