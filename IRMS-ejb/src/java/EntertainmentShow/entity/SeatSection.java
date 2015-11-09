/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntertainmentShow.entity;

import ConventionExhibition.entity.Auditorium;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author xing zhe
 */
@Entity
public class SeatSection implements Serializable {
    @Id   
    private String num;
    private int totalNum;
    
    @OneToMany(mappedBy="SeatSection")
    private Collection<Seat> seats;
    @ManyToOne
    private Theater theater;
    @ManyToOne
    private Auditorium auditorium;
    @OneToMany(mappedBy="SeatSection")     
    private Collection<SectionTicket> sectionIickets;
    
    public SeatSection(){}
    
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getNum() != null ? getNum().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the num fields are not set
        if (!(object instanceof SeatSection)) {
            return false;
        }
        SeatSection other = (SeatSection) object;
        if ((this.getNum() == null && other.getNum() != null) || (this.getNum() != null && !this.num.equals(other.num))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntertainmentShow.entity.SeatSection[ id=" + getNum() + " ]";
    }

    /**
     * @return the totalNum
     */
    public int getTotalNum() {
        return totalNum;
    }

    /**
     * @param totalNum the totalNum to set
     */
    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * @return the theater
     */
    public Theater getTheater() {
        return theater;
    }

    /**
     * @param theater the theater to set
     */
    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    /**
     * @return the auditorium
     */
    public Auditorium getAudirium() {
        return auditorium;
    }

    /**
     * @param audirium the auditorium to set
     */
    public void setAudirium(Auditorium audirium) {
        this.auditorium = audirium;
    }


    /**
     * @return the seats
     */
    public Collection<Seat> getSeats() {
        return seats;
    }

    /**
     * @param seats the seats to set
     */
    public void setSeats(Collection<Seat> seats) {
        this.seats = seats;
    }

    /**
     * @return the sectionIickets
     */
    public Collection<SectionTicket> getSectionIickets() {
        return sectionIickets;
    }

    /**
     * @param sectionIickets the sectionIickets to set
     */
    public void setSectionIickets(Collection<SectionTicket> sectionIickets) {
        this.sectionIickets = sectionIickets;
    }    
}
