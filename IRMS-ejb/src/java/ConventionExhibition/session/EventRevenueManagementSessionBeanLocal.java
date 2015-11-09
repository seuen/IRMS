/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition.session;

import ConventionExhibition.entity.EventOrder;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface EventRevenueManagementSessionBeanLocal {
    public List<EventOrder> getCEevents(Date start, Date end);
    public List<EventOrder> getBanquets(Date start, Date end);
    public List<EventOrder> getEnterns(Date start, Date end);
    public float calculateTotalRevenue(List<EventOrder> eo);
    public float calculateCommissionRevenue(Long evenID);
}
