/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition.entity;

import EntertainmentShow.entity.Theater;
import FoodBeverage.entity.BanquetHall;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Acer
 */
@Entity
public class Quotation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date quotationDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date eventDate;
    private float venueprice;
    private String requestparty;
    private String replyparty;
    
    @OneToOne
    private OpenSpace openspace=new OpenSpace();
    
    @OneToOne
    private Auditorium auditorium=new Auditorium();
    
    @OneToOne
    private OtherVenue othervenue=new OtherVenue();
    
    @OneToMany
    private List<ExhibitionSection> exhibitionsections;
    
    @OneToOne
    private Theater theater=new Theater();
    
    
    @OneToOne
    private BanquetHall banquethall=new BanquetHall();
    
    public Quotation(){
        exhibitionsections=new ArrayList();
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
        if (!(object instanceof Quotation)) {
            return false;
        }
        Quotation other = (Quotation) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConventionExhibition.entity.Quotation[ id=" + getId() + " ]";
    }

    /**
     * @return the quotationDate
     */
    public Date getQuotationDate() {
        return quotationDate;
    }

    /**
     * @param quotationDate the quotationDate to set
     */
    public void setQuotationDate(Date quotationDate) {
        this.quotationDate = quotationDate;
    }

    /**
     * @return the eventDate
     */
    public Date getEventDate() {
        return eventDate;
    }

    /**
     * @param eventDate the eventDate to set
     */
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * @return the venueprice
     */
    public float getVenueprice() {
        return venueprice;
    }

    /**
     * @param venueprice the venueprice to set
     */
    public void setVenueprice(float venueprice) {
        this.venueprice = venueprice;
    }

    /**
     * @return the requestparty
     */
    public String getRequestparty() {
        return requestparty;
    }

    /**
     * @param requestparty the requestparty to set
     */
    public void setRequestparty(String requestparty) {
        this.requestparty = requestparty;
    }

    /**
     * @return the replyparty
     */
    public String getReplyparty() {
        return replyparty;
    }

    /**
     * @param replyparty the replyparty to set
     */
    public void setReplyparty(String replyparty) {
        this.replyparty = replyparty;
    }

    /**
     * @return the openspace
     */
    public OpenSpace getOpenspace() {
        return openspace;
    }

    /**
     * @param openspace the openspace to set
     */
    public void setOpenspace(OpenSpace openspace) {
        this.openspace = openspace;
    }

    /**
     * @return the auditorium
     */
    public Auditorium getAuditorium() {
        return auditorium;
    }

    /**
     * @param auditorium the auditorium to set
     */
    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    /**
     * @return the exhibitionsections
     */
    public List<ExhibitionSection> getExhibitionsections() {
        return exhibitionsections;
    }

    /**
     * @param exhibitionsections the exhibitionsections to set
     */
    public void setExhibitionsections(List<ExhibitionSection> exhibitionsections) {
        this.exhibitionsections = exhibitionsections;
    }

    /**
     * @return the banquethall
     */
    public BanquetHall getBanquethall() {
        return banquethall;
    }

    /**
     * @param banquethall the banquethall to set
     */
    public void setBanquethall(BanquetHall banquethall) {
        this.banquethall = banquethall;
    }

    /**
     * @return the othervenue
     */
    public OtherVenue getOthervenue() {
        return othervenue;
    }

    /**
     * @param othervenue the othervenue to set
     */
    public void setOthervenue(OtherVenue othervenue) {
        this.othervenue = othervenue;
    }

    /**
     * @return the theater
     */
    public Theater getTheater() {
        return theater;
    }

    /**
     * @param theater the theater to set
     */
    public void setTheater(Theater theater) {
        this.theater = theater;
    }
    
}
