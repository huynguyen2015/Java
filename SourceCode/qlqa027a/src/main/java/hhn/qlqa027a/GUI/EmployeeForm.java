/*
 * Employees Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.GUI;

import com.toedter.calendar.JDateChooser;
import hhn.qlqa027a.controller.AppController;
import hhn.qlqa027a.framework.AbstractFormView;
import hhn.qlqa027a.framework.DataPageController;
import hhn.qlqa027a.entities.Employee;
import hhn.qlqa027a.component.JTextFieldExt;
import hhn.qlqa027a.component.MessageBox;
import hhn.qlqa027a.controller.EmployeeController;
import hhn.qlqa027a.entities.Certificate;
import hhn.qlqa027a.entities.Role;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.util.I18n;
import hhn.qlqa027a.util.ViewHelpers;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import net.miginfocom.swing.MigLayout;

/**
 * Employee form view
 *
 * @author Cem Ikta
 */
public class EmployeeForm extends AbstractFormView<Employee> {

    private Employee employee = null;
    private boolean isNewModel = false;

    // General page
    private JTextFieldExt tfxIdentityNumber;
    private JTextFieldExt tfxFullName;    
    private JTextFieldExt tfxPhone;
    private JTextFieldExt tfxEmail;
    private JPasswordField tfxPassword;
    private JPasswordField tfxRePassword;    
    private JDateChooser jdcBirthDay;
    private JTextFieldExt tfxAddress;
    private List<JCheckBox> cbCertificates;
    private List<Certificate> certificates;
    private JComboBox cbRole;
    private JComboBox cbStatus;    

    /**
     * Gets new instance of employee form
     *
     * @param controller employee controller
     * @param employee model
     */
    public EmployeeForm(DataPageController<Employee> controller, Employee employee) {
        super(AppController.get().getAppView(), controller);
        this.employee = employee;
        if (employee.getId() == null) {
            isNewModel = true;
        }
    }

    @Override
    public void buildUI() {
        initComponents();
        // add pages
        addPageToForm(I18n.CUSTOMERS.getString("Employee.Form.GeneralPage.Title"),
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
        tfxIdentityNumber = new JTextFieldExt(100);        
        tfxFullName= new JTextFieldExt(50);
        tfxPhone = new JTextFieldExt(50);
        tfxEmail = new JTextFieldExt(50);        
        tfxPassword = new JPasswordField(50); 
        tfxRePassword = new JPasswordField(50); 
        tfxAddress = new JTextFieldExt(100);  
        jdcBirthDay = new JDateChooser(); 
        cbRole = new JComboBox(((EmployeeController) controller).getRoleList().toArray());
        cbStatus = new JComboBox(((EmployeeController) controller).getStatusList().toArray());
        cbCertificates = new ArrayList<JCheckBox>();
        certificates = ((EmployeeController) controller).getCertificateList();
        
        JPanel panel = new JPanel(new MigLayout("insets 20 10 10 10", "[][50:100,fill][fill,grow]", ""));

        JPanel panelCertificates = new JPanel(new MigLayout(("wrap 3")));
        for(Certificate certificate : certificates){
            JCheckBox cb = new JCheckBox(certificate.getName());
            cbCertificates.add(cb);
            panelCertificates.add(cb);
        }
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Employee.Form.Certificates")), "gap para");
        panel.add(panelCertificates, "span"); 
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Employee.Form.IdentityNumber")), "gap para");
        panel.add(tfxIdentityNumber, "span");        

        panel.add(new JLabel(I18n.CUSTOMERS.getString("Employee.Form.FullName")), "gap para");
        panel.add(tfxFullName, "span");
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Employee.Form.BirthDay")), "gap para");
        panel.add(jdcBirthDay, "span");

        panel.add(new JLabel(I18n.CUSTOMERS.getString("Employee.Form.Phone")), "gap para");
        panel.add(tfxPhone, "span");

        panel.add(new JLabel(I18n.CUSTOMERS.getString("Employee.Form.Email")), "gap para");
        panel.add(tfxEmail, "span");
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Employee.Form.Password")), "gap para");
        panel.add(tfxPassword, "span");
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Employee.Form.RePassword")), "gap para");
        panel.add(tfxRePassword, "span");
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Employee.Form.Address")), "gap para");
        panel.add(tfxAddress, "span");

        panel.add(new JLabel(I18n.CUSTOMERS.getString("Employee.Form.Status")), "gap para");
        panel.add(cbStatus, "span");
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Employee.Form.Role")), "gap para");
        panel.add(cbRole, "span");                 

        return panel;
    }       
    

    @Override
    public Employee getEntity() {
        return employee;
    }

    @Override
    public void popFields() {
        // general page
        tfxFullName.setText(employee.getFullName());
        tfxPhone.setText(employee.getPhone());
        tfxEmail.setText(employee.getEmail());
        tfxPassword.setText(employee.getPassword());
        jdcBirthDay.setDate(employee.getBirthday());
        tfxAddress.setText(employee.getAddress());
        tfxIdentityNumber.setText(employee.getIdentityNumber());
        if (employee.getRole()!= null) {
            cbRole.setSelectedItem(employee.getRole());
        }
        if (employee.getStatus()!= null) {
            cbStatus.setSelectedItem(employee.getStatus());
        }
        if(employee.getCertificates() != null && employee.getCertificates().size() > 0){
            for(JCheckBox cb : cbCertificates){
                if(containsName((List<Certificate>) employee.getCertificates(), cb.getText())){
                    cb.setSelected(true);
                }
            }
        }
    }
    public boolean containsName(final List<Certificate> list, final String name){
        return list != null && list.stream().filter(o -> o.getName().equals(name)).findFirst().isPresent();
    }
    @Override
    public void pushFields() {
        employee.setFullName(tfxFullName.getText());
        employee.setPhone(tfxPhone.getText());
        employee.setEmail(tfxEmail.getText());
        employee.setPassword(tfxPassword.getText());
        employee.setBirthday(jdcBirthDay.getDate());
        employee.setAddress(tfxAddress.getText());
        employee.setIdentityNumber(tfxIdentityNumber.getText());
        employee.setRole(Role.class.cast(cbRole.getSelectedItem()));
        employee.setStatus(Status.class.cast(cbStatus.getSelectedItem()));
        List<String> certificateNames = new ArrayList<String>();
        for(JCheckBox cb : cbCertificates){
            if(cb.isSelected()){
                certificateNames.add(cb.getText());
            }
        }
        
        if(certificateNames.size() > 0){
            employee.setCertificates(((EmployeeController) controller).getCertificateListByNames(certificateNames));
        }
    }

    /**
     * Form validate
     *
     * @return true if no validate errors
     */
    @Override
    public boolean validateForm() {
        if (tfxIdentityNumber.getText().equals("")) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Employee.Form.Error.RequiredIdentityNumber"));
            tfxIdentityNumber.requestFocus();
            return false;
        }

        if (tfxFullName.getText().equals("")) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Employee.Form.Error.RequiredFullName"));
            tfxFullName.requestFocus();
            return false;
        }

