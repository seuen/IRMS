/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingMall.session;

import ShoppingMall.entity.AdhocEvent;
import ShoppingMall.entity.RentalReports;
import ShoppingMall.entity.Shop;
import java.util.ArrayList;
import java.util.Date;
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
public class ChargesManagementSessionBeanTest {
    
    ChargesManagementSessionBeanRemote sasbr = this.lookupChargesManagementSessionBeanRemote();
    
    public ChargesManagementSessionBeanTest() {
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
     * Test of getShop method, of class ChargesManagementSessionBean.
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
     * Test of mergeShop method, of class ChargesManagementSessionBean.
     */
    @Test
    public void testMergeShop() throws Exception {
        System.out.println("mergeShop");
        Shop shop = null;
        
        
        boolean expResult = false;
        boolean result = sasbr.mergeShop(shop);
        assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getAllAdhocEvents method, of class ChargesManagementSessionBean.
     */
    @Test
    public void testGetAllAdhocEvents() throws Exception {
        System.out.println("getAllAdhocEvents");
        
        
        List<AdhocEvent> expResult = new ArrayList<AdhocEvent>();
        List<AdhocEvent> result = sasbr.getAllAdhocEvents();
        assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
        
    }



    /**
     * Test of computeAdhocCharges method, of class ChargesManagementSessionBean.
     */
    @Test
    public void testComputeAdhocCharges() throws Exception {
        System.out.println("computeAdhocCharges");
        List<AdhocEvent> events = null;
        
        
        float expResult = 0.0F;
        float result = sasbr.computeAdhocCharges(events);
        assertEquals(expResult, result, 0.0);

        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of addAdhocEvents method, of class ChargesManagementSessionBean.
     */
    @Test
    public void testAddAdhocEvents() throws Exception {
        System.out.println("addAdhocEvents");
        String eventType = "";
        float charge = 0.0F;
        String description = "";
        Date eventDate = null;
        Long shopId = null;
        
        
        Long expResult = null;
        Long result = sasbr.addAdhocEvents(eventType, charge, description, eventDate, shopId);
        assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of createRentalReports method, of class ChargesManagementSessionBean.
     */
    @Test
    public void testCreateRentalReports() throws Exception {
        System.out.println("createRentalReports");
        
        
        boolean expResult = true;
        boolean result = sasbr.createRentalReports();
        assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getRentalReports method, of class ChargesManagementSessionBean.
     */
    @Test
    public void testGetRentalReports() throws Exception {
        System.out.println("getRentalReports");
        Long rID = null;
        
        
        RentalReports expResult = null;
        RentalReports result = sasbr.getRentalReports(rID);
        assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getMyMonthlyAdhocEvents method, of class ChargesManagementSessionBean.
     */
    @Test
    public void testGetMyMonthlyAdhocEvents() throws Exception {
        System.out.println("getMyMonthlyAdhocEvents");
        Long shopId = null;
        Date date = null;
        
        
        List expResult = null;
        List result = sasbr.getMyMonthlyAdhocEvents(shopId, date);
        assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
        
    }
            private ChargesManagementSessionBeanRemote lookupChargesManagementSessionBeanRemote() {
        try {
            Context c = new InitialContext();
            return (ChargesManagementSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/ChargesManagementSessionBean!ShoppingMall.session.ChargesManagementSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}