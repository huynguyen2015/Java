/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */

package hhn.qlqa027a.framework;

import hhn.qlqa027a.entities.BaseEntity;
import hhn.qlqa027a.service.AbstractService;
import java.util.List;

/**
 * Data Page Controller interface.
 * 
 * @param <T> entity
 * 
 * @author Cem Ikta
 */
public interface DataPageController<T extends BaseEntity> extends Controller {
    
    /**
     * Gets service of this controller
     * 
     * @return service  
     */
    AbstractService<T> getService();
    
    /**
     * Gets data page view
     * 
     * @return data page view 
     */
    DataPageView<T> getDataPageView();
    
    /**
     * Open form view
     * 
     * @param entity form view's entity object 
     */
    void openFormView(T entity);
    
    /**
     * Add new action 
     */
    void onAddNew();
    
    /**
     * Edit action
     */
    void onEdit();
    
    /**
     * Delete action
     */
    void onDelete();
    
    /**
     * Refresh action
     */
    void onRefresh();
    
    /**
     * Save action
     * 
     * @param entity to save  
     */
    void onSave(T entity);
    
    /**
     * Gets data list for JXTable
     * 
     * @param filter filter for data list
     * @param start start for paging
     * @param end end for paging
     * @return data list
     */
    List<T> getData(String filter, int start, int end);
    
    /**
     * Gets data size
     * 
     * @param filter filter for data list
     * @return data record size
     */
    int getDataSize(String filter);
    
    /**
     * Gets named query
     * 
     * @return named query in entity 
     */
    String getNamedQuery();
    
    /**
     * Gets named query with filter
     * 
     * @return named query with filter in entity 
     */
    String getNamedQueryWithFilter();
    
}
