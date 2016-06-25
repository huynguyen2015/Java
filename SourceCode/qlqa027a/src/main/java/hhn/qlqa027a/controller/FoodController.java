/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhn.qlqa027a.controller;

import hhn.qlqa027a.GUI.FoodForm;
import hhn.qlqa027a.GUI.FoodPage;
import hhn.qlqa027a.entities.Category;
import hhn.qlqa027a.entities.Food;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.framework.AbstractDataPageController;
import hhn.qlqa027a.framework.DataPageView;
import hhn.qlqa027a.service.AbstractService;
import hhn.qlqa027a.service.CategoryService;
import hhn.qlqa027a.service.FoodService;
import static hhn.qlqa027a.service.QueryParameter.with;
import hhn.qlqa027a.service.StatusService;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class FoodController extends AbstractDataPageController<Food>{
 
    @Override
    protected AbstractService<Food> createService() {
        return new FoodService();
    }

    @Override
    protected DataPageView<Food> createDataPageView() {
        return new FoodPage();
    }

    @Override
    public void openFormView(Food food) {
        new FoodForm(this, food).showDialog();
    }

    @Override
    public void onAddNew() {
        Food food = new Food();        
        openFormView(food);
    }

    @Override
    public List<Food> getData(String filter, int start, int end) {
        if (filter.equals("")) {
            return getService().getListWithNamedQuery(getNamedQuery(), start, end);
        } else {
            
            return getService().getListWithNamedQuery(getNamedQueryWithFilter(),
                    with("name", "%" + filter + "%")
                    .parameters(), start, end);
        }
    }

    @Override
    public int getDataSize(String filter) {
        if (filter.equals("")) {
            return getService().getListWithNamedQuery(getNamedQuery()).size();
        } else {
            return getService().getListWithNamedQuery(getNamedQueryWithFilter(),
                     with("name", "%" + filter + "%")
                    .parameters()).size();
        }
    }

    @Override
    public String getNamedQuery() {
        return Food.FIND_ALL;
    }

    @Override
    public String getNamedQueryWithFilter() {
        return Food.FIND_BY_FILTER;
    }

    @Override
    public String getName() {
        return "FoodController";
    }
    
    public List<Status> getStatusList() {
        return new StatusService().getListWithNamedQuery(Status.FIND_ALL);
    }
    
    public List<Category> getCategoryList() {
        return new CategoryService().getListWithNamedQuery(Category.FIND_ALL);
    }
    
    public Status getStatusById(int statusId) {
        return new StatusService().find(statusId);
    }
}
