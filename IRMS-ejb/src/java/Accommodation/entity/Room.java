
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
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    
    private String roomNum;
    private int floor;
    
    private String status; //status: occupied   free tocheckout maintaining
    private String stay_status; //available; unavailable
    
    private boolean HK_status; // status: clean, dirty
    @OneToMany(mappedBy="room")
    private Collection<Stay> stays;
    @ManyToOne
    private RoomType roomtype;
    
    
    public Room(){
       this.stays=new ArrayList();
    }
    
    public void create(int floor,String status, String roomNum, RoomType roomtype,Boolean HK_status,
                        Collection<Stay> stays){
        this.setFloor(floor);
        this.setStatus(status);
        this.setHK_status(HK_status);
        this.setRoomtype(roomtype);
        this.setRoomNum(roomNum);
        this.setStays(stays);
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
       
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getRoomId() != null ? getRoomId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.getRoomId() == null && other.getRoomId() != null) || (this.getRoomId() != null && !this.roomId.equals(other.roomId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Accommodation.entity.Room[ id=" + getRoomId() + " ]";
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public boolean getHK_status() {
        return HK_status;
    }

    public void setHK_status(boolean HK_status) {
        this.HK_status = HK_status;
    }

    public Collection<Stay> getStays() {
        return stays;
    }

    public void setStays(Collection<Stay> stays) {
        this.stays = stays;
    }

    public RoomType getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(RoomType roomtype) {
        this.roomtype = roomtype;
    }

    public String getStay_status() {
        return stay_status;
    }

    public void setStay_status(String stay_status) {
        this.stay_status = stay_status;
    }
  
}
