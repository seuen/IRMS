package Accommodation;

import Accommodation.entity.Guest;
import Accommodation.entity.RTReservation;
import Accommodation.entity.ReservationState;
import Accommodation.entity.Room;
import Accommodation.entity.RoomType;
import Accommodation.entity.Stay;
import Accommodation.session.CheckInSessionBeanLocal;
import Accommodation.session.ReservationManagementSessionBeanLocal;
import Accommodation.session.RoomManagementSessionBeanLocal;
import CRM.entity.MemberAccount;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class CheckInManagedBean implements Serializable{

    @EJB
    RoomManagementSessionBeanLocal rmsbl;
    @EJB
    CheckInSessionBeanLocal cisbl;
    @EJB
    ReservationManagementSessionBeanLocal remsbl;
    
    private List<Room> rooms = new ArrayList();
    private RTReservation reservation = new RTReservation();
    private List<Room> selectedRooms = new ArrayList();
    private List<Guest> guests = new ArrayList();
    private Guest guest = new Guest();
    private int guestNo = 1;
    private float totalprice = 0;
    private Stay stay = new Stay();
    private List<Stay> stays = new ArrayList();
    private Date startingDate;
    private Date leavingDate;
    private int guestnum;
    private int roomnum;
    private List<RoomType> roomtypes = new ArrayList();
    private List<Stay> checkinStays=new ArrayList();
    
    private Long memberId;
    private MemberAccount mem;
    private List<String> selectroomnums;
    private String selectedroomnum;
    private List<String> guestname=new ArrayList();
    private String firstname;
    private String lastname;

    public CheckInManagedBean() {
    }

    public void searchRoomType() throws IOException {
        System.err.println(roomnum);
        setRoomtypes(remsbl.makeReservation1(startingDate, leavingDate, roomnum, guestnum));

        if (getRoomtypes() != null) {
//            for (RoomType rt : roomTypes) {
//                System.err.println("Available Room Type: " + rt.getType());
//            }

            for (RoomType currentRoomType : getRoomtypes()) {
                int minimumCnum = 100;
                System.err.println(currentRoomType);
                for (ReservationState currentReservationState : currentRoomType.getReservationStates()) {
                    if (currentReservationState.getRdate().compareTo(startingDate) >= 0
                            && currentReservationState.getRdate().compareTo(leavingDate) <= 0) {
                        if (currentReservationState.getAvailableNum() < minimumCnum) {
                            minimumCnum = currentReservationState.getAvailableNum();
                        }
                        System.out.println(minimumCnum);
                    }
                }
                currentRoomType.setTotalNum(minimumCnum);
            }
            startingDate=null;
            leavingDate=null;
            guestnum=1;
            roomnum=1;
            FacesContext.getCurrentInstance().getExternalContext().redirect("AvailableRoomtypes.xhtml");
        }else{
            FacesMessage msg=new FacesMessage("No match rooms in other hotels");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void clear() throws IOException {
        selectedRooms = new ArrayList();
        guests = new ArrayList();
        reservation = new RTReservation();
        guestNo = 1;
        totalprice = 0;
        FacesContext.getCurrentInstance().getExternalContext().redirect("searchReservation.xhtml");
    }

    public void createStay() throws IOException {
        int i=0;
        List<Guest> temp=new ArrayList();
        temp.add(guest);
        float total;
        for (Room currentRoom : selectedRooms) {
            stay = new Stay();
            stay.setStatus(true);
            stay.setDateFrom(reservation.getDateFrom());
            stay.setDateTo(reservation.getDateTo());
            stay.setRoom(currentRoom);
            stay.setTotalCharges(0);
            stay.setMember(mem);
            if(mem!=null){
                total=(float) (rmsbl.checkPrice(reservation.getDateFrom(), reservation.getDateTo(), currentRoom)*0.95);
            }else{
               total=rmsbl.checkPrice(reservation.getDateFrom(), reservation.getDateTo(), currentRoom);
            }
            stay.setTotalPrice(total);
           stay.setGuestnames(guestname);
            stay.setGuests(temp);
            
            rmsbl.addStay(stay, currentRoom);
            reservation.setStatus("checkedin");
            cisbl.updateReservation(reservation);
            temp=new ArrayList();
            getStays().add(stay);
        }
        selectedRooms = new ArrayList();
        guests = new ArrayList();
        reservation = new RTReservation();
        guestNo = 1;
        FacesContext.getCurrentInstance().getExternalContext().redirect("ConfirmReservationStay.xhtml");
    }

    public void displayStay() throws IOException {
        guestname.add(firstname+" "+lastname);
        lastname=null;
        firstname=null;
        for (Room currentroom : selectedRooms) {
            setTotalprice(getTotalprice() + rmsbl.checkPrice(reservation.getDateFrom(), reservation.getDateTo(), currentroom));
        }
        System.out.println("the size of guests is " + guests.size());
        System.out.println("before direct to page");
        FacesContext.getCurrentInstance().getExternalContext().redirect("ConfirmRCheckinStay.xhtml");
    }

    public void checkGuest() throws IOException {
       
        RoomType roomtype = reservation.getRoomtype();
        int maxguest = roomtype.getMaxGuest() * reservation.getRoomQuantity();
        if (guestname.size() < maxguest - 1) {
            guestname.add(firstname+" "+lastname);
            firstname=null;
            lastname=null;
            guestNo++;
            FacesContext.getCurrentInstance().getExternalContext().redirect("addRCheckinGuestName.xhtml");
        } else {
            FacesMessage msg = new FacesMessage("The no of guest has excced the max num " + maxguest);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
   

    public void checkRoom() throws IOException {
        if (reservation.getRoomQuantity() > selectedRooms.size()) {
            FacesMessage msg = new FacesMessage("Select more rooms, please uncheck some rooms");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (reservation.getRoomQuantity() < selectedRooms.size()) {
            FacesMessage msg1 = new FacesMessage("Select not enough rooms, please choose more rooms");
            FacesContext.getCurrentInstance().addMessage(null, msg1);
        } else {
            int i = 0;
            for (Room currentRoom : selectedRooms) {
                if (currentRoom.getStatus().equals("occupied") || currentRoom.getStatus().equals("maintaining")) {
                    i++;
                }
            }
            if (i != 0) {
                FacesMessage msg2 = new FacesMessage("Some rooms chose are not available, please rechoose rooms");
                FacesContext.getCurrentInstance().addMessage(null, msg2);
            } else {
                
                FacesContext.getCurrentInstance().getExternalContext().redirect("AddRMember.xhtml");
            }
        }
    }
    
    public void setguestInfo() throws IOException{
        System.err.println("first name is "+guest.getFirstName());
        System.err.println("last naem is "+guest.getLastName());
        guestname.add(guest.getFirstName()+" "+guest.getLastName());
        guestNo++;
        FacesContext.getCurrentInstance().getExternalContext().redirect("addRCheckinGuestName.xhtml");
    }
    
    public void displayMessage(String response){
        FacesMessage msg=new FacesMessage(response);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void addmember() throws IOException{
        mem=rmsbl.findmember(memberId);
         if(mem==null){
            this.displayMessage("No such member, Pls skil this step");
        }else{
        FacesContext.getCurrentInstance().getExternalContext().redirect("addRCheckinGuest.xhtml");
        }
    }

    public void checkIn(RTReservation reservation1) throws IOException {
        Date today = new Date();
        Date start = reservation1.getDateFrom();
        if (reservation1.getStatus().equals("checkedin")) {
            FacesMessage msg = new FacesMessage("This reservation has been checked in", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (start.after(today)) {
            FacesMessage msg1 = new FacesMessage("The check-in date has not arrived, please come at " + start);
            FacesContext.getCurrentInstance().addMessage(null, msg1);
        }else {
            setReservation(reservation1);
            if (reservation1.getType().equals("guaranteed")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("ChooseReservationRoom.xhtml");
            } else {
                if(cisbl.checkCreservation(reservation))
                    FacesContext.getCurrentInstance().getExternalContext().redirect("ChooseReservationRoom.xhtml");
                else{
                    FacesMessage msg2=new FacesMessage("No enought rooms for confirmed books");
                    FacesContext.getCurrentInstance().addMessage(null, msg2);
                }

            }
        }
    }

    public List<Room> getRooms() {
        RoomType roomtype = getReservation().getRoomtype();
        rooms = (List<Room>) roomtype.getRooms();
        for (Room currentRoom : rooms) {
            System.out.println("the room num is " + currentRoom.getRoomNum() + " and room Id is " + currentRoom.getRoomId());
        }
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
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
     * @return the selectedRooms
     */
    public List<Room> getSelectedRooms() {
        return selectedRooms;
    }

    /**
     * @param selectedRooms the selectedRooms to set
     */
    public void setSelectedRooms(List<Room> selectedRooms) {
        this.selectedRooms = selectedRooms;
    }

    /**
     * @return the guests
     */
    public List<Guest> getGuests() {
        System.out.println("the size of guests is " + guests.size());
        return guests;
    }

    /**
     * @param guests the guests to set
     */
    public void setGuests(List<Guest> guests) {
        this.guests = guests;
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
     * @return the guestNo
     */
    public int getGuestNo() {
        return guestNo;
    }

    /**
     * @param guestNo the guestNo to set
     */
    public void setGuestNo(int guestNo) {
        this.guestNo = guestNo;
    }

    /**
     * @return the totalprice
     */
    public float getTotalprice() {

        System.out.println("the total price in get function is " + totalprice);
        return totalprice;
    }

    /**
     * @param totalprice the totalprice to set
     */
    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    /**
     * @return the stay
     */
    public Stay getStay() {
        return stay;
    }

    /**
     * @param stay the stay to set
     */
    public void setStay(Stay stay) {
        this.stay = stay;
    }

    /**
     * @return the stays
     */
    public List<Stay> getStays() {
        return stays;
    }

    /**
     * @param stays the stays to set
     */
    public void setStays(List<Stay> stays) {
        this.stays = stays;
    }

    /**
     * @return the startingDate
     */
    public Date getStartingDate() {
        return startingDate;
    }

    /**
     * @param startingDate the startingDate to set
     */
    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    /**
     * @return the leavingDate
     */
    public Date getLeavingDate() {
        return leavingDate;
    }

    /**
     * @param leavingDate the leavingDate to set
     */
    public void setLeavingDate(Date leavingDate) {
        this.leavingDate = leavingDate;
    }

    /**
     * @return the guestnum
     */
    public int getGuestnum() {
        return guestnum;
    }

    /**
     * @param guestnum the guestnum to set
     */
    public void setGuestnum(int guestnum) {
        this.guestnum = guestnum;
    }

    /**
     * @return the roomnum
     */
    public int getRoomnum() {
        return roomnum;
    }

    /**
     * @param roomnum the roomnum to set
     */
    public void setRoomnum(int roomnum) {
        this.roomnum = roomnum;
    }

    /**
     * @return the roomtypes
     */
    public List<RoomType> getRoomtypes() {
        return roomtypes;
    }

    /**
     * @param roomtypes the roomtypes to set
     */
    public void setRoomtypes(List<RoomType> roomtypes) {
        this.roomtypes = roomtypes;
    }

    /**
     * @return the checkinStays
     */
    public List<Stay> getCheckinStays() {
        checkinStays=cisbl.getcheckinStay();
        System.out.println("the size of checkinstays is "+checkinStays.size());
        return checkinStays;
    }

    /**
     * @param checkinStays the checkinStays to set
     */
    public void setCheckinStays(List<Stay> checkinStays) {
        this.checkinStays = checkinStays;
    }

    /**
     * @return the memberId
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * @param memberId the memberId to set
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * @return the mem
     */
    public MemberAccount getMem() {
        return mem;
    }

    /**
     * @param mem the mem to set
     */
    public void setMem(MemberAccount mem) {
        this.mem = mem;
    }

    /**
     * @return the selectroomnums
     */
    public List<String> getSelectroomnums() {
        return selectroomnums;
    }

    /**
     * @param selectroomnums the selectroomnums to set
     */
    public void setSelectroomnums(List<String> selectroomnums) {
        this.selectroomnums = selectroomnums;
    }

    /**
     * @return the selectedroomnum
     */
    public String getSelectedroomnum() {
        return selectedroomnum;
    }

    /**
     * @param selectedroomnum the selectedroomnum to set
     */
    public void setSelectedroomnum(String selectedroomnum) {
        this.selectedroomnum = selectedroomnum;
    }

    /**
     * @return the guestname
     */
    public List<String> getGuestname() {
        return guestname;
    }

    /**
     * @param guestname the guestname to set
     */
    public void setGuestname(List<String> guestname) {
        this.guestname = guestname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
}
