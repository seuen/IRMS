/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author ARIEL CHENG
 */
@Entity
public class Message implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String Title;
    private String Content;
    private Long ReceiverId;
    private boolean ReadorNot;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date MessageTime;
    @ManyToOne
    private StaffAccount staffAccount;
    
    public Message(){
       
    }
    
    public void CreateMessage(String title,String content,Long receiverId){
        
        this.setTitle(title);
        this.setContent(content);
        this.setReceiverId(receiverId);
        this.setReadorNot(false);
        
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
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Common.entity.Message[ id=" + getId() + " ]";
    }

    /**
     * @return the Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * @param Title the Title to set
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     * @return the Content
     */
    public String getContent() {
        return Content;
    }

    /**
     * @param Content the Content to set
     */
    public void setContent(String Content) {
        this.Content = Content;
    }

    /**
     * @return the ReceiverId
     */
    public Long getReceiverId() {
        return ReceiverId;
    }

    /**
     * @param ReceiverId the ReceiverId to set
     */
    public void setReceiverId(Long ReceiverId) {
        this.ReceiverId = ReceiverId;
    }

    /**
     * @return the MessageTime
     */
    public Date getMessageTime() {
        return MessageTime;
    }

    /**
     * @param MessageTime the MessageTime to set
     */
    public void setMessageTime(Date MessageTime) {
        this.MessageTime = MessageTime;
    }

    /**
     * @return the staffAccount
     */
    public StaffAccount getStaffAccount() {
        return staffAccount;
    }

    /**
     * @param staffAccount the staffAccount to set
     */
    public void setStaffAccount(StaffAccount staffAccount) {
        this.staffAccount = staffAccount;
    }

    /**
     * @return the read
     */
    public boolean isReadorNot() {
        return ReadorNot;
    }

    /**
     * @param read the read to set
     */
    public void setReadorNot(boolean ReadorNot) {
        this.ReadorNot = ReadorNot;
    }
    
}
