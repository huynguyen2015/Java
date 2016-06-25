/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhn.qlqa027a.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Huy
 */
@Entity
@Table(name = "certificate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Certificate.findAll", query = "SELECT c FROM Certificate c"),
    @NamedQuery(name = "Certificate.findById", query = "SELECT c FROM Certificate c WHERE c.id = :id"),
    @NamedQuery(name = "Certificate.findByNames", query = "SELECT c FROM Certificate c WHERE c.name IN :names")})
public class Certificate extends BaseEntity {
    
    public static final String FIND_ALL = "Certificate.findAll";
    public static final String FIND_BY_NAMES = "Certificate.findByNames";        
    
    @Column(name = "Name")
    private String name;

    public Certificate() {
    }
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    

    @Override
    public String toString() {
        return this.name;
    }
    
}
