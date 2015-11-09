/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Accommodation.session;

import Accommodation.entity.Hotel;
import Accommodation.entity.Item;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zsy
 */
@Stateless
public class ItemManagementSessionBean implements ItemManagementSessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager em;
        
    private Item item;    
    
    public ItemManagementSessionBean(){}
    
    @Override
    public String addItem(Item item, String hotelId){
        if(item!=null){
            System.out.println("the hotelId is "+ hotelId);
            Hotel hotel=em.find(Hotel.class, hotelId);
            item.setHotel(hotel);
                        em.persist(item);

            List<Item> items=(List<Item>) hotel.getItems();
            for(Object o: items){
                Item it=(Item)o;
                if(item.getName().equals(it.getName()))
                    return "same item in one hotel";
            }
            items.add(item);
            hotel.setItems(items);
            em.merge(hotel);
            em.flush();

//            item.setHotel(hotel);
//            em.persist(item);
            
            System.out.println("new item added to the system");
            return "Item added successfully";
        }
        else
            return "item is null";  
    }
    
    @Override
    public boolean updateItemPrice(String name, Float price){
        if(em.find(Item.class, name)!=null){
            item=em.find(Item.class, name);
            item.setPrice(price);
            em.persist(item);
            System.out.println("item updated to the system");
            return true;
        }
        else
            return false;
    }

}
