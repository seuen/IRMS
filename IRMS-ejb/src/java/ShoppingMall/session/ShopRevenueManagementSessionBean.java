/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingMall.session;

import ShoppingMall.entity.Contract;
import ShoppingMall.entity.DetailShopOrder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author user
 */
@Stateless
public class ShopRevenueManagementSessionBean implements ShopRevenueManagementSessionBeanLocal {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<DetailShopOrder> getDetailShopOrders(Long shopId, Date start, Date end) {
        Query q = em.createQuery("SELECT od FROM DetailShopOrder od WHERE od.shopId = :shopId");
        q.setParameter("shopId", shopId);
        List<DetailShopOrder> allDSO = q.getResultList();
        List<DetailShopOrder> result = new ArrayList();
        for (DetailShopOrder od : allDSO) {
            if (od.getPurchaseDate().compareTo(start) >= 0 && od.getPurchaseDate().compareTo(end) <= 0) {
                result.add(od);
            }
        }
        return result;
    }

    public List<Contract> getAllContracts() {
        List<Contract> l = new ArrayList();
        Query q = em.createQuery("SELECT c FROM Contract c WHERE c.shop.shopType!='Restaurant'");
        return q.getResultList();
    }

    public List<Contract> getAllResContracts() {
        List<Contract> l = new ArrayList();
        Query q = em.createQuery("SELECT c FROM Contract c WHERE c.shop.shopType='Restaurant'");
        return q.getResultList();
    }

    @Override
    public float calculateTotalRevenue(List<DetailShopOrder> dso) {
        float revenue = 0;
        for (DetailShopOrder od : dso) {
            revenue += od.getTotalPrice();
        }
        return revenue;
    }

    @Override
    public List<Contract> getAllActiveContracts() {
        List<Contract> l = this.getAllContracts();
        List<Contract> l2 = new ArrayList();
        for (Contract o : l) {
            if (o.getActivated().equalsIgnoreCase("Activated")) {
                l2.add(o);
            }
        }
        return l2;
    }

    @Override
    public List<Contract> getAllActiveResContracts() {
        List<Contract> l = this.getAllResContracts();
        List<Contract> l2 = new ArrayList();
        for (Contract o : l) {
            if (o.getActivated().equalsIgnoreCase("Activated")) {
                l2.add(o);
            }
        }
        return l2;
    }
}
