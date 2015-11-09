/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FoodBeverage.entity;

import Accommodation.entity.Guest;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Acer
 */
@Entity
public class resReservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date curDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date resDate;
    private int amount;
   
    @OneToOne
    private Timeslot timeslot=new Timeslot();
    
    @ManyToOne
    private TableType tabletype=new TableType();
    
    @ManyToOne
    private Guest guest=new Guest();
    
    

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
        if (!(object instanceof resReservation)) {
            return false;
        }
        resReservation other = (resReservation) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FoodBeverage.entity.resReservation[ id=" + getId() + " ]";
    }

    /**
     * @return the curDate
     */
    public Date getCurDate() {
        return curDate;
    }

    /**
     * @param curDate the curDate to set
     */
    public void setCurDate(Date curDate) {
        this.curDate = curDate;
    }

    /**
     * @return the resDate
     */
    public Date getResDate() {
        return resDate;
    }

    /**
     * @param resDate the resDate to set
     */
    public void setResDate(Date resDate) {
        this.resDate = resDate;
    }

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * @return the timeslot
     */
    public Timeslot getTimeslot() {
        return timeslot;
    }

    /**
     * @param timeslot the timeslot to set
     */
    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    /**
     * @return the tabletype
     */
    public TableType getTabletype() {
        return tabletype;
    }

    /**
     * @param tabletype the tabletype to set
     */
    public void setTabletype(TableType tabletype) {
        this.tabletype = tabletype;
    }

    /**
     * @return the guest
     */
    public Guest getGuest() {
        return guest;
    }

    /**
     * @param guest the guest to set
     */
    public void setGuest(Guest guest) {
        this.guest = guest;
    }
    
}
