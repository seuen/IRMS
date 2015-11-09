/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingMall.session;

import Common.session.StaffAccountSessionBeanRemote;
import ShoppingMall.entity.TenantBill;
import ShoppingMall.entity.TenantReceipt;
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
public class BillingManagementSessionBeanTest {
    
    BillingManagementSessionBeanRemote sasbr = this.lookupBillingManagementSessionBeanRemote();
    
    public BillingManagementSessionBeanTest() {
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
     * Test of checkBadDebt method, of class BillingManagementSessionBean.
     */
    @Test
    public void testCheckBadDebt() throws Exception {
        System.out.println("checkBadDebt");
        
        
        boolean expResult = true;
        boolean result = sasbr.checkBadDebt();
        assertEquals(expResult, result);
       
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of sendMonthlyBills method, of class BillingManagementSessionBean.
     */
    @Test
    public void testSendMonthlyBills() throws Exception {
        System.out.println("sendMonthlyBills");
        
        
        boolean expResult = true;
        boolean result = sasbr.sendMonthlyBills();
        assertEquals(expResult, result);
       
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getBill method, of class BillingManagementSessionBean.
     */
    @Test
    public void testGetBill() throws Exception {
        System.out.println("getBill");
        Long billid = null;

        TenantBill expResult = null;
        TenantBill result = sasbr.getBill(billid);
        assertNull(result);
       
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of editBill method, of class BillingManagementSessionBean.
     */
    @Test
    public void testEditBill() throws Exception {
        System.out.println("editBill");
        Long billid = null;
        Date deadline = null;
        String status = "";
        
        
        TenantBill expResult = null;
        TenantBill result = sasbr.editBill(billid, deadline, status);
        assertEquals(expResult, result);
       
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of deleteBill method, of class BillingManagementSessionBean.
     */
    @Test
    public void testDeleteBill() throws Exception {
        System.out.println("deleteBill");
        Long billid = null;
        
        
        String expResult = "";
        String result = sasbr.deleteBill(billid);
        assertEquals(expResult, result);
       
        // TODO review the generated test code and remove the default call to fail.

    }


    /**
     * Test of getAllResBills method, of class BillingManagementSessionBean.
     */
    @Test
    public void testGetAllResBills() throws Exception {
        System.out.println("getAllResBills");

        List<TenantBill> result = sasbr.getAllResBills();
        assertNotNull(result);
       
        // TODO review the generated test code and remove the default call to fail.

    }


    /**
     * Test of getBadDebtResBills method, of class BillingManagementSessionBean.
     */
    @Test
    public void testGetBadDebtResBills() throws Exception {
        System.out.println("getBadDebtResBills");
        
        
        List<TenantBill> expResult = new ArrayList<TenantBill>();
        List<TenantBill> result = sasbr.getBadDebtResBills();
        assertEquals(expResult, result);
       
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of createReceipt method, of class BillingManagementSessionBean.
     */
    @Test
    public void testCreateReceipt() throws Exception {
        System.out.println("createReceipt");
        TenantBill bill = null;
        
        
        TenantReceipt expResult = null;
        TenantReceipt result = sasbr.createReceipt(bill);
        assertEquals(expResult, result);
       
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getAllReceipts method, of class BillingManagementSessionBean.
     */
    @Test
    public void testGetAllReceipts() throws Exception {
        System.out.println("getAllReceipts");
        
        
        List<TenantReceipt> expResult = new ArrayList<TenantReceipt>();
        List<TenantReceipt> result = sasbr.getAllReceipts();
        assertEquals(expResult, result);
       
        // TODO review the generated test code and remove the default call to fail.

    }



    /**
     * Test of getReceipt method, of class BillingManagementSessionBean.
     */
    @Test
    public void testGetReceipt() throws Exception {
        System.out.println("getReceipt");
        Long receiptId = null;
        
        
        TenantReceipt expResult = null;
        TenantReceipt result = sasbr.getReceipt(receiptId);
        assertEquals(expResult, result);
       
        // TODO review the generated test code and remove the default call to fail.

    }
        private BillingManagementSessionBeanRemote lookupBillingManagementSessionBeanRemote() {
        try {
            Context c = new InitialContext();
            return (BillingManagementSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/BillingManagementSessionBean!ShoppingMall.session.BillingManagementSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}