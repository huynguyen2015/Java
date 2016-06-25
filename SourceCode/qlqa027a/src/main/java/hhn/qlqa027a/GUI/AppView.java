/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2016, HNH
 */
package hhn.qlqa027a.GUI;

import hhn.qlqa027a.component.AboutDialog;
import hhn.qlqa027a.component.LookAndFeelDialog;
import hhn.qlqa027a.component.MessageBox;
import hhn.qlqa027a.controller.AppController;
import hhn.qlqa027a.controller.DashboardController;
import hhn.qlqa027a.controller.EmployeeController;
import hhn.qlqa027a.controller.FoodController;
import hhn.qlqa027a.controller.EmployeeScheduleController;
import hhn.qlqa027a.controller.MenuController;
import hhn.qlqa027a.controller.MenufoodController;
import hhn.qlqa027a.controller.PermissionController;
import hhn.qlqa027a.controller.RoleController;
import hhn.qlqa027a.controller.ShiftController;
import hhn.qlqa027a.entities.Employee;
import hhn.qlqa027a.entities.Permission;
import hhn.qlqa027a.controller.ScheduleController;
import hhn.qlqa027a.controller.StableController;;
import hhn.qlqa027a.controller.StableController;
import hhn.qlqa027a.framework.View;
import hhn.qlqa027a.util.I18n;
import hhn.qlqa027a.util.ViewHelpers;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.prefs.Preferences;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdesktop.swingx.JXStatusBar;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.JCommandButton.CommandButtonKind;
import org.pushingpixels.flamingo.api.common.JCommandToggleButton;
import org.pushingpixels.flamingo.api.common.icon.EmptyResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.JRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenu;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntryFooter;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntryPrimary;
import org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority;
import org.pushingpixels.flamingo.api.ribbon.RibbonTask;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;
import org.pushingpixels.flamingo.api.ribbon.resize.IconRibbonBandResizePolicy;

/**
 * Application View
 *
 * @author Cem Ikta
 */
public class AppView extends JRibbonFrame {

    private JXStatusBar xstatusBar;
    private JPanel centerPanel;
    // show exit message box?
    private final boolean showExitDialog = false;
    private boolean isAuthorize = false;
    private final Preferences prefs;
    private JRibbonBand membersBand;
    private JRibbonBand foodsBand;
    private JRibbonBand equipmentsBand;
    private JLabel lblUser;
    // Member band
    private JCommandButton cbtnDashboard;
    private JCommandButton cbtnMembers;
    private JCommandButton cbtnRoles;
    private JCommandButton cbtnPermissions;
    private JCommandButton cbtnShifts;
    private JCommandButton cbtnSchedules;
    private JCommandButton cbtnCPs;
    private JCommandButton cbtnBills;
    // Food band
    private JCommandButton cbtnMenus;
    private JCommandButton cbtnCategories;
    private JCommandButton cbtnFoods;
    private JCommandButton cbtnMenufoods;
    // Equipment band
    private JCommandButton cbtnTables;
    private JCommandButton cbtnItems;
    private JCommandButton cbtnLogOut;
    
    private boolean hasMemberPermission;
    private boolean hasMenuPermission;
    private boolean hasTablePermission;
    private boolean hasCategoryPermission;
    private boolean hasFoodPermission;
    private boolean hasRolePermission;
    private boolean hasPermissionPermission;
    private boolean hasItemPermission;
    private boolean hasShiftPermission;
    private boolean hasSchedulePermission;
    private boolean hasCPPermission;
    private boolean hasBillPermission;
    
    /**
     * Creates a new instance of AppView
     *
     * @param title
     */
    public AppView(String title) {
        super(title);
        prefs = Preferences.userRoot().node("UserInfo");        
        this.setIconImages(Arrays.asList(
                new ImageIcon(getClass().getResource(
                                ViewHelpers.ICONS16 + "app.png")).getImage(),
                new ImageIcon(getClass().getResource(
                                ViewHelpers.ICONS22 + "app.png")).getImage())
        );

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        initComponents();
        //setSize(1024, 768);
        // for max screen
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }

