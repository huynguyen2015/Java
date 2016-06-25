/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.framework;

import hhn.qlqa027a.service.AbstractService;
import hhn.qlqa027a.component.MessageBox;
import hhn.qlqa027a.entities.BaseEntity;
import hhn.qlqa027a.util.I18n;

/**
 * Abstract Data Page Controller.
 *
 * @param <T> entity
 *
 * @author Cem Ikta
 */
public abstract class AbstractDataPageController<T extends BaseEntity> 
        implements DataPageController<T> {

    private AbstractService<T> service;
    private DataPageView<T> dataPageView;

    public AbstractDataPageController() {
    }

    protected abstract AbstractService<T> createService();

    public AbstractService<T> getService() {
        if (service == null) {
            service = createService();
        }
        return service;
    }

    protected abstract DataPageView<T> createDataPageView();

    public DataPageView<T> getDataPageView() {
        if (dataPageView == null) {
            dataPageView = createDataPageView();
            dataPageView.init(this);
            dataPageView.refreshData();
        } else {
            dataPageView.refreshData();
        }
        
        return dataPageView;
    }

    public void onEdit() {
        if (dataPageView == null) {
            return;
        }
        if (dataPageView.getSelectedModel() == null) {
            return;
        }
        openFormView(dataPageView.getSelectedModel());
    }

    public void onDelete() {
        if (dataPageView == null) {
            return;
        }
        if (dataPageView.getSelectedModel() == null) {
            return;
        }
        if (MessageBox.showAskYesNo(I18n.COMMON.getString(
                "MessageBox.Confirm.Delete")) == MessageBox.YES_OPTION) {
            try {
                getService().remove(dataPageView.getSelectedModel());
                onRefresh();
            } catch (Exception e) {
                MessageBox.showError(e.getMessage());
            }
        }
    }

    public void onRefresh() {
        if (dataPageView != null) {
            dataPageView.refreshData();
        }
    }

    public void onSave(T entity) {
        try {
            if (entity.getId() == null) {
                getService().create(entity);
            } else {
                getService().update(entity);
            }

        } catch (Exception e) {
            MessageBox.showError(e.getMessage());
        }
    }

}
