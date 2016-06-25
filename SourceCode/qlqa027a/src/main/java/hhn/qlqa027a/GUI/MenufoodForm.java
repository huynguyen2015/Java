/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhn.qlqa027a.GUI;

import hhn.qlqa027a.component.MessageBox;
import hhn.qlqa027a.controller.AppController;
import hhn.qlqa027a.controller.MenufoodController;
import hhn.qlqa027a.entities.Food;
import hhn.qlqa027a.entities.Menu;
import hhn.qlqa027a.entities.Menufood;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.framework.AbstractFormView;
import hhn.qlqa027a.framework.DataPageController;
import hhn.qlqa027a.util.Constants;
import hhn.qlqa027a.util.I18n;
import hhn.qlqa027a.util.ViewHelpers;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Hoa The
 */
public class MenufoodForm extends AbstractFormView<Menufood>{
    
    private Menufood menufood = null;
    private boolean isNewModel = false;
    
    private JComboBox cbMenu;
    private JComboBox cbFood; 
    
    public MenufoodForm(DataPageController<Menufood> controller, Menufood menufood) {
        super(AppController.get().getAppView(), controller);
        this.menufood = menufood;
        if (menufood.getId() == null) {
            isNewModel = true;
        }
    }

    @Override
    public void buildUI() {
        initComponents();
        // add pages
        addPageToForm(I18n.CUSTOMERS.getString("Menufood.Form.GeneralPage.Title"),
                buildGeneralPage());
        
        popFields();
        pack(); 
        setSize(550, 530);
    }
    
    private JPanel buildGeneralPage() {
        cbMenu = new JComboBox(((MenufoodController) controller).getMenuList().toArray());
        cbFood = new JComboBox(((MenufoodController) controller).getFoodList().toArray());
        
        JPanel panel = new JPanel(new MigLayout("insets 20 10 10 10", "[][50:100,fill][fill,grow]", ""));   
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Menufood.Page.Menu")), "gap para");
        panel.add(cbMenu, "span");
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Menufood.Page.Food")), "gap para");
        panel.add(cbFood, "span");    
        
        return panel;
    }
    
    @Override
    public Menufood getEntity() {
        return menufood;
    }

    @Override
    public void popFields() {
        // general page
        if (menufood.getMenu()!= null) {
            cbMenu.setSelectedItem(menufood.getMenu());
        }
        if (menufood.getFood()!= null) {
            cbFood.setSelectedItem(menufood.getFood());
        }      
    } 
    
    @Override
    public void pushFields() {
        menufood.setMenu(Menu.class.cast(cbMenu.getSelectedItem()));
        menufood.setFood(Food.class.cast(cbFood.getSelectedItem()));        
    }

    /**
     * Form validate
     *
     * @return true if no validate errors
     */
    @Override
    public boolean validateForm() {
        Status activedStatus = ((MenufoodController) controller).getStatusById(Constants.statusActived);
        if (activedStatus == null){
            MessageBox.showWarning("No actived status found!");
            return false;
        }
        menufood.setStatus(activedStatus);
        return true;
    }

    @Override
    public String getFormIconPath() {
        return ViewHelpers.ICONS16 + "food.png";
    }

    @Override
    public String getFormTitle() {
        if (isNewModel) {
            return I18n.CUSTOMERS.getString("Menufood.Form.NewTitle");
        } else {
            return I18n.CUSTOMERS.getString("Menufood.Form.EditTitle");
        }
    }
    
    @Override
    public boolean isMultiPageForm() {
        return true;
    }  
}
