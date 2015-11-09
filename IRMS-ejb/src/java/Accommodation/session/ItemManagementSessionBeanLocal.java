/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Accommodation.session;

import Accommodation.entity.Hotel;
import javax.ejb.Local;
import java.util.List;
import Accommodation.entity.Item;
/**
 *
 * @author zsy
 */
@Local
public interface ItemManagementSessionBeanLocal {
    public String addItem(Item item, String hotelId);
    public boolean updateItemPrice(String name, Float price);
}
