/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExternalFoodBeverage;

import Accommodation.entity.Guest;
import FoodBeverage.entity.AvailableTable;
import FoodBeverage.entity.Restaurant;
import FoodBeverage.entity.TableType;
import FoodBeverage.entity.Timeslot;
import FoodBeverage.entity.resReservation;
import FoodBeverage.session.TableReservationSessionBeanLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Acer
 */
@ManagedBean
@SessionScoped
public class ExternalTableReserveManagedBean {

    @EJB
    private TableReservationSessionBeanLocal trsbl;
    private List<Restaurant> availablerestaurants = new ArrayList();
    private List<TableType> availlableTabletypes = new ArrayList();
    private resReservation reservation = new resReservation();
    private Restaurant selectRestaurant = new Restaurant();
    private TableType selecttabletype;
    private String tableamount;
    private Guest guest = new Guest();
    private Date resDate;
    private String timeslotId;
    private int peoplenum;
    private Timeslot timeslot = new Timeslot();
    private int click = 0;
    
    private List<resReservation> allreservations=new ArrayList();
    private List<resReservation> todayreservations=new ArrayList();
    private resReservation deletereservation=new resReservation();

    public ExternalTableReserveManagedBean() {
    }

    public void displayMessage(String response) {
        FacesMessage msg = new FacesMessage(response);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void deletereservation(){
        trsbl.deleteReservation(deletereservation);
        Long resid=deletereservation.getId();
        deletereservation=new resReservation();
        this.displayMessage("Reservation "+resid+" has been deleted");
    }

    public void test() throws IOException {
        setAvaillableTabletypes((List<TableType>) new ArrayList());
        System.err.println("the selecttabletype after select is " + getSelecttabletype());
        FacesContext.getCurrentInstance().getExternalContext().redirect("AddDetails.xhtml");
    }

    public void reservetable() throws IOException {

        if (getClick() == 0) {
            int amount = Integer.parseInt(getTableamount());
            if (amount > getSelecttabletype().getReserveNum()) {
                this.displayMessage("No available Table for " + getSelecttabletype().getType());
            } else {
                getReservation().setAmount(Integer.parseInt(getTableamount()));
                getReservation().setGuest(getTrsbl().addguest(getGuest()));
                getReservation().setResDate(getResDate());
                getReservation().setTimeslot(getTimeslot());
                getReservation().setTabletype(getTrsbl().findtabletype(getSelecttabletype().getId()));
                getReservation().setCurDate(new Date());
                getTrsbl().addReservation(getReservation());
                setClick(getClick() + 1);
                setSelecttabletype(new TableType());
                setGuest(new Guest());
                setResDate(null);
                setReservation(new resReservation());
                 setAvailablerestaurants((List<Restaurant>) new ArrayList());
                setSelectRestaurant(new Restaurant());
                 setAvaillableTabletypes((List<TableType>) new ArrayList());
                this.displayMessage("Reservation Added Successfully, please view your email for Info");
            }
        } else {
            this.displayMessage("Reservation has been added");
        }

    }

    public void navigateReserve(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("SearchAvailableRestaurant.xhtml");
    }
    
    public void getavailablerestaurants() throws IOException {
        click=0;
        System.err.println("get into getavailalble restaurants");
        setTimeslot(getTrsbl().findtimeslot(getTimeslotId()));
        setAvailablerestaurants(getTrsbl().searchTableType(getTimeslot(), getPeoplenum(), getResDate()));
        FacesContext.getCurrentInstance().getExternalContext().redirect("SearchAvailableRestaurant.xhtml");
    }

    public void getavailabletabletypes() throws IOException {
        setAvaillableTabletypes(getTrsbl().viewResetaurantTable(getSelectRestaurant(), getTimeslot(), getResDate(), getPeoplenum()));
        for (TableType temp : getAvaillableTabletypes()) {
            for (AvailableTable at : temp.getAvailabletables()) {
                if (at.getCountDate().equals(getResDate()) && at.getTimeslot().equals(getTimeslot())) {
                    temp.setReserveNum(at.getAvailableNum());
                }
            }
        }
        setAvailablerestaurants((List<Restaurant>) new ArrayList());
        setSelectRestaurant(new Restaurant());
        FacesContext.getCurrentInstance().getExternalContext().redirect("AvailableTableTypes.xhtml");
    }

    /**
     * @return the availablerestaurants
     */
    public List<Restaurant> getAvailablerestaurants() {
        return availablerestaurants;
    }

    /**
     * @param availablerestaurants the availablerestaurants to set
     */
    public void setAvailablerestaurants(List<Restaurant> availablerestaurants) {
        this.availablerestaurants = availablerestaurants;
    }

    /**
     * @return the availlableTabletypes
     */
    public List<TableType> getAvaillableTabletypes() {
        return availlableTabletypes;
    }

    /**
     * @param availlableTabletypes the availlableTabletypes to set
     */
    public void setAvaillableTabletypes(List<TableType> availlableTabletypes) {
        this.availlableTabletypes = availlableTabletypes;
    }

    /**
     * @return the reservation
     */
    public resReservation getReservation() {
        return reservation;
    }

    /**
     * @param reservation the reservation to set
     */
    public void setReservation(resReservation reservation) {
        this.reservation = reservation;
    }

    /**
     * @return the resDate
     */
    public Date getResDate() {
        return resDate;
    }

    /**
     * @param resDate the resDate to set
     */
    public void setResDate(Date resDate) {
        this.resDate = resDate;
    }

    /**
     * @return the timeslotId
     */
    public String getTimeslotId() {
        return timeslotId;
    }

    /**
     * @param timeslotId the timeslotId to set
     */
    public void setTimeslotId(String timeslotId) {
        this.timeslotId = timeslotId;
    }

    /**
     * @return the peoplenum
     */
    public int getPeoplenum() {
        return peoplenum;
    }

    /**
     * @param peoplenum the peoplenum to set
     */
    public void setPeoplenum(int peoplenum) {
        this.peoplenum = peoplenum;
    }

    /**
     * @return the trsbl
     */
    public TableReservationSessionBeanLocal getTrsbl() {
        return trsbl;
    }

    /**
     * @param trsbl the trsbl to set
     */
    public void setTrsbl(TableReservationSessionBeanLocal trsbl) {
        this.trsbl = trsbl;
    }

    /**
     * @return the selectRestaurant
     */
    public Restaurant getSelectRestaurant() {
        return selectRestaurant;
    }

    /**
     * @param selectRestaurant the selectRestaurant to set
     */
    public void setSelectRestaurant(Restaurant selectRestaurant) {
        this.selectRestaurant = selectRestaurant;
    }

    /**
     * @return the timeslot
     */
    public Timeslot getTimeslot() {
        return timeslot;
    }

    /**
     * @param timeslot the timeslot to set
     */
    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    /**
     * @return the tableamount
     */
    public String getTableamount() {
        return tableamount;
    }

    /**
     * @param tableamount the tableamount to set
     */
    public void setTableamount(String tableamount) {
        this.tableamount = tableamount;
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
     * @return the selecttabletype
     */
    public TableType getSelecttabletype() {
        return selecttabletype;
    }

    /**
     * @param selecttabletype the selecttabletype to set
     */
    public void setSelecttabletype(TableType selecttabletype) {
        this.selecttabletype = selecttabletype;
    }

    /**
     * @return the click
     */
    public int getClick() {
        return click;
    }

    /**
     * @param click the click to set
     */
    public void setClick(int click) {
        this.click = click;
    }

    /**
     * @return the allreservations
     */
    public List<resReservation> getAllreservations() {
        allreservations=trsbl.listallreservation();
        return allreservations;
    }

    /**
     * @param allreservations the allreservations to set
     */
    public void setAllreservations(List<resReservation> allreservations) {
        this.allreservations = allreservations;
    }

    /**
     * @return the todayreservations
     */
    public List<resReservation> getTodayreservations() {
        todayreservations=trsbl.listtodayreservation();
        return todayreservations;
    }

    /**
     * @param todayreservations the todayreservations to set
     */
    public void setTodayreservations(List<resReservation> todayreservations) {
        this.todayreservations = todayreservations;
    }

    /**
     * @return the deletereservation
     */
    public resReservation getDeletereservation() {
        return deletereservation;
    }

    /**
     * @param deletereservation the deletereservation to set
     */
    public void setDeletereservation(resReservation deletereservation) {
        this.deletereservation = deletereservation;
    }
}