        if (tfxEmail.getText().equals("")) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Employee.Form.Error.RequiredEmail"));
            tfxEmail.requestFocus();
            return false;
        }
        
        if (!isValidEmailAddress(tfxEmail.getText())) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Employee.Form.Error.InvalidEmail"));
            tfxEmail.requestFocus();
            return false;
        }
        
        if (((EmployeeController) controller).isEmailInUsed(tfxEmail.getText(), employee.getId() == null ? 0 : employee.getId())) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Employee.Form.Error.UsedEmail"));
            tfxEmail.requestFocus();
            return false;
        }
                
        if (tfxPassword.getText().equals("")) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Employee.Form.Error.RequiredPassword"));
            tfxPassword.requestFocus();
            return false;
        }
        
        if (tfxPassword.getText().length() < 6) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Employee.Form.Error.MinPassword"));
            tfxPassword.requestFocus();
            return false;
        }
        
        if (tfxPassword.getText().length() > 50) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Employee.Form.Error.MaxPassword"));
            tfxPassword.requestFocus();
            return false;
        }
        
        if (isNewModel && tfxRePassword.getText().equals("")) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Employee.Form.Error.RequiredRePassword"));
            tfxRePassword.requestFocus();
            return false;
        }
        
        if ((isNewModel && !tfxRePassword.getText().equals(tfxPassword.getText()))
                || (!isNewModel && tfxRePassword.getText().length() > 0 && !tfxRePassword.getText().equals(tfxPassword.getText()))) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Employee.Form.Error.RequiredRePasswordNotMatch"));
            tfxRePassword.requestFocus();
            return false;
        }
        
        if (tfxAddress.getText().equals("")) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Employee.Form.Error.RequiredAddress"));
            tfxAddress.requestFocus();
            return false;
        }
        
        if (!isNewModel && cbStatus.getSelectedItem().equals("")) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Employee.Form.Error.RequiredStatus"));
            cbStatus.requestFocus();
            return false;
        }
        
        if (cbRole.getSelectedItem().equals("")) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Employee.Form.Error.RequiredRole"));
            cbRole.requestFocus();
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
            return I18n.CUSTOMERS.getString("Employee.Form.NewTitle");
        } else {
            return I18n.CUSTOMERS.getString("Employee.Form.EditTitle");
        }
    }
    
    @Override
    public boolean isMultiPageForm() {
        return true;
    }  
}