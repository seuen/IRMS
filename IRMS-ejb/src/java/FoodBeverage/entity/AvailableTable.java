/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FoodBeverage.entity;

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
public class AvailableTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date countDate;
    
    private int availableNum;
    
    @OneToOne
    private Timeslot timeslot=new Timeslot();
    
    @ManyToOne
    private TableType tabletype=new TableType();

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
        if (!(object instanceof AvailableTable)) {
            return false;
        }
        AvailableTable other = (AvailableTable) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FoodBeverage.entity.AvailableTable[ id=" + getId() + " ]";
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
     * @return the countDate
     */
    public Date getCountDate() {
        return countDate;
    }

    /**
     * @param countDate the countDate to set
     */
    public void setCountDate(Date countDate) {
        this.countDate = countDate;
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
     * @return the availableNum
     */
    public int getAvailableNum() {
        return availableNum;
    }

    /**
     * @param availableNum the availableNum to set
     */
    public void setAvailableNum(int availableNum) {
        this.availableNum = availableNum;
    }
    
}
