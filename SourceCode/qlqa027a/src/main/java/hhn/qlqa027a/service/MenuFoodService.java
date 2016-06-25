/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhn.qlqa027a.service;

import hhn.qlqa027a.entities.Menufood;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.util.Constants;

/**
 *
 * @author Hoa The
 */
public class MenuFoodService extends AbstractService<Menufood> {
    
    private final StatusService statusService;
    
    public MenuFoodService() {
        super(Menufood.class);
        statusService = new StatusService();
    }
    
    @Override
    public boolean remove(Menufood menufood){
        try{
            Status deleteStatus = statusService.find(Constants.statusDeleted);
            menufood.setStatus(deleteStatus);
            update(menufood);
            return true;
        }catch(Exception ex){
             
        }
        return false;
    }
}
