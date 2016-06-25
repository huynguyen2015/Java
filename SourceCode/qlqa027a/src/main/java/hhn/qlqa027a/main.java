/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a;

import hhn.qlqa027a.component.Splash;
import hhn.qlqa027a.controller.AppController;
import hhn.qlqa027a.util.I18n;
import hhn.qlqa027a.util.ViewHelpers;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 * Customers desktop application.
 *
 * @author Cem Ikta
 */
public class main extends AppController {

    private final static Logger LOGGER = Logger.getLogger(main.class.getName());

    public static Splash splash;

    /**
     * Customers Desktop Application
     */
    public main() {

    }

    /**
     * Application main class.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // set locale for i18n
        Locale.setDefault(new Locale("en", "US"));               

        // application splash form
        splash = new Splash(I18n.CUSTOMERS.getString("App.Title"),
                ViewHelpers.ICONS16 + "app.png",
                ViewHelpers.IMAGES + "Food_Bank_icon.svg.png");
        splash.setVisible(true);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                controller = new main();
                controller.setSplash(splash);
                controller.start();
            }
        });
    }

}
