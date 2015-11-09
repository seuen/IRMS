/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice.restful.accom;

import Accommodation.entity.Hotel;
import Accommodation.entity.RTReservation;
import Accommodation.entity.ReservationState;
import Accommodation.entity.RoomType;
import Accommodation.session.ReservationManagementSessionBeanLocal;
import CRM.entity.Address;
import CRM.entity.MemberAccount;
import CRM.entity.Membership;
import CRM.session.MemberAccountManagementSessionBeanLocal;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import restful.accom.AccomRestfulLocal;
import util.ExternalEmailManager;
import util.ReceiveLoginRequest;
import util.ReceiveReservationRequest;
import util.ReceiveSignUpRequest;

/**
 * REST Web Service
 *
 * @author yifeng
 */
@Stateless
@Path("accom")
public class AccomResource {

    @Context
    private UriInfo context;
    @EJB
    AccomRestfulLocal arl;
    @EJB
    ReservationManagementSessionBeanLocal rmsbl;
    @EJB
    private MemberAccountManagementSessionBeanLocal mmsbl;
    private ExternalEmailManager emailManager = new ExternalEmailManager();

    /**
     * Creates a new instance of AccomResource
     */
    public AccomResource() {
    }

    /**
     * Retrieves representation of an instance of
     * webservice.restful.accom.AccomResource
     *
     * @return an instance of java.lang.String
     */
//    @Produces("application/xml")
    @GET
    @Path("getHotelInfor")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hotel> getHotelInfor() {

        System.out.println("get hotels");
        List<Hotel> allHotels = arl.getAllHotels();
        List<Hotel> response = new ArrayList();
        for (Hotel hotel : allHotels) {
            response.add(this.deleteAllRelationsOfHotel(hotel));
        }
        return response;

    }
    
    @POST
    @Path("checkLogin")
    @Consumes("application/json")
    @Produces("application/json")
    public ReceiveSignUpRequest checkLogin(ReceiveLoginRequest receiveLoginRequest) throws NoSuchAlgorithmException{
        System.out.println("member Id " + receiveLoginRequest.getMemberId());
        System.out.println("password " + receiveLoginRequest.getPassword());
        
        if (mmsbl.verifyLogin(receiveLoginRequest.getMemberId(), receiveLoginRequest.getPassword())) {
            MemberAccount curMember = mmsbl.searchMember(receiveLoginRequest.getMemberId());
            //return curMember;
            return this.deleteAllRelationsOfMemberAccount(curMember);
        } else {
            ReceiveSignUpRequest temp = new ReceiveSignUpRequest();
            temp.setName("password account not match");
            return temp;
        }
        
        
    }
    
    @POST
    @Path("signupCustomer")
    @Consumes("application/json")
    @Produces("application/json")
    public void signupCustomer(ReceiveSignUpRequest receiveSignUpRequest) throws ParseException, NoSuchAlgorithmException {
        System.out.println("received " + receiveSignUpRequest);
        MemberAccount member = new MemberAccount();
        Address address = new Address();
        Membership membership = new Membership();
        
        member.setTitle(receiveSignUpRequest.getTitle());
        String name = receiveSignUpRequest.getName();
        String[] names = name.split(" ");
        member.setFirstName(names[0]);
        member.setLastName(names[1]);
        member.setEmailAddr(receiveSignUpRequest.getEmail());
        member.setDateOfBirth(this.parseDateFor(receiveSignUpRequest.getDateOfBirth()));
        member.setNationality(receiveSignUpRequest.getNation());
        member.setPhoneNum(receiveSignUpRequest.getPhoneNum());
        
        address.setCountry(receiveSignUpRequest.getCountry());
        address.setLine1(receiveSignUpRequest.getAddress());
        address.setLine2(receiveSignUpRequest.getAddress());
        address.setPostCode(receiveSignUpRequest.getPostalCode());
        
        String psw = mmsbl.addMember(member, address, membership);
        System.err.println("Member password: " + psw);
        System.err.println("Member password2: " + member.getMembership().getPassword());
        emailManager.sendMemberPassword(member, psw);
        
    }

