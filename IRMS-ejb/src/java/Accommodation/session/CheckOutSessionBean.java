package Accommodation.session;

import Accommodation.entity.ReservationState;
import Accommodation.entity.Room;
import Accommodation.entity.Stay;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name="checkOutSession")
public class CheckOutSessionBean implements CheckOutSessionBeanLocal, CheckOutSessionBeanRemote {

    @PersistenceContext
    EntityManager em;

    @Override
    public boolean updateHKstatus() {
        try {
            Query q = em.createQuery("SELECT r FROM Room r");
            for (Object o : q.getResultList()) {
                Room room = (Room) o;
                if (room.getStatus().equals("free") || room.getStatus().equals("occupied")) {
                    room.setHK_status(false);
                    em.merge(room);
                    em.flush();
                }
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean updateRooms() {
        try {
            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
            Date today = sd.parse(sd.format(new Date()));
            Query q = em.createQuery("SELECT s FROM Stay s");
            for (Object o : q.getResultList()) {
                Stay stay = (Stay) o;
                if (stay.isStatus() && stay.getDateTo().equals(today)) {
                    Room room = stay.getRoom();
                    room.setStatus("tocheckout");
                    em.merge(room);
                    em.flush();
                }
            }
            return true;
        } catch (ParseException ex) {
            Logger.getLogger(CheckOutSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }


    }

    @Override
    public boolean payCharges(Stay stay) {
        try {
            stay.setTotalCharges(0);
            em.merge(stay);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean checkout(Stay stay) {
        try {
            Room room = stay.getRoom();
            room.setStatus("free");
            room.setStay_status("available");
            room.setHK_status(Boolean.FALSE);
            em.merge(room);

//    List<Guest> guests=new ArrayList();
//    guests=(List<Guest>) stay.getGuests();
            stay.setGuests(null);
            stay.setStatus(false);
            em.merge(stay);
            em.flush();
            if (stay.getGuests() != null) {
                System.err.println("the stay has not been updated");
            }
            if (stay.isStatus()) {
                System.out.println("the stay status is still true");
            }
            System.err.println("change the room status in stay");
//      for(Guest currentGuest:guests){
//        if(currentGuest.getReservations().isEmpty()){
//            System.err.println("before remove the guest");
//            Guest tobeRemoved=em.merge(currentGuest);
//            em.remove(tobeRemoved);
//            em.flush();
//            System.err.println("after remove the guest");
//        }
//    }

            Date today = new Date();
            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
            try {
                today = sd.parse(sd.format(today));
            } catch (ParseException ex) {
                Logger.getLogger(CheckOutSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (today.before(stay.getDateTo())) {
                for (ReservationState currentReservationState : stay.getRoom().getRoomtype().getReservationStates()) {
                    if (currentReservationState.getRdate().compareTo(today) >= 0
                            && currentReservationState.getRdate().compareTo(stay.getDateTo()) < 0) {
                        int ava = currentReservationState.getAvailableNum();
                        currentReservationState.setAvailableNum(ava + 1);
                        em.merge(currentReservationState);
                        if (ava == currentReservationState.getAvailableNum() - 1) {
                            System.err.println("reservationstate updated successfully");
                        }
                    }
                }
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<Stay> getCheckOutStay(Date checkout) {
        try {
            System.out.println("before date format, the checkout date is " + checkout);
            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
            try {
                checkout = sd.parse(sd.format(checkout));
            } catch (ParseException ex) {
                Logger.getLogger(CheckOutSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            List<Stay> stays = new ArrayList();
            Query query = em.createQuery("SELECT s FROM Stay s");
            for (Object o : query.getResultList()) {
                Stay stay = (Stay) o;
                Date leavingDate = stay.getDateTo();
                System.err.println("after date format checkout date is " + checkout);
                System.err.println("leaving date is " + leavingDate);
                if (checkout.equals(leavingDate)) {
                    stays.add(stay);
                }
            }
            System.err.println("the size of stay is " + stays.size());
            return stays;
        } catch (Exception ex) {
            return null;
        }
    }
}
