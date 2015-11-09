/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client implements Serializable {
    @Id
    private String ic;
    private String title; //Mr Ms Mrs
    private String firstname;
    private String lastname;
    private String emailAddr;
    private String phoneNum;
    private String addr;
    private String company;
    private String industry;
    
    @OneToMany(mappedBy="client")
    private List<Enquiry> enquiries;
    
    @OneToMany(mappedBy="client")
    private List<EventOrder> events;
//    
    @OneToMany(mappedBy="client",cascade={CascadeType.MERGE,CascadeType.REMOVE})
    private List<ClientBill> clientbills;

    
    public Client(){
        enquiries=new ArrayList();
        events=new ArrayList();
       // clientbills=new ArrayList();
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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.getIc() == null && other.getIc() != null) || (this.getIc() != null && !this.ic.equals(other.ic))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConventionExhibition.entity.Client[ id=" + getIc() + " ]";
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
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the emailAddr
     */
    public String getEmailAddr() {
        return emailAddr;
    }

    /**
     * @param emailAddr the emailAddr to set
     */
    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    /**
     * @return the phoneNum
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * @param phoneNum the phoneNum to set
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * @return the addr
     */
    public String getAddr() {
        return addr;
    }

    /**
     * @param addr the addr to set
     */
    public void setAddr(String addr) {
        this.addr = addr;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the industry
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * @param industry the industry to set
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /**
     * @return the enquiries
     */
    public List<Enquiry> getEnquiries() {
        return enquiries;
    }

    /**
     * @param enquiries the enquiries to set
     */
    public void setEnquiries(List<Enquiry> enquiries) {
        this.enquiries = enquiries;
    }

    /**
     * @return the events
     */
    public List<EventOrder> getEvents() {
        return events;
    }

    /**
     * @param events the events to set
     */
    public void setEvents(List<EventOrder> events) {
        this.events = events;
    }

    /**
     * @return the clientbills
     */
    public List<ClientBill> getClientbills() {
        return clientbills;
    }

    /**
     * @param clientbills the clientbills to set
     */
    public void setClientbills(List<ClientBill> clientbills) {
        this.clientbills = clientbills;
    }
    
}
