/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM.entity;

import Common.entity.StaffAccount;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author ARIEL CHENG
 */
@Entity
public class CRMreport implements Serializable {
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String targetCustomer;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date reportDate;
    private double lifeTimeValue;
    private double retentionRate;
    private double acquisitionCost;
    private double acquisitionRate;
    private String comments;
    
    @ManyToOne
    private StaffAccount staffAccount;
    
    public CRMreport(){
        
    }
    
    public void create(String targetCustomer, Date reportDate, double lifeTimeValue, double retentionRate, double acquisitionRate,
            double acquisitionCost, String comments){
        this.setTargetCustomer(targetCustomer);
        this.setReportDate(reportDate);
        this.setLifeTimeValue(lifeTimeValue);
        this.setRetentionRate(retentionRate);
        this.setAcquisitionCost(acquisitionCost);
        this.setAcquisitionRate(acquisitionRate);
        this.setComments(comments);
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
        if (!(object instanceof CRMreport)) {
            return false;
        }
        CRMreport other = (CRMreport) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CRM.entity.CRMreport[ id=" + getId() + " ]";
    }

    /**
     * @return the targetCustomer
     */
    public String getTargetCustomer() {
        return targetCustomer;
    }

    /**
     * @param targetCustomer the targetCustomer to set
     */
    public void setTargetCustomer(String targetCustomer) {
        this.targetCustomer = targetCustomer;
    }

    /**
     * @return the reportDate
     */
    public Date getReportDate() {
        return reportDate;
    }

    /**
     * @param reportDate the reportDate to set
     */
    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    /**
     * @return the lifeTimeValue
     */
    public double getLifeTimeValue() {
        return lifeTimeValue;
    }

    /**
     * @param lifeTimeValue the lifeTimeValue to set
     */
    public void setLifeTimeValue(double lifeTimeValue) {
        this.lifeTimeValue = lifeTimeValue;
    }

    /**
     * @return the retentionRate
     */
    public double getRetentionRate() {
        return retentionRate;
    }

    /**
     * @param retentionRate the retentionRate to set
     */
    public void setRetentionRate(double retentionRate) {
        this.retentionRate = retentionRate;
    }

    /**
     * @return the acquisitionCost
     */
    public double getAcquisitionCost() {
        return acquisitionCost;
    }

    /**
     * @param acquisitionCost the acquisitionCost to set
     */
    public void setAcquisitionCost(double acquisitionCost) {
        this.acquisitionCost = acquisitionCost;
    }

    /**
     * @return the acquisitionRate
     */
    public double getAcquisitionRate() {
        return acquisitionRate;
    }

    /**
     * @param acquisitionRate the acquisitionRate to set
     */
    public void setAcquisitionRate(double acquisitionRate) {
        this.acquisitionRate = acquisitionRate;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return the staffAccount
     */
    public StaffAccount getStaffAccount() {
        return staffAccount;
    }

    /**
     * @param staffAccount the staffAccount to set
     */
    public void setStaffAccount(StaffAccount staffAccount) {
        this.staffAccount = staffAccount;
    }
    
}
