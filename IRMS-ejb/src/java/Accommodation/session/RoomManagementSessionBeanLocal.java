/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Accommodation.session;

import Accommodation.entity.Room;
import Accommodation.entity.Stay;
import CRM.entity.MemberAccount;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface RoomManagementSessionBeanLocal {
    public boolean checkRoom(Room room,Date startingDate,Date leavingDate);
    public List<Room> getHotelRoom(String hotelId);
     public void addStay(Stay stay,Room room);
     public void createStay(Stay stay,Room room);
     public float checkPrice(Date startingDate,Date endingDate,Room room);
     public void maintain(Room room);
     public void freeRoom(Room room);
     public void updateReservationStateWalkIn(Date startingDate, Date leavingDate,Room room);
     public void cleanRoom(List<Room> rooms);
     public void dirtyRoom(List<Room> rooms);
     public MemberAccount findmember(Long memberId);
     public Room findroom(Long roomid);
  
} 
