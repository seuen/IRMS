/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntertainmentShow.entity;

import ConventionExhibition.entity.Auditorium;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author zsy
 */
@Entity
public class ShowInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String name;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;
    private Float duration;
    private String showType;
    private String detail;
    private Long eventId;
    private Float commissionFee;
    private Float commissionRate;
    
    @ManyToOne
    private Auditorium auditorium;
    @ManyToOne
    private Theater theater;
    @OneToMany(mappedBy="showInfo")
    private Collection<ESShow> shows;

    public ShowInfo(){}
    
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
        if (!(object instanceof ShowInfo)) {
            return false;
        }
        ShowInfo other = (ShowInfo) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntertainmentShow.entity.ShowInfo[ id=" + getId() + " ]";
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
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the duration
     */
    public Float getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(Float duration) {
        this.duration = duration;
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
     * @return the shows
     */
    public Collection<ESShow> getShows() {
        return shows;
    }

    /**
     * @param shows the shows to set
     */
    public void setShows(Collection<ESShow> shows) {
        this.shows = shows;
    }

    /**
     * @return the showType
     */
    public String getShowType() {
        return showType;
    }

    /**
     * @param showType the showType to set
     */
    public void setShowType(String showType) {
        this.showType = showType;
    }

    /**
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail the detail to set
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * @return the eventId
     */
    public Long getEventId() {
        return eventId;
    }

    /**
     * @param eventId the eventId to set
     */
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    /**
     * @return the commissionFee
     */
    public Float getCommissionFee() {
        return commissionFee;
    }

    /**
     * @param commissionFee the commissionFee to set
     */
    public void setCommissionFee(Float commissionFee) {
        this.commissionFee = commissionFee;
    }

    /**
     * @return the commissionRate
     */
    public Float getCommissionRate() {
        return commissionRate;
    }

    /**
     * @param commissionRate the commissionRate to set
     */
    public void setCommissionRate(Float commissionRate) {
        this.commissionRate = commissionRate;
    }
    
}
