/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FoodBeverage.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Acer
 */
@Entity
public class BanquetItemOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float totalprice;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date orderDate;
    private int quantity;
    private List<String> menunames;
    private List<Long> menuids;

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
        if (!(object instanceof BanquetItemOrder)) {
            return false;
        }
        BanquetItemOrder other = (BanquetItemOrder) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FoodBeverage.entity.BanquetItemOrder[ id=" + getId() + " ]";
    }

    /**
     * @return the totalprice
     */
    public float getTotalprice() {
        return totalprice;
    }

    /**
     * @param totalprice the totalprice to set
     */
    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    /**
     * @return the orderDate
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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
     * @return the menunames
     */
    public List<String> getMenunames() {
        return menunames;
    }

    /**
     * @param menunames the menunames to set
     */
    public void setMenunames(List<String> menunames) {
        this.menunames = menunames;
    }

    /**
     * @return the menuids
     */
    public List<Long> getMenuids() {
        return menuids;
    }

    /**
     * @param menuids the menuids to set
     */
    public void setMenuids(List<Long> menuids) {
        this.menuids = menuids;
    }
    
}
