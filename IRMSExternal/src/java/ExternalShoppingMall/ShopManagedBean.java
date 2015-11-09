/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExternalShoppingMall;

import ShoppingMall.entity.Shop;
import ShoppingMall.entity.TenantVenue;
import ShoppingMall.session.ShopVenueManagementSessionBeanLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author user
 */
@ManagedBean
@RequestScoped
public class ShopManagedBean {

    private List<Shop> activeShops = new ArrayList();
    private String selectFloor="";
    @EJB
    ShopVenueManagementSessionBeanLocal svmsbl;
    
    /**
     * Creates a new instance of ShopManagedBean
     */
    public ShopManagedBean() {
    }

    /**
     * @return the activeShops
     */
    public List<Shop> getActiveShops() {
        if(selectFloor.equalsIgnoreCase(""))
            activeShops = svmsbl.getAllActiveShops();
        else {
            List<Shop> temp = svmsbl.getAllActiveShops();
            activeShops = new ArrayList();
            for(Shop s: temp) {
                if(s.getCurrentVenues().iterator().next().getFloor().equalsIgnoreCase(selectFloor))
                    activeShops.add(s);
            }
        }
        return activeShops;
    }
    
    public String getVenueString(List<TenantVenue> venues) {
        try {
        String venueString = "";
        for (TenantVenue venue : venues) {
            String concat = venueString.concat(venue.getId()+ "(Floor " + venue.getFloor().toUpperCase()+")");
            venueString = concat;
        }
        return venueString;
        } catch(Exception ex) {
            return null;
        }
    }    

    /**
     * @param activeShops the activeShops to set
     */
    public void setActiveShops(List<Shop> activeShops) {
        this.activeShops = activeShops;
    }

    /**
     * @return the selectFloor
     */
    public String getSelectFloor() {
        return selectFloor;
    }

    /**
     * @param selectFloor the selectFloor to set
     */
    public void setSelectFloor(String selectFloor) {
        this.selectFloor = selectFloor;
    }
    
    
}
