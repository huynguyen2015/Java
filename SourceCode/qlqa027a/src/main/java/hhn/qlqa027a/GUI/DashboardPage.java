/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */

package hhn.qlqa027a.GUI;

import hhn.qlqa027a.component.MessageBox;
import hhn.qlqa027a.controller.AppController;
import hhn.qlqa027a.controller.DashboardController;
import hhn.qlqa027a.entities.Employee;
import hhn.qlqa027a.framework.PageController;
import hhn.qlqa027a.framework.PageView;
import hhn.qlqa027a.util.I18n;
import hhn.qlqa027a.util.ViewHelpers;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXHyperlink;
import org.jdesktop.swingx.WrapLayout;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.painter.decoration.DecorationAreaType;

/**
 * Dashboard Page View
 *
 * @author Cem Ikta
 */
public class DashboardPage implements PageView {  
    private JPanel pageView;
    private PageController controller;
    private boolean isAuthorized;
    private Preferences prefs;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JLabel lblUserName;
    private JLabel lblPassword;
    private JButton btnLogin;
    private JPanel loginPanelWrap;
    private JPanel memberItem;
    private JPanel roleItem;
    private JPanel permissionItem;
    private JPanel menuItem;
    private JPanel categoryItem;
    private JPanel foodItem;
    private JPanel tableItem;
    private JPanel itemItem;
    private JPanel shiftItem;
    private JPanel scheduleItem;
    private JPanel employeeScheduleItem;
    private JPanel computerProficiencyItem;
    private JPanel billItem;

    public DashboardPage() {
    }

    @Override
    public void init(PageController controller) {
        prefs = Preferences.userRoot().node("UserInfo");        
        this.controller = controller;        
        isAuthorized = prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.IsAuthorized"), false);        
        initComponents();
    }

    /**
     * init components
     */
    private void initComponents() {        
        pageView = new JPanel(new BorderLayout());        
        pageView.add(getHeaderBar(), BorderLayout.NORTH);
        pageView.add(getCenterPanel(), BorderLayout.CENTER);        
    }

