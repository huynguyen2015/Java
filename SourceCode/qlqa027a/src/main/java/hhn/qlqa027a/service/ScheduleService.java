/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhn.qlqa027a.service;
import hhn.qlqa027a.entities.Schedule;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.util.Constants;
/**
 *
 * @author Administrator
 */
public class ScheduleService extends AbstractService<Schedule>{
    
    private final StatusService statusService;
    
    public ScheduleService() {
        super(Schedule.class);
        statusService = new StatusService();
    }
    
    @Override
    public boolean remove(Schedule schedule){
        try{
            Status deleteStatus = statusService.find(Constants.statusDeleted);
            schedule.setStatus(deleteStatus);
            update(schedule);
            return true;
        }catch(Exception ex){
             
        }
        return false;
    }
}
