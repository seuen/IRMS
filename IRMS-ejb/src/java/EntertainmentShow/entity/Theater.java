/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntertainmentShow.entity;

import ConventionExhibition.entity.ConventionSchedule;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author xing zhe
 */
@Entity
public class Theater implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int capacity;
    private float dayPrice;
    private String address;
    private String roomNo;
    
    @OneToMany(mappedBy="theater")
    private Collection<SeatSection> seatSections;
    @OneToMany(mappedBy="theater")
    private Collection<ShowInfo> showInfos;
//    @OneToOne(mappedBy="theater")
//    private ShowSchedule showSchedule;
    @OneToMany(mappedBy="theater")
    private List<ConventionSchedule> cschedules;
    
    public Theater(){}
    
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
        if (!(object instanceof Theater)) {
            return false;
        }
        Theater other = (Theater) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntertainmentShow.entity.Theater[ id=" + getId() + " ]";
    }

    /**
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * @return the dayPrice
     */
    public float getDayPrice() {
        return dayPrice;
    }

    /**
     * @param dayPrice the dayPrice to set
     */
    public void setDayPrice(float dayPrice) {
        this.dayPrice = dayPrice;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the roomNo
     */
    public String getRoomNo() {
        return roomNo;
    }

    /**
     * @param roomNo the roomNo to set
     */
    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

//    /**
//     * @return the showSchedule
//     */
//    public ShowSchedule getShowSchedule() {
//        return showSchedule;
//    }
//
//    /**
//     * @param showSchedule the showSchedule to set
//     */
//    public void setShowSchedule(ShowSchedule showSchedule) {
//        this.showSchedule = showSchedule;
//    }

    /**
     * @return the seatSections
     */
    public Collection<SeatSection> getSeatSections() {
        return seatSections;
    }

    /**
     * @param seatSections the seatSections to set
     */
    public void setSeatSections(Collection<SeatSection> seatSections) {
        this.seatSections = seatSections;
    }

    /**
     * @return the showInfos
     */
    public Collection<ShowInfo> getShowInfos() {
        return showInfos;
    }

    /**
     * @param showInfos the showInfos to set
     */
    public void setShowInfos(Collection<ShowInfo> showInfos) {
        this.showInfos = showInfos;
    }

    /**
     * @return the cschedules
     */
    public List<ConventionSchedule> getCschedules() {
        return cschedules;
    }

    /**
     * @param cschedules the cschedules to set
     */
    public void setCschedules(List<ConventionSchedule> cschedules) {
        this.cschedules = cschedules;
    }
   
}
