/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition.entity;

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
 * @author user
 */
@Entity
public class ClientReceipt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date receiptDate;
    private float totalPayment;
    private String receiver;
    private String payer;
    private String receiptDateString;
    
    @OneToOne (mappedBy="receipt")
    private ClientBill clientBill;

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
        if (!(object instanceof ClientReceipt)) {
            return false;
        }
        ClientReceipt other = (ClientReceipt) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConventionExhibition.entity.ClientReceipt[ id=" + getId() + " ]";
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
     * @return the clientBill
     */
    public ClientBill getClientBill() {
        return clientBill;
    }

    /**
     * @param clientBill the clientBill to set
     */
    public void setClientBill(ClientBill clientBill) {
        this.clientBill = clientBill;
    }
    
}
