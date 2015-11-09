/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soap.common;

import CRM.entity.MemberAccount;
import CRM.entity.Membership;
import FoodBeverage.entity.Restaurant;
import FoodBeverage.entity.TableType;
import ShoppingMall.entity.DetailShopOrder;
import ShoppingMall.entity.Shop;
import ShoppingMall.entity.ShopItem;
import ShoppingMall.entity.ShopOrder;
import ShoppingMall.session.ShopProductManagementSessionBeanLocal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 *
 * @author yifeng
 */
@WebService(serviceName = "CommonFunctionsUPOS")
@Stateless()
public class CommonFunctionsUPOS {

    @PersistenceContext
    private EntityManager em;
    @EJB
    ShopProductManagementSessionBeanLocal spmsbl;

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getShopById")
    public Shop getShopById(@WebParam(name = "shopId") long shopId) {
        //TODO write your implementation code here:
        Shop shop = em.find(Shop.class, shopId);
        if (shop != null) {
            Shop temp = this.disableRelationsForShop(shop);
            return temp;
        } else {
            System.err.println("shop in UPOS is null");
            return null;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getShoppingMallItemsByShopId")
    public List<ShopItem> getShoppingMallItemsByShopId(@WebParam(name = "shopId") long shopId) {
        //TODO write your implementation code here:
        System.out.println("shopid received " + shopId);
        Shop shop = em.find(Shop.class, shopId);
        if (shop != null) {
            List<ShopItem> shopitems = new ArrayList();
            for (ShopItem item : shop.getShoppingItems()) {
                ShopItem temp = this.disableRelationsForShopItem(item);
                shopitems.add(temp);
            }
            return shopitems;
        } else {
            return null;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getShopItemByItemId")
    public ShopItem getShopItemByItemId(@WebParam(name = "itemId") long itemId) {
        //TODO write your implementation code here:
        ShopItem temp = em.find(ShopItem.class, itemId);

        return this.disableRelationsForShopItem(temp);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteShopItemByItemId")
    public ShopItem deleteShopItemByItemId(@WebParam(name = "itemId") long itemId) {
        //TODO write your implementation code here:
        ShopItem tempItem = em.find(ShopItem.class, itemId);
        if (tempItem != null) {
            Shop tempShop = tempItem.getShop();
            if (tempShop != null) {
                tempShop.getShoppingItems().remove(tempItem);
                tempItem.setShop(null);
                em.remove(tempItem);
            } else {
                System.out.println("item is not linked to shop");
            }
        }

        return this.disableRelationsForShopItem(tempItem);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getShopItemByBarcode")
    public ShopItem getShopItemByBarcode(@WebParam(name = "shopId") String shopId, @WebParam(name = "barcode") String barcode) {
        //TODO write your implementation code here:
        System.out.println("enter new barcode " + barcode);
        Shop shop = em.find(Shop.class, Long.parseLong(shopId));
        if (shop != null) {
            Collection<ShopItem> shopitems = shop.getShoppingItems();
            for (ShopItem item : shopitems) {
                if (item.getBarcode().toString().equals(barcode)) {
                    return this.disableRelationsForShopItem(item);
                }
            }
        }
        return null;
//        Query query = em.createQuery("SELECT s FROM ShopItem s WHERE s.barcode = :inBarcode");
//        query.setParameter("inBarcode", Long.valueOf(barcode));
//        try {
//            return this.disableRelationsForShopItem((ShopItem) query.getSingleResult());
//        } catch (Exception ex) {
//            return null;
//        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createDetailOrder")
    public DetailShopOrder createDetailOrder(@WebParam(name = "shopItem") ShopItem shopItem, @WebParam(name = "quantity") int quantity) {
        //TODO write your implementation code here:

        return new DetailShopOrder(shopItem, quantity);
    }

    //utils
    private ShopItem disableRelationsForShopItem(ShopItem item) {
        ShopItem temp = new ShopItem();

        temp.setBarcode(item.getBarcode());
        temp.setCategory(item.getCategory());
        temp.setDescription(item.getDescription());
        temp.setId(item.getId());
        temp.setName(item.getName());
        temp.setPrice(item.getPrice());
        temp.setQuantity(item.getQuantity());

        return temp;
    }

    private Shop disableRelationsForShop(Shop shop) {
        Shop temp = new Shop();

        temp.setArea(shop.getArea());
        temp.setCategory(shop.getCategory());
        temp.setContractStatus(shop.getContractStatus());
        temp.setShopName(shop.getShopName());
        temp.setShopId(shop.getShopId());

        return temp;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createShopOrder")
    @RequestWrapper(className = "soap.createShopOrder")
    @ResponseWrapper(className = "soap.createShopOrderResponse")
    public String createShopOrder(@WebParam(name = "shopOrder") ShopOrder shopOrder, @WebParam(name = "detailOrders") List<DetailShopOrder> detailOrders, @WebParam(name = "memberId") Long memberId) {
        //TODO write your implementation code here:
        try {
            MemberAccount account = null;
            shopOrder.setDetailShoppintOrder(new ArrayList());
            if (memberId != null) {
                account = em.find(MemberAccount.class, memberId);
            }
            spmsbl.createOrder(shopOrder, detailOrders, account);
            return "create order successfully";
        } catch (Exception ex) {
            return "failed to create shop order";
        }

    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getTablesByRestaurantId")
    public List<TableType> getTablesByRestaurantId(@WebParam(name = "restId") Long restId) {
        //TODO write your implementation code here:
        List<TableType> response;
        System.out.println("restId received " + restId);
        if (restId != null) {
            Restaurant rest = em.find(Restaurant.class, restId);
            if (rest != null) {
                response = new ArrayList();
                List<TableType> tables = rest.getTabletypes();
                for (TableType table : tables) {
                    response.add(this.disableRelationsForTableType(table));
                }
                return response;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private TableType disableRelationsForTableType(TableType table) {
        TableType temp = new TableType();
        temp.setCapacity(table.getCapacity());
        temp.setId(table.getId());
        temp.setReserveNum(table.getReserveNum());
        temp.setType(table.getType());
        return temp;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "addShoppingMallItem")
    public ShopItem addShoppingMallItem(@WebParam(name = "curShop") String curShop, @WebParam(name = "newItem") ShopItem newItem) {
        //TODO write your implementation code here:

        //look for shop
        Shop shop = em.find(Shop.class, Long.parseLong(curShop));
        if ((shop != null) && (newItem != null)) {
            if (newItem.getId() == null) {
                System.out.println(newItem.getName() + " " + newItem.getCategory() + " " + newItem.getDescription());
                shop.getShoppingItems().add(newItem);
                newItem.setShop(shop);
                em.persist(newItem);
                em.merge(shop);
                System.out.println("newitem id " + newItem.getId());
            } else {
                System.out.println("update item " + newItem.getId() + newItem.getName());
                newItem.setShop(shop);
                em.merge(newItem);
            }
            ShopItem temp = this.disableRelationsForShopItem(newItem);
            System.out.println("shop in newItem " + newItem.getShop());
            return temp;
        } else {
            return null;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "addRestaurantTable")
    public String addRestaurantTable(@WebParam(name = "restId") String restId, @WebParam(name = "newTable") TableType newTable) {
        //TODO write your implementation code here:

        Restaurant rest = em.find(Restaurant.class, Long.parseLong(restId));
        if ((rest != null) && (newTable != null)) {
            newTable.setRestaurant(rest);
            rest.getTabletypes().add(newTable);
            em.persist(newTable);
            em.merge(rest);
            return "table added successfully";
        }
        return "fail to add table";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getMemberAccountByNFCCardNum")
    public Membership getMemberAccountByNFCCardNum(@WebParam(name = "nfcCardNum") String nfcCardNum) {
        //TODO write your implementation code here:
        Query query = em.createQuery("SELECT m FROM Membership m WHERE m.cardNum = :inCardNum");
        query.setParameter("inCardNum", nfcCardNum);
        Membership mem = (Membership) query.getSingleResult();
        if (mem != null) {
            return this.deleteAllRelationsForMembership(mem);
        } else {
            return null;
        }

    }

    private Membership deleteAllRelationsForMembership(Membership mem) {
        Membership temp = new Membership();
        temp.setCardNum(mem.getCardNum());
        temp.setCardValue(mem.getCardValue());
        temp.setId(mem.getId());
        temp.setLoyaltyPoints(mem.getLoyaltyPoints());
        temp.setMemberType(mem.getMemberType());
        return temp;
    }
}
