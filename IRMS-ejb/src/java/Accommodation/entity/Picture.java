/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Accommodation.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Picture implements Serializable {

    @Id
    private String name;
    
    public Picture(){
    
    }
    
    public void create(String name){
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Picture)) {
            return false;
        }
        Picture other = (Picture) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Accommodation.entity.Picture[ id=" + name + " ]";
    }

}
