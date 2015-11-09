/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Accommodation.session;

import Accommodation.entity.Item;
import Accommodation.entity.ItemOrder;
import Accommodation.entity.RSOrder;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author zsy
 */

@Local
public interface RoomServiceManagementSessionBeanLocal {
    public List<RSOrder> getAllOrder(String hotelId);   
    public boolean deleteOrder(RSOrder order);
    public List<Item> viewItems(RSOrder order);
    public int addRoomOrder(List<ItemOrder> itemOrders,String roomNum);
    public float computeTotal(List<ItemOrder> itemOrders);
    public ItemOrder updateOrder(ItemOrder itemOrder, int quantity);
}
