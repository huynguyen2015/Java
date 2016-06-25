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
@Table(name = "rolepermissions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rolepermissions.findAll", query = "SELECT r FROM Rolepermissions r"),
    @NamedQuery(name = "Rolepermissions.findById", query = "SELECT r FROM Rolepermissions r WHERE r.id = :id"),
    @NamedQuery(name = "Rolepermissions.findByIsEnabled", query = "SELECT r FROM Rolepermissions r WHERE r.isEnabled = :isEnabled")})
@AttributeOverride(name = "id", column = @Column(name = "Id",
        nullable = false, columnDefinition = "BIGINT UNSIGNED"))
public class Rolepermissions extends BaseEntity {

    @Column(name = "IsEnabled")
    private Integer isEnabled;
    @JoinColumn(name = "PermissionId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Permission permissionId;
    @JoinColumn(name = "RoleId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Role roleId;

    public Rolepermissions() {
    }

    public Integer getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Permission getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Permission permissionId) {
        this.permissionId = permissionId;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "hhn.qlqa027a.entities.Rolepermissions[ id=" + super.getId() + " ]";
    }

}
