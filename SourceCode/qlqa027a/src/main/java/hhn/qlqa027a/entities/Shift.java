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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "shift")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shift.findAll", query = "SELECT s FROM Shift s WHERE s.status <> 5"),
    @NamedQuery(name = "Shift.findByFilter", query = "SELECT s FROM Shift s "
            + " WHERE s.name like :name "
            + " AND s.status <> 5"),
    
    @NamedQuery(name = "Shift.findById", query = "SELECT s FROM Shift s WHERE s.id = :id"),
    @NamedQuery(name = "Shift.findByName", query = "SELECT s FROM Shift s WHERE s.name = :name")})
@AttributeOverride(name = "id", column = @Column(name = "Id",
        nullable = false, columnDefinition = "BIGINT UNSIGNED"))
public class Shift extends BaseEntity {
    public static final String FIND_ALL = "Shift.findAll";
    public static final String FIND_BY_FILTER = "Shift.findByFilter";  
    @Column(name = "Salary")
    private Long salary;
    @Column(name = "MinServicers")
    private Short minServicers;
    @Column(name = "MaxServicers")
    private Short maxServicers;
    @Column(name = "MinBartenders")
    private Short minBartenders;
    @Column(name = "MaxBartenders")
    private Short maxBartenders;
    @Column(name = "MinManagers")
    private Short minManagers;
    @Column(name = "MaxManagers")
    private Short maxManagers;
    @JoinColumn(name = "StatusId", referencedColumnName = "Id")
    @ManyToOne(optional = false) 
    private Status status;

    @Column(name = "Name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shift")
    private Collection<Schedule> schedules;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shiftId")
    private Collection<Shiftnumbermember> shiftnumbermemberCollection;

    public Shift() {
    }  
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Collection<Schedule> schedules) {
        this.schedules = schedules;
    }

    @XmlTransient
    public Collection<Shiftnumbermember> getShiftnumbermemberCollection() {
        return shiftnumbermemberCollection;
    }

    public void setShiftnumbermemberCollection(Collection<Shiftnumbermember> shiftnumbermemberCollection) {
        this.shiftnumbermemberCollection = shiftnumbermemberCollection;
    }

    @Override
    public String toString() {
        //return "hhn.qlqa027a.entities.Shift[ id=" + super.getId() + " ]";
        return this.name;
    }    
    
    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Short getMinServicers() {
        return minServicers;
    }

    public void setMinServicers(Short minServicers) {
        this.minServicers = minServicers;
    }

    public Short getMaxServicers() {
        return maxServicers;
    }

    public void setMaxServicers(Short maxServicers) {
        this.maxServicers = maxServicers;
    }

    public Short getMinBartenders() {
        return minBartenders;
    }

    public void setMinBartenders(Short minBartenders) {
        this.minBartenders = minBartenders;
    }

    public Short getMaxBartenders() {
        return maxBartenders;
    }

    public void setMaxBartenders(Short maxBartenders) {
        this.maxBartenders = maxBartenders;
    }

    public Short getMinManagers() {
        return minManagers;
    }

    public void setMinManagers(Short minManagers) {
        this.minManagers = minManagers;
    }

    public Short getMaxManagers() {
        return maxManagers;
    }

    public void setMaxManagers(Short maxManagers) {
        this.maxManagers = maxManagers;
    }   
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
}
