/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingMall.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Cindylulu
 */
@Entity
public class DetailShopOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date purchaseDate;
    private float totalPrice;  
    private int quantity;
    private Long itemId;
    private Long barcode;
    private String name;
    private String category; 
    private float unitPrice;
    private Long shopId;
    private String shopName;
    
    @ManyToOne
    private ShopOrder shoppingOrder;

    public DetailShopOrder(){
        
    }
    
    public DetailShopOrder(ShopItem shoppingItem, int quantity) {
        purchaseDate = Calendar.getInstance().getTime();
        this.shopId = shoppingItem.getShop().getShopId();
        this.shopName = shoppingItem.getShop().getShopName();
        this.quantity = quantity;
        this.itemId = shoppingItem.getId();
        this.barcode = shoppingItem.getBarcode();
        this.name = shoppingItem.getName();
        this.category = shoppingItem.getCategory();
        this.unitPrice = shoppingItem.getPrice();
        this.totalPrice = this.unitPrice * this.quantity;
    }       
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailShopOrder)) {
            return false;
        }
        DetailShopOrder other = (DetailShopOrder) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ShoppingMall.entity.DetailShoppingOrder[ id=" + getId() + " ]";
    }

    /**
     * @return the purchaseDate
     */
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * @param purchaseDate the purchaseDate to set
     */
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * @return the totalPrice
     */
    public float getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the itemId
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * @param itemId the itemId to set
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * @return the barcode
     */
    public Long getBarcode() {
        return barcode;
    }

    /**
     * @param barcode the barcode to set
     */
    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the unitPrice
     */
    public float getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return the shoppingOrder
     */
    public ShopOrder getShoppingOrder() {
        return shoppingOrder;
    }

    /**
     * @param shoppingOrder the shoppingOrder to set
     */
    public void setShoppingOrder(ShopOrder shoppingOrder) {
        this.shoppingOrder = shoppingOrder;
    }

    /**
     * @return the shopId
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * @param shopId the shopId to set
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * @return the shopName
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * @param shopName the shopName to set
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
