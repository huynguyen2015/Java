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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinTable;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Huy
 */
@Entity
@Table(name = "employee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e WHERE StatusId <> 5"),
    @NamedQuery(name = "Employee.findByFilter", query = "SELECT e FROM Employee e "
            + "WHERE e.fullName LIKE :fullName "
            + " OR e.email LIKE :email"
            + " OR e.phone LIKE :phone"
            + " OR e.identityNumber LIKE :identityNumber"),
    
    @NamedQuery(name = "Employee.findById", query = "SELECT e FROM Employee e WHERE e.id = :id AND StatusId <> 5"),
    @NamedQuery(name = "Employee.findByFullName", query = "SELECT e FROM Employee e WHERE e.fullName = :fullName AND StatusId <> 5"),
    @NamedQuery(name = "Employee.findByPhone", query = "SELECT e FROM Employee e WHERE e.phone = :phone"),
    @NamedQuery(name = "Employee.findByEmail", query = "SELECT e FROM Employee e WHERE e.email = :email AND StatusId <> 5"),
    @NamedQuery(name = "Employee.findByPassword", query = "SELECT e FROM Employee e WHERE e.password = :password"),
    @NamedQuery(name = "Employee.findByBirthday", query = "SELECT e FROM Employee e WHERE e.birthday = :birthday"),
    @NamedQuery(name = "Employee.findByAddress", query = "SELECT e FROM Employee e WHERE e.address = :address"),
    @NamedQuery(name = "Employee.findByIdentityNumber", query = "SELECT e FROM Employee e WHERE e.identityNumber = :identityNumber AND StatusId <> 5"),
    @NamedQuery(name = "Employee.findByStartDate", query = "SELECT e FROM Employee e WHERE e.startDate = :startDate AND StatusId <> 5")})
@AttributeOverride(name = "id", column = @Column(name = "Id",
        nullable = false, columnDefinition = "BIGINT UNSIGNED"))
public class Employee extends BaseEntity {

    public static final String FIND_ALL = "Employee.findAll";
    public static final String FIND_BY_FILTER = "Employee.findByFilter";    
    public static final String FIND_BY_EMAIL = "Employee.findByEmail";
    
    @ManyToMany
    @JoinTable(name="employeesschedule", 
          joinColumns=@JoinColumn(name="EmployeeId"),
          inverseJoinColumns=@JoinColumn(name="ScheduleId"))
    private Collection<Schedule> schedules;
    
    @ManyToMany
    @JoinTable(name="employeecertificates", 
          joinColumns=@JoinColumn(name="EmployeeId"),
          inverseJoinColumns=@JoinColumn(name="CertificateId"))
    private Collection<Certificate> certificates;  

    @Column(name = "FullName")
    private String fullName;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Email")
    private String email;
    @Column(name = "Password")
    private String password;
    @Column(name = "Birthday")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;
    @Column(name = "Address")
    private String address;
    @Column(name = "IdentityNumber")
    private String identityNumber;
    @Column(name = "StartDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId")
    private Collection<Bill> billCollection;   
    @JoinColumn(name = "RoleId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Role role;    
    @JoinColumn(name = "StatusId", referencedColumnName = "Id")
    @ManyToOne(optional = false) 
    private Status status;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId")
    private Collection<Employeeitems> employeeitemsCollection;

    public Employee() {
    }
   
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @XmlTransient
    public Collection<Bill> getBillCollection() {
        return billCollection;
    }

    public void setBillCollection(Collection<Bill> billCollection) {
        this.billCollection = billCollection;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    } 

    @XmlTransient
    public Collection<Employeeitems> getEmployeeitemsCollection() {
        return employeeitemsCollection;
    }

    public void setEmployeeitemsCollection(Collection<Employeeitems> employeeitemsCollection) {
        this.employeeitemsCollection = employeeitemsCollection;
    } 

    @Override
    public String toString() {
        return this.email;
    }

    @XmlTransient
    public Collection<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Collection<Schedule> schedules) {
        this.schedules = schedules;
    }

     public Collection<Certificate> getCertificates() {
        return this.certificates;
    }
     
     public void setCertificates(Collection<Certificate> certificates) {
        this.certificates = certificates;
    }
}
