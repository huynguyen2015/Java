/*
 * Employees Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.GUI;

import hhn.qlqa027a.component.JTextFieldExt;
import hhn.qlqa027a.component.MessageBox;
import hhn.qlqa027a.controller.AppController;
import hhn.qlqa027a.controller.ShiftController;
import hhn.qlqa027a.entities.Certificate;
import hhn.qlqa027a.framework.AbstractFormView;
import hhn.qlqa027a.framework.DataPageController;
import hhn.qlqa027a.entities.Shift;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.util.I18n;
import hhn.qlqa027a.util.ViewHelpers;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 * Employee form view
 *
 * @author Cem Ikta
 */
public class ShiftForm extends AbstractFormView<Shift> {

    private Shift shift = null;
    private boolean isNewModel = false;
// General page
    private JTextFieldExt tfxName;
    private JTextFieldExt tfxSalary;    
    private JTextFieldExt tfxMinServicers;
    private JTextFieldExt tfxMaxServicers;
    private JTextFieldExt tfxMinBartenders;
    private JTextFieldExt tfxMaxBartenders;    
    private JTextFieldExt tfxMinManagers;
    private JTextFieldExt tfxMaxManagers; 
    private JComboBox cbStatus;

    /**
     * Gets new instance of employee form
     *
     * @param controller employee controller
     * @param shift model
     */
    public ShiftForm(DataPageController<Shift> controller, Shift shift) {
        super(AppController.get().getAppView(), controller);
        this.shift = shift;
        if (shift.getId() == null) {
            isNewModel = true;
        }
    }

    @Override
    public void buildUI() {
        initComponents();
        // add pages
        addPageToForm(I18n.CUSTOMERS.getString("Shift.Form.GeneralPage.Title"),
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
     
        tfxName = new JTextFieldExt(50); 
        tfxSalary = new JTextFieldExt(50);    
        tfxMinServicers = new JTextFieldExt(50);
        tfxMaxServicers = new JTextFieldExt(50);
        tfxMinBartenders = new JTextFieldExt(50);
        tfxMaxBartenders = new JTextFieldExt(50);    
        tfxMinManagers = new JTextFieldExt(50);
        tfxMaxManagers = new JTextFieldExt(50); 
        cbStatus = new JComboBox(((ShiftController) controller).getStatusList().toArray());
        JPanel panel = new JPanel(new MigLayout("insets 20 10 10 10", "[][50:100,fill][fill,grow]", ""));
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Shift.Form.Name.Title")), "gap para");
        panel.add(tfxName, "span");
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Shift.Form.Salary.Title")), "gap para");
        panel.add(tfxSalary, "span");
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Shift.Form.MinServicers.Title")), "gap para");
        panel.add(tfxMinServicers, "span");
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Shift.Form.MaxServicers.Title")), "gap para");
        panel.add(tfxMaxServicers, "span");
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Shift.Form.MinBartenders.Title")), "gap para");
        panel.add(tfxMinBartenders, "span");
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Shift.Form.MaxBartenders.Title")), "gap para");
        panel.add(tfxMaxBartenders, "span");
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Shift.Form.MinManagers.Title")), "gap para");
        panel.add(tfxMinManagers, "span");
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Shift.Form.MaxManagers.Title")), "gap para");
        panel.add(tfxMaxManagers, "span");
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Employee.Form.Status")), "gap para");
        panel.add(cbStatus, "span");
        return panel;
    }       
    

    @Override
    public Shift getEntity() {
        return shift;
    }

    @Override
    public void popFields() {
        // general page
        if(shift == null)
            return;
        tfxName.setText(shift.getName() != null ? shift.getName(): "");
        tfxSalary.setText(shift.getSalary() != null ? shift.getSalary().toString() : "");
        tfxMinServicers.setText(shift.getMinServicers() != null ? shift.getMinServicers().toString() : "");
        tfxMaxServicers.setText(shift.getMaxServicers() != null ? shift.getMaxServicers().toString() : "");
        tfxMinBartenders.setText(shift.getMinBartenders() != null ? shift.getMinBartenders().toString() : "");
        tfxMaxBartenders.setText(shift.getMaxBartenders() != null ? shift.getMaxBartenders().toString() : "");
        tfxMinManagers.setText(shift.getMinManagers() != null ? shift.getMinManagers().toString() : "");
        tfxMaxManagers.setText(shift.getMaxManagers() != null ? shift.getMaxManagers().toString() : "");
        if (shift.getStatus()!= null) {
            cbStatus.setSelectedItem(shift.getStatus());
        }
    }
    public boolean containsName(final List<Certificate> list, final String name){
        return list != null && list.stream().filter(o -> o.getName().equals(name)).findFirst().isPresent();
    }
    @Override
    public void pushFields() {
        shift.setName(tfxName.getText());
        shift.setSalary(Long.parseLong(tfxSalary.getText()));
        shift.setMinServicers(Short.parseShort(tfxMinServicers.getText()));
        shift.setMaxServicers(Short.parseShort(tfxMaxServicers.getText()));
        shift.setMinBartenders(Short.parseShort(tfxMinBartenders.getText()));
        shift.setMaxBartenders(Short.parseShort(tfxMaxBartenders.getText()));
        shift.setMinManagers(Short.parseShort(tfxMinManagers.getText()));
        shift.setMaxManagers(Short.parseShort(tfxMaxManagers.getText()));
        shift.setStatus(Status.class.cast(cbStatus.getSelectedItem()));
    }

    /**
     * Form validate
     *
     * @return true if no validate errors
     */
    @Override
    public boolean validateForm() {
       if (tfxName.getText().equals("")) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Shift.Form.Error.RequiredData"));
            tfxName.requestFocus();
            return false;
        }

        if (tfxSalary.getText().equals("")) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Shift.Form.Error.RequiredData"));
            tfxSalary.requestFocus();
            return false;
        }
        
