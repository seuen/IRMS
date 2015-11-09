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
public class TenantReceipt implements Serializable {
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
    private float rentalPayment;
    private float commissionPayment;
    private float adhocPayment;
    private float depositPayment;
    private float totalPayment;
    private String payer;
    private String receiver;
    private String receiptDateString;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date receiptDate;
    @OneToOne (mappedBy="tenantReceipt")
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
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TenantReceipt)) {
            return false;
        }
        TenantReceipt other = (TenantReceipt) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Common.entity.TenantInvoice[ id=" + getId() + " ]";
    }

    /**
     * @return the rentalPayment
     */
    public float getRentalPayment() {
        return rentalPayment;
    }

    /**
     * @param rentalPayment the rentalPayment to set
     */
    public void setRentalPayment(float rentalPayment) {
        this.rentalPayment = rentalPayment;
    }

    /**
     * @return the commissionPayment
     */
    public float getCommissionPayment() {
        return commissionPayment;
    }

    /**
     * @param commissionPayment the commissionPayment to set
     */
    public void setCommissionPayment(float commissionPayment) {
        this.commissionPayment = commissionPayment;
    }

    /**
     * @return the adhocPayment
     */
    public float getAdhocPayment() {
        return adhocPayment;
    }

    /**
     * @param adhocPayment the adhocPayment to set
     */
    public void setAdhocPayment(float adhocPayment) {
        this.adhocPayment = adhocPayment;
    }

    /**
     * @return the depositPayment
     */
    public float getDepositPayment() {
        return depositPayment;
    }

    /**
     * @param depositPayment the depositPayment to set
     */
    public void setDepositPayment(float depositPayment) {
        this.depositPayment = depositPayment;
    }

    /**
     * @return the totalPayment
     */
    public float getTotalPayment() {
        return totalPayment;
    }

    /**
     * @param totalPayment the totalPayment to set
     */
    public void setTotalPayment(float totalPayment) {
        this.totalPayment = totalPayment;
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
     * @return the receiptDateString
     */
    public String getReceiptDateString() {
        return receiptDateString;
    }

    /**
     * @param receiptDateString the receiptDateString to set
     */
    public void setReceiptDateString(String receiptDateString) {
        this.receiptDateString = receiptDateString;
    }

    /**
     * @return the receiptDate
     */
    public Date getReceiptDate() {
        return receiptDate;
    }

    /**
     * @param receiptDate the receiptDate to set
     */
    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
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
}
