/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingMall.session;

import ShoppingMall.entity.AdhocEvent;
import ShoppingMall.entity.RentalReports;
import ShoppingMall.entity.Shop;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Cindylulu 
 */
@Local
public interface ChargesManagementSessionBeanLocal {
    public Long addAdhocEvents(String eventType, float charge, String description, Date eventDate, Long shopId);
    public Shop getShop(Long shopId);
    public boolean mergeShop(Shop shop);
    public List <AdhocEvent> getAllAdhocEvents();
    public List <AdhocEvent> getAllResAdhocEvents();
    public List <AdhocEvent> getMyAdHocEvents(Long shopId);
    public float computeAdhocCharges(List <AdhocEvent> events);
    // Auto
    public boolean createRentalReports();

    public List<AdhocEvent> getMyMonthlyAdhocEvents(Long shopId, Date date);
    public RentalReports getRentalReports(Long rID);
}
