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
import hhn.qlqa027a.controller.RoleController;
import hhn.qlqa027a.entities.Permission;
import hhn.qlqa027a.entities.Role;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.util.Constants;
import hhn.qlqa027a.util.I18n;
import hhn.qlqa027a.util.ViewHelpers;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 * Employee form view
 *
 * @author Cem Ikta
 */
public class RoleForm extends AbstractFormView<Role> {

    private Role role = null;
    private boolean isNewModel = false;    

    // General page
    private JTextFieldExt tfxName;    
    private JTextFieldExt tfxType; 
    private List<JCheckBox> cbPermissions;
    private List<Permission> permissions;

    /**
     * Gets new instance of employee form
     *
     * @param controller employee controller
     * @param role model
     */
    public RoleForm(DataPageController<Role> controller, Role role) {
        super(AppController.get().getAppView(), controller);
        this.role = role;
        if (role.getId() == null) {
            isNewModel = true;
        }
    }

    @Override
    public void buildUI() {
        initComponents();
        // add pages
        addPageToForm(I18n.CUSTOMERS.getString("Role.Form.GeneralPage.Title"),
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
        tfxType = new JTextFieldExt(100);  
        cbPermissions = new ArrayList<JCheckBox>();
        permissions = ((RoleController) controller).getPermissionList();
        
        JPanel panel = new JPanel(new MigLayout("insets 20 10 10 10", "[][50:100,fill][fill,grow]", ""));
       
        JPanel panelPermissions = new JPanel(new MigLayout("wrap 3"));
        for(Permission permission : permissions){
            JCheckBox cb = new JCheckBox(permission.getName());
            cbPermissions.add(cb);
            panelPermissions.add(cb);
        }
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Role.Form.Name")), "gap para");
        panel.add(tfxName, "span"); 
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Role.Form.Type")), "gap para");
        panel.add(tfxType, "span");
     
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Role.Form.Permissions")), "gap para");
        panel.add(panelPermissions, "span"); 
        
        return panel;
    }       
    

    @Override
    public Role getEntity() {
        return role;
    }

    @Override
    public void popFields() {
        // general page
        tfxName.setText(role != null ? role.getName() : "");
        tfxType.setText(role != null ? role.getType() : "");
        if(role.getPermissions() != null && role.getPermissions().size() > 0){
            for(JCheckBox cb : cbPermissions){
                if(containsName((List<Permission>) role.getPermissions(), cb.getText())){
                    cb.setSelected(true);
                }
            }
        }
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
        role.setName(tfxName.getText());        
        role.setType(tfxType.getText()); 
        List<Permission> addPermissions = new ArrayList<Permission>();
        for(JCheckBox cb : cbPermissions){
            if(cb.isSelected()){
                addPermissions.add(getFirst(permissions, cb.getText()));
            }
        }
        role.setPermissions(addPermissions);
    }

    /**
     * Form validate
     *
     * @return true if no validate errors
     */
    @Override
    public boolean validateForm() {
        if (tfxName.getText().equals("")) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Role.Form.Error.RequiredName"));
            tfxName.requestFocus();
            return false;
        }  
        
        if (tfxType.getText().equals("")) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Role.Form.Error.RequiredType"));
            tfxType.requestFocus();
            return false;
        }
        
        Status activedStatus = ((RoleController) controller).getStatusById(Constants.statusActived);
        if (activedStatus == null){
            MessageBox.showWarning("No actived status found!");
            return false;
        }
        role.setStatus(activedStatus);  

        return true;
    }

    @Override
    public String getFormIconPath() {
        return ViewHelpers.ICONS16 + "role.png";
    }

    @Override
    public String getFormTitle() {
        if (isNewModel) {
            return I18n.CUSTOMERS.getString("Role.Form.NewTitle");
        } else {
            return I18n.CUSTOMERS.getString("Role.Form.EditTitle");
        }
    }       
}