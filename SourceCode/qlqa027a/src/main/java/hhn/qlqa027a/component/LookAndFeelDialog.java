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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.MatteBorder;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXHeader;
import org.jdesktop.swingx.JXList;
import org.jdesktop.swingx.decorator.HighlighterFactory;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.SubstanceCremeCoffeeLookAndFeel;
import org.jvnet.substance.skin.SubstanceCremeLookAndFeel;
import org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel;
import org.jvnet.substance.skin.SubstanceModerateLookAndFeel;
import org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel;
import org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel;

/**
 * Look And Feel Dialog
 *
 * @author Cem Ikta
 */
public class LookAndFeelDialog extends JDialog {

    private JXHeader xheader;
    private JButton btnOk;
    private JButton btnCancel;
    private Action acOk;
    private Action acCancel;
    private JXList xlist;

    public LookAndFeelDialog(JFrame parent) {
        super(parent);
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * Build UI
     */
    @SuppressWarnings("serial")
    private void initComponents() {
        setLayout(new MigLayout());
        setTitle(I18n.COMPONENT.getString("LookAndFeelDialog.Title"));
        setIconImage(new ImageIcon(getClass().getResource(
                ViewHelpers.ICONS16 + "laf.png")).getImage());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        xheader = new JXHeader();
        xheader.setTitle("<html><body><b>"
                + I18n.COMPONENT.getString("LookAndFeelDialog.Header.Title")
                + "</b></body></html>");
        xheader.setDescription(I18n.COMPONENT.getString("LookAndFeelDialog.Header.Description"));
        xheader.setFont(new Font("Tahoma", 0, 12));
        xheader.setIcon(new ImageIcon(getClass().getResource(
                ViewHelpers.ICONS22 + "laf.png")));
        xheader.setBorder(new MatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        acOk = new AbstractAction(I18n.COMMON.getString("Action.Ok"),
                new ImageIcon(getClass().getResource(ViewHelpers.ICONS16 + "ok.png"))) {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        apply();
                        setVisible(false);
                        dispose();
                    }
                };
        btnOk = new JButton(acOk);

        acCancel = new AbstractAction(I18n.COMMON.getString("Action.Cancel"),
                new ImageIcon(getClass().getResource(ViewHelpers.ICONS16 + "cancel.png"))) {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        setVisible(false);
                        dispose();
                    }
                };
        btnCancel = new JButton(acCancel);

        JPanel buttonPanel = new JPanel(new MigLayout("nogrid, fillx, aligny 100%, gapy unrel"));
        buttonPanel.add(btnCancel, "tag cancel");
        buttonPanel.add(btnOk, "tag ok");

        JPanel buttonBar = new JPanel(new BorderLayout());
        buttonBar.add(new JSeparator(), BorderLayout.NORTH);
        buttonBar.add(buttonPanel, BorderLayout.CENTER);

        getContentPane().add(xheader, "dock north");
        getContentPane().add(buildCenterPanel(), "dock center");
        getContentPane().add(buttonBar, "dock south");

        pack();
        setSize(400, 400);
        setModal(true);
        btnOk.requestFocus();
    }

    /**
     * Set selected look and feel
     */
    private void apply() {
        LafItem lafItem = null;
        if (xlist.getSelectedValue() != null) {
            lafItem = (LafItem) xlist.getSelectedValue();
        }

        try {
            UIManager.setLookAndFeel(lafItem.getLafObject().getClass().getName());
        } catch (Exception ex) {
            MessageBox.showError(I18n.COMPONENT.getString("LookAndFeelDialog.LafError"), ex);
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

    /**
     * Center Panel
     */
    private JPanel buildCenterPanel() {
        JPanel panel = new JPanel(new MigLayout("insets 10 20 10 20"));
        JLabel lblSkins = new JLabel(I18n.COMPONENT.getString("LookAndFeelDialog.Themes"));
        ImageIcon lafIcon = new ImageIcon(getClass().getResource(
                ViewHelpers.ICONS16 + "laf.png"));

        DefaultListModel<LafItem> listModel = new DefaultListModel();
        listModel.addElement(
                new LafItem(lafIcon, new SubstanceOfficeSilver2007LookAndFeel()));
        listModel.addElement(
                new LafItem(lafIcon, new SubstanceOfficeBlue2007LookAndFeel()));
        listModel.addElement(
                new LafItem(lafIcon, new SubstanceCremeLookAndFeel()));
        listModel.addElement(
                new LafItem(lafIcon, new SubstanceCremeCoffeeLookAndFeel()));
        listModel.addElement(
                new LafItem(lafIcon, new SubstanceModerateLookAndFeel()));
        listModel.addElement(
                new LafItem(lafIcon, new SubstanceMistAquaLookAndFeel()));

        xlist = new JXList(listModel);
        xlist.setCellRenderer(new LafItemCellRenderer());
        xlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        xlist.setHighlighters(HighlighterFactory.createSimpleStriping(
                HighlighterFactory.BEIGE));
        xlist.setSelectedIndex(0);
        // add double click event
        xlist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    btnOk.doClick();
                }
            }
        });

        JScrollPane sp = new JScrollPane(xlist);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.setPreferredSize(new Dimension(350, 400));

        panel.add(lblSkins, "wrap");
        panel.add(sp, "wrap");

        return panel;
    }

    /**
     * Create and show dialog
     */
    public static void showDialog() {
        LookAndFeelDialog dialog = new LookAndFeelDialog(
                AppController.get().getAppView());
        dialog.setVisible(true);
    }

    /**
     * Laf Item class for JXList
     */
    class LafItem {

        private final ImageIcon icon;
        private final String title;
        private final SubstanceLookAndFeel lafObject;

        /**
         * Laf item
         *
         * @param icon look and feel icon
         * @param lafObject look and feel object
         */
        public LafItem(ImageIcon icon, SubstanceLookAndFeel lafObject) {
            this.icon = icon;
            this.lafObject = lafObject;
            this.title = lafObject.getName();
        }

        public ImageIcon getIcon() {
            return icon;
        }

        public String getTitle() {
            return title;
        }

        public SubstanceLookAndFeel getLafObject() {
            return lafObject;
        }

        @Override
        public String toString() {
            return title;
        }
    }

    /**
     * CellRenderer for JXList
     */
    class LafItemCellRenderer extends JLabel implements ListCellRenderer {

        private static final long serialVersionUID = 1L;

        public LafItemCellRenderer() {
            setOpaque(true);
            setIconTextGap(10);
        }

        @Override
        public Component getListCellRendererComponent(JList list,
                Object value, int index, boolean isSelected,
                boolean cellHasFocus) {

            LafItem lafItem = (LafItem) value;
            setIcon(lafItem.getIcon());
            setText(lafItem.getTitle());

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            return this;
        }
    }

}