/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.controller;

import hhn.qlqa027a.GUI.PermissionForm;
import hhn.qlqa027a.GUI.PermissionPage;
import hhn.qlqa027a.component.MessageBox;
import hhn.qlqa027a.entities.Permission;
import hhn.qlqa027a.framework.AbstractDataPageController;
import hhn.qlqa027a.framework.DataPageView;
import hhn.qlqa027a.service.AbstractService;
import hhn.qlqa027a.service.PermissionService;
import static hhn.qlqa027a.service.QueryParameter.with;
import hhn.qlqa027a.util.I18n;
import java.util.List;

/**
 * Dashboard Controller.
 *
 * @author Cem Ikta
 */
public class PermissionController extends AbstractDataPageController<Permission> {
    
    @Override
    protected AbstractService<Permission> createService() {
        return new PermissionService();
    }

    @Override
    protected DataPageView<Permission> createDataPageView() {
        return new PermissionPage();
    }

    @Override
    public void openFormView(Permission permission) {
        new PermissionForm(this, permission).showDialog();
    }

    @Override
    public void onAddNew() {
        Permission permission = new Permission();        
        openFormView(permission);
    }       

    @Override
    public List<Permission> getData(String filter, int start, int end) {
        if (filter.equals("")) {
            return getService().getListWithNamedQuery(getNamedQuery(), start, end);
        } else {
            
            return getService().getListWithNamedQuery(getNamedQueryWithFilter(),
                    with("name", "%" + filter + "%").parameters(), start, end);
        }
    }

    @Override
    public int getDataSize(String filter) {
        if (filter.equals("")) {
            return getService().getListWithNamedQuery(getNamedQuery()).size();
        } else {
            return getService().getListWithNamedQuery(getNamedQueryWithFilter(),
                     with("name", "%" + filter + "%").parameters()).size();
        }
    }

    @Override
    public String getNamedQuery() {
        return Permission.FIND_ALL;
    }

    @Override
    public String getNamedQueryWithFilter() {
        return Permission.FIND_BY_FILTER;
    }

    @Override
    public String getName() {
        return "PermissionController";
    }
    
    public Permission getPermissionByName(String name) {
        return ((PermissionService)getService()).findByName(name);
    }
}
