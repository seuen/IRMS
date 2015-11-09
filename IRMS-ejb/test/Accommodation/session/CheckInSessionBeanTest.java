/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Accommodation.session;

import Accommodation.entity.RTReservation;
import Accommodation.entity.Stay;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CheckInSessionBeanTest {
    
    CheckInSessionBeanRemote sasbr = this.lookupCheckInSessionBeanRemote();
    
    public CheckInSessionBeanTest() {
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
     * Test of updateReservationCheckIn method, of class CheckInSessionBean.
     */
    @Test
    public void testUpdateReservationCheckIn() throws Exception {
        System.out.println("updateReservationCheckIn");
        
        
        boolean expResult = true;
        boolean result = sasbr.updateReservationCheckIn();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getcheckinStay method, of class CheckInSessionBean.
     */
    @Test
    public void testGetcheckinStay() throws Exception {
        System.out.println("getcheckinStay");
        
        
        List expResult = null;
        List<Stay> result = sasbr.getcheckinStay();
        assertNotNull(result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of updateReservation method, of class CheckInSessionBean.
     */
    @Test
    public void testUpdateReservation() throws Exception {
        System.out.println("updateReservation");
        RTReservation reservation = null;
        
        
        boolean expResult = false;
        boolean result = sasbr.updateReservation(reservation);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of checkCreservation method, of class CheckInSessionBean.
     */
    @Test
    public void testCheckCreservation() throws Exception {
        System.out.println("checkCreservation");
        RTReservation reservation = null;
        
        
        boolean expResult = true;
        boolean result = sasbr.checkCreservation(reservation);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
    private CheckInSessionBeanRemote lookupCheckInSessionBeanRemote() {
        try {
            Context c = new InitialContext();
            return (CheckInSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/checkInSession!Accommodation.session.CheckInSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}