/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author yifeng
 */
public class ReceiveReservationRequest {
    private String hotelName;
    private String checkinDate;
    private String checkoutDate;
    private Integer numOfGuestPerRoom;
    private Integer numOfRooms;
    private Long roomTypeId;
    private Integer roomQuantity;

    public ReceiveReservationRequest() {
    }

    public ReceiveReservationRequest(String hotelName, String checkinDate, String checkoutDate, Integer numOfGuestPerRoom, Integer numOfRooms, Long roomTypeId, Integer roomQuantity) {
        this.hotelName = hotelName;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.numOfGuestPerRoom = numOfGuestPerRoom;
        this.numOfRooms = numOfRooms;
        this.roomTypeId = roomTypeId;
        this.roomQuantity = roomQuantity;
    }           

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Integer getNumOfGuestPerRoom() {
        return numOfGuestPerRoom;
    }

    public void setNumOfGuestPerRoom(Integer numOfGuestPerRoom) {
        this.numOfGuestPerRoom = numOfGuestPerRoom;
    }

    public Integer getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(Integer numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Integer getRoomQuantity() {
        return roomQuantity;
    }

    public void setRoomQuantity(Integer roomQuantity) {
        this.roomQuantity = roomQuantity;
    }
    
    
}
