/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingMall.session;

import ShoppingMall.entity.Contract;
import ShoppingMall.entity.DetailShopOrder;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface ShopRevenueManagementSessionBeanLocal {
    public List<DetailShopOrder> getDetailShopOrders(Long shopId, Date start, Date end);
    public float calculateTotalRevenue(List<DetailShopOrder> dso);
    public List<Contract> getAllActiveContracts();
    public List<Contract> getAllActiveResContracts();
}
