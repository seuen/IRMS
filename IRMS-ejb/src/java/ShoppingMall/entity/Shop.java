/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingMall.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author user
 */
@Entity
public class Shop implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shopId;
    private String shopName;
    private String shopType;
    private String shopOwnerName;
    private String email;
    private String telNum;
    private String status;
    private String contractStatus;
    private String category;
    private int area; 
    
    @OneToMany(mappedBy = "shop")
    private Collection<TenantVenue> currentVenues;
    @ManyToMany(mappedBy = "shops")
    private Collection<TenantVenue> historyVenues;
    @OneToMany(mappedBy = "shop")
    private Collection<ShopItem> shoppingItems;
    @OneToMany(mappedBy = "shop")
    private Collection<Contract> contracts;
    @OneToMany(mappedBy = "shop")
    private Collection<AdhocEvent> adhocEvents;

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getShopId() != null ? getShopId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shop)) {
            return false;
        }
        Shop other = (Shop) object;
        if ((this.getShopId() == null && other.getShopId() != null) || (this.getShopId() != null && !this.shopId.equals(other.shopId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ShoppingMall.entity.Shop[ id=" + getShopId() + " ]";
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

    /**
     * @return the shopType
     */
    public String getShopType() {
        return shopType;
    }

    /**
     * @param shopType the shopType to set
     */
    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    /**
     * @return the shopOwnerName
     */
    public String getShopOwnerName() {
        return shopOwnerName;
    }

    /**
     * @param shopOwnerName the shopOwnerName to set
     */
    public void setShopOwnerName(String shopOwnerName) {
        this.shopOwnerName = shopOwnerName;
    }


    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telNum
     */
    public String getTelNum() {
        return telNum;
    }

    /**
     * @param telNum the telNum to set
     */
    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the contractStatus
     */
    public String getContractStatus() {
        return contractStatus;
    }

    /**
     * @param contractStatus the contractStatus to set
     */
    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
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
     * @return the area
     */
    public int getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(int area) {
        this.area = area;
    }

    /**
     * @return the currentVenues
     */
    public Collection<TenantVenue> getCurrentVenues() {
        return currentVenues;
    }

    /**
     * @param currentVenues the currentVenues to set
     */
    public void setCurrentVenues(Collection<TenantVenue> currentVenues) {
        this.currentVenues = currentVenues;
    }

//    /**
//     * @return the historyVenues
//     */
//    public Collection<TenantVenue> getHistoryVenues() {
//        return historyVenues;//    }
//
//    /**
//     * @param historyVenues the historyVenues to set
//     */
//    public void setHistoryVenues(Collection<TenantVenue> historyVenues) {
//        this.historyVenues = historyVenues;
//    }

    /**
     * @return the shoppingItems
     */
    public Collection<ShopItem> getShoppingItems() {
        return shoppingItems;
    }

    /**
     * @param shoppingItems the shoppingItems to set
     */
    public void setShoppingItems(Collection<ShopItem> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }

    /**
     * @return the contracts
     */
    public Collection<Contract> getContracts() {
        return contracts;
    }

    /**
     * @param contracts the contracts to set
     */
    public void setContracts(Collection<Contract> contracts) {
        this.contracts = contracts;
    }

    /**
     * @return the adhocEvents
     */
    public Collection<AdhocEvent> getAdhocEvents() {
        return adhocEvents;
    }

    /**
     * @param adhocEvents the adhocEvents to set
     */
    public void setAdhocEvents(Collection<AdhocEvent> adhocEvents) {
        this.adhocEvents = adhocEvents;
    }

    /**
     * @return the historyVenues
     */
    public Collection<TenantVenue> getHistoryVenues() {
        return historyVenues;
    }

    /**
     * @param historyVenues the historyVenues to set
     */
    public void setHistoryVenues(Collection<TenantVenue> historyVenues) {
        this.historyVenues = historyVenues;
    }
    
}
