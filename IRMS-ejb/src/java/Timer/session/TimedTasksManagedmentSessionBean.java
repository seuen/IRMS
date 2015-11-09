/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Timer.session;

import Accommodation.session.CheckOutSessionBeanLocal;
import CRM.session.CRManalysisSessionBeanLocal;
import CRM.session.StaffManageMemberSessionBeanLocal;
import ShoppingMall.session.BillingManagementSessionBeanLocal;
import ShoppingMall.session.ChargesManagementSessionBeanLocal;
import ShoppingMall.session.ContractManagementSessionBeanLocal;
import ShoppingMall.session.ShopVenueManagementSessionBeanLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;

/**
 *
 * @author Cindylulu
 */
@Stateless
@LocalBean
public class TimedTasksManagedmentSessionBean {

    @EJB
    ChargesManagementSessionBeanLocal chargesmsb;
    @EJB
    ContractManagementSessionBeanLocal contractmsb;
    @EJB
    BillingManagementSessionBeanLocal billingmsb;
    @EJB
    CheckOutSessionBeanLocal cosbl;
    @EJB
    StaffManageMemberSessionBeanLocal smmsbl;
    @EJB
    ShopVenueManagementSessionBeanLocal svmsbl;

    //******************************************************************
    // How to specify time: JAVA EE 6 Tutorial Pages 479, 480, 483, 484.
    // Usefule Examples:
    // Run every 2 seconds: 
    // @Schedule (second="*/2", minute="*", hour="*")
    // Run every day at 18:38
    // @Schedule (second="0", minute="38", hour="18", dayOfWeek="*")
    // Run every week on Thursday at 18:38
    // @Schedule (second="0", minute="38", hour="18", dayOfWeek="Thu")
    // Run every first day of month at 18:38
    // @Schedule (second="0", minute="38", hour="18", dayOfMonth="1")
    // Run every last day of month at 18:38
    // @Schedule (second="0", minute="38", hour="18", dayOfMonth="Last")
    //********************************************************************

    @Schedule(second = "00", minute = "50", hour = "21", dayOfWeek = "*")
    public void updateroom() {
        cosbl.updateRooms();
    }

    @Schedule(second = "00", minute = "50", hour = "21", dayOfWeek = "*")
    public void updatHKstatus() {
        cosbl.updateHKstatus();
    }

    // Daily beginning
    @Schedule(second = "00", minute = "00", hour = "22", dayOfWeek = "*")
    public void updateContractStatus() {
        System.out.println("[Daily 00:03] Update contract status start..");
        contractmsb.setStatusContracts();
        System.out.println("[Daily 00:03] Update contract status finished.");
    }

    // Daily beginning
    @Schedule(second = "10", minute = "00", hour = "22", dayOfWeek = "*")
    public void updateVenueNegoStatus() {
        System.out.println("[Daily 00:06] Update venue negotiation availability status start..");
        svmsbl.setNegoStatus();
        System.out.println("[Daily 00:06] Update venue negotiation availability status finished.");
    }

    // Daily beginning
    @Schedule(second = "10", minute = "00", hour = "22", dayOfWeek = "*")
    public void sendContractLapseAndRenewReminders() {
        System.out.println("[Daily 00:06] Send contract lapse and renew reminders start..");
        contractmsb.contractLapseAndRenewRemind();
        System.out.println("[Daily 00:06] Send contract lapse and renew reminders finished.");
    }
    
    // Daily ending
    @Schedule(second = "00", minute = "05", hour = "22", dayOfWeek = "*")
    public void checkBadDebt() {
        System.out.println("[Daily 23:30] Check Bad Debts start..");
        billingmsb.checkBadDebt();
        System.out.println("[Daily 23:30] Check Bad Debts finished.");
    }

    // Monthly ending
    @Schedule(second = "30", minute = "05", hour = "22", dayOfMonth = "21")
    public void generateMonthlyRentalReports() {
        System.out.println("[Monthly Last Day 23:33] Generate monthly rental reports start..");
        chargesmsb.createRentalReports();
        System.out.println("[Monthly Last Day 23:33] Generate monthly rental reports finished.");
    }

    // Monthly ending
    @Schedule(second = "00", minute = "05", hour = "22", dayOfMonth = "21")
    public void generateaAndSendMonthlyBills() {
        System.out.println("[Monthly Last Day 23:36] Generate and send monthly bills start..");
        billingmsb.sendMonthlyBills();
        System.out.println("[Monthly Last Day 23:36] Generate and send monthly bills finished.");
    }

    @Schedule(second = "35", minute = "55", hour = "21", dayOfWeek = "*")
    public void sendBirthdayEmail() {
        System.out.println("[Daily 00:00] Check Member Birthday starts..");
        smmsbl.sendBirthdayEmail();
        System.out.println("[Daily 00:00] Check Member Birthday finishes..");
    }
}
