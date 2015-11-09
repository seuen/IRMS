/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FoodBeverage.session;

import Accommodation.entity.Guest;
import FoodBeverage.entity.AvailableTable;
import FoodBeverage.entity.Restaurant;
import FoodBeverage.entity.TableType;
import FoodBeverage.entity.Timeslot;
import FoodBeverage.entity.resReservation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.session.EmailManager2;

/**
 *
 * @author Acer
 */
@Stateless
public class TableReservationSessionBean implements TableReservationSessionBeanLocal {

    @PersistenceContext
    EntityManager em;
    EmailManager2 em2 = new EmailManager2();

    @Override
    public List<resReservation> listtodayreservation() {
        List<resReservation> temp = new ArrayList();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat();
            Date today = sdf.parse(sdf.format(new Date()));
            Query q = em.createQuery("SELECT r FROM resReservation r");
            for (Object o : q.getResultList()) {
                resReservation res = (resReservation) o;
                if (res.getResDate().equals(today)) {
                    temp.add(res);
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(TableReservationSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;

    }

    @Override
    public List<resReservation> listallreservation() {
        List<resReservation> temp = new ArrayList();
        Query q = em.createQuery("SELECT r FROM resReservation r");
        for (Object o : q.getResultList()) {
            resReservation res = (resReservation) o;
            temp.add(res);
        }
        return temp;
    }

    @Override
    public TableType findtabletype(Long Tabletypeid) {
        return em.find(TableType.class, Tabletypeid);
    }

    @Override
    public Guest addguest(Guest guest) {
        Guest temp = em.find(Guest.class, guest.getIc());
        if (temp != null) {
            em.merge(guest);
            return guest;
        } else {
            em.persist(guest);
            return guest;
        }
    }

    @Override
    public Timeslot findtimeslot(String timeslotid) {
        Long id = Long.parseLong(timeslotid);
        Timeslot temp = em.find(Timeslot.class, id);
        return temp;
    }

    @Override
    public void addTableType(TableType tabletype) {
        em.persist(tabletype);
    }

    @Override
    public List<Restaurant> searchTableType(Timeslot timeslot, int peoplenum, Date resDate) {
        List<Restaurant> temp = new ArrayList();
        Query q = em.createQuery("SELECT r FROM Restaurant r");
        for (Object o : q.getResultList()) {
            int i = 0;
            Restaurant res = (Restaurant) o;
            for (TableType tt : res.getTabletypes()) {
                for (AvailableTable at : tt.getAvailabletables()) {
                    if (at.getTimeslot().equals(timeslot) && at.getCountDate().equals(resDate)) {
                        if (at.getAvailableNum() * tt.getCapacity() >= peoplenum) {
                            i++;
                        }
                    }
                }
            }
            if (i != 0) {
                temp.add(res);
            }
        }
        return temp;
    }

    @Override
    public List<TableType> viewResetaurantTable(Restaurant restaurant, Timeslot timeslot, Date resDate, int peoplenum) {
        List<TableType> temp = new ArrayList();
        for (TableType tabletype : restaurant.getTabletypes()) {
            for (AvailableTable at : tabletype.getAvailabletables()) {
                if (at.getTimeslot().equals(timeslot) && at.getCountDate().equals(resDate)) {
                    if (at.getAvailableNum() * tabletype.getCapacity() >= peoplenum) {
                        temp.add(tabletype);
                    }
                }
            }
        }
        return temp;
    }

    @Override
    public void addReservation(resReservation reservation) {
        TableType tabletype = reservation.getTabletype();
        List<resReservation> temp = tabletype.getReservations();
        List<AvailableTable> temptable = tabletype.getAvailabletables();
        Guest guest = reservation.getGuest();
        List<resReservation> tempguestres = guest.getResreservations();
        Timeslot timeslot = reservation.getTimeslot();
        AvailableTable atf = new AvailableTable();
        for (AvailableTable at : temptable) {
            if (at.getTimeslot().equals(timeslot) && at.getCountDate().equals(reservation.getResDate())) {
                atf = at;
            }
        }
        temptable.remove(atf);
        em.persist(reservation);

        tempguestres.add(reservation);
        guest.setResreservations(tempguestres);
        em.merge(guest);

        int avanum = atf.getAvailableNum() - reservation.getAmount();
        atf.setAvailableNum(avanum);
        em.merge(atf);

        temptable.add(atf);
        temp.add(reservation);
        tabletype.setAvailabletables(temptable);
        tabletype.setReservations(temp);
        em.merge(tabletype);
        this.sendEmail(reservation);
    }

    @Override
    public void sendEmail(resReservation reservation) {
        System.err.println("go into send email");
        String time;
        Long timeid = reservation.getTimeslot().getId();
        if (timeid == 1) {
            time = "10:00 - 12:00";
        } else if (timeid == 2) {
            time = "12:00 - 14:00";
        } else if (timeid == 3) {
            time = "15:00 - 17:00";
        } else if (timeid == 4) {
            time = "17:00 - 19:00";
        } else if (timeid == 5) {
            time = "19:00 - 21:00";
        } else {
            time = "21:00 - 23:00";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String reservationdate;
        reservationdate = sdf.format(reservation.getResDate());
        String subject = "[ " + reservation.getTabletype().getRestaurant().getShopName() + " ] Reservation Details";
        String text = "Dear Customer " + reservation.getGuest().getFirstName() + " " + reservation.getGuest().getLastName() + ",\n\n";
        text += "This is your table resevation confirmation details.\n";
        text += "Reservation Id: " + reservation.getId() + "\n\n";
        text += "Table Type: " + reservation.getTabletype().getType() + "\n\n";
        text += "Reservation Amount: " + reservation.getAmount() + "\n\n";
        text += "Reservation Date: " + reservationdate + "\n\n";
        text += "Time Slot: " + time + "\n\n";
        text += "Guest IC: " + reservation.getGuest().getIc() + "\n\n";
        text += "Please bring your IC card as identification when dining. \n\n";
        text += "********************************************************\n\n";
        text += "Thank you for your corporation.\n";
        text += "Best Regards\n";
        text += "Coral Island Resort " + reservation.getTabletype().getRestaurant().getShopName() + " .\n";
        em2.sendEmail(reservation.getGuest().getEmailAddress(), subject, text);


    }

    @Override
    public List<resReservation> searchReservation(String guestIc) {
        List<resReservation> reservations = new ArrayList();
        Guest guest = em.find(Guest.class, guestIc);
        return guest.getResreservations();
    }

    @Override
    public resReservation searchReservationNum(Long reservationid) {
        resReservation reservation = em.find(resReservation.class, reservationid);
        return reservation;
    }

    @Override
    public void deleteReservation(resReservation reservation) {
        TableType tabletype = reservation.getTabletype();
        List<resReservation> temp = tabletype.getReservations();
        List<AvailableTable> temptable = tabletype.getAvailabletables();
        Guest guest = reservation.getGuest();
        List<resReservation> tempguestres = guest.getResreservations();
        Timeslot timeslot = reservation.getTimeslot();
        AvailableTable atf = new AvailableTable();
        for (AvailableTable at : tabletype.getAvailabletables()) {
            if (at.getTimeslot().equals(timeslot) && at.getCountDate().equals(reservation.getResDate())) {
                atf = at;
                
            }
        }
            temptable.remove(atf);

        tempguestres.remove(reservation);
        guest.setResreservations(tempguestres);
        em.merge(guest);

        int avanum = atf.getAvailableNum() + reservation.getAmount();
        atf.setAvailableNum(avanum);
        em.merge(atf);

        temptable.add(atf);
        temp.remove(reservation);
        tabletype.setAvailabletables(temptable);
        tabletype.setReservations(temp);
        em.merge(tabletype);

        resReservation toberemoved=em.merge(reservation);
        em.remove(toberemoved);
    }
}
