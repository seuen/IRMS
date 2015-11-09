/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Accommodation.session;

import Accommodation.entity.Stay;
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
 * @author yifeng
 */
public class CheckOutSessionBeanTest {
    
    CheckOutSessionBeanRemote sasbr = this.lookupCheckOutSessionBeanRemote();
    
    public CheckOutSessionBeanTest() {
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
     * Test of updateHKstatus method, of class CheckOutSessionBean.
     */
    @Test
    public void testUpdateHKstatus() throws Exception {
        System.out.println("updateHKstatus");
        
        
        boolean expResult = true;
        boolean result = sasbr.updateHKstatus();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of updateRooms method, of class CheckOutSessionBean.
     */
    @Test
    public void testUpdateRooms() throws Exception {
        System.out.println("updateRooms");
        
        
        boolean expResult = true;
        boolean result = sasbr.updateRooms();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of payCharges method, of class CheckOutSessionBean.
     */
    @Test
    public void testPayCharges() throws Exception {
        System.out.println("payCharges");
        Stay stay = null;
        
        
        boolean expResult = false;
        boolean result = sasbr.payCharges(stay);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of checkout method, of class CheckOutSessionBean.
     */
    @Test
    public void testCheckout() throws Exception {
        System.out.println("checkout");
        Stay stay = null;
        
        
        boolean expResult = false;
        boolean result = sasbr.checkout(stay);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getCheckOutStay method, of class CheckOutSessionBean.
     */
    @Test
    public void testGetCheckOutStay() throws Exception {
        System.out.println("getCheckOutStay");
        Date checkout = null;
        
        
        List expResult = null;
        List result = sasbr.getCheckOutStay(checkout);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
    private CheckOutSessionBeanRemote lookupCheckOutSessionBeanRemote() {
        try {
            Context c = new InitialContext();
            return (CheckOutSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/checkOutSession!Accommodation.session.CheckOutSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}