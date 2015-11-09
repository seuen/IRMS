/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Accommodation.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class RoomType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomtypeId;
    
    private String type;
    private int totalNum;
    private String detail;
    private float price_h;
    private float price_l;
    private int bedNum;
    private float oRatio;
    private int maxGuest;
    
    @ManyToOne
    private Hotel hotel;
    @OneToMany
    private Collection<Picture> pictures; 
    @OneToMany(mappedBy="roomtype")
    private Collection<RTReservation> reservations;
    @OneToMany(mappedBy="roomtype")
    private Collection<Room> rooms;
    @OneToMany(mappedBy="roomtype")
    private Collection<ReservationState> reservationStates;
    
    
   
    public RoomType(){
        reservationStates=new ArrayList();
        pictures=new ArrayList();
        reservations=new ArrayList();
        rooms=new ArrayList();
      
    }
  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getRoomtypeId() != null ? getRoomtypeId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomType)) {
            return false;
        }
        RoomType other = (RoomType) object;
        if ((this.getRoomtypeId() == null && other.getRoomtypeId() != null) || (this.getRoomtypeId() != null && !this.roomtypeId.equals(other.roomtypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Accommodation.entity.RoomType[ id=" + getRoomtypeId() + " ]";
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public float getPrice_l() {
        return price_l;
    }

    public void setPrice_l(float price_l) {
        this.price_l=price_l;
    }

    public int getBedNum() {
        return bedNum;
    }

    public void setBedNum(int bedNum) {
        this.bedNum = bedNum;
    }

    
    public float getORatio() {
        return oRatio;
    }

    public void setORatio(float oRatio) {
        this.oRatio = oRatio;
    }

    public int getMaxGuest() {
        return maxGuest;
    }

    public void setMaxGuest(int maxGuest) {
        this.maxGuest = maxGuest;
    }

    public Collection<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Collection<Picture> pictures) {
        this.pictures = pictures;
    }

    public Collection<RTReservation> getReservations() {
        return reservations;
    }

    public void setReservations(Collection<RTReservation> reservations) {
        this.reservations = reservations;
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public Long getRoomtypeId() {
        return roomtypeId;
    }

    public void setRoomtypeId(Long roomtypeId) {
        this.roomtypeId = roomtypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Collection<ReservationState> getReservationStates() {
        return reservationStates;
    }

    public void setReservationStates(Collection<ReservationState> reservationStates) {
        this.reservationStates = reservationStates;
    }

    public float getPrice_h() {
        return price_h;
    }

    public void setPrice_h(float price_h) {
        this.price_h = price_h;
    }

}
