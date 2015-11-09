package Accommodation;

import Accommodation.entity.Guest;
import Accommodation.entity.RTReservation;
import Accommodation.entity.ReservationState;
import Accommodation.entity.Room;
import Accommodation.entity.RoomType;
import Accommodation.entity.Stay;
import Accommodation.session.HotelManagementSessionBeanLocal;
import Accommodation.session.ReservationManagementSessionBeanLocal;
import Accommodation.session.RoomManagementSessionBeanLocal;
import CRM.entity.MemberAccount;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped
public class RoomManagedBean implements Serializable{

    @EJB
    RoomManagementSessionBeanLocal rmsbl;
    @EJB
    HotelManagementSessionBeanLocal hmsbl;
    @EJB
    ReservationManagementSessionBeanLocal remsbl;
    private String hotelId = "Singland Hotel";  //for record the hotelId of hotel
    private List<Room> rooms;               //for checkin get all rooms in one hotel
    private List<Room> filteredRooms;     //for filter rooms in check in
    private List<Room> selectedRooms;
    private int guestNo = 1;                 //for calculate the no of guests of each stay
    private Guest guest = new Guest();       // for store the info of one guest temporalily
    private List<Guest> guests = new ArrayList();  //for store the info of guests of one stay
    private float totalprice;                   //for store the total price of one stay
    private Date startingDate;       //for record the starting date of stay
    private Date leavingDate;        //for record the ending date of a stay
    private Room room = new Room();
    private Stay stay = new Stay();             //store the info of one stay for confirmed stay
    private List<RoomType> roomtypes = new ArrayList(); //for display all roomtypes in one hotel in block room
    private int roomQuantity;
    private int rq;
    private RTReservation reservation = new RTReservation();
    private List<Stay> stays = new ArrayList();
    
    private Long memberId;
    private MemberAccount member;
    private List<String> guestnames=new ArrayList();

    public RoomManagedBean() {
        
    }

    public void cleanRoom() throws IOException {
        if (selectedRooms.isEmpty()) {
            FacesMessage msg = new FacesMessage("Please choose at least one room");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            int i = 0;
            for (Room currentroom : selectedRooms) {
                if (!currentroom.getHK_status()) {
                    i++;
                }
            }
            if (i == 0) {
                FacesMessage msg2 = new FacesMessage("Rooms chosen are all in clean condition, Do not need clean");
                FacesContext.getCurrentInstance().addMessage(null, msg2);
            } else {
                rmsbl.cleanRoom(selectedRooms);
                selectedRooms = new ArrayList();
                FacesContext.getCurrentInstance().getExternalContext().redirect("RoomHKStatus.xhtml");
                FacesMessage msg1 = new FacesMessage("Rooms have been cleaned");
                FacesContext.getCurrentInstance().addMessage(null, msg1);
            }
        }

    }

    public void dirtyRoom() throws IOException {
        if (selectedRooms.isEmpty()) {
            FacesMessage msg = new FacesMessage("Please choose at least one room");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            int i = 0;
            for (Room currentroom : selectedRooms) {
                if (currentroom.getHK_status()) {
                    i++;
                }
            }
            if (i == 0) {
                FacesMessage msg2 = new FacesMessage("Rooms chosen are all in dirty condition, Do not need report");
                FacesContext.getCurrentInstance().addMessage(null, msg2);
            } else {
                rmsbl.dirtyRoom(selectedRooms);
                selectedRooms = new ArrayList();
                FacesContext.getCurrentInstance().getExternalContext().redirect("RoomHKStatus.xhtml");
                FacesMessage msg1 = new FacesMessage("Rooms which need housekeeping have been reported");
                FacesContext.getCurrentInstance().addMessage(null, msg1);
            }
        }
    }

    public void saveQuantity() {
        setRq(roomQuantity);
    }

    public void blockRoom(RoomType roomtype) throws IOException {
        Calendar ca = new GregorianCalendar();
        if (rq != 0) {
            roomQuantity = rq;
            System.out.println("the roomquantity is " + roomQuantity);
            if (roomQuantity > roomtype.getTotalNum()) {
                roomQuantity = 0;
                FacesMessage msg = new FacesMessage("No enough rooms for roomtype " + roomtype.getType());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                Date today = new Date();
                reservation.setRoomtype(roomtype);
                reservation.setDateFrom(startingDate);
                reservation.setDateTo(leavingDate);
                reservation.setDuration((int)(startingDate.getTime()-leavingDate.getTime())/(24*60*60*1000));
                reservation.setReserveDate(today);
                reservation.setRoomQuantity(roomQuantity);
                reservation.setStatus("Reserved");
                reservation.setType("guaranteed");
                reservation.setGuest(guest);
                remsbl.addReservationG(reservation);
                FacesMessage msg1 = new FacesMessage(roomQuantity + " " + roomtype.getType() + " have been booked");
                FacesContext.getCurrentInstance().addMessage(null, msg1);
                roomQuantity = 0;
                reservation = new RTReservation();
                updateAvailableNum();
            }
        } else {
            FacesMessage msg2 = new FacesMessage("please complete entering room quantity; The Room Quantity cannot be 0!!!");
            FacesContext.getCurrentInstance().addMessage(null, msg2);
        }

    }

