/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Icon Label component.
 *
 * @author Cem Ikta
 */
public class IconLabel extends JLabel {

    /**
     * Creates IconLabel.
     *
     * <p>
     *  Default icon on the left side and text on the right side.
     * </p>
     *
     * @param icon image icon of label
     */
    public IconLabel(ImageIcon icon) {
        this(icon, "", JLabel.CENTER, JLabel.RIGHT);
    }

    /**
     * Creates IconLabel.
     *
     * @param icon image icon of label
     * @param verticalTextPos vertical text position
     * @param horizontalTextPos horizontal text position
     */
    public IconLabel(ImageIcon icon, int verticalTextPos, int horizontalTextPos) {
        this(icon, "", verticalTextPos, horizontalTextPos);
    }

    /**
     * Creates IconLabel
     *
     * @param icon image icon of label
     * @param text text of label
     * @param verticalTextPos vertical text position
     * @param horizontalTextPos horizontal text position
     */
    public IconLabel(ImageIcon icon, String text, int verticalTextPos, int horizontalTextPos) {
        super(text, icon, SwingConstants.CENTER);

        setVerticalTextPosition(verticalTextPos);
        setHorizontalTextPosition(horizontalTextPos);
        setIconTextGap(10);
    }

}