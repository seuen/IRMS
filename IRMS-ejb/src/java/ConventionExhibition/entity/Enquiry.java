/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;


@Entity
public class Enquiry implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomtype;
    private int peopleNo;
    private float budget;
    private String specialrequest;
    private String datetype;
    private String type;
    private boolean status;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date enquiryDate;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date StartingDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date EndingDate;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date eventDate;
    private String timeslot;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date startTime;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date endTime;
            
    @ManyToOne
    private Client client;
    

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
        if (!(object instanceof Enquiry)) {
            return false;
        }
        Enquiry other = (Enquiry) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConventionExhibition.entity.Enquiry[ id=" + getId() + " ]";
    }

    /**
     * @return the roomtype
     */
    public String getRoomtype() {
        return roomtype;
    }

    /**
     * @param roomtype the roomtype to set
     */
    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    /**
     * @return the peopleNo
     */
    public int getPeopleNo() {
        return peopleNo;
    }

    /**
     * @param peopleNo the peopleNo to set
     */
    public void setPeopleNo(int peopleNo) {
        this.peopleNo = peopleNo;
    }

    /**
     * @return the budget
     */
    public float getBudget() {
        return budget;
    }

    /**
     * @param budget the budget to set
     */
    public void setBudget(float budget) {
        this.budget = budget;
    }

    /**
     * @return the specialrequest
     */
    public String getSpecialrequest() {
        return specialrequest;
    }

    /**
     * @param specialrequest the specialrequest to set
     */
    public void setSpecialrequest(String specialrequest) {
        this.specialrequest = specialrequest;
    }

    /**
     * @return the datetype
     */
    public String getDatetype() {
        return datetype;
    }

    /**
     * @param datetype the datetype to set
     */
    public void setDatetype(String datetype) {
        this.datetype = datetype;
    }

    /**
     * @return the StartingDate
     */
    public Date getStartingDate() {
        return StartingDate;
    }

    /**
     * @param StartingDate the StartingDate to set
     */
    public void setStartingDate(Date StartingDate) {
        this.StartingDate = StartingDate;
    }

    /**
     * @return the EndingDate
     */
    public Date getEndingDate() {
        return EndingDate;
    }

    /**
     * @param EndingDate the EndingDate to set
     */
    public void setEndingDate(Date EndingDate) {
        this.EndingDate = EndingDate;
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
     * @return the timeslot
     */
    public String getTimeslot() {
        return timeslot;
    }

    /**
     * @param timeslot the timeslot to set
     */
    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @return the enquiryDate
     */
    public Date getEnquiryDate() {
        return enquiryDate;
    }

    /**
     * @param enquiryDate the enquiryDate to set
     */
    public void setEnquiryDate(Date enquiryDate) {
        this.enquiryDate = enquiryDate;
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
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
