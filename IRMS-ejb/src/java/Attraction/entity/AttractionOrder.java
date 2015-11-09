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
public class AttractionOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float totalPrice;
    private int quantity;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date orderDate;
    //relationship
  
    @ManyToOne
    private MemberAccount member;
    @ManyToOne
    private AttractionGuest attractionGuest;
     @OneToMany(mappedBy="attractionOrder")
     private Collection<BundleOrder> bundleOrders;
      @OneToMany(mappedBy="attractionOrder")
    private Collection<AttractionTicketOrder> attractionTicketOrders;
     
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
        if (!(object instanceof AttractionOrder)) {
            return false;
        }
        AttractionOrder other = (AttractionOrder) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Attraction.entity.AttractionOrder[ id=" + getId() + " ]";
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
     * @return the member
     */
    public MemberAccount getMember() {
        return member;
    }

    /**
     * @param member the member to set
     */
    public void setMember(MemberAccount member) {
        this.member = member;
    }

    /**
     * @return the attractionGuest
     */
    public AttractionGuest getAttractionGuest() {
        return attractionGuest;
    }

    /**
     * @param attractionGuest the attractionGuest to set
     */
    public void setAttractionGuest(AttractionGuest attractionGuest) {
        this.attractionGuest = attractionGuest;
    }

    /**
     * @return the attractionTicketOrder
     */
    public Collection<AttractionTicketOrder> getAttractionTicketOrders() {
        return attractionTicketOrders;
    }

    /**
     * @param attractionTicketOrder the attractionTicketOrder to set
     */
    public void setAttractionTicketOrders(Collection<AttractionTicketOrder> attractionTicketOrder) {
        this.attractionTicketOrders = attractionTicketOrder;
    }

    /**
     * @return the bundleOrders
     */
    public Collection<BundleOrder> getBundleOrders() {
        return bundleOrders;
    }

    /**
     * @param bundleOrders the bundleOrders to set
     */
    public void setBundleOrders(Collection<BundleOrder> bundleOrders) {
        this.bundleOrders = bundleOrders;
    }

    /**
     * @return the orderDate
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    
}
