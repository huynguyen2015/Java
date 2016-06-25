/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.controller;

import hhn.qlqa027a.GUI.StableForm;
import hhn.qlqa027a.GUI.StablePage;
import hhn.qlqa027a.entities.Permission;
import hhn.qlqa027a.entities.Stable;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.framework.AbstractDataPageController;
import hhn.qlqa027a.framework.DataPageView;
import hhn.qlqa027a.service.AbstractService;
import hhn.qlqa027a.service.PermissionService;
import hhn.qlqa027a.service.StableService;
import static hhn.qlqa027a.service.QueryParameter.with;
import hhn.qlqa027a.service.StatusService;
import java.util.List;

/**
 * Dashboard Controller.
 *
 * @author Cem Ikta
 */
public class StableController extends AbstractDataPageController<Stable> {

    @Override
    protected AbstractService<Stable> createService() {
        return new StableService();
    }

    @Override
    protected DataPageView<Stable> createDataPageView() {
        return new StablePage();
    }

    @Override
    public void openFormView(Stable stable) {
         new StableForm(this, stable).showDialog();
    }

    @Override
    public void onAddNew() {
        openFormView(new Stable());
    }

    @Override
    public List<Stable> getData(String filter, int start, int end) {
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
        return Stable.FIND_ALL;
    }

    @Override
    public String getNamedQueryWithFilter() {
        return Stable.FIND_BY_FILTER;
    }

    @Override
    public String getName() {
        return "StableController";
    }

    public List<Permission> getPermissionList() {
        return new PermissionService().getListWithNamedQuery(Permission.FIND_ALL);
    }

    public Status getStatusById(int statusId) {
        return new StatusService().find(statusId);
    }
    
    public List<Status> getStatusList() {
        return new StatusService().getListWithNamedQuery(Status.FIND_ALL);
    }
}
