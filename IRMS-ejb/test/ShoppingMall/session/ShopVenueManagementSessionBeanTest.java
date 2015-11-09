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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class ShopVenueManagementSessionBeanTest {
    ShopVenueManagementSessionBeanRemote sasbr = this.lookupShopVenueManagementSessionBeanRemote();
    public ShopVenueManagementSessionBeanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createVenue method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testCreateVenue() throws Exception {
        System.out.println("createVenue");
        TenantVenue venue = null;
        
        
        TenantVenue expResult = null;
        TenantVenue result = sasbr.createVenue(venue);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }


    /**
     * Test of getVenue method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testGetVenue() throws Exception {
        System.out.println("getVenue");
        Long venueId = null;
        
        
        TenantVenue expResult = null;
        TenantVenue result = sasbr.getVenue(venueId);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of updateVenue method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testUpdateVenue() throws Exception {
        System.out.println("updateVenue");
        TenantVenue venue = null;
        
        
        boolean expResult = false;
        boolean result = sasbr.updateVenue(venue);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of deleteVenue method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testDeleteVenue() throws Exception {
        System.out.println("deleteVenue");
        Long venueId = null;
        
        
        boolean expResult = false;
        boolean result = sasbr.deleteVenue(venueId);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getNegotiators method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testGetNegotiators_Long() throws Exception {
        System.out.println("getNegotiators");
        Long venueId = null;
        
        
        List expResult = null;
        List<Negotiator> result = sasbr.getNegotiators(venueId);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getShops method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testGetShops() throws Exception {
        System.out.println("getShops");
        Long venueId = null;
        List expResult = null;
        
        List<Shop> result = sasbr.getShops(venueId);
        assertEquals(expResult,result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getActiveShop method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testGetActiveShop() throws Exception {
        System.out.println("getActiveShop");
        Long venueId = null;
        
        
        Shop expResult = null;
        Shop result = sasbr.getActiveShop(venueId);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of createNegotiator method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testCreateNegotiator() throws Exception {
        System.out.println("createNegotiator");
        Negotiator nego = null;
        
        
        Negotiator expResult = null;
        Negotiator result = sasbr.createNegotiator(nego);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of createNegotiation method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testCreateNegotiation() throws Exception {
        System.out.println("createNegotiation");
        Negotiator nego = null;
        TenantVenue venue = null;
        
        
        boolean expResult = false;
        boolean result = sasbr.createNegotiation(nego, venue);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of deleteNegotiation method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testDeleteNegotiation() throws Exception {
        System.out.println("deleteNegotiation");
        Negotiator nego = null;
        TenantVenue venue = null;
        
        
        boolean expResult = false;
        boolean result = sasbr.deleteNegotiation(nego, venue);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of createShop method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testCreateShop() throws Exception {
        System.out.println("createShop");
        Negotiator negotiator = null;
        
        
        Shop expResult = null;
        Shop result = sasbr.createShop(negotiator);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of createRes method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testCreateRes() throws Exception {
        System.out.println("createRes");
        Negotiator negotiator = null;
        
        
        Restaurant expResult = null;
        Restaurant result = sasbr.createRes(negotiator);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of attach method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testAttach() throws Exception {
        System.out.println("attach");
        Shop shop = null;
        TenantVenue tv = null;
        
        
        boolean expResult = false;
        boolean result = sasbr.attach(shop, tv);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getShop method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testGetShop() throws Exception {
        System.out.println("getShop");
        Long shopId = null;
        
        
        Shop expResult = null;
        Shop result = sasbr.getShop(shopId);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getRestaurant method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testGetRestaurant() throws Exception {
        System.out.println("getRestaurant");
        Long resId = null;
        
        
        Restaurant expResult = null;
        Restaurant result = sasbr.getRestaurant(resId);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getNegotiators method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testGetNegotiators_0args() throws Exception {
        System.out.println("getNegotiators");
        
        List<Negotiator> result = sasbr.getNegotiators();
        assertNotNull(result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getResNegotiators method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testGetResNegotiators() throws Exception {
        System.out.println("getResNegotiators");
        
        
        List<Negotiator> result = sasbr.getResNegotiators();
        assertNotNull(result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getShopNegotiators method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testGetShopNegotiators() throws Exception {
        System.out.println("getShopNegotiators");
        

        List<Negotiator> result = sasbr.getShopNegotiators();
        assertNotNull(result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getVenueString method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testGetVenueString() throws Exception {
        System.out.println("getVenueString");
        List<TenantVenue> venues = null;
        
        
        String expResult = null;
        String result = sasbr.getVenueString(venues);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getAllShops method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testGetAllShops() throws Exception {
        System.out.println("getAllShops");

        List<Shop> result = sasbr.getAllShops();
        assertNotNull(result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getAllRestaurants method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testGetAllRestaurants() throws Exception {
        System.out.println("getAllRestaurants");
        

        List<Restaurant> result = sasbr.getAllRestaurants();
        assertNotNull(result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getAllHistoryShops method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testGetAllHistoryShops() throws Exception {
        System.out.println("getAllHistoryShops");
        
        
        List<Shop> result = sasbr.getAllHistoryShops();
        assertNotNull(result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getAllActiveShops method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testGetAllActiveShops() throws Exception {
        System.out.println("getAllActiveShops");
        
        
        List<Shop> result = sasbr.getAllActiveShops();
        assertNotNull(result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of deleteNegotiator method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testDeleteNegotiator() throws Exception {
        System.out.println("deleteNegotiator");
        String negoId = "";
        
        
        boolean expResult = false;
        boolean result = sasbr.deleteNegotiator(negoId);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getNegotiator method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testGetNegotiator() throws Exception {
        System.out.println("getNegotiator");
        String negoId = "";
        
        
        Negotiator expResult = null;
        Negotiator result = sasbr.getNegotiator(negoId);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of updateNegotiator method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testUpdateNegotiator() throws Exception {
        System.out.println("updateNegotiator");
        Negotiator n = null;
        
        
        boolean expResult = false;
        boolean result = sasbr.updateNegotiator(n);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of detach method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testDetach() throws Exception {
        System.out.println("detach");
        Shop shop = null;
        TenantVenue v = null;
        
        
        boolean expResult = false;
        boolean result = sasbr.detach(shop, v);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of updateShop method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testUpdateShop() throws Exception {
        System.out.println("updateShop");
        Shop shop = null;
        
        
        boolean expResult = false;
        boolean result = sasbr.updateShop(shop);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of updateRestaurant method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testUpdateRestaurant() throws Exception {
        System.out.println("updateRestaurant");
        Restaurant res = null;
        
        
        boolean expResult = false;
        boolean result = sasbr.updateRestaurant(res);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of registerShop method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testRegisterShop() throws Exception {
        System.out.println("registerShop");
        Shop s = null;
        
        
        boolean expResult = false;
        boolean result = sasbr.registerShop(s);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setNegoStatus method, of class ShopVenueManagementSessionBean.
     */
    @Test
    public void testSetNegoStatus() throws Exception {
        System.out.println("setNegoStatus");
        
        
        boolean expResult = true;
        boolean result = sasbr.setNegoStatus();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }


            private ShopVenueManagementSessionBeanRemote lookupShopVenueManagementSessionBeanRemote() {
        try {
            Context c = new InitialContext();
            return (ShopVenueManagementSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/ShopVenueManagementSessionBean!ShoppingMall.session.ShopVenueManagementSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}