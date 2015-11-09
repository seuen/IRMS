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
public class Seat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String rowNum;
    private String seatNum;
    
    @ManyToOne
    private SeatSection seatSection;
    @OneToMany(mappedBy="seat")
    private Collection<ShowTicket> showTickets;
    
    public Seat(){}
    
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
        if (!(object instanceof Seat)) {
            return false;
        }
        Seat other = (Seat) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntertainmentShow.entity.Seat[ id=" + getId() + " ]";
    }

    /**
     * @return the rowNum
     */
    public String getRowNum() {
        return rowNum;
    }

    /**
     * @param rowNum the rowNum to set
     */
    public void setRowNum(String rowNum) {
        this.rowNum = rowNum;
    }

    /**
     * @return the seatNum
     */
    public String getSeatNum() {
        return seatNum;
    }

    /**
     * @param seatNum the seatNum to set
     */
    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
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
    
}
