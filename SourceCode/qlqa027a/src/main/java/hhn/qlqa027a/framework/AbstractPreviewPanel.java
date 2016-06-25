/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.framework;

import hhn.qlqa027a.entities.BaseEntity;
import hhn.qlqa027a.util.I18n;
import hhn.qlqa027a.util.ViewHelpers;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import org.jvnet.substance.api.ColorSchemeAssociationKind;
import org.jvnet.substance.api.ComponentState;
import org.jvnet.substance.api.SubstanceColorScheme;
import org.jvnet.substance.utils.SubstanceColorSchemeUtilities;

/**
 * Preview panel for page views.
 *
 * @param <T> entity
 *
 * @author Cem Ikta
 */
public abstract class AbstractPreviewPanel<T extends BaseEntity> extends JPanel {

    private T entity;

    /**
     * Creates preview panel
     */
    public AbstractPreviewPanel() {
        initComponents();
    }

    /**
     * Sets entity data model of preview panel.
     *
     * @param entity data model
     */
    public void setEntity(T entity) {
        this.entity = entity;
        popFields();
    }

    /**
     * Gets data object of preview panel.
     *
     * @return entity
     */
    public T getEntity() {
        return entity;
    }

    /**
     * Preview panel ui process.
     */
    public abstract void buildUI();

    /**
     * Set data of preview fields.
     */
    public abstract void popFields();

    /**
     * init components.
     */
    private void initComponents() {
        setLayout(new BorderLayout());
        setFocusable(false);

        SubstanceColorScheme borderColorScheme = SubstanceColorSchemeUtilities.getColorScheme(
                this, ColorSchemeAssociationKind.BORDER, ComponentState.DEFAULT);
        setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1,
                borderColorScheme.getDarkColor()));

        JToolBar tbHeaderBar = new JToolBar();
        tbHeaderBar.setPreferredSize(new Dimension(150, 25));

        JLabel lblCaption = new JLabel(I18n.COMMON.getString("AbstractPreviewPanel.Title"));
        lblCaption.setFont(lblCaption.getFont().deriveFont(Font.BOLD, 14));
        lblCaption.setIcon(new ImageIcon(getClass().getResource(
                ViewHelpers.ICONS16 + "preview.png")));

        tbHeaderBar.addSeparator();
        tbHeaderBar.add(lblCaption);
        tbHeaderBar.setFloatable(false);
        tbHeaderBar.setRollover(true);
        tbHeaderBar.setFocusable(false);

        add(tbHeaderBar, BorderLayout.NORTH);
        buildUI();
    }

    /**
     * Center component of the preview panel.
     *
     * @param component center component of preview panel.
     */
    public void addCenterComponent(JComponent component) {
        add(new JScrollPane(component), BorderLayout.CENTER);
        setPreferredSize(component.getPreferredSize());
    }

}
