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
import javax.persistence.OneToMany;

/**
 *
 * @author Acer
 */
@Entity
public class ExhibitionHall implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int sectionAmt;
    private int capacity;
    private String roomNo;
    
    @OneToMany(mappedBy="exhibitionhall")
    private List<ExhibitionSection> sections;
   
    public ExhibitionHall(){
        sections=new ArrayList();
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
        if (!(object instanceof ExhibitionHall)) {
            return false;
        }
        ExhibitionHall other = (ExhibitionHall) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConventionExhibition.entity.ExhibitionHall[ id=" + getId() + " ]";
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
     * @return the roomNo
     */
    public String getRoomNo() {
        return roomNo;
    }

    /**
     * @param roomNo the roomNo to set
     */
    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    /**
     * @return the sectionAmt
     */
    public int getSectionAmt() {
        return sectionAmt;
    }

    /**
     * @param sectionAmt the sectionAmt to set
     */
    public void setSectionAmt(int sectionAmt) {
        this.sectionAmt = sectionAmt;
    }

    /**
     * @return the sections
     */
    public List<ExhibitionSection> getSections() {
        return sections;
    }

    /**
     * @param sections the sections to set
     */
    public void setSections(List<ExhibitionSection> sections) {
        this.sections = sections;
    }

}
