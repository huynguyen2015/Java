/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.component;

import hhn.qlqa027a.controller.AppController;
import hhn.qlqa027a.util.I18n;
import hhn.qlqa027a.util.ViewHelpers;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXHeader;
import org.jdesktop.swingx.JXHyperlink;

/**
 * About Dialog
 *
 * @author Cem Ikta
 */
public class AboutDialog extends JDialog {

    private JXHeader xheader;
    private JButton btnOk;
    private Action acOk;
    private Action acBlog;
    private Action acSourceCode;

    /**
     * Creates a new instance of AboutDialog
     *
     * @param parent parent component
     */
    public AboutDialog(JFrame parent) {
        super(parent);
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * Build UI components
     */
    @SuppressWarnings("serial")
    private void initComponents() {
        setTitle(I18n.COMPONENT.getString("AboutDialog.Title"));
        setIconImage(new ImageIcon(getClass()
                .getResource(ViewHelpers.ICONS16 + "app.png")).getImage());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        xheader = new JXHeader();
        xheader.setTitle("<html><body><b>"
                + I18n.COMPONENT.getString("AboutDialog.Header.Title")
                + "</b></body></html>");
        xheader.setDescription(I18n.COMPONENT.getString("AboutDialog.Header.Description"));
        xheader.setFont(new Font("Tahoma", 0, 12));
        xheader.setIcon(new ImageIcon(getClass().getResource(
                ViewHelpers.ICONS22 + "about.png")));
        xheader.setBorder(new MatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        acOk = new AbstractAction(I18n.COMMON.getString("Action.Ok"),
                new ImageIcon(getClass().getResource(ViewHelpers.ICONS16 + "ok.png"))) {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        setVisible(false);
                        dispose();
                    }
                };
        btnOk = new JButton(acOk);

        JPanel buttonPanel = new JPanel(new MigLayout("nogrid, fillx, aligny 100%, gapy unrel"));
        buttonPanel.add(btnOk, "align center");

        JPanel buttonBar = new JPanel(new BorderLayout());
        buttonBar.add(buttonPanel, BorderLayout.CENTER);

        getContentPane().add(xheader, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(buildCenterPanel()), BorderLayout.CENTER);
        getContentPane().add(buttonBar, BorderLayout.SOUTH);

        pack();
        setSize(620, 350);
        setModal(true);
        btnOk.requestFocus();
    }

    /**
     * Build center panel
     *
     * @return center panel
     */
    private JPanel buildCenterPanel() {
        buildActions();
        JPanel centerPanel = new JPanel(new MigLayout("insets 20 10 10 10"));
        JLabel lblAppName = new JLabel(
                I18n.CUSTOMERS.getString("App.Title") + " "
                + I18n.CUSTOMERS.getString("App.Version"));
        JLabel lblCopyright = new JLabel(I18n.CUSTOMERS.getString("App.Copyright"));
        JLabel lblCopyrightLink = new JLabel("www.devsniper.com.");

        JLabel lblDetails = new JLabel(I18n.CUSTOMERS.getString("App.Details"));
        JXHyperlink xlinkBlog = new JXHyperlink(acBlog);
        JXHyperlink xlinkSourceCode = new JXHyperlink(acSourceCode);
        
        JLabel lblIcons = new JLabel(I18n.CUSTOMERS.getString("App.Icons"));

        centerPanel.add(lblAppName, "wrap");
        centerPanel.add(lblCopyright, "wrap");
        centerPanel.add(lblCopyrightLink, "wrap");
        centerPanel.add(new JLabel(""), "wrap");
        centerPanel.add(lblDetails, "wrap");
        centerPanel.add(xlinkBlog, "wrap");
        centerPanel.add(xlinkSourceCode, "wrap");
        centerPanel.add(new JLabel(""), "wrap");
        centerPanel.add(lblIcons, "wrap");

        return centerPanel;
    }

    private void buildActions() {
        acBlog = new AbstractAction("http://www.devsniper.com/customers-java-swing-application-with-source-code") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewHelpers.browseUrl("http://www.devsniper.com/customers-java-swing-application-with-source-code");
            }
        };

        acSourceCode = new AbstractAction("https://bitbucket.org/devsniper/customersdesktop/") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewHelpers.browseUrl("https://bitbucket.org/devsniper/customersdesktop/");
            }
        };
    }

    /**
     * Create and show dialog
     */
    public static void showDialog() {
        AboutDialog aboutDialog = new AboutDialog(AppController.get().getAppView());
        aboutDialog.setVisible(true);
    }

}