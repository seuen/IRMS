/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingMall.session;

import ShoppingMall.entity.AdhocEvent;
import ShoppingMall.entity.Contract;
import ShoppingMall.entity.DetailShopOrder;
import ShoppingMall.entity.RentalReports;
import ShoppingMall.entity.Shop;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Cindylulu
 */
@Stateless(name="ChargesManagementSessionBean")
public class ChargesManagementSessionBean implements ChargesManagementSessionBeanLocal,ChargesManagementSessionBeanRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager entityManager;
    @EJB
    ShopRevenueManagementSessionBeanLocal srmsbl;

    @Override
    public Shop getShop(Long shopId) {
        try {
        Shop shop = entityManager.find(Shop.class, shopId);
        return shop;
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public boolean mergeShop(Shop shop) {
        try {
            entityManager.merge(shop);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<AdhocEvent> getAllAdhocEvents() {
        try {
        Query query = entityManager.createQuery("SELECT e FROM AdhocEvent e");
        return query.getResultList();
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<AdhocEvent> getAllResAdhocEvents() {
        try {
        Query query = entityManager.createQuery("SELECT e FROM AdhocEvent e WHERE e.shop.shopType='Restaurant'");
        return query.getResultList();
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<AdhocEvent> getMyAdHocEvents(Long shopId) {
        try {
        Shop shop = getShop(shopId);
        Query query = entityManager.createQuery("SELECT e FROM AdhocEvent e WHERE e.shop = :inShop");
        query.setParameter("inShop", shop);
        return query.getResultList();
        } catch(Exception ex ) {
            return null;
        }
    }

    @Override
    public float computeAdhocCharges(List<AdhocEvent> events) {
        try {
        float total = 0;
        for (Object o : events) {
            AdhocEvent a = (AdhocEvent) o;
            total += a.getCharge();
            System.err.println("computeAdhocCharges: " + total);
        }
        return total;
        } catch(Exception ex) {
            return 0;
        }
    }

    @Override
    public Long addAdhocEvents(String eventType, float charge, String description, Date eventDate, Long shopId) {
        try {
            System.err.println("addAdhocEvents");
        
        Shop shop = getShop(shopId);
        AdhocEvent adhocEvent = new AdhocEvent();
        adhocEvent.setEventType(eventType);
        adhocEvent.setCharge(charge);
        adhocEvent.setDescription(description);
        adhocEvent.setEnterDate(Calendar.getInstance().getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(eventDate);
        adhocEvent.setEventDate(c.getTime());
        adhocEvent.setShop(shop);
        entityManager.persist(adhocEvent);
        entityManager.flush();
        shop.getAdhocEvents().add(adhocEvent);
        entityManager.merge(shop);
        return adhocEvent.getId();
        } catch(Exception ex) {
            return null;
        }
    }

    // Auto at last day of each month 23:00
    @Override
    public boolean createRentalReports() {
        try {
            Query q = entityManager.createQuery("SELECT c FROM Contract c WHERE c.activated='Activated'");
            List<Contract> contracts = q.getResultList();
            for (Contract o : contracts) {
                Contract activeC = entityManager.find(Contract.class, o.getId());
                RentalReports r = new RentalReports();
                System.out.println("generating reports");
                // Name set based on shopId and time
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                r.setName(o.getShop().getShopId() * 1000000 + year * 100 + month + 1);
                r.setGenerationDate(cal.getTime());
                // Set according to contract
                Date endD = cal.getTime();
                cal.set(Calendar.DATE, 1);
                Date startD = cal.getTime();
                List<DetailShopOrder> list = srmsbl.getDetailShopOrders(o.getShop().getShopId(), startD, endD);
                r.setTotalSales(srmsbl.calculateTotalRevenue(list));
                r.setCommissionRate(o.getCommissionRate());
                r.setCommissionFee(r.getCommissionRate() * r.getTotalSales());

                r.setAdhocCharges(this.computeAdhocCharges(this.getMyMonthlyAdhocEvents(activeC.getShop().getShopId(), Calendar.getInstance().getTime())));

                // Set rental as firstMonth rental or lastMonth rental or normal rental, as well as set deposit if first month
                Calendar start = Calendar.getInstance();
                start.setTime(o.getLeaseDateFrom());
                int startYear = start.get(Calendar.YEAR);
                int startMonth = start.get(Calendar.MONTH);
                Calendar end = Calendar.getInstance();
                end.setTime(o.getLeaseDateTo());
                int endYear = end.get(Calendar.YEAR);
                int endMonth = end.get(Calendar.MONTH);
                if (year == startYear && month == startMonth) {
                    r.setDeposit(o.getDeposit());
                    r.setMonthlyRental(o.getFirstMonthRental());
                } else if (year == endYear && month == endMonth) {
                    r.setDeposit(0);
                    r.setMonthlyRental(o.getLastMonthRental());
                } else {
                    r.setDeposit(0);
                    r.setMonthlyRental(o.getMonthlyRental());
                }

                // Calculate and set monthly total
                r.setTotalCharges(r.getDeposit() + r.getAdhocCharges() + r.getCommissionFee() + r.getMonthlyRental());

                entityManager.persist(r);

                // Set up relationship
                r.setContract(o);
                activeC.getRentalReports().add(r);
                entityManager.flush();
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public RentalReports getRentalReports(Long rID) {
        try {
        return entityManager.find(RentalReports.class, rID);
        } catch(Exception ex) {
            return null;
        }
    }

    // Could be called any time, just to calculate CURRENT month's monthly adhoc events dynamically
    @Override
    public List<AdhocEvent> getMyMonthlyAdhocEvents(Long shopId, Date date) {
        try {
        System.err.println("getMyMonthlyAdhocEvents");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // Set date2 as first day of that month
        c.set(Calendar.DATE, 1);
        Date date2 = c.getTime();
        List<AdhocEvent> myadhoc = this.getMyAdHocEvents(shopId);
        List<AdhocEvent> monthlyAdhoc = new ArrayList();
        System.out.println("" + date2);
        System.out.println("" + date);
        for (Object o : myadhoc) {
            AdhocEvent a = (AdhocEvent) o;
            if ((a.getEventDate().before(date) || a.getEventDate().compareTo(date) == 0) && (a.getEventDate().after(date2)) || a.getEventDate().compareTo(date2) == 0) {
                monthlyAdhoc.add(a);
            }
        }
        return monthlyAdhoc;
        } catch(Exception ex) {
            return null;
        }
    }
}