        if(validateNumberInteger(tfxSalary.getText())){
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Shift.Form.Error.WrongFormat"));
            tfxSalary.requestFocus();
            return false;
        }
        
        if (Long.valueOf(tfxSalary.getText()).longValue() <= 0) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Shift.Form.Error.RequiredData"));
            tfxSalary.requestFocus();
            return false;
        }                

        if (tfxMinServicers.getText().equals("") || Long.valueOf(tfxMinServicers.getText()).longValue() <= 0) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Shift.Form.Error.RequiredData"));
            tfxMinServicers.requestFocus();
            return false;
        }
        
        if(validateNumberShort(tfxMinServicers.getText())){
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Shift.Form.Error.WrongFormat"));
            tfxMinServicers.requestFocus();
            return false;
        }
        
        if (tfxMaxServicers.getText().equals("") || Long.valueOf(tfxMaxServicers.getText()).longValue() <= 0) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Shift.Form.Error.RequiredData"));
            tfxMaxServicers.requestFocus();
            return false;
        }
        
        if(validateNumberShort(tfxMaxServicers.getText())){
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Shift.Form.Error.WrongFormat"));
            tfxMaxServicers.requestFocus();
            return false;
        }
        
        if (tfxMinBartenders.getText().equals("") || Long.valueOf(tfxMinBartenders.getText()).longValue() <= 0) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Shift.Form.Error.RequiredData"));
            tfxMinBartenders.requestFocus();
            return false;
        }
         if(validateNumberShort(tfxMinBartenders.getText())){
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Shift.Form.Error.WrongFormat"));
            tfxMinBartenders.requestFocus();
            return false;
        }
        if (tfxMaxBartenders.getText().equals("") || Long.valueOf(tfxMaxBartenders.getText()).longValue() <= 0) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Shift.Form.Error.RequiredData"));
            tfxMaxBartenders.requestFocus();
            return false;
        }
        if(validateNumberShort(tfxMaxBartenders.getText())){
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Shift.Form.Error.WrongFormat"));
            tfxMaxBartenders.requestFocus();
            return false;
        }
        if (tfxMinManagers.getText().equals("") || Long.valueOf(tfxMinManagers.getText()).longValue() <= 0) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Shift.Form.Error.RequiredData"));
            tfxMinManagers.requestFocus();
            return false;
        }
         if(validateNumberShort(tfxMinManagers.getText())){
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Shift.Form.Error.WrongFormat"));
            tfxMinManagers.requestFocus();
            return false;
        }
        
        if (tfxMaxManagers.getText().equals("") || Long.valueOf(tfxMaxManagers.getText()).longValue() <= 0) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Shift.Form.Error.RequiredData"));
            tfxMaxManagers.requestFocus();
            return false;
        }
         if(validateNumberShort(tfxMaxManagers.getText())){
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Shift.Form.Error.WrongFormat"));
            tfxMaxManagers.requestFocus();
            return false;
        }
        
        if (!isNewModel && cbStatus.getSelectedItem().equals("")) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Shift.Form.Error.RequiredData"));
            cbStatus.requestFocus();
            return false;
        }
        
        return true;
    }

    @Override
    public String getFormIconPath() {
        return ViewHelpers.ICONS16 + "customer.png";
    }

    @Override
    public String getFormTitle() {
        if (isNewModel) {
            return I18n.CUSTOMERS.getString("Shift.Form.NewTitle");
        } else {
            return I18n.CUSTOMERS.getString("Shift.Form.EditTitle");
        }
    }
    
    @Override
    public boolean isMultiPageForm() {
        return true;
    }  
}