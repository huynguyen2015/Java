/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.controller;

import hhn.qlqa027a.GUI.ShiftForm;
import hhn.qlqa027a.GUI.ShiftPage;
import hhn.qlqa027a.entities.Shift;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.framework.AbstractDataPageController;
import hhn.qlqa027a.framework.DataPageView;
import hhn.qlqa027a.service.AbstractService;
import static hhn.qlqa027a.service.QueryParameter.with;
import hhn.qlqa027a.service.ShiftService;
import hhn.qlqa027a.service.StatusService;
import java.util.List;

/**
 * Dashboard Controller.
 *
 * @author Cem Ikta
 */
public class ShiftController extends AbstractDataPageController<Shift> { 

    @Override
    protected AbstractService<Shift> createService() {
        return new ShiftService();
    }

    @Override
    protected DataPageView<Shift> createDataPageView() {
        return new ShiftPage();
    }

    @Override
    public void openFormView(Shift shift) {
        new ShiftForm(this, shift).showDialog();
    }

    @Override
    public void onAddNew() {
        Shift shift = new Shift();        
        openFormView(shift);
    }

    @Override
    public List<Shift> getData(String filter, int start, int end) {
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
        return Shift.FIND_ALL;
    }
    
    
    @Override
    public String getNamedQueryWithFilter() {
        return Shift.FIND_BY_FILTER;
    }

    @Override
    public String getName() {
        return "ShiftController";
    }
    public List<Status> getStatusList() {
        return new StatusService().getListWithNamedQuery(Status.FIND_ALL);
    }
    
}
