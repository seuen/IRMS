/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntertainmentShow.entity;

import ConventionExhibition.entity.Client;
import java.io.Serializable;
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
 * @author xing zhe
 */
@Entity
public class Bill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date biilDate;
    private Float totalCharges;
    
    @ManyToOne
    private Client client;
    @OneToOne(mappedBy="bill")
    private BillReceipt billReceipt;
    
    public Bill(){}
    
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
        if (!(object instanceof Bill)) {
            return false;
        }
        Bill other = (Bill) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntertainmentShow.entity.Bill[ id=" + getId() + " ]";
    }

    /**
     * @return the biilDate
     */
    public Date getBiilDate() {
        return biilDate;
    }

    /**
     * @param biilDate the biilDate to set
     */
    public void setBiilDate(Date biilDate) {
        this.biilDate = biilDate;
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
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @return the billReceipt
     */
    public BillReceipt getBillReceipt() {
        return billReceipt;
    }

    /**
     * @param billReceipt the billReceipt to set
     */
    public void setBillReceipt(BillReceipt billReceipt) {
        this.billReceipt = billReceipt;
    }
    
}
