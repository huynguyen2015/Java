/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhn.qlqa027a.entities;

import java.util.Collection;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Huy
 */
@Entity
@Table(name = "status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Status.findAll", query = "SELECT s FROM Status s"),
    @NamedQuery(name = "Status.findById", query = "SELECT s FROM Status s WHERE s.id = :id"),
    @NamedQuery(name = "Status.findByName", query = "SELECT s FROM Status s WHERE s.name = :name")})
@AttributeOverride(name = "id", column = @Column(name = "Id",
        nullable = false, columnDefinition = "BIGINT UNSIGNED"))
public class Status extends BaseEntity {

    public static final String FIND_ALL = "Status.findAll";
    @Column(name = "Name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusId")
    private Collection<Billdetail> billdetailCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusId")
    private Collection<Bill> billCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
    private Collection<Employee> employeeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
    private Collection<Food> foodCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
    private Collection<Schedule> scheduleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
    private Collection<Stable> stableCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusId")
    private Collection<Employeeitems> employeeitemsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
    private Collection<Category> categoryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
    private Collection<Shift> shiftCollection;    
    
    public Status() {
    }
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Billdetail> getBilldetailCollection() {
        return billdetailCollection;
    }

    public void setBilldetailCollection(Collection<Billdetail> billdetailCollection) {
        this.billdetailCollection = billdetailCollection;
    }

    @XmlTransient
    public Collection<Bill> getBillCollection() {
        return billCollection;
    }

    public void setBillCollection(Collection<Bill> billCollection) {
        this.billCollection = billCollection;
    }

    @XmlTransient
    public Collection<Employee> getEmployeeCollection() {
        return employeeCollection;
    }

    public void setEmployeeCollection(Collection<Employee> employeeCollection) {
        this.employeeCollection = employeeCollection;
    }

    @XmlTransient
    public Collection<Food> getFoodCollection() {
        return foodCollection;
    }

    public void setFoodCollection(Collection<Food> foodCollection) {
        this.foodCollection = foodCollection;
    }

    @XmlTransient
    public Collection<Schedule> getScheduleCollection() {
        return scheduleCollection;
    }

    public void setScheduleCollection(Collection<Schedule> scheduleCollection) {
        this.scheduleCollection = scheduleCollection;
    }

    @XmlTransient
    public Collection<Stable> getStableCollection() {
        return stableCollection;
    }

    public void setStableCollection(Collection<Stable> stableCollection) {
        this.stableCollection = stableCollection;
    }

    @XmlTransient
    public Collection<Employeeitems> getEmployeeitemsCollection() {
        return employeeitemsCollection;
    }

    public void setEmployeeitemsCollection(Collection<Employeeitems> employeeitemsCollection) {
        this.employeeitemsCollection = employeeitemsCollection;
    }

    @XmlTransient
    public Collection<Category> getCategoryCollection() {
        return categoryCollection;
    }

    public void setCategoryCollection(Collection<Category> categoryCollection) {
        this.categoryCollection = categoryCollection;
    }
    
    @XmlTransient
    public Collection<Shift> getShiftCollection() {
        return shiftCollection;
    }

    public void setShiftCollection(Collection<Shift> shiftCollection) {
        this.shiftCollection = shiftCollection;
    }

    @Override
    public String toString() {
        return this.name;
    }   
    
}
