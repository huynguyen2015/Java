/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */

package hhn.qlqa027a.framework;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Custom Table Model
 *
 * @author Cem Ikta
 */
public class EntityTableModel extends AbstractTableModel {

    /**
     * Table model columns
     */
    private List columns;
    
    /**
     * Table model data
     */
    private List data;

    /**
     * Default constructor
     */
    public EntityTableModel() {
        this(new ArrayList());
    }

    /**
     * Contructor with data
     * 
     * @param data table model's data 
     */
    public EntityTableModel(List data) {
        this.data = data;
        this.columns = new ArrayList();
    }

    /**
     * Add column to columns list
     * 
     * @param column 
     */
    private void addColumnToList(EntityTableColumn column) {
        if (!columns.contains(column)) {
            columns.add(column);
        }
    }

    /**
     * Create column and add to table model
     * 
     * @param column entity table column
     */
    public void addColumn(EntityTableColumn column) {
        addColumnToList(column);
    }

    /**
     * Gets column from index
     * 
     * @param index column index
     * @return entity table column 
     */
    public EntityTableColumn getColumn(int index) {
        return (EntityTableColumn) columns.get(index);
    }

    /**
     * Gets row count 
     * 
     * @return row count
     */
    @Override
    public int getRowCount() {
        return data.size();
    }

    /**
     * Gets column count
     * 
     * @return column count 
     */
    @Override
    public int getColumnCount() {
        return columns.size();
    }

    /**
     * Gets column's value
     * 
     * @param rowIndex row index
     * @param columnIndex column index
     * @return column data
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        EntityTableColumn column = getColumn(columnIndex);
        Object obj = data.get(rowIndex);
        
        return column.getValue(obj);
    }

    /**
     * Sets column's value
     * 
     * @param value
     * @param rowIndex
     * @param columnIndex 
     */
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        EntityTableColumn column = getColumn(columnIndex);
        Object obj = data.get(rowIndex);
        column.setValue(obj, value);
    }

    /**
     * Editable property of table column.
     * 
     * @param rowIndex row index
     * @param columnIndex column index
     * @return true if this column is editable
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return getColumn(columnIndex).isEditable();
    }

    /**
     * Gets column name
     * 
     * @param columnIndex
     * @return 
     */
    @Override
    public String getColumnName(int columnIndex) {
        return getColumn(columnIndex).getTitle();
    }

    /**
     * Gets column class
     * 
     * @return 
     */
    @Override
    public Class getColumnClass(int columnIndex) {
        return getColumn(columnIndex).getClassType();
    }

    /**
     * Gets data in this row index
     * 
     * @param rowIndex
     * @return 
     */
    public Object getRowAt(int rowIndex) {
        return data.get(rowIndex);
    }

    /**
     * Gets table model data
     * 
     * @return data 
     */
    public List getData() {
        return data;
    }

    /**
     * Sets table model data
     * 
     * @param data table model data
     */
    public void setData(List data) {
        this.data = data;
    }
    
}
