/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author zsy
 */
@Entity
public class Bundle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String price;
    
    //relationship
    @OneToMany(mappedBy="bundle")
    private Collection<PkgTicket> pkgTickets;
    @OneToMany(mappedBy ="bundle")
    private Collection<BundleOrder> bundleOrders;
    
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
        if (!(object instanceof Bundle)) {
            return false;
        }
        Bundle other = (Bundle) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Attraction.entity.Bundle[ id=" + getId() + " ]";
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
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return the pkgTickets
     */
    public Collection<PkgTicket> getPkgTickets() {
        return pkgTickets;
    }

    /**
     * @param pkgTickets the pkgTickets to set
     */
    public void setPkgTickets(Collection<PkgTicket> pkgTickets) {
        this.pkgTickets = pkgTickets;
    }

   

    /**
     * @return the bundleOrders
     */
    public Collection<BundleOrder> getBundleOrders() {
        return bundleOrders;
    }

    /**
     * @param bundleOrders the bundleOrders to set
     */
    public void setBundleOrders(Collection<BundleOrder> bundleOrders) {
        this.bundleOrders = bundleOrders;
    }
    
}
