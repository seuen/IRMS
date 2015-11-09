/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction;

import Attraction.entity.Attraction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xing zhe
 */
public class AttractionTicketSale {
    
    private Attraction attraction;
    private List<AttractionTicketSaleEntry> attractionTicketSaleEntries;

    public AttractionTicketSale() {
        attractionTicketSaleEntries = new ArrayList<AttractionTicketSaleEntry>();
    }

    public AttractionTicketSale(Attraction attraction) {
        
        this();
        
        this.attraction = attraction;
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
     * @return the attractionTicketSaleEntries
     */
    public List<AttractionTicketSaleEntry> getAttractionTicketSaleEntries() {
        return attractionTicketSaleEntries;
    }

    /**
     * @param attractionTicketSaleEntries the attractionTicketSaleEntries to set
     */
    public void setAttractionTicketSaleEntries(List<AttractionTicketSaleEntry> attractionTicketSaleEntries) {
        this.attractionTicketSaleEntries = attractionTicketSaleEntries;
    }
    
}
