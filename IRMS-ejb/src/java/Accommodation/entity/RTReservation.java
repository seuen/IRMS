
package Accommodation.entity;

import CRM.entity.MemberAccount;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;


@Entity
public class RTReservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationNum;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFrom;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateTo;
    
    private int duration;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date reserveDate;
    
    private int roomQuantity;
    private String status;  //status: reserving cancelled and past
    private String bundleStatus; //bundleStatus: promotion name
    private String type;      //type: confirmed or guaranteed
    
   
    @ManyToOne
    private RoomType roomtype;
    
    @ManyToOne
    private Guest guest;
    
    @ManyToOne
    private MemberAccount member;
    
    public RTReservation(){
    }

    @Override
    public String toString() {
        return "Accommodation.entity.RTReservation[ id=" + getReservationNum() + " ]";
    }

    /**
     * @return the reservationNum
     */
    public Long getReservationNum() {
        return reservationNum;
    }

    /**
     * @param reservationNum the reservationNum to set
     */
    public void setReservationNum(Long reservationNum) {
        this.reservationNum = reservationNum;
    }

    /**
     * @return the dateFrom
     */
    public Date getDateFrom() {
        return dateFrom;
    }

    /**
     * @param dateFrom the dateFrom to set
     */
    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     * @return the dateTo
     */
    public Date getDateTo() {
        return dateTo;
    }

    /**
     * @param dateTo the dateTo to set
     */
    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
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

    /**
     * @return the reserveDate
     */
    public Date getReserveDate() {
        return reserveDate;
    }

    /**
     * @param reserveDate the reserveDate to set
     */
    public void setReserveDate(Date reserveDate) {
        this.reserveDate = reserveDate;
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
     * @return the bundleStatus
     */
    public String getBundleStatus() {
        return bundleStatus;
    }

    /**
     * @param bundleStatus the bundleStatus to set
     */
    public void setBundleStatus(String bundleStatus) {
        this.bundleStatus = bundleStatus;
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
     * @return the roomtype
     */
    public RoomType getRoomtype() {
        return roomtype;
    }

    /**
     * @param roomtype the roomtype to set
     */
    public void setRoomtype(RoomType roomtype) {
        this.roomtype = roomtype;
    }

    /**
     * @return the guest
     */
    public Guest getGuest() {
        return guest;
    }

    /**
     * @param guest the guest to set
     */
    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    /**
     * @return the member
     */
    public MemberAccount getMember() {
        return member;
    }

    /**
     * @param member the member to set
     */
    public void setMember(MemberAccount member) {
        this.member = member;
    }

    /**
     * @return the roomQuantity
     */
    public int getRoomQuantity() {
        return roomQuantity;
    }

    /**
     * @param roomQuantity the roomQuantity to set
     */
    public void setRoomQuantity(int roomQuantity) {
        this.roomQuantity = roomQuantity;
    }
    
}
