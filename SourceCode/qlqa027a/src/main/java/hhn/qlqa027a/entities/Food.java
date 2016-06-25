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
@Table(name = "food")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Food.findAll", query = "SELECT f FROM Food f where StatusId <> 5"),
    @NamedQuery(name = "Food.findByFilter", query = "SELECT f FROM Food f "
            + "WHERE f.name LIKE :name and StatusId <> 5"),
    @NamedQuery(name = "Food.findById", query = "SELECT f FROM Food f WHERE f.id = :id"),
    @NamedQuery(name = "Food.findByName", query = "SELECT f FROM Food f WHERE f.name = :name"),
    @NamedQuery(name = "Food.findByPrice", query = "SELECT f FROM Food f WHERE f.price = :price")})
@AttributeOverride(name = "id", column = @Column(name = "Id",
        nullable = false, columnDefinition = "BIGINT UNSIGNED"))
public class Food extends BaseEntity{

    public static final String FIND_ALL = "Food.findAll";
    public static final String FIND_BY_FILTER = "Food.findByFilter";
    
    @Column(name = "Name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Price")
    private Double price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "foodId")
    private Collection<Billdetail> billdetailCollection;
    @JoinColumn(name = "CategoryId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Category category;
    @JoinColumn(name = "StatusId", referencedColumnName = "Id")
    @ManyToOne(optional = false) 
    private Status status; 


    public Food() {
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @XmlTransient
    public Collection<Billdetail> getBilldetailCollection() {
        return billdetailCollection;
    }

    public void setBilldetailCollection(Collection<Billdetail> billdetailCollection) {
        this.billdetailCollection = billdetailCollection;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
