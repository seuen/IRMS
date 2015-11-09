/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM.entity;

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
public class BuyShowTicketItem implements Serializable {
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
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date buyTime;
    private String ticketName;
    private int quantity;
    private double unitPrice;
    @ManyToOne
    private MemberAccount memberAccount;
    
    public BuyShowTicketItem(){
        
    }
    
    public void create(Date buyTime, String ticketName, int quantity, double unitPrice){
        this.setUnitPrice(unitPrice);
        this.setQuantity(quantity);
        this.setTicketName(ticketName);
        this.setBuyTime(buyTime);
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
        if (!(object instanceof BuyShowTicketItem)) {
            return false;
        }
        BuyShowTicketItem other = (BuyShowTicketItem) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CRM.entity.BuyShowTicketItem[ id=" + getId() + " ]";
    }

    /**
     * @return the buyTime
     */
    public Date getBuyTime() {
        return buyTime;
    }

    /**
     * @param buyTime the buyTime to set
     */
    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    /**
     * @return the ticketName
     */
    public String getTicketName() {
        return ticketName;
    }

    /**
     * @param ticketName the ticketName to set
     */
    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the unitPrice
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return the memberAccount
     */
    public MemberAccount getMemberAccount() {
        return memberAccount;
    }

    /**
     * @param memberAccount the memberAccount to set
     */
    public void setMemberAccount(MemberAccount memberAccount) {
        this.memberAccount = memberAccount;
    }
    
}
