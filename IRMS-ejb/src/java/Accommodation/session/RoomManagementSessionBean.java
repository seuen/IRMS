package Accommodation.session;

import Accommodation.entity.Guest;
import Accommodation.entity.Hotel;
import Accommodation.entity.ReservationState;
import Accommodation.entity.Room;
import Accommodation.entity.RoomType;
import Accommodation.entity.Stay;
import CRM.entity.MemberAccount;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class RoomManagementSessionBean implements RoomManagementSessionBeanLocal {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Room findroom(Long roomid){
        return em.find(Room.class, roomid);
    }
    
    @Override
     public MemberAccount findmember(Long memberId){
        System.err.println("member id is "+memberId);
         return em.find(MemberAccount.class, memberId);
     }

    @Override
    public void cleanRoom(List<Room> rooms){
        for(Room currentRoom: rooms){
            currentRoom.setHK_status(Boolean.TRUE);
            em.merge(currentRoom);
        }
    
    }
    
    @Override
     public void dirtyRoom(List<Room> rooms){
         for(Room currentRoom: rooms){
            currentRoom.setHK_status(Boolean.FALSE);
            em.merge(currentRoom);
        }
     }
    @Override
    public List<Room> getHotelRoom(String hotelId) {
        System.err.println("before get hotel info");
        Hotel hotel = em.find(Hotel.class, hotelId);
        System.err.println("get hotel info");
        List<Room> roomList = new ArrayList();
        for (Object o : hotel.getRoomTypes()) {
            RoomType type = (RoomType) o;
            for (Object obj : type.getRooms()) {
                Room room = (Room) obj;
                roomList.add(room);
            }
        }
        return roomList;
    }

    @Override
    public boolean checkRoom(Room room, Date startingDate, Date leavingDate) {
        try {
            System.err.println(room.getStay_status());
            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
            Date start = sd.parse(sd.format(startingDate));
            Date leave = sd.parse(sd.format(leavingDate));
            System.out.println("the start Date " + start);
            System.out.println("the leave Date " + leave);
            if (room.getStay_status().equals("available")) {
                for (ReservationState currentReservationState : room.getRoomtype().getReservationStates()) {
                    if (currentReservationState.getRdate().compareTo(start) >= 0
                            && currentReservationState.getRdate().compareTo(leave) < 0) {
                        System.out.println("the rDate meet requiremtns is " + currentReservationState.getRdate());
                        System.out.println("the available num of this rDate is " + currentReservationState.getAvailableNum());
                        if (currentReservationState.getAvailableNum() == 0) {
                            return false;
                        }
                    }
                }
                return true;
            }
        } catch (ParseException ex) {
            Logger.getLogger(RoomManagementSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void addStay(Stay stay, Room room) {
        List<Stay> stays = new ArrayList();
        for(Guest currentGuest: stay.getGuests()){
            if(em.find(Guest.class, currentGuest.getIc())==null)
                em.persist(currentGuest);
        }
        stay.setTotalCharges(0);
        em.persist(stay);
        stays = (List<Stay>) room.getStays();
        stays.add(stay);
        room.setStatus("occupied");
        room.setStay_status("unavailable");
        room.setStays(stays);
        em.merge(room);
        System.err.println("after merge room insession bean");
        System.err.println("the stay member is "+stay.getMember());
        if(stay.getMember()!=null){
            System.err.println("go into set member insession bean");
            MemberAccount mem=stay.getMember();
            List<Stay> memstays=mem.getStays();
            memstays.add(stay);
            mem.setStays(memstays);
            em.merge(mem);
            em.flush();
        }
        System.err.println("the status of room is " + room.getStatus());
    }

    @Override
    public void createStay(Stay stay, Room room) {
        em.persist(stay);
        List<Stay> stays = new ArrayList();
        stays = (List<Stay>) room.getStays();
        stays.add(stay);
        room.setStays(stays);
        em.merge(room);
    }

    @Override
    public float checkPrice(Date startingDate, Date endingDate, Room room) {
        float price = 0;
        int month;
        Calendar ca = new GregorianCalendar();

        do {
            month = ca.get(Calendar.MONTH);
            if (month == 6 || month == 7 || month == 11 || month == 12) {
                price = price + room.getRoomtype().getPrice_h();
            } else {
                price = price + room.getRoomtype().getPrice_l();
            }
            ca.setTime(startingDate);
            ca.add(Calendar.DATE, 1);
            startingDate = ca.getTime();
        } while (startingDate.before(endingDate));
        return price;
    }

    @Override
    public void maintain(Room room) {
        room.setStatus("maintaining");
        room.setStay_status("unavailable");
        em.merge(room);
        Date today=new Date();
        SimpleDateFormat sd=new SimpleDateFormat("dd/MM/yyyy");
        try {
            today=sd.parse(sd.format(today));
        } catch (ParseException ex) {
            Logger.getLogger(RoomManagementSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
       for(ReservationState rs: room.getRoomtype().getReservationStates())
            if(rs.getRdate().compareTo(today)>0){
                if(rs.getAvailableNum()>0){
               rs.setAvailableNum(rs.getAvailableNum()-1);
               em.merge(rs);
                }
            }
    }

    @Override
    public void freeRoom(Room room) {
        room.setStatus("free");
        room.setStay_status("available");
        em.merge(room);
         Date today=new Date();
        SimpleDateFormat sd=new SimpleDateFormat("dd/MM/yyyy");
        try {
            today=sd.parse(sd.format(today));
        } catch (ParseException ex) {
            Logger.getLogger(RoomManagementSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(ReservationState rs: room.getRoomtype().getReservationStates()){
            if(rs.getRdate().compareTo(today)>=0){
               rs.setAvailableNum(rs.getAvailableNum()+1);
               em.merge(rs);
            }
        }
    }

    @Override
    public void updateReservationStateWalkIn(Date startingDate, Date leavingDate, Room room) {
        try {
            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");

            Date start = sd.parse(sd.format(startingDate));
            Date leave = sd.parse(sd.format(leavingDate));
            System.err.println("startingDate is " + start);
            System.err.println("leavingDate is " + leave);
            System.err.println("go into the update reservation state");

            for (ReservationState currentReservationState : room.getRoomtype().getReservationStates()) {
                if (currentReservationState.getRdate().compareTo(start) >= 0
                        && currentReservationState.getRdate().compareTo(leave) < 0) {
                    System.err.println(currentReservationState.getRdate());
                    int book = currentReservationState.getBooknum() + 1;
                    currentReservationState.setBooknum(book);
                    currentReservationState.computeAvailableNum();
                    em.merge(currentReservationState);
                    System.err.println("the available num of " + room.getRoomNum() + " is "
                            + currentReservationState.getAvailableNum() + " on " + currentReservationState.getRdate());
                    System.err.println("the book num is " + currentReservationState.getBooknum());
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(RoomManagementSessionBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
