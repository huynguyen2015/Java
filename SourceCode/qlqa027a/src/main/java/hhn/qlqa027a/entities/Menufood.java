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
@Table(name = "menufood")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menufood.findAll", query = "SELECT m FROM Menufood m where StatusId <> 5"),
    @NamedQuery(name = "Menufood.findByFilter", query = "SELECT m FROM Menufood m "
            + "WHERE m.menu LIKE :menu and StatusId <> 5"),
    @NamedQuery(name = "Menufood.findById", query = "SELECT m FROM Menufood m WHERE m.id = :id")})
@AttributeOverride(name = "id", column = @Column(name = "Id",
        nullable = false, columnDefinition = "BIGINT UNSIGNED"))
public class Menufood extends BaseEntity {
    
    public static final String FIND_ALL = "Menufood.findAll";
    public static final String FIND_BY_FILTER = "Menufood.findByFilter";
    
    @JoinColumn(name = "FoodId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Food food;
    @JoinColumn(name = "MenuId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Menu menu;
    @JoinColumn(name = "StatusId", referencedColumnName = "Id")
    @ManyToOne(optional = false) 
    private Status status; 
    
    public Menufood() {
    }
    
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    } 
    
    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "hhn.qlqa027a.entities.Menufood[ id=" + super.getId() + " ]";
    }
    
}
