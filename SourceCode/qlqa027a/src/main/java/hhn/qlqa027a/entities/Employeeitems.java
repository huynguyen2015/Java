/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhn.qlqa027a.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "employeeitems")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employeeitems.findAll", query = "SELECT e FROM Employeeitems e"),
    @NamedQuery(name = "Employeeitems.findById", query = "SELECT e FROM Employeeitems e WHERE e.id = :id")})
@AttributeOverride(name = "id", column = @Column(name = "Id",
        nullable = false, columnDefinition = "BIGINT UNSIGNED"))
public class Employeeitems extends BaseEntity{

    @JoinColumn(name = "EmployeeId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Employee employeeId;
    @JoinColumn(name = "ItemId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Item itemId;
    @JoinColumn(name = "StatusId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Status statusId;

    public Employeeitems() {
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    public Status getStatusId() {
        return statusId;
    }

    public void setStatusId(Status statusId) {
        this.statusId = statusId;
    }
  
    @Override
    public String toString() {
        return "hhn.qlqa027a.entities.Employeeitems[ id=" + super.getId() + " ]";
    }    
}
