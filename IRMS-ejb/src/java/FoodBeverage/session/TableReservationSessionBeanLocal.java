/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FoodBeverage.session;

import Accommodation.entity.Guest;
import FoodBeverage.entity.Restaurant;
import FoodBeverage.entity.TableType;
import FoodBeverage.entity.Timeslot;
import FoodBeverage.entity.resReservation;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Acer
 */
@Local
public interface TableReservationSessionBeanLocal {
    public void addTableType(TableType tabletaype);
    public List<Restaurant> searchTableType(Timeslot timeslot, int peoplenum, Date resDate);
    public List<TableType> viewResetaurantTable(Restaurant restaurant,Timeslot timeslot, Date resDate,int peoplenum);
    public void addReservation(resReservation reservation);
    public List<resReservation> searchReservation(String guestIc);
    public resReservation searchReservationNum(Long reservationid);
    public List<resReservation> listallreservation();
    public List<resReservation> listtodayreservation();
    public void deleteReservation(resReservation reservation);
    public Timeslot findtimeslot(String timeslotid);
    public Guest addguest(Guest guest);
    public TableType findtabletype(Long Tabletypeid);
    public void sendEmail(resReservation reservation);
}
