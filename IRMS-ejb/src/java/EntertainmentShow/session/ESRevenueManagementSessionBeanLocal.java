/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntertainmentShow.session;

import EntertainmentShow.entity.SectionTicket;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface ESRevenueManagementSessionBeanLocal {
    public List<SectionTicket> getInternalTicketSections();
    public List<SectionTicket> getExternalTicketSections();
    public float calcualteTotalRevenue(List<SectionTicket> st);
}
