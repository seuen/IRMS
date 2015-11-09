/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM;

import CRM.entity.Feedback;
import CRM.entity.MemberAccount;
import CRM.session.FeedbackManagedSessionBeanLocal;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.RateEvent;

/**
 *
 * @author ARIEL CHENG
 */
@ManagedBean
@ViewScoped
public class FeedbackManagedBean {

    @EJB
    private FeedbackManagedSessionBeanLocal fmsbl;
    private Feedback feedback = new Feedback();
    private MemberAccount memberAccount;
    private Integer rating;
    private String Fsubject;
    private String Fsubject2;
    private Map<String, String> subjects = new HashMap<String, String>();
    private Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();
    private Map<String, String> subjects2 = new HashMap<String, String>();

    /**
     * Creates a new instance of FeedbackManagedBean
     */
    public FeedbackManagedBean() {
    }

    @PostConstruct
    public void init() {
        getSubjects().put("Accommodation", "Accommodation");
        getSubjects().put("Food and Beverage", "Food and Beverage");
        getSubjects().put("Attractions", "Attractions");
        getSubjects().put("Shopping Mall", "Shopping Mall");
        getSubjects().put("Entertainment Show", "Entertainment Show");
        getSubjects().put("Exhibition and Convention Hall", "Exhibition and Convention Hall");
        getSubjects().put("Customer Relationship", "Customer Relationship");
        getSubjects().put("Others", "Others");

        Map<String, String> s1 = new HashMap<String, String>();
        s1.put("City Club Hotel", "City Club Hotel");
        s1.put("Coral Bay Hotel", "Coral Bay Hotel");
        s1.put("Crone Plaza Hotel", "Crone Plaza Hotel");
        s1.put("Grand Park Hotel", "Grand Park Hotel");
        s1.put("Singland Hotel", "Singland Hotel");
        s1.put("Others", "Others");

        Map<String, String> s2 = new HashMap<String, String>();
        s2.put("R1", "R1");
        s2.put("R2", "R2");
        s2.put("Others", "Others");

        Map<String, String> s3 = new HashMap<String, String>();
        s3.put("Disney World", "Disney World");
        s3.put("Harry Potter Theme Park", "Harry Potter Theme Park");
        s3.put("Marine Life Travel", "Marine Life Travel");
        s3.put("The Botanic Experimental Museum", "The Botanic Experimental Museum");
        s3.put("Others", "Others");

        Map<String, String> s4 = new HashMap<String, String>();
        s4.put("s1", "s1");
        s4.put("Others", "Others");

        Map<String, String> s5 = new HashMap<String, String>();
        s5.put("Ticket Purchasing", "Ticket Purchasing");
        s5.put("Show Quality", "Show Quality");
        s5.put("Venue", "Venue");
        s5.put("Others", "Others");

        Map<String, String> s6 = new HashMap<String, String>();
        s6.put("Venue Booking Service", "Venue Booking Service");
        s6.put("Facilities", "Facilities");
        s6.put("Staff Training", "Staff Training");
        s6.put("Venue Quality", "Venue Quality");
        s6.put("Others", "Others");

        Map<String, String> s7 = new HashMap<String, String>();
        s7.put("Online Portal Services", "Online Portal Services");
        s7.put("Mobile App Services", "Mobile App Services");
        s7.put("Loyalty Program", "Loyalty Program");
        s7.put("After Sale Service","After Sale Service");
        s7.put("Others","Others");
        
        Map<String, String> s8 = new HashMap<String, String>();
        s8.put("", "");
        
        data.put("Accommodation", s1);
        data.put("Food and Beverage", s2);
        data.put("Attractions", s3);
        data.put("Shopping Mall", s4);
        data.put("Entertainment Show", s5);
        data.put("Exhibition and Convention Hall", s6);
        data.put("Customer Relationship", s7);
        data.put("Others", s8);
    }
    
    public void handleSubjectChange(){
        if(Fsubject!=null&&!Fsubject.equals("")){
              this.setSubjects2(data.get(Fsubject));
        }else{
            this.setSubjects2(new HashMap<String,String>());
        }       
    }

    public void onrate(RateEvent rateEvent) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", "You rated:" + ((Integer) rateEvent.getRating()).intValue());

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void oncancel() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancel Event", "Rate Reset");

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void createFeedback(ActionEvent action) {
        setMemberAccount((MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember"));
        System.err.println("Get memberID = " + getMemberAccount().getId());
        String content=feedback.getContent();
        int rate=feedback.getRating();
        this.setFeedback(fmsbl.createFeedback(memberAccount,content,Fsubject,Fsubject2,rate));
        System.err.println("New feedback is created. Feedback ID = " + getFeedback().getId());
        System.err.println("Before Feedback member loyalty points :" +memberAccount.getMembership().getLoyaltyPoints());
        fmsbl.feedbackRewardPoints(memberAccount);
        System.err.println("After feedback member loyalty points : "+memberAccount.getMembership().getLoyaltyPoints());
        FacesMessage msg = new FacesMessage("You feedback is submitted successfully! 5 loyalty points are rewarded to your membership account. Thank you for your participation!");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }
    
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public MemberAccount getMemberAccount() {
        return (MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember");
    }

    public void setMemberAccount(MemberAccount memberAccount) {
        this.memberAccount = memberAccount;
    }

    /**
     * @return the Fsubject
     */
    public String getFsubject() {
        return Fsubject;
    }

    /**
     * @param Fsubject the Fsubject to set
     */
    public void setFsubject(String Fsubject) {
        this.Fsubject = Fsubject;
    }

    /**
     * @return the Fsubject2
     */
    public String getFsubject2() {
        return Fsubject2;
    }

    /**
     * @param Fsubject2 the Fsubject2 to set
     */
    public void setFsubject2(String Fsubject2) {
        this.Fsubject2 = Fsubject2;
    }

    /**
     * @return the subjects
     */
    public Map<String, String> getSubjects() {
        return subjects;
    }

    /**
     * @param subjects the subjects to set
     */
    public void setSubjects(Map<String, String> subjects) {
        this.subjects = subjects;
    }

    /**
     * @return the data
     */
    public Map<String, Map<String, String>> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Map<String, Map<String, String>> data) {
        this.data = data;
    }

    /**
     * @return the subjects2
     */
    public Map<String, String> getSubjects2() {
        return subjects2;
    }

    /**
     * @param subjects2 the subjects2 to set
     */
    public void setSubjects2(Map<String, String> subjects2) {
        this.subjects2 = subjects2;
    }
}
