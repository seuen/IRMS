/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Accommodation.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    
    private String name;
    private Float price;
    private String type;
    @ManyToOne
    private Hotel hotel;
    
    @OneToMany(mappedBy="item")
    private Collection<ItemOrder> itemOrders;
          
    public Item(){
        this.itemOrders=new ArrayList();
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the name fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Accommodation.entity.Item[ name=" + name + " ]";
    }
    
    public String getName() {
        return name;
    }
   
    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getItemId() {
        return itemId;
    }
    
    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * @return the itemOrders
     */
    public Collection<ItemOrder> getItemOrders() {
        return itemOrders;
    }

    /**
     * @param itemOrders the itemOrders to set
     */
    public void setItemOrders(Collection<ItemOrder> itemOrders) {
        this.itemOrders = itemOrders;
    }
    

}
