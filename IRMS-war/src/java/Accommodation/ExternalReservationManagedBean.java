/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Accommodation;

import Accommodation.entity.Guest;
import Accommodation.entity.RTReservation;
import Accommodation.entity.ReservationState;
import Accommodation.entity.RoomType;
import Accommodation.session.CustomerReservationSessionBeanLocal;
import CRM.entity.MemberAccount;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import util.EmailManager;

@ManagedBean
@SessionScoped
public class ExternalReservationManagedBean implements Serializable{

    public ExternalReservationManagedBean() {
    }


    private RTReservation reservation = new RTReservation();
    private RTReservation rtreservation = new RTReservation();
    private List<RTReservation> guestReservations = new ArrayList();
    private List<RTReservation> reservations = new ArrayList();
    private List<RTReservation> filteredGuestReservations = new ArrayList();
    private List<RoomType> roomTypes = new ArrayList();
    private List<Integer> quotas;
    private Guest guest = new Guest();
    private Long reservationNum;
    private MemberAccount member;
    private RoomType roomType;
    private String ic;
    private String hotelId;
    private int guestNum;
    private Date dateFrom;
    private Date dateTo;
    private int duration;
    private int roomQuantity;
    private int rq;
    private Long roomtypeId;
    private int index;
    private EmailManager emailManager = new EmailManager();
    @EJB
    CustomerReservationSessionBeanLocal rmsbl;
    FacesContext fc = FacesContext.getCurrentInstance();
    ExternalContext ec = fc.getExternalContext();

  
    public void updateReservation() throws IOException {
        System.err.println("inside update reservation");
        Date today = new Date();
        if (dateFrom.before(today) || dateTo.before(today)) {
            FacesMessage msg = new FacesMessage("Please enter valid date input!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            dateFrom=null;
            dateTo=null;
            roomQuantity=1;
//            roomTypes=null;
//            guestNum=1;
        } else {
            RTReservation r = rmsbl.updateReservation(reservation, dateFrom, dateTo, roomQuantity, guestNum);
            reservation = r;
            dateFrom=null;
            dateTo=null;
            roomQuantity=1;
            refresh();
            ec.redirect("customerViewAllReservations.xhtml");
        }    
    }

    public void refresh(){
//        reservation = new RTReservation();
        dateFrom=null;
        dateTo=null;
        roomQuantity=1;
        guestNum=1;
        roomTypes= new ArrayList();
    }
    
    public void addReservation1() throws IOException, NoSuchAlgorithmException {
        System.err.println(roomQuantity);
        Date today = new Date();
        if (dateFrom.before(today) || dateTo.before(today)) {
            FacesMessage msg = new FacesMessage("Please enter valid date input!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            roomQuantity = 1;
            dateTo = null;
            dateFrom = null;
            guestNum = 1;
            roomTypes = new ArrayList();
        } else {

            roomTypes = rmsbl.makeReservation1(dateFrom, dateTo, roomQuantity, guestNum);

            if (roomTypes != null) {
//            for (RoomType rt : roomTypes) {
//                System.err.println("Available Room Type: " + rt.getType());
//            }

                for (RoomType currentRoomType : roomTypes) {
                    int minimumCnum = 100;
                    for (ReservationState currentReservationState : currentRoomType.getReservationStates()) {
                        if (currentReservationState.getRdate().compareTo(dateFrom) >= 0
                                && currentReservationState.getRdate().compareTo(dateTo) < 0) {

                            if (currentReservationState.getBooknum() < minimumCnum) {
                                minimumCnum = currentReservationState.getAvailableNum();
                            }
                        }
                    }

                    if (minimumCnum >= roomQuantity) {
                        currentRoomType.setTotalNum(minimumCnum);
                    }
                }

                System.out.println("end to loop 1");

                System.out.println("step 1 finish!");
                rtreservation= new RTReservation();
                getRtreservation().setDateFrom(dateFrom);
                getRtreservation().setDateTo(dateTo);
                getRtreservation().setReserveDate(today);
                duration = rmsbl.setDura(dateFrom, dateTo);
                getRtreservation().setDuration(duration);
                dateFrom = null;
                dateTo = null;
                guestNum = 1;
                ec.redirect("CustomerAddReservationRoom.xhtml");

            } else {
                FacesMessage msg = new FacesMessage("Invalid reservation! Please try again ");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                roomQuantity = 1;
                dateTo = null;
                dateFrom = null;
                guestNum = 1;
                roomTypes = new ArrayList();
            }
        }
    }

    public void addReservation2() throws NoSuchAlgorithmException, IOException {
        System.err.println(roomType +" "+roomQuantity);
        if (roomType != null && roomQuantity != 0) {
            System.err.println("inside step 2");
            System.err.println(roomQuantity);
            if (rq != 0) {
                roomQuantity = getRq();
            }

            int temp = rmsbl.updateRoomQuantity(roomTypes, roomQuantity, roomType.getRoomtypeId());
            if (temp == 1) {
                System.out.println(roomType + " " + roomQuantity);
                getRtreservation().setRoomtype(roomType);
                getRtreservation().setRoomQuantity(roomQuantity);
                roomTypes = new ArrayList();
                roomQuantity = 1;
                System.err.println("end 2");
                ec.redirect("CustomerAddReservationGuest.xhtml");
            } else if (temp == 2) {
                FacesMessage msg = new FacesMessage("There is no enough room!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            FacesMessage msg = new FacesMessage("Fail to proceed!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            roomType = new RoomType();
            roomQuantity = 1;
        }
    }

    public void addReservation3() throws NoSuchAlgorithmException, IOException {

        if (rmsbl.addGuest(guest)) {
            System.err.println("inside step 3");
            getRtreservation().setGuest(guest);
            guest = new Guest();
            ec.redirect("CustomerAddReservationPayment.xhtml");
        } else {
            FacesMessage msg = new FacesMessage("Fail to add " + guest.getIc());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            guest = new Guest();
            roomTypes = new ArrayList();
            roomType = new RoomType();
        }
    }

    public void addReservation() throws IOException {
        System.err.println("inside add");
        System.err.println(rtreservation.getDateFrom());
        
        if (rtreservation!= null) {
            getRtreservation().setType("guaranteed");
            getRtreservation().setStatus("Reserved");
            getRtreservation().setBundleStatus("False");
            
            rmsbl.addReservationG(rtreservation);
            
            String text="hello, "+rtreservation.getGuest().getFirstName()+rtreservation.getGuest().getLastName()+". You have booked a room "
                        +rtreservation.getRoomtype().getType()+" with confirmation num of "+rtreservation.getReservationNum();
//            emailManager.sendEmail(reservation.getGuest().getEmailAddress(), "Room Reservation notification", text);d
            rtreservation = new RTReservation();
            FacesMessage msg = new FacesMessage("Reservation is successful ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("Fail to make reservation");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            rtreservation = new RTReservation();
        }
    }

    public List<RTReservation> listAllReservation() {
        reservations = new ArrayList();
        setReservations(rmsbl.getAllReservation());
        return reservations;
    }

    public void deleteReservation() throws IOException {
        System.err.println("inside delete");
        System.err.println(reservation);
        if (rmsbl.deleteReservation(reservation)) {
            reservation = new RTReservation();
            ec.redirect("customerViewAllReservations.xhtml");
        } else {
            FacesMessage msg = new FacesMessage("Fail to delete");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void searchReservationBN() throws IOException {
        System.err.println(this);
        guestReservations=new ArrayList();
        if (reservationNum == null || "".equals(reservationNum)) {
            FacesMessage msg = new FacesMessage("Empty reservation number","Please enter reservation number");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            if (rmsbl.getReservationBN(reservationNum) != null) {
                reservation = rmsbl.getReservationBN(reservationNum);
                guestReservations.add(reservation);
                reservation = new RTReservation();
                System.out.println("the size of guestReservations is "+guestReservations.size());
                FacesContext.getCurrentInstance().getExternalContext().redirect("customerViewAllReservations.xhtml");

            } else {
                FacesMessage msg1;
                msg1 = new FacesMessage("Fail to find reservation by reservation number "+reservationNum,"");
                FacesContext.getCurrentInstance().addMessage(null, msg1);
            }
        }
        reservationNum = null;
    }

    public void searchReservationBI() throws IOException {
        System.err.println(this);
        guestReservations=new ArrayList();
        if (ic == null || "".equals(ic)) {
            FacesMessage msg = new FacesMessage("Empty guest NRIC ","Please enter an ic");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            guestReservations=rmsbl.getAllGuestReservation(ic);
            if (guestReservations!=null) {
                reservation = new RTReservation();
                System.out.println("the size of guestReservations is "+guestReservations.size());
                FacesContext.getCurrentInstance().getExternalContext().redirect("customerViewAllReservations.xhtml");
            } else {
                FacesMessage msg = new FacesMessage("Fail to find reservation by guest NRIC: " + ic,"");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                reservation = new RTReservation();
            }
        }
        ic = null;
    }

    public void saveQuantity() {
        System.err.println("inside save roomQuantity");
        rq = getRoomQuantity();
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
     * @return the ic
     */
    public String getIc() {
        return ic;
    }

    /**
     * @param ic the ic to set
     */
    public void setIc(String ic) {
        this.ic = ic;
    }

    /**
     * @return the guestReservations
     */
    public List<RTReservation> getGuestReservations() {
        return guestReservations;
    }

    /**
     * @param guestReservations the guestReservations to set
     */
    public void setGuestReservations(List<RTReservation> guestReservations) {
        this.guestReservations = guestReservations;
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
     * @return the member
     */
    public MemberAccount getMember() {
        return member;
    }

    /**
     * @param member the member to set
     */
    public void setMember(MemberAccount member) {
        this.member = member;
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

    /**
     * @return the hotelId
     */
    public String getHotelId() {
        return hotelId;
    }

    /**
     * @param hotelId the hotelId to set
     */
    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    /**
     * @return the guestNum
     */
    public int getGuestNum() {
        return guestNum;
    }

    /**
     * @param guestNum the guestNum to set
     */
    public void setGuestNum(int guestNum) {
        this.guestNum = guestNum;
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
     * @return the dateFrom
     */
    public Date getDateFrom() {
        return dateFrom;
    }

    /**
     * @param dateFrom the dateFrom to set
     */
    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     * @return the dateTo
     */
    public Date getDateTo() {
        return dateTo;
    }

    /**
     * @param dateTo the dateTo to set
     */
    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    /**
     * @return the roomQuantity
     */
    public int getRoomQuantity() {
        return roomQuantity;
    }

    /**
     * @param roomQuantity the roomQuantity to set
     */
    public void setRoomQuantity(int roomQuantity) {
        this.roomQuantity = roomQuantity;
    }

    /**
     * @return the quotas
     */
    public List<Integer> getQuotas() {
        return quotas;
    }

    /**
     * @param quotas the quotas to set
     */
    public void setQuotas(List<Integer> quotas) {
        this.quotas = quotas;
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
     * @return the roomtypeId
     */
    public Long getRoomtypeId() {
        return roomtypeId;
    }

    /**
     * @param roomtypeId the roomtypeId to set
     */
    public void setRoomtypeId(Long roomtypeId) {
        this.roomtypeId = roomtypeId;
    }

    /**
     * @return the rtreservation
     */
    public RTReservation getRtreservation() {
        return rtreservation;
    }

    /**
     * @param rtreservation the rtreservation to set
     */
    public void setRtreservation(RTReservation rtreservation) {
        this.rtreservation = rtreservation;
    }

    /**
     * @return the rq
     */
    public int getRq() {
        return rq;
    }

    /**
     * @param rq the rq to set
     */
    public void setRq(int rq) {
        this.rq = rq;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return the filteredGuestReservations
     */
    public List<RTReservation> getFilteredGuestReservations() {
        return filteredGuestReservations;
    }

    /**
     * @param filteredGuestReservations the filteredGuestReservations to set
     */
    public void setFilteredGuestReservations(List<RTReservation> filteredGuestReservations) {
        this.filteredGuestReservations = filteredGuestReservations;
    }

    /**
     * @return the emailManager
     */
    public EmailManager getEmailManager() {
        return emailManager;
    }

    /**
     * @param emailManager the emailManager to set
     */
    public void setEmailManager(EmailManager emailManager) {
        this.emailManager = emailManager;
    }
}
