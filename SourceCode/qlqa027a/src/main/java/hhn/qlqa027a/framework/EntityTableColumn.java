/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.framework;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Entity table column model
 *
 * @author Cem Ikta
 */
public class EntityTableColumn {

    private static final Logger LOGGER = Logger.getLogger(EntityTableColumn.class.getName());

    /**
     * Column's title.
     */
    private String title;

    /**
     * Column's field name. 
     * This is the field name of entity model field.
     */
    private String fieldName;

    /**
     * Column's class.
     */
    private Class classType;

    /**
     * Column's width in JXTable.
     */
    private int width;

    /**
     * Column's editable propety for JXTable settings.
     */
    private boolean editable;

    /**
     * Column's visible property for JXTable settings.
     */
    private boolean visible;

    /**
     * Create Entity Table Column.
     *
     * @param title column's title
     * @param fieldName column's field name
     * @param classType column's class type
     * @param width column's width
     */
    public EntityTableColumn(String title, String fieldName, Class classType, int width) {
        this(title, fieldName, classType, width, false, true);
    }

    /**
     * Create Entity Table Column.
     *
     * @param title column's title
     * @param fieldName column's field name
     * @param classType column's class type
     * @param width column's width
     * @param editable is this column editable
     * @param visible is this column visible
     */
    public EntityTableColumn(String title, String fieldName, Class classType,
            int width, boolean editable, boolean visible) {
        this.title = title;
        this.fieldName = fieldName;
        this.classType = classType;
        this.width = width;
        this.editable = editable;
        this.visible = visible;
    }

    /**
     * Gets this column's data value.
     *
     * @param entity entity model for this column's value
     * @return column's data value
     */
    public Object getValue(Object entity) {
        try {
            Field field = entity.getClass().getDeclaredField(getFieldName());
            field.setAccessible(true);

            try {
                return field.get(entity);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Sets this column's data
     *
     * @param entity entity object
     * @param value value object
     */
    public void setValue(Object entity, Object value) {
        try {
            Field field = entity.getClass().getDeclaredField(getFieldName());
            field.setAccessible(true);
            field.set(entity, value);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Gets title
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets field name
     *
     * @return fieldName
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Sets field name
     *
     * @param fieldName
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * Gets class type
     *
     * @return classType
     */
    public Class getClassType() {
        return classType;
    }

    /**
     * Sets class type.
     *
     * @param classType
     */
    public void setClassType(Class classType) {
        this.classType = classType;
    }

    /**
     * Gets width
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets width
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * is this column editable?
     *
     * @return editable
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * Sets editable
     *
     * @param editable
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    /**
     * Is this column visible?
     *
     * @return visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Sets visible
     *
     * @param visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}
