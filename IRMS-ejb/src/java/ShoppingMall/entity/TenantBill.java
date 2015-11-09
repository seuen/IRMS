/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingMall.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Cindylulu
 */
@Entity
public class TenantBill implements Serializable {

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
    private float rentalCharges;
    private float totalSales;
    private float commissionRate;
    private float commissionCharges;
    private float adhocCharges;
    private float depost;
    private float totalCharges;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date deadLine;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date billDate;
    private String billDateString;
    private String status;
    private String payer;
    private String receiver;
    @OneToOne(mappedBy="tenantBill")
    private RentalReports rentalReports;
    @OneToOne
    private TenantReceipt tenantReceipt;

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
        if (!(object instanceof TenantBill)) {
            return false;
        }
        TenantBill other = (TenantBill) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Common.entity.Bill[ id=" + getId() + " ]";
    }

    /**
     * @return the rentalCharges
     */
    public float getRentalCharges() {
        return rentalCharges;
    }

    /**
     * @param rentalCharges the rentalCharges to set
     */
    public void setRentalCharges(float rentalCharges) {
        this.rentalCharges = rentalCharges;
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
     * @return the commissionCharges
     */
    public float getCommissionCharges() {
        return commissionCharges;
    }

    /**
     * @param commissionCharges the commissionCharges to set
     */
    public void setCommissionCharges(float commissionCharges) {
        this.commissionCharges = commissionCharges;
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
     * @return the deadLine
     */
    public Date getDeadLine() {
        return deadLine;
    }

    /**
     * @param deadLine the deadLine to set
     */
    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    /**
     * @return the billDate
     */
    public Date getBillDate() {
        return billDate;
    }

    /**
     * @param billDate the billDate to set
     */
    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the payer
     */
    public String getPayer() {
        return payer;
    }

    /**
     * @param payer the payer to set
     */
    public void setPayer(String payer) {
        this.payer = payer;
    }

    /**
     * @return the receiver
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * @param receiver the receiver to set
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * @return the rentalReports
     */
    public RentalReports getRentalReports() {
        return rentalReports;
    }

    /**
     * @param rentalReports the rentalReports to set
     */
    public void setRentalReports(RentalReports rentalReports) {
        this.rentalReports = rentalReports;
    }

    /**
     * @return the tenantReceipt
     */
    public TenantReceipt getTenantReceipt() {
        return tenantReceipt;
    }

    /**
     * @param tenantReceipt the tenantReceipt to set
     */
    public void setTenantReceipt(TenantReceipt tenantReceipt) {
        this.tenantReceipt = tenantReceipt;
    }

    /**
     * @return the depost
     */
    public float getDepost() {
        return depost;
    }

    /**
     * @param depost the depost to set
     */
    public void setDepost(float depost) {
        this.depost = depost;
    }

    /**
     * @return the billDateString
     */
    public String getBillDateString() {
        return billDateString;
    }

    /**
     * @param billDateString the billDateString to set
     */
    public void setBillDateString(String billDateString) {
        this.billDateString = billDateString;
    }
}
