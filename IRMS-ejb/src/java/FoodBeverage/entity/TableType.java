/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FoodBeverage.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Acer
 */
@Entity
public class TableType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int capacity;
    private String type;
    private int reserveNum;//
    
    @ManyToOne
    private Restaurant restaurant=new Restaurant();
    
    @OneToMany(mappedBy="tabletype")
    private List<resReservation> reservations;
    
    @OneToMany(mappedBy="tabletype")
    private List<AvailableTable> availabletables;
    
    public TableType(){
        reservations=new ArrayList();
        availabletables=new ArrayList();
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
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TableType)) {
            return false;
        }
        TableType other = (TableType) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FoodBeverage.entity.TableType[ id=" + getId() + " ]";
    }

    /**
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
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
     * @return the reserveNum
     */
    public int getReserveNum() {
        return reserveNum;
    }

    /**
     * @param reserveNum the reserveNum to set
     */
    public void setReserveNum(int reserveNum) {
        this.reserveNum = reserveNum;
    }

    /**
     * @return the restaurant
     */
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * @param restaurant the restaurant to set
     */
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    /**
     * @return the reservations
     */
    public List<resReservation> getReservations() {
        return reservations;
    }

    /**
     * @param reservations the reservations to set
     */
    public void setReservations(List<resReservation> reservations) {
        this.reservations = reservations;
    }

    /**
     * @return the availabletables
     */
    public List<AvailableTable> getAvailabletables() {
        return availabletables;
    }

    /**
     * @param availabletables the availabletables to set
     */
    public void setAvailabletables(List<AvailableTable> availabletables) {
        this.availabletables = availabletables;
    }
    
}
