/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Accommodation.session;

import Accommodation.entity.Guest;
import Accommodation.entity.RTReservation;
import Accommodation.entity.ReservationState;
import Accommodation.entity.RoomType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Acer
 */
@Stateless
public class CustomerReservationSessionBean implements CustomerReservationSessionBeanLocal {
  
    @PersistenceContext
    private EntityManager em;
    private RTReservation reservation;
    private List<RTReservation> reservations;
    private Long reservationNum;
    private Guest guest;
    private int duration;
    private List<ReservationState> reservationStates;
    private List<RoomType> roomTypes;
    private RoomType roomType;
    
    public CustomerReservationSessionBean(){
        
    }
    
    @Override
    public List<RoomType> getAllRoomTypes(String hotelId){
        Query q = em.createQuery("SELECT r FROM RoomType r WHERE r.hotel.name=:hotelName");
        q.setParameter("hotelName", hotelId);
        return q.getResultList();
    }
    
    @Override
    public int setDura(Date dateFrom, Date dateTo) {
        if (dateFrom.before(dateTo)) {
            setDuration((int) (dateTo.getTime() - dateFrom.getTime()) / (24 * 60 * 60 * 1000));
            return getDuration();
        } else {
            return 0;
        }
    }
    
    @Override
    public List<RTReservation> getAllGuestReservation(String ic) {
        
        if (getEm().find(Guest.class, ic) != null) {
            Query query = getEm().createQuery("SELECT r FROM RTReservation r WHERE r.guest.ic=:ic");
            query.setParameter("ic", ic);
            setReservations((List<RTReservation>) query.getResultList());
            if (!reservations.isEmpty()) {
                return getReservations();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    
    @Override
    public List<RTReservation> getAllReservation() {
        Query query = em.createQuery("SELECT r FROM RTReservation r WHERE r.type=:status");
        query.setParameter("status", "guaranteed");
        setReservations((List<RTReservation>) query.getResultList());
        
        return getReservations();
    }
    
    @Override
    public RTReservation getReservationBN(Long reservationNum) {
        if (reservationNum != null) {
            return getEm().find(RTReservation.class, reservationNum);
        }
        return null;
    }
    
    @Override
    public RTReservation getReservationBI(String ic) {
        System.out.println("ic is " + ic);
//        Date today = new Date();
        if (ic != null) {
            if (getEm().find(Guest.class, ic) != null) {
                System.out.println("ic are " + ic);
                guest = getEm().find(Guest.class, ic);
                System.out.println(guest);
//            System.out.println(sdf.format(today));

                Query query = getEm().createQuery("SELECT MAX(r.reservationNum) FROM RTReservation r "
                        + "WHERE r.guest.ic=:ic");
                query.setParameter("ic", ic);
                setReservationNum((Long) query.getSingleResult());
                reservation = getReservationBN(getReservationNum());
                System.out.println(reservation);
                System.out.println(reservation.getDateFrom() + " " + reservation.getDateTo());
                return reservation;
            } else {
                System.err.println("Cannot find reservation, please try again.");
                return null;
            }
        } else {
            System.err.println("inside null ic");
            return null;
        }
    }
    
    @Override
    public List<RoomType> makeReservation1(Date dateFrom, Date dateTo, int roomQuantity, int guestNum) {
        if (dateFrom.before(dateTo)) {
            setRoomTypes((List<RoomType>) new ArrayList());
            
            Query q = getEm().createQuery("SELECT rt FROM RoomType rt WHERE rt.maxGuest>=:guestNum");
            q.setParameter("guestNum", guestNum);
            List<RoomType> rts = (List<RoomType>) q.getResultList();
            
            List<RoomType> availableRoomTypes = new ArrayList<>();
            
            for (RoomType currentRoomType : rts) {
                int minimumCnum = 100;
                
                for (ReservationState currentReservationState : currentRoomType.getReservationStates()) {
                    if (currentReservationState.getRdate().compareTo(dateFrom) >= 0
                            && currentReservationState.getRdate().compareTo(dateTo) <= 0) {
                        if (currentReservationState.getAvailableNum() < minimumCnum) {
                            minimumCnum = currentReservationState.getAvailableNum();
                        }
                    }
                }
                
                if (minimumCnum >= roomQuantity) {
                    availableRoomTypes.add(currentRoomType);
                }
            }
            return availableRoomTypes;
            
        } else {
            System.out.println("invalid date entry");
            return null;
        }
    }
    
    @Override
    public boolean isPeak(Date today) {
        System.err.println("inside isPeak check");
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        System.err.println(month);
        return ((month == Calendar.SEPTEMBER) || (month == Calendar.AUGUST) || (month == Calendar.JULY));
    }
    
    @Override
    public boolean addGuest(Guest guest) {
        System.err.println(guest);
        if (guest != null) {
            if (getEm().find(Guest.class, guest.getIc()) != null) {
                System.out.println("merge");
                em.merge(guest);
                em.flush();
            } else {
                System.out.println("persist");
                em.persist(guest);
                System.out.println("New guest added to the system");
            }
            return true;
        } else {
            System.out.println("Fail to add new guest");
            return false;
        }
        
    }
    
    @Override
    public boolean addReservation(RTReservation rtreservation) {
        if (rtreservation != null) {
            List<ReservationState> rss = (List<ReservationState>) rtreservation.getRoomtype().getReservationStates();
            Date d = rtreservation.getDateFrom();
            
            while (d.before(rtreservation.getDateTo())) {
                int test = 0;
                System.out.println(d);
                for (int i = 0; i < rss.size(); i++) {
                    if (rss.get(i).getRdate().equals(d)) {
                        int temp = rss.get(i).getConfirmbook();
//                        System.out.println(temp);
                        test = 1;
                        ReservationState rs = getEm().find(ReservationState.class, rss.get(i).getReservationStateId());
                        rs.setConfirmbook(temp + rtreservation.getRoomQuantity());
//                        rs.computeGNum();
                        rs.computeAvailableNum();
                        getEm().merge(rs);
                    }
                }
                if (test == 1) {
                    Calendar cal1 = new GregorianCalendar();
                    cal1.setTime(d);
                    cal1.add(Calendar.DAY_OF_YEAR, +1);
                    d = cal1.getTime();
                    System.out.println(d);
                } else {
                    break;
                }
            }

//            RoomType rt=em.find(RoomType.class, rtreservation.getRoomtype().getRoomtypeId());

            getEm().persist(rtreservation);
            getEm().flush();
            System.out.println("rtreservation is " + rtreservation);
            System.out.println("New Reseravtion added to the system");
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public boolean addReservationG(RTReservation rtreservation) {
        List<RTReservation> reservations1 = new ArrayList();
        if (rtreservation != null) {
            Guest guest1 = rtreservation.getGuest();
            Guest result = em.find(Guest.class, guest1.getIc());
            if (result == null) {
                reservations1.add(rtreservation);
                guest1.setReservations(reservations1);
                em.persist(guest1);
            } else {
                reservations1 = (List<RTReservation>) result.getReservations();
                reservations1.add(rtreservation);
                guest1.setReservations(reservations1);
                em.merge(guest1);
            }
            em.persist(rtreservation);
            int roomQuantity = rtreservation.getRoomQuantity();
            List<ReservationState> rss = (List<ReservationState>) rtreservation.getRoomtype().getReservationStates();
            Date startingDate = rtreservation.getDateFrom();
            Date leavingDate = rtreservation.getDateTo();
            
            for (ReservationState currentReservationState : rss) {
                if (currentReservationState.getRdate().compareTo(startingDate) >= 0
                        && currentReservationState.getRdate().compareTo(leavingDate) < 0) {
                    
                    currentReservationState.setBooknum(currentReservationState.getBooknum() + roomQuantity);
                    currentReservationState.computeAvailableNum();
                    em.merge(currentReservationState);
                }
            }
            return true;
        }
        return false;
    }
    
    @Override
    public int updateRoomQuantity(List<RoomType> roomTypes, int roomQuantity, Long roomtypeId) {
        System.err.println(roomTypes + " " + roomQuantity + " " + roomtypeId);
        if (roomTypes != null && roomQuantity != 0 && roomtypeId != null) {
            for (int i = 0; i < roomTypes.size(); i++) {
                if (roomTypes.get(i).getRoomtypeId().equals(roomtypeId)) {
                    if (roomTypes.get(i).getTotalNum() >= roomQuantity) {
                        System.out.println("OK!");
                        return 1;
                    }
                }
            }
            return 2;
        } else {
            System.out.println("FAIL!!");
            return 0;
        }
    }
    
    @Override
    public boolean deleteReservation(RTReservation reservation) {
        Guest rguest = reservation.getGuest();
        RoomType roomtype = reservation.getRoomtype();
        List<ReservationState> rss = (List<ReservationState>) reservation.getRoomtype().getReservationStates();
        List<RTReservation> r1 = (List<RTReservation>) rguest.getReservations();
        List<RTReservation> r2 = (List<RTReservation>) roomtype.getReservations();
        
        if (em.find(RTReservation.class, reservation.getReservationNum()) != null) {
//            r1.set(r1.indexOf(reservation), null);
//            r2.set(r2.indexOf(reservation), null);
            r1.remove(reservation);
            r2.remove(reservation);
            
            RTReservation todelete = em.merge(reservation);
            em.remove(todelete);
            
            for (ReservationState currentReservationState : rss) {
                if (currentReservationState.getRdate().compareTo(reservation.getDateFrom()) >= 0
                        && currentReservationState.getRdate().compareTo(reservation.getDateTo()) < 0) {
                    currentReservationState.setBooknum(currentReservationState.getBooknum() - reservation.getRoomQuantity());
//                    currentReservationState.setConfirmbook(currentReservationState.getConfirmbook()+reservation.getRoomQuantity());
                    currentReservationState.computeAvailableNum();
                    em.merge(currentReservationState);
                }
            }
            
            roomtype.setReservationStates(rss);
            rguest.setReservations(r1);
            roomtype.setReservations(r2);
            em.merge(rguest);
            em.merge(roomtype);
            return true;
            
        } else {
            System.err.println("Reservation not found in the system");
            return false;
        }
    }
    
    @Override
    public RTReservation updateReservation(RTReservation reservation, Date dateFrom, Date dateTo, int roomQuantity, int guestNum) {
        if (deleteReservation(reservation)) {
            RTReservation r = new RTReservation();
            Guest g = reservation.getGuest();
            RoomType roomtype = reservation.getRoomtype();
            
            r.setType("guaranteed");
            r.setStatus("Reserved");
            r.setBundleStatus("False");
            
            r.setDateFrom(dateFrom);
            r.setDateTo(dateTo);
            Date today = new Date();
            r.setReserveDate(today);
            r.setDuration(setDura(dateFrom, dateTo));
            
            r.setRoomtype(roomtype);
            r.setRoomQuantity(roomQuantity);
            if (addGuest(g)) {
                r.setGuest(g);
                if (addReservationG(r)) {
                    deleteReservation(reservation);
                    System.err.println(r);
                    return em.find(RTReservation.class, r.getReservationNum());
                } else {
                    return reservation;
                }
            } else {
                return reservation;
            }
        } else {
            return reservation;
        }
        
    }

    /**
     * @return the reservation
     */
    public RTReservation getReservation() {
        return reservation;
    }

    /**
     * @param reservation the reservation to set
     */
    public void setReservation(RTReservation reservation) {
        this.reservation = reservation;
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
     * @return the reservations
     */
    public List<RTReservation> getReservations() {
        return reservations;
    }

    /**
     * @param reservations the reservations to set
     */
    public void setReservations(List<RTReservation> reservations) {
        this.reservations = reservations;
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
     * @return the reservationStates
     */
    public List<ReservationState> getReservationStates() {
        return reservationStates;
    }

    /**
     * @param reservationStates the reservationStates to set
     */
    public void setReservationStates(List<ReservationState> reservationStates) {
        this.reservationStates = reservationStates;
    }

    /**
     * @return the em
     */
    public EntityManager getEm() {
        return em;
    }

    /**
     * @param em the em to set
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }

    /**
     * @return the roomTypes
     */
    public List<RoomType> getRoomTypes() {
        return roomTypes;
    }

    /**
     * @param roomTypes the roomTypes to set
     */
    public void setRoomTypes(List<RoomType> roomTypes) {
        this.roomTypes = roomTypes;
    }

    /**
     * @return the roomType
     */
    public RoomType getRoomType() {
        return roomType;
    }

    /**
     * @param roomType the roomType to set
     */
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}
