/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.session;

import Common.entity.Notification;
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
public class StaffNotificationSessionBeanTest {
    
    StaffNotificationSessionBeanRemote snsbr = this.lookupStaffNotificationSessionBeanRemote();
    
    public StaffNotificationSessionBeanTest() {
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
     * Test of createNotification method, of class StaffNotificationSessionBean.
     */
    @Test
    public void testCreateNotification() throws Exception {
        System.out.println("createNotification");
        Long sender = null;
        String content = "";
        
        
        Notification expResult = null;
        Notification result = snsbr.createNotification(sender, content);
        assertNull(result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

//    /**
//     * Test of getNotficationRecords method, of class StaffNotificationSessionBean.
//     */
//    @Test
//    public void testGetNotficationRecords() throws Exception {
//        System.out.println("getNotficationRecords");
//        Long receiver = null;
//        
//        
//        List expResult = null;
//        List result = snsbr.getNotficationRecords(receiver);
//        assertEquals(expResult, result);
//        
//        // TODO review the generated test code and remove the default call to fail.
//        
//    }

    /**
     * Test of deleteNotificationRecord method, of class StaffNotificationSessionBean.
     */
    @Test
    public void testDeleteNotificationRecord() throws Exception {
        System.out.println("deleteNotificationRecord");
        Long id = null;
        
        
        boolean expResult = false;
        boolean result = snsbr.deleteNotificationRecord(id);
        assertFalse(result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
    private StaffNotificationSessionBeanRemote lookupStaffNotificationSessionBeanRemote() {
        try {
            Context c = new InitialContext();
            return (StaffNotificationSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/StaffNotificationSession!Common.session.StaffNotificationSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}