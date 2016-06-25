/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhn.qlqa027a.entities;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Huy
 */
@Entity
@Table(name = "schedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Schedule.findAll", query = "SELECT s FROM Schedule s WHERE StatusId <> 5"),
    @NamedQuery(name = "Schedule.findByFilter", query = "SELECT s FROM Schedule s "
            + "WHERE DATE(s.workingDate) LIKE :workingDate "),
    @NamedQuery(name = "Schedule.findById", query = "SELECT s FROM Schedule s WHERE s.id = :id"),
    @NamedQuery(name = "Schedule.findByWorkingDate", query = "SELECT s FROM Schedule s WHERE s.workingDate = :workingDate")})
@AttributeOverride(name = "id", column = @Column(name = "Id",
        nullable = false, columnDefinition = "BIGINT UNSIGNED"))
public class Schedule extends BaseEntity {
    
    public static final String FIND_ALL = "Schedule.findAll";
    public static final String FIND_BY_FILTER = "Schedule.findByFilter";
    
    @Column(name = "WorkingDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date workingDate;
    @JoinColumn(name = "ShiftId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Shift shift;
    @JoinColumn(name = "StatusId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Status status;

    public Schedule() {
    }
    
    public Date getWorkingDate() {
        return workingDate;
    }

    public void setWorkingDate(Date workingDate) {
        this.workingDate = workingDate;
    }     

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.workingDate.toString();
    }    
}
