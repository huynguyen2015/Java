/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhn.qlqa027a.GUI;
import hhn.qlqa027a.entities.Employee;
import hhn.qlqa027a.entities.Shift;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.entities.Schedule;
import hhn.qlqa027a.framework.AbstractDataPageView;
import hhn.qlqa027a.framework.EntityTableColumn;
import hhn.qlqa027a.util.I18n;
import hhn.qlqa027a.util.ViewHelpers;
import java.awt.BorderLayout;
import java.util.Date;
/**
 *
 * @author Administrator
 */
public class SchedulePage extends AbstractDataPageView<Schedule>{
    
    public SchedulePage() {
        initComponents();        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>   
    
    @Override
    public void addTableColumns() {
        getTableModel().addColumn(new EntityTableColumn(
                I18n.CUSTOMERS.getString("Schedule.Page.WorkingDate"), "workingDate", Date.class, 300));              
        
        getTableModel().addColumn(new EntityTableColumn(
                I18n.CUSTOMERS.getString("Schedule.Page.Shift"), "shift", Shift.class, 200));
        
        getTableModel().addColumn(new EntityTableColumn(
                I18n.CUSTOMERS.getString("Schedule.Page.Status"), "status", Status.class, 200));
    }
    
    @Override
    public String getIconPath() {
        return ViewHelpers.ICONS16 + "schedule.png";
    }
   
    @Override
    public String getTitle() {
        return I18n.CUSTOMERS.getString("Schedule.Page.Title");
    }

    private void setLayout(BorderLayout borderLayout) {
        
    }

    // Variables declaration - do not modify                     
    private com.toedter.calendar.JDateChooser jDateChooser1;
    // End of variables declaration      
}
