/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingMall.session;

import FoodBeverage.entity.Restaurant;
import ShoppingMall.entity.Negotiator;
import ShoppingMall.entity.Shop;
import ShoppingMall.entity.TenantVenue;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Cindylulu
 */
@Remote
public interface ShopVenueManagementSessionBeanRemote{
    // Space Management

    public TenantVenue createVenue(TenantVenue venue);
    public List<TenantVenue> getAllVenues();
    public List<TenantVenue> getAllResVenues();
    public TenantVenue getVenue(Long venueId);
    public boolean updateVenue(TenantVenue venue);
    public boolean deleteVenue(Long venueId);
    public List<Negotiator> getNegotiators(Long venueId);
    public Shop getShop(Long shopId);    
    public Restaurant getRestaurant(Long resId);
    public List<Shop> getShops(Long venueId);
    public Shop getActiveShop(Long venueId);
    // Negotiation Management
    public List<TenantVenue> getAvailableVenues();
    public Negotiator createNegotiator(Negotiator nego);
    public boolean createNegotiation(Negotiator nego, TenantVenue venue);
    public boolean deleteNegotiator(String negoId);
    public boolean deleteNegotiation(Negotiator nego, TenantVenue venue);
    public List<Negotiator> getNegotiators();
    public List<Negotiator> getShopNegotiators();
    public List<Negotiator> getResNegotiators();
    public Negotiator getNegotiator(String netoId);
    public boolean updateNegotiator(Negotiator n);
    // Contract Management
//    public List<TenantVenue> getNegotiatorVenues(Long negotiatorId);
    public Shop createShop(Negotiator negotiator);
    public Restaurant createRes(Negotiator negotiator);
    public boolean attach(Shop shop, TenantVenue venue);
    public boolean detach(Shop shop, TenantVenue venue);
    public String getVenueString(List<TenantVenue> venue);
    public List<TenantVenue> getLevelVenues(String level);
    public List<Shop> getAllShops();
    public List<Restaurant> getAllRestaurants();   
    public List<Shop> getAllHistoryShops();    
    public List<Restaurant> getAllHistoryRestaurants();
    public List<Shop> getAllActiveShops();    
    public List<Restaurant> getAllActiveRestaurants();
//    public String convertVenueString(List<TenantVenue> tv);

    public boolean registerShop(Shop shop); 
    public boolean updateShop(Shop shop);   
    public boolean updateRestaurant(Restaurant res);
    // Time event daily beginning
    public boolean setNegoStatus();
}