    /**
     * Header Bar of dashboard page view
     *
     * @return header bar
     */
    public JPanel getHeaderBar() {
        JPanel headerBar = new JPanel(new MigLayout("insets 2 2 2 2"));

        JLabel lblTitle = new JLabel(getTitle());
        lblTitle.setIcon(new ImageIcon(getClass().getResource(getIconPath())));
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, 14));

        headerBar.setPreferredSize(new Dimension(lblTitle.getWidth(), lblTitle.getHeight() + 28));
        headerBar.add(lblTitle, "dock center, gapleft 4");

        SubstanceLookAndFeel.setDecorationType(headerBar, DecorationAreaType.HEADER);

        return headerBar;
    }
    
    /**
     * Center panel of dashboard page view
     * 
     * @return center panel 
     */
    private Component getCenterPanel() {          
        JPanel centerPanel = new JPanel(new BorderLayout());    
        centerPanel.setName("pnMenu");
        JPanel northPanel = new JPanel(new WrapLayout(FlowLayout.CENTER, 20, 50));                  
        northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));                     
        
        memberItem = createItemPanel(ViewHelpers.IMAGES + "customer.png",
            I18n.CUSTOMERS.getString("Dashboard.Admin.LinkMembers"), 
            new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!isAuthorized 
                        || !prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasMemberPermission"), false))
                        return;
                    AppController.get().getAppView().onOpenCustomers();
                }
            }
        );                     
        memberItem.setPreferredSize(new Dimension(120, 70));
        
        roleItem = createItemPanel(ViewHelpers.IMAGES + "role.png", 
            I18n.CUSTOMERS.getString("Dashboard.Admin.LinkRoles"), 
            new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!isAuthorized 
                        || !prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasRolePermission"), false))
                        return;
                    AppController.get().getAppView().onOpenRoles();
                }
            }
        );           
        roleItem.setPreferredSize(new Dimension(120, 70));
        
        permissionItem = createItemPanel(ViewHelpers.IMAGES + "permission.png", 
            I18n.CUSTOMERS.getString("Dashboard.Admin.LinkPermissions"), 
            new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!isAuthorized 
                        || !prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasPermissionPermission"), false))
                        return;
                    AppController.get().getAppView().onOpenPermissions();
                }
            }
        );         
        permissionItem.setPreferredSize(new Dimension(120, 70));
        
        menuItem = createItemPanel(ViewHelpers.IMAGES + "menu.png", 
            I18n.CUSTOMERS.getString("Dashboard.Admin.LinkMenus"), 
            new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!isAuthorized 
                        || !prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasMenuPermission"), false))
                        return;
                    AppController.get().getAppView().onOpenMenu();
                }
            }
        );          
        menuItem.setPreferredSize(new Dimension(120, 70));
        
        categoryItem = createItemPanel(ViewHelpers.IMAGES + "category.png", 
            I18n.CUSTOMERS.getString("Dashboard.Admin.LinkCategories") , 
            new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!isAuthorized 
                        || !prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasCategoryPermission"), false))
                        return;
                    AppController.get().getAppView().onOpenCategories();
                }
            }
        );         
        categoryItem.setPreferredSize(new Dimension(120, 70));

        foodItem = createItemPanel(ViewHelpers.IMAGES + "food.png", 
            I18n.CUSTOMERS.getString("Dashboard.Admin.LinkFoods"), 
            new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!isAuthorized 
                        || !prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasFoodPermission"), false))
                        return;
                    AppController.get().getAppView().onOpenFood();
                }
            }
        );          
        foodItem.setPreferredSize(new Dimension(120, 70));
        
        tableItem = createItemPanel(ViewHelpers.IMAGES + "table.png", 
            I18n.CUSTOMERS.getString("Dashboard.Admin.LinkTables") , 
            new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!isAuthorized 
                        || !prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasTablePermission"), false))
                        return;
                    AppController.get().getAppView().onOpenStable();
                }
            }
        );           
        tableItem.setPreferredSize(new Dimension(120, 70));
        
        itemItem = createItemPanel(ViewHelpers.IMAGES + "item.png", 
            I18n.CUSTOMERS.getString("Dashboard.Admin.LinkItems") , 
            new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!isAuthorized 
                        || !prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasItemPermission"), false))
                        return;
                    AppController.get().getAppView().onOpenCountries();
                }
            }
        );           
        itemItem.setPreferredSize(new Dimension(120, 70));
        
        shiftItem = createItemPanel(ViewHelpers.IMAGES + "shift.png", 
            I18n.CUSTOMERS.getString("Dashboard.Admin.LinkShifts"), 
            new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!isAuthorized 
                        || !prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasShiftPermission"), false))
                        return;
                        AppController.get().getAppView().onOpenViewShift();
                }
            }
        );           
        shiftItem.setPreferredSize(new Dimension(120, 70));
        
        scheduleItem = createItemPanel(ViewHelpers.IMAGES + "schedule.png", 
            I18n.CUSTOMERS.getString("Dashboard.Admin.LinkSchedules"), 
            new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!isAuthorized 
                        || !prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasSchedulePermission"), false))
                        return;                    
                    AppController.get().getAppView().onOpenSchedule();
                }
            }
        );          
        scheduleItem.setPreferredSize(new Dimension(120, 70));
        
        employeeScheduleItem = createItemPanel(ViewHelpers.IMAGES + "schedule.png", 
            I18n.CUSTOMERS.getString("Dashboard.Admin.LinkEmployeeSchedules"), 
            new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!isAuthorized 
                        || !prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasSchedulePermission"), false))
                        return;                    
                    AppController.get().getAppView().onOpenEmployeeSchedule();
                }
            }
        );          
        employeeScheduleItem.setPreferredSize(new Dimension(120, 70));
        
        computerProficiencyItem = createItemPanel(ViewHelpers.IMAGES + "computer_proficiency.png", 
            I18n.CUSTOMERS.getString("Dashboard.Admin.LinkComputerProficiencies"), 
            new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!isAuthorized 
                        || !prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasCPPermission"), false))
                        return;
                    AppController.get().getAppView().onOpenCountries();
                }
            }
        );         
        computerProficiencyItem.setPreferredSize(new Dimension(120, 70));
        
        billItem = createItemPanel(ViewHelpers.IMAGES + "bill.png", 
            I18n.CUSTOMERS.getString("Dashboard.Admin.LinkBills"), 
            new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!isAuthorized 
                        || !prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasBillPermission"), false))
                        return;
                    AppController.get().getAppView().onOpenCountries();
                }
            }
        );           
        billItem.setPreferredSize(new Dimension(120, 70));
        
        // Login panel        
        JPanel loginPanel = createLoginPanel();        
        enabledMenus();
        northPanel.add(memberItem, "gapright 70");
        northPanel.add(menuItem, "gapright 70");
        northPanel.add(tableItem, "gapright 70");
        northPanel.add(foodItem, "gapright 70");        
        northPanel.add(roleItem, "gapright 70");  
        northPanel.add(permissionItem, "gapright 70");  
        northPanel.add(itemItem, "gapright 70");  
        northPanel.add(shiftItem, "gapright 70");  
        northPanel.add(scheduleItem, "gapright 70"); 
        northPanel.add(employeeScheduleItem, "gapright 70"); 
        northPanel.add(computerProficiencyItem, "gapright 70");  
        northPanel.add(billItem, "gapright 70");                                       
                
        centerPanel.add(northPanel, BorderLayout.NORTH);     
        centerPanel.add(loginPanel, BorderLayout.CENTER);
        
        return centerPanel;
    }

    /**
     * Creates item panel.
     * 
     * @param title title 
     * @param iconPath icon
     * @param found found string
     * @param openAction action of hyperlink
     * @return  item panel
     */
    private JPanel createItemPanel(String iconPath, String title, final AbstractAction openAction) {
        JPanel itemPanel = new JPanel(new BorderLayout());
        
        JLabel lblItem = new JLabel(new ImageIcon(getClass().getResource(iconPath)));
        lblItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblItem.addMouseListener(new MouseAdapter() { 
          @Override
          public void mousePressed(MouseEvent me) { 
            openAction.actionPerformed(null); 
          } 
        });                 
                
        JXHyperlink link = new JXHyperlink(openAction);
        link.setText(title);
        link.setHorizontalAlignment(JXHyperlink.CENTER);
        
        itemPanel.add(lblItem, BorderLayout.NORTH);
        itemPanel.add(link, BorderLayout.SOUTH);
        
        return itemPanel;
    }
    
    private JPanel createLoginPanel(){
        loginPanelWrap = new JPanel(new FlowLayout()); 
        loginPanelWrap.setVisible(!isAuthorized);
        if(!isAuthorized)
        {
            loginPanelWrap = new JPanel(new FlowLayout());          
            JPanel centerPanel = new JPanel(new GridBagLayout());        
            GridBagConstraints c = new GridBagConstraints();
            // Title        
            JLabel lblItem = new JLabel("Login Form");
            lblItem.setHorizontalAlignment(JLabel.CENTER); 
            lblItem.setFont(new Font("Serif", Font.BOLD, 14));
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 2;
            centerPanel.add(lblItem, c);

            // User name       
            lblUserName = new JLabel("Email");
            lblUserName.setHorizontalAlignment(JLabel.CENTER);
            txtUsername = new JTextField("huynq@gmail.com");
            txtUsername.setPreferredSize(new Dimension(200, 30)); 
            c.insets = new Insets(10,10,0,0);          
            c.gridy = 1;
            c.gridwidth = 1;
            centerPanel.add(lblUserName, c);            
            c.gridx = 1;                      
            centerPanel.add(txtUsername, c);

            // Password        
            lblPassword = new JLabel("Password");
            lblPassword.setHorizontalAlignment(JLabel.CENTER);
            txtPassword = new JPasswordField(45);
            txtPassword.setText("123456");
            txtPassword.setPreferredSize(new Dimension(200, 30));                   
            c.gridx = 0;
            c.gridy = 2;        
            centerPanel.add(lblPassword, c);              
            c.gridx = 1;        
            centerPanel.add(txtPassword, c);
            // Btn Login                                
            btnLogin = new JButton("Login");
            btnLogin.addActionListener((ActionEvent e) -> {
                Employee employee;
                try {
                    employee = ((DashboardController)getController()).CheckLogin(txtUsername.getText(), txtPassword.getText());
                    isAuthorized = employee != null;
                    AppController.get().getAppView().setPermission(employee);
                    enabledMenus();                
                    loginPanelWrap.setVisible(!isAuthorized);
                } catch (Exception ex) {
                    MessageBox.showWarning(ex.getMessage());
                    Logger.getLogger(DashboardPage.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);                    
                }

            });
            c.gridwidth = 2;
            c.gridx = 0;
            c.gridy = 3;
            centerPanel.add(btnLogin,c);

            loginPanelWrap.add(centerPanel);
        }
        return loginPanelWrap;
    }
    
    @Override
    public PageController getController() {
        return this.controller;
    }

    @Override
    public Component asComponent() {
        return this.pageView;
    }
    
    @Override
    public String getIconPath() {
        return ViewHelpers.ICONS16 + "dashboard.png";
    }

    @Override
    public String getTitle() {
        return I18n.CUSTOMERS.getString("Dashboard.Admin.Dashboard");
    }   

    public void enableComponents(Container container, boolean enable) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(enable);
            if (component instanceof Container) {
                enableComponents((Container)component, enable);
            }
        }
    }
    
    private void enabledMenus(){
        
        enableComponents(memberItem
                , isAuthorized 
                && prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasMemberPermission"), false));
        enableComponents(roleItem
                , isAuthorized && prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasRolePermission"), false));
        enableComponents(permissionItem
                , isAuthorized && prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasPermissionPermission"), false));
        enableComponents(menuItem
                , isAuthorized && prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasMenuPermission"), false));
        enableComponents(categoryItem
                , isAuthorized && prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasCategoryPermission"), false));
        enableComponents(foodItem
                , isAuthorized && prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasFoodPermission"), false));
        enableComponents(tableItem
                , isAuthorized && prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasTablePermission"), false));
        enableComponents(itemItem
                , isAuthorized && prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasItemPermission"), false));
        enableComponents(shiftItem
                , isAuthorized && prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasShiftPermission"), false));
        enableComponents(scheduleItem
                , isAuthorized && prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasSchedulePermission"), false));
        enableComponents(employeeScheduleItem
                , isAuthorized && prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasSchedulePermission"), false));
        enableComponents(computerProficiencyItem
                , isAuthorized  && prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasCPPermission"), false));
        enableComponents(billItem
                , isAuthorized && prefs.getBoolean(I18n.CUSTOMERS.getString("App.Session.HasBillPermission"), false));
    }
}
