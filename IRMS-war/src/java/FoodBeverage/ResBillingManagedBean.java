/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FoodBeverage;

import ShoppingMall.entity.AdhocEvent;
import ShoppingMall.entity.TenantBill;
import ShoppingMall.entity.TenantReceipt;
import ShoppingMall.session.BillingManagementSessionBeanLocal;
import ShoppingMall.session.ChargesManagementSessionBeanLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import util.EmailManager;

/**
 *
 * @author Cindylulu
 */
@ManagedBean
@SessionScoped
public class ResBillingManagedBean implements Serializable {

    private List<TenantBill> reportingBills = new ArrayList();
    private List<TenantBill> unpaidBills = new ArrayList();
    private List<TenantBill> allBills = new ArrayList();
    private List<TenantBill> badDebtBills = new ArrayList();
    private List<TenantReceipt> allreceipts = new ArrayList();
    private TenantBill bill;
    private TenantReceipt receipt;
    private List<AdhocEvent> historyMonthlyAdhoc = new ArrayList();
    @EJB
    private BillingManagementSessionBeanLocal bmsb;
    @EJB
    private ChargesManagementSessionBeanLocal cmsb;

    public ResBillingManagedBean() {
    }

    /**
     * @param reportingBills the reportingBills to set
     */
    public void setReportingBills(List<TenantBill> reportingBills) {
        this.reportingBills = reportingBills;
    }

    /**
     * @return the reportingBills
     */
    public List<TenantBill> getReportingBills() {

            this.setReportingBills(bmsb.getRecentMonthResBills(Calendar.getInstance().getTime()));

        return reportingBills;
    }

    public void update() {
        Date day = Calendar.getInstance().getTime();
        if ((int) ((day.getTime() - bill.getBillDate().getTime()) / (1000 * 60 * 60 * 24)) < 32) {
            bill = bmsb.editBill(bill.getId(), bill.getDeadLine(), bill.getStatus());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Update successfully!", ""));
            this.resetAll();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cannot update because bill was frozen as history.", ""));
        }
    }

    public void navigateBillInfo(ActionEvent event) throws IOException {
        System.out.println("inside navigateBillInfo");
        Long billid = Long.valueOf(event.getComponent().getAttributes().get("billid").toString());
        if (!billid.toString().isEmpty()) {
            this.setBill(bmsb.getBill(billid));
            this.setHistoryMonthlyAdhoc(cmsb.getMyMonthlyAdhocEvents(bill.getRentalReports().getContract().getShop().getShopId(), bill.getBillDate()));
            FacesContext.getCurrentInstance().getExternalContext().redirect("viewResBill.xhtml");
        } else {
            System.out.println("billId does not get correctly");
        }
    }

    public void navigateReceiptInfo(ActionEvent event) throws IOException {
        System.out.println("inside navigateReceiptInfo");
        Long rid = Long.valueOf(event.getComponent().getAttributes().get("rid").toString());
        if (!rid.toString().isEmpty()) {
            this.setReceipt(bmsb.getReceipt(rid));
            FacesContext.getCurrentInstance().getExternalContext().redirect("viewResReceipt.xhtml");
        } else {
            System.out.println("receiptId does not get correctly");
        }
    }

