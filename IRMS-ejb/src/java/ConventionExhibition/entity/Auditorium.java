/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition.entity;

import EntertainmentShow.entity.SeatSection;
import EntertainmentShow.entity.ShowInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Acer
 */
@Entity
public class Auditorium implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int capacity;
    private float dayprice;
    private String roomNo;
    
    @OneToMany(mappedBy="auditorium")
    private List<ConventionSchedule> cschedules;
//    @OneToMany(mappedBy="auditorium")
    
     @OneToMany(mappedBy ="auditorium")
    private List<ShowInfo> showInfos; 
    @OneToMany(mappedBy="auditorium")
    private Collection<SeatSection> seatSections;
    
    public Auditorium(){
        cschedules=new ArrayList();
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
        if (!(object instanceof Auditorium)) {
            return false;
        }
        Auditorium other = (Auditorium) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConventionExhibition.entity.Auditorium[ id=" + getId() + " ]";
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
     * @return the dayprice
     */
    public float getDayprice() {
        return dayprice;
    }

    /**
     * @param dayprice the dayprice to set
     */
    public void setDayprice(float dayprice) {
        this.dayprice = dayprice;
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

    /**
     * @return the showInfos
     */
    public List<ShowInfo> getShowInfos() {
        return showInfos;
    }

    /**
     * @param showInfos the showInfos to set
     */
    public void setShowInfos(List<ShowInfo> showInfos) {
        this.showInfos = showInfos;
    }

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

}
