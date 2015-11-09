package Accommodation.session;

import Accommodation.entity.Hotel;
import Accommodation.entity.Item;
import Accommodation.entity.Room;
import Accommodation.entity.RoomType;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class HotelManagementSessionBean implements HotelManagementSessionBeanLocal {
    @PersistenceContext
    EntityManager em;

    @Override
   public List<Hotel> getAllHotel(){
       Query query=em.createQuery("Select s from Hotel h");
       return query.getResultList();
   }
    
    @Override
    public List<Item> getAllItem(String hotelname){
        Hotel hotel=em.find(Hotel.class, hotelname);
        return (List<Item>) hotel.getItems();
    }
    
    @Override
    public List<Item> listroomserviceitem(String hotelname){
        List<Item> temp=new ArrayList();
        Hotel hotel=em.find(Hotel.class, hotelname);
        for(Item current: hotel.getItems()){
            if(current.getType().equals("Food")||current.getType().equals("Laundry")||current.getType().equals("Min-bar Item")){
                temp.add(current);
            }
        }
        return temp;
    }
    
    @Override
    public Hotel getHotel(String hotelname){
        return em.find(Hotel.class, hotelname);
    }
    
    @Override
     public List<RoomType> getAllRoom(String hotelname){
         Hotel hotel=em.find(Hotel.class, hotelname);
         return (List<RoomType>) hotel.getRoomTypes();
     }
    
    
    @Override
    public RoomType searchRoomType(String type) {
        Query query = em.createQuery("Select r from RoomType r Where r.type=:type");
        query.setParameter("type", type);
        return (RoomType) query.getResultList().get(0);
    }

    
    @Override
    public void updateHotel(Hotel hotel){
        em.merge(hotel);
    }
    
    @Override
    public void updateRoomType(RoomType roomtype){
        em.merge(roomtype);
    }
    
    
    @Override
    public void updateItem(Item item){
        em.merge(item);
    }
    
    @Override
    public void deleteItem(Item item){
        System.out.println("the name of item is"+item.getName());
        System.out.println("the id of item is"+item.getItemId());
        Hotel temp=item.getHotel();
        System.out.println("the hotel name is "+temp.getName());
        List<Item> items=(List<Item>) temp.getItems();
        items.remove(item);
        System.out.println("item has been removed from item list in hotel");
        temp.setItems(items);
        em.merge(temp);
        System.out.println("the hotel database has been refreshed");
        Item toBeRemoved=em.merge(item);
        em.remove(toBeRemoved);
        System.out.println("the item has been removed");
    }
    @Override
    public List<Room> getRooms(String hotelId){
        Hotel hotel=em.find(Hotel.class, hotelId);
        List<Room> roomlist=new ArrayList();
        for (Object o: hotel.getRoomTypes()) {
            RoomType roomtype=(RoomType) o;
            for(Object obj: roomtype.getRooms()){
                Room room=(Room) obj;
                roomlist.add(room);
            }
        }
        return roomlist;
    }
    
}
