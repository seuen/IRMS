/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.session;

import Attraction.entity.Attraction;
import Attraction.entity.AttractionTicketOrder;
import Attraction.entity.Bundle;
import Attraction.entity.BundleOrder;
import Attraction.entity.PkgTicket;
import Attraction.entity.TicketType;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Local;
import java.util.List;

/**
 *
 * @author zsy
 */
@Local
public interface AttraTicketManagementSessionBeanLocal {
    public boolean updateTicketType(TicketType ticketType);
    public boolean updateBundle(Bundle theBundle);
    public List<TicketType> getAllTicketType(String attractionName);
    public List<TicketType> getAllTicketTypes();
    public void addTicketType(TicketType ticketType, String attractionName);
    public TicketType findTicketType(Long ticketTypeId);
    public boolean deleteTicketType(Long ticketTypeId);
    public boolean deleteBundle(Long bundleId);
    
    public TicketType searchTicketType(String type);
    public boolean createBundle(Collection<PkgTicket> pkgTickets, String bundleName, String bundlePrice);
    public List<Bundle> getAllBundles();
    public List<Attraction> getAllAttractions();
    public List<AttractionTicketOrder> getAllTheTicketOrders(Attraction attraction);
    public boolean sellTickets(List<AttractionTicketOrder> atos, List<BundleOrder> bundleOrders,Date date);
    public List<AttractionTicketOrder> getDisneyTicketOrders();
     public boolean checkTicketAvailability(List<AttractionTicketOrder> atos,  List<BundleOrder> bundleOrders, Date date);
}