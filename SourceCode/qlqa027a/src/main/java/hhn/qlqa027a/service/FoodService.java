/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhn.qlqa027a.service;

import hhn.qlqa027a.entities.Food;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.util.Constants;

/**
 *
 * @author Administrator
 */
public class FoodService extends AbstractService<Food>{
    
    private final StatusService statusService;
    
    public FoodService() {
        super(Food.class);
        statusService = new StatusService();
    }
    
    @Override
    public boolean remove(Food food){
        try{
            Status deleteStatus = statusService.find(Constants.statusDeleted);
            food.setStatus(deleteStatus);
            update(food);
            return true;
        }catch(Exception ex){
             
        }
        return false;
    }
}
