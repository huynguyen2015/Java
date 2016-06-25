/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhn.qlqa027a.controller;

import hhn.qlqa027a.GUI.MenufoodForm;
import hhn.qlqa027a.GUI.MenufoodPage;
import hhn.qlqa027a.entities.Food;
import hhn.qlqa027a.entities.Menu;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.entities.Menufood;
import hhn.qlqa027a.framework.AbstractDataPageController;
import hhn.qlqa027a.framework.DataPageView;
import hhn.qlqa027a.service.AbstractService;
import hhn.qlqa027a.service.FoodService;
import hhn.qlqa027a.service.MenuFoodService;
import hhn.qlqa027a.service.MenuService;
import static hhn.qlqa027a.service.QueryParameter.with;
import hhn.qlqa027a.service.StatusService;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class MenufoodController extends AbstractDataPageController<Menufood>{
    
    @Override
    protected AbstractService<Menufood> createService() {
        return new MenuFoodService();
    }

    @Override
    protected DataPageView<Menufood> createDataPageView() {
        return new MenufoodPage();
    }

    @Override
    public void openFormView(Menufood menufood) {
        new MenufoodForm(this, menufood).showDialog();
    }

    @Override
    public void onAddNew() {
        Menufood menufood = new Menufood();        
        openFormView(menufood);
    }

    @Override
    public List<Menufood> getData(String filter, int start, int end) {
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
                     with("menu", "%" + filter + "%")
                    .parameters()).size();
        }
    }

    @Override
    public String getNamedQuery() {
        return Menufood.FIND_ALL;
    }

    @Override
    public String getNamedQueryWithFilter() {
        return Menufood.FIND_BY_FILTER;
    }

    @Override
    public String getName() {
        return "MenufoodController";
    }
    
    public List<Status> getStatusList() {
        return new StatusService().getListWithNamedQuery(Status.FIND_ALL);
    }
    
    public List<Menu> getMenuList() {
        return new MenuService().getListWithNamedQuery(Menu.FIND_ALL);
    }
    
    public List<Food> getFoodList() {
        return new FoodService().getListWithNamedQuery(Food.FIND_ALL);
    }
    
    public Status getStatusById(int statusId) {
        return new StatusService().find(statusId);
    }
}