    public void clearBlock() throws IOException {
        startingDate = null;
        leavingDate = null;
        guest = new Guest();
        roomtypes = new ArrayList();
        FacesContext.getCurrentInstance().getExternalContext().redirect("AddGuestBlock.xhtml");
    }
    public void updateCheckIn() throws IOException, ParseException{
        startingDate=new Date();
        Calendar ca = new GregorianCalendar();
        ca.setTime(new Date());
        ca.add(Calendar.DATE, 10);
        Date deadline = ca.getTime();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        String deadlineS = sd.format(deadline);
        startingDate=sd.parse(sd.format(startingDate));
        if (!leavingDate.after(startingDate)) {
            FacesMessage msg = new FacesMessage("leaving Date should be after starting Date, please input the date again");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (leavingDate.after(deadline)) {
            FacesMessage msg1 = new FacesMessage("the leaving Date cannot be after than " + deadlineS);
            FacesContext.getCurrentInstance().addMessage(null, msg1);
        } else {
                    rooms = rmsbl.getHotelRoom(hotelId);
            for (Room currentRoom : rooms) {
                int minimumCnum = 100;
                for (ReservationState currentReservationState : currentRoom.getRoomtype().getReservationStates()) {
                    if (currentReservationState.getRdate().compareTo(startingDate) >= 0
                            && currentReservationState.getRdate().compareTo(leavingDate) < 0) {
                        System.out.println("get the rdate");
                        if (currentReservationState.getAvailableNum() < minimumCnum) {
                            minimumCnum = currentReservationState.getAvailableNum();
                        }
                    }
                }
                currentRoom.getRoomtype().setTotalNum(minimumCnum);
                System.out.println("the minimumCnum is " + minimumCnum);
                System.out.println("the available num of roomtype " + currentRoom.getRoomtype().getType() + " is " + currentRoom.getRoomtype().getTotalNum());
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect("CheckIn.xhtml");
        }
    }

    public void updateAvailableNum() throws IOException {
        Calendar ca = new GregorianCalendar();
        ca.setTime(new Date());
        ca.add(Calendar.DATE, 10);
        Date deadline = ca.getTime();
        ca.setTime(new Date());
        ca.add(Calendar.DATE, -1);
        Date today=ca.getTime();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        String deadlineS = sd.format(deadline);
        if (!leavingDate.after(startingDate)) {
            FacesMessage msg = new FacesMessage("leaving Date should be after starting Date, please input the date again");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (leavingDate.after(deadline)) {
            FacesMessage msg1 = new FacesMessage("the leaving Date cannot be after than " + deadlineS);
            FacesContext.getCurrentInstance().addMessage(null, msg1);
        } else if (startingDate.before(today)) {
            FacesMessage msg2 = new FacesMessage("Starting Date cannot before today, please input again");
            FacesContext.getCurrentInstance().addMessage(null, msg2);
        } else {
            roomtypes = hmsbl.getAllRoom(hotelId);
            for (RoomType currentRoomType : roomtypes) {
                int minimumCnum = 100;
                for (ReservationState currentReservationState : currentRoomType.getReservationStates()) {
                    if (currentReservationState.getRdate().compareTo(startingDate) >= 0
                            && currentReservationState.getRdate().compareTo(leavingDate) < 0) {
                        if (currentReservationState.getAvailableNum() < minimumCnum) {
                            minimumCnum = currentReservationState.getAvailableNum();
                        }
                    }
                }
                currentRoomType.setTotalNum(minimumCnum);
                System.out.println("the minimumCnum is " + minimumCnum);
                System.out.println("the available num of roomtype " + currentRoomType.getType() + " is " + currentRoomType.getTotalNum());
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect("BlockRoom.xhtml");
        }
    }

    public void checkRoom(ActionEvent event) throws IOException {
        room = (Room) event.getComponent().getAttributes().get("room");
        System.out.println("get into managed bean checkroom");
        if (!rmsbl.checkRoom(room, startingDate, leavingDate)) {
            System.out.println("the room is not available");
            FacesMessage msg = new FacesMessage("The room is not available in this period");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            System.out.println("before direct to add guest");
            FacesContext.getCurrentInstance().getExternalContext().redirect("AddMember.xhtml");
        }
    }
    
    public void displayMessage(String response){
        FacesMessage msg=new FacesMessage(response);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void addmember() throws IOException{
        member=rmsbl.findmember(memberId);
        if(member==null){
            this.displayMessage("No such member, Pls skil this step");
        }else{
        FacesContext.getCurrentInstance().getExternalContext().redirect("AddGuest.xhtml");
        }
    }

    public void checkGuest(ActionEvent event) throws IOException {
        if (guests.size() < room.getRoomtype().getMaxGuest() - 1) {
            System.out.println("the size of guests is " + guests.size());
            if (guest != null) {
                System.out.println("add the " + guestNo + "guest");
                guests.add(guest);
                guestNo++;
                guestnames.add(guest.getFirstName()+" "+guest.getLastName());
                System.err.println("the size of guest name is "+guestnames.size());
                guest = new Guest();
                System.out.println("has clear the guest");

                FacesContext.getCurrentInstance().getExternalContext().redirect("AddGuest.xhtml");

            } else {
                System.out.println("the guest is null");
            }
        } else {
            System.out.println("the guest no excced the max guest no of room");

            FacesMessage msg = new FacesMessage("No of Guests excceds the Max num of " + room.getRoomtype().getType() + " Please allocate room.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void displayStay() throws IOException {
        guests.add(guest);
        guestnames.add(guest.getFirstName()+" "+guest.getLastName());
        System.err.println("the size of guest name is "+guestnames.size());
        guest = new Guest();
        totalprice = rmsbl.checkPrice(startingDate, leavingDate, room);
        System.out.println("before display stay");
        FacesContext.getCurrentInstance().getExternalContext().redirect("ConfirmStay.xhtml");
        System.out.println("after display stay");
    }

    public void createStay() throws IOException {
        stay = new Stay();
        stay.setStatus(true);
        getStay().setDateFrom(startingDate);
        getStay().setDateTo(leavingDate);
        getStay().setNumGuest(guests.size());
         stay.setMember(member);
         if(member!=null){
             totalprice=(float) (totalprice*0.95);
         }
        getStay().setTotalPrice(totalprice);
        getStay().setRoom(room);
        System.err.println("the guestnames are "+guestnames);
       stay.setGuestnames(guestnames);
        getStay().setGuests(guests);
        rmsbl.addStay(stay, room);
        rmsbl.updateReservationStateWalkIn(startingDate, leavingDate, room);
        room = new Room();
        guests = new ArrayList();
        leavingDate = new Date();
        FacesContext.getCurrentInstance().getExternalContext().redirect("ConfirmedStay.xhtml");
    }

    public void clear() throws IOException {
        room = new Room();
        guests = new ArrayList();
        leavingDate = new Date();
        FacesContext.getCurrentInstance().getExternalContext().redirect("CheckIn.xhtml");
    }

    public List<Room> getRooms() {
        rooms=rmsbl.getHotelRoom(hotelId);
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public List<Room> getFilteredRooms() {
        return filteredRooms;
    }

    public void setFilteredRooms(List<Room> filteredRooms) {
        this.filteredRooms = filteredRooms;
    }

    public Date getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(Date leavingDate) {
        this.leavingDate = leavingDate;
    }

    public Room getRoom() {
        return room;
    }

    public int getGuestNo() {
        return guestNo;
    }

    public void setGuestNo(int guestNo) {
        this.guestNo = guestNo;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public Stay getStay() {
        return stay;
    }

    public void setStay(Stay stay) {
        this.stay = stay;
    }

    public List<Stay> getStays() {
        return stays;
    }

    public void setStays(List<Stay> stays) {
        this.stays = stays;
    }

    public float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    public List<RoomType> getRoomtypes() {
        return roomtypes;
    }

    public void setRoomtypes(List<RoomType> roomtypes) {
        this.roomtypes = roomtypes;
    }

    public int getRoomQuantity() {
        return roomQuantity;
    }

    public void setRoomQuantity(int roomQuantity) {
        this.roomQuantity = roomQuantity;
    }

    public RTReservation getReservation() {
        return reservation;
    }

    public void setReservation(RTReservation reservation) {
        this.reservation = reservation;
    }

    public int getRq() {
        return rq;
    }

    public void setRq(int rq) {
        this.rq = rq;
    }

    public List<Room> getSelectedRooms() {
        return selectedRooms;
    }

    public void setSelectedRooms(List<Room> selectedRooms) {
        this.selectedRooms = selectedRooms;
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
     * @return the guestnames
     */
    public List<String> getGuestnames() {
        return guestnames;
    }

    /**
     * @param guestnames the guestnames to set
     */
    public void setGuestnames(List<String> guestnames) {
        this.guestnames = guestnames;
    }
}
