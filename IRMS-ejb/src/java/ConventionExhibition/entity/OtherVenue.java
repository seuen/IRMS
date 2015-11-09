/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
public class OtherVenue implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private int capacity;
    private float hourprice;
    private float halfdayprice;
    private float dayprice;
    private String roomNo;
    
    @OneToMany(mappedBy="othervenue")
    private List<ConventionSchedule> cschedules;
    
    public OtherVenue(){
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
        if (!(object instanceof OtherVenue)) {
            return false;
        }
        OtherVenue other = (OtherVenue) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConventionExhibition.entity.OtherVenue[ id=" + getId() + " ]";
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
     * @return the hourprice
     */
    public float getHourprice() {
        return hourprice;
    }

    /**
     * @param hourprice the hourprice to set
     */
    public void setHourprice(float hourprice) {
        this.hourprice = hourprice;
    }

    /**
     * @return the halfdayprice
     */
    public float getHalfdayprice() {
        return halfdayprice;
    }

    /**
     * @param halfdayprice the halfdayprice to set
     */
    public void setHalfdayprice(float halfdayprice) {
        this.halfdayprice = halfdayprice;
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

}
