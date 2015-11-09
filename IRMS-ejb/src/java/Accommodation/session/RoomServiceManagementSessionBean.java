/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Accommodation.session;

import Accommodation.entity.Hotel;
import Accommodation.entity.Item;
import Accommodation.entity.ItemOrder;
import Accommodation.entity.RSOrder;
import Accommodation.entity.Room;
import Accommodation.entity.Stay;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author zsy
 */
@Stateless
public class RoomServiceManagementSessionBean implements RoomServiceManagementSessionBeanLocal {

    @PersistenceContext
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public RoomServiceManagementSessionBean() {
    }

    @Override
    public float computeTotal(List<ItemOrder> itemOrders) {
        float total = 0;
        for (ItemOrder itemOrder : itemOrders) {
            total += itemOrder.getItem().getPrice() * itemOrder.getQuantity();
        }
        return total;
    }
    
    @Override
    public ItemOrder updateOrder(ItemOrder itemOrder, int quantity){
        if(itemOrder!=null && quantity!=0){
            System.out.println("inside update order");
            RSOrder order=itemOrder.getRsorder();
            Item item=itemOrder.getItem();
            Stay stay=order.getStay();
            
            float charge=stay.getTotalCharges();
            float payment=stay.getTotalPrice();
            float old=order.getTotalPrice();
            
            List<ItemOrder> temp1=(List<ItemOrder>) order.getItemOrders();
            List<ItemOrder> temp2=(List<ItemOrder>) item.getItemOrders();
            List<RSOrder> temp3=(List<RSOrder>) stay.getRsOrders();
            itemOrder.setQuantity(quantity);
            
            temp1.set(temp1.indexOf(itemOrder), itemOrder);
            temp2.set(temp2.indexOf(itemOrder), itemOrder);
            
            order.setTotalPrice(computeTotal(temp1));
            order.setItemOrders(temp1);
            item.setItemOrders(temp2);
            
            em.merge(itemOrder);
            em.merge(order);
            em.merge(item);
            
            temp3.set(temp3.indexOf(order), order);
            for(RSOrder o: temp3){
                System.err.println(o.getOrderNum());
                System.out.println(o.getTotalPrice());
                System.out.println(o.getItemOrders());
            }
            stay.setRsOrders(temp3);
            
            charge = charge+order.getTotalPrice()-old;
            payment = payment+order.getTotalPrice()-old;
            stay.setTotalCharges(charge);
            stay.setTotalPrice(payment);
            
            em.merge(stay);
            System.err.println(stay.getTotalCharges());
            
            return itemOrder;
        }
        else{
            System.err.println("invalid input");
            return null;
        }
        
    }

    @Override
    public int addRoomOrder(List<ItemOrder> itemOrders, String roomNum) {
        System.err.println("inside add room order sessionbean");
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date today = null;
        try {
            today = sdf.parse(sdf.format(d));
        } catch (ParseException ex) {
            Logger.getLogger(RoomServiceManagementSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (roomNum != null && (!roomNum.equals("")) && itemOrders != null) {
            System.err.println("inside input correctly");
            String hotelName = itemOrders.get(0).getItem().getHotel().getName();

            Query q = em.createQuery("SELECT r FROM Room r WHERE r.roomNum=:roomNum "
                    + "AND r.roomtype.hotel.name=:hotelName");
            q.setParameter("roomNum", roomNum);
            q.setParameter("hotelName", hotelName);

            List<Room> rooms = q.getResultList();
            if (rooms.isEmpty()) {
                return 0;
            } else {
                Room room = (Room) q.getSingleResult();

                if (room != null && room.getRoomtype().getHotel().getName().equals(hotelName)) {
                    System.err.println("inside room!=null");
                    int test = 0;
                    //persist order with itemOrder
                    RSOrder order = new RSOrder();
                    for (Stay stay : room.getStays()) {
                        if (stay.getDateFrom().compareTo(today) <= 0 && stay.getDateTo().compareTo(today) > 0) {
                            System.out.println("inside stay found");

                            order.setStay(stay);
                            order.setDateo(today);
                            order.setPayStatus("Unpaid");
                            order.setItemOrders(itemOrders);
                            float total = computeTotal(itemOrders);
                            order.setTotalPrice(total);

                            float temp1 = stay.getTotalCharges();
                            float temp2 = stay.getTotalPrice();
                            stay.setTotalCharges(temp1 + total);
                            stay.setTotalPrice(temp2 + total);
                            stay.getRsOrders().add(order);
                            
                            em.persist(order);
                            em.merge(stay);
                            
                            test = 1;
                            break;
                        }

                    }
                       
                    //persist Item with itemOrder
                    for (ItemOrder itemOrder : itemOrders) {
                        System.err.println("inside loop 2");
                        Item item = itemOrder.getItem();
                        itemOrder.setRsorder(order);
                        item.getItemOrders().add(itemOrder);
                        System.err.println("inside itemOrder loop");

                        em.merge(item);
                        em.persist(itemOrder);
                    }

                    if (test == 1) {
                        return 1;
                    } else {
                        return 3;//persist not successful
                    }
                } else {
                    return 2;//room not found
                }
            }
        }
        return 0;//invalid input data
    }

    @Override
    public List<RSOrder> getAllOrder(String hotelId) {
        if (em.find(Hotel.class, hotelId) != null) {
            Query q = em.createQuery("SELECT o FROM RSOrder o WHERE o.stay.room.roomtype.hotel.name=:hotelName");
            q.setParameter("hotelName", hotelId);
            List<RSOrder> orders = q.getResultList();
            return orders;
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteOrder(RSOrder order) {
        if (em.find(RSOrder.class, order.getOrderNum()) != null) {
            System.err.println("inside delete");

//            order.setItemOrders(null);
            for (ItemOrder io : order.getItemOrders()) {
                System.err.println("for loop 1 start");

                List<ItemOrder> temp1 = (List<ItemOrder>) order.getItemOrders();
                temp1.set(temp1.indexOf(io), null);
                order.setItemOrders(temp1);
                
                Item item = io.getItem();
                List<ItemOrder> temp2 = (List<ItemOrder>) item.getItemOrders();
                temp2.set(temp2.indexOf(io), null);
                item.setItemOrders(temp2);
                
                //remove itemOrder and merge item
                ItemOrder todelete = em.merge(io);
                em.remove(todelete);

                em.merge(item);
                em.merge(order);
//                em.flush();

                System.err.println("for loop 1 end");
            }

//            em.merge(order);
            System.err.println("after order update");
            Stay s = order.getStay();
            System.err.println("after get stay");
            float t = order.getTotalPrice();

            //remove order in stay
            if (!s.getRsOrders().contains(order)) {
                System.err.println("order not found in stay");
                return false;
            } else {
                System.err.println("inside stay loop");
                float temp1 = s.getTotalCharges();
                float temp2 = s.getTotalPrice();
                s.setTotalCharges(temp1 - t);
                s.setTotalPrice(temp2 - t);
                s.getRsOrders().remove(order);
                em.merge(s);

                //remove rsOrder
                RSOrder tobeRemoved = em.merge(order);
                em.remove(tobeRemoved);
                em.flush();

                System.err.println("Order is canceled!");
                return true;
            }
        } else {
            System.err.println("Order not found in the system");
            return false;
        }
    }

    @Override
    public List<Item> viewItems(RSOrder order) {
        List<Item> items = new ArrayList();
        Query q = em.createQuery("SELECT i From Item i WHERE i.name");
        return items;
    }
}
