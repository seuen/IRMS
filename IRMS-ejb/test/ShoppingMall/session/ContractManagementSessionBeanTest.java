/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingMall.session;

import ShoppingMall.entity.Contract;
import ShoppingMall.entity.Shop;
import ShoppingMall.entity.TenantVenue;
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
public class ContractManagementSessionBeanTest {
    ContractManagementSessionBeanRemote sasbr = this.lookupContractManagementSessionBeanRemote();
    
    public ContractManagementSessionBeanTest() {
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
     * Test of createContract method, of class ContractManagementSessionBean.
     */
    @Test
    public void testCreateContract() throws Exception {
        System.out.println("createContract");
        String lessee = "";
        Date leaseDateFrom = null;
        int leaseTerm = 0;
        float monthlyRental = 0.0F;
        float commissionRate = 0.0F;
        float deposit = 0.0F;
        List<TenantVenue> venues = null;
        String negoId = "";
        float baselineRental = 0.0F;
        
        
        Contract expResult = null;
        Contract result = sasbr.createContract(lessee, leaseDateFrom, leaseTerm, monthlyRental, commissionRate, deposit, venues, negoId, baselineRental);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of renewContract method, of class ContractManagementSessionBean.
     */
    @Test
    public void testRenewContract() throws Exception {
        System.out.println("renewContract");
        Date leaseDateFrom = null;
        int leaseTerm = 0;
        float monthlyRental = 0.0F;
        float commissionRate = 0.0F;
        float deposit = 0.0F;
        float baselineRental = 0.0F;
        Shop shop = null;
        
        
        Contract expResult = null;
        Contract result = sasbr.renewContract(leaseDateFrom, leaseTerm, monthlyRental, commissionRate, deposit, baselineRental, shop);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of attachContractShop method, of class ContractManagementSessionBean.
     */
    @Test
    public void testAttachContractShop() throws Exception {
        System.out.println("attachContractShop");
        Contract con = null;
        Shop s = null;
        
        
        boolean expResult = false;
        boolean result = sasbr.attachContractShop(con, s);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of updateContract method, of class ContractManagementSessionBean.
     */
    @Test
    public void testUpdateContract() throws Exception {
        System.out.println("updateContract");
        Contract contract = null;
        
        
        Contract expResult = null;
        Contract result = sasbr.updateContract(contract);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of deleteContract method, of class ContractManagementSessionBean.
     */
    @Test
    public void testDeleteContract() throws Exception {
        System.out.println("deleteContract");
        Long contractId = null;
        
        
        String expResult = null;
        String result = sasbr.deleteContract(contractId);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getContract method, of class ContractManagementSessionBean.
     */
    @Test
    public void testGetContract() throws Exception {
        System.out.println("getContract");
        Long contractId = null;
        
        
        Contract expResult = null;
        Contract result = sasbr.getContract(contractId);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

//    /**
//     * Test of getAllContracts method, of class ContractManagementSessionBean.
//     */
//    @Test
//    public void testGetAllContracts() throws Exception {
//        System.out.println("getAllContracts");
//        
//
//        List<Contract> result = sasbr.getAllContracts();
//        assertNotNull(result);
//        
//        // TODO review the generated test code and remove the default call to fail.
//        
//    }

//    /**
//     * Test of getAllResContracts method, of class ContractManagementSessionBean.
//     */
//    @Test
//    public void testGetAllResContracts() throws Exception {
//        System.out.println("getAllResContracts");
//        
//        List<Contract> result = sasbr.getAllResContracts();
//        assertNotNull(result);
//        
//        // TODO review the generated test code and remove the default call to fail.
//        
//    }

    /**
     * Test of setStatusContracts method, of class ContractManagementSessionBean.
     */
    @Test
    public void testSetStatusContracts() throws Exception {
        System.out.println("setStatusContracts");
        
        
        boolean expResult = true;
        boolean result = sasbr.setStatusContracts();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }



    /**
     * Test of getMyContracts method, of class ContractManagementSessionBean.
     */
    @Test
    public void testGetMyContracts() throws Exception {
        System.out.println("getMyContracts");
        Long shopId = null;
        
        
        List expResult = null;
        List<Contract> result = sasbr.getMyContracts(shopId);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of contractLapseAndRenewRemind method, of class ContractManagementSessionBean.
     */
    @Test
    public void testContractLapseAndRenewRemind() throws Exception {
        System.out.println("contractLapseAndRenewRemind");
        
        
        boolean expResult = true;
        boolean result = sasbr.contractLapseAndRenewRemind();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
            private ContractManagementSessionBeanRemote lookupContractManagementSessionBeanRemote() {
        try {
            Context c = new InitialContext();
            return (ContractManagementSessionBeanRemote) c.lookup("java:global/IRMS/IRMS-ejb/ContractManagementSessionBean!ShoppingMall.session.ContractManagementSessionBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}