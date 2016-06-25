/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhn.qlqa027a.GUI;

import hhn.qlqa027a.entities.Food;
import hhn.qlqa027a.entities.Menu;
import hhn.qlqa027a.entities.Menufood;
import hhn.qlqa027a.framework.AbstractDataPageView;
import hhn.qlqa027a.framework.EntityTableColumn;
import hhn.qlqa027a.util.I18n;
import hhn.qlqa027a.util.ViewHelpers;
import java.awt.BorderLayout;

/**
 *
 * @author Administrator
 */
public class MenufoodPage extends AbstractDataPageView<Menufood>{
    
    public MenufoodPage() {
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
                I18n.CUSTOMERS.getString("Menufood.Page.Menu"), "menu", Menu.class, 300));

        getTableModel().addColumn(new EntityTableColumn(
                I18n.CUSTOMERS.getString("Menu.Page.Food"), "food", Food.class, 200));
               
//        getTableModel().addColumn(new EntityTableColumn(
//                I18n.CUSTOMERS.getString("Menu.Page.Status"), "status", Status.class, 200));
    }
        
    @Override
    public String getIconPath() {
        return ViewHelpers.ICONS16 + "food.png";// cai nay dau co dau?
    }
   
    @Override
    public String getTitle() {
        return I18n.CUSTOMERS.getString("Menufood.Page.Title");
    }

    private void setLayout(BorderLayout borderLayout) {
        
    }

    // Variables declaration - do not modify                     
    private com.toedter.calendar.JDateChooser jDateChooser1;
    // End of variables declaration    
}
