/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition.entity;

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
public class ExhibitionSection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int sectionNo;
    private float dayprice;
    private int capacity;
    
    @OneToMany
    private List<ExhibitionSection> neigbours;
    
    @ManyToOne
    private ExhibitionHall exhibitionhall;
    
    @OneToMany(mappedBy="exhibitionsection")
    private List<ConventionSchedule> cschedules;
    
    public ExhibitionSection(){
        neigbours=new ArrayList();
        cschedules=new ArrayList();
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
        if (!(object instanceof ExhibitionSection)) {
            return false;
        }
        ExhibitionSection other = (ExhibitionSection) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConventionExhibition.entity.ExhibitionSection[ id=" + getId() + " ]";
    }

    /**
     * @return the sectionNo
     */
    public int getSectionNo() {
        return sectionNo;
    }

    /**
     * @param sectionNo the sectionNo to set
     */
    public void setSectionNo(int sectionNo) {
        this.sectionNo = sectionNo;
    }

    /**
     * @return the dayprice
     */
    public float getDayprice() {
        return dayprice;
    }

    /**
     * @param dayprice the dayprice to set
     */
    public void setDayprice(float dayprice) {
        this.dayprice = dayprice;
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
     * @return the neigbours
     */
    public List<ExhibitionSection> getNeigbours() {
        return neigbours;
    }

    /**
     * @param neigbours the neigbours to set
     */
    public void setNeigbours(List<ExhibitionSection> neigbours) {
        this.neigbours = neigbours;
    }

    /**
     * @return the exhibitionhall
     */
    public ExhibitionHall getExhibitionhall() {
        return exhibitionhall;
    }

    /**
     * @param exhibitionhall the exhibitionhall to set
     */
    public void setExhibitionhall(ExhibitionHall exhibitionhall) {
        this.exhibitionhall = exhibitionhall;
    }

    /**
     * @return the cschedules
     */
    public List<ConventionSchedule> getCschedules() {
        return cschedules;
    }

    /**
     * @param cschedules the cschedules to set
     */
    public void setCschedules(List<ConventionSchedule> cschedules) {
        this.cschedules = cschedules;
    }
    
}
