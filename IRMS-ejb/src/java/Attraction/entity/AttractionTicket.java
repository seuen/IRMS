/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.entity;

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
 * @author zsy
 */
@Entity
public class AttractionTicket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private float price;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expireDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    
    //relationship
    @ManyToOne
    private TicketType ticketType;
    @ManyToOne
    private AttractionTicketOrder attractionTicketOrder;

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
        if (!(object instanceof AttractionTicket)) {
            return false;
        }
        AttractionTicket other = (AttractionTicket) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Attraction.entity.Ticket[ id=" + getId() + " ]";
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the expireDate
     */
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * @param expireDate the expireDate to set
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the ticketType
     */
    public TicketType getTicketType() {
        return ticketType;
    }

    /**
     * @param ticketType the ticketType to set
     */
    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    /**
     * @return the attractionOrder
     */
    public AttractionTicketOrder getAttractionTicketOrder() {
        return attractionTicketOrder;
    }

    /**
     * @param attractionOrder the attractionOrder to set
     */
    public void setAttractionTicketOrder(AttractionTicketOrder attractionTicketOrder) {
        this.attractionTicketOrder = attractionTicketOrder;
    }
    
}
