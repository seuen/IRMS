/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntertainmentShow.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author xing zhe
 */
@Entity
public class ShowOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float totalPrice;
    private int quantity;
    @OneToMany(mappedBy="showOrder")
    private Collection<ShowTicket> showTickets;
    
    public ShowOrder(){}
    
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
        if (!(object instanceof ShowOrder)) {
            return false;
        }
        ShowOrder other = (ShowOrder) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntertainmentShow.entity.Oderder[ id=" + getId() + " ]";
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
     * @return the showTickets
     */
    public Collection<ShowTicket> getShowTickets() {
        return showTickets;
    }

    /**
     * @param showTickets the showTickets to set
     */
    public void setShowTickets(Collection<ShowTicket> showTickets) {
        this.showTickets = showTickets;
    }
    
}
