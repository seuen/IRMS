
package Accommodation.session;

import Accommodation.entity.Guest;
import Accommodation.entity.Hotel;
import Accommodation.entity.Item;
import Accommodation.entity.Room;
import Accommodation.entity.RoomType;
import java.util.Date;
import java.util.List;
import javax.ejb.Local; 

@Local
public interface HotelManagementSessionBeanLocal {
    public  List<Hotel> getAllHotel();
    public Hotel getHotel(String hotelname);
    public List<RoomType> getAllRoom(String hotelname);
    public void updateHotel(Hotel hotel);
    public void updateRoomType(RoomType roomtype);
    public List<Item> getAllItem(String hotelname);
    public void updateItem(Item item);
    public void deleteItem(Item item);
    public List<Room> getRooms(String hotelname);
    public List<Item> listroomserviceitem(String hotelname);

    public RoomType searchRoomType(String type);
   
}
