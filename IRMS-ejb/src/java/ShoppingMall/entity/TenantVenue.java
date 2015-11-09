/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingMall.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author xing zhe
 */
@Entity
public class TenantVenue implements Serializable {
    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long realID;
    private String id;
    private String floor; 
    private String sector;
    private String status; 
    private int counter = 0;
    private Long occupiedShopId;
    private int area;
    private String combineStatus;
    private String building; // ShoppingMall Or Other Bulldings
    private String negoAvailability;
   
    @ManyToOne
    private Shop shop;
    @ManyToMany(cascade={CascadeType.REMOVE})
    @JoinTable (name="TenantVenue_Shop",  
    joinColumns={@JoinColumn(name="shops_ShopId")},  
    inverseJoinColumns={@JoinColumn(name="historyVenues_realID")}  
            )
    private Collection<Shop> shops;
    @ManyToOne
    private Combine combine;
    @ManyToMany
    private Collection<Negotiator> negotiators;
    
    public TenantVenue(){
        this.status="Free";
    }
    public void createVenue(String id, String floor, String sector){
        this.setId(id);
        this.setFloor(floor);
        this.setSector(sector);
        occupiedShopId = null;
        combineStatus = "Single";
        this.setStatus("Empty"); 
        this.setNegoAvailability("Available");
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
   
    public String getFloor(){
        return floor;
    }
    
    public void setFloor(String floor){
        this.floor = floor;
    }
    
    public String getSector(){
        return sector;
}
    public void setSector(String sector){
        this.sector = sector;
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
        if (!(object instanceof TenantVenue)) {
            return false;
        }
        TenantVenue other = (TenantVenue) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ShoppingMall.entity.venue[ id=" + getId() + " ]";
    }

    /**
     * @return the status
     */
    public String getStatus() {
      return status;
    }
//
//    /**
//     * @param status the status to set
//     */
   public void setStatus(String status) {
       this.status = status;
     }

    /**
     * @return the occupiedShopId
     */
    public Long getOccupiedShopId() {
        return occupiedShopId;
    }

    /**
     * @param occupiedShopId the occupiedShopId to set
     */
    public void setOccupiedShopId(Long occupiedShopId) {
        this.occupiedShopId = occupiedShopId;
    }

    /**
     * @return the counter
     */
    public int getCounter() {
        return counter;
    }

    /**
     * @param counter the counter to set
     */
    public void setCounter(int counter) {
        this.counter = counter;
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
     * @return the combine
     */
    public Combine getCombine() {
        return combine;
    }

    /**
     * @param combine the combine to set
     */
    public void setCombine(Combine combine) {
        this.combine = combine;
    }

    /**
     * @return the combineStatus
     */
    public String getCombineStatus() {
        return combineStatus;
    }

    /**
     * @param combineStatus the combineStatus to set
     */
    public void setCombineStatus(String combineStatus) {
        this.combineStatus = combineStatus;
    }

//    /**
//     * @return the shop
//     */
//    public Collection<Shop> getShops() {
//        return shops;
//    }
//
//    /**
//     * @param shop the shop to set
//     */
//    public void setShops(Collection<Shop> shops) {
//        this.shops = shops;
//    }


    /**
     * @return the building
     */
    public String getBuilding() {
        return building;
    }

    /**
     * @param building the building to set
     */
    public void setBuilding(String building) {
        this.building = building;
    }

    /**
     * @return the negoAvailability
     */
    public String getNegoAvailability() {
        return negoAvailability;
    }

    /**
     * @param negoAvailability the negoAvailability to set
     */
    public void setNegoAvailability(String negoAvailability) {
        this.negoAvailability = negoAvailability;
    }

    /**
     * @return the negotiators
     */
    public Collection<Negotiator> getNegotiators() {
        return negotiators;
    }

    /**
     * @param negotiators the negotiators to set
     */
    public void setNegotiators(Collection<Negotiator> negotiators) {
        this.negotiators = negotiators;
    }

    /**
     * @return the realID
     */
    public Long getRealID() {
        return realID;
    }

    /**
     * @param realID the realID to set
     */
    public void setRealID(Long realID) {
        this.realID = realID;
    }

    /**
     * @return the shop
     */
    public Shop getShop() {
        return shop;
    }

    /**
     * @param shop the shop to set
     */
    public void setShop(Shop shop) {
        this.shop = shop;
    }


    /**
     * @return the shops
     */
    public Collection<Shop> getShops() {
        return shops;
    }

    /**
     * @param shops the shops to set
     */
    public void setShops(Collection<Shop> shops) {
        this.shops = shops;
    }
}
