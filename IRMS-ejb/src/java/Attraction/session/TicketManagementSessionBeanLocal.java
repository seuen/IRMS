/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.session;

import Attraction.entity.TicketType;
import javax.ejb.Local;
import java.util.List;

/**
 *
 * @author zsy
 */
@Local
public interface TicketManagementSessionBeanLocal {
    public boolean addTicketType(TicketType ticketType);
    public boolean updateTicketType();
    public boolean deleteTicketType();
    public List<TicketType> getAllTicketTypes();
}