    /**
     * init ui components
     */
    private void initComponents() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitForm(e);
            }
        });

        getContentPane().add(buildStatusBar(), BorderLayout.SOUTH);

        // ribbon menu 
        configureRibbonMenu();

        // add dashboard page
        DashboardController dashboardController = new DashboardController();
        centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createMatteBorder(0, -1, 0, -1,
                ViewHelpers.getSubstanceComponentBorderColor(centerPanel)));
        centerPanel.add(dashboardController.getPageView().asComponent(), BorderLayout.CENTER);

        getContentPane().add(centerPanel, BorderLayout.CENTER);
        pack();
    }

    /**
     * Application exit
     * 
     * @see AppController#exit() 
     */
    private void exitForm(WindowEvent evt) {
        if (showExitDialog) {
            if (MessageBox.showAskYesNo(I18n.COMMON.getString(
                    "MessageBox.Confirm.ProgramExit")) == MessageBox.NO_OPTION) {
                return;
            }
        }
        
        setUserInfo(true, "", false, false, false, false, false, false, false, false, false
                , false, false, false, false);

        AppController.get().exit();
    }

    /**
     * Open dashboard page
     */
    public void onOpenDashboard() {
        DashboardController controller = new DashboardController();
        addPageToCenter(controller.getPageView());
    }

    /**
     * Open customers page
     */
    public void onOpenCustomers() {
        EmployeeController controller = new EmployeeController();
        addPageToCenter(controller.getDataPageView());
    }
    
    /**
     * Open view shift page
     */
    public void onOpenViewShift() {
        ShiftController controller = new ShiftController();
        addPageToCenter(controller.getDataPageView());
    }
    
    /**
     * Open view Role page
     */
    public void onOpenRoles() {
        RoleController controller = new RoleController();
        addPageToCenter(controller.getDataPageView());
    }
    
        /**
     * Open view Stable page
     */
    public void onOpenViewStable() {
        StableController controller = new StableController();
        addPageToCenter(controller.getDataPageView());
    }
    
    /**
     * Open view Permission page
     */
    public void onOpenPermissions() {
        PermissionController controller = new PermissionController();
        addPageToCenter(controller.getDataPageView());
    }

    /**
     * Open Schedule page
     */
    public void onOpenSchedule() {
        ScheduleController controller = new ScheduleController();
        addPageToCenter(controller.getDataPageView());
    }
    
    /**
     * Open Menu page
     */
    public void onOpenMenu() {
        MenuController controller = new MenuController();
        addPageToCenter(controller.getDataPageView());
    }
    
    /**
     * Open Food page
     */
    public void onOpenFood() {
        FoodController controller = new FoodController();
        addPageToCenter(controller.getDataPageView());
    }
    
    /**
     * Open Food page
     */
    public void onOpenMenufood() {
        MenufoodController controller = new MenufoodController();
        addPageToCenter(controller.getDataPageView());
    }
    
     /**
     * Open Schedule page
     */
    public void onOpenEmployeeSchedule() {
        EmployeeScheduleController controller = new EmployeeScheduleController();
        addPageToCenter(controller.getDataPageView());
    }
    
    /**
     * Open Stable page
     */
    public void onOpenStable() {
        StableController controller = new StableController();
        addPageToCenter(controller.getDataPageView());
    }
    
    /**
    
    /**
     * Open categories page
     */
    public void onOpenCategories() {
        
    }

    /**
     * Open countries page
     */
    public void onOpenCountries() {
       
    }

    /**
     * Help
     */
    public void onHelp() {
        MessageBox.showInfo("Help not implemented yet!");
    }

    /**
     * Show hide the status bar
     */
    public void onToogleStatusBar() {
        xstatusBar.setVisible(!xstatusBar.isVisible());
    }

    /**
     * Look and feel dialog
     */
    public void onLookAndFeel() {
        LookAndFeelDialog.showDialog();
    }

    /**
     * About Dialog
     */
    public void onAbout() {
        AboutDialog.showDialog();
    }

    /**
     * Settings Dialog
     */
    public void onSettings() {
        MessageBox.showInfo("Settings not implemented yet!");
    }

    /**
     * Adds page view or data page view to center
     *
     * @param page page view or data page view
     */
    public void addPageToCenter(View page) {
        centerPanel.removeAll();
        centerPanel.add(page.asComponent());
        centerPanel.revalidate();
        centerPanel.repaint();
        page.asComponent().requestFocus();
    }

    /**
     * Ribbon menu config
     */
    private void configureRibbonMenu() {
        // Member task
        RibbonTask memberTask = new RibbonTask(
                I18n.COMMON.getString("AppView.Managements"),
                getMembersBand(),
                getFoodsBand(),
                getEquipmentsBand(),
                getAppLogOutBand(),
                getAppExitBand());        
        memberTask.setKeyTip("Managements");             

        // extras task
        RibbonTask extrasTask = new RibbonTask(
                I18n.COMMON.getString("AppView.Extras"),
                getViewBand(),
                getExtrasBand());
        extrasTask.setKeyTip("E");

        RibbonTask helpTask = new RibbonTask(
                I18n.COMMON.getString("AppView.Help"),
                getHelpBand());
        helpTask.setKeyTip("H");

        this.getRibbon().addTask(memberTask);
        this.getRibbon().addTask(extrasTask);
        this.getRibbon().addTask(helpTask);               
             
        configureTaskBar();
        configureApplicationMenu();

        // help button to left side
        this.getRibbon().configureHelp(ViewHelpers.createResizableIcon(
                new ImageIcon(getClass().getResource(ViewHelpers.ICONS16 + "help.png"))), (ActionEvent e) -> {
                    onHelp();
        });
    }

    /**
     * Ribbon menu top taskbar actions
     */
    protected void configureTaskBar() {
        JCommandButton cbtnDashboard = new JCommandButton("",
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS16 + "dashboard.png"))));
        cbtnDashboard.addActionListener((ActionEvent e) -> {
            onOpenDashboard();
        });
        cbtnDashboard.setEnabled(isAuthorize);
        
        JCommandButton cbtnMembers = new JCommandButton("",
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS16 + "customer.png"))));
        cbtnMembers.addActionListener((ActionEvent e) -> {
            onOpenCustomers();
        });
        cbtnMembers.setEnabled(isAuthorize);

        JCommandButton cbtnTables = new JCommandButton("",
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS16 + "table.png"))));
        cbtnTables.addActionListener((ActionEvent e) -> {
            onOpenCategories();
        });
        cbtnTables.setEnabled(isAuthorize);

        JCommandButton cbtnFoods = new JCommandButton("",
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS16 + "food.png"))));
        cbtnFoods.addActionListener((ActionEvent e) -> {
            onOpenFood();
        });
        cbtnFoods.setEnabled(isAuthorize);
        
        JCommandButton cbtnMenufoods = new JCommandButton("",
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS16 + "food.png"))));
        cbtnFoods.addActionListener((ActionEvent e) -> {
            onOpenMenufood();
        });
        cbtnFoods.setEnabled(isAuthorize);

        this.getRibbon().addTaskbarComponent(cbtnDashboard);
        this.getRibbon().addTaskbarComponent(cbtnMembers);
        this.getRibbon().addTaskbarComponent(cbtnTables);
        this.getRibbon().addTaskbarComponent(cbtnFoods);
        this.getRibbon().addTaskbarComponent(cbtnMenufoods);
    }
    RibbonApplicationMenuEntryPrimary amEntryDashboard;
    RibbonApplicationMenuEntryPrimary amEntryMembers;
    RibbonApplicationMenuEntryPrimary amEntryMenus;
    RibbonApplicationMenuEntryPrimary amEntryTables;
    RibbonApplicationMenuEntryPrimary amEntryFoods;
    RibbonApplicationMenuEntryPrimary amEntryMenufoods;
    RibbonApplicationMenuEntryPrimary amEntryRoles;
    RibbonApplicationMenuEntryPrimary amEntryPermissions;
    RibbonApplicationMenuEntryPrimary amEntryItems;
    RibbonApplicationMenuEntryPrimary amEntryShifts;
    RibbonApplicationMenuEntryPrimary amEntrySchedules;
    RibbonApplicationMenuEntryPrimary amEntryCPs;
    RibbonApplicationMenuEntryPrimary amEntryBills;    
    /**
     * Ribbon Application Menu settings
     */
    protected void configureApplicationMenu() {
        amEntryDashboard = new RibbonApplicationMenuEntryPrimary(
                ViewHelpers.createResizableIcon(
                        new ImageIcon(getClass().getResource(ViewHelpers.ICONS22 + "dashboard.png"))),
                I18n.CUSTOMERS.getString("Action.Dashboard"), (ActionEvent e) -> {
                    onOpenDashboard();
        }, CommandButtonKind.ACTION_ONLY);
        amEntryDashboard.setActionKeyTip("D");

        amEntryMembers = new RibbonApplicationMenuEntryPrimary(
                ViewHelpers.createResizableIcon(
                        new ImageIcon(getClass().getResource(ViewHelpers.ICONS22 + "customer.png"))),
                I18n.CUSTOMERS.getString("Action.Members"), (ActionEvent e) -> {
                    onOpenCustomers();
        }, CommandButtonKind.ACTION_ONLY);
        amEntryMembers.setActionKeyTip("C");
        
        amEntryMenus = new RibbonApplicationMenuEntryPrimary(
                ViewHelpers.createResizableIcon(
                        new ImageIcon(getClass().getResource(ViewHelpers.ICONS22 + "menu.png"))),
                I18n.CUSTOMERS.getString("Action.Menus"), (ActionEvent e) -> {
                    onOpenMenu();
        }, CommandButtonKind.ACTION_ONLY);
        amEntryMenus.setActionKeyTip("A");

        amEntryTables = new RibbonApplicationMenuEntryPrimary(
                ViewHelpers.createResizableIcon(
                        new ImageIcon(getClass().getResource(ViewHelpers.ICONS22 + "table.png"))),
                I18n.CUSTOMERS.getString("Action.Tables"), (ActionEvent e) -> {
                    onOpenCategories();
        }, CommandButtonKind.ACTION_ONLY);
        amEntryTables.setActionKeyTip("A");

        amEntryFoods = new RibbonApplicationMenuEntryPrimary(
                ViewHelpers.createResizableIcon(
                        new ImageIcon(getClass().getResource(ViewHelpers.ICONS22 + "food.png"))),
                I18n.CUSTOMERS.getString("Action.Foods"), (ActionEvent e) -> {
                    onOpenFood();
        }, CommandButtonKind.ACTION_ONLY);
        amEntryFoods.setActionKeyTip("O");
        
        amEntryMenufoods = new RibbonApplicationMenuEntryPrimary(
                ViewHelpers.createResizableIcon(
                        new ImageIcon(getClass().getResource(ViewHelpers.ICONS22 + "food.png"))),
                I18n.CUSTOMERS.getString("Action.Foods"), (ActionEvent e) -> {
                    onOpenMenufood();
        }, CommandButtonKind.ACTION_ONLY);
        amEntryMenufoods.setActionKeyTip("O");
        
        amEntryRoles = new RibbonApplicationMenuEntryPrimary(
                ViewHelpers.createResizableIcon(
                        new ImageIcon(getClass().getResource(ViewHelpers.ICONS22 + "role.png"))),
                I18n.CUSTOMERS.getString("Action.Roles"), (ActionEvent e) -> {
                    onOpenRoles();
        }, CommandButtonKind.ACTION_ONLY);
        amEntryRoles.setActionKeyTip("R");

        amEntryPermissions = new RibbonApplicationMenuEntryPrimary(
                ViewHelpers.createResizableIcon(
                        new ImageIcon(getClass().getResource(ViewHelpers.ICONS22 + "permission.png"))),
                I18n.CUSTOMERS.getString("Action.Permissions"), (ActionEvent e) -> {
                    onOpenPermissions();
        }, CommandButtonKind.ACTION_ONLY);
        amEntryPermissions.setActionKeyTip("R");
        
        amEntryItems = new RibbonApplicationMenuEntryPrimary(
                ViewHelpers.createResizableIcon(
                        new ImageIcon(getClass().getResource(ViewHelpers.ICONS22 + "item.png"))),
                I18n.CUSTOMERS.getString("Action.Items"), (ActionEvent e) -> {
                    onOpenCountries();
        }, CommandButtonKind.ACTION_ONLY);
        amEntryItems.setActionKeyTip("R");
        
        amEntryShifts = new RibbonApplicationMenuEntryPrimary(
                ViewHelpers.createResizableIcon(
                        new ImageIcon(getClass().getResource(ViewHelpers.ICONS22 + "shift.png"))),
                I18n.CUSTOMERS.getString("Action.Shifts"), (ActionEvent e) -> {
                    onOpenViewShift();
        }, CommandButtonKind.ACTION_ONLY);
        amEntryShifts.setActionKeyTip("S");
        
        amEntrySchedules = new RibbonApplicationMenuEntryPrimary(
                ViewHelpers.createResizableIcon(
                        new ImageIcon(getClass().getResource(ViewHelpers.ICONS22 + "schedule.png"))),
                I18n.CUSTOMERS.getString("Action.Schedules"), (ActionEvent e) -> {
                    onOpenSchedule();
        }, CommandButtonKind.ACTION_ONLY);
        amEntrySchedules.setActionKeyTip("S");
        
        amEntryCPs = new RibbonApplicationMenuEntryPrimary(
                ViewHelpers.createResizableIcon(
                        new ImageIcon(getClass().getResource(ViewHelpers.ICONS22 + "computer_proficiency.png"))),
                I18n.CUSTOMERS.getString("Action.ComputerProficiencies"), (ActionEvent e) -> {
                    onOpenCountries();
        }, CommandButtonKind.ACTION_ONLY);
        amEntryCPs.setActionKeyTip("S");
        
        amEntryBills = new RibbonApplicationMenuEntryPrimary(
                ViewHelpers.createResizableIcon(
                        new ImageIcon(getClass().getResource(ViewHelpers.ICONS22 + "bill.png"))),
                I18n.CUSTOMERS.getString("Action.Bills"), (ActionEvent e) -> {
                    onOpenCountries();
        }, CommandButtonKind.ACTION_ONLY);
        amEntryBills.setActionKeyTip("S");
        
        RibbonApplicationMenuEntryFooter amEntryExit = new RibbonApplicationMenuEntryFooter(
                ViewHelpers.createResizableIcon(
                        new ImageIcon(getClass().getResource(ViewHelpers.ICONS16 + "exit.png"))),
                I18n.COMMON.getString("Action.Exit"), (ActionEvent e) -> { exitForm(null);});
        amEntryExit.setActionKeyTip("X");

        RibbonApplicationMenu applicationMenu = new RibbonApplicationMenu();
        
        applicationMenu.addMenuEntry(amEntryDashboard);
        applicationMenu.addMenuEntry(amEntryMenus);
        applicationMenu.addMenuEntry(amEntryMembers);
        applicationMenu.addMenuEntry(amEntryTables);
        applicationMenu.addMenuEntry(amEntryFoods);
        applicationMenu.addMenuEntry(amEntryMenufoods);
        applicationMenu.addMenuEntry(amEntryRoles);
        applicationMenu.addMenuEntry(amEntryPermissions);
        applicationMenu.addMenuEntry(amEntryItems);
        applicationMenu.addMenuEntry(amEntryShifts);
        applicationMenu.addMenuEntry(amEntrySchedules);
        applicationMenu.addMenuEntry(amEntryCPs);
        applicationMenu.addMenuEntry(amEntryBills);        
        applicationMenu.addFooterEntry(amEntryExit);
        setEnabledApplicationMenu();

        this.getRibbon().setApplicationMenu(applicationMenu);
    }
    private void setEnabledApplicationMenu(){
        amEntryMembers.setEnabled(hasMemberPermission);
        amEntryMenus.setEnabled(hasMenuPermission);
        amEntryTables.setEnabled(hasTablePermission);
        amEntryFoods.setEnabled(hasFoodPermission);
        amEntryRoles.setEnabled(hasRolePermission);
        amEntryPermissions.setEnabled(hasPermissionPermission);
        amEntryItems.setEnabled(hasItemPermission);
        amEntryShifts.setEnabled(hasShiftPermission);
        amEntrySchedules.setEnabled(hasSchedulePermission);
        amEntryCPs.setEnabled(hasCPPermission);
        amEntryBills.setEnabled(hasBillPermission);
    }
    private void configureApplicationMenuGroupMembers(){
        
    }
    
    private void configureApplicationMenuGroupFoods(){
        
    }
    
    private void configureApplicationMenuGroupEquipments(){
        
    }      

    /**
     * Member menu actions band
     *
     * @return file menu membersBand
     */
    private JRibbonBand getMembersBand() {
        membersBand = new JRibbonBand(
                I18n.COMMON.getString("AppView.MembersBand"),
                new EmptyResizableIcon(22));
        
        membersBand.setResizePolicies(
                (List)Arrays.asList(new CoreRibbonResizePolicies.None(membersBand.getControlPanel())
                    ,new IconRibbonBandResizePolicy(membersBand.getControlPanel())));        
        cbtnDashboard = new JCommandButton(
                I18n.CUSTOMERS.getString("Action.Dashboard"),
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS22 + "dashboard.png"))));
        cbtnDashboard.addActionListener((ActionEvent e) -> {
            onOpenDashboard();
        });
        cbtnDashboard.setActionKeyTip("D");        
        membersBand.addCommandButton(cbtnDashboard, RibbonElementPriority.TOP);

        cbtnMembers = new JCommandButton(
                I18n.CUSTOMERS.getString("Action.Members"),
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS22 + "customer.png"))));
        cbtnMembers.addActionListener((ActionEvent e) -> {
            onOpenCustomers(); 
        });
        cbtnMembers.setActionKeyTip("C");
        membersBand.addCommandButton(cbtnMembers, RibbonElementPriority.TOP);                
        
        cbtnRoles = new JCommandButton(
                I18n.CUSTOMERS.getString("Action.Roles"),
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS22 + "role.png"))));
        cbtnRoles.addActionListener((ActionEvent e) -> {
            onOpenRoles();
        });
        cbtnRoles.setActionKeyTip("O");
        membersBand.addCommandButton(cbtnRoles, RibbonElementPriority.TOP);
        
        cbtnPermissions = new JCommandButton(
                I18n.CUSTOMERS.getString("Action.Permissions"),
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS22 + "permission.png"))));
        cbtnPermissions.addActionListener((ActionEvent e) -> {
            onOpenPermissions();
        });
        cbtnPermissions.setActionKeyTip("O");
        membersBand.addCommandButton(cbtnPermissions, RibbonElementPriority.TOP);               
        
        cbtnShifts = new JCommandButton(
                I18n.CUSTOMERS.getString("Action.Shifts"),
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS22 + "shift.png"))));
        cbtnShifts.addActionListener((ActionEvent e) -> {
            onOpenViewShift();
        });
        cbtnShifts.setActionKeyTip("O");
        membersBand.addCommandButton(cbtnShifts, RibbonElementPriority.TOP);
        
        cbtnSchedules = new JCommandButton(
                I18n.CUSTOMERS.getString("Action.Schedules"),
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS22 + "schedule.png"))));
        cbtnSchedules.addActionListener((ActionEvent e) -> {
            onOpenSchedule();
        });
        cbtnSchedules.setActionKeyTip("O");
        membersBand.addCommandButton(cbtnSchedules, RibbonElementPriority.TOP);
        
        cbtnCPs = new JCommandButton(
                I18n.CUSTOMERS.getString("Action.ComputerProficiencies"),
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS22 + "computer_proficiency.png"))));
        cbtnCPs.addActionListener((ActionEvent e) -> {
            onOpenCountries();
        });
        cbtnCPs.setActionKeyTip("O");
        membersBand.addCommandButton(cbtnCPs, RibbonElementPriority.TOP);
        
        cbtnBills = new JCommandButton(
                I18n.CUSTOMERS.getString("Action.Bills"),
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS22 + "bill.png"))));
        cbtnBills.addActionListener((ActionEvent e) -> {
            onOpenCountries();
        });
        cbtnBills.setActionKeyTip("O");
        membersBand.addCommandButton(cbtnBills, RibbonElementPriority.TOP);         
        setEnableMemberBand();
        return membersBand;
    }    
    private void setEnableMemberBand(){        
        cbtnMembers.setEnabled(hasMemberPermission);
        cbtnRoles.setEnabled(hasRolePermission);
        cbtnPermissions.setEnabled(hasPermissionPermission);
        cbtnShifts.setEnabled(hasShiftPermission);
        cbtnSchedules.setEnabled(hasSchedulePermission);
        cbtnCPs.setEnabled(hasCPPermission);        
        cbtnBills.setEnabled(hasBillPermission);
    }
        
    /**
     * Food menu actions band
     * @return 
     */
    private JRibbonBand getFoodsBand(){
        
        foodsBand = new JRibbonBand(
                I18n.COMMON.getString("AppView.FoodsBand"),
                new EmptyResizableIcon(22));
        
        foodsBand.setResizePolicies(
                (List)Arrays.asList(new CoreRibbonResizePolicies.None(foodsBand.getControlPanel())
                    ,new IconRibbonBandResizePolicy(foodsBand.getControlPanel())));
        
        cbtnMenus = new JCommandButton(
                I18n.CUSTOMERS.getString("Action.Menus"),
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS22 + "menu.png"))));
        cbtnMenus.addActionListener((ActionEvent e) -> {
            onOpenMenu();
        });
        cbtnMenus.setActionKeyTip("C");
        foodsBand.addCommandButton(cbtnMenus, RibbonElementPriority.TOP);  
        
        cbtnCategories = new JCommandButton(
                I18n.CUSTOMERS.getString("Action.Categories"),
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS22 + "category.png"))));
        cbtnCategories.addActionListener((ActionEvent e) -> {
            onOpenCategories();
        });
        cbtnCategories.setActionKeyTip("C");
        foodsBand.addCommandButton(cbtnCategories, RibbonElementPriority.TOP);  

        cbtnFoods = new JCommandButton(
                I18n.CUSTOMERS.getString("Action.Foods"),
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS22 + "food.png"))));
        cbtnFoods.addActionListener((ActionEvent e) -> {
            onOpenFood();
        });
        cbtnFoods.setActionKeyTip("O");
        foodsBand.addCommandButton(cbtnFoods, RibbonElementPriority.TOP);
        
        cbtnMenufoods = new JCommandButton(
                I18n.CUSTOMERS.getString("Action.MenuFoods"),
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS22 + "food.png"))));
        cbtnMenufoods.addActionListener((ActionEvent e) -> {
            onOpenMenufood();
        });
        cbtnMenufoods.setActionKeyTip("O");
        foodsBand.addCommandButton(cbtnMenufoods, RibbonElementPriority.TOP);
        
        setEnableFoodBand();
        return foodsBand;
    }
    private void setEnableFoodBand(){        
        cbtnMenus.setEnabled(hasMenuPermission);
        cbtnCategories.setEnabled(hasCategoryPermission);        
        cbtnFoods.setEnabled(hasFoodPermission);    
        cbtnMenufoods.setEnabled(hasFoodPermission || hasMenuPermission); 
    }
        
    /**
     * Equipment menu actions band
     * @return 
     */
    private JRibbonBand getEquipmentsBand(){
        
        equipmentsBand = new JRibbonBand(
                I18n.COMMON.getString("AppView.FoodsBand"),
                new EmptyResizableIcon(22));
        
        equipmentsBand.setResizePolicies(
                (List)Arrays.asList(new CoreRibbonResizePolicies.None(equipmentsBand.getControlPanel())
                    ,new IconRibbonBandResizePolicy(equipmentsBand.getControlPanel())));
        
        cbtnTables = new JCommandButton(
                I18n.CUSTOMERS.getString("Action.Tables"),
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS22 + "table.png"))));
        cbtnTables.addActionListener((ActionEvent e) -> {
            onOpenStable();
        });
        cbtnTables.setActionKeyTip("A");
        
        equipmentsBand.addCommandButton(cbtnTables, RibbonElementPriority.TOP);
        
        cbtnItems = new JCommandButton(
                I18n.CUSTOMERS.getString("Action.Items"),
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS22 + "item.png"))));
        cbtnItems.addActionListener((ActionEvent e) -> {
            onOpenCountries();
        });
        cbtnItems.setActionKeyTip("O");
        equipmentsBand.addCommandButton(cbtnItems, RibbonElementPriority.TOP);
        
        setEnableEquipmentBand();
        return equipmentsBand;
    }
    private void setEnableEquipmentBand(){        
        cbtnTables.setEnabled(hasTablePermission);
        cbtnItems.setEnabled(hasItemPermission);       
    }
    
    /**
     * File menu logout band
     *
     * @return LogoutBand
     */
    private JRibbonBand getAppLogOutBand() {
        JRibbonBand appLogOutBand = new JRibbonBand(
                I18n.COMMON.getString("AppView.AppLogOutBand"),
                new EmptyResizableIcon(22));
        appLogOutBand.setResizePolicies(
                (List)Arrays.asList(new CoreRibbonResizePolicies.None(appLogOutBand.getControlPanel())
                        , new IconRibbonBandResizePolicy(appLogOutBand.getControlPanel())));
        
        
        cbtnLogOut = new JCommandButton(
                I18n.COMMON.getString("Action.Logout"),
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS22 + "close.png"))));
        cbtnLogOut.addActionListener((ActionEvent e) -> {
             setUserInfo(true, "",                      
                     isAuthorize = false,
                     hasMemberPermission = false, 
                     hasMenuPermission = false, 
                     hasTablePermission = false, 
                     hasCategoryPermission = false, 
                     hasFoodPermission = false, 
                     hasRolePermission = false, 
                     hasPermissionPermission = false, 
                     hasItemPermission = false, 
                     hasShiftPermission = false, 
                     hasSchedulePermission = false, 
                     hasCPPermission = false, 
                     hasBillPermission = false);
            onOpenDashboard();                   
        });        
        appLogOutBand.addCommandButton(cbtnLogOut, RibbonElementPriority.TOP);

        return appLogOutBand;
    }
    private void setEnableLogoutBand(){        
        cbtnLogOut.setEnabled(isAuthorize);             
    }
    /**
     * File menu exit band
     *
     * @return exitBand
     */
    private JRibbonBand getAppExitBand() {
        JRibbonBand appExitBand = new JRibbonBand(
                I18n.COMMON.getString("AppView.AppExitBand"),
                new EmptyResizableIcon(22));
        appExitBand.setResizePolicies(
                (List)Arrays.asList(new CoreRibbonResizePolicies.None(appExitBand.getControlPanel())
                        , new IconRibbonBandResizePolicy(appExitBand.getControlPanel())));

        JCommandButton cbtnAppExit = new JCommandButton(
                I18n.COMMON.getString("Action.Exit"),
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS22 + "exit.png"))));
        cbtnAppExit.addActionListener((ActionEvent e) -> {
            exitForm(null);
        });
        cbtnAppExit.setActionKeyTip("X");
        
        JCommandButton cbtnLogOut = new JCommandButton(
                I18n.COMMON.getString("Action.Exit"),
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS22 + "exit.png"))));
        cbtnAppExit.addActionListener((ActionEvent e) -> {
            exitForm(null);
        });
        cbtnAppExit.setActionKeyTip("X");
        appExitBand.addCommandButton(cbtnAppExit, RibbonElementPriority.TOP);

        return appExitBand;
    }

    /**
     * Extras menu view band and command buttons
     *
     * @return viewBand
     */
    private JRibbonBand getViewBand() {
        JRibbonBand viewBand = new JRibbonBand(
                I18n.COMMON.getString("AppView.ViewBand"),
                new EmptyResizableIcon(22));
        viewBand.setResizePolicies(
                (List)Arrays.asList(new CoreRibbonResizePolicies.None(viewBand.getControlPanel())
                        , new IconRibbonBandResizePolicy(viewBand.getControlPanel())));
        JCommandToggleButton cbtnStatusBar = new JCommandToggleButton(
                I18n.COMMON.getString("Action.StatusBar"),
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS22 + "statusbar.png"))));
        cbtnStatusBar.addActionListener((ActionEvent e) -> {
            onToogleStatusBar();
        });
        cbtnStatusBar.setActionKeyTip("S");
        cbtnStatusBar.getActionModel().setSelected(true);
        viewBand.addCommandButton(cbtnStatusBar, RibbonElementPriority.TOP);

        JCommandButton cbtnLookAndFeel = new JCommandButton(
                I18n.COMMON.getString("Action.LookAndFeel"),
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS22 + "laf.png"))));
        cbtnLookAndFeel.addActionListener((ActionEvent e) -> {
            onLookAndFeel();
        });
        cbtnLookAndFeel.setActionKeyTip("T");
        viewBand.addCommandButton(cbtnLookAndFeel, RibbonElementPriority.TOP);

        return viewBand;
    }

    /**
     * Extras menu band and command buttons
     *
     * @return extrasBand
     */
    private JRibbonBand getExtrasBand() {
        JRibbonBand extrasBand = new JRibbonBand(
                I18n.COMMON.getString("AppView.ExtrasBand"),
                new EmptyResizableIcon(22));
        extrasBand.setResizePolicies(
                (List)Arrays.asList(new CoreRibbonResizePolicies.None(extrasBand.getControlPanel())
                        , new IconRibbonBandResizePolicy(extrasBand.getControlPanel())));

        JCommandButton cbtnSettings = new JCommandButton(
                I18n.COMMON.getString("Action.Settings"),
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS22 + "settings.png"))));
        cbtnSettings.addActionListener((ActionEvent e) -> {
            onSettings();
        });
        cbtnSettings.setActionKeyTip("E");
        extrasBand.addCommandButton(cbtnSettings, RibbonElementPriority.TOP);

        return extrasBand;
    }

    /**
     * Help menu band and command buttons
     *
     * @return helpBand
     */
    private JRibbonBand getHelpBand() {
        JRibbonBand helpBand = new JRibbonBand(
                I18n.COMMON.getString("AppView.HelpBand"),
                new EmptyResizableIcon(22));
        helpBand.setResizePolicies(
                (List)Arrays.asList(new CoreRibbonResizePolicies.None(helpBand.getControlPanel())
                        , new IconRibbonBandResizePolicy(helpBand.getControlPanel())));

        JCommandButton cbtnHelp = new JCommandButton(
                I18n.COMMON.getString("AppView.Help"),
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS22 + "help.png"))));
        cbtnHelp.addActionListener((ActionEvent e) -> {
            onHelp();
        });
        cbtnHelp.setActionKeyTip("H");
        helpBand.addCommandButton(cbtnHelp, RibbonElementPriority.TOP);

        JCommandButton cbtnAbout = new JCommandButton(
                I18n.COMMON.getString("AppView.Help.About"),
                ViewHelpers.createResizableIcon(new ImageIcon(getClass().getResource(
                                        ViewHelpers.ICONS22 + "about.png"))));
        cbtnAbout.addActionListener((ActionEvent e) -> {
            onAbout();
        });
        cbtnAbout.setActionKeyTip("A");
        helpBand.addCommandButton(cbtnAbout, RibbonElementPriority.TOP);

        return helpBand;
    }

    /**
     * Create XStatusBar with user name and date
     */
    private JXStatusBar buildStatusBar() {
        xstatusBar = new JXStatusBar();
        xstatusBar.setPreferredSize(new Dimension(15, 20));

        lblUser = new JLabel();
        lblUser.setText(I18n.COMMON.getString("AppView.StatusBar.User") + " : Admin User ");
        xstatusBar.add(lblUser, new JXStatusBar.Constraint(400));

        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("EEEE', 'dd. MMMM yyyy");
        JLabel lblDate = new JLabel(" " + sdf.format(new Date()));
        xstatusBar.add(lblDate, new JXStatusBar.Constraint(300));
        xstatusBar.setEnabled(isAuthorize);
        return xstatusBar;
    }       
     
    /**
     * Huy Nguyen, 2016-05-22, Set permission     
     * @param employee
     */
    public void setPermission(Employee employee){
        isAuthorize = employee != null;        
                
        if (employee == null) return;
        Collection<Permission> permissions = employee.getRole() != null 
                ? employee.getRole().getPermissions() 
                : new ArrayList<Permission>();
        
        setUserInfo(false
                , null != employee ? employee.getFullName(): ""
                , isAuthorize
                , (hasMemberPermission = containsName((List<Permission>) permissions, I18n.CUSTOMERS.getString("App.Session.HasMemberPermission")))
                , (hasMenuPermission = containsName((List<Permission>) permissions, I18n.CUSTOMERS.getString("App.Session.HasMenuPermission")))
                , (hasTablePermission = containsName((List<Permission>) permissions, I18n.CUSTOMERS.getString("App.Session.HasTablePermission")))
                , (hasCategoryPermission = containsName((List<Permission>) permissions, I18n.CUSTOMERS.getString("App.Session.HasCategoryPermission")))
                , (hasFoodPermission = containsName((List<Permission>) permissions, I18n.CUSTOMERS.getString("App.Session.HasFoodPermission")))
                , (hasRolePermission = containsName((List<Permission>) permissions, I18n.CUSTOMERS.getString("App.Session.HasRolePermission")))
                , (hasPermissionPermission = containsName((List<Permission>) permissions, I18n.CUSTOMERS.getString("App.Session.HasPermissionPermission")))
                , (hasItemPermission = containsName((List<Permission>) permissions, I18n.CUSTOMERS.getString("App.Session.HasItemPermission")))
                , (hasShiftPermission = containsName((List<Permission>) permissions, I18n.CUSTOMERS.getString("App.Session.HasShiftPermission")))
                , (hasSchedulePermission = containsName((List<Permission>) permissions, I18n.CUSTOMERS.getString("App.Session.HasSchedulePermission")))
                , (hasCPPermission = containsName((List<Permission>) permissions, I18n.CUSTOMERS.getString("App.Session.HasCPPermission")))
                , (hasBillPermission = containsName((List<Permission>) permissions, I18n.CUSTOMERS.getString("App.Session.HasBillPermission"))));                
        
        setEnableMemberBand();
        setEnableFoodBand();
        setEnableEquipmentBand();
        setEnabledApplicationMenu();
    }
    public boolean containsName(final List<Permission> list, final String name){
        return list != null && list.stream().filter(o -> o.getName().equals(name)).findFirst().isPresent();
    }
     
    /**
     * Huy Nguyen, 2016-05-22, enable or disable a container
     * Use for check permission
     * @param container
     * @param enable
     */
    public void enableComponents(Container container, boolean enable) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(enable);
            if (component instanceof Container) {
                enableComponents((Container)component, enable);
            }
        }
    }
    
    /**
     * Huy Nguyen, 2016-05-22, Set user login info into session
     */
    private void setUserInfo(boolean isClearSession,
            String userName,
            boolean isAuthorized,
            boolean memberPermission,
            boolean menuPermission,
            boolean tablePermission,
            boolean categoryPermission,
            boolean foodPermission,
            boolean rolePermission,
            boolean permissionPermission,
            boolean itemPermission,
            boolean shiftPermission,
            boolean schedulePermission,
            boolean cpPermission,
            boolean billPermission){
        if(isClearSession){
            prefs.remove(I18n.CUSTOMERS.getString("App.Session.UserName"));
            prefs.remove(I18n.CUSTOMERS.getString("App.Session.IsAuthorized"));
            prefs.remove(I18n.CUSTOMERS.getString("App.Session.HasMemberPermission"));
            prefs.remove(I18n.CUSTOMERS.getString("App.Session.HasMenuPermission"));
            prefs.remove(I18n.CUSTOMERS.getString("App.Session.HasTablePermission"));
            prefs.remove(I18n.CUSTOMERS.getString("App.Session.HasCategoryPermission"));
            prefs.remove(I18n.CUSTOMERS.getString("App.Session.HasFoodPermission"));
            prefs.remove(I18n.CUSTOMERS.getString("App.Session.HasRolePermission"));
            prefs.remove(I18n.CUSTOMERS.getString("App.Session.HasPermissionPermission"));
            prefs.remove(I18n.CUSTOMERS.getString("App.Session.HasItemPermission"));
            prefs.remove(I18n.CUSTOMERS.getString("App.Session.HasShiftPermission"));            
            prefs.remove(I18n.CUSTOMERS.getString("App.Session.HasSchedulePermission"));
            prefs.remove(I18n.CUSTOMERS.getString("App.Session.HasCPPermission"));
            prefs.remove(I18n.CUSTOMERS.getString("App.Session.HasBillPermission"));                                   
            lblUser.setText(I18n.COMMON.getString("AppView.StatusBar.User") + " : Admin User");          
        }
        else{
            lblUser.setText(I18n.COMMON.getString("AppView.StatusBar.User") + " : " + userName);
            prefs.put(I18n.CUSTOMERS.getString("App.Session.UserName"), userName);
            prefs.putBoolean(I18n.CUSTOMERS.getString("App.Session.IsAuthorized"), isAuthorized);
            prefs.putBoolean(I18n.CUSTOMERS.getString("App.Session.HasMemberPermission"), memberPermission);
            prefs.putBoolean(I18n.CUSTOMERS.getString("App.Session.HasMenuPermission"), menuPermission);
            prefs.putBoolean(I18n.CUSTOMERS.getString("App.Session.HasTablePermission"), tablePermission);
            prefs.putBoolean(I18n.CUSTOMERS.getString("App.Session.HasCategoryPermission"), categoryPermission);
            prefs.putBoolean(I18n.CUSTOMERS.getString("App.Session.HasFoodPermission"), foodPermission);
            prefs.putBoolean(I18n.CUSTOMERS.getString("App.Session.HasRolePermission"), rolePermission);
            prefs.putBoolean(I18n.CUSTOMERS.getString("App.Session.HasPermissionPermission"), permissionPermission);
            prefs.putBoolean(I18n.CUSTOMERS.getString("App.Session.HasItemPermission"), itemPermission);
            prefs.putBoolean(I18n.CUSTOMERS.getString("App.Session.HasShiftPermission"), shiftPermission);
            prefs.putBoolean(I18n.CUSTOMERS.getString("App.Session.HasSchedulePermission"), schedulePermission);
            prefs.putBoolean(I18n.CUSTOMERS.getString("App.Session.HasCPPermission"), cpPermission);
            prefs.putBoolean(I18n.CUSTOMERS.getString("App.Session.HasBillPermission"), billPermission);
        }
        setEnableMemberBand();
        setEnableFoodBand();
        setEnableEquipmentBand();
        setEnabledApplicationMenu();
        setEnableLogoutBand();
    }
}