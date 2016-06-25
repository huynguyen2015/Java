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
@Table(name = "Stable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stable.findAll", query = "SELECT s FROM Stable s WHERE StatusId <> 5"),
    @NamedQuery(name = "Stable.findByFilter", query = "SELECT s FROM Stable s WHERE s.name LIKE :filter AND StatusId <> 5"),
    @NamedQuery(name = "Stable.findById", query = "SELECT s FROM Stable s WHERE s.id = :id AND StatusId <> 5"),
    @NamedQuery(name = "Stable.findByName", query = "SELECT s FROM Stable s WHERE s.name = :name AND StatusId <> 5")})
@AttributeOverride(name = "id", column = @Column(name = "Id",
        nullable = false, columnDefinition = "BIGINT UNSIGNED"))
public class Stable extends BaseEntity {

    public static final String FIND_ALL = "Stable.findAll";
    public static final String FIND_BY_FILTER = "Stable.findByFilter"; 
    
    @Column(name = "Name")
    private String name;
    @Column(name = "NumberSlot")
    private Integer numberSlot;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tableId")
    private Collection<Bill> billCollection;
    @JoinColumn(name = "StatusId", referencedColumnName = "Id")
    @ManyToOne(optional = false) 
    private Status status;           

    public Stable() {
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getNumberSlot() {
        return numberSlot;
    }

    public void setNumberSlot(int numberSlot) {
        this.numberSlot = numberSlot;
    }

    @XmlTransient
    public Collection<Bill> getBillCollection() {
        return billCollection;
    }

    public void setBillCollection(Collection<Bill> billCollection) {
        this.billCollection = billCollection;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    } 

    @Override
    public String toString() {
        return this.name;
    }
       
}
