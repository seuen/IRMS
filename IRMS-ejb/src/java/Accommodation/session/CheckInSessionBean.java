package Accommodation.session;

import Accommodation.entity.RTReservation;
import Accommodation.entity.ReservationState;
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

@Stateless(name="checkInSession")
public class CheckInSessionBean implements CheckInSessionBeanLocal, CheckInSessionBeanRemote {

    @PersistenceContext
    EntityManager em;

    //update confirm reservation in
    @Override
    public boolean updateReservationCheckIn() {
        try {
            Query query = em.createQuery("SELECT r FROM ReservationState r");
            return true;
        } catch (Exception ex) {
            return false;
        }
        //tobefinished
    }

    @Override
    public List<Stay> getcheckinStay() {
        try {
            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
            List<Stay> checkinstays = new ArrayList();
            Date today = sd.parse(sd.format(new Date()));
            System.err.println("the today date is " + today);
            Query q = em.createQuery("SELECT s FROM Stay s");
            for (Object o : q.getResultList()) {
                Stay stay = (Stay) o;
                System.err.println("the datefrom of stay is " + stay.getDateFrom());
                if (stay.getDateFrom().equals(today)) {
                    checkinstays.add(stay);
                    System.err.println("the size of checkinstays is " + checkinstays.size());
                }
            }
            return checkinstays;
        } catch (ParseException ex) {
            Logger.getLogger(CheckInSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.err.println("something happening after return checkinstays");

        return null;
    }

    @Override
    public boolean updateReservation(RTReservation reservation) {
        try {
            em.merge(reservation);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean checkCreservation(RTReservation reservation) {
        try {
            Date checkin = reservation.getDateFrom();
            ReservationState rs = new ReservationState();
            for (ReservationState currentReservationState : reservation.getRoomtype().getReservationStates()) {
                if (currentReservationState.getRdate().equals(checkin)) {
                    rs = currentReservationState;
                }
            }
            float oRatio = reservation.getRoomtype().getORatio();
            int todayconfirmed = rs.getTodayconfirm();
            int confirmquota = (int) Math.ceil(rs.getConfirmbook() / oRatio);
            int availableNo = rs.getAvailableNum();
            if (todayconfirmed >= confirmquota) {
                if (availableNo >= reservation.getRoomQuantity()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (confirmquota + availableNo - todayconfirmed >= reservation.getRoomQuantity()) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (Exception ex) {
            return true;
        }

    }
}
