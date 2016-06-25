/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.service;

import hhn.qlqa027a.entities.Role;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.util.Constants;

/**
 * Category Service
 *
 * @author Cem Ikta
 */
public class RoleService extends AbstractService<Role> {

    private final StatusService statusService;
    public RoleService() {
        super(Role.class);
        statusService = new StatusService();
    }  

    @Override
    public boolean remove(Role role){
        try{
            Status deleteStatus = statusService.find(Constants.statusDeleted);
            role.setStatus(deleteStatus);
            update(role);
            return true;
        }catch(Exception ex){
             throw ex;
        }      
    }
}
