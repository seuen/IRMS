/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingMall.session;

import ShoppingMall.entity.Contract;
import ShoppingMall.entity.RentalReports;
import ShoppingMall.entity.TenantBill;
import ShoppingMall.entity.TenantReceipt;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.session.EmailManager2;

/**
 *
 * @author Cindylulu
 */
@Stateless(name="BillingManagementSessionBean")
public class BillingManagementSessionBean implements BillingManagementSessionBeanLocal, BillingManagementSessionBeanRemote{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TenantBill> createBills() {
        try {
        List<TenantBill> t = new ArrayList();
        Query q = entityManager.createQuery("SELECT c FROM Contract c WHERE c.activated='Activated' AND c.rentalReports IS NOT EMPTY");
        List<Contract> cl = q.getResultList();
        for (Contract o : cl) {
            List<RentalReports> rl = (List<RentalReports>) o.getRentalReports();
            RentalReports r = rl.get(rl.size() - 1);

            // Create A Bill for each RentalReports Instance r
            TenantBill b = new TenantBill();
            b.setAdhocCharges(r.getAdhocCharges());
            b.setBillDate(Calendar.getInstance().getTime());
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH) + 1;
            int date = c.get(Calendar.DATE);
            b.setBillDateString(year + "/" + month + "/" + date);
            b.setCommissionCharges(r.getCommissionFee());
            b.setCommissionRate(r.getCommissionRate());
            c.add(Calendar.DATE, 10);
            b.setDeadLine(c.getTime());
            b.setDepost(r.getDeposit());
            b.setPayer(r.getContract().getLessee());
            b.setReceiver(r.getContract().getLessor());
            b.setRentalCharges(r.getMonthlyRental());
            b.setStatus("Unpaid");
            b.setTotalCharges(r.getTotalCharges());
            b.setTotalSales(r.getTotalSales());
            entityManager.persist(b);
            b.setRentalReports(r);
            r.setTenantBill(b);
            t.add(b);
            entityManager.flush();
        }
            return t;
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public boolean checkBadDebt() {
        try {
            Query q = entityManager.createQuery("SELECT b FROM TenantBill b WHERE b.status='Unpaid'");
            List<TenantBill> bills = q.getResultList();
            for (TenantBill bill : bills) {
                TenantBill B = entityManager.find(TenantBill.class, bill.getId());
                if (B.getDeadLine().compareTo(Calendar.getInstance().getTime()) <= 0) {
                    B.setStatus("BadDebt");
                }
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean sendMonthlyBills() {
        try {
            EmailManager2 emg = new EmailManager2();
            List<TenantBill> tb = this.createBills();
            for (TenantBill b : tb) {
                System.err.println("sending email for bill " + b.getRentalReports().getName());
                emg.sendBill(b);
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public TenantBill getBill(Long billid) {
        try {
        return entityManager.find(TenantBill.class, billid);
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public TenantBill editBill(Long billid, Date deadline, String status) {
        try {
        TenantBill bill = this.getBill(billid);
        bill.setDeadLine(deadline);
        bill.setStatus(status); // IMPORTANT: Dropdown list in jsf: only two options: paid or unpaid, baddebt is set by system, not staff
        return bill;
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public String deleteBill(Long billid) {
        try {
        Date day = Calendar.getInstance().getTime();
        TenantBill b = entityManager.find(TenantBill.class, billid);
        if (b == null) {
            return "Cannot delete the bill because no such bill exists.";
        } else if ((int) ((day.getTime() - b.getBillDate().getTime()) / (1000 * 60 * 60 * 24)) < 32) {
            return "Cannot delete the bill because it is active in the current month.";
        } else {
            b.setTenantReceipt(null);
            RentalReports r = b.getRentalReports();
            r.setTenantBill(null);
            b.setRentalReports(null);
            entityManager.remove(b);
            entityManager.flush();
            return "Delete bill successfully!";
        }
        } catch(Exception ex) {
            return "";
        }
    }

    @Override
    public List<TenantBill> getRecentMonthBills(Date day) {
        try {
        Query q = entityManager.createQuery("SELECT b FROM TenantBill b WHERE b.rentalReports.contract.shop.shopType<>'Restaurant'");
        List<TenantBill> tl = q.getResultList();
        List<TenantBill> reporting = new ArrayList();
        for (TenantBill t : tl) {
            if ((int) ((day.getTime() - t.getBillDate().getTime()) / (1000 * 60 * 60 * 24)) < 32) {
                reporting.add(t);
            }
        }
        System.err.println("getRecentMonthBills" + reporting.size());
        return reporting;
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<TenantBill> getRecentMonthResBills(Date day) {
        try {
        Query q = entityManager.createQuery("SELECT b FROM TenantBill b WHERE b.rentalReports.contract.shop.shopType='Restaurant'");
        List<TenantBill> tl = q.getResultList();
        List<TenantBill> reporting = new ArrayList();
        for (TenantBill t : tl) {
            if ((int) ((day.getTime() - t.getBillDate().getTime()) / (1000 * 60 * 60 * 24)) < 32) {
                reporting.add(t);
            }
        }
        System.err.println("getRecentMonthResBills" + reporting.size());
        return reporting;
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<TenantBill> getAllBills() {
        try {
        Query q = entityManager.createQuery("SELECT b FROM TenantBill b WHERE b.rentalReports.contract.shop.shopType<>'Restaurant'");
        return q.getResultList();
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<TenantBill> getAllResBills() {
        try {
        Query q = entityManager.createQuery("SELECT b FROM TenantBill b WHERE b.rentalReports.contract.shop.shopType='Restaurant'");
        return q.getResultList();
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<TenantBill> getAllUnpaidBills() {
        try {
        Query q = entityManager.createQuery("SELECT b FROM TenantBill b WHERE b.status<>'Paid' AND b.rentalReports.contract.shop.shopType<>'Restaurant'");
        return q.getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<TenantBill> getAllUnpaidResBills() {
        try {
        Query q = entityManager.createQuery("SELECT b FROM TenantBill b WHERE b.status<>'Paid' AND b.rentalReports.contract.shop.shopType='Restaurant'");
        return q.getResultList();
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<TenantBill> getBadDebtBills() {
        try {
        Query q = entityManager.createQuery("SELECT b FROM TenantBill b WHERE b.status='BadDebt' AND b.rentalReports.contract.shop.shopType<>'Restaurant'");
        return q.getResultList();
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<TenantBill> getBadDebtResBills() {
        try {
        Query q = entityManager.createQuery("SELECT b FROM TenantBill b WHERE b.status='BadDebt' AND b.rentalReports.contract.shop.shopType='Restaurant'");
        return q.getResultList();
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public TenantReceipt createReceipt(TenantBill bill) {
        try {
        TenantReceipt r = null;
        if (bill.getTenantReceipt() == null) {
            r = new TenantReceipt();
            r.setAdhocPayment(bill.getAdhocCharges());
            r.setCommissionPayment(bill.getCommissionCharges());
            r.setDepositPayment(bill.getDepost());
            r.setPayer(bill.getPayer());
            r.setReceiptDate(Calendar.getInstance().getTime());
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH) + 1;
            int date = c.get(Calendar.DATE);
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            int second = c.get(Calendar.SECOND);
            r.setReceiptDateString(year + "/" + month + "/" + date + "-" + hour + ":" + minute + ":" + second);
            r.setReceiver(bill.getReceiver());
            r.setRentalPayment(bill.getRentalCharges());
            r.setTotalPayment(bill.getTotalCharges());
            entityManager.persist(r);
            r.setTenantBill(bill);
            TenantBill b = entityManager.find(TenantBill.class, bill.getId());
            b.setTenantReceipt(r);
            entityManager.flush();
        }
        return r;
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<TenantReceipt> getAllReceipts() {
        try {
        Query q = entityManager.createQuery("SELECT r FROM TenantReceipt r WHERE r.tenantBill.rentalReports.contract.shop.shopType<>'Restaurant'");
        return q.getResultList();
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<TenantReceipt> getAllResReceipts() {
        try {
        Query q = entityManager.createQuery("SELECT r FROM TenantReceipt r WHERE r.tenantBill.rentalReports.contract.shop.shopType='Restaurant'");
        return q.getResultList();
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public TenantReceipt getReceipt(Long receiptId) {
        try {
        return entityManager.find(TenantReceipt.class, receiptId);
        } catch(Exception ex) {
            return null;
        }
    }
}
