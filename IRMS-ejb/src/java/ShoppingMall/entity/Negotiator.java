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

/**
 *
 * @author Cindylulu
 */
@Entity
public class Negotiator implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String email;
    private String negotiatorName;
    private String telNum;
    private String shopName;
    private String shopType;
    private String shopCategory;
    @ManyToMany (mappedBy = "negotiators")
    private Collection<TenantVenue> venues;


    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Negotiator)) {
            return false;
        }
        Negotiator other = (Negotiator) object;
        if ((this.getNegotiatorName() == null && other.getNegotiatorName() != null) || (this.getNegotiatorName() != null && !this.negotiatorName.equals(other.negotiatorName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ShoppingMall.entity.NegotiatingShop[ id=" + getNegotiatorName() + " ]";
    }

    /**
     * @return the negotiatorName
     */
    public String getNegotiatorName() {
        return negotiatorName;
    }

    /**
     * @param negotiatorName the negotiatorName to set
     */
    public void setNegotiatorName(String negotiatorName) {
        this.negotiatorName = negotiatorName;
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
     * @return the shopCategory
     */
    public String getShopCategory() {
        return shopCategory;
    }

    /**
     * @param shopCategory the shopCategory to set
     */
    public void setShopCategory(String shopCategory) {
        this.shopCategory = shopCategory;
    }

    /**
     * @return the venues
     */
    public Collection<TenantVenue> getVenues() {
        return venues;
    }

    /**
     * @param venues the venues to set
     */
    public void setVenues(Collection<TenantVenue> venues) {
        this.venues = venues;
    }
    
}
