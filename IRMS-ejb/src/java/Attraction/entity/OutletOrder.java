/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.entity;

import CRM.entity.MemberAccount;
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
 * @author zsy
 */
@Entity
public class OutletOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float totalPrice;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date purchaseDate;
    private String paymentStatus;

    //relationship
    @OneToMany(mappedBy="outletOrder")
    private Collection<AttraItemOrder> attraItemOrders;
    @ManyToOne 
    private MemberAccount memberAccount;
    
    
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
        if (!(object instanceof OutletOrder)) {
            return false;
        }
        OutletOrder other = (OutletOrder) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Attraction.entity.OutletOrder[ id=" + getId() + " ]";
    }

    /**
     * @return the totalPrice
     */
    public float getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return the purchaseDate
     */
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * @param purchaseDate the purchaseDate to set
     */
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * @return the paymentStatus
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * @param paymentStatus the paymentStatus to set
     */
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * @return the attraItemOrders
     */
    public Collection<AttraItemOrder> getAttraItemOrders() {
        return attraItemOrders;
    }

    /**
     * @param attraItemOrders the attraItemOrders to set
     */
    public void setAttraItemOrders(Collection<AttraItemOrder> attraItemOrders) {
        this.attraItemOrders = attraItemOrders;
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
