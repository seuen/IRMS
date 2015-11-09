/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor
 */
package ShoppingMall.session;

import CRM.entity.MemberAccount;
import Common.entity.DelayedPayment;
import Common.entity.MemberTransaction;
import ShoppingMall.entity.DetailShopOrder;
import ShoppingMall.entity.ShopItem;
import ShoppingMall.entity.ShopOrder;
import java.util.List;
import javax.ejb.Local;
import util.exception.OutOfStockException;

/**
 *
 * @author Cindylulu
 */
@Local
public interface ShopProductManagementSessionBeanLocal {
    // Manager Portal
    public ShopItem createItem(ShopItem shoppingItem, Long shopId); // Link up item to shop and persist
    public boolean deleteItem(Long itemId); // Detach and delete and flush 
    public boolean updateItem(ShopItem shoppingItem); // Flush the change
    public ShopItem getItem(Long itemId); // View one item
    public List<ShopItem> getShopItems(Long shopId); // View all items
    public List<DetailShopOrder> getDetailShopOrders(Long shopId); // View all line items of one shop
    
    // Cashier Portal
    public ShopOrder createOrder(ShopOrder order, List<DetailShopOrder> sOrders, MemberAccount mem) throws OutOfStockException; // Link up detail orders and member(could be null) to order and                                                                                                                    //persist details order together with order
    public boolean deleteDetailShopOrder(Long detailId);  // Delete and detach line item for returned product
    public DetailShopOrder getDetailShopOrder(Long detailId);  // View line item information    
    public ShopOrder getOrder(Long orderId); // View one order
    public MemberTransaction addTransaction(MemberTransaction memberT, Long orderId);
}
