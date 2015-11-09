/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.session;

import Common.entity.Schedule;
import Common.entity.Title;
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
public class StaffScheduleSessionBeanTest {
    
    StaffScheduleSessionBeanRemote snsbr = this.lookupStaffScheduleSessionBeanRemote();
    
    public StaffScheduleSessionBeanTest() {
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
     * Test of updateHotelPattern method, of class StaffScheduleSessionBean.
     */
    @Test
    public void testUpdateHotelPattern() throws Exception {
        System.out.println("updateHotelPattern");
        String choice = "";
        
        
        int expResult = 0;
        int result = snsbr.updateHotelPattern(choice);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getHotelShiftPatterns method, of class StaffScheduleSessionBean.
     */
    @Test
    public void testGetHotelShiftPatterns() throws Exception {
        System.out.println("getHotelShiftPatterns");
        
        
        List<String> result = snsbr.getHotelShiftPatterns();
        assertNotNull(result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getAllSchedules method, of class StaffScheduleSessionBean.
     */
    @Test
    public void testGetAllSchedules() throws Exception {
        System.out.println("getAllSchedules");
        
        
        List expResult = null;
        List<Schedule> result = snsbr.getAllSchedules();
        assertNotNull(result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of deleteStaffShiftSchedule method, of class StaffScheduleSessionBean.
     */
    @Test
    public void testDeleteStaffShiftSchedule() throws Exception {
        System.out.println("deleteStaffShiftSchedule");
        Schedule tempSchedule = null;
        
        
        boolean expResult = false;
        boolean result = snsbr.deleteStaffShiftSchedule(tempSchedule);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getHotelSchedule method, of class StaffScheduleSessionBean.
     */
    @Test
    public void testGetHotelSchedule() throws Exception {
        System.out.println("getHotelSchedule");
        int offset = 0;
        String staffLocation = "";
        String staffPosition = "";
        
        
        Schedule expResult = null;
        Schedule result = snsbr.getHotelSchedule(offset, staffLocation, staffPosition);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of createNewHotelSchedule method, of class StaffScheduleSessionBean.
     */
    @Test
    public void testCreateNewHotelSchedule() throws Exception {
        System.out.println("createNewHotelSchedule");
        int offset = 0;
        Title criteria = null;
        
        
        Schedule expResult = null;
        Schedule result = snsbr.createNewHotelSchedule(offset, criteria);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    private StaffScheduleSessionBeanRemote lookupStaffScheduleSessionBeanRemote() {
        try {
            Context c = new InitialContext();
            return (StaffScheduleSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/StaffScheduleSession!Common.session.StaffScheduleSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}