/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingMall.session;

import ShoppingMall.entity.TenantBill;
import ShoppingMall.entity.TenantReceipt;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Cindylulu
 */
@Local 
public interface BillingManagementSessionBeanLocal {
   
    public List<TenantBill> createBills();   // Automatically create bills for activated contracts monthly
    public boolean checkBadDebt();  // Automatically checks bill status daily to update baddebt status
    public boolean sendMonthlyBills();// ---- Automatically calls the above function, and send the returned list of bills to tenants monthly
    public TenantBill getBill(Long billid);// For display information of a bill
    public TenantBill editBill(Long billid, Date deadline, String status);// For editting possible fields in a bill, status: unpaid, paid, badDebt(if unpaid after deadline)
    public String deleteBill(Long billid);// Applicable when bill was not outstanding
    public List<TenantBill> getRecentMonthBills(Date day);// For display in home page, first column should be shopId
    public List<TenantBill> getRecentMonthResBills(Date day);
    public List<TenantBill> getAllBills();// For display in viewAllBills' tab page (could search by shopId or time-use string for time. refer to Contract date strings)
    public List<TenantBill> getAllResBills();
    public List<TenantBill> getAllUnpaidBills();// For display in viewAllUnpaidBills' tab page
    public List<TenantBill> getAllUnpaidResBills();
    public List<TenantBill> getBadDebtBills();
    public List<TenantBill> getBadDebtResBills();
    public TenantReceipt createReceipt(TenantBill bill);// Triggered when user click pay bill in home page datatable row
    public List<TenantReceipt> getAllReceipts();// For display in viewAllReceipts' tab page (search by shop or time-use string for time. refer to Contract date strings)
    public List<TenantReceipt> getAllResReceipts();
    public TenantReceipt getReceipt(Long receiptId);// For display information of a receipt after clicking "view" in all receipts' datatable
}
