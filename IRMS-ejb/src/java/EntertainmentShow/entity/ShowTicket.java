/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntertainmentShow.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author xing zhe
 */
@Entity
public class ShowTicket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private float price;
    
    @ManyToOne 
    private Seat seat;
    @ManyToOne 
    private SectionTicket sectionTicket;
    @ManyToOne
    private ShowOrder showOrder;
    
    public ShowTicket(){}
    
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
        if (!(object instanceof ShowTicket)) {
            return false;
        }
        ShowTicket other = (ShowTicket) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntertainmentShow.entity.showTicket[ id=" + getId() + " ]";
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
     * @return the seat
     */
    public Seat getSeat() {
        return seat;
    }

    /**
     * @param seat the seat to set
     */
    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    /**
     * @return the sectionTicket
     */
    public SectionTicket getSectionTicket() {
        return sectionTicket;
    }

    /**
     * @param sectionTicket the sectionTicket to set
     */
    public void setSectionTicket(SectionTicket sectionTicket) {
        this.sectionTicket = sectionTicket;
    }

    /**
     * @return the showOrder
     */
    public ShowOrder getShowOrder() {
        return showOrder;
    }

    /**
     * @param showOrder the showOrder to set
     */
    public void setShowOrder(ShowOrder showOrder) {
        this.showOrder = showOrder;
    }
    
}
