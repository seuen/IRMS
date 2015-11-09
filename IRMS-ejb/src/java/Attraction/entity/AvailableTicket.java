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
 * @author xing zhe
 */
@Entity
public class AvailableTicket implements Serializable {
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
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tDate;
    private int purchasedNum;
    @ManyToOne
    private TicketType ticketType;

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
        if (!(object instanceof AvailableTicket)) {
            return false;
        }
        AvailableTicket other = (AvailableTicket) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Attraction.entity.AvailableTicket[ id=" + getId() + " ]";
    }

    /**
     * @return the tDate
     */
    public Date gettDate() {
        return tDate;
    }

    /**
     * @param tDate the tDate to set
     */
    public void settDate(Date tDate) {
        this.tDate = tDate;
    }

    /**
     * @return the purchasedNum
     */
    public int getPurchasedNum() {
        return purchasedNum;
    }

    /**
     * @param purchasedNum the purchasedNum to set
     */
    public void setPurchasedNum(int purchasedNum) {
        this.purchasedNum = purchasedNum;
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
    
}
