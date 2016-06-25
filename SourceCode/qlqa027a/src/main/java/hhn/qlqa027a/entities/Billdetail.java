/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhn.qlqa027a.entities;

import javax.persistence.AttributeOverride;
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
@Table(name = "billdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Billdetail.findAll", query = "SELECT b FROM Billdetail b"),
    @NamedQuery(name = "Billdetail.findById", query = "SELECT b FROM Billdetail b WHERE b.id = :id"),
    @NamedQuery(name = "Billdetail.findByQuantum", query = "SELECT b FROM Billdetail b WHERE b.quantum = :quantum"),
    @NamedQuery(name = "Billdetail.findByDescription", query = "SELECT b FROM Billdetail b WHERE b.description = :description")})
@AttributeOverride(name = "id", column = @Column(name = "Id",
        nullable = false, columnDefinition = "BIGINT UNSIGNED"))
public class Billdetail extends BaseEntity {

   
    @Basic(optional = false)
    @Column(name = "Quantum")
    private int quantum;
    @Column(name = "Description")
    private String description;
    @JoinColumn(name = "BillId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Bill billId;
    @JoinColumn(name = "FoodId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Food foodId;
    @JoinColumn(name = "StatusId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Status statusId;

    public Billdetail() {
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bill getBillId() {
        return billId;
    }

    public void setBillId(Bill billId) {
        this.billId = billId;
    }

    public Food getFoodId() {
        return foodId;
    }

    public void setFoodId(Food foodId) {
        this.foodId = foodId;
    }

    public Status getStatusId() {
        return statusId;
    }

    public void setStatusId(Status statusId) {
        this.statusId = statusId;
    }   

    @Override
    public String toString() {
        return "hhn.qlqa027a.entities.Billdetail[ id=" + super.getId() + " ]";
    }    
    
}
