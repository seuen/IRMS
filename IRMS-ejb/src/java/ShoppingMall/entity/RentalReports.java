/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingMall.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Cindylulu
 */
@Entity
public class RentalReports implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long name;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date generationDate;
    private float monthlyRental;
    private float totalSales;
    private float commissionRate;
    private float commissionFee;
    private float adhocCharges;
    private float deposit;
    private float totalCharges;
    @ManyToOne
    private Contract contract;
    @OneToOne
    private TenantBill tenantBill;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RentalReports)) {
            return false;
        }
        RentalReports other = (RentalReports) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ShoppingMall.entity.RentalReports[ id=" + id + " ]";
    }

    /**
     * @return the name
     */
    public Long getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(Long name) {
        this.name = name;
    }

    /**
     * @return the generationDate
     */
    public Date getGenerationDate() {
        return generationDate;
    }

    /**
     * @param generationDate the generationDate to set
     */
    public void setGenerationDate(Date generationDate) {
        this.generationDate = generationDate;
    }

    /**
     * @return the monthlyRental
     */
    public float getMonthlyRental() {
        return monthlyRental;
    }

    /**
     * @param monthlyRental the monthlyRental to set
     */
    public void setMonthlyRental(float monthlyRental) {
        this.monthlyRental = monthlyRental;
    }

    /**
     * @return the totalSales
     */
    public float getTotalSales() {
        return totalSales;
    }

    /**
     * @param totalSales the totalSales to set
     */
    public void setTotalSales(float totalSales) {
        this.totalSales = totalSales;
    }

    /**
     * @return the commissionRate
     */
    public float getCommissionRate() {
        return commissionRate;
    }

    /**
     * @param commissionRate the commissionRate to set
     */
    public void setCommissionRate(float commissionRate) {
        this.commissionRate = commissionRate;
    }

    /**
     * @return the commissionFee
     */
    public float getCommissionFee() {
        return commissionFee;
    }

    /**
     * @param commissionFee the commissionFee to set
     */
    public void setCommissionFee(float commissionFee) {
        this.commissionFee = commissionFee;
    }

    /**
     * @return the adhocCharges
     */
    public float getAdhocCharges() {
        return adhocCharges;
    }

    /**
     * @param adhocCharges the adhocCharges to set
     */
    public void setAdhocCharges(float adhocCharges) {
        this.adhocCharges = adhocCharges;
    }

    /**
     * @return the totalCharges
     */
    public float getTotalCharges() {
        return totalCharges;
    }

    /**
     * @param totalCharges the totalCharges to set
     */
    public void setTotalCharges(float totalCharges) {
        this.totalCharges = totalCharges;
    }

    /**
     * @return the contract
     */
    public Contract getContract() {
        return contract;
    }

    /**
     * @param contract the contract to set
     */
    public void setContract(Contract contract) {
        this.contract = contract;
    }

    /**
     * @return the tenantBill
     */
    public TenantBill getTenantBill() {
        return tenantBill;
    }

    /**
     * @param tenantBill the tenantBill to set
     */
    public void setTenantBill(TenantBill tenantBill) {
        this.tenantBill = tenantBill;
    }

    /**
     * @return the deposit
     */
    public float getDeposit() {
        return deposit;
    }

    /**
     * @param deposit the deposit to set
     */
    public void setDeposit(float deposit) {
        this.deposit = deposit;
    }
}
