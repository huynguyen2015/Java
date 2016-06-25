/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhn.qlqa027a.controller;
import hhn.qlqa027a.GUI.ScheduleForm;
import hhn.qlqa027a.GUI.SchedulePage;
import hhn.qlqa027a.entities.Schedule;
import hhn.qlqa027a.entities.Shift;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.framework.AbstractDataPageController;
import hhn.qlqa027a.framework.DataPageView;
import hhn.qlqa027a.service.AbstractService;
import static hhn.qlqa027a.service.QueryParameter.with;
import hhn.qlqa027a.service.ScheduleService;
import hhn.qlqa027a.service.StatusService;
import hhn.qlqa027a.service.ShiftService;
import java.util.List;
/**
 *
 * @author Hoa The
 */
public class ScheduleController extends AbstractDataPageController<Schedule> {
    
    @Override
    protected AbstractService<Schedule> createService() {
        return new ScheduleService();
    }

    @Override
    protected DataPageView<Schedule> createDataPageView() {
        return new SchedulePage();
    }

    @Override
    public void openFormView(Schedule schedule) {
        new ScheduleForm(this, schedule).showDialog();
    }

    @Override
    public void onAddNew() {
        Schedule schedule = new Schedule();        
        openFormView(schedule);
    }

    @Override
    public List<Schedule> getData(String filter, int start, int end) {
        if (filter.equals("")) {
            return getService().getListWithNamedQuery(getNamedQuery(), start, end);
        } else {
            return getService().getListWithNamedQuery(getNamedQueryWithFilter(),
                    with("workingDate", "'" + filter + "%'").parameters(), start, end);
        }
    }

    @Override
    public int getDataSize(String filter) {
        if (filter.equals("")) {
            return getService().getListWithNamedQuery(getNamedQuery()).size();
        } else {
            return getService().getListWithNamedQuery(getNamedQueryWithFilter(),
                    with("workingDate", "'" + filter + "%'").parameters()).size();
        }
    }

    @Override
    public String getNamedQuery() {
        return Schedule.FIND_ALL;
    }

    @Override
    public String getNamedQueryWithFilter() {
        return Schedule.FIND_BY_FILTER;
    }

    @Override
    public String getName() {
        return "ScheduleController";
    }
    
    public List<Status> getStatusList() {
        return new StatusService().getListWithNamedQuery(Status.FIND_ALL);
    }
    
    public List<Shift> getShiftList() {
        return new ShiftService().getListWithNamedQuery(Shift.FIND_ALL);
    }
}
