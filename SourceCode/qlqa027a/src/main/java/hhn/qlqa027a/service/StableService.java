/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.service;

import hhn.qlqa027a.entities.Stable;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.util.Constants;

/**
 * Category Service
 *
 * @author Cem Ikta
 */
public class StableService extends AbstractService<Stable> {

    private final StatusService statusService;
    public StableService() {
        super(Stable.class);
        statusService = new StatusService();
    }  

    @Override
    public boolean remove(Stable stable){
        try{
            Status deleteStatus = statusService.find(Constants.statusDeleted);
            stable.setStatus(deleteStatus);
            update(stable);
            return true;
        }catch(Exception ex){
             throw ex;
        }      
    }
}
