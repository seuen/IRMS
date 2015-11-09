/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author zsy
 */
@Entity
public class AttraSection implements Serializable {
    @Id
    private String num;
    private String status;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date nextDate;
    private int totalEquipNum;

    @OneToMany(mappedBy="attraSection")
    private Collection<Equipment> equipments;
    @ManyToOne
    private Attraction attraction;
    
    @Override
    public String toString() {
        return "Attraction.entity.Section[ num=" + getNum() + " ]";
    }

    /**
     * @return the num
     */
    public String getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(String num) {
        this.num = num;
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
     * @return the totalEquipNum
     */
    public int getTotalEquipNum() {
        return totalEquipNum;
    }

    /**
     * @param totalEquipNum the totalEquipNum to set
     */
    public void setTotalEquipNum(int totalEquipNum) {
        this.totalEquipNum = totalEquipNum;
    }

    /**
     * @return the equipments
     */
    public Collection<Equipment> getEquipments() {
        return equipments;
    }

    /**
     * @param equipments the equipments to set
     */
    public void setEquipments(Collection<Equipment> equipments) {
        this.equipments = equipments;
    }

    /**
     * @return the attraction
     */
    public Attraction getAttraction() {
        return attraction;
    }

    /**
     * @param attraction the attraction to set
     */
    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    /**
     * @return the nextDate
     */
    public Date getNextDate() {
        return nextDate;
    }

    /**
     * @param nextDate the nextDate to set
     */
    public void setNextDate(Date nextDate) {
        this.nextDate = nextDate;
    }

}
