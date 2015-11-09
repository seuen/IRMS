/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.session;

import Common.entity.Announcement;
import Common.entity.Message;
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
public class StaffMessageSessionBeanTest {
    
    StaffMessageSessionBeanRemote smsbr = this.lookupStaffMessageSessionBeanRemote();
    
    public StaffMessageSessionBeanTest() {
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
     * Test of createAnnouncement method, of class StaffMessageSessionBean.
     */
    @Test
    public void testCreateAnnouncement() throws Exception {
        System.out.println("createAnnouncement");
        Long sender = null;
        String title = "";
        String content = "";
        Long departmentId = null;
        
        
        Announcement expResult = null;
        Announcement result = smsbr.createAnnouncement(sender, title, content, departmentId);
        assertNull(result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of createNewMessage method, of class StaffMessageSessionBean.
     */
    @Test
    public void testCreateNewMessage() throws Exception {
        System.out.println("createNewMessage");
        Long sender = null;
        Long receiver = null;
        String title = "";
        String content = "";
        
        
        Message expResult = null;
        Message result = smsbr.createNewMessage(sender, receiver, title, content);
        assertNull(result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

//    /**
//     * Test of getAllMessages method, of class StaffMessageSessionBean.
//     */
//    @Test
//    public void testGetAllMessages() throws Exception {
//        System.out.println("getAllMessages");
//        Long receiver = null;
//        
//        
//        List expResult = null;
//        List result = smsbr.getAllMessages(receiver);
//        assertEquals(expResult, result);
//        
//        // TODO review the generated test code and remove the default call to fail.
//        
//    }
//
//    /**
//     * Test of getAllAnnouncements method, of class StaffMessageSessionBean.
//     */
//    @Test
//    public void testGetAllAnnouncements() throws Exception {
//        System.out.println("getAllAnnouncements");
//        Long staffAccountId = null;
//        
//        
//        List expResult = null;
//        List result = smsbr.getAllAnnouncements(staffAccountId);
//        assertEquals(expResult, result);
//        
//        // TODO review the generated test code and remove the default call to fail.
//        
//    }
//
//    /**
//     * Test of getUnreadMessages method, of class StaffMessageSessionBean.
//     */
//    @Test
//    public void testGetUnreadMessages() throws Exception {
//        System.out.println("getUnreadMessages");
//        Long receiver = null;
//        
//        
//        List expResult = null;
//        List result = smsbr.getUnreadMessages(receiver);
//        assertEquals(expResult, result);
//        
//        // TODO review the generated test code and remove the default call to fail.
//        
//    }

    /**
     * Test of deleteMessage method, of class StaffMessageSessionBean.
     */
    @Test
    public void testDeleteMessage() throws Exception {
        System.out.println("deleteMessage");
        Long id = null;
        
        
        boolean expResult = false;
        boolean result = smsbr.deleteMessage(id);
        assertFalse(result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of deleteAnnouncement method, of class StaffMessageSessionBean.
     */
    @Test
    public void testDeleteAnnouncement() throws Exception {
        System.out.println("deleteAnnouncement");
        Long id = null;
        
        
        boolean expResult = false;
        boolean result = smsbr.deleteAnnouncement(id);
        assertFalse(result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of readMessage method, of class StaffMessageSessionBean.
     */
    @Test
    public void testReadMessage() throws Exception {
        System.out.println("readMessage");
        Long id = null;
        
        
        boolean expResult = false;
        boolean result = smsbr.readMessage(id);
        assertFalse(result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of unreadCount method, of class StaffMessageSessionBean.
     */
    @Test
    public void testUnreadCount() throws Exception {
        System.out.println("unreadCount");
        Long receiver = null;
        
        
        int expResult = 0;
        int result = smsbr.unreadCount(receiver);
        assertNotNull(result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
    private StaffMessageSessionBeanRemote lookupStaffMessageSessionBeanRemote() {
        try {
            Context c = new InitialContext();
            return (StaffMessageSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/StaffMessageSession!Common.session.StaffMessageSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}