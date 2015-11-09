/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.session;

import Common.entity.CIRInternalLog;
import Common.entity.Staff;
import Common.entity.StaffAccount;
import Common.entity.Title;
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
public class StaffManagementSessionBeanTest {
    
    StaffManagementSessionBeanRemote smsbr = this.lookupStaffManagementSessionBeanRemote();
    
    //Test data
    Title titleKey;
    
    public StaffManagementSessionBeanTest() {
        titleKey = new Title();
        titleKey.setWorkLocation("ShoppingMall");
        titleKey.setPosition("Manager");
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
     * Test of fetchStaffWithTitle method, of class StaffManagementSessionBean.
     */
//    @Test
//    public void testFetchStaffWithTitle() throws Exception {
//        System.out.println("fetchStaffWithTitle");
//        Title criteria = titleKey;                
//        List result = smsbr.fetchStaffWithTitle(criteria);        
//        assertNotNull(result);
//        
//        // TODO review the generated test code and remove the default call to fail.
//        
//    }

    /**
     * Test of fethStaffAccountWithTitle method, of class StaffManagementSessionBean.
     */
//    @Test
//    public void testFethStaffAccountWithTitle() throws Exception {
//        System.out.println("fethStaffAccountWithTitle");
//        Title criteria = titleKey;
//        
//        
//        List result = smsbr.fethStaffAccountWithTitle(criteria);
//        assertNotNull(result);
//        
//        // TODO review the generated test code and remove the default call to fail.
//        
//    }
//
//    /**
//     * Test of getCurStaffTitles method, of class StaffManagementSessionBean.
//     */
//    @Test
//    public void testGetCurStaffTitles() throws Exception {
//        System.out.println("getCurStaffTitles");
//        Staff curStaff = null;
//        
//        
//        List expResult = null;
//        List result = smsbr.getCurStaffTitles(curStaff);
//        assertEquals(expResult, result);
//        
//        // TODO review the generated test code and remove the default call to fail.
//        
//    }
//
//    /**
//     * Test of getAllTitles method, of class StaffManagementSessionBean.
//     */
//    @Test
//    public void testGetAllTitles() throws Exception {
//        System.out.println("getAllTitles");
//        
//        
//        List expResult = null;
//        List result = smsbr.getAllTitles();
//        assertNotNull(result);
//        
//        // TODO review the generated test code and remove the default call to fail.
//        
//    }
//
//    /**
//     * Test of getPositionsForSpeLocation method, of class StaffManagementSessionBean.
//     */
//    @Test
//    public void testGetPositionsForSpeLocation() throws Exception {
//        System.out.println("getPositionsForSpeLocation");
//        String workLocation = "Singland Hotel";
//        boolean normal = true;
//        
//        
//        List expResult = null;
//        List result = smsbr.getPositionsForSpeLocation(workLocation, normal);
//        assertNotNull(result);
//        
//        // TODO review the generated test code and remove the default call to fail.
//        
//    }
//
//    /**
//     * Test of getWorkLocations method, of class StaffManagementSessionBean.
//     */
//    @Test
//    public void testGetWorkLocations() throws Exception {
//        System.out.println("getWorkLocations");
//        String department = "";
//        boolean normal = false;
//        
//        
//        List expResult = null;
//        List result = smsbr.getWorkLocations(department, normal);
//        assertEquals(expResult, result);
//        
//        // TODO review the generated test code and remove the default call to fail.
//        
//    }
//
//    /**
//     * Test of getDepartments method, of class StaffManagementSessionBean.
//     */
//    @Test
//    public void testGetDepartments() throws Exception {
//        System.out.println("getDepartments");
//        String subsystem = "";
//        
//        
//        List expResult = null;
//        List result = smsbr.getDepartments(subsystem);
//        assertEquals(expResult, result);
//        
//        // TODO review the generated test code and remove the default call to fail.
//        
//    }
//
//    /**
//     * Test of checkAccountFrozen method, of class StaffManagementSessionBean.
//     */
    @Test
    public void testCheckAccountFrozen() throws Exception {
        System.out.println("checkAccountFrozen");
        String username = "";
        
        
        boolean result = smsbr.checkAccountFrozen(username);
        assertFalse(result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }
//
//    /**
//     * Test of getStaffByStaffId method, of class StaffManagementSessionBean.
//     */
    @Test
    public void testGetStaffByStaffId() throws Exception {
        System.out.println("getStaffByStaffId");
        Long staffId = Long.valueOf("12");        
        
        Staff expResult = null;
        Staff result = smsbr.getStaffByStaffId(staffId);
        assertNull(result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }
//
//    /**
//     * Test of getStaff method, of class StaffManagementSessionBean.
//     */
    @Test
    public void testGetStaff() throws Exception {
        System.out.println("getStaff");
        String username = "0";
        
        
        Staff expResult = null;
        Staff result = smsbr.getStaff(username);
        if(result != null) System.out.println("result " + result.getFirstName());
        assertNull(result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }
//
//    /**
//     * Test of logCIRInternalLog method, of class StaffManagementSessionBean.
//     */
    @Test
    public void testLogCIRInternalLog() throws Exception {
        System.out.println("logCIRInternalLog");
        CIRInternalLog log = new CIRInternalLog(Long.parseLong("12"),"Grace","Hou","url accessed","http://localhost:8080");
        
        
        boolean expResult = false;
        boolean result = smsbr.logCIRInternalLog(log);
        assertTrue(result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }
//
//    /**
//     * Test of updateTaskStatus method, of class StaffManagementSessionBean.
//     */
//    @Test
//    public void testUpdateTaskStatus() throws Exception {
//        System.out.println("updateTaskStatus");
//        Long staffId = null;
//        Task curTask = null;
//        String newStatus = "";
//        
//        
//        List expResult = null;
//        List result = smsbr.updateTaskStatus(staffId, curTask, newStatus);
//        assertEquals(expResult, result);
//        
//        // TODO review the generated test code and remove the default call to fail.
//        
//    }
//
//    /**
//     * Test of resetPasswordForStaff method, of class StaffManagementSessionBean.
//     */
    @Test
    public void testResetPasswordForStaff1() throws Exception {
        System.out.println("resetPasswordForStaff1");
        Staff resetStaff = smsbr.getStaff("13");
        String oldPass = "un6bgGFd";
        String newPass = "un6bgGFd";
        
        
        String expResult = "change to newPassword successfully";
        String result = smsbr.resetPasswordForStaff(resetStaff, oldPass, newPass);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
    @Test
    public void testResetPasswordForStaff2() throws Exception {
        System.out.println("resetPasswordForStaff2");
        Staff resetStaff = smsbr.getStaff("13");
        String oldPass = "hahahaha";
        String newPass = "un6bgGFd";
        
        
        String expResult = "old password does not match!";
        String result = smsbr.resetPasswordForStaff(resetStaff, oldPass, newPass);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }
//
//    /**
//     * Test of resetStaffAccountPassword method, of class StaffManagementSessionBean.
//     */
    @Test
    public void testResetStaffAccountPassword() throws Exception {
        System.out.println("resetStaffAccountPassword");
        StaffAccount staffAccount = null;
        
        
        String expResult = "staffaccount is null";
        String result = smsbr.resetStaffAccountPassword(staffAccount);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }
//
//    /**
//     * Test of resetStaffAccountStatus method, of class StaffManagementSessionBean.
//     */
    @Test
    public void testResetStaffAccountStatus() throws Exception {
        System.out.println("resetStaffAccountStatus");
        StaffAccount staffAccount = null;
        
        
        boolean expResult = false;
        boolean result = smsbr.resetStaffAccountStatus(staffAccount);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }
//
//    /**
//     * Test of frozeStaffAccount method, of class StaffManagementSessionBean.
//     */
    @Test
    public void testFrozeStaffAccount() throws Exception {
        System.out.println("frozeStaffAccount");
        Staff staff = null;
        
        
        boolean expResult = false;
        boolean result = smsbr.frozeStaffAccount(staff);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }
//
//    /**
//     * Test of addExtraTitle method, of class StaffManagementSessionBean.
//     */
//    @Test
//    public void testAddExtraTitle() throws Exception {
//        System.out.println("addExtraTitle");
//        String extraTitle = "";
//        Long tempStaffId = null;
//        
//        
//        List expResult = null;
//        List result = smsbr.addExtraTitle(extraTitle, tempStaffId);
//        assertEquals(expResult, result);
//        
//        // TODO review the generated test code and remove the default call to fail.
//        
//    }
//
//    /**
//     * Test of createSuper method, of class StaffManagementSessionBean.
//     */
    @Test
    public void testCreateSuper() throws Exception {
        System.out.println("createSuper");
        
        
        boolean expResult = true;
        boolean result = smsbr.createSuper();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }
//
//    /**
//     * Test of addStaff method, of class StaffManagementSessionBean.
//     */
    @Test
    public void testAddStaff1() throws Exception {
        System.out.println("addStaff1");
        Staff staff = new Staff();
        staff.setFirstName("Grace");
        staff.setLastName("Hou");
        boolean createAccount = false;
        String titleKey = "Singland HotelHouseKeeping";
        
        
        String expResult = "NoAccount";
        String result = smsbr.addStaff(staff, createAccount, titleKey);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
    @Test
    public void testAddStaff2() throws Exception {
        System.out.println("addStaff2");
        Staff staff = new Staff();
        staff.setFirstName("Mr. Bean");
        staff.setLastName("Tasty");
        boolean createAccount = true;
        String titleKey = "ShoppingMallManager";
        
        
        String expResult = "Password";
        String result = smsbr.addStaff(staff, createAccount, titleKey);
        String[] results = result.split("_");
        assertEquals(expResult, results[0]);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
    @Test
    public void testAddStaff3() throws Exception {
        System.out.println("addStaff3");
        Staff staff = null;
        boolean createAccount = false;
        String titleKey = "";
        
        
        String expResult = "fail to add new staff";
        String result = smsbr.addStaff(staff, createAccount, titleKey);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }
//
//    /**
//     * Test of addNewTaskToStaff method, of class StaffManagementSessionBean.
//     */
//    @Test
//    public void testAddNewTaskToStaff() throws Exception {
//        System.out.println("addNewTaskToStaff");
//        Long staffId = null;
//        Task newStaffTask = null;
//        
//        
//        List expResult = null;
//        List result = smsbr.addNewTaskToStaff(staffId, newStaffTask);
//        assertEquals(expResult, result);
//        
//        // TODO review the generated test code and remove the default call to fail.
//        
//    }
//
//    /**
//     * Test of replaceStaff method, of class StaffManagementSessionBean.
//     */
    @Test
    public void testReplaceStaff() throws Exception {
        System.out.println("replaceStaff");
        Staff staff = null;
        
        
        boolean expResult = false;
        boolean result = smsbr.replaceStaff(staff);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }
//
//    /**
//     * Test of deleteExtraTitle method, of class StaffManagementSessionBean.
//     */
//    @Test
//    public void testDeleteExtraTitle() throws Exception {
//        System.out.println("deleteExtraTitle");
//        Staff tempStaff = null;
//        Title extraTitle = null;
//        
//        
//        List expResult = null;
//        List result = smsbr.deleteExtraTitle(tempStaff, extraTitle);
//        assertEquals(expResult, result);
//        
//        // TODO review the generated test code and remove the default call to fail.
//        
//    }
//
//    /**
//     * Test of deleteTaskToStaff method, of class StaffManagementSessionBean.
//     */
//    @Test
//    public void testDeleteTaskToStaff() throws Exception {
//        System.out.println("deleteTaskToStaff");
//        Long staffId = null;
//        Task remTask = null;
//        
//        
//        List expResult = null;
//        List result = smsbr.deleteTaskToStaff(staffId, remTask);
//        assertEquals(expResult, result);
//        
//        // TODO review the generated test code and remove the default call to fail.
//        
//    }
//
//    /**
//     * Test of remove method, of class StaffManagementSessionBean.
//     */
//    @Test
//    public void testRemove() throws Exception {
//        System.out.println("remove");
//        Long remStaffId = null;
//        
//        
//        List expResult = null;
//        List result = smsbr.remove(remStaffId);
//        assertEquals(expResult, result);
//        
//        // TODO review the generated test code and remove the default call to fail.
//        
//    }
//
//    /**
//     * Test of checkStaffPassword method, of class StaffManagementSessionBean.
//     */
    @Test
    public void testCheckStaffPassword() throws Exception {
        System.out.println("checkStaffPassword");
        String username = "13";
        String password = "un6bgGFd";
        
        
        boolean expResult = true;
        boolean result = smsbr.checkStaffPassword(username, password);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }
//
//    /**
//     * Test of getPassLength method, of class StaffManagementSessionBean.
//     */
    @Test
    public void testGetPassLength() throws Exception {
        System.out.println("getPassLength");
        
        
        int expResult = 8;
        int result = smsbr.getPassLength();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
    private StaffManagementSessionBeanRemote lookupStaffManagementSessionBeanRemote() {
        try {
            Context c = new InitialContext();
            return (StaffManagementSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/StaffManagementSession!Common.session.StaffManagementSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}