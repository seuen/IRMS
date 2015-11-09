/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.session;

import Common.entity.Staff;
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
public class StaffAccountSessionBeanTest {
    
    StaffAccountSessionBeanRemote sasbr = this.lookupStaffAccountSessionBeanRemote();
    
    public StaffAccountSessionBeanTest() {
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
     * Test of findStaff method, of class StaffAccountSessionBean.
     */
    @Test
    public void testFindStaff() throws Exception {
        System.out.println("findStaff");
        Long staffAccountId = null;                
        
        Staff result = sasbr.findStaff(staffAccountId);
        assertNull(result);        
        // TODO review the generated test code and remove the default call to fail.        
    }

    /**
     * Test of getAllTitles method, of class StaffAccountSessionBean.
     */
    @Test
    public void testGetAllTitles() throws Exception {
        System.out.println("getAllTitles");
        Long staffAccountId = null;
                
        List expResult = null;
        List<Title> result = sasbr.getAllTitles(staffAccountId);
        assertEquals(expResult, result);        
        // TODO review the generated test code and remove the default call to fail.        
    }


    /**
     * Test of updateStaff method, of class StaffAccountSessionBean.
     */
    @Test
    public void testUpdateStaff() throws Exception {
        System.out.println("updateStaff");
        Staff staff = null;        
        
        boolean expResult = false;
        boolean result = sasbr.updateStaff(staff);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of updateEmail method, of class StaffAccountSessionBean.
     */
    @Test
    public void testUpdateEmail() throws Exception {
        System.out.println("updateEmail");
        Long id = null;
        String email = "";
        
        
        boolean expResult = false;
        boolean result = sasbr.updateEmail(id, email);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
    private StaffAccountSessionBeanRemote lookupStaffAccountSessionBeanRemote() {
        try {
            Context c = new InitialContext();
            return (StaffAccountSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/StaffAccountSession!Common.session.StaffAccountSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}