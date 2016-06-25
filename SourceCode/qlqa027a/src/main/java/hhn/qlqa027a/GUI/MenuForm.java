/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhn.qlqa027a.GUI;
import com.toedter.calendar.JDateChooser;
import hhn.qlqa027a.component.JTextFieldExt;
import hhn.qlqa027a.component.MessageBox;
import hhn.qlqa027a.controller.AppController;
import hhn.qlqa027a.controller.MenuController;
import hhn.qlqa027a.framework.AbstractFormView;
import hhn.qlqa027a.entities.Menu;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.framework.DataPageController;
import hhn.qlqa027a.util.Constants;
import hhn.qlqa027a.util.I18n;
import hhn.qlqa027a.util.ViewHelpers;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
/**
 *
 * @author Hoa The
 */
public class MenuForm extends AbstractFormView<Menu> {
    
    private Menu menu = null;
    private boolean isNewModel = false;
    
    private JTextFieldExt tfxName;
    private JDateChooser jdcCreatedAt;
    
     public MenuForm(DataPageController<Menu> controller, Menu menu) {
        super(AppController.get().getAppView(), controller);
        this.menu = menu;
        if (menu.getId() == null) {
            isNewModel = true;
        }
    }

    @Override
    public void buildUI() {
        initComponents();
        // add pages
        addPageToForm(I18n.CUSTOMERS.getString("Menu.Form.GeneralPage.Title"),
                buildGeneralPage());
        
        popFields();
        pack(); 
        setSize(550, 530);
    }
    
    private JPanel buildGeneralPage() {
        
        tfxName= new JTextFieldExt(50);
        jdcCreatedAt = new JDateChooser(); 
        
        JPanel panel = new JPanel(new MigLayout("insets 20 10 10 10", "[][50:100,fill][fill,grow]", ""));
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Menu.Form.Name")), "gap para");
        panel.add(tfxName, "span");        

        panel.add(new JLabel(I18n.CUSTOMERS.getString("Menu.Page.CreatedAt")), "gap para");
        panel.add(jdcCreatedAt, "span");
        
        return panel;
    }
    
    @Override
    public Menu getEntity() {
        return menu;
    }

    @Override
    public void popFields() {
        // general page
        tfxName.setText(menu.getName());       
        jdcCreatedAt.setDate(menu.getCreatedAt());              
    }

    @Override
    public void pushFields() {
        menu.setName(tfxName.getText());      
        menu.setCreatedAt(jdcCreatedAt.getDate());       
    }

    /**
     * Form validate
     *
     * @return true if no validate errors
     */
    @Override
    public boolean validateForm() {        
        if (tfxName.getText().equals("")) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Menu.Form.Error.Name"));
            tfxName.requestFocus();
            return false;
        }
        
        Status activedStatus = ((MenuController) controller).getStatusById(Constants.statusActived);
        if (activedStatus == null){
            MessageBox.showWarning("No actived status found!");
            return false;
        }
        menu.setStatus(activedStatus); 
        
        return true;
    }

    @Override
    public String getFormIconPath() {
        return ViewHelpers.ICONS16 + "menu.png";
    }

    @Override
    public String getFormTitle() {
        if (isNewModel) {
            return I18n.CUSTOMERS.getString("Menu.Form.NewTitle");
        } else {
            return I18n.CUSTOMERS.getString("Menu.Form.EditTitle");
        }
    }
    
    @Override
    public boolean isMultiPageForm() {
        return true;
    } 
}
