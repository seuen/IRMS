/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntertainmentShow.entity;

import ConventionExhibition.entity.Auditorium;
import ConventionExhibition.entity.Client;
import ConventionExhibition.entity.HourTime;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author xing zhe
 */
@Entity
public class ESShow implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String name;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date sDate;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date startTime;
    private int duration;
    
//    @OneToMany
//    private Collection<HalfDayTime> halfDayTimes;
    @OneToOne(cascade={CascadeType.ALL})
    private HourTime hourTime;
//    @OneToMany
//    private Collection<DayTime> dayTimes;
   
   @OneToMany(mappedBy="show")
    private Collection<SectionTicket> sectionTickets;
   @ManyToOne
   private Client client;
   @ManyToOne
   private Auditorium auditorium;
   @ManyToOne
   private ShowInfo showInfo;
   
    public ESShow(){
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "EntertainmentShow.entity.Show[ id=" + getId() + " ]";
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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
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

    /**
     * @return the sectionTickets
     */
    public Collection<SectionTicket> getSectionTickets() {
        return sectionTickets;
    }

    /**
     * @param sectionTickets the sectionTickets to set
     */
    public void setSectionTickets(Collection<SectionTicket> sectionTickets) {
        this.sectionTickets = sectionTickets;
    }
//
//    /**
//     * @return the client
//     */
//    public Client getClient() {
//        return client;
//    }
//
//    /**
//     * @param client the client to set
//     */
//    public void setClient(Client client) {
//        this.client = client;
//    }

    /**
     * @return the sDate
     */
    public Date getsDate() {
        return sDate;
    }

    /**
     * @param sDate the sDate to set
     */
    public void setsDate(Date sDate) {
        this.sDate = sDate;
    }

    /**
     * @return the showInfo
     */
    public ShowInfo getShowInfo() {
        return showInfo;
    }

    /**
     * @param showInfo the showInfo to set
     */
    public void setShowInfo(ShowInfo showInfo) {
        this.showInfo = showInfo;
    }

    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @return the auditorium
     */
    public Auditorium getAuditorium() {
        return auditorium;
    }

    /**
     * @param auditorium the auditorium to set
     */
    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    /**
     * @return the hourTime
     */
    public HourTime getHourTime() {
        return hourTime;
    }

    /**
     * @param hourTime the hourTime to set
     */
    public void setHourTime(HourTime hourTime) {
        this.hourTime = hourTime;
    }
    
}
