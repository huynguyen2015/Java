/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.controller;

import hhn.qlqa027a.GUI.RoleForm;
import hhn.qlqa027a.GUI.RolePage;
import hhn.qlqa027a.entities.Permission;
import hhn.qlqa027a.entities.Role;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.framework.AbstractDataPageController;
import hhn.qlqa027a.framework.DataPageView;
import hhn.qlqa027a.service.AbstractService;
import hhn.qlqa027a.service.PermissionService;
import hhn.qlqa027a.service.RoleService;
import static hhn.qlqa027a.service.QueryParameter.with;
import hhn.qlqa027a.service.StatusService;
import java.util.List;

/**
 * Dashboard Controller.
 *
 * @author Cem Ikta
 */
public class RoleController extends AbstractDataPageController<Role> {

    @Override
    protected AbstractService<Role> createService() {
        return new RoleService();
    }

    @Override
    protected DataPageView<Role> createDataPageView() {
        return new RolePage();
    }

    @Override
    public void openFormView(Role role) {
         new RoleForm(this, role).showDialog();
    }

    @Override
    public void onAddNew() {
        openFormView(new Role());
    }

    @Override
    public List<Role> getData(String filter, int start, int end) {
        if (filter.equals("")) {
            return getService().getListWithNamedQuery(getNamedQuery(), start, end);
        } else {
            return getService().getListWithNamedQuery(getNamedQueryWithFilter(),
                    with("filter", "%" + filter + "%").parameters(), start, end);
        }
    }

    @Override
    public int getDataSize(String filter) {
        if (filter.equals("")) {
            return getService().getListWithNamedQuery(getNamedQuery()).size();
        } else {
            return getService().getListWithNamedQuery(getNamedQueryWithFilter(),
                    with("filter", "%" + filter + "%").parameters()).size();
        }
    }

    @Override
    public String getNamedQuery() {
        return Role.FIND_ALL;
    }

    @Override
    public String getNamedQueryWithFilter() {
        return Role.FIND_BY_FILTER;
    }

    @Override
    public String getName() {
        return "RoleController";
    }

    public List<Permission> getPermissionList() {
        return new PermissionService().getListWithNamedQuery(Permission.FIND_ALL);
    }

    public Status getStatusById(int statusId) {
        return new StatusService().find(statusId);
    }
}
