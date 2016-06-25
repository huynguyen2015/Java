/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhn.qlqa027a.GUI;
import hhn.qlqa027a.entities.Menu;
import hhn.qlqa027a.framework.AbstractDataPageView;
import hhn.qlqa027a.framework.EntityTableColumn;
import hhn.qlqa027a.util.I18n;
import hhn.qlqa027a.util.ViewHelpers;
import java.awt.BorderLayout;
import java.util.Date;
/**
 *
 * @author Hoa The
 */
public class MenuPage extends AbstractDataPageView<Menu> {
    
    public MenuPage() {
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
                I18n.CUSTOMERS.getString("Menu.Page.Name"), "name", String.class, 300));

        getTableModel().addColumn(new EntityTableColumn(
                I18n.CUSTOMERS.getString("Menu.Page.CreatedAt"), "createdAt", Date.class, 200));
               
//        getTableModel().addColumn(new EntityTableColumn(
//                I18n.CUSTOMERS.getString("Menu.Page.Status"), "status", Status.class, 200));
    }
        
    @Override
    public String getIconPath() {
        return ViewHelpers.ICONS16 + "menu.png";
    }
   
    @Override
    public String getTitle() {
        return I18n.CUSTOMERS.getString("Menu.Page.Title");
    }

    private void setLayout(BorderLayout borderLayout) {
        
    }

    // Variables declaration - do not modify                     
    private com.toedter.calendar.JDateChooser jDateChooser1;
    // End of variables declaration    
}
