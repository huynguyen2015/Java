/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.framework;

import hhn.qlqa027a.entities.BaseEntity;

/**
 * Data Page View interface
 *
 * @param <T> entity
 *
 * @author Cem Ikta
 */
public interface DataPageView<T extends BaseEntity> extends View {

    /**
     * Data page view init
     *
     * @param controller data page view's controller
     */
    void init(DataPageController<T> controller);

    /**
     * Gets data page controller
     *
     * @return data page controller controller
     */
    DataPageController<T> getController();

    /**
     * Gets selected data model of data page view
     *
     * @return selected entity model
     */
    T getSelectedModel();

    /**
     * Refresh data of data page view
     */
    void refreshData();

}