    @POST
    @Path("createReservation")
    @Consumes("application/json")
    @Produces("application/json")
    public RTReservation createReservation(ReceiveReservationRequest receiveReservationRequest) throws ParseException {

        System.out.println("enter createReservation");

        RTReservation reservation = new RTReservation();

        //reservation 1
        Date checkin = this.parseDateFor(receiveReservationRequest.getCheckinDate());
        Date checkout = this.parseDateFor(receiveReservationRequest.getCheckoutDate());
        reservation.setDateFrom(checkin);
        reservation.setDateTo(checkout);
        reservation.setReserveDate(new Date());
        reservation.setDuration(rmsbl.setDura(checkin, checkout));

        //reservation 2
        List<RoomType> roomTypes = rmsbl.makeReservation1(checkin, checkout, receiveReservationRequest.getNumOfRooms(), receiveReservationRequest.getNumOfGuestPerRoom());
        int temp = rmsbl.updateRoomQuantity(roomTypes, receiveReservationRequest.getRoomQuantity(), receiveReservationRequest.getRoomTypeId());
        System.out.println("temp " + temp);
        if (temp == 1) {
            reservation.setRoomtype(rmsbl.retrieveRoomTypeForId(receiveReservationRequest.getRoomTypeId()));
            reservation.setRoomQuantity(receiveReservationRequest.getRoomQuantity());
        }
        
        //add guest

        //finish stage
        reservation.setType("Confirmed");
        reservation.setStatus("Reserved");
        reservation.setBundleStatus("False");
        RTReservation response = rmsbl.addReservation(reservation);
        System.out.println("response " + response);
        return this.deleteAllRelationsOfRTReservation(reservation);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("searchAvailableRooms")
    public List<RoomType> searchAvailableRooms(ReceiveReservationRequest receiveReservationRequest) throws ParseException {
        System.out.println("enter searchAvailableRooms");

        Date checkin = this.parseDateFor(receiveReservationRequest.getCheckinDate());
        Date checkout = this.parseDateFor(receiveReservationRequest.getCheckoutDate());

        List<RoomType> roomTypes = rmsbl.makeReservation1(checkin, checkout, receiveReservationRequest.getNumOfRooms(), receiveReservationRequest.getNumOfGuestPerRoom());

        List<RoomType> response = new ArrayList();

        System.out.println("hotel name is " + receiveReservationRequest.getHotelName());
        try {
            for (RoomType currentRoomType : roomTypes) {

                int minimumCnum = 100;

                Collection<ReservationState> rss = currentRoomType.getReservationStates();
                System.out.println("rss is null? " + (rss == null));
                if (rss != null) {
                    for (ReservationState currentReservationState : rss) {
                        if (currentReservationState.getRdate().compareTo(checkin) >= 0
                                && currentReservationState.getRdate().compareTo(checkout) < 0) {

                            if (currentReservationState.getAvailableNum() < minimumCnum) {
                                minimumCnum = currentReservationState.getAvailableNum();
                            }
                        }
                    }

                    System.out.println("before update, total number " + currentRoomType.getTotalNum());
                    if (minimumCnum >= receiveReservationRequest.getNumOfRooms()) {
                        currentRoomType.setTotalNum(minimumCnum);
                    }
                    System.out.println("updated total number " + currentRoomType.getTotalNum());
                }

                if (currentRoomType.getHotel().getName().equals(receiveReservationRequest.getHotelName())) {
                    response.add(this.deleteAllRelationsOfRoomType(currentRoomType));
                }
            }

        } catch (Exception ex) {
            System.out.println("size of roomTypes " + roomTypes.size());
        }

        //set all relations in response to null
        System.out.println("roomTypes received are " + response.size());
        return response;
    }

    private Date parseDateFor(String dateString) throws ParseException {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        return df.parse(dateString);
    }

    private RoomType deleteAllRelationsOfRoomType(RoomType roomType) {
        RoomType temp = new RoomType();
        temp.setBedNum(roomType.getBedNum());
        temp.setDetail(roomType.getDetail());
        temp.setMaxGuest(roomType.getMaxGuest());
        temp.setORatio(roomType.getORatio());
        temp.setPrice_h(roomType.getPrice_h());
        temp.setPrice_l(roomType.getPrice_l());
        temp.setRoomtypeId(roomType.getRoomtypeId());
        temp.setTotalNum(roomType.getTotalNum());
        temp.setType(roomType.getType());
        return temp;
    }

    private Hotel deleteAllRelationsOfHotel(Hotel hotel) {
        Hotel temp = new Hotel();
        temp.setAddress(hotel.getAddress());
        temp.setDescription(hotel.getDescription());
        temp.setName(hotel.getName());
        return temp;
    }
    
    private RTReservation deleteAllRelationsOfRTReservation(RTReservation reservation){
        RTReservation temp = new RTReservation();
        
        temp.setBundleStatus(reservation.getBundleStatus());
        temp.setDateFrom(reservation.getDateFrom());
        temp.setDateTo(reservation.getDateTo());
        temp.setDuration(reservation.getDuration());
        temp.setReservationNum(reservation.getReservationNum());
        temp.setRoomQuantity(reservation.getRoomQuantity());
        temp.setStatus(reservation.getStatus());
        temp.setType(reservation.getType());
        
        return temp;
    }
    
    private ReceiveSignUpRequest deleteAllRelationsOfMemberAccount(MemberAccount member){
        ReceiveSignUpRequest temp = new ReceiveSignUpRequest();        
        temp.setAddress(member.getAddress().getLine1());
        temp.setCountry(member.getAddress().getCountry());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        System.out.println("day of birth " + df.format(member.getDateOfBirth().getTime()));
        temp.setDateOfBirth(df.format(member.getDateOfBirth().getTime()));
        temp.setEmail(member.getEmailAddr());
        temp.setName(member.getFirstName() + " " + member.getLastName());
        temp.setNation(member.getNationality());
        temp.setPhoneNum(member.getPhoneNum());
        temp.setPostalCode(member.getAddress().getPostCode());
        temp.setTitle(member.getTitle());
        return temp;
    }
}
