/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhn.qlqa027a.service;

import hhn.qlqa027a.entities.Menu;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.util.Constants;

/**
 *
 * @author Hoa The
 */
public class MenuService extends AbstractService<Menu>{
    
    private final StatusService statusService;
    
    public MenuService() {
        super(Menu.class);
        statusService = new StatusService();
    }
    
    @Override
    public boolean remove(Menu menu){
        try{
            Status deleteStatus = statusService.find(Constants.statusDeleted);
            menu.setStatus(deleteStatus);
            update(menu);
            return true;
        }catch(Exception ex){
             
        }
        return false;
    }
}
