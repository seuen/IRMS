/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction;

import Attraction.entity.AttractionTicketOrder;
import Attraction.entity.TicketType;

/**
 *
 * @author zsy
 */
public class ExAttractionTicketSaleEntry {
    private TicketType ticketType;
    private AttractionTicketOrder attractionTicketOrder; 
    
    
    public ExAttractionTicketSaleEntry() {
    }

    public ExAttractionTicketSaleEntry(TicketType ticketType, AttractionTicketOrder attractionTicketOrder) {
        this.ticketType = ticketType;
        this.attractionTicketOrder = attractionTicketOrder;
    }
    
    

    /**
     * @return the ticketType
     */
    public TicketType getTicketType() {
        return ticketType;
    }

    /**
     * @param ticketType the ticketType to set
     */
    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    /**
     * @return the attractionTicketOrder
     */
    public AttractionTicketOrder getAttractionTicketOrder() {
        return attractionTicketOrder;
    }

    /**
     * @param attractionTicketOrder the attractionTicketOrder to set
     */
    public void setAttractionTicketOrder(AttractionTicketOrder attractionTicketOrder) {
        this.attractionTicketOrder = attractionTicketOrder;
    }
}
