/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhn.qlqa027a.controller;

import hhn.qlqa027a.GUI.MenuForm;
import hhn.qlqa027a.GUI.MenuPage;
import hhn.qlqa027a.entities.Menu;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.framework.AbstractDataPageController;
import hhn.qlqa027a.framework.DataPageView;
import hhn.qlqa027a.service.AbstractService;
import hhn.qlqa027a.service.MenuService;
import static hhn.qlqa027a.service.QueryParameter.with;
import hhn.qlqa027a.service.StatusService;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class MenuController extends AbstractDataPageController<Menu> {
    
    @Override
    protected AbstractService<Menu> createService() {
        return new MenuService();
    }

    @Override
    protected DataPageView<Menu> createDataPageView() {
        return new MenuPage();
    }

    @Override
    public void openFormView(Menu menu) {
        new MenuForm(this, menu).showDialog();
    }

    @Override
    public void onAddNew() {
        Menu menu = new Menu();        
        openFormView(menu);
    }

    @Override
    public List<Menu> getData(String filter, int start, int end) {
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
        return Menu.FIND_ALL;
    }

    @Override
    public String getNamedQueryWithFilter() {
        return Menu.FIND_BY_FILTER;
    }

    @Override
    public String getName() {
        return "MenuController";
    }
    
    public Status getStatusById(int statusId) {
        return new StatusService().find(statusId);
    }
}