    public void removeInTable(ActionEvent event) throws IOException {
        try {
            System.out.println("inside removeInTable");
            TenantBill b = (TenantBill) event.getComponent().getAttributes().get("bill");
            String msg = bmsb.deleteBill(b.getId());
            if (msg.compareTo("Delete bill successfully!") != 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, ""));
            } else {
                this.resetAll();
                FacesContext.getCurrentInstance().getExternalContext().redirect("viewAllUnpaidResBills.xhtml");
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Unknown Error Occurred", ""));
        }
    }

    public void createReceipt(ActionEvent event) {
        try {
            TenantBill b = (TenantBill) event.getComponent().getAttributes().get("b");
            System.err.println("inside createReceipt: " + b.getId());
            if (!b.getStatus().equalsIgnoreCase("Paid")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cannot create receipt because uanpaid or no receipt available.", ""));
            } else {
                TenantReceipt r = bmsb.createReceipt(b);
                if (r == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cannot create receipt because receip already exists.", ""));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Create receipt successfully.", "Receipt ID is "+r.getId()));
                    this.resetAll();
                }
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Unknown Error Occurred", ""));
        }
    }

    public void sendReceipt(ActionEvent event) {
        try {
            TenantBill b = (TenantBill)event.getComponent().getAttributes().get("b2");
            TenantBill b2=bmsb.getBill(b.getId());
            System.err.println("inside sendReceipt");
            if (b2.getTenantReceipt() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Cannot send receipt because unpaid or no receipt.", ""));
            } else {
                EmailManager emailManager = new EmailManager();
                System.out.println("receipt email sender");
                emailManager.sendReceipt(b2.getTenantReceipt());
                System.out.println("after receipt email sent");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Receipt sent successfully!", "Receipt number is "+b2.getTenantReceipt().getId()));

            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unknown Error Occurred", ""));
        }
    }

    public void sendBill(ActionEvent event) {
        try {
            TenantBill b = (TenantBill) event.getComponent().getAttributes().get("bb");
            EmailManager emailManager = new EmailManager();
            emailManager.sendBill(b);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bill sent successfully!", ""));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unknown Error Occurred", ""));
        }
    }

    /**
     * @return the bill
     */
    public TenantBill getBill() {
        return bill;
    }

    /**
     * @param bill the bill to set
     */
    public void setBill(TenantBill bill) {
        this.bill = bill;
    }

    public void resetAll() {
        this.setReportingBills(bmsb.getRecentMonthResBills(Calendar.getInstance().getTime()));
        this.setUnpaidBills(bmsb.getAllUnpaidResBills());
        this.setAllBills(bmsb.getAllResBills());
        this.setAllreceipts(bmsb.getAllResReceipts());
    }

    /**
     * @return the unpaidBills
     */
    public List<TenantBill> getUnpaidBills() {
        if (unpaidBills.isEmpty()) {
            this.setUnpaidBills(bmsb.getAllUnpaidResBills());
        }
        return unpaidBills;
    }

    /**
     * @param unpaidBills the unpaidBills to set
     */
    public void setUnpaidBills(List<TenantBill> unpaidBills) {
        this.unpaidBills = unpaidBills;
    }

    /**
     * @return the allBills
     */
    public List<TenantBill> getAllBills() {
        if (allBills.isEmpty()) {
            this.setAllBills(bmsb.getAllResBills());
        }
        return allBills;
    }

    /**
     * @param allBills the allBills to set
     */
    public void setAllBills(List<TenantBill> allBills) {
        this.allBills = allBills;
    }

    /**
     * @return the allreceipts
     */
    public List<TenantReceipt> getAllreceipts() {
        if (allreceipts.isEmpty()) {
            this.setAllreceipts(bmsb.getAllResReceipts());
        }
        return allreceipts;
    }

    /**
     * @param allreceipts the allreceipts to set
     */
    public void setAllreceipts(List<TenantReceipt> allreceipts) {
        this.allreceipts = allreceipts;
    }

    /**
     * @return the receipt
     */
    public TenantReceipt getReceipt() {
        return receipt;
    }

    /**
     * @param receipt the receipt to set
     */
    public void setReceipt(TenantReceipt receipt) {
        this.receipt = receipt;
    }

    /**
     * @return the historyMonthlyAdhoc
     */
    public List<AdhocEvent> getHistoryMonthlyAdhoc() {
        return historyMonthlyAdhoc;
    }

    /**
     * @param historyMonthlyAdhoc the historyMonthlyAdhoc to set
     */
    public void setHistoryMonthlyAdhoc(List<AdhocEvent> historyMonthlyAdhoc) {
        this.historyMonthlyAdhoc = historyMonthlyAdhoc;
    }

    /**
     * @return the badDebtBills
     */
    public List<TenantBill> getBadDebtBills() {
        if (badDebtBills.isEmpty()) {
            this.setBadDebtBills(bmsb.getBadDebtResBills());
        }
        return badDebtBills;
    }

    /**
     * @param badDebtBills the badDebtBills to set
     */
    public void setBadDebtBills(List<TenantBill> badDebtBills) {
        this.badDebtBills = badDebtBills;
    }
}
