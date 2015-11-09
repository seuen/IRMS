/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Accommodation.session;

import Accommodation.entity.Guest;
import Accommodation.entity.RTReservation;
import Accommodation.entity.RoomType;
import CRM.entity.MemberAccount;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author zsy 
 */
@Local
public interface ReservationManagementSessionBeanLocal {
    public List<RTReservation> getAllGuestReservation(String ic);
    public List<RTReservation> getAllReservation();
    public RTReservation getReservationBN(Long reservationNum);
    public RTReservation getReservationBI(String ic);
    public RTReservation addReservation(RTReservation rtreservation);
    public boolean addReservationG(RTReservation rtreservation);
    public boolean addGuest(Guest guest);
    public int setDura(Date dateFrom, Date dateTo);
    public List<RoomType> makeReservation1(Date dateFrom, Date dateTo, int roomQuantity, int guestNum);
    public boolean isPeak(Date today);
    public int updateRoomQuantity(List<RoomType> roomTypes, int roomQuantity, Long roomtypeId);
    public boolean deleteReservation(RTReservation reservation);
    public RTReservation updateReservation(RTReservation reservation, Date dateFrom, Date dateTo, int roomQuantity, int guestNum);
    
    
    //for restful retrieve entity
    public RoomType retrieveRoomTypeForId(Long roomTypeId);
    
}
