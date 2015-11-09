/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FoodBeverage.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author zsy
 */
@Entity
public class FoodMaterialOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date odate;
    private String hotelName;
    
    @OneToMany(mappedBy = "foodMaterialOrder")
    private Collection<FoodOrder> foodOrders;
    
    
    public FoodMaterialOrder(){
        odate = new Date();
        status = "New";
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FoodMaterialOrder)) {
            return false;
        }
        FoodMaterialOrder other = (FoodMaterialOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FoodBeverage.FoodMaterialOrder[ id=" + id + " ]";
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
     * @return the odate
     */
    public Date getOdate() {
        return odate;
    }

    /**
     * @param odate the odate to set
     */
    public void setOdate(Date odate) {
        this.odate = odate;
    }

    /**
     * @return the foodOrders
     */
    public Collection<FoodOrder> getFoodOrders() {
        return foodOrders;
    }

    /**
     * @param foodOrders the foodOrders to set
     */
    public void setFoodOrders(Collection<FoodOrder> foodOrders) {
        this.foodOrders = foodOrders;
    }

    /**
     * @return the hotelName
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * @param hotelName the hotelName to set
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
    
}
