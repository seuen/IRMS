/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author zsy
 */
@Entity
public class AttractionGuest implements Serializable {
    @Id
    private String IC;
    private String firstName;
    private String lastName;
    private String email;
    
    //relationship
    @OneToMany(mappedBy="attractionGuest")
    private Collection<AttractionOrder> attractionOrders;


    @Override
    public String toString() {
        return "Attraction.entity.AttractionGuest[ id=" + getIC() + " ]";
    }

    /**
     * @return the IC
     */
    public String getIC() {
        return IC;
    }

    /**
     * @param IC the IC to set
     */
    public void setIC(String IC) {
        this.IC = IC;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
     * @return the attractionOrders
     */
    public Collection<AttractionOrder> getAttractionOrders() {
        return attractionOrders;
    }

    /**
     * @param attractionOrders the attractionOrders to set
     */
    public void setAttractionOrders(Collection<AttractionOrder> attractionOrders) {
        this.attractionOrders = attractionOrders;
    }
    
}
