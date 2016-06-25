/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.component;

import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * JTextFieldExt Component.
 *
 * <p>
 *      JTextField with max lenght property. <br/>
 * </p>
 *
 * <p>
 * <pre><code>
 *      JTextFieldExt tfxName = new JTextFieldExt(50);
 * </code></pre>
 * </p>
 *
 * @author Cem Ikta
 */
public class JTextFieldExt extends JTextField {

    /**
     * max lenght property
     */
    private int maxLength;

    /**
     * Constructor with max lenght property
     *
     * @param maxLength max lenght property
     */
    public JTextFieldExt(int maxLength) {
        super();
        this.maxLength = maxLength;
        this.setDocument(new PlainDocumentExt(maxLength));
    }

    /**
     * Gets max length
     *
     * @return maxLength
     */
    public int getMaxLength() {
        return maxLength;
    }

    /**
     * Sets max length
     *
     * @param maxLength max length property
     */
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
        this.setDocument(new PlainDocumentExt(maxLength));
    }

}

/*
 * Plain document with max length
 */
class PlainDocumentExt extends PlainDocument {

    private final int maxLength;

    public PlainDocumentExt(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet a)
            throws BadLocationException {

        if (str.length() == 0) {
            return;
        }

        if (getLength() + str.length() <= maxLength) {
            super.insertString(offset, str, a);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }

}