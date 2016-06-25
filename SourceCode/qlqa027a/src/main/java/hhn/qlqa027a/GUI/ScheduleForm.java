/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhn.qlqa027a.GUI;
import com.toedter.calendar.JDateChooser;
import hhn.qlqa027a.component.MessageBox;
import hhn.qlqa027a.controller.AppController;
import hhn.qlqa027a.controller.ScheduleController;
import hhn.qlqa027a.framework.AbstractFormView;
import hhn.qlqa027a.framework.DataPageController;
import hhn.qlqa027a.entities.Schedule;
import hhn.qlqa027a.entities.Shift;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.util.I18n;
import hhn.qlqa027a.util.ViewHelpers;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
/**
 *
 * @author Administrator
 */
public class ScheduleForm extends AbstractFormView<Schedule>{
    
    private Schedule schedule = null;
    private boolean isNewModel = false;
    
    private JDateChooser jdcWorkingDate;
    private JComboBox cbShift;
    private JComboBox cbStatus; 
    
    public ScheduleForm(DataPageController<Schedule> controller, Schedule schedule) {
        super(AppController.get().getAppView(), controller);
        this.schedule = schedule;
        if (schedule.getId() == null) {
            isNewModel = true;
        }
    }

    @Override
    public void buildUI() {
        initComponents();
        // add pages
        addPageToForm(I18n.CUSTOMERS.getString("Schedule.Form.GeneralPage.Title"),
                buildGeneralPage());
        
        popFields();
        pack(); 
        setSize(550, 530);
    }
    
    private JPanel buildGeneralPage() {
        jdcWorkingDate = new JDateChooser();
        cbShift = new JComboBox(((ScheduleController) controller).getShiftList().toArray());
        cbStatus = new JComboBox(((ScheduleController) controller).getStatusList().toArray());
        
        JPanel panel = new JPanel(new MigLayout("insets 20 10 10 10", "[][50:100,fill][fill,grow]", ""));
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Schedule.Page.WorkingDate")), "gap para");
        panel.add(jdcWorkingDate, "span");
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Schedule.Page.Status")), "gap para");
        panel.add(cbStatus, "span");
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Schedule.Page.Shift")), "gap para");
        panel.add(cbShift, "span");    
        
        return panel;
    }
    
    @Override
    public Schedule getEntity() {
        return schedule;
    }

    @Override
    public void popFields() {
        // general page
        jdcWorkingDate.setDate(schedule.getWorkingDate());
        if (schedule.getShift()!= null) {
            cbShift.setSelectedItem(schedule.getShift());
        }
        if (schedule.getStatus()!= null) {
            cbStatus.setSelectedItem(schedule.getStatus());
        }      
    } 
    
    @Override
    public void pushFields() {
        schedule.setWorkingDate(jdcWorkingDate.getDate());
        schedule.setShift(Shift.class.cast(cbShift.getSelectedItem()));
        schedule.setStatus(Status.class.cast(cbStatus.getSelectedItem()));        
    }

    /**
     * Form validate
     *
     * @return true if no validate errors
     */
    @Override
    public boolean validateForm() {
         if (jdcWorkingDate.getDate().toString().equals("")){
             MessageBox.showWarning(I18n.CUSTOMERS.getString("Schedule.Form.Error.RequiredWorkingDate"));
             jdcWorkingDate.requestFocus();
             return false;
         }    
        return true;
    }

    @Override
    public String getFormIconPath() {
        return ViewHelpers.ICONS16 + "schedule.png";
    }

    @Override
    public String getFormTitle() {
        if (isNewModel) {
            return I18n.CUSTOMERS.getString("Schedule.Form.NewTitle");
        } else {
            return I18n.CUSTOMERS.getString("Schedule.Form.EditTitle");
        }
    }
    
    @Override
    public boolean isMultiPageForm() {
        return true;
    }  
}
