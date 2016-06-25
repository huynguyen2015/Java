/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.framework;

/**
 * Page View interface
 *
 * @author Cem Ikta
 */
public interface PageView extends View {   
    /**
     * Page view init
     *
     * @param controller page view's controller
     */
    void init(PageController controller);
    
    /**
     * Gets controller
     *
     * @return page controller
     */
    PageController getController();
    
}
