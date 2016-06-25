/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.service;

import hhn.qlqa027a.entities.Shift;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.util.Constants;
import java.util.List;
import javax.persistence.Query;
/**
 * Customer Service
 *
 * @author Cem Ikta
 */
public class ShiftService extends AbstractService<Shift> {

    private final StatusService statusService;
    public ShiftService() {
        super(Shift.class);
        statusService = new StatusService();
    }
    
    @Override
    public boolean remove(Shift shift){
        try{
            Status deleteStatus = statusService.find(Constants.statusDeleted);
            shift.setStatus(deleteStatus);
            update(shift);
            return true;
        }catch(Exception ex){
            throw ex;
        }    
    }
}
