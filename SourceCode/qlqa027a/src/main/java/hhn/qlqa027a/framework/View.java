/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.framework;

import java.awt.Component;

/**
 * View interface.
 * 
 * @author Cem Ikta
 */
public interface View {
    
    /**
     * Gets view's title
     *
     * @return view's title
     */
    String getTitle();
    
    /**
     * Gets view's icon path
     *
     * @return view's icon path
     */
    String getIconPath();
    
    /**
     * This view as component
     *
     * @return view as component
     */
    Component asComponent();
    
}