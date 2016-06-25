/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhn.qlqa027a.entities;

import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Huy
 */
@Entity
@Table(name = "menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m WHERE StatusId <> 5"),
    @NamedQuery(name = "Menu.findByFilter", query = "SELECT m FROM Menu m "
            + "WHERE m.name LIKE :name and StatusId <> 5"),
    @NamedQuery(name = "Menu.findById", query = "SELECT m FROM Menu m WHERE m.id = :id"),
    @NamedQuery(name = "Menu.findByName", query = "SELECT m FROM Menu m WHERE m.name = :name"),
    @NamedQuery(name = "Menu.findByCreatedAt", query = "SELECT m FROM Menu m WHERE m.createdAt = :createdAt")})
@AttributeOverride(name = "id", column = @Column(name = "Id",
        nullable = false, columnDefinition = "BIGINT UNSIGNED"))
public class Menu extends BaseEntity {
    
    public static final String FIND_ALL = "Menu.findAll";
    public static final String FIND_BY_FILTER = "Menu.findByFilter";
    
    @Column(name = "Name")
    private String name;
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;    
    @JoinColumn(name = "StatusId", referencedColumnName = "Id")
    @ManyToOne(optional = false) 
    private Status status; 
    
    
    public Menu() {
    }
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
