/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author xing zhe
 */
@Entity
public class AttractionTicketOrder implements Serializable {
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private float price;
    private int quantity;
    @ManyToOne
    private Attraction attraction;
    @OneToMany(mappedBy= "attractionTicketOrder")
    private Collection <AttractionTicket> attractionTicket;
    @ManyToOne
    private AttractionOrder attractionOrder;
    
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
        if (!(object instanceof AttractionTicketOrder)) {
            return false;
        }
        AttractionTicketOrder other = (AttractionTicketOrder) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Attraction.entity.AttractionTicketOrder[ id=" + getId() + " ]";
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
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
     * @return the attraction
     */
    public Attraction getAttraction() {
        return attraction;
    }

    /**
     * @param attraction the attraction to set
     */
    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    /**
     * @return the attractionTicket
     */
    public Collection <AttractionTicket> getAttractionTicket() {
        return attractionTicket;
    }

    /**
     * @param attractionTicket the attractionTicket to set
     */
    public void setAttractionTicket(Collection <AttractionTicket> attractionTicket) {
        this.attractionTicket = attractionTicket;
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
    
}
