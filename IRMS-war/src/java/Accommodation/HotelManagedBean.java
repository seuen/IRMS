
package Accommodation;

import Accommodation.entity.Hotel;
import Accommodation.entity.Item;
import Accommodation.entity.Room;
import Accommodation.entity.RoomType;
import Accommodation.session.HotelManagementSessionBeanLocal;
import Accommodation.session.RoomManagementSessionBeanLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.RowEditEvent;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class HotelManagedBean implements Serializable{
    @EJB
    HotelManagementSessionBeanLocal hmsbl;
    @EJB
    RoomManagementSessionBeanLocal rmsbl;
    
    
  
    private String hotelId;
    private Hotel hotel;
    private List<RoomType> roomtypes=new ArrayList();
    private List<Item> items=new ArrayList();
    private List<Room> rooms=new ArrayList();
    private Room room=new Room();
    
    
    public HotelManagedBean() {
    }

    public void save(){
        hmsbl.updateHotel(hotel);
        FacesMessage msg=new FacesMessage("Hotel "+hotelId+" has been updated");
        System.out.println(msg);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
   
    
    public void navigateHotelInfo(ActionEvent event) throws IOException
    {
        setHotelId(event.getComponent().getAttributes().get("hotelId").toString());
        System.err.println("hotelId: " + hotelId);
        hotel=hmsbl.getHotel(hotelId);
       
        if(getHotelId().contains("Hotel")){
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("hotelId", getHotelId());
            FacesContext.getCurrentInstance().getExternalContext().redirect("DetailHotel.xhtml");
        }
        else{
            System.out.println("hotelId does not get correctly");
        }
    } 
    
    public void navigateRoomTypeInfo(ActionEvent event) throws IOException{
        setHotelId(event.getComponent().getAttributes().get("hotelId").toString());
        System.err.println("hotelId: "+hotelId);
        hotel=hmsbl.getHotel(hotelId);
        roomtypes=hmsbl.getAllRoom(hotelId);
        if(getHotelId().contains("Hotel")){
            FacesContext.getCurrentInstance().getExternalContext().redirect("DetailRoom.xhtml");
        }
        else{
            System.out.println("hotelId does not get correctly");
        }
    }
    
    public void navigateRoomInfo(ActionEvent event) throws IOException{
        setHotelId(event.getComponent().getAttributes().get("hotelId").toString());
        System.err.println("hotelId: "+hotelId);
        rooms=hmsbl.getRooms(hotelId);
        
        if(getHotelId().contains("Hotel")){
            FacesContext.getCurrentInstance().getExternalContext().redirect("RoomDetails.xhtml");
        }
        else{
            System.out.println("hotelId does not get correctly");
        }
    }
    
    public void navigateItemInfo(ActionEvent event) throws IOException{
        setHotelId(event.getComponent().getAttributes().get("hotelId").toString());
        System.err.println("hotelId: "+hotelId);
        hotel=hmsbl.getHotel(hotelId);
        items=hmsbl.getAllItem(hotelId);
        if(getHotelId().contains("Hotel")){
            FacesContext.getCurrentInstance().getExternalContext().redirect("DetailItem.xhtml");
        }
        else{
            System.out.println("hotelId does not get correctly");
    }
    }

//    @PostConstruct
//    public void init(){
//        if(FacesContext.getCurrentInstance().getExternalContext().getFlash().get("hotelId") != null){
//            hotelId = FacesContext.getCurrentInstance().getExternalContext().getFlash().get("hotelId").toString();
//        
//                    }
//    }
    
    public void deleteItem(Item item){
        hmsbl.deleteItem(item);
        FacesMessage msg=new FacesMessage("Item Deleted Successfully",item.getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        item=new Item();
    }
    
   public void onEditRoomType(RowEditEvent event){
       System.out.println("has already get into onEdit");
       RoomType temp=(RoomType)event.getObject();
       hmsbl.updateRoomType(temp);
       FacesMessage msg=new FacesMessage("RoomType Edited",temp.getType());
       FacesContext.getCurrentInstance().addMessage(null, msg);
   }
   
   public void onCancelRoomType(RowEditEvent event){
       System.out.println("has already get into onCancel");
       FacesMessage msg1 = new FacesMessage("RoomType Edit Cancelled",((RoomType)event.getObject()).getType());
       System.out.println(msg1);
       FacesContext.getCurrentInstance().addMessage(null, msg1);
//       FacesMessage msg=new FacesMessage("RoomType Cancelled",((RoomType)event.getObject()).getType());
//       System.out.println(msg);
//       FacesContext.getCurrentInstance().addMessage(null, msg);
   }
   
   public void onEditItem(RowEditEvent event){
       System.out.println("has already get into onEdit");
       Item temp=(Item)event.getObject();
       hmsbl.updateItem(temp);
       FacesMessage msg=new FacesMessage("Item Edited",temp.getName());
       FacesContext.getCurrentInstance().addMessage(null, msg);
       
   }
   
   public void onEditCancelItem(RowEditEvent event){
        System.out.println("has already get into onCancel");
       FacesMessage msg1 = new FacesMessage("Item Edit Cancelled",((Item)event.getObject()).getName());
       System.out.println(msg1);
       FacesContext.getCurrentInstance().addMessage(null, msg1);
   }
    
   public void mantainRoom(ActionEvent event){
       room=(Room) event.getComponent().getAttributes().get("room");
        switch (room.getStatus()) {
            case "maintaining":
                FacesMessage msg=new FacesMessage("The room is undergoing maintenance");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                break;
            case "free":
                rmsbl.maintain(room);
                FacesMessage msg1=new FacesMessage("Room Status has been changed successfully");
                FacesContext.getCurrentInstance().addMessage(null, msg1);
                break;
            default:
                FacesMessage msg2=new FacesMessage("The room is occupied. Cannot maintain now!!");
                FacesContext.getCurrentInstance().addMessage(null, msg2);
                break;
        }
   }
   
   public void freeRoom(ActionEvent event){
       room=(Room) event.getComponent().getAttributes().get("room");
       if("maintaining".equals(room.getStatus())){
           rmsbl.freeRoom(room);
           FacesMessage msg=new FacesMessage("Maintenance has been done");
                FacesContext.getCurrentInstance().addMessage(null, msg);
       }
       else{
           FacesMessage msg1=new FacesMessage("The room is not in maintenance now");
                FacesContext.getCurrentInstance().addMessage(null, msg1);
       }
   }
    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<RoomType> getRoomtypes() {
        return roomtypes;
    }

    public void setRoomtypes(List<RoomType> roomtypes) {
        this.roomtypes = roomtypes;
    }
    
    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    
    
    
}
