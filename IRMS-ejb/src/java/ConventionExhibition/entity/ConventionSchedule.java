
package ConventionExhibition.entity;

import EntertainmentShow.entity.Theater;
import FoodBeverage.entity.BanquetHall;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
public class ConventionSchedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date eventdate;
    private String venuetype;
    private String roomnum;
  
    @ManyToOne(cascade={CascadeType.MERGE})
    private OtherVenue othervenue;
    
    @ManyToOne(cascade={CascadeType.MERGE})
    private Auditorium auditorium;
    
    @ManyToOne(cascade={CascadeType.MERGE})
    private BanquetHall banquethall;
    
    @ManyToOne(cascade={CascadeType.MERGE})
    private ExhibitionSection exhibitionsection;
    
    @ManyToOne(cascade={CascadeType.MERGE})
    private OpenSpace openspace;
    
    @ManyToOne(cascade={CascadeType.MERGE})
    private Theater theater;
    
    @ManyToOne
    private DayTime daytime;
    
    @OneToMany
    private List<HalfDayTime> halfdaytimes;
    
    @OneToMany
    private List<HourTime> hourtimes;
    
    public ConventionSchedule(){
        halfdaytimes=new ArrayList();
        hourtimes=new ArrayList();
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
        if (!(object instanceof ConventionSchedule)) {
            return false;
        }
        ConventionSchedule other = (ConventionSchedule) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConventionExhibition.entity.ConventionSchedule[ id=" + getId() + " ]";
    }


    /**
     * @return the othervenue
     */
    public OtherVenue getOthervenue() {
        return othervenue;
    }

    /**
     * @param othervenue the othervenue to set
     */
    public void setOthervenue(OtherVenue othervenue) {
        this.othervenue = othervenue;
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
     * @return the openspace
     */
    public OpenSpace getOpenspace() {
        return openspace;
    }

    /**
     * @param openspace the openspace to set
     */
    public void setOpenspace(OpenSpace openspace) {
        this.openspace = openspace;
    }

    /**
     * @return the exhibitionsection
     */
    public ExhibitionSection getExhibitionsection() {
        return exhibitionsection;
    }

    /**
     * @param exhibitionsection the exhibitionsection to set
     */
    public void setExhibitionsection(ExhibitionSection exhibitionsection) {
        this.exhibitionsection = exhibitionsection;
    }

    /**
     * @return the halfdaytimes
     */
    public List<HalfDayTime> getHalfdaytimes() {
        return halfdaytimes;
    }

    /**
     * @param halfdaytimes the halfdaytimes to set
     */
    public void setHalfdaytimes(List<HalfDayTime> halfdaytimes) {
        this.halfdaytimes = halfdaytimes;
    }

    /**
     * @return the hourtimes
     */
    public List<HourTime> getHourtimes() {
        return hourtimes;
    }

    /**
     * @param hourtimes the hourtimes to set
     */
    public void setHourtimes(List<HourTime> hourtimes) {
        this.hourtimes = hourtimes;
    }

    /**
     * @return the daytime
     */
    public DayTime getDaytime() {
        return daytime;
    }

    /**
     * @param daytime the daytime to set
     */
    public void setDaytime(DayTime daytime) {
        this.daytime = daytime;
    }

    /**
     * @return the eventdate
     */
    public Date getEventdate() {
        return eventdate;
    }

    /**
     * @param eventdate the eventdate to set
     */
    public void setEventdate(Date eventdate) {
        this.eventdate = eventdate;
    }

    /**
     * @return the banquethall
     */
    public BanquetHall getBanquethall() {
        return banquethall;
    }

    /**
     * @param banquethall the banquethall to set
     */
    public void setBanquethall(BanquetHall banquethall) {
        this.banquethall = banquethall;
    }

    /**
     * @return the venuetype
     */
    public String getVenuetype() {
        return venuetype;
    }

    /**
     * @param venuetype the venuetype to set
     */
    public void setVenuetype(String venuetype) {
        this.venuetype = venuetype;
    }

    /**
     * @return the roomnum
     */
    public String getRoomnum() {
        return roomnum;
    }

    /**
     * @param roomnum the roomnum to set
     */
    public void setRoomnum(String roomnum) {
        this.roomnum = roomnum;
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

}
