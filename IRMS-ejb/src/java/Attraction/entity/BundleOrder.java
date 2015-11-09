/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author xing zhe
 */
@Entity
public class BundleOrder implements Serializable {
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
    private Long id;
    private float price;
    private int quantity;
    @ManyToOne
    private AttractionOrder attractionOrder; 
    @ManyToOne
    private Bundle bundle;
    
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
        if (!(object instanceof BundleOrder)) {
            return false;
        }
        BundleOrder other = (BundleOrder) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Attraction.entity.BundleOrder[ id=" + getId() + " ]";
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
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
     * @return the attractionOrder
     */
    public AttractionOrder getAttractionOrder() {
        return attractionOrder;
    }

    /**
     * @param attractionOrder the attractionOrder to set
     */
    public void setAttractionOrder(AttractionOrder attractionOrder) {
        this.attractionOrder = attractionOrder;
    }

    /**
     * @return the bundle
     */
    public Bundle getBunlde() {
        return bundle;
    }

    /**
     * @param bunlde the bundle to set
     */
    public void setBunlde(Bundle bunlde) {
        this.bundle = bunlde;
    }
    
}
