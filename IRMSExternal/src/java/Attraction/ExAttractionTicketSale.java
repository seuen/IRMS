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
 * @author zsy
 */
public class ExAttractionTicketSale {
    private Attraction attraction;
    private List<ExAttractionTicketSaleEntry> exAttractionTicketSaleEntries;
    
    public ExAttractionTicketSale(){
     exAttractionTicketSaleEntries = new ArrayList<ExAttractionTicketSaleEntry>();
    }
    
    public ExAttractionTicketSale(Attraction attraction){
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
    public List<ExAttractionTicketSaleEntry> getExAttractionTicketSaleEntries() {
        return exAttractionTicketSaleEntries;
    }

    /**
     * @param attractionTicketSaleEntries the attractionTicketSaleEntries to set
     */
    public void setExAttractionTicketSaleEntries(List<ExAttractionTicketSaleEntry> exAttractionTicketSaleEntries) {
        this.exAttractionTicketSaleEntries = exAttractionTicketSaleEntries;
    }
}
