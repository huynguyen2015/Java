/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.controller;

import hhn.qlqa027a.GUI.EmployeeScheduleForm;
import hhn.qlqa027a.GUI.EmployeeSchedulePage;
import hhn.qlqa027a.entities.Employee;
import hhn.qlqa027a.entities.Employeeschedule;
import hhn.qlqa027a.entities.Schedule;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.framework.AbstractDataPageController;
import hhn.qlqa027a.framework.DataPageView;
import hhn.qlqa027a.service.AbstractService;
import hhn.qlqa027a.service.EmployeeScheduleService;
import hhn.qlqa027a.service.EmployeeService;
import static hhn.qlqa027a.service.QueryParameter.with;
import hhn.qlqa027a.service.ScheduleService;
import hhn.qlqa027a.service.StatusService;
import java.util.List;

/**
 * Dashboard Controller.
 *
 * @author Cem Ikta
 */
public class EmployeeScheduleController extends AbstractDataPageController<Employeeschedule> {

    @Override
    protected AbstractService<Employeeschedule> createService() {
        return new EmployeeScheduleService();
    }

    @Override
    protected DataPageView<Employeeschedule> createDataPageView() {
        return new EmployeeSchedulePage();
    }

    @Override
    public void openFormView(Employeeschedule employeeschedule) {
        new EmployeeScheduleForm(this, employeeschedule).showDialog();
    }

    @Override
    public void onAddNew() {
        Employeeschedule employeeschedule = new Employeeschedule();        
        openFormView(employeeschedule);
    }

    @Override
    public List<Employeeschedule> getData(String filter, int start, int end) {
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
        return Employeeschedule.FIND_ALL;
    }

    @Override
    public String getNamedQueryWithFilter() {
        return Employeeschedule.FIND_BY_FILTER;
    }

    @Override
    public String getName() {
        return "EmployeeScheduleController";
    }
    
    /**
     * Huy Nguyen, 2016-05-29, Gets status from database.
     *
     * @return status list
     */
    public List<Status> getStatusList() {
        return new StatusService().getListWithNamedQuery(Status.FIND_ALL);
    } 
    
    /**
     * Huy Nguyen, 2016-05-29, Gets status from database.
     *
     * @return status list
     */
    public List<Employee> getEmployeeList() {
        return new EmployeeService().getListWithNamedQuery(Employee.FIND_ALL);
    }
    
    /**
     * Huy Nguyen, 2016-05-29, Gets list schedule from database.
     *
     * @return status list
     */
    public List<Schedule> getScheduleList() {
        return new ScheduleService().getListWithNamedQuery(Schedule.FIND_ALL);
    }
    
    /**
     * Huy Nguyen, 2016-05-29, Gets status by id from database.
     *
     * @param statusId
     * @return status list
     */
    public Status getStatusById(int statusId) {
        return new StatusService().find(statusId);
    }
}
