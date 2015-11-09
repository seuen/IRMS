/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Acer
 */
@Entity
public class ClientBill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date billDate;
    private String status; //First Paid, Complete
    private float totalcharges;
    private float totalprice;
    private String payer;
    private String receiver;
    private String billDateString;
    
    @ManyToOne
    private Client client=new Client();
    
    @OneToMany
    private List<EventOrder> events;
    
    @OneToOne
    private ClientReceipt receipt;
    
    public ClientBill(){
        events=new ArrayList();
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
        if (!(object instanceof ClientBill)) {
            return false;
        }
        ClientBill other = (ClientBill) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConventionExhibition.entity.ClientBill[ id=" + getId() + " ]";
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
     * @return the totalcharges
     */
    public float getTotalcharges() {
        return totalcharges;
    }

    /**
     * @param totalcharges the totalcharges to set
     */
    public void setTotalcharges(float totalcharges) {
        this.totalcharges = totalcharges;
    }

    /**
     * @return the totalprice
     */
    public float getTotalprice() {
        return totalprice;
    }

    /**
     * @param totalprice the totalprice to set
     */
    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
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
     * @return the events
     */
    public List<EventOrder> getEvents() {
        return events;
    }

    /**
     * @param events the events to set
     */
    public void setEvents(List<EventOrder> events) {
        this.events = events;
    }

    /**
     * @return the receipt
     */
    public ClientReceipt getReceipt() {
        return receipt;
    }

    /**
     * @param receipt the receipt to set
     */
    public void setReceipt(ClientReceipt receipt) {
        this.receipt = receipt;
    }

}
