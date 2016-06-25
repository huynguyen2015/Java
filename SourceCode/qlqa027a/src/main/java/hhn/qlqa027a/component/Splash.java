/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 * Splash Form Component.
 * 
 * <p>
 * <pre><code>
 *  Splash splash = new Splash(I18n.COMMON.getString("App.Title"), 
 *      ViewHelpers.ICONS16 + "app.png", 
 *      ViewHelpers.IMAGES + "splash.png");
 * </code></pre>
 * </p>
 *
 * @author Cem Ikta
 */
public class Splash extends JFrame {

    /**
     * Title of Splash form.
     */
    private final String title;
    
    /**
     * Icon path of Splash form.
     */
    private final String iconPath;
    
    /**
     * Image path of Splash form.
     */
    private final String imagePath;

    /**
     * Create splash form
     * 
     * @param title title of splash form for taskbar
     * @param iconPath icon path for taskbar
     * @param imagePath image path for splash form
     */
    public Splash(String title, String iconPath, String imagePath) {
        this.title = title;
        this.iconPath = iconPath;
        this.imagePath = imagePath;

        initComponents();
    }

    /**
     * init components
     */
    private void initComponents() {
        setTitle(title);
        setIconImage(new ImageIcon(getClass().getResource(iconPath)).getImage());
        setResizable(false);
        setUndecorated(true);

        JLabel imgSplash = new JLabel(new ImageIcon(getClass().getResource(imagePath)));
        
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setIndeterminate(true);
        progressBar.setPreferredSize(new Dimension(7, 7));
        progressBar.setBackground(new Color(165, 196, 238));
        progressBar.setForeground(new Color(243, 179, 69));

        getContentPane().add(imgSplash, BorderLayout.CENTER);
        getContentPane().add(progressBar, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
    }

}