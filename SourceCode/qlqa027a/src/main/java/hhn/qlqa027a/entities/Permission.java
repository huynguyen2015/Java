/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhn.qlqa027a.entities;

import java.util.Collection;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "permission")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permission.findAll", query = "SELECT p FROM Permission p"),
    @NamedQuery(name = "Permission.findByFilter", query = "SELECT p FROM Permission p WHERE p.name LIKE :name"),
    @NamedQuery(name = "Permission.findById", query = "SELECT p FROM Permission p WHERE p.id = :id"),
    @NamedQuery(name = "Permission.findByName", query = "SELECT p FROM Permission p WHERE p.name = :name")})
@AttributeOverride(name = "id", column = @Column(name = "Id",
        nullable = false, columnDefinition = "BIGINT UNSIGNED"))
public class Permission extends BaseEntity {

    public static final String FIND_ALL = "Permission.findAll";
    public static final String FIND_BY_FILTER = "Permission.findByFilter";   
    public static final String FIND_BY_NAME = "Permission.findByName";
    
    @Column(name = "Name")
    private String name;


    public Permission() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "hhn.qlqa027a.entities.Permission[ id=" + super.getId() + " ]";
    }
}
