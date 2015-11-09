/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntertainmentShow.entity;

import ConventionExhibition.entity.Auditorium;
import ConventionExhibition.entity.DayTime;
import ConventionExhibition.entity.HalfDayTime;
import ConventionExhibition.entity.HourTime;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author xing zhe
 */
@Entity
public class ShowSchedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateS;
    
//    @OneToOne 
//    private Theater theater;
//    @OneToOne
//    private Auditorium auditorium;
//    @OneToMany(mappedBy="showSchedule")
//    private Collection<DayTime> dayTimes;
//    @OneToMany(mappedBy="showSchedule")
//    private Collection<HourTime> hourTimes;
//    @OneToMany(mappedBy="showSchedule")
//    private Collection<HalfDayTime> halfDayTimes;
    
    public ShowSchedule(){}
    
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
        if (!(object instanceof ShowSchedule)) {
            return false;
        }
        ShowSchedule other = (ShowSchedule) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntertainmentShow.entity.Schedule[ id=" + getId() + " ]";
    }

    /**
     * @return the dateS
     */
    public Date getDateS() {
        return dateS;
    }

    /**
     * @param dateS the dateS to set
     */
    public void setDateS(Date dateS) {
        this.dateS = dateS;
    }
//
//    /**
//     * @return the theater
//     */
//    public Theater getTheater() {
//        return theater;
//    }
//
//    /**
//     * @param theater the theater to set
//     */
//    public void setTheater(Theater theater) {
//        this.theater = theater;
//    }
//
//    /**
//     * @return the auditorium
//     */
//    public Auditorium getAuditorium() {
//        return auditorium;
//    }
//
//    /**
//     * @param auditorium the auditorium to set
//     */
//    public void setAuditorium(Auditorium auditorium) {
//        this.auditorium = auditorium;
//    }
//
//    /**
//     * @return the dayTimes
//     */
//    public Collection<DayTime> getDayTimes() {
//        return dayTimes;
//    }
//
//    /**
//     * @param dayTimes the dayTimes to set
//     */
//    public void setDayTimes(Collection<DayTime> dayTimes) {
//        this.dayTimes = dayTimes;
//    }
//
//    /**
//     * @return the hourTimes
//     */
//    public Collection<HourTime> getHourTimes() {
//        return hourTimes;
//    }
//
//    /**
//     * @param hourTimes the hourTimes to set
//     */
//    public void setHourTimes(Collection<HourTime> hourTimes) {
//        this.hourTimes = hourTimes;
//    }
//
//    /**
//     * @return the halfDayTimes
//     */
//    public Collection<HalfDayTime> getHalfDayTimes() {
//        return halfDayTimes;
//    }
//
//    /**
//     * @param halfDayTimes the halfDayTimes to set
//     */
//    public void setHalfDayTimes(Collection<HalfDayTime> halfDayTimes) {
//        this.halfDayTimes = halfDayTimes;
//    }
    
}
