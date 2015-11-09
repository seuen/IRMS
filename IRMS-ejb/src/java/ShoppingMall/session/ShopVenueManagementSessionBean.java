/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor
 */
package ShoppingMall.session;

import Common.entity.Department;
import Common.entity.Title;
import FoodBeverage.entity.Restaurant;
import ShoppingMall.entity.Contract;
import ShoppingMall.entity.Negotiator;
import ShoppingMall.entity.Shop;
import ShoppingMall.entity.TenantVenue;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Cindylulu
 */
@Stateless (name="ShopVenueManagementSessionBean")
public class ShopVenueManagementSessionBean implements ShopVenueManagementSessionBeanLocal,ShopVenueManagementSessionBeanRemote {

    @PersistenceContext
    EntityManager em;

    @Override
    public TenantVenue createVenue(TenantVenue venue) {
        try {
        System.err.println("SessionBean: create venue");
        Query query = em.createQuery("SELECT v FROM TenantVenue v WHERE v.building=:inVenue ORDER BY v.id");
        query.setParameter("inVenue", venue.getBuilding());
        //System.err.println("getAllVenues " + query.getResultList().size());
        int counter = 1;
        for (Object o : query.getResultList()) {
            TenantVenue theVenue = (TenantVenue) o;
            if (theVenue.getFloor().equals(venue.getFloor())) {
                counter = theVenue.getCounter() + 1;
            }
        }
        //System.err.println("finished loop");
        //System.err.println("Counter: " + counter);
        String venueNumber = Integer.toString(counter);
        //System.err.println(venueNumber);
        if (counter / 100 == 0) {
            venueNumber = "0" + venueNumber;
        }
        if (counter / 10 == 0) {
            venueNumber = "0" + venueNumber;
        }

        String id = venue.getFloor() + "-" + venueNumber;
        venue.setId(id);
        venue.setCounter(counter);
        em.persist(venue);
        em.flush();
        return venue;
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<TenantVenue> getAllVenues() {
        try {
        Query query = em.createQuery("SELECT v FROM TenantVenue v WHERE v.building='Shopping Mall' ORDER BY v.id");
        return query.getResultList();
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public TenantVenue getVenue(Long venueId) {
        try {
        return em.find(TenantVenue.class, venueId);
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public boolean updateVenue(TenantVenue venue) {
        try {
            em.merge(venue);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean deleteVenue(Long venueId) {
        try {
        TenantVenue vn = em.find(TenantVenue.class, venueId);
        List<Shop> sps = (List<Shop>) vn.getShops();
        boolean deletable = true;
        // If there are negotiations going on with the venue, then the venue cannot be deleted
        if (!(vn.getNegotiators().isEmpty()) || !(vn.getShop() == null) || vn.getNegoAvailability().compareToIgnoreCase("N.A.") == 0) {
            deletable = false;
        }
        if (deletable == true) {
            em.remove(vn);
            em.flush();
        } else {
            return false;
        }
        return true;
        } catch(Exception ex) {
            return false;
        }
    }

    @Override
    public List<Negotiator> getNegotiators(Long venueId) {
        try {
        return (List<Negotiator>) em.find(TenantVenue.class, venueId).getNegotiators();
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<Shop> getShops(Long venueId) {
//        return null;
        try  {
        return (List<Shop>) em.find(TenantVenue.class, venueId).getShops();
        }catch(Exception ex) {
            return null;
        }
    }

    @Override
    public Shop getActiveShop(Long venueId) {
        try {
        Long shopId = em.find(TenantVenue.class, venueId).getOccupiedShopId();
        return em.find(Shop.class, shopId);
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<TenantVenue> getAvailableVenues() {
        try {
        Query query = em.createQuery("SELECT v FROM TenantVenue v WHERE v.building='Shopping Mall' ORDER BY v.id");
        List<TenantVenue> tvs = query.getResultList();
        List<TenantVenue> avs = new ArrayList();
        for (TenantVenue tv : tvs) {
            if (checkNegoAvailability(tv.getRealID())) {
                avs.add(tv);
            }
        }
        return avs;
        } catch (Exception ex) {
            return null;
        }
    }

    // This function returns true if no contracts or all the contracts with a shop and the venue having leaseDateTo within 180 days later of today
    // else returns false (as long as there is an active or inactive contract NOT expiring in six month).
    private boolean checkNegoAvailability(Long venueId) {
        try {
        TenantVenue vn = em.find(TenantVenue.class, venueId);
        List<Shop> sps = (List<Shop>) vn.getShops();
        boolean negoA = true;
        for (Shop sp : sps) {
            List<Contract> cts = (List<Contract>) sp.getContracts();
            for (Contract ct : cts) {
                if (ct.getVenue().contains(vn.getId()) && (ct.getLeaseDateTo().getTime() - Calendar.getInstance().getTime().getTime()) / (1000 * 3600 * 24) > 180) {
                    negoA = false;
                }
            }
        }
        return negoA;
        } catch(Exception ex) {
            return false;
        }
    }

    @Override
    public Negotiator createNegotiator(Negotiator nego) {
        try {
        em.persist(nego);
        return nego;
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public boolean createNegotiation(Negotiator nego, TenantVenue venue) {
        try {
            nego.getVenues().add(venue);
            venue.getNegotiators().add(nego);
            em.merge(venue);
            em.merge(nego);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean deleteNegotiation(Negotiator nego, TenantVenue venue) {
        try {
            nego.getVenues().remove(venue);
            venue.getNegotiators().remove(nego);
            em.merge(venue);
            em.merge(nego);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Shop createShop(Negotiator negotiator) {
        try {
        Shop s = new Shop();
        s.setCategory(negotiator.getShopCategory());
        s.setEmail(negotiator.getEmail());
        s.setShopName(negotiator.getShopName());
        s.setShopType(negotiator.getShopType());
        s.setShopOwnerName(negotiator.getNegotiatorName());
        s.setTelNum(negotiator.getTelNum());
        s.setContractStatus("Inactive");
        em.persist(s);
//        System.out.println("inside shop creation session shopID: " + s.getShopId());
        // Register shop to common infra
        return s;
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public Restaurant createRes(Negotiator negotiator) {
        try {
        Restaurant res = new Restaurant();
        res.setCategory(negotiator.getShopCategory());
        res.setEmail(negotiator.getEmail());
        res.setShopName(negotiator.getShopName());
        res.setShopType(negotiator.getShopType());
        res.setShopOwnerName(negotiator.getNegotiatorName());
        res.setTelNum(negotiator.getTelNum());
        res.setContractStatus("Inactive");
        em.persist(res);
        // Register shop to common infra
        return res;
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public boolean attach(Shop shop, TenantVenue tv) {
        try {
            int area = 0;
//        System.out.println("shopID attach: " + shop.getShopId());
//        System.out.println("venueID attach: " + tv.getRealID());
//
//        System.out.println("after add venue");
            area += tv.getArea();
            shop.setArea(area);
//        System.out.println("after set area");
//
//        System.out.println("before merge shop");
            shop.getHistoryVenues().add(tv);
//        System.out.println("after merge shop");
            tv.getShops().add(shop);
//        System.out.println("after set shop");
            tv.setNegoAvailability("N.A.");
            em.merge(tv);
            em.merge(shop);
//        System.out.println("after merge venue");
            em.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Shop getShop(Long shopId) {
        try {
        return em.find(Shop.class, shopId);
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public Restaurant getRestaurant(Long resId) {
        try {
        return em.find(Restaurant.class, resId);
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<Negotiator> getNegotiators() {
        try {
        Query query = em.createQuery("SELECT n FROM Negotiator n");
        return query.getResultList();
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<Negotiator> getResNegotiators() {
        try {
        Query query = em.createQuery("SELECT n FROM Negotiator n WHERE n.shopType='Restaurant'");
        return query.getResultList();
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<Negotiator> getShopNegotiators() {
        try {
        Query query = em.createQuery("SELECT n FROM Negotiator n WHERE n.shopType<>'Restaurant'");
        return query.getResultList();
        }catch(Exception ex) {
            return null;
        }
    }

    @Override
    public String getVenueString(List<TenantVenue> venues) {
        try {
        String venueString = "";
        for (TenantVenue venue : venues) {
            String concat = venueString.concat("ID: " + venue.getId() + " (Building " + venue.getBuilding() + " Floor " + venue.getFloor().toUpperCase() + ", Sector " + venue.getSector().toUpperCase() + ")   ");
            venueString = concat;
        }
        return venueString;
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<TenantVenue> getLevelVenues(String level) {
        try {
        Query query = em.createQuery("SELECT v FROM TenantVenue v WHERE v.floor=:level");
        query.setParameter("level", level);
        return query.getResultList();
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<Shop> getAllShops() {
        try {
        Query query = em.createQuery("SELECT s FROM Shop s WHERE s.shopType!='Restaurant' ");
        return query.getResultList();
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        try {
        Query query = em.createQuery("SELECT s FROM Shop s WHERE s.shopType='Restaurant'");
        return query.getResultList();
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<Shop> getAllHistoryShops() {
        try {
        Query query = em.createQuery("SELECT s FROM Shop s WHERE s.shopType!='Restaurant' AND s.contractStatus='Expired'");
        return query.getResultList();
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<Shop> getAllActiveShops() {
        try {
        Query query = em.createQuery("SELECT s FROM Shop s WHERE s.shopType!='Restaurant' AND s.contractStatus='Active'");
        return query.getResultList();
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public boolean deleteNegotiator(String negoId) {
        try {
            Negotiator n = em.find(Negotiator.class, negoId);
            List<TenantVenue> tv = (List<TenantVenue>) n.getVenues();
            for (TenantVenue v : tv) {
                v.getNegotiators().remove(n);
                n.getVenues().remove(v);
                em.flush();
            }
            em.remove(n);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Negotiator getNegotiator(String negoId) {
        try {
        return em.find(Negotiator.class, negoId);
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public boolean updateNegotiator(Negotiator n) {
        try {
            em.merge(n);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean detach(Shop shop, TenantVenue v) {
        try {
            shop.getCurrentVenues().remove(v);
            shop.getHistoryVenues().add(v);
            v.setShop(null);
            v.getShops().add(shop);
            em.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean updateShop(Shop shop) {
        try {
            em.merge(shop);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean updateRestaurant(Restaurant res) {
        try {
            em.merge(res);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean registerShop(Shop s) {
        try {
            if (!s.getShopType().equalsIgnoreCase("Restaurant")) {
                String subsystem = "ShoppingMallGeneral";
                String departmentName = "Tenants Management";
                Query query = em.createQuery("SELECT d FROM Department d WHERE d.subSystem = :inSubSystem AND d.departmentName = :inDepartmentName");
                query.setParameter("inSubSystem", subsystem);
                query.setParameter("inDepartmentName", departmentName);
                Department dep = (Department) query.getSingleResult();
                Title temp10 = new Title(s.getShopId() + "_" + s.getShopName() + "_" + s.getHistoryVenues().iterator().next().getBuilding() + "Cashier", s.getShopId() + "_" + s.getShopName() + "_" + s.getHistoryVenues().iterator().next().getBuilding(), "Cashier", "UPOS", false);
                Title temp11 = new Title(s.getShopId() + "_" + s.getShopName() + "_" + s.getHistoryVenues().iterator().next().getBuilding() + "ShopManager", s.getShopId() + "_" + s.getShopName() + "_" + s.getHistoryVenues().iterator().next().getBuilding(), "ShopManager", "UPOS", false);
                temp10.setDepartment(dep);
                temp11.setDepartment(dep);
                em.persist(temp10);
                em.persist(temp11);
                dep.getTitles().add(temp10);
                dep.getTitles().add(temp11);
                em.merge(dep);
            } else {
                String subsystem = "Food and BeverageGeneral";
                String departmentName = "Tenants Management";
                Query query = em.createQuery("SELECT d FROM Department d WHERE d.subSystem = :inSubSystem AND d.departmentName = :inDepartmentName");
                query.setParameter("inSubSystem", subsystem);
                query.setParameter("inDepartmentName", departmentName);
                Department dep = (Department) query.getSingleResult();
                Title temp10 = new Title(s.getShopId() + "_" + s.getShopName() + "_" + s.getHistoryVenues().iterator().next().getBuilding() + "RestaurantCashier", s.getShopId() + "_" + s.getShopName() + "_" + s.getHistoryVenues().iterator().next().getBuilding(), "RestaurantCashier", "UPOS", false);
                Title temp11 = new Title(s.getShopId() + "_" + s.getShopName() + "_" + s.getHistoryVenues().iterator().next().getBuilding() + "RestaurantManager", s.getShopId() + "_" + s.getShopName() + "_" + s.getHistoryVenues().iterator().next().getBuilding(), "RestaurantManager", "UPOS", false);
                temp10.setDepartment(dep);
                temp11.setDepartment(dep);
                em.persist(temp10);
                em.persist(temp11);
                dep.getTitles().add(temp10);
                dep.getTitles().add(temp11);
                em.merge(dep);

            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean setNegoStatus() {
        try {
            Query query = em.createQuery("SELECT v FROM TenantVenue v");
            List<TenantVenue> tvs = query.getResultList();
            Date current = Calendar.getInstance().getTime();
            for (TenantVenue tv : tvs) {
                List<Contract> con;
                boolean Avail = false;
                if (tv.getShop() == null && !tv.getNegoAvailability().equalsIgnoreCase("N.A.")) {
                    Avail = true;
                }
                if (tv.getShop() != null) {
                    con = (List<Contract>) tv.getShop().getContracts();
                    for (Contract c : con) {
                        if (current.after(c.getLeaseDateFrom()) && (c.getLeaseDateTo().getTime() - current.getTime()) / (1000 * 3600 * 24) > 0 && (c.getLeaseDateTo().getTime() - current.getTime()) / (1000 * 3600 * 24) <= 180) {
                            Avail = true;
                        }
                    }
                }
                if (Avail) {
                    tv.setNegoAvailability("Available");
                } else {
                    tv.setNegoAvailability("N.A.");
                }
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<TenantVenue> getAllResVenues() {
        try {
        Query query = em.createQuery("SELECT v FROM TenantVenue v WHERE v.building!='Shopping Mall' ORDER BY v.building");
        return query.getResultList();
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<Restaurant> getAllHistoryRestaurants() {
        try {
        Query query = em.createQuery("SELECT r FROM Restaurant r WHERE r.contractStatus='Expired'");
        return query.getResultList();
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<Restaurant> getAllActiveRestaurants() {
        try {
        Query query = em.createQuery("SELECT r FROM Restaurant r WHERE r.contractStatus='Active'");
        return query.getResultList();
        } catch(Exception ex) {
            return null;
        }
    }
}
