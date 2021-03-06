/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhn.qlqa027a.GUI;

import hhn.qlqa027a.component.JTextFieldExt;
import hhn.qlqa027a.component.MessageBox;
import hhn.qlqa027a.controller.AppController;
import hhn.qlqa027a.controller.FoodController;
import hhn.qlqa027a.entities.Category;
import hhn.qlqa027a.entities.Food;
import hhn.qlqa027a.entities.Status;
import hhn.qlqa027a.framework.AbstractFormView;
import hhn.qlqa027a.framework.DataPageController;
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
public class FoodForm extends AbstractFormView<Food>{
    
    private Food food = null;
    private boolean isNewModel = false;
    
    private JTextFieldExt tfxName;
    private JTextFieldExt tfxPrice;
    private JComboBox cbCategory;
    private JComboBox cbStatus; 
    
    public FoodForm(DataPageController<Food> controller, Food food) {
        super(AppController.get().getAppView(), controller);
        this.food = food;
        if (food.getId() == null) {
            isNewModel = true;
        }
    }

    @Override
    public void buildUI() {
        initComponents();
        // add pages
        addPageToForm(I18n.CUSTOMERS.getString("Food.Form.GeneralPage.Title"),
                buildGeneralPage());
        
        popFields();
        pack(); 
        setSize(550, 530);
    }
    
    private JPanel buildGeneralPage() {

        tfxName= new JTextFieldExt(50);
        tfxPrice= new JTextFieldExt(50);
        cbCategory = new JComboBox(((FoodController) controller).getCategoryList().toArray());
        cbStatus = new JComboBox(((FoodController) controller).getStatusList().toArray());
        
        JPanel panel = new JPanel(new MigLayout("insets 20 10 10 10", "[][50:100,fill][fill,grow]", ""));
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Food.Form.Name")), "gap para");
        panel.add(tfxName, "span");
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Food.Form.Price")), "gap para");
        panel.add(tfxPrice, "span");
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Food.Form.Category")), "gap para");
        panel.add(cbCategory, "span");    
        
        panel.add(new JLabel(I18n.CUSTOMERS.getString("Food.Form.Status")), "gap para");
        panel.add(cbStatus, "span");
                    
        return panel;
    }
    
    @Override
    public Food getEntity() {
        return food;
    }

    @Override
    public void popFields() {
        // general page
        tfxName.setText(food.getName());
        tfxPrice.setText(food.getPrice() != null ? food.getPrice().toString() : "");
        if (food.getCategory()!= null) {
            cbCategory.setSelectedItem(food.getCategory());
        }
        if (food.getStatus()!= null) {
            cbStatus.setSelectedItem(food.getStatus());
        }      
    } 
    
    @Override
    public void pushFields() {
        food.setName(tfxName.getText());
        food.setPrice(Double.parseDouble(tfxPrice.getText()));
        food.setCategory(Category.class.cast(cbCategory.getSelectedItem()));
        food.setStatus(Status.class.cast(cbStatus.getSelectedItem()));        
    }

    /**
     * Form validate
     *
     * @return true if no validate errors
     */
    @Override
    public boolean validateForm() {
         if (tfxName.getText().equals("")) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Food.Form.Error.Name"));
            tfxName.requestFocus();
            return false;
        } 
        
         if (tfxPrice.getText().equals("")) {
            MessageBox.showWarning(I18n.CUSTOMERS.getString("Food.Form.Error.Price"));
            tfxPrice.requestFocus();
            return false;
        }
         
//        if (!tfxPrice.getText().matches("\\+\\d+")) {
//            MessageBox.showWarning(I18n.CUSTOMERS.getString("Food.Form.Error.Price2"));
//            tfxPrice.requestFocus();
//            return false;
//        }
        
        return true;
    }

    @Override
    public String getFormIconPath() {
        return ViewHelpers.ICONS16 + "food.png";
    }

    @Override
    public String getFormTitle() {
        if (isNewModel) {
            return I18n.CUSTOMERS.getString("Food.Form.NewTitle");
        } else {
            return I18n.CUSTOMERS.getString("Food.Form.EditTitle");
        }
    }
    
    @Override
    public boolean isMultiPageForm() {
        return true;
    }  
}
