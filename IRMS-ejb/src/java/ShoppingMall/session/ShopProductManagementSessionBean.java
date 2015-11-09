/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor
 */
package ShoppingMall.session;

import CRM.entity.MemberAccount;
import Common.entity.MemberTransaction;
import ShoppingMall.entity.DetailShopOrder;
import ShoppingMall.entity.Shop;
import ShoppingMall.entity.ShopItem;
import ShoppingMall.entity.ShopOrder;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.OutOfStockException;

/**
 *
 * @author Cindylulu
 */
@Stateless
public class ShopProductManagementSessionBean implements ShopProductManagementSessionBeanLocal {

    @PersistenceContext
    EntityManager em;
//    @EJB
//    ShopVenueManagementSessionBeanLocal svmsb;

    @Override
    public ShopItem createItem(ShopItem shoppingItem, Long shopId) {
        Shop shop = em.find(Shop.class, shopId);
        shoppingItem.setShop(shop);
        shop.getShoppingItems().add(shoppingItem);
        em.persist(shoppingItem);
        em.merge(shop);
        em.flush();
        return shoppingItem;
    }

    @Override
    public boolean deleteItem(Long itemId) {
        ShopItem sItem = em.find(ShopItem.class, itemId);
        if (sItem == null) {
            return false;
        } else {
            Shop shop = sItem.getShop();
            shop.getShoppingItems().remove(sItem);
            sItem.setShop(null);
            em.remove(sItem);
            em.merge(shop);
            em.flush();
            return true;
        }
    }

    @Override
    public boolean updateItem(ShopItem shoppingItem) {
        try {
            em.merge(shoppingItem);
            em.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public ShopItem getItem(Long itemId) {
        return em.find(ShopItem.class, itemId);
    }

    @Override
    public List<ShopItem> getShopItems(Long shopId) {
        Query q = em.createQuery("SELECT si FROM ShoppingItem si WHERE si.shop.shopId=:shopId");
        q.setParameter("shopId", shopId);
        return q.getResultList();
    }

    @Override
    public List<DetailShopOrder> getDetailShopOrders(Long shopId) {
        Query q = em.createQuery("SELECT dso FROM DetailShopOrder dso WHERE dso.shopId=:shopId");
        q.setParameter("shopId", shopId);
        return q.getResultList();
    }

    @Override
    public ShopOrder createOrder(ShopOrder order, List<DetailShopOrder> sOrders, MemberAccount mem) throws OutOfStockException {
        em.persist(order);
        for (DetailShopOrder dso : sOrders) {
            try {
                addDSOtoOrder(order, dso);
            } catch (OutOfStockException ex) {
                Logger.getLogger(ShopProductManagementSessionBean.class.getName()).log(Level.SEVERE, null, ex);
                throw new OutOfStockException(ex.getItemId(), ex.getItemName(), ex.getPrice(), ex.getQuantity(), ex.getBarcode(), ex.getCategory());
            }
        }
        order.setMemberAccount(mem);
        if (mem != null) {
            mem.getShoppingOrder().add(order);
            em.merge(mem);
        }
        order.setPurchaseDate(Calendar.getInstance().getTime());
        em.merge(order);
        em.flush();
        return order;
    }

    private void addDSOtoOrder(ShopOrder order, DetailShopOrder dso) throws OutOfStockException {
        ShopItem sItem = em.find(ShopItem.class, dso.getItemId());
        if (dso.getQuantity() <= sItem.getQuantity()) {
            dso.setPurchaseDate(Calendar.getInstance().getTime());
            dso.setShoppingOrder(order);
            em.persist(dso);
            order.getDetailShoppintOrder().add(dso);
            sItem.setQuantity(sItem.getQuantity() - dso.getQuantity());
            em.flush();
        } else {
            throw new OutOfStockException(dso.getItemId(), dso.getName(), dso.getUnitPrice(), dso.getQuantity(), dso.getBarcode(), dso.getCategory());
        }
    }

    @Override
    public boolean deleteDetailShopOrder(Long detailId) {
        DetailShopOrder dso = em.find(DetailShopOrder.class, detailId);
        if (dso == null) {
            return false;
        } else {
            ShopOrder so = em.find(ShopOrder.class, dso.getShoppingOrder().getId());
            dso.setShoppingOrder(null);
            so.getDetailShoppintOrder().remove(dso);
            ShopItem sItem = em.find(ShopItem.class, dso.getItemId());
            sItem.setQuantity((sItem.getQuantity() + dso.getQuantity()));
            em.merge(so);
            em.remove(dso);
            return true;
        }
    }

    @Override
    public DetailShopOrder getDetailShopOrder(Long detailId) {
        return em.find(DetailShopOrder.class, detailId);
    }

    @Override
    public ShopOrder getOrder(Long orderId) {
        return em.find(ShopOrder.class, orderId);
    }

    @Override
    public MemberTransaction addTransaction(MemberTransaction memberT, Long orderId) {
        ShopOrder so = em.find(ShopOrder.class, orderId);
        memberT.getShoppingOrder().add(so);
        em.merge(memberT);
        return memberT;
    }
}
