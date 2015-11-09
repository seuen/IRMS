 
package Accommodation.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;


@Entity
public class ReservationState implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationStateId;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date rdate;
    private int spareNum;
    private int availableNum;
    private int booknum;
    private int confirmbook;
    private int todayconfirm;
    
    @ManyToOne
    private RoomType roomtype;
    
    public ReservationState(){
       
        this.booknum=0;
        this.confirmbook=0;
        this.todayconfirm=0;
    }

    public void initialize(RoomType roomtype){
        this.roomtype=roomtype;
         this.spareNum=roomtype.getTotalNum();
        this.availableNum=spareNum;
    }
    public void computeAvailableNum(){
        float oratio=roomtype.getORatio();
        int confirm=(int) Math.ceil(confirmbook/oratio);
        availableNum=spareNum-booknum-confirm;
    }
    public int getSpareNum() {
        return spareNum;
    }

    public void setSpareNum(int spareNum) {
        this.spareNum = spareNum;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getReservationStateId() != null ? getReservationStateId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ReservationState)) {
            return false;
        }
        ReservationState other = (ReservationState) object;
        if ((this.getReservationStateId() == null && other.getReservationStateId() != null) || (this.getReservationStateId() != null && !this.reservationStateId.equals(other.reservationStateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Accommodation.entity.ReservationState[ id=" + getReservationStateId() + " ]";
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }

    public RoomType getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(RoomType roomtype) {
        this.roomtype = roomtype;
    }

    public Long getReservationStateId() {
        return reservationStateId;
    }

    public void setReservationStateId(Long reservationStateId) {
        this.reservationStateId = reservationStateId;
    }

    public int getAvailableNum() {
        return availableNum;
    }

    public void setAvailableNum(int availableNum) {
        this.availableNum = availableNum;
    }

    public int getBooknum() {
        return booknum;
    }

    public void setBooknum(int booknum) {
        this.booknum = booknum;
    }

    public int getConfirmbook() {
        return confirmbook;
    }

    public void setConfirmbook(int confirmbook) {
        this.confirmbook = confirmbook;
    }

    public int getTodayconfirm() {
        return todayconfirm;
    }

    public void setTodayconfirm(int todayconfirm) {
        this.todayconfirm = todayconfirm;
    }
    
}
