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
import hhn.qlqa027a.controller.StableController;
import hhn.qlqa027a.entities.Permission;
import hhn.qlqa027a.entities.Stable;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.util.Constants;
import hhn.qlqa027a.util.I18n;
import hhn.qlqa027a.util.ViewHelpers;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 * Employee form view
 *
 * @author Cem Ikta
 */
public class StableForm extends AbstractFormView<Stable> {

    private Stable stable = null;
    private boolean isNewModel = false;    

    // General page
    private JTextFieldExt tfxName;    
    private JTextFieldExt tfxNumberOfSlot; 

    /**
     * Gets new instance of employee form
     *
     * @param controller employee controller
     * @param stable model
     */
    public StableForm(DataPageController<Stable> controller, Stable stable) {
        super(AppController.get().getAppView(), controller);
        this.stable = stable;
        if (stable.getId() == null) {
            isNewModel = true;
        }
    }

    @Override
    public void buildUI() {
        initComponents();
        // add pages
        addPageToForm(I18n.CUSTOMERS.getString("Stable.Form.GeneralPage.Title"),
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
        tfxName = new JTextFieldExt(100);        
        tfxNumberOfSlot = new JTextFieldExt(100);  
        
        JPanel panel = new JPanel(new MigLayout("insets 20 10 10 10", "[][50:100,fill][fill,grow]", ""));              
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Stable.Form.Name")), "gap para");
        panel.add(tfxName, "span"); 
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Stable.Form.NumberOfSlot")), "gap para");
        panel.add(tfxNumberOfSlot, "span");
     
        return panel;
    }       
    

    @Override
    public Stable getEntity() {
        return stable;
    }

    @Override
    public void popFields() {
        // general page
        tfxName.setText(stable != null ? stable.getName() : "");
        tfxNumberOfSlot.setText(stable.getNumberSlot() != null ? stable.getNumberSlot().toString(): "");
        
    }
    public boolean containsName(final List<Permission> list, final String name){
        return list != null && list.stream().filter(o -> o.getName().equals(name)).findFirst().isPresent();
    }
    public Permission getFirst(final List<Permission> list, final String name){
        for (Permission p : list) {
            if (name.equals(p.getName())) {
              return p; 
            }
        }
        return null;
    }
    
    @Override
    public void pushFields() {
        stable.setName(tfxName.getText());        
        stable.setNumberSlot(Integer.parseInt(tfxNumberOfSlot.getText()));         
    }

    /**
     * Form validate
     *
     * @return true if no validate errors
     */
    @Override
    public boolean validateForm() {
        if (tfxName.getText().equals("")) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Stable.Form.Error.RequiredName"));
            tfxName.requestFocus();
            return false;
        }  
        
        if (tfxNumberOfSlot.getText().equals("")) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Stable.Form.Error.RequiredNumberOfSlot"));
            tfxNumberOfSlot.requestFocus();
            return false;
        }
        
        if(!validateNumberShort(tfxNumberOfSlot.getText())){
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Stable.Form.Error.NumberOfSlotNumberFormat"));
            tfxNumberOfSlot.requestFocus();
            return false;
        }
        
        Status activedStatus = ((StableController) controller).getStatusById(Constants.statusActived);
        if (activedStatus == null){
            MessageBox.showWarning("No actived status found!");
            return false;
        }
        stable.setStatus(activedStatus);  

        return true;
    }
    


    @Override
    public String getFormIconPath() {
        return ViewHelpers.ICONS16 + "table.png";
    }

    @Override
    public String getFormTitle() {
        if (isNewModel) {
            return I18n.CUSTOMERS.getString("Stable.Form.NewTitle");
        } else {
            return I18n.CUSTOMERS.getString("Stable.Form.EditTitle");
        }
    }       
}