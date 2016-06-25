/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhn.qlqa027a.service;

import hhn.qlqa027a.entities.Category;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.util.Constants;

/**
 *
 * @author Administrator
 */
public class CategoryService extends AbstractService<Category>{
    
    private final StatusService statusService;
    
    public CategoryService() {
        super(Category.class);
        statusService = new StatusService();
    }
    
    @Override
    public boolean remove(Category category){
        try{
            Status deleteStatus = statusService.find(Constants.statusDeleted);
            category.setStatus(deleteStatus);
            update(category);
            return true;
        }catch(Exception ex){
             
        }
        return false;
    }
}
