/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author zsy
 */
@Entity
public class TicketType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String maxNumber;
    private String price;
    private int purchaseNum;
       
    //relationship
    @ManyToOne
    private Attraction attraction;
    @OneToMany(mappedBy="ticketType")
    private Collection<AttractionTicket> tickets;
    @OneToMany (mappedBy="ticketType")
    private Collection<PkgTicket> pkgTickets;
    @OneToMany (mappedBy="ticketType")
    private Collection<AvailableTicket> availableTickets;
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
        if (!(object instanceof TicketType)) {
            return false;
        }
        TicketType other = (TicketType) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Attraction.entity.TicketType[ id=" + getId() + " ]";
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
     * @return the maxNumber
     */
    public String getMaxNumber() {
        return maxNumber;
    }

    /**
     * @param maxNumber the maxNumber to set
     */
    public void setMaxNumber(String maxNumber) {
        this.maxNumber = maxNumber;
    }

    /**
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return the purchaseNum
     */
    public int getPurchaseNum() {
        return purchaseNum;
    }

    /**
     * @param purchaseNum the purchaseNum to set
     */
    public void setPurchaseNum(int purchaseNum) {
        this.purchaseNum = purchaseNum;
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
     * @return the tickets
     */
    public Collection<AttractionTicket> getTickets() {
        return tickets;
    }

    /**
     * @param tickets the tickets to set
     */
    public void setTickets(Collection<AttractionTicket> tickets) {
        this.tickets = tickets;
    }

    /**
     * @return the pkgTickets
     */
    public Collection<PkgTicket> getPkgTickets() {
        return pkgTickets;
    }

    /**
     * @param pkgTickets the pkgTickets to set
     */
    public void setPkgTickets(Collection<PkgTicket> pkgTickets) {
        this.pkgTickets = pkgTickets;
    }

    /**
     * @return the availableTickets
     */
    public Collection<AvailableTicket> getAvailableTickets() {
        return availableTickets;
    }

    /**
     * @param availableTickets the availableTickets to set
     */
    public void setAvailableTickets(Collection<AvailableTicket> availableTickets) {
        this.availableTickets = availableTickets;
    }

    
    
}
