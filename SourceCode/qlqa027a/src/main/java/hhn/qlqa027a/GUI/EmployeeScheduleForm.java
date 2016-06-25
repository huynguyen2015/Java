/*
 * Employees Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.GUI;

import hhn.qlqa027a.controller.AppController;
import hhn.qlqa027a.framework.AbstractFormView;
import hhn.qlqa027a.framework.DataPageController;
import hhn.qlqa027a.component.JTextFieldExt;
import hhn.qlqa027a.component.MessageBox;
import hhn.qlqa027a.controller.EmployeeScheduleController;
import hhn.qlqa027a.entities.Employee;
import hhn.qlqa027a.entities.Employeeschedule;
import hhn.qlqa027a.entities.Schedule;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.util.Constants;
import hhn.qlqa027a.util.I18n;
import hhn.qlqa027a.util.ViewHelpers;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 * Employee form view
 *
 * @author Cem Ikta
 */
public class EmployeeScheduleForm extends AbstractFormView<Employeeschedule> {

    private Employeeschedule employeeschedule = null;
    private boolean isNewModel = false;  
    
    private List<Employee> employees;
    private JComboBox cbEmployee;

    private List<Schedule> schedules;
    private JComboBox cbSchedule;
    
    private List<Status> statuss;
    private JComboBox cbStatus;
    
    // General page
    private JCheckBox ckbIsWorked;    
    private JTextFieldExt tfxMinutesLate;

    /**
     * Gets new instance of employee form
     *
     * @param controller employee controller
     * @param employeeschedule model
     */
    public EmployeeScheduleForm(DataPageController<Employeeschedule> controller, Employeeschedule employeeschedule) {
        super(AppController.get().getAppView(), controller);
        this.employeeschedule = employeeschedule;
        if (employeeschedule.getId() == null) {
            isNewModel = true;
        }
    }

    @Override
    public void buildUI() {
        initComponents();
        // add pages
        addPageToForm(I18n.CUSTOMERS.getString("Employeeschedule.Form.GeneralPage.Title"),
                buildGeneralPage());
        
        popFields();
        pack(); 
        setSize(550, 530);
    }

    /**
     * General page UI
     *
     * @return general page panel
     */
    private JPanel buildGeneralPage() {
        ckbIsWorked = new JCheckBox();        
        tfxMinutesLate = new JTextFieldExt(100);          
        employees = ((EmployeeScheduleController) controller).getEmployeeList();
        cbEmployee = new JComboBox(employees.toArray());
        schedules = ((EmployeeScheduleController) controller).getScheduleList();
        cbSchedule = new JComboBox(schedules.toArray());
        statuss = ((EmployeeScheduleController) controller).getStatusList();
        cbStatus = new JComboBox(statuss.toArray());
        
        JPanel panel = new JPanel(new MigLayout("insets 20 10 10 10", "[][50:100,fill][fill,grow]", ""));               
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Employeeschedule.Form.IsWorked")), "gap para");
        panel.add(ckbIsWorked, "span"); 
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Employeeschedule.Form.MinutesLate")), "gap para");
        panel.add(tfxMinutesLate, "span");
     
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Employeeschedule.Form.Employee")), "gap para");
        panel.add(cbEmployee, "span"); 
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Employeeschedule.Form.Schedule")), "gap para");
        panel.add(cbSchedule, "span"); 
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Employeeschedule.Form.Status")), "gap para");
        panel.add(cbStatus, "span"); 
        
        return panel;
    }       
    

    @Override
    public Employeeschedule getEntity() {
        return employeeschedule;
    }

    @Override
    public void popFields() {
        // general page
        ckbIsWorked.setSelected(employeeschedule.getIsWorked() == null ? false : employeeschedule.getIsWorked());
        tfxMinutesLate.setText(employeeschedule.getMinutesLate() != null ? employeeschedule.getMinutesLate().toString() : "");
        if (employeeschedule.getEmployee()!= null) {
            cbEmployee.setSelectedItem(employeeschedule.getEmployee());
        }
        if (employeeschedule.getSchedule()!= null) {
            cbSchedule.setSelectedItem(employeeschedule.getSchedule());
        }
        if (employeeschedule.getStatus()!= null) {
            cbStatus.setSelectedItem(employeeschedule.getStatus());
        }
    }
    
    @Override
    public void pushFields() {
        employeeschedule.setIsWorked(ckbIsWorked.isSelected());    
        if(!"".equals(tfxMinutesLate.getText())){      
            employeeschedule.setMinutesLate(Short.parseShort(tfxMinutesLate.getText())); 
        }
        employeeschedule.setEmployee(Employee.class.cast(cbEmployee.getSelectedItem()));
        employeeschedule.setSchedule(Schedule.class.cast(cbSchedule.getSelectedItem()));
        employeeschedule.setStatus(Status.class.cast(cbStatus.getSelectedItem()));
    }

    /**
     * Form validate
     *
     * @return true if no validate errors
     */
    @Override
    public boolean validateForm() {   
        if(!"".equals(tfxMinutesLate.getText())){       
            try{
                short minutes = Short.parseShort(tfxMinutesLate.getText());
            }catch(Exception ex){
                MessageBox.showWarning(I18n.CUSTOMERS.getString("Employee.Form.Error.WrongNumberFormat"));
                tfxMinutesLate.requestFocus();
                return false;
            }
        }
        
        if(cbEmployee.getSelectedItem() == null){
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Employee.Form.Error.RequiredEmployee"));
            cbEmployee.requestFocus();
            return false;
        }
        if(cbSchedule.getSelectedItem() == null){
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Employee.Form.Error.RequiredSchedule"));
            cbSchedule.requestFocus();
            return false;
        }  
        if(cbStatus.getSelectedItem() == null){
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Employee.Form.Error.RequiredStatus"));
            cbStatus.requestFocus();
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
            return I18n.CUSTOMERS.getString("Employeeschedule.Form.NewTitle");
        } else {
            return I18n.CUSTOMERS.getString("Employeeschedule.Form.EditTitle");
        }
    }       
}