/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM.entity;

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
public class Feedback implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date feedbackTime;
    private String content;
    private String subject;
    private String subject2;
    private int rating;
    @ManyToOne
    private MemberAccount memberAccount;
   
    
    public Feedback() {
        
    }
    
    public void create(String content, String subject,String subject2, int rating ){
        this.setContent(content);
        this.setSubject(subject);
        this.setSubject2(subject2);
        this.setRating(rating);
    }
        
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Date feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public MemberAccount getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(MemberAccount memberAccount) {
        this.memberAccount = memberAccount;
    }

    /**
     * @return the subject2
     */
    public String getSubject2() {
        return subject2;
    }

    /**
     * @param subject2 the subject2 to set
     */
    public void setSubject2(String subject2) {
        this.subject2 = subject2;
    }

    /**
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(int rating) {
        this.rating = rating;
    }
    
}
