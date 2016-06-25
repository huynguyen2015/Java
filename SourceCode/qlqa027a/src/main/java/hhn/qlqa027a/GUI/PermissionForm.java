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
import hhn.qlqa027a.controller.PermissionController;
import hhn.qlqa027a.entities.Permission;
import hhn.qlqa027a.util.I18n;
import hhn.qlqa027a.util.ViewHelpers;
import java.util.Objects;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 * Employee form view
 *
 * @author Cem Ikta
 */
public class PermissionForm extends AbstractFormView<Permission> {

    private Permission permission = null;
    private boolean isNewModel = false;

    // General page
    private JTextFieldExt tfxName;    

    /**
     * Gets new instance of employee form
     *
     * @param controller employee controller
     * @param permission model
     */
    public PermissionForm(DataPageController<Permission> controller, Permission permission) {
        super(AppController.get().getAppView(), controller);
        this.permission = permission;
        if (permission.getId() == null) {
            isNewModel = true;
        }
    }

    @Override
    public void buildUI() {
        initComponents();
        // add pages
        addPageToForm(I18n.CUSTOMERS.getString("Permission.Form.GeneralPage.Title"),
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
       
        
        JPanel panel = new JPanel(new MigLayout("insets 20 10 10 10", "[][50:100,fill][fill,grow]", ""));
       
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Permission.Form.Name")), "gap para");
        panel.add(tfxName, "span"); 
        
     
        return panel;
    }       
    

    @Override
    public Permission getEntity() {
        return permission;
    }

    @Override
    public void popFields() {
        // general page
        tfxName.setText(permission != null ? permission.getName() : "");
    }
    
    @Override
    public void pushFields() {
        permission.setName(tfxName.getText());        
    }

    /**
     * Form validate
     *
     * @return true if no validate errors
     */
    @Override
    public boolean validateForm() {
        if (tfxName.getText().equals("")) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Permission.Form.Error.RequiredName"));
            tfxName.requestFocus();
            return false;
        }       
        Permission temp = ((PermissionController) controller).getPermissionByName(tfxName.getText());
        if(null != temp && !Objects.equals(temp.getId(), permission.getId()))
        {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Permission.Form.Error.NameExisted"));
            tfxName.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public String getFormIconPath() {
        return ViewHelpers.ICONS16 + "permission.png";
    }

    @Override
    public String getFormTitle() {
        if (isNewModel) {
            return I18n.CUSTOMERS.getString("Permission.Form.NewTitle");
        } else {
            return I18n.CUSTOMERS.getString("Permission.Form.EditTitle");
        }
    }       
}