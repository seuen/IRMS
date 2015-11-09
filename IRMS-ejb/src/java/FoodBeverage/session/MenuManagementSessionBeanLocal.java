/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FoodBeverage.session;

import FoodBeverage.entity.MenuItem;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Acer
 */
@Local
public interface MenuManagementSessionBeanLocal {
    public void addMenuItem(MenuItem item);
    public List<MenuItem> listAllMenuItem();
    public MenuItem findmenuitem(Long itemid);
    public boolean checkItemconflict(MenuItem item);
    
}
