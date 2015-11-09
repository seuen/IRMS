/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Accommodation.entity;

import CRM.entity.MemberAccount;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
public class Stay implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stayId;
  
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFrom;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateTo;
    private int numGuest;
    private float totalPrice;
    private float totalCharges;
    private boolean status;
    private List<String> guestnames;
    
    
    @ManyToOne
    private MemberAccount memberAccount;
    
   
    @ManyToOne
    private Room room;
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="stay")
    private Collection<RSOrder> rsOrders;
    
    @ManyToMany
    private Collection<Guest> guests;
    
    
    public Stay(){
      rsOrders=new ArrayList();
      guests=new ArrayList();
      guestnames=new ArrayList();
      
    }
    
    @Override
    public String toString() {
        return "Accommodation.entity.Stay[ id=" + stayId + " ]";
    }

    public Long getStayId() {
        return stayId;
    }

    public void setStayId(Long stayId) {
        this.stayId = stayId;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Integer getNumGuest() {
        return numGuest;
    }

    public void setNumGuest(Integer numGuest) {
        this.numGuest = numGuest;
    }
 
    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Collection<RSOrder> getRsOrders() {
        return rsOrders;
    }

    public void setRsOrders(Collection<RSOrder> rsOrders) {
        this.rsOrders = rsOrders;
    }

    public Collection<Guest> getGuests() {
        return guests;
    }

    public void setGuests(Collection<Guest> guests) {
        this.guests = guests;
    }

    public float getTotalCharges() {
        return totalCharges;
    }

    public void setTotalCharges(float totalCharges) {
        this.totalCharges = totalCharges;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the member
     */
    public MemberAccount getMember() {
        return memberAccount;
    }

    /**
     * @param member the member to set
     */
    public void setMember(MemberAccount member) {
        this.memberAccount = member;
    }

    /**
     * @return the guestnames
     */
    public List<String> getGuestnames() {
        return guestnames;
    }

    /**
     * @param guestnames the guestnames to set
     */
    public void setGuestnames(List<String> guestnames) {
        this.guestnames = guestnames;
    }
  
}
