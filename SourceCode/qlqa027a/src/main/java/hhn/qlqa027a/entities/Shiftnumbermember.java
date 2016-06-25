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
@Table(name = "shiftnumbermember")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shiftnumbermember.findAll", query = "SELECT s FROM Shiftnumbermember s"),
    @NamedQuery(name = "Shiftnumbermember.findById", query = "SELECT s FROM Shiftnumbermember s WHERE s.id = :id"),
    @NamedQuery(name = "Shiftnumbermember.findByNumberEmployee", query = "SELECT s FROM Shiftnumbermember s WHERE s.numberEmployee = :numberEmployee")})
@AttributeOverride(name = "id", column = @Column(name = "Id",
        nullable = false, columnDefinition = "BIGINT UNSIGNED"))
public class Shiftnumbermember extends BaseEntity {
 
    @Column(name = "NumberEmployee")
    private Integer numberEmployee;
    @JoinColumn(name = "RoleId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Role roleId;
    @JoinColumn(name = "ShiftId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Shift shiftId;

    public Shiftnumbermember() {
    }

    public Integer getNumberEmployee() {
        return numberEmployee;
    }

    public void setNumberEmployee(Integer numberEmployee) {
        this.numberEmployee = numberEmployee;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    public Shift getShiftId() {
        return shiftId;
    }

    public void setShiftId(Shift shiftId) {
        this.shiftId = shiftId;
    }

    @Override
    public String toString() {
        return "hhn.qlqa027a.entities.Shiftnumbermember[ id=" + super.getId() + " ]";
    }
    
}
