/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntertainmentShow.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author xing zhe
 */
@Entity
public class SectionTicket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int availableNum;
    private float price;
    private String status;
    
    @OneToMany(mappedBy="sectionTicket")
    private Collection<ShowTicket> showTickets;
    @ManyToOne
    private SeatSection seatSection;
    @ManyToOne
    private ESShow show;
    
    public SectionTicket(){}
    
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
        if (!(object instanceof SectionTicket)) {
            return false;
        }
        SectionTicket other = (SectionTicket) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntertainmentShow.entity.SectionTicket[ id=" + getId() + " ]";
    }

    /**
     * @return the availableNum
     */
    public int getAvailableNum() {
        return availableNum;
    }

    /**
     * @param availableNum the availableNum to set
     */
    public void setAvailableNum(int availableNum) {
        this.availableNum = availableNum;
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
     * @return the seatSection
     */
    public SeatSection getSeatSection() {
        return seatSection;
    }

    /**
     * @param seatSection the seatSection to set
     */
    public void setSeatSection(SeatSection seatSection) {
        this.seatSection = seatSection;
    }

    /**
     * @return the showTickets
     */
    public Collection<ShowTicket> getShowTickets() {
        return showTickets;
    }

    /**
     * @param showTickets the showTickets to set
     */
    public void setShowTickets(Collection<ShowTicket> showTickets) {
        this.showTickets = showTickets;
    }

    /**
     * @return the show
     */
    public ESShow getShow() {
        return show;
    }

    /**
     * @param show the show to set
     */
    public void setShow(ESShow show) {
        this.show = show;
    }
    
}
