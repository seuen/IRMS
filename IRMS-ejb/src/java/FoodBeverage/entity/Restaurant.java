/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FoodBeverage.entity;

import ShoppingMall.entity.Shop;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Cindylulu
 */
@Entity
public class Restaurant extends Shop implements Serializable {

    private String resDescription;
    private String priceLevel;
    private String timeOfOpening;
    private String timeOfClosing;
    private int totalSeats;
    
    @OneToMany(cascade={CascadeType.REMOVE},mappedBy="restaurant")
    private List<TableType> tabletypes;
    
    public Restaurant(){
        tabletypes=new ArrayList();
    }

    /**
     * @return the resDescription
     */
    public String getResDescription() {
        return resDescription;
    }

    /**
     * @param resDescription the resDescription to set
     */
    public void setResDescription(String resDescription) {
        this.resDescription = resDescription;
    }

    /**
     * @return the priceLevel
     */
    public String getPriceLevel() {
        return priceLevel;
    }

    /**
     * @param priceLevel the priceLevel to set
     */
    public void setPriceLevel(String priceLevel) {
        this.priceLevel = priceLevel;
    }

    /**
     * @return the timeOfOpening
     */
    public String getTimeOfOpening() {
        return timeOfOpening;
    }

    /**
     * @param timeOfOpening the timeOfOpening to set
     */
    public void setTimeOfOpening(String timeOfOpening) {
        this.timeOfOpening = timeOfOpening;
    }

    /**
     * @return the timeOfClosing
     */
    public String getTimeOfClosing() {
        return timeOfClosing;
    }

    /**
     * @param timeOfClosing the timeOfClosing to set
     */
    public void setTimeOfClosing(String timeOfClosing) {
        this.timeOfClosing = timeOfClosing;
    }

    /**
     * @return the totalSeats
     */
    public int getTotalSeats() {
        return totalSeats;
    }

    /**
     * @param totalSeats the totalSeats to set
     */
    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    /**
     * @return the tabletypes
     */
    public List<TableType> getTabletypes() {
        return tabletypes;
    }

    /**
     * @param tabletypes the tabletypes to set
     */
    public void setTabletypes(List<TableType> tabletypes) {
        this.tabletypes = tabletypes;
    }

}
