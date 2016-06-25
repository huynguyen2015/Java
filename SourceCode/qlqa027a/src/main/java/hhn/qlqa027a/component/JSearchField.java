/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.component;

import hhn.qlqa027a.util.ViewHelpers;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 * JSearchField component.
 *
 * @author Cem Ikta
 */
public class JSearchField extends JPanel implements KeyListener {

    private JTextField tfSearch;
    private final Action acSearch;
    private String prompt;

    /**
     * Creates JSearchField
     *
     * @param acSearch search action
     */
    public JSearchField(Action acSearch) {
        this(acSearch, "Search");
    }

    /**
     * Creates JSearchField
     *
     * @param acSearch search action
     * @param prompt inline prompt text
     */
    public JSearchField(Action acSearch, String prompt) {
        super(new BorderLayout());
        this.acSearch = acSearch;
        this.prompt = prompt;
        initComponents();
    }

    /**
     * component init
     */
    private void initComponents() {
        ImageIcon icoSearch = new ImageIcon(getClass().getResource(
                ViewHelpers.ICONS12 + "search.png"));
        JLabel lblSearch = new JLabel();
        lblSearch.setIcon(icoSearch);
        lblSearch.setPreferredSize(new Dimension(22, 20));

        tfSearch = new JTextField(50);
        PromptSupport.setPrompt(prompt, tfSearch);
        tfSearch.addKeyListener(this);
        setBorder(tfSearch.getBorder());
        tfSearch.setBorder(null);

        add(lblSearch, BorderLayout.WEST);
        add(tfSearch, BorderLayout.CENTER);

        setPreferredSize(new Dimension(
                tfSearch.getPreferredSize().width,
                tfSearch.getPreferredSize().height + 6));
    }

    /**
     * Gets text field component of JSearchField.
     *
     * @return text field
     */
    public final JTextField getSearchTextField() {
        return tfSearch;
    }

    /**
     * Enter makes search.
     *
     * @param e KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (acSearch != null) {
                acSearch.actionPerformed(null);
            }
        }
    }

    /**
     * If search string is deleted, refresh search.
     *
     * @param e KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (acSearch != null) {
            acSearch.actionPerformed(null);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}