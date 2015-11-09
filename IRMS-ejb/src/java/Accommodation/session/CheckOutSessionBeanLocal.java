 
package Accommodation.session;

import Accommodation.entity.Stay;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CheckOutSessionBeanLocal {
    public List<Stay> getCheckOutStay(Date checkout);
    public boolean payCharges(Stay stay);
    public boolean checkout(Stay stay);
    public boolean updateHKstatus();
    public boolean updateRooms();
}
