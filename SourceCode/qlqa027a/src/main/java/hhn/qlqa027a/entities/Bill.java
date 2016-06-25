/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhn.qlqa027a.entities;

import java.util.Collection;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Huy
 */
@Entity
@Table(name = "bill")
@NamedQueries({
    @NamedQuery(name = "Bill.findAll", query = "SELECT b FROM Bill b"),
    @NamedQuery(name = "Bill.findById", query = "SELECT b FROM Bill b WHERE b.id = :id"),
    @NamedQuery(name = "Bill.findByCreatedAt", query = "SELECT b FROM Bill b WHERE b.createdAt = :createdAt"),
    @NamedQuery(name = "Bill.findByNumberOfCustomer", query = "SELECT b FROM Bill b WHERE b.numberOfCustomer = :numberOfCustomer"),
    @NamedQuery(name = "Bill.findByAmount", query = "SELECT b FROM Bill b WHERE b.amount = :amount"),
    @NamedQuery(name = "Bill.findByCustomerPay", query = "SELECT b FROM Bill b WHERE b.customerPay = :customerPay"),
    @NamedQuery(name = "Bill.findByCustomerReturn", query = "SELECT b FROM Bill b WHERE b.customerReturn = :customerReturn"),
    @NamedQuery(name = "Bill.findByMergeBillId", query = "SELECT b FROM Bill b WHERE b.mergeBillId = :mergeBillId")})
@AttributeOverride(name = "id", column = @Column(name = "Id",
        nullable = false, columnDefinition = "BIGINT UNSIGNED"))
public class Bill extends BaseEntity {   
 
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "NumberOfCustomer")
    private Integer numberOfCustomer;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Amount")
    private Double amount;
    @Column(name = "CustomerPay")
    private Double customerPay;
    @Column(name = "CustomerReturn")
    private Double customerReturn;
    @Column(name = "MergeBillId")
    private Integer mergeBillId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "billId")
    private Collection<Billdetail> billdetailCollection;
    @JoinColumn(name = "EmployeeId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Employee employeeId;
    @JoinColumn(name = "StatusId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Status statusId;
    @JoinColumn(name = "TableId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Stable tableId;

    public Bill() {
    }

    public Bill(Integer id) {
        super.setId(id);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getNumberOfCustomer() {
        return numberOfCustomer;
    }

    public void setNumberOfCustomer(Integer numberOfCustomer) {
        this.numberOfCustomer = numberOfCustomer;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getCustomerPay() {
        return customerPay;
    }

    public void setCustomerPay(Double customerPay) {
        this.customerPay = customerPay;
    }

    public Double getCustomerReturn() {
        return customerReturn;
    }

    public void setCustomerReturn(Double customerReturn) {
        this.customerReturn = customerReturn;
    }

    public Integer getMergeBillId() {
        return mergeBillId;
    }

    public void setMergeBillId(Integer mergeBillId) {
        this.mergeBillId = mergeBillId;
    }

    @XmlTransient
    public Collection<Billdetail> getBilldetailCollection() {
        return billdetailCollection;
    }

    public void setBilldetailCollection(Collection<Billdetail> billdetailCollection) {
        this.billdetailCollection = billdetailCollection;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Status getStatusId() {
        return statusId;
    }

    public void setStatusId(Status statusId) {
        this.statusId = statusId;
    }

    public Stable getTableId() {
        return tableId;
    }

    public void setTableId(Stable tableId) {
        this.tableId = tableId;
    }

    
    @Override
    public String toString() {
        return "hhn.qlqa027a.entities.Bill[ id=" + super.getId() + " ]";
    }    
    
}
