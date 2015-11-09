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
public class Attraction implements Serializable {
    @Id
    private String name;
    private String openTime;
    private String closeTime;
    private String address;
    private String detail;
    
    //relationship
    @OneToMany(mappedBy="attraction")
    private Collection<Equipment> equipments;
    @OneToMany(mappedBy="attraction")
    private Collection<AttraSection> attraSections;
    @OneToMany(mappedBy="attraction")
    private Collection<TicketType> ticketTypes;
    @OneToMany(mappedBy="attraction")
    private Collection<AttraItem> attraItems;
    @OneToMany(mappedBy="attraction")
    private Collection<Outlet> outlets;
    @OneToMany(mappedBy="attraction")
    private Collection<RetailOutlet> retailOutlets;
    @OneToMany(mappedBy = "attraction")
    private Collection<AttractionTicketOrder> attractionTicketOrder;

    @Override
    public String toString() {
        return "Attraction.entity.Attraction[ id=" + getName() + " ]";
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
     * @return the openTime
     */
    public String getOpenTime() {
        return openTime;
    }

    /**
     * @param openTime the openTime to set
     */
    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    /**
     * @return the closeTime
     */
    public String getCloseTime() {
        return closeTime;
    }

    /**
     * @param closeTime the closeTime to set
     */
    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the equipments
     */
    public Collection<Equipment> getEquipments() {
        return equipments;
    }

    /**
     * @param equipments the equipments to set
     */
    public void setEquipments(Collection<Equipment> equipments) {
        this.equipments = equipments;
    }

    /**
     * @return the ticketTypes
     */
    public Collection<TicketType> getTicketTypes() {
        return ticketTypes;
    }

    /**
     * @param ticketTypes the ticketTypes to set
     */
    public void setTicketTypes(Collection<TicketType> ticketTypes) {
        this.ticketTypes = ticketTypes;
    }

    /**
     * @return the attraItems
     */
    public Collection<AttraItem> getAttraItems() {
        return attraItems;
    }

    /**
     * @param attraItems the attraItems to set
     */
    public void setAttraItems(Collection<AttraItem> attraItems) {
        this.attraItems = attraItems;
    }

    /**
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail the detail to set
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * @return the attraSections
     */
    public Collection<AttraSection> getAttraSections() {
        return attraSections;
    }

    /**
     * @param attraSections the attraSections to set
     */
    public void setAttraSections(Collection<AttraSection> attraSections) {
        this.attraSections = attraSections;
    }

    /**
     * @return the outlets
     */
    public Collection<Outlet> getOutlets() {
        return outlets;
    }

    /**
     * @param outlets the outlets to set
     */
    public void setOutlets(Collection<Outlet> outlets) {
        this.outlets = outlets;
    }

    /**
     * @return the retailOutlets
     */
    public Collection<RetailOutlet> getRetailOutlets() {
        return retailOutlets;
    }

    /**
     * @param retailOutlets the retailOutlets to set
     */
    public void setRetailOutlets(Collection<RetailOutlet> retailOutlets) {
        this.retailOutlets = retailOutlets;
    }

    /**
     * @return the attractionTicketOrder
     */
    public Collection<AttractionTicketOrder> getAttractionTicketOrder() {
        return attractionTicketOrder;
    }

    /**
     * @param attractionTicketOrder the attractionTicketOrder to set
     */
    public void setAttractionTicketOrder(Collection<AttractionTicketOrder> attractionTicketOrder) {
        this.attractionTicketOrder = attractionTicketOrder;
    }
    
}
