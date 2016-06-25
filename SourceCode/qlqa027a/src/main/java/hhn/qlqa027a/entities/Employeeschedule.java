/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhn.qlqa027a.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Huy
 */
@Entity
@Table(name = "employeeschedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employeeschedule.findAll", query = "SELECT e FROM Employeeschedule e WHERE StatusId <> 5"),
    @NamedQuery(name = "Employeeschedule.findByFilter", query = "SELECT e "
            + "FROM Employeeschedule e "
            + "LEFT JOIN e.employee em "
            + "LEFT JOIN e.schedule s "
            + "WHERE e.status.id <> 5 AND em.status.id <> 5 "
            + "AND (em.email LIKE :filter "
            + "OR em.phone LIKE :filter OR s.workingDate LIKE :filter)"),
    @NamedQuery(name = "Employeeschedule.findById", query = "SELECT e FROM Employeeschedule e WHERE e.id = :id AND StatusId <> 5"),
    @NamedQuery(name = "Employeeschedule.findByIsWorked", query = "SELECT e FROM Employeeschedule e WHERE e.isWorked = :isWorked AND StatusId <> 5"),
    @NamedQuery(name = "Employeeschedule.findByMinutesLate", query = "SELECT e FROM Employeeschedule e WHERE e.minutesLate = :minutesLate AND StatusId <> 5")})
public class Employeeschedule extends BaseEntity {

    public static final String FIND_ALL = "Employeeschedule.findAll";
    public static final String FIND_BY_FILTER = "Employeeschedule.findByFilter";
    
    @JoinColumn(name = "EmployeeId", referencedColumnName = "Id")
    @ManyToOne(optional = false) 
    private Employee employee; 
    @JoinColumn(name = "ScheduleId", referencedColumnName = "Id")
    @ManyToOne(optional = false) 
    private Schedule schedule; 
    @Column(name = "IsWorked")
    private Boolean isWorked;
    @Column(name = "MinutesLate")
    private Short minutesLate;
    @JoinColumn(name = "StatusId", referencedColumnName = "Id")
    @ManyToOne(optional = false) 
    private Status status; 

    public Employeeschedule() {
    }   

    public Boolean getIsWorked() {
        return isWorked;
    }

    public void setIsWorked(Boolean isWorked) {
        this.isWorked = isWorked;
    }

    public Short getMinutesLate() {
        return minutesLate;
    }

    public void setMinutesLate(Short minutesLate) {
        this.minutesLate = minutesLate;
    }
    
    public Status getStatus() {
        return status;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }
    
    public Employee getEmployee() {
        return employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public Schedule getSchedule() {
        return schedule;
    }
    
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
    
    public String getWorking() {
        return this.isWorked ? "Yes" : "No";
    }
    
    @Override
    public String toString() {
        return "";
    }
    
}
