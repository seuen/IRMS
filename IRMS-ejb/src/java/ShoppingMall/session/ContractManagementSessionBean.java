/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingMall.session;

import ShoppingMall.entity.Contract;
import ShoppingMall.entity.Negotiator;
import ShoppingMall.entity.Shop;
import ShoppingMall.entity.TenantVenue;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.session.EmailManager2;

/**
 *
 * @author Cindylulu
 */
@Stateless(name="ContractManagementSessionBean")
public class ContractManagementSessionBean implements ContractManagementSessionBeanLocal,ContractManagementSessionBeanRemote {

    @PersistenceContext
    private EntityManager entityManager;
    @EJB
    public ShopVenueManagementSessionBeanLocal svmsb;

    @Override
    public Contract createContract(String lessee, Date leaseDateFrom, int leaseTerm, float monthlyRental, float commissionRate, float deposit, List<TenantVenue> venues, String negoId, float baselineRental) {
        
        try {
        String venue = svmsb.getVenueString(venues);
        Negotiator nr = entityManager.find(Negotiator.class, negoId);

        Contract contract = new Contract();

        contract.setLessor("Coral Island Resort Shopping Mall (CIRSM)");
        contract.setLessee(lessee);
        contract.setContractDate(Calendar.getInstance().getTime());
        contract.setLeaseDateFrom(leaseDateFrom);
        contract.setLeaseterm(leaseTerm);
        contract.setBaselineRental(baselineRental);

        Calendar calt = Calendar.getInstance();
        calt.setTime(contract.getLeaseDateFrom());
        calt.add(Calendar.YEAR, leaseTerm);
        contract.setLeaseDateTo(calt.getTime());

        Calendar cal = Calendar.getInstance();
        cal.setTime(contract.getContractDate());
        contract.setcDate(cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE));
        cal.setTime(contract.getLeaseDateFrom());
        contract.setDateFrom(cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE));
        cal.setTime(contract.getLeaseDateTo());
        contract.setDateTo(cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE));

        contract.setMonthlyRental(monthlyRental);
        contract.setTotalRental(monthlyRental * leaseTerm * 12);

        //Calculate first month rental and last month rental       
        int from = cal.get(Calendar.DATE);
        contract.setFirstMonthRental((31 - from) * (monthlyRental) / 30);

        int to = cal.get(Calendar.DATE);
        contract.setLastMonthRental(to * (monthlyRental) / 30);

        contract.setCommissionRate(commissionRate);
        contract.setDeposit(deposit);
        contract.setVenue(venue);

        Iterator<TenantVenue> v = venues.iterator();
        while (v.hasNext()) {
            TenantVenue temp = v.next();
            List<Negotiator> ns = (List<Negotiator>) temp.getNegotiators();
            for (Negotiator n : ns) {
                n.getVenues().remove(temp);
                entityManager.merge(n);
            }
            temp.setNegotiators(null);
            entityManager.merge(temp);
        }
        entityManager.persist(contract);
        entityManager.merge(contract);
        return contract;
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public Contract renewContract(Date leaseDateFrom, int leaseTerm, float monthlyRental, float commissionRate, float deposit, float baselineRental, Shop shop) {
        try {
        String venue = svmsb.getVenueString((List<TenantVenue>) shop.getCurrentVenues());
        Contract contract = new Contract();
        contract.setLessor("Coral Island Resort Shopping Mall (CIRSM)");
        contract.setLessee(shop.getShopName() + "-Manager:" + shop.getShopOwnerName());
        contract.setContractDate(Calendar.getInstance().getTime());
        contract.setLeaseDateFrom(leaseDateFrom);
        contract.setLeaseterm(leaseTerm);
        contract.setBaselineRental(baselineRental);

        Calendar calt = Calendar.getInstance();
        calt.setTime(leaseDateFrom);
        calt.add(Calendar.YEAR, leaseTerm);
        contract.setLeaseDateTo(calt.getTime());
        Date leaseDateTo = calt.getTime();
        Calendar cal = Calendar.getInstance();
        contract.setcDate(cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE));
        cal.setTime(leaseDateFrom);
        contract.setDateFrom(cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE));
        cal.setTime(leaseDateTo);
        contract.setDateTo(cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE));

        contract.setMonthlyRental(monthlyRental);
        contract.setTotalRental(monthlyRental * leaseTerm * 12);

        int from = cal.get(Calendar.DATE);
        contract.setFirstMonthRental((31 - from) * (monthlyRental) / 30);

        int to = cal.get(Calendar.DATE);
        contract.setLastMonthRental(to * (monthlyRental) / 30);

        contract.setCommissionRate(commissionRate);
        contract.setDeposit(deposit);
        contract.setVenue(venue);

        Iterator<TenantVenue> v = shop.getCurrentVenues().iterator();
        while (v.hasNext()) {
            TenantVenue temp = v.next();
            List<Negotiator> ns = (List<Negotiator>) temp.getNegotiators();
            for (Negotiator n : ns) {
                n.getVenues().remove(temp);
                entityManager.merge(n);
            }
            temp.setNegotiators(null);
            entityManager.merge(temp);
        }
        contract.setShop(shop);
        entityManager.persist(contract);
        entityManager.merge(contract);
        shop.getContracts().add(contract);
        entityManager.merge(shop);
        return contract;
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public boolean attachContractShop(Contract con, Shop s) {
        try {
            con.setShop(s);
            s.getContracts().add(con);
            entityManager.merge(s);
            entityManager.merge(con);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Contract updateContract(Contract contract) {
        try {
        contract.setContractDate(Calendar.getInstance().getTime());
        Calendar calt = Calendar.getInstance();
        calt.setTime(contract.getLeaseDateFrom());
        calt.add(Calendar.YEAR, contract.getLeaseterm());
        contract.setLeaseDateTo(calt.getTime());

        Calendar cal = Calendar.getInstance();
        cal.setTime(contract.getContractDate());
        contract.setcDate(cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE));
        cal.setTime(contract.getLeaseDateFrom());
        contract.setDateFrom(cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE));
        cal.setTime(contract.getLeaseDateTo());
        contract.setDateTo(cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE));

        contract.setTotalRental((contract.getMonthlyRental() * 12 * contract.getLeaseterm()));
        entityManager.merge(contract);
        entityManager.flush();
        return contract;
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public String deleteContract(Long contractId) {
        try {
        Contract b = entityManager.find(Contract.class, contractId);
        if (b == null) {
            return "Alert: Cannot delete contract because no such conctract.";
        } else if (!b.getActivated().equalsIgnoreCase("Inactivated")) {
            return "Alert: Cannot delete contract because it is activated or archived(expired).";
        } else {
            System.err.println("Num of shop contracts: " + b.getShop().getContracts().size());
            Shop s = b.getShop();
            s.getContracts().remove(b);
            b.setShop(null);

            List<TenantVenue> venueList = (List<TenantVenue>) s.getHistoryVenues();

            for (int i = 0; i < venueList.size(); i++) {
                TenantVenue temp = venueList.get(i);
                temp.getShops().remove(s);
                temp.setNegoAvailability("Available");
                entityManager.merge(temp);
            }
            s.setHistoryVenues(null);
            entityManager.merge(s);
            entityManager.flush();
//            Collection <Venue> vl = (Collection <Venue>) s.getVenues();
//            Iterator<Venue> v = vl.iterator();
//            while(v.hasNext()){
////                smsb.detachShop(v.next().getId(), s.getShopId());
//                TenantVenue temp = v.next();
//                temp.getShops().remove(s);
//                s.getVenues().remove(temp);
//            }
            entityManager.remove(b);

            entityManager.flush();
            System.out.println("Num of shop contracts2: " + s.getContracts().size());
            return "Delete successfully";
        }
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public Contract getContract(Long contractId) {
        try {
        Contract c = entityManager.find(Contract.class, contractId);
        return c;
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<Contract> getAllContracts() {
        try {
        List<Contract> l = new ArrayList();
        Query q = entityManager.createQuery("SELECT c FROM Contract c WHERE c.shop.shopType!='Restaurant'");
        return q.getResultList();
        } catch(Exception ex) {
            return null;
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<Contract> getAllResContracts() {
        try {
        List<Contract> l = new ArrayList();
        Query q = entityManager.createQuery("SELECT c FROM Contract c WHERE c.shop.shopType='Restaurant'");
        return q.getResultList();
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public boolean setStatusContracts() {
        try {
            List<Contract> l = getAllContracts();
            Date current = Calendar.getInstance().getTime();
            System.out.println("setStatusContracts");
            for (Contract O : l) {
                Contract o = entityManager.find(Contract.class, O.getId());
                if ((current.after(o.getLeaseDateFrom()) || current.equals(o.getLeaseDateFrom())) && (current.before(o.getLeaseDateTo()) || current.equals(o.getLeaseDateTo()))) {
                    if (o.getShop().getContractStatus().equalsIgnoreCase("InActive")) {
                        o.setActivated("Activated");
                        o.getShop().setContractStatus("Active");
                        List<TenantVenue> tvs = (List<TenantVenue>) o.getShop().getHistoryVenues();
                        for (TenantVenue tv : tvs) {
                            tv.setStatus("Occupied");
                            o.getShop().getCurrentVenues().add(tv);
                            tv.setOccupiedShopId(o.getShop().getShopId());
                            tv.setShop(o.getShop());
                            tv.getShops().remove(o.getShop());
                            entityManager.merge(tv);
                        }
                        o.getShop().setHistoryVenues(null);
                    }
//                System.out.println(o.getActivated());
                    entityManager.merge(o);
                    entityManager.merge(o.getShop());
                } else if (current.after(o.getLeaseDateTo())) {
                    if (o.getActivated().equalsIgnoreCase("Activated")) {
                        o.setActivated("Expired");
                        o.getShop().setContractStatus("Expired");
                        List<TenantVenue> tvs = (List<TenantVenue>) o.getShop().getCurrentVenues();
                        for (TenantVenue tv : tvs) {
                            tv.setStatus("Empty");
                            o.getShop().getHistoryVenues().add(tv);
                            tv.getShops().add(o.getShop());
                            tv.setOccupiedShopId(null);
                            tv.setShop(null);
                            entityManager.merge(tv);
                        }
                        o.getShop().setCurrentVenues(null);
//                    System.out.println(o.getActivated());
                        entityManager.merge(o);
                    }
                }
            }
            entityManager.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<Contract> getAllActiveContracts() {
        try {
        List<Contract> l = this.getAllContracts();
        List<Contract> l2 = new ArrayList();
        for (Contract o : l) {
            if (o.getActivated().equalsIgnoreCase("Activated")) {
                l2.add(o);
            }
        }
        return l2;
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<Contract> getAllActiveResContracts() {
        try {
        List<Contract> l = this.getAllResContracts();
        List<Contract> l2 = new ArrayList();
        for (Contract o : l) {
            if (o.getActivated().equalsIgnoreCase("Activated")) {
                l2.add(o);
            }
        }
        return l2;
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<Contract> getAllInActiveContracts() {
        try {
        List<Contract> l = this.getAllContracts();
        List<Contract> l2 = new ArrayList();
        for (Contract o : l) {
            if (o.getActivated().equalsIgnoreCase("InActivated")) {
                l2.add(o);
            }
        }
        return l2;
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<Contract> getAllInactiveResContracts() {
        try {
        List<Contract> l = this.getAllResContracts();
        List<Contract> l2 = new ArrayList();
        for (Contract o : l) {
            if (o.getActivated().equalsIgnoreCase("InActivated")) {
                l2.add(o);
            }
        }
        return l2;
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<Contract> getMyContracts(Long shopId) {
        try {
        Shop s = entityManager.find(Shop.class, shopId);
        List<Contract> l = (List<Contract>) s.getContracts();
        return l;
        } catch(Exception ex ) {
            return null;
        }
    }

    @Override
    public boolean contractLapseAndRenewRemind() {
        try {
            Date curr = Calendar.getInstance().getTime();
            EmailManager2 emg = new EmailManager2();
            List<Contract> lcon = entityManager.createQuery("SELECT c FROM Contract c WHERE c.activated='Activated'").getResultList();
            for (Contract c : lcon) {
                if ((c.getLeaseDateTo().getTime() - curr.getTime()) / (3600.0 * 1000.0 * 24.0) == 30) {
                    emg.sendLapseReminders(c);
                } else if ((c.getLeaseDateTo().getTime() - curr.getTime()) / (3600.0 * 1000.0 * 24.0) == 210) {
                    emg.sendRenewalReminders(c);
                }
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
