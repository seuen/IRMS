/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Accommodation.entity;

import FoodBeverage.entity.resReservation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;


@Entity
public class Guest implements Serializable{
    @Id
    private String ic; //NRIC
    private String firstName;
    private String lastName;
     @Temporal(javax.persistence.TemporalType.DATE)
    private Date dob;
    private String title;
    private String contactNum;
    private String emailAddress;
    @OneToOne(cascade={CascadeType.ALL})
    private GuestAddress guestAddress;
    
    @OneToMany(mappedBy="guest")
    private List<resReservation> resreservations;

    
    @OneToMany(mappedBy="guest")
    private Collection<RTReservation> reservations;
    
    public Guest(){
        this.reservations=new ArrayList();
        this.guestAddress=new GuestAddress();
        resreservations=new ArrayList();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIc() != null ? getIc().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Guest)) {
            return false;
        }
        Guest other = (Guest) object;
        if ((this.getIc() == null && other.getIc() != null) || (this.getIc() != null && !this.ic.equals(other.ic))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Accommodation.entity.Guest[ ic=" + getIc() + " ]";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Collection<RTReservation> getReservations() {
        return reservations;
    }

    public void setReservations(Collection<RTReservation> reservations) {
        this.reservations = reservations;
    }

    /**
     * @return the guestAddress
     */
    public GuestAddress getGuestAddress() {
        return guestAddress;
    }

    /**
     * @param guestAddress the guestAddress to set
     */
    public void setGuestAddress(GuestAddress guestAddress) {
        this.guestAddress = guestAddress;
    }

    /**
     * @return the ic
     */
    public String getIc() {
        return ic;
    }

    /**
     * @param ic the ic to set
     */
    public void setIc(String ic) {
        this.ic = ic;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the resreservations
     */
    public List<resReservation> getResreservations() {
        return resreservations;
    }

    /**
     * @param resreservations the resreservations to set
     */
    public void setResreservations(List<resReservation> resreservations) {
        this.resreservations = resreservations;
    }
    
}
