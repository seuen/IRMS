/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingMall.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author xing zhe
 */
@Entity
public class Combine implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int test = 0;
    @OneToMany(mappedBy ="combine")
     private Collection<TenantVenue> venues;
    
   public  Combine(){
        
    }
    
   public void createCombine(int test){
        this.setTest(test);
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Combine)) {
            return false;
        }
        Combine other = (Combine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ShoppingMall.entity.Combine[ id=" + id + " ]";
    }

    /**
     * @return the venues
     */
    public Collection<TenantVenue> getVenues() {
        return venues;
    }

    /**
     * @param venues the venues to set
     */
    public void setVenues(Collection<TenantVenue> venues) {
        this.venues = venues;
    }

    /**
     * @return the test
     */
    public int getTest() {
        return test;
    }

    /**
     * @param test the test to set
     */
    public void setTest(int test) {
        this.test = test;
    }
    
}
