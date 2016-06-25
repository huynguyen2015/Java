/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.controller;

import hhn.qlqa027a.component.Splash;
import hhn.qlqa027a.util.I18n;
import hhn.qlqa027a.util.JpaUtil;
import hhn.qlqa027a.GUI.AppView;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jvnet.lafwidget.LafWidget;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.api.SubstanceConstants.TabContentPaneBorderKind;
import org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel;

/**
 * Application Controller
 *
 * @author Cem Ikta
 */
public abstract class AppController {

    private final static Logger LOGGER = Logger.getLogger(AppController.class.getName());

    protected static AppController controller;
    protected AppView appView;
    private Splash splash;
    private Locale appLocale;

    public AppController() {
    }

    /**
     * Start the application and show the application view.
     */
    public void start() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        try {
            UIManager.setLookAndFeel(new SubstanceOfficeSilver2007LookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            LOGGER.log(Level.SEVERE, "Substance Look and Feel Error", ex);
        }

        // TabbedPane border settings in substance look and feel. 
        UIManager.put(SubstanceLookAndFeel.TABBED_PANE_CONTENT_BORDER_KIND,
                TabContentPaneBorderKind.DOUBLE_PLACEMENT);

        // Cut, copy, paste menu in TextField with substance look and feel. 
        UIManager.put(LafWidget.TEXT_EDIT_CONTEXT_MENU, true);

        if (splash != null) {
            splash.setVisible(false);
            splash.dispose();
        }

        appView = new AppView(I18n.CUSTOMERS.getString("App.Title"));
        appView.setVisible(true);
    }

    /**
     * Gets application controller.
     *
     * @return app controller
     */
    public static AppController get() {
        return controller;
    }

    /**
     * Gets application view.
     *
     * @return app view
     */
    public AppView getAppView() {
        return appView;
    }

    /**
     * Sets application splash form
     *
     * @param splash splash form.
     */
    public void setSplash(Splash splash) {
        this.splash = splash;
    }

    /**
     * Application exit.
     */
    public void exit() {
        JpaUtil.closeEntityManagerFactory();
        System.exit(0);
    }

}