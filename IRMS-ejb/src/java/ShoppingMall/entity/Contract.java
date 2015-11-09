/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingMall.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Cindylulu
 */
@Entity
public class Contract implements Serializable {
    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date contractDate;
    private String lessor;
    private String lessee;
    private int leaseterm;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date leaseDateTo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date leaseDateFrom;
    private float totalRental;
    private float monthlyRental;
    private float commissionRate;
    private float deposit;
    @ManyToOne
    private Shop shop;
    private String venue;
    private String activated;
    private String renewed;
    private String cDate;
    private String dateFrom;
    private String dateTo;
    
    private float firstMonthRental;
    private float lastMonthRental;
    private float baselineRental;
    
    @OneToMany(mappedBy = "contract")
    private Collection<RentalReports> rentalReports;
    
    public Contract () {
        activated="Inactivated";
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contract)) {
            return false;
        }
        Contract other = (Contract) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Common.entity.Contract[ id=" + getId() + " ]";
    }

    /**
     * @return the contractDate
     */
    public Date getContractDate() {
        return contractDate;
    }

    /**
     * @param contractDate the contractDate to set
     */
    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    /**
     * @return the lessor
     */
    public String getLessor() {
        return lessor;
    }

    /**
     * @param lessor the lessor to set
     */
    public void setLessor(String lessor) {
        this.lessor = lessor;
    }

    /**
     * @return the lessee
     */
    public String getLessee() {
        return lessee;
    }

    /**
     * @param lessee the lessee to set
     */
    public void setLessee(String lessee) {
        this.lessee = lessee;
    }

    /**
     * @return the leaseterm
     */
    public int getLeaseterm() {
        return leaseterm;
    }

    /**
     * @param leaseterm the leaseterm to set
     */
    public void setLeaseterm(int leaseterm) {
        this.leaseterm = leaseterm;
    }

    /**
     * @return the leaseDateFrom
     */
    public Date getLeaseDateFrom() {
        return leaseDateFrom;
    }

    /**
     * @param leaseDateFrom the leaseDateFrom to set
     */
    public void setLeaseDateFrom(Date leaseDateFrom) {
        this.leaseDateFrom = leaseDateFrom;
    }

    /**
     * @return the totalRental
     */
    public float getTotalRental() {
        return totalRental;
    }

    /**
     * @param totalRental the totalRental to set
     */
    public void setTotalRental(float totalRental) {
        this.totalRental = totalRental;
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
     * @return the commission
     */
    public float getCommissionRate() {
        return commissionRate;
    }

    /**
     * @param commission the commission to set
     */
    public void setCommissionRate(float commission) {
        this.commissionRate = commission;
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

    /**
     * @return the shop
     */
    public Shop getShop() {
        return shop;
    }

    /**
     * @param shop the shop to set
     */
    public void setShop(Shop shop) {
        this.shop = shop;
    }

    /**
     * @return the venue
     */
    public String getVenue() {
        return venue;
    }

    /**
     * @param venue the venue to set
     */
    public void setVenue(String venue) {
        this.venue = venue;
    }

    /**
     * @return the status
     */
    public String getActivated() {
        return activated;
    }

    /**
     * @param status the status to set
     */
    public void setActivated(String status) {
        this.activated = status;
    }

    /**
     * @return the leaseDateTo
     */
    public Date getLeaseDateTo() {
        return leaseDateTo;
    }

    /**
     * @param leaseDateTo the leaseDateTo to set
     */
    public void setLeaseDateTo(Date leaseDateTo) {
        this.leaseDateTo = leaseDateTo;
    }

    /**
     * @return the cDate
     */
    public String getcDate() {
        return cDate;
    }

    /**
     * @param cDate the cDate to set
     */
    public void setcDate(String cDate) {
        this.cDate = cDate;
    }

    /**
     * @return the dateFrom
     */
    public String getDateFrom() {
        return dateFrom;
    }

    /**
     * @param dateFrom the dateFrom to set
     */
    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     * @return the dateTo
     */
    public String getDateTo() {
        return dateTo;
    }

    /**
     * @param dateTo the dateTo to set
     */
    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    /**
     * @return the firstMonthRental
     */
    public float getFirstMonthRental() {
        return firstMonthRental;
    }

    /**
     * @param firstMonthRental the firstMonthRental to set
     */
    public void setFirstMonthRental(float firstMonthRental) {
        this.firstMonthRental = firstMonthRental;
    }

    /**
     * @return the lastMonthRental
     */
    public float getLastMonthRental() {
        return lastMonthRental;
    }

    /**
     * @param lastMonthRental the lastMonthRental to set
     */
    public void setLastMonthRental(float lastMonthRental) {
        this.lastMonthRental = lastMonthRental;
    }

    /**
     * @return the rentalReports
     */
    public Collection<RentalReports> getRentalReports() {
        return rentalReports;
    }

    /**
     * @param rentalReports the rentalReports to set
     */
    public void setRentalReports(Collection<RentalReports> rentalReports) {
        this.rentalReports = rentalReports;
    }

    /**
     * @return the baselineRental
     */
    public float getBaselineRental() {
        return baselineRental;
    }

    /**
     * @param baselineRental the baselineRental to set
     */
    public void setBaselineRental(float baselineRental) {
        this.baselineRental = baselineRental;
    }

    /**
     * @return the renewed
     */
    public String getRenewed() {
        return renewed;
    }

    /**
     * @param renewed the renewed to set
     */
    public void setRenewed(String renewed) {
        this.renewed = renewed;
    }
    
}
